package com.Model;

import java.util.HashSet;

import com.Graph.Heuristic;
import com.Graph.State;

public class BoardHeuristic implements Heuristic<Direction> {
	@Override
	public int hcost(State<Direction> state) {
		BoardState trueState = (BoardState)state;
		TileMap map = trueState.getTileMap();
		
		HashSet<Point> goalPositions = new HashSet<>(map.getGoalPositions());
		HashSet<Point> boxPositions = trueState.getBoxPositions();
		Point playerPos = trueState.getPlayerPos();
		
		for (Point curr : trueState.getBoxPositions()) {
			if (map.get(curr).equals(Tile.GOAL)) {
				goalPositions.remove(curr);
				boxPositions.remove(curr);
			}	
		}
		
		int totalCost = 0;
		while (!boxPositions.isEmpty()) {
			int minBoxDist = Integer.MAX_VALUE;
			int minGoalDist = Integer.MAX_VALUE;
			
			Point closestGoal = null;
			Point closestBox = null;
			
			// Find the box closest to the player
			for (Point boxPos : boxPositions) {
				int dist = map.getShortestDist(playerPos, boxPos);
				if (dist < minBoxDist) {
					minBoxDist = dist;
					closestBox = boxPos;
				}
			}
			
			// Find the goal closest to the given box
			for (Point goalPos : goalPositions) {
				int dist = map.getShortestDist(closestBox, goalPos);
				if (dist < minGoalDist) {
					minGoalDist = dist;
					closestGoal = goalPos;
				}
			}
			
			totalCost += minBoxDist + minGoalDist;
			playerPos = closestGoal;
			
			boxPositions.remove(closestBox);
			goalPositions.remove(closestGoal);
		}
		
		/*for (Point boxPos : boxPositions) {
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
		}*/
		
		return totalCost;
	}
}
