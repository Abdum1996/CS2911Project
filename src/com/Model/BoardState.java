package com.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.Graph.State;
import java.util.List;

/**
 * This class keeps track of the state of a game board.
 */
public class BoardState implements State<Direction> {
	private final HashSet<Point> boxPositions;
	private final Point playerPos;
	private final int pushCount;
	private final TileMap map;
	
	public BoardState(TileMap map, Point playerPos, Collection<Point> boxPositions) {
		this.playerPos = playerPos;
		this.boxPositions = new HashSet<>(boxPositions);
		pushCount = 0;
		this.map = map;
	}

	public HashSet<Point> getBoxPositions() {
		return new HashSet<>(boxPositions);
	}
	
	public Point getPlayerPos() {
		return playerPos;
	}
	
	public TileMap getTileMap() {
		return map;
	}
	
	/**
	 * Create a board state from a given game board.
	 * @param board - board which is presumably in an unsolved state
	 */
	public BoardState(TileMap map, Player player, Iterable<Box> boxes) {
		playerPos = player.getPosition();
		boxPositions = new HashSet<>();
		pushCount = 0;
		this.map  = map;
		
		for (Box curr : boxes)
			boxPositions.add(curr.getPosition());
	}
	
	/**
	 * Create successor board state by applying an action to it.
	 * @param state - state to which action is being applied
	 * @param dir   - direction in which player is moved 
	 */
	private BoardState(BoardState state, Direction dir) {
		boxPositions = new HashSet<>(state.boxPositions);
		playerPos = state.playerPos.move(dir);
		map = state.map;
		
		if (boxPositions.contains(playerPos)) {
			boxPositions.remove(playerPos);
			boxPositions.add(playerPos.move(dir));
			pushCount = state.pushCount + 1;
			
		} else {
			pushCount = state.pushCount;
		}
	}
	
	private boolean isValidAction(Direction dir) {
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
	public int getActionCount() {
		return pushCount;
	}
	
	@Override
	public int getStepCost(State<Direction> prev) {
		return 1; // Move costs are uniform for now
	}

	@Override
	public State<Direction> applyAction(Direction dir) {
		return new BoardState(this, dir);
	}

	@Override
	public List<Direction> getValidActions() {
		List<Direction> actions = new ArrayList<>();
		
		for (Direction curr : Direction.values()) {
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
		
		result = prime*result + boxPositions.hashCode();
		result = prime*result + playerPos.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		BoardState other = (BoardState)obj;
		return (boxPositions.equals(other.boxPositions)
				&& playerPos.equals(other.playerPos));
	}
}
