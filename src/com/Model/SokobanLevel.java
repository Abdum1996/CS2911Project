package com.Model;

import java.util.Collections;
import java.util.Collection;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.Deque;
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
	
	private final Deque<ActionResult> pastActionResults;
	private final Deque<Action> pastActions;
	
	private final int minMoves;
	private int undoCount = 0;
	private int moveCount = 0;	
	
	public SokobanLevel(Difficulty difficulty) {
		this.difficulty = difficulty;
		
		boxMap = new HashMap<>();
		tileMap = new TileMap();
		player = new Player(Point.at(0, 0));
		
		
		pastActionResults = new LinkedList<>();
		pastActions = new LinkedList<>();
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
	}

	@Override
	public ActionResult getActionResult(Action action) {
		ActionResult result = ActionResult.PLAYER_MOVE;
		Direction dir = Direction.readAction(action);
		
		// Ensure player is not moving outside of the map or
		// into a tile that is either empty or a wall
		Point next1 = player.getPosition().move(dir);
		if (!tileMap.isValidEntityPos(next1)) return ActionResult.NONE;
		
		
		
		// If next position is not a box then the result is a player move
		if (!boxMap.containsKey(next1)) return ActionResult.PLAYER_MOVE;
		
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
		// TODO Auto-generated method stub
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
