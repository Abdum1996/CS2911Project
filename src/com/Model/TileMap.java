package com.Model;

import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.List;
import java.util.Map;

/**
 * 2D fixed coordinate grid of tiles. The top left of the grid is located at (0, 0).
 * As you move from left to right, the grid's x coordinate increases, and the
 * y coordinate increases when moving down the grid. This coordinate grid also
 * keeps track of the locations of the goal tiles in the map.
 */
public class TileMap {
	private final Map<Point, Map<Point, Integer>> distances;
	private final List<Point> goals;
	private final List<Tile> tiles;
	
	private final int height;
	private final int width;
	
	/**
	 * Construct a new tile map, adding tiles supplied by the iterator in order of
	 * left to right, top to bottom.
	 * @pre number of tiles supplied by the iterator is >= width*height of map
	 * @param newTiles - collection supplying the tiles
	 * @param width    - width of the tile map in columns
	 * @param height   - height of the tile map in rows
	 */
	public TileMap(Iterable<Tile> newTiles, int width, int height) {
		tiles = new ArrayList<>(width*height);
		goals = new ArrayList<>();
		
		this.width  = width;
		this.height = height;
		
		Iterator<Tile> it = newTiles.iterator();
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				Tile next = it.next();
				tiles.add(next);
				
				if (next.equals(Tile.GOAL))
					goals.add(Point.at(x, y));
			}
		}
		
		// Compute distances between all valid entity points on the map
		distances = new HashMap<>();
		for (Point curr : getFloorPositions()) {
			distances.put(curr, runBFS(curr));
		}	
	}
	
	/**
	 * Get the distance between two points on a map, assuming that
	 * they both are actually located on the map.
	 * @param point1 - first point
	 * @param point2 - second point
	 * @return distance between the two points
	 */
	public int getShortestDist(Point point1, Point point2) {
		return distances.get(point1).get(point2);
	}
	
	/**
	 * Get the distance between the input point and every other point
	 * on the map which are considered to be valid entity positions.
	 * @param point - current position
	 */
	private Map<Point, Integer> runBFS(Point point) {
		Queue<Point> queue = new ArrayDeque<>();
		Map<Point, Integer> dist = new HashMap<>();
		Set<Point> visited = new HashSet<>();
		
		dist.put(point, 0);
		queue.add(point);
		
		while (!queue.isEmpty()) {
			Point point1 = queue.poll();
			if (visited.contains(point1)) continue;
			visited.add(point1);
			
			for (Point point2 : getAdjTilePoints(point1)) {
				if (!visited.contains(point2)) {
					dist.put(point2,  dist.get(point1) + 1);
					queue.offer(point2);
				}
			}
		}
		
		return dist;
	}
	
	/**
	 * Get the tile at the specified location.
	 * @pre grid actually contains the input point
	 * @param point - point representing tiles location
	 * @return tile at the given location
	 */
	public Tile get(Point point) {
		return tiles.get(point.getX() + point.getY()*width);
	}
	
	/**
	 * Get the width of the map in columns.
	 * @return width of map
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Get the height of the map in rows.
	 * @return height of map
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Get a list of all the floor positions in the map.
	 * @return list of points
	 */
	public List<Point> getFloorPositions() {
		List<Point> positions = new ArrayList<>();
		
		for (int y = 0; y < height; ++y) {
			for (int x  = 0; x < width; ++x) {
				Tile tile = tiles.get(x + y*width);
				
				if (tile.equals(Tile.FLOOR) || tile.equals(Tile.GOAL)) {
					Point pos = Point.at(x, y);
					positions.add(pos);
				}
			}
		}
		
		return positions;
	}
	
	/**
	 * Get read-only access to the tiles in the tile map.
	 * @return iterable reference to such tiles
	 */
	public Iterable<Tile> getTiles() {
		return Collections.unmodifiableCollection(tiles);
	}
	
	/**
	 * Get read-only access to the goal positions in the tile map. 
	 * @return iterable reference to the goal positions
	 */
	public Iterable<Point> getGoalPositions() {
		return Collections.unmodifiableCollection(goals);
	}
	
	/**
	 * Determine if a point is within the bounds of the map.
	 * @param point - point being checked
	 * @return true if the map contains the point
	 */
	public boolean hasPoint(Point point) {
		if ((point.getX() < 0) || (point.getX() >= width)) return false;
		if ((point.getY() < 0) || (point.getY() >= height)) return false;
		return true;
	}
	
	/**
	 * Determine if an entity (box or player) can be placed at a given
	 * point in the map. The point is valid if it lies inside the grid
	 * and the corresponding tile is not empty or a wall of some sort.
	 * @param point - point being checked
	 * @return true if entities can be placed at that point
	 */
	public boolean isValidEntityPos(Point point) {
		if (!hasPoint(point)) return false;
		
		Tile tile = get(point);
		return !tile.equals(Tile.EMPTY) && !tile.equals(Tile.WALL);
	}
	
	public List<Point> getAdjTilePoints(Point pos) {
		List<Point> nextPoints = new ArrayList<>();
		
		for (Direction dir : Direction.values()) {
			Point next = pos.move(dir);
			if (isValidEntityPos(next))
				nextPoints.add(next);
		}
		
		return nextPoints;
	}
	
	/**
	 * Determine if every floor on the map is reachable.
	 * @return true if the map is path connected
	 */
	public boolean isPathConnected() {
		List<Point> floorPositions = getFloorPositions();
		Queue<Point> queue = new ArrayDeque<>();
		Set<Point> visited = new HashSet<>();
		
		queue.add(floorPositions.get(0));
		while (!queue.isEmpty()) {
			Point curr = queue.poll();
			if (visited.contains(curr)) continue;
			
			visited.add(curr);
			for (Point next : getAdjTilePoints(curr)) {
				if (!isValidEntityPos(next)) continue;
				if (!visited.contains(next)) queue.add(next);
			}
		}
		
		for (Point curr : floorPositions)
			if (!visited.contains(curr)) return false;
		return true;
	}
}
