import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class FortniteMethods {

	static String username = "Dak-dak";

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static double 	Sheight = screenSize.getHeight();
	static double 	Swidth = screenSize.getWidth();

	static boolean 	isDancing, postLog;
	static long 	startGTime, endGTime;

	static long 	startTime = System.currentTimeMillis();
	static String 	timeString, gameString, activity;

	static int 		errorCounter, allSeconds;

	static double 	battlePassLevelUp;

	static long 	minutes, hours, seconds;

	static int 		danceKey = 'Y'; // L key
	static int		favouriteDanceKey = (char) '4';

	static boolean 	clickReturnDebug = false; // if u just cant be fucked debugging and u want it to click return to
												// lobby

	static double 	HUDscale = 1.00;
	static int 		timesPlayed, timesPlayedElimated, timesPlayedReturn; // test if times played and elimated methods both
																	 // work stable
	static int 		startLevel = 0;
	static double 	startBPLevel = 0;
	
	static Thread 	startThread;

	/* Coordinates */

	// static Coordinate example = new Coordinate(x, y);
	static Coordinate playCoord = new Coordinate(2184, 869);
	static Coordinate newsCoord = new Coordinate(1141, 971);
	static Coordinate returnCoord = new Coordinate(2435, 1040);
	static Coordinate inGameHealthIcon = new Coordinate(1062, 980);
	static Coordinate inGameRadarCoord = new Coordinate(1280, 25);
	static Coordinate elimatedCoord = new Coordinate(236, 111);
	static Coordinate sessionCoord = new Coordinate(1200, 665);
	static Coordinate lobbyCoord = new Coordinate(750, 90);

	static Coordinate battlePassCoord = new Coordinate(433, 918); // 456, 860
	static Coordinate battlePassIconCoord = new Coordinate(190, 125);

	static Coordinate vButton1Coord = new Coordinate(1425, 1040);
	static Coordinate vButton2Coord = new Coordinate(1425, 1035);
	static Coordinate errorCoord = new Coordinate(1270, 971);
	static Coordinate errorCoord2 = new Coordinate(1242, 671);
	static Coordinate pingCoord = new Coordinate(322, 18);

	static Coordinate newItemGUICoord = new Coordinate(489, 952); // made for the battlepass upgrades, would glitch
																	// cause different gui

	static Coordinate lobbyBurgerIconDropDownCoord = new Coordinate(322, 18);

	static Coordinate redGamertagCoord = new Coordinate(32, 80); // you are dead, alternative for clicking the return
																	// toi lobby button. as i had tried to get colors
																	// for view stats button and it was sometimes buggy
																	// i believe.
	static Coordinate newBattlePassLevelGUICoord = new Coordinate(384, 173); // On the battle pass level up, it is the
																				// coord for the white colour in the
																				// middle of the 'B'.

	static Coordinate GUIMap = new Coordinate(384, 173);

	/* Colors */
	public static Color playSelectedColor = Color.white;
	public static Color playColor = new Color(255, 255, 141);
	public static Color newsColor = new Color(121, 246, 255);
	public static Color upgradeColor = Color.BLACK;
	public static Color inGameHealthIconColor = new Color(254, 254, 254);
	public static Color inGameRadarColor = new Color(252, 253, 253);
	public static Color elimatedColor = new Color(255, 0, 31); // fix
	public static Color sessionExpiredColor = new Color(239, 213, 0); // lobby selected color too
	public static Color sessionSelExpiredColor = new Color(55, 91, 184);

	public static Color selectedTabColor = new Color(255, 246, 0);

	public static Color battlePassLevelButtonOutlineColor = new Color(141, 185, 235); // 108, 173, 139
	public static Color battlePassIconWhiteColor = Color.white; // 108, 173, 139

	public static Color lobbyBurgerIconDropDownColor = new Color(0, 13, 46);
	public static Color vButton1Color = new Color(153, 129, 65);
	public static Color vButton2Color = new Color(79, 97, 169);
	public static Color topLeftColor = new Color(28, 35, 56);
	public static Color pingColor = Color.white;

	public static Color redGamertagColor = new Color(232, 74, 74);
	public static Color errorColor2 = new Color(68, 103, 182);
	public static Color continueColor = new Color(58, 95, 183);

	
	
	public static void runXP(Robot r)
			throws AWTException, InterruptedException, IOException, NumberFormatException, TesseractException {

		escape(r); // @TODO fix for news screen
		errorMsg(); // error on screen

		clickLobby();
						
		if (mainMenu()) {
			
			getPlayerInfo();
			clickPlay(r);
			if(timesPlayed <= 1 ) { // should be < 1, but i want to give it 2 tries to capture a level, was <= 2
				startLevel = IngameAI.getLevel();
				startBPLevel = IngameAI.getBPLevel();
			}
			continueButton();

		}

		if (inGame()) {
			//System.out.println("Detected: In a game");
			new IngameAI();
		}
		danceIngame(r);
		clickReturn(r);
		log();
		leaveBattlePassGUI();

		if(!mainMenu() && !battlePassLevelGUI() && !inGame()) {
			isFortniteCrashed();
		}
		 continueButton();

	/*	if (inGame()) {
			System.out.println("Detected: In a game");
			new IngameAI();
			
		}else {
			System.out.println("Detected:Not In a game");
		}*/
		
	}

	private static void continueButton() throws AWTException, IOException {
		try {
			if (CheckColor.compareToScreen("continueButton.png")) {
				Action.left(CheckColor.getColorCoord(continueColor));
				errorCounter++;
			}
		}catch(Exception e) {}

	}

	private static String setActivity(String str) {
		return activity = str;
	}
	
	public static String timeRan(long endTime) {
		long totalSecs = ((endTime - startTime) / 1000);
		hours = (totalSecs / 3600);
		minutes = ((totalSecs % 3600) / 60);
		seconds = (totalSecs % 60);

		return timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);

	}


	/*
	 * TODO
	 * 
	 * FIX BATTLEPASS SCREEN
	 * 
	 * 
	 * make it possible to run in window 1280x720 and work
	 * 
	 * make battlepass screen more stable
	 * 
	 * have it exit steam on launch (it opens sometimes)
	 */

	/* BOOLEAN */

	/*
	 * public static boolean errorMsg() throws AWTException { if
	 * (CheckColor.check(errorCoord, 255, 255, 255, "Error Message") ||
	 * (CheckColor.check(errorCoord2, errorColor2, "Error message v2 blue colour"))
	 * ) { return true; } return false; }
	 */

	public static void errorMsg() throws AWTException {
		if (CheckColor.check(errorCoord, 255, 255, 255, "Error Message")) {
			Action.left(errorCoord);
			errorCounter++;
		}

		if (CheckColor.check(errorCoord2, errorColor2, "Error message v2 blue colour")) {
			Action.left(errorCoord2);
			errorCounter++;
		}

		if ((CheckColor.check(sessionCoord, sessionExpiredColor, "Session expired button"))
				|| CheckColor.check(sessionCoord, sessionSelExpiredColor, "Session expired button")) {
			Action.left(sessionCoord);
			errorCounter++;
		}
	}

	public static boolean mainMenu() throws AWTException {
/*		if ((CheckColor.check(0, 0, 37, 43, 66, "Lobby screen - Top left"))
				&& CheckColor.check(35, 25, new Color(255, 255, 255), "Lobby screen - Logo")) {*/
		Robot r = new Robot();
//		BufferedImage replicalobbyTopLeft = r.createScreenCapture(new Rectangle(0, 0, 80, 80));
		BufferedImage replicalobbyTopLeft = r.createScreenCapture(new Rectangle(0, 0, 118, 32));
		
		try {
			if((CheckColor.compare("lobbyTopLeft.png", replicalobbyTopLeft) > 60) || IngameAI.checkUsernameInLobby()) {
				setActivity("Lobby");
				isDancing = false;
				return true;
			}
		} catch (IOException | TesseractException e) {
			
			System.out.println("mainmenu bug");
			e.printStackTrace();
		}
		return false;
	}
	
	

	public static boolean topMenu() throws AWTException {
		if (CheckColor.check(lobbyBurgerIconDropDownCoord, lobbyBurgerIconDropDownColor, "Lobby escape menu - burger icon")) {
			return true;
		}
		return false;
	}

	public static boolean isDeadOnGUI() throws AWTException { // made 31/12/2018 for season 7 update
		if (CheckColor.check(redGamertagCoord, redGamertagColor, "Red gamertag check")) {
			return true;
		}
		return false;
	}

	public static boolean battlePassLevelGUI() throws AWTException, IOException {
		/*
		 * if ((CheckColor.check(battlePassCoord, battlePassLevelButtonOutlineColor,
		 * "Collect and Close button on Battle Pass Level-Up GUI")) &&
		 * (CheckColor.check(battlePassIconCoord, battlePassIconWhiteColor,
		 * "Battle Pass Icon")) || CheckColor.check(new Coordinate(500, 919), new
		 * Color(141, 186, 235))) {
		 */

		if ((CheckColor.check(battlePassIconCoord, battlePassIconWhiteColor, "Battle pass icon")
				&& CheckColor.check(newBattlePassLevelGUICoord, Color.white, "The colour B in battle pass gui"))
				|| newItemGUI())  {
			
			
			// battlePassLevelUp++; //moved to once u click it method
			return true;
		}
		return false;
	}

	private static boolean newItemGUI() throws AWTException, IOException {
		if (CheckColor.check(newItemGUICoord, Color.white) 
				|| CheckColor.compareToScreen("collectButton.png", 39.0) 
				/*|| CheckColor.compareToScreen("collectButton2.png", 39.0)*/) {
			return true;
		}
		return false;
	}


	public static boolean elimatedVisible() throws AWTException {
		if (inGame() || inGameHealth())
			if (CheckColor.check(elimatedCoord, elimatedColor, "Elimated") == true) {
				Main.print("* Eliminated");
				timesPlayedElimated++;
				return true;
			}
		return false;
	}

	/*
	 * public static boolean victoryRoyaleGUI() throws AWTException { if
	 * (!inGameRadar() && inGameHealth()) { return true; } return false; }
	 */

	public static boolean pingVisible() throws AWTException {
		if (CheckColor.check(pingCoord, pingColor, "Ping debug GUI") == true) {
			return true;
		}
		return false;
	}

	public static boolean inGame() throws AWTException {
		if (inGameRadar() || inGameHealth() || pingVisible() || CheckColor.check(new Coordinate(2000,1005), Color.white)) { // FIXME
			setActivity("In game, earning XP");
			return true;
		}
		return false;
	}

	public static boolean inGameRadar() throws AWTException {
		if (CheckColor.check(inGameRadarCoord, inGameRadarColor, "Ingame radar") == true) {
			return true;
		}

		return false;
	}

	public static boolean inGameHealth() throws AWTException {
		if (CheckColor.check(inGameHealthIcon, inGameHealthIconColor, "Ingame health icon near green bar") == true) {
			return true;
		}
		return false;
	}

	public static boolean newsVisible() throws AWTException {
		if (CheckColor.check(newsCoord, newsColor, "News button")
				|| (CheckColor.check(newsCoord, new Color(16, 198, 227), "alternative news")) 
			|| (CheckColor.check(newsCoord, new Color(44,37,111), "avengers news"))) {
			return true;
		}
		/*
		 * if(!mainMenu()) { return true; }
		 */
		return false;
	}

	/* METHODS */
	
	private static void isFortniteCrashed() {
		try {
			if (CheckColor.compareToScreen("crash.png")) {
				errorCounter++;
				Action.left(CheckColor.foundImageOnScreenX + (680 + 170), CheckColor.foundImageOnScreenY + (560 + 155));
			}
		}catch(Exception e) {}

	}

	public static void estimateEXP() throws AWTException {
		int seconds = 0;
		int allEXP, allSeconds = 0;

		if (inGame()) {
			if (elimatedVisible()) {
				if (allSeconds <= seconds)
					allSeconds = seconds + seconds;
				Main.print("Experience (seconds) - " + allSeconds);
			} else {

				try {
					Thread.sleep(1000);
					seconds++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} else if (mainMenu() && !inGame()) {
			allEXP = seconds + seconds;
			// Main.print("Experience (seconds) - " + seconds);
			Main.print("Total EXP - " + allSeconds);
		}
		// seconds = 0;
	}

	public static void clickLobby() throws AWTException {
		if (mainMenu() && (!CheckColor.check(lobbyCoord, selectedTabColor, "Lobby tab - Main menu")) && !inGame()) {
			Action.left(lobbyCoord.getX() + 55, lobbyCoord.getY() + 12);
		}
	}

	public static void escape(Robot r) throws AWTException {
		if (newsVisible() || topMenu() /* || testBoolean() */ ) {
			Action.left(battlePassCoord);
			Action.escape(r);
		}
	}

	public static void leaveBattlePassGUI() throws AWTException, IOException {
		Robot r = new Robot();
		// int x = 25 + battlePassCoord.getX();
		// int y = 15 + battlePassCoord.getY();

		if (battlePassLevelGUI()) {
			Action.left(650, 950);
			Main.print("I leveled up battlepass!" + "\nI also closed out of the level up GUI.");
		}
		else {
			Main.print("help");
		}
	}

	public static void clickReturn(Robot r) throws AWTException {

		Date date = new Date();
		if (/* victoryRoyaleGUI() || */ elimatedVisible() || isDeadOnGUI() || (elimatedVisible() && inGameRadar())
				|| clickReturnDebug) { // not in lobby but
			// is victory or elimated?
			try {
				Main.print(date.toString() + " clickReturn | Returning to lobby..." + "\n");
				Action.left(returnCoord);
				timesPlayedReturn++;
			} catch (Exception e) {
				e.printStackTrace();
				Main.print("Error: Trying to click return to lobby..." + "\n");
			}
			if ((!inGameHealth() && !inGameRadar()) || !mainMenu() && Main.debugging) {
				Main.print("clickReturn | I couldn't exit out of game!");
			}

		}
	}

	public static void clickPlay(Robot r) throws AWTException, InterruptedException {
		Date date = new Date();
		try {
		//if (CheckColor.check(playCoord, playColor, "Play Button")
		//|| CheckColor.check(playCoord, playSelectedColor, "Play Button")) {//
		if(CheckColor.compareToScreen("readyButton.png")) {
			if (!inGame() || mainMenu()) { // comment mainmenu if no worky
				try {
					newGame();

					Main.print(date.toString() + " | Started a new game! " /* + "\n" */);
//					Action.left(playCoord);
					Action.left(playCoord.getX() + 50, playCoord.getY() + 15);
					r.delay(100);

					//r.mouseMove((int) Swidth / 2, (int) Sheight / 2);

					dance();

					timesPlayed++;
					postLog = true;

				} catch (Exception e) {
					e.printStackTrace();
					Main.print("Error: trying to click new game..." /* + "\n" */);
				}

			} else {
				Main.print(date.toString() + " | Trying to start a new game... "/* + "\n" */);


			}
		}
		} catch (Exception e) {
			e.printStackTrace();
			Main.print("Error: trying to loop start game..." + "\n");
		}
	}



	public static void newGame() throws AWTException, TesseractException { // inside the main menu, not starting a new game..
		if (postLog && (mainMenu() && !inGame())) {
			log();
		}
	}

	public static void log() throws AWTException, TesseractException {
		Date date = new Date();
	 String str = "**********************************************" + "\n"
				+ "* Fortnite Bot : " + activity 		 	   + "\n"
				+ "**********************************************" + "\n";
		str += "* " + getPlayerInfo() + " \n";
		str += "* Played " + timesPlayed + " games" + "\n";

		if (timesPlayed > 1)
			str += "* Grinded a total of " /*+ IngameAI.getExperience() */+ " experience." + "\n";

		str += "* Leveled up " + (IngameAI.getLevel() - startLevel)  + " times\n";
		str += "* Leveled up battle pass " + (IngameAI.getBPLevel() - startBPLevel) + " times" + "\n";

		str += "* Time ran: " + timeRan(System.currentTimeMillis()) + "\n";

		if (errorCounter > 0)
			str += "* Ran into " + errorCounter + " error screens" + "\n";

		str += "\n\n\n\n\n\n";

		// "* Time - " + date.toString() + "\n" +
		for (int i = 0; i < 15; ++i)
			System.out.println(); // clear console

		Main.print(str);
		postLog = false;
	}
	
	public static String getPlayerInfo() {
		String str = "";
		try {
			int level = IngameAI.getLevel();
			double bpLevel = IngameAI.getBPLevel();
			
			str += "Level: " + level + " | Battle Pass Level: " + bpLevel;
		} catch (AWTException | TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	/* DANCES */

	public static void danceIngame(Robot r) throws AWTException {
		if (inGame()) {
			// r.delay(60000);
			if (!isDancing) {

				if (Main.debugging)
					Main.print("Dancing!");

				dance();
				isDancing = true;

				if (isDancing) {
					if (Main.debugging)
						Main.print("Already dancing");
				}
			}
		}
	}

	public static void dance() throws AWTException {
		Action.left(1280, 720);
		Action.pressKey(danceKey);
		Action.delay(100);
		Action.pressKey(favouriteDanceKey);
	}

	public static String getUsername() {
		return username;
	}

}
