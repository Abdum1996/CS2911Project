package com.Model;

import java.util.Collections;
import java.util.Collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the game level interface. Actions here correspond 
 * to player movements and game moves are defined as box pushes.
 */
public class SokobanLevel implements GameLevel {
	private final Difficulty difficulty;
	
	private final Map<Point, Box> boxMap;
	private final TileMap tileMap;
	private Player player;
	
	private final SizedStack<ActionResult> pastActionResults;
	private final SizedStack<Action> pastActions;
	
	private final int minMoves;
	private int undoCount = 0;
	private int moveCount = 0;	
	
	public SokobanLevel(Difficulty difficulty) {
		this.difficulty = difficulty;
		
		boxMap = new HashMap<>();
		tileMap = new TileMap();
		player = new Player(Point.at(0, 0));
		
		int maxMoves = (difficulty.equals(Difficulty.HARD)) ? minMoves*2 : Integer.MAX_VALUE;
		pastActionResults = new SizedStack<>(maxMoves);
		pastActions = new SizedStack<>(maxMoves);
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
	}

	@Override
	public ActionResult getActionResult(Action action) {
		// If the game cannot be won, or already has been won then no actions can occur
		if (!getGameState().equals(State.SOLVABLE)) return ActionResult.NONE;
		
		// Action is invalid if player attempts to move outside of the map or into a wall
		Direction dir = Direction.readAction(action);
		Point next1 = player.getPosition().move(dir);
		if (!tileMap.isValidEntityPos(next1)) return ActionResult.NONE;
		
		// If the adjacent tile has no boxes then a player move can occur
		if (!boxMap.containsKey(next1)) return ActionResult.PLAYER_MOVE;
		
		// Handle case where the maximum allowed box pushes has been reached
		if (moveCount == difficulty.getMaxMoves(minMoves)) return ActionResult.NONE;
		
		// Box must be movable for a box push to occur
		Point next2 = next1.move(dir);
		
		if (!tileMap.isValidEntityPos(next2)) return ActionResult.NONE;
		if (boxMap.containsKey(next2)) return ActionResult.NONE;
		
		return ActionResult.BOX_MOVE;
	}

	@Override
	public boolean applyAction(Action action) {
		ActionResult result = getActionResult(action);
		if (result.equals(ActionResult.NONE)) return false;
		
		pastActionResults.push(result);
		pastActions.push(action);
		
		Direction dir = Direction.readAction(action);
		player = player.move(dir);
		
		if (result.equals(ActionResult.BOX_MOVE)) {
			Point pos = player.getPosition();
			Box newBox = boxMap.remove(pos).move(dir);
			boxMap.put(newBox.getPosition(), newBox);
			
			moveCount++;
		}
		
		return true;
	}

	@Override
	public void undoLastMove() {
		// TODO Auto-generated method stub

	}

	@Override
	public State getGameState() {
		
		return null;
	}
	
	@Override
	public Difficulty getDifficulty() {
		return difficulty;
	}

	@Override
	public int getMinMoves() {
		return minMoves;
	}

	@Override
	public int getUndoCount() {
		return undoCount;
	}

	@Override
	public int getMoveCount() {
		return moveCount;
	}

	@Override
	public Iterable<Tile> getTiles() {
		return tileMap.getTiles();
	}

	@Override
	public Iterable<Box> getBoxes() {
		Collection<Box> boxes = boxMap.values();
		return Collections.unmodifiableCollection(boxes);
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public int getMapWidth() {
		return tileMap.getWidth();
	}

	@Override
	public int getMapHeight() {
		return tileMap.getHeight();
	}
}
