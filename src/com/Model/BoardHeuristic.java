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
		
		// Determine if any boxes are in 'dead lock' positions
		for (Point curr : boxPositions) {
			int validCount = map.getAdjTilePoints(curr).size();
			if (!map.get(curr).equals(Tile.GOAL) && validCount <= 2) return Integer.MAX_VALUE;	
		}
		
		return 0;
	}
}
