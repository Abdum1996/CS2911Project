package com.Model;

import java.util.HashSet;

import com.Graph.Heuristic;
import com.Graph.State;

public class BoardHeuristic implements Heuristic<Direction> {
	@Override
	public int hcost(State<Direction> state) {
		BoardState trueState = (BoardState)state;
		
		HashSet<Point> boxPositions = trueState.getBoxPositions();
		Point playerPos = trueState.getPlayerPos();
		TileMap map = trueState.getTileMap();
		
		int totalCost = 0;
		for (Point boxPos : boxPositions) {
			// Boxes already in goal states obviously can be ignored
			if (map.get(boxPos).equals(Tile.GOAL)) continue;
			
			// Ensure that the box is not in a dead lock
			int validCount = map.getAdjTilePoints(boxPos).size();
			if (validCount <= 2) return Integer.MAX_VALUE;
			
			// Calculate the shortest distance between each box and their closest goal
			int minDist = Integer.MAX_VALUE;
			for (Point goalPos : map.getGoalPositions()) {
				if (boxPositions.contains(goalPos)) continue;
				
				int currDist = map.getShortestDist(boxPos, goalPos);
				if (currDist < minDist)
					minDist = currDist;
			}
			
			totalCost += minDist;
		}
		
		return totalCost;
	}
}
