package com.Model;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Factory class responsible for creating new tile maps,
 * through random procedural generation methods.
 * @author Samir Mustavi
 * @author Thomas Daniell
 */
public class BoardGeneratorOld {
	private static final Random generator = new Random();
	private static final int MAX_GOALS = 10;
	private Template[] templates;
	
	public BoardGeneratorOld() {
		templates = new Template[17];
		
		for (int i = 0; i < 17; i++) {
			templates[i] = new Template(getArray(i+1));
		}
	}
	
	public static TileMap genTileCollection() {
		BoardGeneratorOld gen = new BoardGeneratorOld();
		char[][] emptyBoard = gen.emptyBoard(GameConstants.MAX_MAP_HEIGHT, 
				GameConstants.MAX_MAP_WIDTH);
		
		
		List<Point> floorList = new ArrayList<>();
		for (int i = 0; i < GameConstants.MAX_MAP_HEIGHT; ++i) {
			for (int j = 0; j < GameConstants.MAX_MAP_WIDTH; ++j) {
				if (emptyBoard[i][j] == 'F') {
					floorList.add(Point.at(j, i));
				}
			}
		}
		
		int numGoals = generator.nextInt(MAX_GOALS) + 1;
		Collections.shuffle(floorList);
			
		for (int i = 0; i < numGoals; ++i) {
			Point pos = floorList.get(i);
			emptyBoard[pos.getY()][pos.getX()] = 'G';
		}
		
		List<Tile> tiles = new ArrayList<>();
		for (int i = 0; i < GameConstants.MAX_MAP_HEIGHT; ++i) {
			for (int j = 0; j < GameConstants.MAX_MAP_WIDTH; ++j) {
				char symbol = emptyBoard[i][j];
				Tile tile;
				
				switch (symbol) {
					case 'E':
					case 'W':
						tile = Tile.WALL;
					case 'G':
						tile = Tile.GOAL;
					case 'F':
						tile = Tile.FLOOR;
					default:
						tile = Tile.EMPTY;	
				}
				
				tiles.add(tile);
			}
		}
		
		TileMap map = new TileMap(tiles, GameConstants.MAX_MAP_WIDTH, 
				GameConstants.MAX_MAP_HEIGHT);
		return map;
	}
	
	/**
	 * Produces a 2d char array containing information on a possible empty board for a Sokoban game
	 * @param height - the desired height of the board
	 * @param width - the desired width of the board
	 * @return the char array containing the randomly generated empty board
	 */
	public char[][] emptyBoard(int height, int width) {
		char[][] board = new char[height][width];
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				board[i][j] = 'E';
			}
		}
		
		int i = 2;
		int j = 2;
		char[][] tempArray = null;
		Template t = null;
		
		while (i < height - 4) {
			t = templates[generator.nextInt(17)];
			t.modifyTemplate(generator);
			
			tempArray = t.getTemplateMap();
			if (!properOverlap(board,tempArray,i,j)) continue;
			board = tempToBoard(board,tempArray,i,j);
			j += 3;
			if (j < width - 4) continue;
			j = 2;
			i += 3;
		}
		
		return board;
	}
	
	/**
	 * Called by emptyBoard to check if a template can be placed on the given board at a given set of points (3x3 space on the board)
	 * The overlap from template and board allow for more interesting puzzles to be created
	 * @param array - the board on which to place the template
	 * @param t - the template to place on the board
	 * @param i - the vertical index of the board to place the template
	 * @param j - the horizontal index of the board to place the template
	 * @return true if the template can be placed on the board with no conflicting overlaps, false otherwise
	 */
	private static boolean properOverlap(char[][] array, char[][] t, int i, int j) {
		int x1 = 0;
		int y1 = 0;
		int x2;
		int y2;
		for (x2 = i-1; x2 < i+4; x2++) {
			for (y2 = j-1; y2 < j+4; y2++) {
				if ((t[x1][y1] != 'E') && (array[x2][y2] != 'E')) {
					if (t[x1][y1] != array[x2][y2]) return false;
				}
				y1++;
			}
			y1 = 0;
			x1++;
		}
		return true;
	}
	
	/**
	 * Called by emptyBoard to place a template on the board at a given set of points (3x3 space on the board)
	 * @param array - the board on which to place the template
	 * @param t - the template to place on the board
	 * @param i - the vertical index of the board to place the template
	 * @param j - the horizontal index of the board to place the template
	 * @return the same board with the template added on
	 */
	private static char[][] tempToBoard(char[][] array, char[][] t, int i, int j) {
		int x1 = 0;
		int y1 = 0;
		int x2;
		int y2;
		for (x2 = i-1; x2 < i+4; x2++) {
			for (y2 = j-1; y2 < j+4; y2++) {
				if ((t[x1][y1] != 'E') && (array[x2][y2] == 'E')) array[x2][y2] = t[x1][y1];
				y1++;
			}
			y1 = 0;
			x1++;
		}
		return array;
	}

	/**
	 * Scans the desired text file in the Templates folder to get a 2d array containing template information
	 * @param index - the template file to extract
	 * @return the 2d char array for the template
	 */
	public char[][] getArray(int index) {
		String s = "./resources/Templates/t" + index + ".txt";
		Scanner sc = null;
	    char array[][] = new char[5][5];
	    
	    try {
	        sc = new Scanner(new FileReader(s));
		    String curr = null;
		    String[] c = null;
		    int i,j = 0;
		    
		    while ((sc.hasNextLine()) || (j < 5)) {    
		    	curr = sc.nextLine();
		    	c = curr.split(" ");
		    	
		    	for (i = 0; i < 5; i++) {
		    		array[j][i] = c[i].charAt(0);
		    	}
		    	j++;
		    }

	    } catch (FileNotFoundException e) {
	    	// do nothing
	    } finally {
	        if (sc != null) sc.close();
	    }
	    
		return array;
	}
	
	/**
	 * Determines whether a produced empty board has too much floor space in single areas (this detracts from possibly interesting boards) usually in 4x3 grids
	 * @param emptyMap - the map to check
	 * @param height - the height of the map
	 * @param width - the width of the map
	 * @return true if there are any big board spaces in the map (which would be disregarded), false otherwise
	 */
	public boolean isHugeFloorSpace(char[][] emptyMap, int height, int width) {
		int i,j;
		for (i = 0; i < height-4; i++) {
			for (j = 0; j < width-4; j++) {
				if (emptyMap[i][j] == 'F') {
					if ((emptyMap[i+1][j] == 'F') && (emptyMap[i+2][j] == 'F') &&
						(emptyMap[i][j+1] == 'F') && (emptyMap[i+1][j+1] == 'F') &&
						(emptyMap[i+2][j+1] == 'F') && (emptyMap[i][j+2] == 'F') &&
						(emptyMap[i+1][j+2] == 'F') && (emptyMap[i+2][j+2] == 'F')) {
						if ((emptyMap[i+3][j] == 'F') && (emptyMap[i+3][j+1] == 'F') &&
							(emptyMap[i+3][j+2] == 'F')) {
							return true;
						}
						if ((emptyMap[i][j+3] == 'F') && (emptyMap[i+1][j+3] == 'F') &&
							(emptyMap[i+2][j+3] == 'F')) {
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
}
