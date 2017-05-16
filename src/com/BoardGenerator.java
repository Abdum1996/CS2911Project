package com;

import java.util.ArrayList;
import java.util.Random;

/**
 * Factory class responsible for procedurally generating a game board.
 */
public class BoardGenerator {
	private static final int MAX_GOALS = 10;
	
	public GameBoard genBoard(int width, int height) {
		/*GameBoard.Builder builder = new SokobanBoard.Builder(width, height);
		Random generator = new Random();
		int boardSize = width*height;
		
		int numGoals  = generator.nextInt(MAX_GOALS + 1);
		int numWalls  = generator.nextInt(boardSize - MAX_GOALS + 1);
		int numFloors = boardSize - numGoals - numWalls;
		
		//for (int i = 0)
		
		
		//ArrayList<Tile> tiles = new ArrayList<>()
		
		for (int i = 0; i < MAX_BOXES; ++i) {
			int offset = generator.nextInt(MAX_BOXES + 1);
			Point pos = Point.at(offset % width, offset/width);
			builder.addBox(pos);
		}
		
		
		
		
		//Collections.s*/
		
		return null;
	}
}
