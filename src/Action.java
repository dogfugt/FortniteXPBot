import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("Duplicates")

public class Action {

	static int r,g,b;
	
	static boolean clicked;

	public static Color pickedColor;
	
	static PointerInfo pointerinfo = MouseInfo.getPointerInfo();
	static Point point = pointerinfo.getLocation();

	static int MouseX = (int) point.getX(), MouseY = (int) point.getY();

	

	/*public static Color getPickedColor() {
		return pickedColor;
	}
	
	public static void setPickedColor(Color newPickedColor, Robot robot, int x, int y) {
		int r,g,b;
		newPickedColor = robot.getPixelColor(x, y);
		r = newPickedColor.getRed();
		g = newPickedColor.getGreen();
		b = newPickedColor.getBlue();
		
//		newPickedColor = (r, g, b);
		pickedColor = newPickedColor;
	}*/
	
/*	public static getPickedColorRGB(int r, int g, int b) {
		return setPickedColorRGB(r, g, b);
	}*/


	
	
	/* no delay */
	
	public static void mouseMoveSide(int x) throws AWTException {
		Robot r = new Robot();
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		
		int y = (int) b.getY();
		
		for(int i = 0; i < x; i++) {
			r.mouseMove(i, 520);
//			r.delay(10);
		}
	}
	public static void mouseMoveLinear(int x2, int y2, int t, int n) throws AWTException, InterruptedException {
		Robot r = new Robot();
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
//		
//		if(CheckColor.secondMonitorInUse())
//			x2 =+ CheckColor.secondMonitorWidth;
		
		int x1 = (int) b.getX();
		int y1 = (int) b.getY();
//		x1 =50; y1 = 50;

		double dx = (x2 - x1) / ((double) n);
		double dy = (y2 - y1) / ((double) n);
		double dt = t / ((double) n);
		for (int step = 1; step <= n; step++) {
			Thread.sleep((int) dt);
			r.mouseMove((int) (x1 + dx * step), (int) (y1 + dy * step));
		}
	}
	
	public static void mouseMoveLinear(int x2, int y2) throws AWTException, InterruptedException {
		
//		int t = 200, n = 20;
	
		int t = Main.randInt(150,400);
		int n = Main.randInt(15, 50);
		
		Robot r = new Robot();
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		
		if(CheckColor.secondMonitorInUse())
			x2 =+ CheckColor.secondMonitorWidth;
		
		int x1 = (int) b.getX();
		int y1 = (int) b.getY();
//		x1 =50; y1 = 50;

		double dx = (x2 - x1) / ((double) n);
		double dy = (y2 - y1) / ((double) n);
		double dt = t / ((double) n);
		for (int step = 1; step <= n; step++) {
			Thread.sleep((int) dt);
			r.mouseMove((int) (x1 + dx * step), (int) (y1 + dy * step));
		}
	}
	
	public static void mouseMove(Coordinate coord) throws AWTException {
		Robot robot = new Robot();
		
		int x = coord.x;
		int y = coord.y;
		
		if(CheckColor.secondMonitorInUse())
			x =+ CheckColor.secondMonitorWidth;
		
		try {
			robot.mouseMove(x, y);
		}catch(Exception e) {}
	}
	
	public static void mouseMove(int x, int y) throws AWTException {
		Robot robot = new Robot();
				
//		if(CheckColor.secondMonitorInUse())
//			x =+ CheckColor.secondMonitorWidth;
		
		try {
			robot.mouseMove(x, y);
		}catch(Exception e) {}
	}
	
	
	public static void left(Coordinate coord) throws AWTException {
		Robot robot = new Robot();
		
		int x = coord.x;
		int y = coord.y;
		
		if(CheckColor.secondMonitorInUse())
			x =+ CheckColor.secondMonitorWidth;
		
		try {
			robot.mouseMove(x, y);
			clicked = true;
//			setPickedColor(getPickedColor(), robot, x, y);
			left();
//			robot.mouseMove(MouseX, MouseY);
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: lClickAt");
		}
	}
	
	public static void left(Coordinate coord, int ms) throws AWTException, InterruptedException {
		Robot robot = new Robot();
		
		int x = coord.x;
		int y = coord.y;
		
		if(CheckColor.secondMonitorInUse())
			x =+ CheckColor.secondMonitorWidth;
		
		try {
			robot.mouseMove(x, y);
			clicked = true;
//			setPickedColor(getPickedColor(), robot, x, y);
			left(robot, ms);
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: lClickAt");
		}
	}
	
	public static void right(Coordinate coord) throws AWTException {
		Robot robot = new Robot();
		
		int x = coord.x;
		int y = coord.y;
		
		if(CheckColor.secondMonitorInUse())
			x =+ CheckColor.secondMonitorWidth;
		
		try {
			robot.mouseMove(x, y);
			clicked = true;
//			setPickedColor(getPickedColor(), robot, x, y);
			right(robot);
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: rClickAt");
		}
	}
	
	public static void right(Coordinate coord, int ms) throws AWTException, InterruptedException {
		Robot robot = new Robot();
		
		int x = coord.x;
		int y = coord.y;
		
		if(CheckColor.secondMonitorInUse())
			x =+ CheckColor.secondMonitorWidth;

		try {
			robot.mouseMove(x, y);
			clicked = true;
//			setPickedColor(getPickedColor(), robot, x, y);
			right(robot, ms);
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: rClickAt");
		}
	}
	
	public static void left(int x, int y) throws AWTException {
		Robot robot = new Robot();
		
		if(CheckColor.secondMonitorInUse())
			x =+ CheckColor.secondMonitorWidth;

		try {
			robot.mouseMove(x, y);
			clicked = true;
//			setPickedColor(getPickedColor(), robot, x, y);
			left();
//			robot.mouseMove(MouseX, MouseY);
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: lClickAt");
		}
	}
	
	public static void left(int x, int y, int ms) throws AWTException, InterruptedException {
		Robot robot = new Robot();
		
		if(CheckColor.secondMonitorInUse())
			x =+ CheckColor.secondMonitorWidth;

		try {
			robot.mouseMove(x, y);
			clicked = true;
//			setPickedColor(getPickedColor(), robot, x, y);
			left(robot, ms);
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: lClickAt");
		}
	}

	public static void right(int x, int y) throws AWTException {
		Robot robot = new Robot();
		
		if(CheckColor.secondMonitorInUse())
			x =+ CheckColor.secondMonitorWidth;

		try {
			robot.mouseMove(x, y);
			clicked = true;
//			setPickedColor(getPickedColor(), robot, x, y);
			right(robot);
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: rClickAt");
		}
	}
	
	public static void right(int x, int y, int ms) throws AWTException, InterruptedException {
		Robot robot = new Robot();

		if(CheckColor.secondMonitorInUse())
			x =+ CheckColor.secondMonitorWidth;
		
		try {
			robot.mouseMove(x, y);
			clicked = true;
//			setPickedColor(getPickedColor(), robot, x, y);
			right(robot, ms);
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: rClickAt");
		}
	}
	
	public static void left() throws AWTException {
		Robot r = new Robot();

		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	public static void right(Robot r) throws AWTException {
		r.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		r.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
	}
	
	public static void left(Robot r, int ms) throws AWTException {
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		r.delay(ms);
	}

	public static void right(Robot r, int ms) throws AWTException {
		r.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		r.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		r.delay(ms);
	}

	/* Sleep methods */
	/*public static void lClickAt(int x, int y, int min, int max) throws AWTException {
		Robot r = new Robot();
		try {
			r.mouseMove(x, y);
			
			r.mousePress(InputEvent.BUTTON1_DOWN_MASK);

			if (Main.randomSleep)
				r.delay(Main.randInt(min, max));

			r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			if (Main.randomSleep)
				r.delay(Main.randInt(min, max));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/
	
	public void left(Robot r, int min, int max) throws AWTException {
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);

		if (Main.randomSleep)
			r.delay(Main.randInt(min, max));

		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

		if (Main.randomSleep)
			r.delay(Main.randInt(min, max));
	}

	public void right(Robot r, int min, int max) throws AWTException {
		r.mousePress(InputEvent.BUTTON3_DOWN_MASK);

		if (Main.randomSleep)
			r.delay(Main.randInt(min, max));

		r.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);

		if (Main.randomSleep)
			r.delay(Main.randInt(min, max));
	}
	
	public static void escape(Robot r) {
		r.keyPress(KeyEvent.VK_ESCAPE);
		r.keyRelease(KeyEvent.VK_ESCAPE);
	}
	
	public static void pressKey(int key) throws AWTException {
		Robot r = new Robot();
		r.keyPress(key);
		r.keyRelease(key);
	}
	
	public static void delay(int ms) throws AWTException {
		Robot r = new Robot();
		
		r.delay(ms);
	}

}
