package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of a Sokoban board.
 */
public class SokobanBoard implements GameBoard {
	private final Map<Point, Box> boxMap;
	private final Grid<Tile> tileMap;
	private Player player;
	
	public static class Builder implements GameBoard.Builder {
		private final Grid.Builder<Tile> mapBuilder;
		private final List<Box>  boxList;
		private Player player;
		
		public Builder(Point start, int width, int height) {
			mapBuilder = new TileMap.Builder(width, height);
			boxList = new ArrayList<>();
			player = new Player(start);
		}
		
		@Override
		public void setTile(Tile value, Point point) {
			mapBuilder.set(value, point);	
		}

		@Override
		public void addBox(Point point) {
			boxList.add(new Box(point));
		}

		@Override
		public GameBoard build() {
			Grid<Tile> tileMap = mapBuilder.build();
			Map<Point, Box> boxMap = new HashMap<>();
			
			for (Box curr : boxList) {
				Point pos = curr.getPosition();
				if (tileMap.isValidPoint(pos))
					boxMap.put(pos, curr);
			}
			
			Point pos = player.getPosition();
			if (!tileMap.isValidPoint(pos) || boxMap.containsKey(pos)) return null;
			return new SokobanBoard(boxMap, tileMap, player);
		}
	}

	private SokobanBoard(Map<Point, Box> boxMap, 
			Grid<Tile> tileMap, Player player) {
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
		Point next1 = player.getPosition().move(dir);
		if (!tileMap.isValidPoint(next1)) return false;
		
		// If next position is a box, then the box must be movable
		if (boxMap.containsKey(next1)) {
			Point next2 = next1.move(dir);
			if (!tileMap.isValidPoint(next2)) return false;
			if (boxMap.containsKey(next2)) return false;
		}
		
		return true;
	}

	@Override
	public boolean applyAction(Action action) {
		if (!isValidAction(action)) return false; 
		
		Direction dir = Direction.readAction(action);
		player = player.move(dir);
		
		Box old = boxMap.remove(player.getPosition());
		if (old != null) {
			old = old.move(dir);
			boxMap.put(old.getPosition(), old);
		}
		
		return true;
	}

	@Override
	public boolean gameWon() {
		for (Point point : boxMap.keySet()) {
			Tile tile = tileMap.get(point);
			if (!tile.equals(Tile.GOAL)) return false;
		}
		
		return true;
	}
}
