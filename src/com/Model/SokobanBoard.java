package com.Model;

import com.Graph.AStarSearch;
import com.Graph.Heuristic;
import com.Graph.State;

import java.util.NoSuchElementException;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.HashMap;
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
	 * Generate a Sokoban board from an input map file.
	 * @param filename - name of text file
	 * @return new Sokoban board
	 */
	public static GameBoard readFile(String filename) {
		GameBoard board = null;
		Scanner sc = null;
		
		try {
			sc = new Scanner(new FileReader(filename));
			
			int width = sc.nextInt();
			int height = sc.nextInt();
			Builder builder = new Builder(width, height);
			
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					String symbol = sc.next();
					Tile tile = Tile.parse(symbol);
					Point pos = Point.at(x, y);
					
					builder.setTile(tile, pos);
					if (symbol.equals("P")) {
						builder.setPlayerPos(pos);
					} else if (symbol.equals("B")) {
						builder.addBox(pos);
					}
				}
				
				System.out.println();
			}
			
			board = builder.build();
			
		} catch (FileNotFoundException | NoSuchElementException e) {
			e.printStackTrace();
			
		} finally {
			if (sc != null) sc.close();
		}
		
		return board;
	}
	
	/**
	 * Builder class for generating a game board.
	 */
	public static class Builder implements GameBoard.Builder {
		private final List<Box>  boxList;
		private final TileMap tileMap;
		private Player player;
		
		/**
		 * Construct a board builder.
		 * @param width  - width of the board (in rows) to be generated
		 * @param height - height of the board (in columns) to be generated 
		 */
		public Builder(int width, int height) {
			tileMap = new TileMap(width, height);
			player  = new Player(Point.at(0, 0));
			boxList = new ArrayList<>();
		}
		
		@Override
		public void setTile(Tile value, Point point) {
			tileMap.set(value, point);	
		}

		@Override
		public void addBox(Point point) {
			boxList.add(new Box(point, boxList.size()));
		}
		
		@Override
		public void setPlayerPos(Point point) {
			player = player.moveTo(point);
		}

		@Override
		public GameBoard build() {
			Map<Point, Box> boxMap = new HashMap<>();
			
			for (Box curr : boxList) {
				Point pos = curr.getPosition();
				if (tileMap.isValidEntityPos(pos))
					boxMap.put(pos, curr);
			}
			
			Point pos = player.getPosition();
			if (!tileMap.isValidEntityPos(pos) || boxMap.containsKey(pos)) return null;
			return new SokobanBoard(boxMap, tileMap, player);
		}
	}

	/**
	 * Construct Sokoban board from a map of tiles and entities.
	 * @param boxMap  - set of boxes and their corresponding positions
	 * @param tileMap - underlying tile map
	 * @param player  - player stored on the map
	 */
	private SokobanBoard(Map<Point, Box> boxMap, 
			TileMap tileMap, Player player) {
		this.boxMap  = boxMap;
		this.tileMap = tileMap;
		this.player  = player;
	}
	
	@Override
	public Tile getTile(Point point) {
		return tileMap.get(point);
	}

	@Override
	public List<Box> getBoxes() {
		return new ArrayList<>(boxMap.values());
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
		if (dir != player.getOrientation()) return true; //changing orientation always valid
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
		if (player.getOrientation() != oldPlayer.getOrientation()) return ActionResult.CHANGE_ORIENTATION;
		
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
		
		if (player.getOrientation() != player.move(dir).getOrientation()) return ActionResult.CHANGE_ORIENTATION;
		Player np = player.move(dir);
		
		Box old = boxMap.get(np.getPosition());
		
		if (old != null) return ActionResult.BOX_MOVE;
		
		return ActionResult.PLAYER_MOVE;
		
	}
	
	@Override 
	public GameBoard genSuccessor(Action action) {
		GameBoard successor = new SokobanBoard(new HashMap<>(boxMap), tileMap, player);
		successor.applyAction(action);
		return successor;
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
		
		return searchAlgo.runAStarSearch(new BoardState(this));
	}

	@Override
	public boolean revertAction(Action action, ActionResult ar) {
		// this function doesn't change orientation (trivial), it only moves things back
		if (ar == ActionResult.CHANGE_ORIENTATION || ar == ActionResult.NONE) 
			return true;
		
		Direction dir = Direction.readAction(action);
		// reverting player moves
		if (ar == ActionResult.PLAYER_MOVE) {
			dir = Direction.oppositeDirection(dir);
			action = Action.readDirection(dir);
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
			applyAction(action); // change orientation
			applyAction(action); // move back
			//change orientation back
			applyAction(Action.readDirection(Direction.oppositeDirection(dir)));
			
		}
		return true;
	}
}
