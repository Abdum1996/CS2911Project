package com.Model;

import com.Graph.AStarSearch;
import com.Graph.Heuristic;
import com.Graph.State;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Implementation of a Sokoban board.
 */
public class SokobanBoard implements GameBoard {
	private final Map<Point, Box> boxMap;
	private final TileMap tileMap;
	private Player player;
	
	/**
	 * Construct a new Sokoban board. Boxes are placed according to the
	 * input positions and the player is placed at the specified spot.
	 * @param tiles        - iterator providing new tiles to place on the board
	 * @param boxPositions - iterator providing positions to place boxes
	 * @param playerPos    - starting location of the player
	 * @param width        - width of the board in columns
	 * @param height       - height of the board in rows
	 * @pre specified player and box positions are valid
	 */
	public SokobanBoard(TileMap map, Iterator<Point> boxPositions, 
			Point playerPos, int width, int height) {
		boxMap  = new HashMap<>();
		tileMap = map;
		
		while (boxPositions.hasNext()) {
			Point pos = boxPositions.next();
			boxMap.put(pos, new Box(pos));
		}
		
		player = new Player(playerPos);
	}
	
	public Iterable<Tile> getTiles() {
		return tileMap;
	}
	
	public void reset() {
		
	}
	
	@Override
	public Tile getTile(Point point) {
		return tileMap.get(point);
	}

	@Override
	public Iterable<Box> getBoxes() {
		return boxMap.values();
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

	@Override
	public boolean isValidAction(Action action) {
		Direction dir = Direction.readAction(action);
		
		// Ensure player is not moving outside of the map or
		// into a tile that is either empty or a wall
		Point next1 = player.getPosition().move(dir);
		if (!tileMap.isValidEntityPos(next1)) return false;
		
		// If next position is a box, then the box must be movable
		if (boxMap.containsKey(next1)) {
			Point next2 = next1.move(dir);
			if (!tileMap.isValidEntityPos(next2)) return false;
			if (boxMap.containsKey(next2)) return false;
		}
		
		return true;
	}

	@Override
	public ActionResult applyAction(Action action) {
		if (!isValidAction(action)) return ActionResult.NONE; 
		
		Direction dir = Direction.readAction(action);
		
		Player oldPlayer = player;
		player = player.move(dir);
		
		Box old = boxMap.remove(player.getPosition());
		if (old != null) {
			old = old.move(dir);
			boxMap.put(old.getPosition(), old);
			return ActionResult.BOX_MOVE;
		}
		
		return ActionResult.PLAYER_MOVE;
	}
	
	@Override
	public ActionResult getActionResult(Action action) {
		if (!isValidAction(action)) return ActionResult.NONE;
		
		Direction dir = Direction.readAction(action);
		
		Player np = player.move(dir);
		
		Box old = boxMap.get(np.getPosition());
		
		if (old != null) return ActionResult.BOX_MOVE;
		
		return ActionResult.PLAYER_MOVE;
		
	}
	
	@Override
	public boolean gameWon() {
		for (Point point : boxMap.keySet()) {
			Tile tile = tileMap.get(point);
			if (!tile.equals(Tile.GOAL)) return false;
		}
		
		return true;
	}

	@Override
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
	}
	
	public int getMinMoves() {
		return 0;
	}
	
	@Override
	public boolean isSolvable() {
		List<Action> actions = solve();
		if (actions == null) return false;
		return true;
	}

	@Override
	public boolean revertAction(Action action, ActionResult ar) {
		// this function only moves things back
		if (ar == ActionResult.NONE) 
			return true;
		
		Direction dir = Direction.readAction(action);
		
		// reverting player moves
		if (ar == ActionResult.PLAYER_MOVE) {
			dir = Direction.oppositeDirection(dir);
			
			action = Action.readDirection(dir);
			if (Direction.readAction(action) != player.getOrientation())
				applyAction(action); // change orientation
			applyAction(action); // move back
			//change orientation back
			applyAction(Action.readDirection(Direction.oppositeDirection(dir)));
			
		} else if (ar == ActionResult.BOX_MOVE) {
			Box moved = boxMap.remove(player.getPosition().move(dir));
			dir = Direction.oppositeDirection(dir);
			moved = moved.move(dir);// move the box back
			boxMap.put(moved.getPosition(), moved);
			
			action = Action.readDirection(dir);
			if (Direction.readAction(action) != player.getOrientation())
				applyAction(action); // change orientation
			applyAction(action); // move back
			//change orientation back
			applyAction(Action.readDirection(Direction.oppositeDirection(dir)));
			
		}
		return true;
	}
}
