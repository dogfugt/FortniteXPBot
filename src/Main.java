import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import net.sourceforge.*;
import net.sourceforge.tess4j.*;
import net.sourceforge.tess4j.ITessAPI.TessBaseAPI;

@SuppressWarnings({ "serial", "unused" })
public class Main implements Runnable, KeyListener, MouseListener {

	static boolean fortnite = true, runescape = false;
	static boolean keepRunning = true, found;
	boolean running;
	static boolean debugging = false;
	static boolean colourdebugging = false, colourdebuggingfalse = false;
	static int x, y, timesChecked;
	static int windowFoundX, windowFoundY;

	public static RSMethods RSm = new RSMethods();
	public static FortniteMethods FNm = new FortniteMethods();
	// int mouseX, mouseY;

	static boolean randomSleep = false;
	static Thread thread = new Thread();
	static int randMs = randInt(50, 150); // doesnt change in a loop
	static int min = 50, max = 150;
	static int[] numArray = { 1, 2, 3, 4, 5 };

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static double Sheight = screenSize.getHeight();
	static double Swidth = screenSize.getWidth();

	static int[] width = java.util.stream.IntStream.rangeClosed(0, (int) Swidth - 1).toArray(),
			height = java.util.stream.IntStream.rangeClosed(0, (int) Sheight - 1).toArray();
	static String base = System.getProperty("user.dir");
	public static Coordinate coord = new Coordinate(50, 600);

	// window settings
	// public static int width = 600, height = 400, scale = 1;

	public static void main(String[] args) throws IOException, AWTException, InterruptedException {
		// new Applet((width * scale), (height * scale)); // run a window
		// System.out.println("Screen Width: " + screenSize.getWidth());
		// System.out.println("Screen Height: " + screenSize.getHeight() + "\n");

		Robot r = new Robot();

		/*
		 * File imageFile = new File("D:\\Java Projects\\Robot\\damagetxt.png");
		 * ITesseract instance = new Tesseract(); TessBaseAPI baseApi = new
		 * TessBaseAPI();
		 * 
		 * instance.setTessVariable("classify_bln_numeric_mode", "1");
		 * instance.setDatapath("D:\\Java Projects\\Robot\\lib\\tessdata");
		 * 
		 * try { String result = instance.doOCR(imageFile); print(result); }
		 * catch(TesseractException e) { print(e.getMessage()); }
		 */

		// GUI gui = new GUI();

		/*
		 * 
		 * print(CheckColor.whatisColor(CheckColor.getColor(255,255), true));
		 * 
		 * if(CheckColor.whatisColor(new Color(255,0,0), false) == "red") {
		 * 
		 * print("clicked at x, y on " + CheckColor.RGBtoString(new Color(255,0,0)));
		 * 
		 * }else print("fail");
		 */

		boolean runBot = true;
		if (runBot) {
			try {

				if (keepRunning) {
					for (;;) {
						FortniteMethods.runXP(r);
						// RSMethods.chin();
						// new RSMethods().chin();
						// r.delay(1000);
					}

				} else {
					for (int i = 0; i <= 10; i++) {
						Main.print("Not running in a loop!");
						FortniteMethods.runXP(r);
						r.delay(60000);

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				// for (int i = 0; i < 10; i++)
				// RSMethods.chin();
			}
		}

	}

	public static int getKey(char key) { // i dont like capital letters
		if (debugging)
			print("i asked for key '" + key + "'");
		return ((int) key - 32);
	}

	public static int getFoundWindowX() {
		return windowFoundX;
	}

	public static int getFoundWindowY() {
		return windowFoundY;
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public static void debug() throws AWTException, InterruptedException {
		Robot robot = new Robot();
		Color color = robot.getPixelColor(x, y);
		// Color pickedColor = Action.getPickedColor();

		print("Red   = " + color.getRed());
		print("Green = " + color.getGreen());
		print("Blue  = " + color.getBlue());
		// thread.sleep(10000);
		// print("click r = " + Click.getPickedColor());
		// Main.print(CheckColor.getColor(elimatedX, elimatedY).toString());

	}

	public static void print(String s) {
		System.out.println(s);
	}

	@Override
	public void run() {
		print("thread");
		// new init();
	}

	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}

	public synchronized void stop() {
		running = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F1)
			System.exit(0);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		/*
		 * x=e.getX(); y=e.getY();
		 * 
		 * 
		 * Robot robot; try { robot = new Robot(); Color color = robot.getPixelColor(x,
		 * y); print("Red   = " + color.getRed()); print("Green = " + color.getGreen());
		 * print("Blue  = " + color.getBlue());
		 * 
		 * } catch (AWTException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } System.out.println(x+","+y);
		 */
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
