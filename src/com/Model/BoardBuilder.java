package com.Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Factory class responsible for generating a new game board.
 */
public class BoardBuilder {
	private static final Random generator = new Random();
	private static final int MAX_GOALS = 10;
	
	private final List<Point> boxPositions;
	private final List<Tile> newTiles;
	private Point playerPos;
	
	private final int width;
	private final int height;
	
	/**
	 * Generate a Sokoban board from an input map file.
	 * @param filename - name of text file
	 * @return new Sokoban board
	 */
	public static SokobanBoard readMap(String filename) {
		BoardBuilder builder = null;
		Scanner sc = null;
		
		try {
			sc = new Scanner(new FileReader(filename));
			builder = new BoardBuilder(sc.nextInt(), sc.nextInt());
			
			for (int y = 0; y < builder.getHeight(); y++) {
				for (int x = 0; x < builder.getWidth(); x++) {
					String symbol = sc.next();
					Tile tile = Tile.parse(symbol);
					Point pos = Point.at(x, y);
					
					builder.addTile(tile);
					if (symbol.equals("P")) {
						builder.setPlayerPos(pos);
					} else if (symbol.equals("B")) {
						builder.addBoxPos(pos);
					}
				}
			}
			
		} catch (FileNotFoundException | NoSuchElementException e) {
			e.printStackTrace();
			
		} finally {
			if (sc != null) sc.close();
		}
		
		return (builder != null) ? builder.build() : null;
	}
	
	public BoardBuilder(int width, int height) {
		this.width  = width;
		this.height = height;
		
		boxPositions = new ArrayList<>();
		newTiles = new ArrayList<>();
	}
	
	public void setPlayerPos(Point pos) {
		playerPos = pos;
	}
	
	public void addTile(Tile type) {
		newTiles.add(type);
	}
	
	public void addBoxPos(Point pos) {
		boxPositions.add(pos);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public SokobanBoard build() {
		return new SokobanBoard(newTiles.iterator(), boxPositions.iterator(),
				playerPos, width, height);
	}
	
	public GameBoard genBoard(int width, int height) {
		Random generator = new Random();
		
		List<Tile> tileList = new ArrayList<>();
		int tilesLeft = width*height;
		int numGoals = 0;
		
		for (Tile type : Tile.values()) {
			if (type.equals(Tile.EMPTY)) continue;
			int numTiles = 0;
			
			if (type.equals(Tile.GOAL)) {
				numTiles = generator.nextInt(MAX_GOALS) + 1;
				numGoals = numTiles;
			} else {
				numTiles = generator.nextInt(tilesLeft) + 1;
			}
			
			for (int i = 0; i < numTiles; ++i)
				tileList.add(type);
			tilesLeft -= numTiles;
		}
		
		Collections.shuffle(tileList);
		GameBoard.Builder builder = new SokobanBoard.Builder(tileList.iterator(), width, height);
		
		
		
		return null;
	}
}
