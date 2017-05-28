package com.Model;

import java.util.Random;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import com.Graph.AStarSearch;
import com.Graph.Heuristic;

public class BoardGenerator {
	private static final Random generator = new Random();
	private static final int MAX_GOALS = 5;
	
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
		AStarSearch<Direction> space;
		
		do {
			do {
				tileMap = LayoutGenerator.genTileMap(numGoals);
			} while (!tileMap.isPathConnected());
			
			System.out.println("loading");
			placeEntities();
			space = getSearchSpace();
			if (space.isSolvable()) break;
			
		} while (!space.isSolvable());
		
		minPushes = space.getActionCount();
	}
	
	private AStarSearch<Direction> getSearchSpace() {
		BoardState start = new BoardState(tileMap, playerPos, boxPositions);
		Heuristic<Direction> heuristic = new BoardHeuristic();
		return new AStarSearch<>(heuristic, start);
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
