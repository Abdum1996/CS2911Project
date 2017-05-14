package com;

import java.util.List;

import com.Graph.State;

public class BoardState implements State<Direction> {
	public 
	
	public BoardState(GameBoard board) {
		
	}
	
	@Override
	public int getStepCost(State<Direction> prev) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public State<Direction> applyAction(Direction action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Direction> getValidActions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isGoalState() {
		// TODO Auto-generated method stub
		return false;
	}

}
