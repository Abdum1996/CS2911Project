package com.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Random;

import com.Graph.AStarSearch;
import com.Graph.Heuristic;

public class BoardBuilder {
	private static final Random generator = new Random();
	private static final int MAX_GOALS = 10;
	private static final int WIDTH  = 25;
	private static final int HEIGHT = 25;
	
	private final List<Point> boxPositions;
	private final int numGoals;
	private TileMap tileMap;
	private Point playerPos;
	
	public BoardBuilder() {
		numGoals = generator.nextInt(MAX_GOALS) + 1;
		boxPositions = new ArrayList<>();
		genValidBoard();
	}
	
	
	
	private void genValidBoard() {
		do {
			tileMap = genTileMap();
			placeEntities();
		} while (!isSolvable());
	}
	
	private boolean isSolvable() {
		BoardState start = new BoardState(tileMap, playerPos, boxPositions);
		Heuristic<Action> heuristic = new BoardHeuristic();
		return new AStarSearch<>(heuristic, start).isSolvable();
	}
	
	private TileMap genTileMap() {
		List<Tile> newTiles = new ArrayList<>(WIDTH*HEIGHT);
		int tilesLeft = WIDTH*HEIGHT - numGoals;
		
		// Place the goals first
		for (int i = 0; i < numGoals; ++i)
			newTiles.add(Tile.GOAL);
		
		// ensure there are at least n floor positions!!!!
		// Place the remaining tile types
		for (Tile type : Tile.values()) {
			if (type.equals(Tile.EMPTY) || type.equals(Tile.GOAL)) continue;
			int numTiles = generator.nextInt(tilesLeft) + 1;
			tilesLeft -= numTiles;
			
			for (int i = 0; i < numTiles; ++i)
				newTiles.add(type);
		}
		
		// Permute the tiles so that the resulting map is random
		Collections.shuffle(newTiles);
		return new TileMap(newTiles, WIDTH, HEIGHT);
	}
	
	private void placeEntities() {
		boxPositions.clear();
		
		// Get all the possible locations where players and boxes can be placed
		List<Point> floorPositions = tileMap.getFloorPositions();
		Collections.shuffle(floorPositions);
		
		// Extract a random subset of those for entity placement
		for (int i = 0; i < numGoals; ++i)
			boxPositions.add(floorPositions.get(i));
		playerPos = floorPositions.get(numGoals);
	}
}
