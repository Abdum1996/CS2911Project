package com;

/**
 * Class representing an abstract entity in a game board. Entities
 * are movable objects which keep track of their own position in
 * 2D space, and their own state.
 */
public abstract class Entity {
	private int xcoord;
	private int ycoord;
	
	/**
	 * Construct a new entity at the given x and y coordinates.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public Entity(int x, int y) {
		xcoord = x;
		ycoord = y;
	}
	
	/**
	 * Construct a new entity at the given point.
	 * @param point - location of new entity
	 */
	public Entity(Point point) {
		this(point.getX(), point.getY());
	}
	
	/**
	 * Get the entity's coordinate position.
	 * @return point representing entity's position. 
	 */
	public Point getPosition() {
		return Point.at(xcoord, ycoord);
	}
	
	/**
	 * Get the x coordinate of this entity.
	 * @return x coordinate
	 */
	public int getX() {
		return xcoord;
	}
	
	/**
	 * Get the y coordinate of this entity.
	 * @return y coordinate
	 */
	public int getY() {
		return ycoord;
	}
	
	/**
	 * Move the entity in a given direction.
	 * @param dir - direction of move
	 */
	public void move(Direction dir) {
		switch (dir) {
			case UP:
				ycoord--;
				break;
			case DOWN:
				ycoord++;
				break;
			case LEFT:
				xcoord--;
				break;
			case RIGHT:
				xcoord++;
			default:
				// don't move the entity
		}
	}
	
	/**
	 * Move the entity to the given (x, y) coordinates.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public void moveTo(int x, int y) {
		xcoord = x;
		ycoord = y;
	}
	
	/**
	 * Move the entity to the given point.
	 * @param point - new point
	 */
	public void moveTo(Point point) {
		moveTo(point.getX(), point.getY());
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime*result + xcoord;
		result = prime*result + ycoord;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Entity other = (Entity)obj;
		return (xcoord == other.xcoord) &&
				(ycoord == other.ycoord);
	}
}
