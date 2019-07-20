

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.Box;


public class RSMethods { // RS Methods
	boolean continueS;
	
    String path = this.getClass().getClassLoader().getResource("").getPath();

//	public int hpX = 560;
//	public int hpY = 92;
	public int hpX = 556;
	public int hpY = 65;
//	public int btmRightX = 711; // very last inventory slot, right height for rock cake: right click, left click
//	public int btmRightY = 463;
	public int btmRightX = 707;
	public int btmRightY = 436;
	
	static int worldSizeWidth = 512, worldSizeHeight = 334, windowSizeWidth = 765, windowSizeHeight = 503, frameSize = 4;
	static int mouseX, mouseY, state;

	public Color rockCakeRGB = new Color(79,66,44);
	public Color lowHealth = new Color(101, 4, 2); // sees if the pixels at (bottom of health + 4 pixels) are there (see if red)
	public Color topLeftColor = new Color(25, 19, 5);
	ArrayList<BoxTrap> boxTrap = new ArrayList<BoxTrap>();

	
	
	static int gapCircleX = 10, gapCircleY = 28;
	static int moveToCenterX = 5, moveToCenterY = 13;
	
	int clickRockCakes = 3;
	Image frame;
	static Coordinate windowCoord = new Coordinate(0, 0);
//	private static int windowFoundX, windowFoundY;
	
	public void chin() throws IOException, AWTException, InterruptedException {
		/*
		red = reset box trap
		blue = successful box trap
		yellow = pending box trap
		orange = chinchompa entering trap
		*/

		
		try {
			if(windowCoord.getX() < 1) {
				getWindowCoord();
			}
			boxTrap.clear();

			getWorld();
			updateXY();
			
			for(int i = 0; i < boxTrap.size(); i++) {
				
				Coordinate trapCoord = new Coordinate(	boxTrap.get(i).getX() +
														windowCoord.getX(), boxTrap.get(i).getY() + windowCoord.getY());
				int state = boxTrap.get(i).getState();


				System.out.println(	 "X: " + boxTrap.get(i).getX() +
									" Y: " + boxTrap.get(i).getY() +
									" State: " + boxTrap.get(i).getName(state));


				clickTrapBox(trapCoord, state);

//				System.out.println(boxTrap.stream().collect(Collectors.toList()) + " ");
			}

//					clickTrapBox(mouseX, mouseY, state);

			
			
			
			

			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void clickTrapBox(Coordinate coord, int state) throws AWTException, InterruptedException {
		boolean antiBanClick = false;
		int trapBoxSize = 3, x, y;
		int randX = Main.randInt(0, trapBoxSize), randY = Main.randInt(0, trapBoxSize);

		if (antiBanClick) {
			x = coord.getX() - randX;
			y = coord.getY() - randY;
		} else {
			x = coord.getX();
			y = coord.getY();
		}
		
		try {
			int failedTrap = 2, successfulTrap = 1;
			
			switch(state) {
			case 0:
				break;
			case 1:
				Action.mouseMoveLinear(x, y);
				Thread.sleep(Main.randInt(450, 750));
				Action.left();
				Thread.sleep(Main.randInt(1200, 14000));
				System.out.println("Clicked on a " + BoxTrap.getName(state) + "\n");

				break;
			case 2:
				Action.mouseMoveLinear(x, y);
				Thread.sleep(Main.randInt(450, 750));
				Action.left();
				Thread.sleep(Main.randInt(1200, 14000));
				System.out.println("Clicked on a " + BoxTrap.getName(state));
				break;
			case 3:
				break;
			}
//			System.out.println("Clicked on a " + BoxTrap.getName(state));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateXY() throws IOException {
		try {
			BufferedImage world = ImageIO.read(new File("D:\\Java Projects\\Robot\\osrsBase\\world.png"));

			for (int y = 0; y < world.getHeight(); y++) {
				for (int x = 0; x < world.getWidth(); x++) {

					Color clr = CheckColor.intToColor(world.getRGB(x, y));
				

				if (clr.getRed() == 255 && clr.getGreen() == 255 && clr.getBlue() == 0) {

					Color validateAllYellow = CheckColor.intToColor(world.getRGB(x - 2, y + 2)); // so it doesnt get a open trap and think it is red when about to collapse.
				/*	if ((validateAllYellow.getRed() > 150 && validateAllYellow.getRed() < 200)
							&& (validateAllYellow.getGreen() > 140 && validateAllYellow.getGreen() < 175)
							&& (validateAllYellow.getBlue() > 50 && validateAllYellow.getBlue() < 55)) {
*/
						// open/yellow

						boxTrap.add(new BoxTrap(x + 0, y + 13, 0));

						// of the circle
						if ((x + gapCircleX) < world.getWidth())
							x += gapCircleX;
						if ((y + gapCircleY) < world.getHeight())
							y += gapCircleY; 
						// go off circle so it doesnt detect the same circle twice
//					}
				}

				if ((clr.getRed() > 25 && clr.getRed() < 75) && (clr.getGreen() > 50 && clr.getGreen() < 65 ) && (clr.getBlue() > 110 && clr.getBlue() < 150)) { //if successful/blue
					boxTrap.add(new BoxTrap(x + moveToCenterX, y + moveToCenterY, 1));


					if ((x + gapCircleX) < world.getWidth())
						x += gapCircleX;
					if ((y + gapCircleY) < world.getHeight())
						y += gapCircleY; 
					// go off circle so it doesnt detect the same circle twice

				}
				
				if ((clr.getRed() > 145 && clr.getRed() < 186) && (clr.getGreen() > 34 && clr.getGreen() < 80) && (clr.getBlue() > 19 && clr.getBlue() < 70)) { // i ffailed/red

					boxTrap.add(new BoxTrap(x + moveToCenterX, y + moveToCenterY, 2));
					
					if ((x + gapCircleX) < world.getWidth())
						x += gapCircleX;
					if ((y + gapCircleY) < world.getHeight())
						y += gapCircleY; 
					// go off circle so it doesnt detect the same circle twice
					
				}
				
				if ( (clr.getRed() > 156 && clr.getRed() < 200) && (clr.getGreen() > 40 && clr.getGreen() < 80) && (clr.getBlue() > 115 && clr.getBlue() < 181)) { //if pink/transistioning
					boxTrap.add(new BoxTrap(x + moveToCenterX, y + moveToCenterY, 3));
					
					if ((x + gapCircleX) < world.getWidth())
						x += gapCircleX;
					if ((y + gapCircleY) < world.getHeight())
						y += gapCircleY; 
					// go off circle so it doesnt detect the same circle twice

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static BufferedImage getWorld() throws IOException, AWTException {
		Robot r = new Robot();
		
		BufferedImage img = r.createScreenCapture(new Rectangle((int) windowCoord.getX() + frameSize, (int) windowCoord.getY() + frameSize, worldSizeWidth - 4, worldSizeHeight - frameSize));
		ImageIO.write(img, "png", new File("D:\\Java Projects\\Robot\\osrsBase\\world.png"));
		System.out.println("Saved world.png");

		return img;
	}
	
	public static BufferedImage getClient() throws IOException, AWTException {
		Robot r = new Robot();
		
		BufferedImage img = r.createScreenCapture(new Rectangle((int) windowCoord.getX(), (int) windowCoord.getY(), windowSizeWidth, windowSizeHeight));
		ImageIO.write(img, "png", new File("D:\\Java Projects\\Robot\\osrsBase\\client.png"));

		System.out.println("Saved client.png");
		return img;
	}
	
	public static Coordinate getWindowCoord() throws IOException, AWTException {
		windowCoord = new Coordinate(0, 0);

		String frame = "osrsBase\\frame.png";
//		String frame = path + "frame.png";

		try {
			if (CheckColor.compareToScreen(new File(frame))) {
				windowCoord.setCoordinate(CheckColor.newXGlobal, CheckColor.newYGlobal);
			} else {
				throw new IllegalArgumentException("Could not find any window that matched " + frame);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return windowCoord;
	}
	
	
	
	public void start(Robot r) throws AWTException, InterruptedException {
		CheckColor.findWindow(topLeftColor, topLeftColor);
		for (int x = 1; x < 10000; x++)
			darts();
//		rockCake(r);
	}
	
	public boolean safeHealth() throws AWTException { // above 19 health?
		if (continueS)
		 if(CheckColor.check(hpX + getFoundWindowX(), hpY + getFoundWindowY(), 101, 4, 2, "Safe Health check")) { //true
			 return true;
		 }
		return false;
	}

	public void rockCake(Robot r) throws AWTException, InterruptedException {
		if (safeHealth()) {
			rockCakeClick(r);

		} else {
			Main.print("hp probably under 21, or something went wrong");
		}
	}

	public void rockCakeClick(Robot r) throws AWTException, InterruptedException {
		Date date = new Date();
		if(continueS) {
			if (CheckColor.check(btmRightX + getFoundWindowX(), btmRightY + getFoundWindowY(), rockCakeRGB, "Rock Cake") == true) {

				int timesClicked = 0;
				for (int i = 1; i <= clickRockCakes; i++) {
					int x = Main.randInt(300, 400);
					int y = Main.randInt(200, 300);

					int x1 = btmRightX + getFoundWindowX();
					int y1 = btmRightY + getFoundWindowY();
					
					Action.right(x1, y1, x);
					Main.print(x1 +" " + y1);
					Action.left(r, y);

					timesClicked++;
					Main.print("Interval of (" + x + " x value), " + " (" + y + " y value)"); // Clicked with an
																								// interval
																								// of... x y
				}
				Main.print("\nGuzzled rock cake " + timesClicked + " times.\n"); // Successfully clicked rock cake

			} else {
				Main.print("I couldn't guzzle rock cake!\n");
				for (int i = 1; i <= 2; i++) {
					Main.print("Trying to loop rock cake... - " + date.toString() + "\n");
					r.delay(20000);
					try {
						rockCakeClick(r);

					} catch (Exception e) {
						e.printStackTrace();
						Main.print("Error: trying to loop rock cake click");
					}
				}
			}
		}
	}
	
	public static void darts() throws AWTException {
		Robot r = new Robot();
		
		try {
//			Action.lClickAt(getFoundWindowX() + 580, getFoundWindowY() + 230, 5, 15);
//			Action.lClickAt(getFoundWindowX() + 580, getFoundWindowY() + 267, 6, 17);
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public static int getFoundWindowX() {
		return Main.windowFoundX;
	}
	
	public static int getFoundWindowY() {
		return Main.windowFoundY;
	}
}
