package com.Model;

import java.util.HashSet;

import com.Graph.Heuristic;
import com.Graph.State;

public class BoardHeuristic implements Heuristic<Action> {
	@Override
	public int hcost(State<Action> state) {
		BoardState trueState = (BoardState)state;
		int cost = 0;
		
		HashSet<Point> boxPositions = trueState.getBoxPositions();
		Point playerPos = trueState.getPlayerPos();
		TileMap map = trueState.getTileMap();
		
		for (Point curr : boxPositions) {
			Direction[] directions = { Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT };
			for (int i = 0; i < directions.length; ++i) {
				
			}
			
			Point[] points = { curr, curr, curr, curr };
			
			
			
			Point[] points = { curr.move(Direction.UP), curr.move(dir)Direction.DOWN, 
					Direction.LEFT, Direction.RIGHT };
			
			
			
			
			
		}
		
		return cost;
	}
}
