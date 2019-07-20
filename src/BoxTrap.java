
class BoxTrap extends Main {

	int x, y, state;
	
	/*
	 * state 0 = open trap
	 * state 1 = successful / caught chin
	 * state 2 = failed box / empty 
	 * state 3 = transitioning trap
	 */

	public BoxTrap(int x, int y, int state) {
		this.x = x;
		this.y = y;
		this.state = state;
	}

	public static String getName(int state) {
		String str = "";
		switch (state) {
		case 0:
			str = "Open trap";
			break;
		case 1:
			str = "Successful";
			break;
		case 2:
			str = "Failed";
			break;
		case 3:
			str = "Transitioning";
			break;

		}
		return str;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Coordinate getCoordinate() {
		return new Coordinate(x, y);
	}
	
	public int getState() {
		return state;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
