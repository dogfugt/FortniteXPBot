import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class IngameAI extends Main {

	static ITesseract instance = new Tesseract();

	int x1, y1, x2, y2;
	int yx, xy, yx2, xy2;

	int turnSpeed = 20;
	static int experience = 0;
	double sensitivity = 0.14;
	double fortniteYaw = 0.55550;
	int playerStuckCounter = 0;
	static double bpLevel = -1;
	static int level = -1;

	PointerInfo a = MouseInfo.getPointerInfo();
	Point b = a.getLocation();
	int mousex = (int) b.getX();
	int mousey = (int) b.getY();

	int centerX = (int) Swidth / 2; // for using for mouse move methods, so it moves from the center of the screen,
									// as it would usually do. otherwise if u just do say 20, it will teleport up to
									// there and not turn exactly how you want
	int centerY = (int) Sheight / 2;

	boolean saveImage = true; // saves image to hard drive, in the robot folder
	static boolean saveTempImages = true; // save hp,shield images, they dont need to be saved to function, (debugging)
	static String directory = base;
	static String directoryTemp = base + "\\fortniteBase\\temp\\";
	File radarLocation = new File(directoryTemp + "radar.png");
	File radarOldLocation = new File(directoryTemp + "radarOld.png");

	/*
	 * anti detection ideas
	 * 
	 * farming
	 * 
	 */

	public IngameAI()
			throws AWTException, IOException, InterruptedException, NumberFormatException, TesseractException {
		// turnToZero();
		// getXY();
		// print("HP: " + getHealth());

		writeRadar();
		// print(/*x1 + ", " + y1 + ", " + */x2 + ", " + y2);
		noobRun();

	}

	public double getAngle() {
		// double m = (y2 - y1) / (x2- x1); //original math

		// double m = (y1 - y2) / (x1 - x2); // green box has x1y1
		double m = Math.toDegrees(Math.atan2(y2, x2));

		if (m != 0) {
			if (m < 0)
				m += 360;

			System.out.println(Math.atan2(y2, x2) + " Radians");
			System.out.println(Math.toDegrees(Math.atan2(y2, x2)) + " Degrees");
			return m;

		} else
			System.out.println("getAngle() returned 0");
		return 0;

	}

	/*
	 * private boolean turnToZero() throws IOException, AWTException,
	 * InterruptedException { // zero on compass boolean temp = false; Robot robot =
	 * new Robot();
	 * 
	 * BufferedImage screenie = robot.createScreenCapture(new Rectangle(0, 0, (int)
	 * Swidth, (int) Sheight)); // BufferedImage currentCompass =
	 * screenie.getSubimage(1275, 5, 9, 13); BufferedImage currentCompass =
	 * screenie.getSubimage(1264, 5, 31, 13);
	 * 
	 * if (saveImage) { try { ImageIO.write(currentCompass, "png", new
	 * File("D:\\Java Projects\\Robot\\fortniteBase\\currentCompass.png"));
	 * print("Written cureentCompass.png to folder"); } catch (IOException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } }
	 * 
	 * if (CheckColor.compare("zeroCompass.png", "currentCompass.png") <= 98) {
	 * //fix this // Action.mouseMoveLinear(mousex - turnSpeed, mousey, 200, 20);
	 * Action.mouseMove(mousex - turnSpeed, mousey);
	 * System.out.println("Turning right, trying to set compass to zero"); } if
	 * (CheckColor.compare("zeroCompass.png", "currentCompass.png") > 98) { //fix
	 * this method System.out.println("Compass is zero"); temp = true; } return
	 * temp;
	 * 
	 * }
	 */

	public void getXY() throws AWTException, IOException {
		BufferedImage radar = null;

		Robot robot = new Robot();
		// BufferedImage img2 = robot.createScreenCapture(new Rectangle(0, 0, (int)
		// Swidth, (int) Sheight));
		// BufferedImage radar = img2.getSubimage(2265, 14, 280, 280);
		radar = ImageIO.read(radarLocation);

		// greenbox:
		// segment 1
		for (int i = 104; i < 175; i++) {
			yx = 105;

			Color clr = new Color(radar.getRGB(i, yx));
			if (clr.getRGB() == Color.white.getRGB() || clr.getRGB() == Color.blue.getRGB()) {
				x1 = i;
				y1 = yx;
				print("Found x1 and y1, (" + x1 + ", " + y1 + ")");

				break;
			}

			// print("segment 1 going " + i);

		}

		// segment 2
		for (int y = 105; y < 176; y++) {
			xy = 175;

			Color clr = new Color(radar.getRGB(xy, y));
			if (clr.getRGB() == Color.white.getRGB() || clr.getRGB() == Color.blue.getRGB()) {
				x1 = xy;
				y1 = y;
				print("Found x1 and y1, (" + x1 + ", " + y1 + ")");

				break;
			}
			// print("segment 2 going " + y);
		}

		// segment 3
		for (int i = 104; i < 175; i++) {
			yx = 176;

			Color clr = new Color(radar.getRGB(i, yx));

			if (clr.getRGB() == Color.white.getRGB() || clr.getRGB() == Color.blue.getRGB()) {
				x1 = i;
				y1 = yx;
				print("Found x1 and y1, (" + x1 + ", " + y1 + ")");

				break;
			}
		}

		// segment 4
		for (int y = 105; y < 176; y++) {
			xy = 104;

			Color clr = new Color(radar.getRGB(xy, y));
			if (clr.getRGB() == Color.white.getRGB() || clr.getRGB() == Color.blue.getRGB()) {
				x1 = xy;
				y1 = y;
				print("Found x1 and y1, (" + x1 + ", " + y1 + ")");

				break;
			}
		}

		bluebox:
		// segment 1
		for (int i = 126; i < 153; i++) {
			yx2 = 127;

			Color clr = new Color(radar.getRGB(i, yx2));
			if (clr.getRGB() == Color.white.getRGB() || clr.getRGB() == Color.blue.getRGB()) {
				x2 = i;
				y2 = yx2;
				print("Found x2 and y2, (" + x2 + ", " + y2 + ")");

				break;
			}
		}

		// segment 2
		for (int y = 127; y < 154; y++) {
			xy2 = 153;

			Color clr = new Color(radar.getRGB(xy2, y));
			if (clr.getRGB() == Color.white.getRGB() || clr.getRGB() == Color.blue.getRGB()) {
				x2 = xy2;
				y2 = y;
				print("Found x2 and y2, (" + x2 + ", " + y2 + ")");

				break;
			}
		}

		// segment 3
		for (int i = 126; i < 154; i++) {
			yx2 = 154;

			Color clr = new Color(radar.getRGB(i, yx2));

			if (clr.getRGB() == Color.white.getRGB() || clr.getRGB() == Color.blue.getRGB()) {
				x2 = i;
				y2 = yx2;
				print("Found x2 and y2, (" + x2 + ", " + y2 + ")");

				break;
			}
		}

		// segment 4
		for (int y = 127; y < 154; y++) {
			xy2 = 126;

			Color clr = new Color(radar.getRGB(xy2, y));
			if (clr.getRGB() == Color.white.getRGB() || clr.getRGB() == Color.blue.getRGB()) {
				x2 = xy2;
				y2 = y;
				print("Found x2 and y2, (" + x2 + ", " + y2 + ")");

				break;
			}
		}

		stormcircle: for (int i = 0; i < radar.getWidth(); i++) {
			for (int j = 0; j < radar.getHeight(); j++) {

				Color clrstorm2 = new Color(radar.getRGB(i, j));

				if (CheckColor.isShadeOfPink(clrstorm2)) {
					print(FortniteMethods.getUsername() + " is near storm " + "(" + i + ", " + j + ")");
					break stormcircle;
				}
			}
		}
	}

	public void noobRun() throws AWTException, InterruptedException, IOException {

		Random rand = new Random();
		Robot r = new Robot();
		int w = getKey('w');

		boolean verticalMouseMovement = false;

		while (FortniteMethods.inGame()) {
			int valueX = centerX + randInt(-450, 450);
			int valueY = centerY + randInt(-125, 125);
			if (verticalMouseMovement)
				Action.mouseMoveLinear(valueX, valueY, randInt(400, 900), randInt(50, 100));
			if (!verticalMouseMovement) {
				Action.mouseMoveLinear(valueX, centerY, randInt(400, 1500), randInt(50, 100));
			}

			/** walk forward for 15 - 30 seconds then wait 5-7 seconds */
			r.keyPress(w);
			r.delay(randInt(15000, 30000));
			r.keyRelease(w);
			r.delay(randInt(5000, 7000));

			if (isPlayerStuck()) {
				Action.mouseMoveLinear((int) ((sensitivity * fortniteYaw) * 1360), 0, randInt(400, 1500),
						randInt(50, 100));
			}
		}

	}

	private void writeRadar() throws AWTException {
		Robot r = new Robot();
		int radarX = 2265, radarY = 14, radarSize = 280;
		BufferedImage img2 = r.createScreenCapture(new Rectangle(0, 0, (int) Swidth, (int) Sheight));
		BufferedImage radarImg = img2.getSubimage(2265, 14, 280, 280);
		// BufferedImage radarImg = r.createScreenCapture(new Rectangle(radarX, radarY,
		// radarSize, radarSize));

		File radar = new File(directoryTemp + "radar.png");
		File radarOld = new File(directoryTemp + "radarOld.png");
		boolean exists = radar.exists();
		boolean oldExists = radarOld.exists();

		try {
			if (!exists && !oldExists) {
				ImageIO.write(radarImg, "png", new File(directoryTemp + "radar.png"));
				print("1 Written radar.png to folder");
			}
			if (!oldExists && exists) {
				radar.renameTo(radarOld);
				ImageIO.write(radarImg, "png", new File(directoryTemp + "radar.png"));
				print("2 Written radar.png and radarOld to folder");
			}
			if (oldExists && exists) { // this one gets excuted most often
				radarOld.delete();
				radar.renameTo(radarOld);
				ImageIO.write(radarImg, "png", new File(directoryTemp + "radar.png"));
				print("3 Deleted radarOld.png, written radar.png");
				print("Player has been stuck: " + playerStuckCounter);

			}
			if (oldExists && !exists) {
				radarOld.delete();
				ImageIO.write(radarImg, "png", new File(directoryTemp + "radar.png"));
				print("4 oldRadar.png existed, and was deleted radar.png was written");
			}
		} catch (IOException e) {
			System.out.println("something went wrong with trying to write a radar picture");
			e.printStackTrace();

		}
	}

	private boolean isPlayerStuck() throws AWTException, IOException {
		File radar = radarLocation;
		File radarOld = radarOldLocation;

		double passablePercentage = 95;
		boolean result = false;

		if (radar.exists() && radarOld.exists()) {
			if (CheckColor.compare(radarLocation, radarOldLocation) > passablePercentage) {
				result = true;
				print("player is stuck!");
				playerStuckCounter++;
			}
		} else {
			print("radar.png or radarOld.png does not exist! Can't check if player is stuck");
		}

		return result;
	}

	public static int getHealth() throws AWTException, NumberFormatException, TesseractException {
		Robot r = new Robot();
		ITesseract instance = new Tesseract();
		instance.setDatapath(base + "\\lib\\tessdata");

		/**
		 * get hp bar image create new image, if the colour is white (255,255,255), copy
		 * it to new image at x,y and colour black else make it white
		 */

		int hpValue = 0, hpTextX = 1093, hpTextY = 970, hpWidth = 35, hpHeight = 15;

		if (FortniteMethods.inGame()) {

			BufferedImage hp = r.createScreenCapture(new Rectangle(hpTextX, hpTextY, hpWidth, hpHeight));
			BufferedImage hpOCR = new BufferedImage(hpWidth, hpHeight, BufferedImage.TYPE_INT_ARGB);

			for (int y = 0; y < hpHeight; y++) {
				for (int x = 0; x < hpWidth; x++) {
					if (hp.getRGB(x, y) == Color.white.getRGB()) { // making it transparent is less bytes
						hpOCR.setRGB(x, y, Color.black.getRGB());
					}
				}
			}

			if (saveTempImages) {

				try { // only reason to save image is to debug
					File f = new File(directoryTemp + "hp.png");
					ImageIO.write(hpOCR, "png", f);
				} catch (IOException e) {
					System.out.println(e);
				}
			}

			String hpOCRString = instance.doOCR(hpOCR).replaceAll("[^\\d.]", "").trim();
			if (hpOCRString.length() > 3) {
				hpOCRString.substring(0, 2);
			}
			try {
				hpValue = Integer.parseInt(hpOCRString);

			} catch (Exception e) {
				System.out.println(e.getStackTrace());
			}
		}
		return hpValue;

	}

	public static boolean checkUsernameInLobby() throws AWTException, TesseractException {
	try {
		Robot r = new Robot();
		ITesseract instance = new Tesseract();
		instance.setDatapath(base + "\\lib\\tessdata");

		int startX = 1234, startY = 148, captureWidth = 123, captureHeight = 27;
		BufferedImage usernameInLobby = r
				.createScreenCapture(new Rectangle(startX, startY, captureWidth, captureHeight));
		BufferedImage usernameInLobbyOCR = new BufferedImage(captureWidth, captureHeight, BufferedImage.TYPE_INT_ARGB);

		for (int y = 0; y < captureHeight; y++) {
			for (int x = 0; x < captureWidth; x++) {
				if (usernameInLobby.getRGB(x, y) == Color.white.getRGB()) { // making it transparent is less bytes
					usernameInLobbyOCR.setRGB(x, y, Color.black.getRGB());
				}
			}
		}

		if (IngameAI.saveTempImages) {
			try { // only reason to save image is to debug
				File f = new File(directoryTemp + "username.png");
				ImageIO.write(usernameInLobbyOCR, "png", f);
			} catch (IOException e) {
				System.out.println(e);
			}
		}

		String usernameOCR = instance.doOCR(usernameInLobbyOCR).replace("\n", "").replace("\r", "");
		Main.print("Read OCR: " + usernameOCR);
		Main.print("Username: " + FortniteMethods.getUsername());

		for (int i = 0; i < usernameOCR.length(); i++) { // so it finds if any of the returning string has the username,
															// less fail proof
			if (usernameOCR.charAt(i) == FortniteMethods.getUsername().charAt(0)) {
				usernameOCR.substring(i, usernameOCR.length());
				Main.print(usernameOCR);
			}
		}

		if (usernameOCR.equals(FortniteMethods.username)) {
			return true;
		} else {
			System.out.println("Wrong or different username!\n" + usernameOCR + " | " + FortniteMethods.username);
			return false;
		}
	}catch(Exception e) {System.out.println("username error");}
	return false;
	}

	public static int getShield() throws AWTException, NumberFormatException, TesseractException {
		Robot r = new Robot();

		int shieldValue = 0, shieldTxtX = 1093, shieldTxtY = 94, hpWidth = 35, hpHeight = 15;

		if (FortniteMethods.inGame()) {

			BufferedImage shield = r.createScreenCapture(new Rectangle(shieldTxtX, shieldTxtY, hpWidth, hpHeight));
			BufferedImage shieldOCR = new BufferedImage(hpWidth, hpHeight, BufferedImage.TYPE_INT_ARGB);

			for (int y = 0; y < hpHeight; y++) {
				for (int x = 0; x < hpWidth; x++) {
					if (shield.getRGB(x, y) == Color.white.getRGB()) { // making it transparent is less bytes
						shieldOCR.setRGB(x, y, Color.black.getRGB());
					}
				}
			}

			if (saveTempImages) {
				try { // only reason to save image is to debug
					File f = new File(directoryTemp + "shield.png");
					ImageIO.write(shieldOCR, "png", f);
				} catch (IOException e) {
					System.out.println(e);
				}
			}

			String shieldOCRString = instance.doOCR(shieldOCR).replaceAll("[^\\d.]", "").trim();

			if (shieldOCRString.length() > 3) {
				shieldOCRString.substring(0, 2);
			}
			try {
				shieldValue = Integer.parseInt(shieldOCRString);

			} catch (Exception e) {
				System.out.println(e.getStackTrace());
			}
		}
		return shieldValue;
	}

	public static int getLevel() throws AWTException, TesseractException {
		Robot r = new Robot();
		String leveloctempr = "";
		instance.setDatapath(base + "\\lib\\tessdata");

		// int startX = 274, startY = 209, width = 80, height = 58;
		int startX = /* 292 */193, startY = 227, width = 161, height = 48;

		if (FortniteMethods.mainMenu()) {
			BufferedImage levelPic = r.createScreenCapture(new Rectangle(startX, startY, width, height));

			if (saveTempImages) {
				try { // only reason to save image is to debug
					File f = new File(directoryTemp + "level.png");
					ImageIO.write(levelPic, "png", f);
				} catch (IOException e) {
					System.out.println(e);
				}
			}

			String levelOCR = instance.doOCR(levelPic).replaceAll("[^\\d.]", "").trim();
			try {
				// Main.print(levelOCR);

				level = Integer.parseInt(levelOCR);

			} catch (Exception e) {
				System.out.println(e.getStackTrace());
			}
		}

		if (FortniteMethods.startLevel < level) {
			return FortniteMethods.startLevel;
		} else {
			return level;
		}
	}

	public static double getBPLevel() throws AWTException, TesseractException {
		Robot r = new Robot();
		instance.setDatapath(base + "\\lib\\tessdata");

		// int startX = 155, startY = 393, width = 42, height = 21;
		// int startX2 = 398, startY2 = 381, width2 = 18, height2 = 23; // for battle
		// pass x / 10

		int startX = 172, startY = 426, width = 48, height = 27;
		int startX2 = 381, startY2 = 418, width2 = 22, height2 = 25; // for battle pass x / 10

		if (FortniteMethods.mainMenu()) {
			BufferedImage bpLevelPic = r.createScreenCapture(new Rectangle(startX, startY, width, height));
			BufferedImage bpLevelDecimalPic = r.createScreenCapture(new Rectangle(startX2, startY2, width2, height2));

			if (saveTempImages) {
				try { // only reason to save image is to debug
					File f = new File(directoryTemp + "bpLevel.png");
					File f2 = new File(directoryTemp + "bpLevelDecimal.png");
					ImageIO.write(bpLevelPic, "png", f);
					ImageIO.write(bpLevelDecimalPic, "png", f2);
				} catch (IOException e) {
					System.out.println(e);
				}
			}

			try {
				String bpLevelOCR = instance.doOCR(bpLevelPic).replaceAll("[^\\d.]", "").trim();
				// Main.print("bpLevelocr" +bpLevelOCR);

				if (!bpLevelOCR.equals("100")) { // when it is 100 it doesnt need it, and it has a fucked coordinate as
													// theres an extra digit
					String bpLevelDecimalOCR = instance.doOCR(bpLevelDecimalPic).replaceAll("[^\\d.]", "").trim();
					double bpLevelDecimal = Double.parseDouble(bpLevelDecimalOCR) / 10;
					bpLevel = Double.parseDouble(bpLevelOCR) + bpLevelDecimal;
					// Main.print("bpLevel" + bpLevel);

				} else {
					// Main.print("bpLevel" + bpLevel);

					bpLevel = Double.parseDouble(bpLevelOCR);
				}
			} catch (Exception e) {
				System.out.println(e.getStackTrace());
			}
		}
		return bpLevel;
	}

	public static void setExperience(int thisGamesXP) {
		experience += thisGamesXP;
	}

	public static void getExperience(int thisGamesXP) {
		experience += thisGamesXP;
	}

}
