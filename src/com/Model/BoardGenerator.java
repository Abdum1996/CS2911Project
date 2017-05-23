package com.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.List;

/**
 * Factory class responsible for procedurally generating a game board.
 */
public class BoardGenerator {
	private static final int MAX_GOALS = 10;
	
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
