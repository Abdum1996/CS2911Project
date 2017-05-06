import java.awt.Point;

/**
 * Class representing a box to be used in a game grid
 *
 */
public class Box {
	
	private Point position;
	
	/** 
	 * Constructs a box given specific coordinates (x, y)
	 * @param x
	 * @param y
	 */
	public Box(int x, int y) {
		position = new Point(x, y);
	}
	
	/**
	 * Constructs a box given a Point object and uses its coordinates
	 * @param p
	 */
	public Box(Point p) {
		position = new Point(p.x, p.y);
	}
	
	/**
	 * Gives the coordinates of this box
	 * @return Point object encapsulating position
	 */
	public Point getCoordinates() {
		return position;
	}
	
	/**
	 * Get x coordinate of this box
	 */
	public int x() {
		return position.x;
	}
	
	/**
	 * Get y coordinate of this box
	 */
	public int y() {
		return position.y;
	}
	
	/**
	 * Moves the box in one of the major 4 direction
	 * @param dir the direction the box is to be moved in
	 */
	public void move(Direction dir) {
		switch (dir) {
			case DOWN:
				position.y++;
				break;
			case UP:
				position.y--;
				break;
			case RIGHT:
				position.x++;
				break;
			case LEFT:
				position.x--;
				break;
		}
	}
}
