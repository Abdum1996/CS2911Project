package com;

public class Point {
	private final int x;
	private final int y;
	
	public static Point at(int x, int y) {
		return new Point(x, y);
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Point move(Direction dir) {
		switch (dir) {
			case UP:
				return Point.at(x, y + 1);
			case DOWN:
				return Point.at(x, y - 1);
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
