package com.Model;

import com.Graph.AStarSearch;
import com.Graph.Heuristic;

import java.util.Collections;
import java.util.Collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	 * Construct a new Sokoban level of given difficulty a
	 * procedurally generated game map.
	 */
	public SokobanLevel(Difficulty difficulty) {
		this.difficulty = difficulty;
		boxMap = new HashMap<>();
		
		BoardGenerator generator = new BoardGenerator();
		player = new Player(generator.getPlayerPos());
		tileMap = generator.getTileMap();
		
		int id = 0;
		for (Point pos : generator.getBoxPositions()) {
			boxMap.put(pos, new Box(pos, id));
			id++;
		}
		
		minPushes = generator.getMinPushes();
		tracker = new MoveTracker(difficulty, minPushes);
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
	
	/**
	 * Determine the optimal sequence of actions to solve the level.
	 * @return solved search space
	 */
	private AStarSearch<Direction> solve() {
		BoardState start = new BoardState(tileMap, player, getBoxes());
		Heuristic<Direction> heuristic = new BoardHeuristic();
		return new AStarSearch<>(heuristic, start);
	}
	
	@Override
	public void reset() {
		player = player.resetPosition();
		tracker.reset();
		
		List<Box> boxes = new ArrayList<>(boxMap.values());
		boxMap.clear();
		
		for (Box curr : boxes) {
			curr = curr.resetPosition();
			boxMap.put(curr.getPosition(), curr);
		}
	}

	@Override
	public Move getResultingMove(Action action) {
		// If the game cannot be won, or already has been won then no actions can occur
		if (!getGameState().equals(GameState.NOT_WON)) return Move.none();
		
		// Action is invalid if player attempts to move outside of the map or into a wall
		Direction dir = Direction.readAction(action);
		if (dir == null) return Move.none();
		
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
		
		Point oldPos = player.getPosition();
		
		// Move the player backwards
		Direction dir = Direction.readAction(lastMove.getAction());
		Direction opposite = Direction.oppositeDirection(dir);
		player = player.moveBack(opposite);
		
		// If applicable, pull the pushed box backwards
		if (lastMove.isPush()) {
			Point oldBoxPos = oldPos.move(dir);
			Box newBox = boxMap.remove(oldBoxPos).moveBack(opposite);
			boxMap.put(newBox.getPosition(), newBox);
		}
	}

	@Override
	public GameState getGameState() {
		if (hasWon()) {
			return GameState.WON;
		} else if (tracker.reachedMaxPushes()) {
			return GameState.LOST;
		} else {
			return GameState.NOT_WON;
		}
	}
	
	@Override
	public boolean isSolvable() {
		GameState state = getGameState();
		if (state.equals(GameState.WON)) return true;
		if (state.equals(GameState.LOST)) return false;
		return solve().isSolvable();
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
	public Tile getTile(Point pos) {
		return tileMap.get(pos);
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
