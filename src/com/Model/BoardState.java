package com.Model;

import java.util.ArrayList;
import java.util.HashSet;

import com.Graph.State;
import java.util.Iterator;
import java.util.List;

/**
 * This class keeps track of the state of a game board.
 */
public class BoardState implements State<Action> {
	private final HashSet<Point> boxPositions;
	private final Point playerPos;
	private final TileMap map;
	
	/**
	 * Create a board state from a given game board.
	 * @param board - board which is presumably in an unsolved state
	 */
	public BoardState(TileMap map, Player player, Iterator<Box> boxes) {
		playerPos = player.getPosition();
		boxPositions = new HashSet<>();
		this.map = map;
		
		while (boxes.hasNext()) {
			Box curr = boxes.next();
			boxPositions.add(curr.getPosition());
		}
	}
	
	/**
	 * Create successor board state by applying an action to it.
	 * @param state - state to which action is being applied
	 */
	private BoardState(BoardState state, Action action) {
		Direction dir = Direction.readAction(action);
		
		boxPositions = new HashSet<>(state.boxPositions);
		playerPos = state.playerPos.move(dir);
		map = state.map;
		
		if (boxPositions.contains(playerPos)) {
			boxPositions.remove(playerPos);
			boxPositions.add(playerPos.move(dir));
		}
	}
	
	private boolean isValidAction(Action action) {
		Direction dir = Direction.readAction(action);
		
		Point next1 = playerPos.move(dir);
		if (!map.isValidEntityPos(next1)) return false;
		
		// If next position is a box, then the box must be movable
		if (boxPositions.contains(next1)) {
			Point next2 = next1.move(dir);
			if (!map.isValidEntityPos(next2)) return false;
			if (boxPositions.contains(next2)) return false;
		}
		
		return true;
	}
	
	@Override
	public int getStepCost(State<Action> prev) {
		return 1; // Move costs are uniform for now
	}

	@Override
	public State<Action> applyAction(Action action) {
		return new BoardState(this, action);
	}

	@Override
	public List<Action> getValidActions() {
		List<Action> actions = new ArrayList<>();
		
		for (Action curr : Action.values()) {
			if (isValidAction(curr))
				actions.add(curr);
		}
		
		return actions;
	}

	@Override
	public boolean isGoalState() {
		for (Point curr : boxPositions) {
			Tile tile = map.get(curr);
			
			if (!tile.equals(Tile.GOAL)) return false;
		}
		
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boxPositions == null) ? 0 : boxPositions.hashCode());
		result = prime * result + ((playerPos == null) ? 0 : playerPos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardState other = (BoardState) obj;
		if (boxPositions == null) {
			if (other.boxPositions != null)
				return false;
		} else if (!boxPositions.equals(other.boxPositions))
			return false;
		if (playerPos == null) {
			if (other.playerPos != null)
				return false;
		} else if (!playerPos.equals(other.playerPos))
			return false;
		return true;
	}
	
	
}
