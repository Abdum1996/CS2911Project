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
	
	private final MoveTracker tracker;
	private final int minPushes;
	
	/**
	 * Construct a new Sokoban level of given difficulty and with
	 * a given game map setup, which is assumed to be valid.
	 * @param difficulty   - difficulty of the level
	 * @param map          - input tile map for the board
	 * @param boxPositions - locations where boxes will be placed
	 * @param playerPos    - designated start position for the player
	 */
	public SokobanLevel(Difficulty difficulty, TileMap map, 
			Iterable<Point> boxPositions, Point playerPos) {
		this.difficulty = difficulty;
		
		boxMap  = new HashMap<>();
		tileMap = map;
		player  = new Player(playerPos);
		
		minPushes = 0; // fix!!!!!!!!!
		tracker = new MoveTracker(difficulty, minPushes);
		
		int id = 0;
		for (Point pos : boxPositions) {
			boxMap.put(pos, new Box(pos, id));
			id++;
		}
	}
	
	/**
	 * Determine if the game is in a winning state.
	 * @return true if this is the case
	 */
	private boolean hasWon() {
		for (Point curr : tileMap.getGoalPositions()) {
			if (!boxMap.containsKey(curr)) return false;
		}
		
		return true;
	}
	
	@Override
	public void reset() {
		tracker.reset(); // fix this
	}

	@Override
	public Move getResultingMove(Action action) {
		// If the game cannot be won, or already has been won then no actions can occur
		if (!getGameState().equals(State.NOT_WON)) return Move.none();
		
		// Action is invalid if player attempts to move outside of the map or into a wall
		Direction dir = Direction.readAction(action);
		Point next1 = player.getPosition().move(dir);
		if (!tileMap.isValidEntityPos(next1)) return Move.none();
		
		// If the adjacent tile has no boxes then a player move can occur
		if (!boxMap.containsKey(next1)) return Move.of(action, Action.Result.PLAYER_MOVE);
		
		// Box must be movable for a box push to occur
		Point next2 = next1.move(dir);
		
		if (!tileMap.isValidEntityPos(next2)) return Move.none();
		if (boxMap.containsKey(next2)) return Move.none();
		
		return Move.of(action, Action.Result.BOX_MOVE);
	}

	@Override
	public void applyAction(Action action) {
		Move move = getResultingMove(action);
		if (move.doesNothing()) return;
		
		tracker.addMove(move);
		
		// Move the player accordingly
		Direction dir = Direction.readAction(action);
		player = player.move(dir);
		
		// Move the box if applicable
		if (move.isPush()) {
			Point pos = player.getPosition();
			Box newBox = boxMap.remove(pos).move(dir);
			boxMap.put(newBox.getPosition(), newBox);
		}
	}

	@Override
	public void undoLastMove() {
		Move lastMove = tracker.undoLastMove();
		if (lastMove == null) return;
		
		// Move the player backwards
		Direction dir = Direction.readAction(lastMove.getAction());
		Direction opposite = Direction.oppositeDirection(dir);
		player = player.moveBack(opposite);
		
		// If applicable, pull the pushed box backwards
		if (lastMove.isPush()) {
			Point pos = player.getPosition();
			Box newBox = boxMap.remove(pos).moveBack(dir);
			boxMap.put(newBox.getPosition(), newBox);
		}
	}

	@Override
	public State getGameState() {
		if (hasWon()) {
			return State.WON;
		} else if (tracker.reachedMaxPushes()) {
			return State.LOST;
		} else {
			return State.NOT_WON;
		}
	}
	
	@Override
	public Difficulty getDifficulty() {
		return difficulty;
	}

	@Override
	public int getMinPushes() {
		return minPushes;
	}

	@Override
	public int getPushCount() {
		return tracker.getPushCount();
	}

	@Override
	public int getUndoCount() {
		return tracker.getUndoCount();
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

/*@Override -- Function preserved for later
public List<Action> solve() {
	AStarSearch<Action> searchAlgo = new AStarSearch<>(
			new Heuristic<Action>() {
		@Override
		public int hcost(State<Action> state) {
			return 0;
		}
	});
	
	BoardState start = new BoardState(tileMap, player, getBoxes());
	return searchAlgo.runAStarSearch(start);
}*/
