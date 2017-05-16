package com;

import java.util.ArrayList;
import com.Graph.State;
import java.util.List;

/**
 * This class keeps track of the state of a game board.
 */
public class BoardState implements State<Action> {
	public final GameBoard board;
	
	/**
	 * Create a board state from a given game board.
	 * @param board - board which is presumably in an unsolved state
	 */
	public BoardState(GameBoard board) {
		this.board = board;
	}
	
	@Override
	public int getStepCost(State<Action> prev) {
		return 1; // Move costs are uniform for now
	}

	@Override
	public State<Action> applyAction(Action action) {
		return new BoardState(board.genSuccessor(action));
	}

	@Override
	public List<Action> getValidActions() {
		List<Action> actions = new ArrayList<>();
		
		for (Action curr : Action.values()) {
			if (board.isValidAction(curr))
				actions.add(curr);
		}
		
		return actions;
	}

	@Override
	public boolean isGoalState() {
		return board.gameWon();
	}
}
