package com;

/**
 * Safe immutable point class with a built in move method.
 */
public class Point {
	private final int x;
	private final int y;
	
	/**
	 * Generate point with coordinates (x, y).
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @return new point
	 */
	public static Point at(int x, int y) {
		return new Point(x, y);
	}
	
	/** Construct a point with coordinates (x, y).
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	private Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get x coordinate.
	 * @return x coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Get y coordinate.
	 * @return y coordinate.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Move point in a given direction.
	 * @param dir - input direction
	 * @return new point
	 */
	public Point move(Direction dir) {
		switch (dir) {
			case UP:
				return Point.at(x, y - 1);
			case DOWN:
				return Point.at(x, y + 1);
			case LEFT:
				return Point.at(x - 1, y);
			case RIGHT:
				return Point.at(x + 1, y);
			default:
				return this;
		}
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		
		result = prime*result + x;
		result = prime*result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Point other = (Point)obj;
		
		return (x == other.x) && (y == other.y);
	}
}
