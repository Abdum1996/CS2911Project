package com.Model;

import java.util.Random;

import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Graph.AStarSearch;
import com.Graph.Heuristic;

public class BoardGenerator {
	private static final Random generator = new Random();
	private static final int MAX_GOALS = 10;
	
	private static final int WIDTH  = 25;
	private static final int HEIGHT = 25;
	private static final int SIZE   = WIDTH*HEIGHT;
	
	private final List<Point> boxPositions;
	private final int numGoals;
	
	private TileMap tileMap;
	private Point playerPos;
	private int minPushes;
	
	public BoardGenerator() {
		numGoals = generator.nextInt(MAX_GOALS) + 1;		
		boxPositions = new ArrayList<>();
		genValidBoard();
	}
	
	public TileMap getTileMap() {
		return tileMap;
	}
	
	public Iterable<Point> getBoxPositions() {
		return Collections.unmodifiableCollection(boxPositions);
	}
	
	public Point getPlayerPos() {
		return playerPos;
	}
	
	public int getMinPushes() {
		return minPushes;
	}
	
	private void genValidBoard() {
		AStarSearch<Action> space;
		
		do {
			tileMap = genTileMap();
			placeEntities();
			
			space = getSearchSpace();
			minPushes = space.getActionCount();
		} while (!space.isSolvable());
	}
	
	private AStarSearch<Action> getSearchSpace() {
		BoardState start = new BoardState(tileMap, playerPos, boxPositions);
		Heuristic<Action> heuristic = new BoardHeuristic();
		return new AStarSearch<>(heuristic, start);
	}
	
	private TileMap genTileMap() {
		List<Tile> newTiles = new ArrayList<>(SIZE);
		Map<Tile, Integer> amounts = new HashMap<>();
		
		int minFloors = numGoals + 1;
		int bound = SIZE - numGoals - minFloors;
		int numFloors = generator.nextInt(bound) + minFloors + 1;
		int numWalls = SIZE - numGoals - numFloors;
		
		amounts.put(Tile.GOAL, numGoals);
		amounts.put(Tile.FLOOR, numFloors);
		amounts.put(Tile.WALL, numWalls);
		
		for (Tile type : amounts.keySet()) {
			int numTiles = amounts.get(type);
			
		}
		
		
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
		System.out.println(newTiles.size());
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
