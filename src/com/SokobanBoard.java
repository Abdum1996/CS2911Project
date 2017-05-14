package com;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

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
	private final Grid<Tile> tileMap;
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
			
			int width = Integer.parseInt(sc.nextLine());
			int height = Integer.parseInt(sc.nextLine());
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
		private final Grid.Builder<Tile> mapBuilder;
		private final List<Box>  boxList;
		private Player player;
		
		/**
		 * Construct a board builder.
		 * @param width  - width of the board (in rows) to be generated
		 * @param height - height of the board (in columns) to be generated 
		 */
		public Builder(int width, int height) {
			mapBuilder = new TileMap.Builder(width, height);
			player  = new Player(Point.at(0, 0));
			boxList = new ArrayList<>();
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
		public void setPlayerPos(Point point) {
			player = player.moveTo(point);
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

	/**
	 * Construct Sokoban board from a map of tiles and entities.
	 * @param boxMap  - set of boxes and their corresponding positions
	 * @param tileMap - underlying tile map
	 * @param player  - player stored on the map
	 */
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
}
