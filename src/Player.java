import java.awt.Point;

/**
 * Class to represent a player and encapsulates its location
 *
 */
public class Player {
	
	private Point position;
	
	/** 
	 * Constructs a player given specific coordinates (x, y)
	 * @param x
	 * @param y
	 */
	public Player(int x, int y) {
		position = new Point(x, y);
	}
	
	/**
	 * Constructs a player given a Point object and uses its coordinates
	 * @param p
	 */
	public Player(Point p) {
		position = new Point(p.x, p.y);
	}
	
	/**
	 * Gives the coordinates of this player
	 * @return Point object encapsulating position
	 */
	public Point getCoordinates() {
		return position;
	}
	
	/**
	 * Get x coordinate of this player
	 */
	public int x() {
		return position.x;
	}
	
	/**
	 * Get y coordinate of this player
	 */
	public int y() {
		return position.y;
	}
	
	/**
	 * Moves the player in one of the major 4 direction
	 * @param dir the direction the player is to move in
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
