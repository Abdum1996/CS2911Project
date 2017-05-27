package com.Model;

import java.util.NoSuchElementException;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.util.Scanner;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.List;

/**
 * Factory class responsible for creating new game boards, both by reading
 * input map files and through random procedural generation methods.
 * @author Samir Mustavi
 * @author Thomas Daniell
 */
public class BoardGenerator {
	private static final Random generator = new Random();
	private static final int MAX_GOALS = 10;
	private Template[] templates;
	
	public BoardGenerator() {
		int i;
		for (i = 0; i < 17; i++) {
			templates[i] = new Template(getArray(i+1));
		}
	}
	
	/**
	 * Generate a Sokoban board from an input map file.
	 * @param filename - name of text file
	 * @return the new Sokoban board
	 */
	public static SokobanBoard readMap(String filename) {
		SokobanBoard board = null;
		Scanner sc = null;
		
		try {
			sc = new Scanner(new FileReader(filename));
			
			int width = sc.nextInt();
			int height = sc.nextInt();
			
			List<Tile> tiles = new ArrayList<>(width*height);
			List<Point> boxPositions = new ArrayList<>();
			Point playerPos = Point.at(0, 0);
			
			for (int y = 0; y < height; ++y) {
				for (int x = 0; x < width; ++x) {
					String symbol = sc.next();
					tiles.add(Tile.parse(symbol));
					
					Point pos = Point.at(x, y);
					if (symbol.equals("P")) {
						playerPos = pos;
					} else if (symbol.equals("B")) {
						boxPositions.add(pos);
					}
				}
			}
			
			TileMap map = new TileMap(tiles.iterator(), width, height);
			board = new SokobanBoard(map, boxPositions.iterator(), 
					playerPos, width, height);
			
		} catch (FileNotFoundException | NoSuchElementException e) {
			e.printStackTrace();
			
		} finally {
			if (sc != null) sc.close();
		}
		
		return board;
	}
	
	/**
	 * Procedurally generate a new Sokoban board.
	 * @param width  - width of the board in columns 
	 * @param height - height of the board in rows
	 * @return procedurally generated game board
	 */
	public static SokobanBoard genBoard(int width, int height) {
		List<Tile> tiles = new ArrayList<>(width*height);
		
		int numGoals = generator.nextInt(MAX_GOALS) + 1;
		int tilesLeft = width*height - numGoals;
		
		for (int i = 0; i < numGoals; ++i)
			tiles.add(Tile.GOAL);
		
		for (Tile type : Tile.values()) {
			if (type.equals(Tile.EMPTY) || type.equals(Tile.GOAL)) continue;
			int numTiles = generator.nextInt(tilesLeft) + 1;
			tilesLeft -= numTiles;
			
			for (int i = 0; i < numTiles; ++i)
				tiles.add(type);
		}
		
		Collections.shuffle(tiles);
		TileMap map = new TileMap(tiles.iterator(), width, height); 
		
		SokobanBoard board = null;
		do {
			List<Point> boxPositions = new ArrayList<>();
			
			for (int i = 0; i < numGoals; ++i) {
				Point boxPos = genRandomPoint(map);
				
				while (!map.isValidEntityPos(boxPos))
					boxPos = genRandomPoint(map);
					boxPositions.add(boxPos);
			}
			
			Point playerPos = genRandomPoint(map);
			while (!map.isValidEntityPos(playerPos)) {
				playerPos = genRandomPoint(map);
			}
			
			board = new SokobanBoard(map, boxPositions.iterator(),
					playerPos, width, height);
		} while (!board.isSolvable());
		//Collections.s*/
		
		return board;
	}
	
	/**
	 * Generates a random point from a given map, for placing boxes and players
	 * @param map - the map to generate a random point for
	 * @return a random point
	 */
	private static Point genRandomPoint(TileMap map) {
		int offset = generator.nextInt(map.getHeight()*map.getHeight());
		return Point.at(offset % map.getWidth(), offset/map.getHeight());
	}
	
	/**
	 * Produces a 2d char array containing information on a possible empty board for a Sokoban game
	 * @param height - the desired height of the board
	 * @param width - the desired width of the board
	 * @return the char array containing the randomly generated empty board
	 */
	public char[][] emptyBoard(int height, int width) {
		char[][] board = new char[height][width];
		Arrays.fill(board,'E');
		int i = 2;
		int j = 2;
		char[][] tempArray = null;
		Template t = null;
		
		while (i < height) {
			t = templates[generator.nextInt(17)];
			t.modifyTemplate(generator);
			tempArray = t.getTemplateMap();
			if (!properOverlap(board,tempArray,i,j)) continue;
			board = tempToBoard(board,tempArray,i,j);
			j += 3;
			if (j < width) continue;
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
	private boolean properOverlap(char[][] array, char[][] t, int i, int j) {
		int x1 = 0;
		int y1 = 0;
		int x2;
		int y2;
		for (x2 = i-1; x2 < i+4; x2++) {
			for (y2 = j-1; y2 < j+4; y2++) {
				if ((t[x1][y1] != 'E') && (array[x2][y2] != 'E')) {
					if (t[x1][x2] != array[x2][y2]) return false;
				}
			}
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
	private char[][] tempToBoard(char[][] array, char[][] t, int i, int j) {
		int x1 = 0;
		int y1 = 0;
		int x2;
		int y2;
		for (x2 = i-1; x2 < i+4; x2++) {
			for (y2 = j-1; y2 < j+4; y2++) {
				if ((t[x1][y1] != 'E') && (array[x2][y2] == 'E')) array[x2][y2] = t[x1][y1];
			}
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
	    try
	    {
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

	    }
	    catch (FileNotFoundException e) {}

	    finally
	    {
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