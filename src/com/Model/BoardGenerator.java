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
 */
public class BoardGenerator {
	private static final Random generator = new Random();
	private static final int MAX_GOALS = 10;
	
	/**
	 * Generate a Sokoban board from an input map file.
	 * @param filename - name of text file
	 * @return new Sokoban board
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
	
	private static Point genRandomPoint(TileMap map) {
		int offset = generator.nextInt(map.getHeight()*map.getHeight());
		return Point.at(offset % map.getWidth(), offset/map.getHeight());
	}
	
	public char[][] emptyBoard(int height, int width) {
		char[][] board = new char[height][width];
		Arrays.fill(board,'E');
		int i = 3 - height % 3;
		int j = 3 - width  % 3;
		char[][] tempArray = null;
		Template t = null;
		
		while (i < height) {
			tempArray = getArray(generator.nextInt(17)+1);
			t = new Template(tempArray);
			t.modifyTemplate();
			if (!properOverlap(board,t,i,j)) continue;
			tempToBoard(board,t,i,j);
			j += 3;
			if (j < width) continue;
			j = 3 - width  % 3;
			i += 3;
		}
		
		return board;
	}
	
	private void tempToBoard(char[][] array, Template t, int i, int j) {
		int x1,y1 = 0;
		int x2,y2 = 2;
		if (i < 3) x1 = 1;
		if (j < 3) y1 = 1;
	}

	private boolean properOverlap(char[][] array, Template t, int i, int j) {
		
		return false;
	}

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
}
