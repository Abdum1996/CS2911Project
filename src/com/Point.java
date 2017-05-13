package com;

/**
 * Safe immutable point class with a built in move method. Its 
 * primary purpose is for information passing.
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
	
	public Point(int x, int y) {
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
