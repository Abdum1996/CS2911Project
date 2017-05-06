import java.awt.Point;

public class Box {
	private Point position;
	
	public Box(int x, int y) {
		position = new Point(x, y);
	}
	
	public Box(Point p) {
		position = new Point(p.x, p.y);
	}
	
	public Point getCoordinates() {
		return position;
	}
	
	public int x() {
		return position.x;
	}
	
	public int y() {
		return position.y;
	}
	
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
