
/**
 * Used for placing pixel coordinates in a clean way
 */

class Coordinate extends Main {

	int x;
	int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
//		int windowX = x + getFoundWindowX();
//		int windowY = x + getFoundWindowY();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
