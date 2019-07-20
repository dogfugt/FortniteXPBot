import java.awt.AWTException;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

public class CheckColor extends Main {

	private static int r, g, b, r2, g2, b2;

	private static Color color = new Color(255, 255, 255);

	static public int secondMonitorWidth = 1440;	
	
	public static int newXGlobal, newYGlobal;

	public static int foundImageOnScreenY = 0, foundImageOnScreenX = 0;

	static boolean secondMonitorInUse() { //needed.
		if(Main.Swidth != 2560) {
			return true;
		}
		return false;
	}
	
	
	
	public static double compare(String file1) throws IOException, AWTException {
		
		Robot robot = new Robot();
		
		int colourStartX = 0, colourStartY = 0, ColourStartClr;
		
		BufferedImage img1 = ImageIO.read(new File(base  + "\\fortniteBase\\" + file1));
		int width = img1.getWidth();
		int height = img1.getHeight();

		double totalPixels = width * height;
		double tempPixels = totalPixels;
		int transparentPixels = 0;

		BufferedImage img2 = robot.createScreenCapture(new Rectangle(0, 0, (int) Swidth, (int) Sheight));
		

		int clr1 = new Color(img1.getRGB(x, y), true).getRGB(); // original icon/image
		int clr2 = new Color(img2.getRGB(x, y), true).getRGB(); // screenshot of fortnite

		
/*		for (int FirstClrFindx = 0; FirstClrFindx < width; FirstClrFindx++) { //get the first colour that isnt transparent of img1, save coord and colour
			for (int FirstClrFindy = 0; FirstClrFindy < height; FirstClrFindy++) {

				if ((clr1 >> 24) != 0x00) {
					colourStartX = FirstClrFindx;
					colourStartY = FirstClrFindy;
					ColourStartClr = new Color(img1.getRGB(FirstClrFindx, FirstClrFindy)).getRGB();
				}
			}
		}*/

		for (int x = 0; x < width; x++) { // scan x ++, once width done, increase y then continue
			for (int y = 0; y < height; y++) {
				
				print(colourStartX + ", " + colourStartY);

				if ((clr1 >> 24) == 0x00) {
					if (debugging)
						System.out.println(x + ", " + y + " Selected pixel is transparent, ignored");
						transparentPixels++;
				} else if (clr1 == clr2) {
					if (debugging)
						System.out.println(x + ", " + y + " Same pixel RGB");
				} else { // if (clr1 != clr2)
					if (debugging)
						System.out.println(x + ", " + y + " Different pixel RGB");
					tempPixels--;

				}
				
			}
		}

		double percentage = (tempPixels / totalPixels) * 100;
		if(Main.debugging)
		System.out.println("\nFinished comparing " + file1 + " to fortnite" + ".\n" + "fortnite screenshot was " + percentage + "% similar.");

		return percentage;

	}
	
	public static double compare(File file1, File file2) throws IOException, AWTException {
		
		//file1 is fortniteBase/icon you want to be *FOUND*
		//file2 is the live monitor picture
		
		Robot robot = new Robot();

//		BufferedImage img1 = ImageIO.read(new File("D:\\Java Projects\\Robot\\fortniteBase\\" + file1));
		BufferedImage img1 = ImageIO.read(file1);
		int width = img1.getWidth();
		int height = img1.getHeight();

		double totalPixels = width * height;
		double tempPixels = totalPixels;

//		BufferedImage img2 = ImageIO.read(new File("D:\\Java Projects\\Robot\\fortniteBase\\" + file2));
		BufferedImage img2 = ImageIO.read(file2);

		for (int x = 0; x < width; x++) { // scan x ++, once width done, increase y then continue
			for (int y = 0; y < height; y++) {

				int clr1 = new Color(img1.getRGB(x, y), true).getRGB(); // original icon/image
				int clr2 = new Color(img2.getRGB(x, y), true).getRGB(); // screenshot of fortnite


				if ((clr1 >> 24) == 0x00) {
					if (debugging)
						System.out.println(x + ", " + y + " Selected pixel is transparent, ignored");
				} else if (clr1 == clr2) {
					if (debugging)
						System.out.println(x + ", " + y + " Same pixel RGB");
				} else { // if (clr1 != clr2)
					if (debugging)
						System.out.println(x + ", " + y + " Different pixel RGB");
					tempPixels--;

				}
				
			}
		}

		double percentage = (tempPixels / totalPixels) * 100;
		System.out.println("\nFinished comparing " + file1 + " to fortnite" + ".\n" + "fortnite screenshot was " + percentage + "% similar.");
		return percentage;
		
	}
	
	public static double compare(String file1, BufferedImage file2) throws IOException, AWTException {
		//BufferedImage file2 so i can just use a live picture as new bufferedimage(0,0, file1.getWidth(), file1.getHeight());
		//file1 is fortniteBase/icon you want to be *FOUND*
		//file2 is the live monitor picture
		
		Robot robot = new Robot();
		
		BufferedImage img1 = ImageIO.read(new File(base  + "\\fortniteBase\\" + file1));
		int width = img1.getWidth();
		int height = img1.getHeight();

		double totalPixels = width * height;
		double tempPixels = totalPixels;

		BufferedImage img2 = file2;

		for (int x = 0; x < width; x++) { // scan x ++, once width done, increase y then continue
			for (int y = 0; y < height; y++) {

				int clr1 = new Color(img1.getRGB(x, y), true).getRGB(); // original icon/image
				int clr2 = new Color(img2.getRGB(x, y), true).getRGB(); // screenshot of fortnite


				if ((clr1 >> 24) == 0x00) {
					if (debugging)
						System.out.println(x + ", " + y + " Selected pixel is transparent, ignored");
				} else if (clr1 == clr2) {
					if (debugging)
						System.out.println(x + ", " + y + " Same pixel RGB");
				} else { // if (clr1 != clr2)
					if (debugging)
						System.out.println(x + ", " + y + " Different pixel RGB");
					tempPixels--;

				}
				
			}
		}

		double percentage = (tempPixels / totalPixels) * 100;
		if(Main.debugging)

		System.out.println("\nFinished comparing " + file1 + " to fortnite" + ".\n" + "fortnite screenshot was " + percentage + "% similar.");

		return percentage;

	}


	
	public static boolean compareToScreen(String file1, double passablePercentage) throws IOException, AWTException {
		Robot robot = new Robot();
		
//		double passablePercentage = 80.0; //how much percent it needs to be passed as 'true'
		double percentage = 0;
		
		BufferedImage img1 = ImageIO.read(new File(base + "\\fortniteBase\\" + file1));
		int width = img1.getWidth();
		int height = img1.getHeight();

		double totalPixels = width * height;
		double tempPixels = totalPixels;

		BufferedImage img2 = robot.createScreenCapture(new Rectangle(0, 0, (int) Swidth, (int) Sheight));
		int screenWidth = img2.getWidth();
		int screenHeight = img2.getHeight();
		
//		ImageIO.write(img2, "png", new File("D:\\Java Projects\\Robot\\fortniteBase\\screenshot.png"));

		int startX = 0, startY = 0; //start of image that isnt alpha
		int startColor = 0;
		
		boolean getFirstColorDone = false;
		boolean searchFirstColorOnScreenDone = false;
		
		getFirstColor:
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				print("1");
				
				int pixel = img1.getRGB(x, y);
			    int alpha = (pixel >> 24) & 0xff;
				if (debugging)
					print("getFirstColor:" + x + ", " + y);
			    
				if ((alpha == 255) /*|| (alpha != 0)*/) {
			    	startX = x;
			    	startY = y;
			    	startColor = img1.getRGB(x, y);
			    	getFirstColorDone = true;
			    	break getFirstColor;
			    }
				
			}
		}
		
		int foundImageOnScreenX = 0, foundImageOnScreenY = 0;
		searchFirstColorOnScreen:
		while (getFirstColorDone) {
			for (int y = 0; y < screenHeight /*720*/; y++) {
				for (int x = 0; x < screenWidth/* 2404*/; x++) {
					if (debugging)
					print("2 " + x + ", " + y);

					int newColor = img2.getRGB(x, y);
					
//					print(starwtX + ", " + startY);
					if (debugging)
				    print("searchF:" + x + ", " + y + ", \n"
				    		+ "newColor: " + intToString(newColor) + "startColor: " + intToString(startColor));
					
					if ((x >= (int)Swidth - 1) && (y >= (int)Sheight - 1)) {
						getFirstColorDone = false;
						print("Couldn't find it");
						break searchFirstColorOnScreen;
					}
					
					if (startColor == newColor) {
						foundImageOnScreenX = x;
						foundImageOnScreenY = y;
						searchFirstColorOnScreenDone = true;
						getFirstColorDone = false;
						print("\nFound pixel of image: " + foundImageOnScreenX + ", " + foundImageOnScreenY);
						break searchFirstColorOnScreen;
					}

				}
			}
		}
		
		int newX = foundImageOnScreenX - startX, newY = foundImageOnScreenY - startY;
		int img1x = 0, img1y = 0;
		int totalSize = newX + height * newY + width;

//		actuallyComparePart:
		while (searchFirstColorOnScreenDone) {
			for (int y = newY; y < newY + height; y++) {
				for (int x = newX; x < newX + width; x++) {
					if (debugging)
					print("3 " + file1 + " " + x + ", " + y);
					if (debugging)
						print("actuallyComparePart:" + x + ", " + y + "\n" + img1x % width + ", " + img1y % height);
					if (debugging)
						print("newX: " + newX + ", newY: " + newY);

					
					int clr1 = new Color(img1.getRGB(img1x % width, img1y % height), true).getRGB(); // original																					// icon/image
					int clr2 = 999999;
					if (x < 2560 && y < 1080)
						clr2 = new Color(img2.getRGB(x, y), true).getRGB(); // screenshot of fortnite

					if ((clr1 >> 24) == 0x00) {
						if (debugging)
							System.out.println(x + ", " + y + " Ignored");
					} else if (clr1 == clr2) {
						if (debugging)
							System.out.println(x + ", " + y + " Same pixel RGB");
					} else { // if (clr1 != clr2)
						if (debugging)
							System.out.println(x + ", " + y + " Different pixel RGB");
						tempPixels--;
					}
					img1x++;
				}
				img1y++;
			}
			percentage = ((tempPixels / totalPixels) * 100);
			searchFirstColorOnScreenDone = false;
			break;
		}

		boolean check = false;
		if (percentage >= passablePercentage) {
			check = true;
		} else {
			check = false;
		}

		print("\n\nPercentage of comparsion (" + file1 + "): " + percentage );
		return check;
		}
	
	
	
	public static boolean compareToScreen(String file1) throws IOException, AWTException {
		Robot robot = new Robot();
		
		double passablePercentage = 80.0; //how much percent it needs to be passed as 'true'
		double percentage = 0;
		
		BufferedImage img1 = ImageIO.read(new File(base  + "\\fortniteBase\\" + file1));
		int width = img1.getWidth();
		int height = img1.getHeight();

		double totalPixels = width * height;
		double tempPixels = totalPixels;

		BufferedImage img2 = robot.createScreenCapture(new Rectangle(0, 0, (int) Swidth, (int) Sheight));
		int screenWidth = img2.getWidth();
		int screenHeight = img2.getHeight();
		
//		ImageIO.write(img2, "png", new File("D:\\Java Projects\\Robot\\fortniteBase\\screenshot.png"));

		int startX = 0, startY = 0; //start of image that isnt alpha
		int startColor = 0;
		
		boolean getFirstColorDone = false;
		boolean searchFirstColorOnScreenDone = false;
		
		getFirstColor:
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				print("1");
				
				int pixel = img1.getRGB(x, y);
			    int alpha = (pixel >> 24) & 0xff;
				if (debugging)
					print("getFirstColor:" + x + ", " + y);
			    
				if ((alpha == 255) /*|| (alpha != 0)*/) {
			    	startX = x;
			    	startY = y;
			    	startColor = img1.getRGB(x, y);
			    	getFirstColorDone = true;
			    	break getFirstColor;
			    }
				
			}
		}
		
		int foundImageOnScreenX = 0, foundImageOnScreenY = 0;
		searchFirstColorOnScreen:
		while (getFirstColorDone) {
			for (int y = 0; y < screenHeight /*720*/; y++) {
				for (int x = 0; x < screenWidth/* 2404*/; x++) {
					if (debugging)
					print("2 " + x + ", " + y);

					int newColor = img2.getRGB(x, y);
					
//					print(starwtX + ", " + startY);
					if (debugging)
				    print("searchF:" + x + ", " + y + ", \n"
				    		+ "newColor: " + intToString(newColor) + "startColor: " + intToString(startColor));
					
					if ((x >= (int)Swidth - 1) && (y >= (int)Sheight - 1)) {
						getFirstColorDone = false;
						if(Main.debugging)
						print("Couldn't find it");
						break searchFirstColorOnScreen;
					}
					
					if (startColor == newColor) {
						foundImageOnScreenX = x;
						foundImageOnScreenY = y;
						searchFirstColorOnScreenDone = true;
						getFirstColorDone = false;
						print("\nFound pixel of image: " + foundImageOnScreenX + ", " + foundImageOnScreenY);
						break searchFirstColorOnScreen;
					}

				}
			}
		}
		
		int newX = foundImageOnScreenX - startX, newY = foundImageOnScreenY - startY;
		int img1x = 0, img1y = 0;
		int totalSize = newX + height * newY + width;

//		actuallyComparePart:
		while (searchFirstColorOnScreenDone) {
			for (int y = newY; y < newY + height; y++) {
				for (int x = newX; x < newX + width; x++) {
					if (debugging)
					print("3 " + file1 + " " + x + ", " + y);
					if (debugging)
						print("actuallyComparePart:" + x + ", " + y + "\n" + img1x % width + ", " + img1y % height);
					if (debugging)
						print("newX: " + newX + ", newY: " + newY);

					int clr1 = new Color(img1.getRGB(img1x % width, img1y % height), true).getRGB(); // original																					// icon/image
					int clr2 = new Color(img2.getRGB(x, y), true).getRGB(); // screenshot of fortnite

					if ((clr1 >> 24) == 0x00) {
						if (debugging)
							System.out.println(x + ", " + y + " Ignored");
					} else if (clr1 == clr2) {
						if (debugging)
							System.out.println(x + ", " + y + " Same pixel RGB");
					} else { // if (clr1 != clr2)
						if (debugging)
							System.out.println(x + ", " + y + " Different pixel RGB");
						tempPixels--;
					}
					img1x++;
				}
				img1y++;
			}
			percentage = ((tempPixels / totalPixels) * 100);
			searchFirstColorOnScreenDone = false;
			break;
		}

		boolean check = false;
		if (percentage >= passablePercentage) {
			check = true;
		} else {
			check = false;
		}

		print("\n\nPercentage of comparsion (" + file1 + "): " + percentage );
		return check;

	}
	
	
	public static boolean compareToScreen(File file) throws IOException, AWTException {
		Robot robot = new Robot();
		
		double passablePercentage = 80.0; //how much percent it needs to be passed as 'true'
		double percentage = 0;
		
		BufferedImage img1 = ImageIO.read(new File(base + file));
		int width = img1.getWidth();
		int height = img1.getHeight();
		
		double totalPixels = width * height;
		double tempPixels = totalPixels;
		
		BufferedImage img2 = robot.createScreenCapture(new Rectangle(0, 0, (int) Swidth, (int) Sheight));
		int screenWidth = img2.getWidth();
		int screenHeight = img2.getHeight();
		
		
//		ImageIO.write(img2, "png", new File("D:\\Java Projects\\Robot\\fortniteBase\\screenshot.png"));
		
		int startX = 0, startY = 0; //start of image that isnt alpha
		int startColor = 0;
		
		boolean getFirstColorDone = false;
		boolean searchFirstColorOnScreenDone = false;
		
		getFirstColor:
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					
					int pixel = img1.getRGB(x, y);
					int alpha = (pixel >> 24) & 0xff;
					if (debugging)
						print("getFirstColor:" + x + ", " + y);
					
					if ((alpha == 255) /*|| (alpha != 0)*/) {
						startX = x;
						startY = y;
						startColor = img1.getRGB(x, y);
						getFirstColorDone = true;
						break getFirstColor;
					}
					
				}
			}
		
		int foundImageOnScreenX = 0, foundImageOnScreenY = 0;
		searchFirstColorOnScreen:
			while (getFirstColorDone) {
				for (int y = 0; y < screenHeight /*720*/; y++) {
					for (int x = 0; x < screenWidth/* 2404*/; x++) {
						
						
						int newColor = img2.getRGB(x, y);
						
//					print(startX + ", " + startY);
						if (debugging)
							print("searchF:" + x + ", " + y + ", \n"
									+ "newColor: " + intToString(newColor) + "startColor: " + intToString(startColor));
						
						if ((x >= (int)Swidth - 1) && (y >= (int)Sheight - 1)) {
							getFirstColorDone = false;
							print("Couldn't find it");
							break searchFirstColorOnScreen;
						}
						
						if (startColor == newColor) {
							foundImageOnScreenX = x;
							foundImageOnScreenY = y;
							searchFirstColorOnScreenDone = true;
							getFirstColorDone = false;
							print("\nFound pixel of image: " + foundImageOnScreenX + ", " + foundImageOnScreenY);
							break searchFirstColorOnScreen;
						}
						
					}
				}
			}
		
		int newX = foundImageOnScreenX - startX, newY = foundImageOnScreenY - startY;
		newXGlobal = newX; newYGlobal = newY; //so i can grab the coordinates for rsmethods getworld method
		int img1x = 0, img1y = 0;
		int totalSize = newX + height * newY + width;
		
		actuallyComparePart:
			while (searchFirstColorOnScreenDone) {
				for (int y = newY; y < newY + height; y++) {
					for (int x = newX; x < newX + width; x++) {
						
						if (debugging)
							print("actuallyComparePart:" + x + ", " + y + "\n" + img1x % width + ", " + img1y % height);
						if (debugging)
							print("newX: " + newX + ", newY: " + newY);
						
						int clr1 = new Color(img1.getRGB(img1x % width, img1y % height), true).getRGB(); // original																					// icon/image
						int clr2 = new Color(img2.getRGB(x, y), true).getRGB(); // screenshot of fortnite
						
						if ((clr1 >> 24) == 0x00) {
							if (debugging)
								System.out.println(x + ", " + y + " Ignored");
						} else if (clr1 == clr2) {
							if (debugging)
								System.out.println(x + ", " + y + " Same pixel RGB");
						} else { // if (clr1 != clr2)
							if (debugging)
								System.out.println(x + ", " + y + " Different pixel RGB");
							tempPixels--;
						}
						img1x++;
					}
					img1y++;
				}
				percentage = ((tempPixels / totalPixels) * 100);
				searchFirstColorOnScreenDone = false;
				break actuallyComparePart;
			}
		
		boolean check = false;
		if (percentage >= passablePercentage)
			check = true;
		else
			check = false;
		
		print("\n\nPercentage of comparsion (" + file + "): " + percentage );
		return check;
		
	}
	
	public void setnewX(int x) {
		newXGlobal = x;
	}
	public void setnewY(int y) {
		newYGlobal = y;
	}
	
	
	public static String whatisColor(Color color, boolean detailed) {
		//Only works if the color selected is not a shade, BRIGHT... at the moment
		
		//If detailed = true, it will print if it is pastel and/or a dark/light colour

		String col = "Error";
		
		//get color values
		color.getRGB();
		
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();


		float[] hsb = Color.RGBtoHSB(r, g, b, null);
		float hue = hsb[0];
		float saturation = hsb[1];
		float brightness = hsb[2];

		//colour detection
		if(brightness >= 0.)
		if (hue >= 0.0 && hue <= 0.05000000) // 0 - 17
			col = "red";
		else if (hue > 0.05000000 && hue <= 0.08888888) // 17 - 32
			col = "orange";
		else if (hue > 0.08888888 && hue <= 0.17254901) // 32- 63
			col = "yellow";
		else if (hue > 0.17254901 && hue <= 0.39477125) // 63- 142
			col = "green";
		else if (hue > 0.39477125 && hue <= 0.5111111) // 142- 184
			col = "cyan";
		else if (hue > 0.5111111 && hue <= 0.7137255) // 184 - 257
			col = "blue";
		else if (hue > 0.7137255 && hue <= 0.8137255) // 257 - 293
			col = "purple";
		else if (hue > 0.8137255 && hue <= 0.9478333) // 293 - 333
			col = "pink";
		else if (hue > 0.9478333) // 333 > 360/0
			col = "red";
		
		if (detailed) {
			// brightness
			if (brightness > 0.75)
				col += " bright";
			else if (brightness >= 0.5 && brightness <= 0.75)
				col += " dark";
			else if (brightness >= 0.25 && brightness < 0.5)
				col += " very dark";
			else if (brightness >= 0.01 && brightness < 0.25)
				col += " almost black";
			else if (brightness >= 0 && brightness < 0.01)
				col = " black";

			// saturation
			if (saturation >= 0.75 && saturation < 0.85)
				col += " pastelish";
			else if (saturation >= 0.5 && saturation < 0.75)
				col += " pastel";
			else if (saturation >= 0.25 && saturation < 0.5)
				col += " very pastel";
			else if (saturation >= 0.10 && saturation < 0.25)
				col += " extremely pastel";
			else if (saturation >= 0.01 && saturation < 0.10)
				col += " nearly white or black";

			// shades
			else if (saturation == 0.0 && brightness == 0.0)
				col = "black";
			else if (saturation == 0.0 && brightness >= 0.85)
				col = "white";
			else if (saturation == 0.0 && (brightness >= 0.5 && brightness < 0.85))
				col = "light grey";
			else if (saturation == 0.0 && (brightness >= 0.15 && brightness < 0.5))
				col = "dark grey";
		}
		
		
		
		return col;
		
	}
	
	public static boolean isShadeOfRed(Color color) {
		
		color.getRGB();
		
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();

		float[] hsb = Color.RGBtoHSB(r, g, b, null);
		float hue = hsb[0];
		float saturation = hsb[1];
		float brightness = hsb[2];
		boolean isShade = false;


		
		if((hue >= 0 && hue <= 0.039215684) && (saturation >= 0.60 && saturation <= 1) && (brightness >= 0.60 && brightness <= 1.0)) {
//		if((r >= 200 && r <= 255) && (g >= 0 && g <= 85) && (b >= 0 && b <= 10)) { //not the same as hue algorithm
			isShade = true;
			System.out.println(hsb[0] + " " + hsb[1] + " " + hsb[2]);
//			System.out.println(r + " " + g + " " + b);
		} else {
			isShade = false;
			System.out.println(hsb[0] + " " + hsb[1] + " " + hsb[2]);
//			System.out.println(r + " " + g + " " + b);
		}
		
		return isShade;

    	/* explanation, plan . FOR OSRS HEALTH
    	if pixel y 0 of hp is not shade of red, continue down y... until it is red... else ur dead
    	
    	calculate how many y pixels it went down for red / the total red sphere size (25) to get percentage of hp 
    		*this will give percentage of damage taken, instead of total hp..
    			so just make it 100.0 - percentage
    			100 - (5/ 25 * 100) = 80% of total hp
    			
    	*/
	}
	
	public static boolean isShadeOfPink(Color color) {
		color.getRGB();
		
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();		

		float[] hsb = Color.RGBtoHSB(r, g, b, null);
		float hue = hsb[0];
		float saturation = hsb[1];
		float brightness = hsb[2];
		boolean isShade = false;
		
		if((hue >= 0.6900 && hue <= 0.8889) && (saturation >= 0.4503 && saturation <= 0.5814) && (brightness >= 0.60 && brightness <= 0.9216)) {
			isShade = true;
//			System.out.println(hsb[0] + " " + hsb[1] + " " + hsb[2]);
//			System.out.println(r + " " + g + " " + b);
		} else {
			isShade = false;
//			System.out.println(hsbl3[0] + " " + hsb[1] + " " + hsb[2]);
//			System.out.println(r + " " + g + " " + b);
		}
		
		return isShade;
	}
	
	public static boolean isWhite(Coordinate coord) throws AWTException {
		
		Robot rb = new Robot();
		
		rb.getPixelColor(coord.getX(), coord.getY());
		
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();

		if ((r == 255) && (g == 255) && (b == 255))
			return true;
		else
			return false;
	}
	
	public static String RGBtoString(Color color) {
		color.getRGB();
		r = color.getRed();
		g = color.getGreen();
		b = color.getBlue();
		
		return "R: " + r + " G: " + g + " B: " + b;
	}

	public static Color getColor(int x, int y) throws AWTException {
		Robot r = new Robot();
		color = r.getPixelColor(x, y);
		return color;
	}

	public static void findWindow(Color color) throws AWTException {
		Robot robot = new Robot();

		color.getRGB();
		
		r = color.getRed();
		g = color.getGreen();
		b = color.getBlue();

		BufferedImage image = robot.createScreenCapture(new Rectangle(0, 0, (int) Swidth, (int) Sheight));

		loopy:
//		for (int xPixel : width) { // Read screen up and down then +1 pixel right
//			for (int yPixel : height) {
			
			 for (int yPixel : height){//read screen left to right, then down
				  for (int xPixel : width) {

				int clr = image.getRGB(xPixel, yPixel);
				int red = (clr & 0x00ff0000) >> 16;
				int green = (clr & 0x0000ff00) >> 8;
				int blue = (clr & 0x000000ff);
				if (red == r && green == g && blue == b) {
					print("\n" + "Found color of (r = " + red + ", g = " + green + ", b = " + blue + ") at " + xPixel
							+ ", " + yPixel);

					found = true;
					windowFoundX = xPixel;
					windowFoundY = yPixel;

					break loopy;
				}
				if (debugging)
					print(xPixel + ", " + yPixel);
				timesChecked++;
			}
			timesChecked++;
		}
		print("\nFinished nested enhanced for loop \n");

		if (found)
			print("FeelsGoodMan, found it at " + windowFoundX + ", " + windowFoundY);
		else
			print("i didnt find it :(");

		if (debugging)
			print("Total number of pixels read = " + timesChecked);
	}
	
	public static void findWindow(Color color, Color color2) throws AWTException {
		Robot robot = new Robot();

		color.getRGB();
		color2.getRGB();

		r = color.getRed();
		g = color.getGreen();
		b = color.getBlue();
		r2 = color2.getRed();
		g2 = color2.getGreen();
		b2 = color2.getBlue();
		
		BufferedImage image = robot.createScreenCapture(new Rectangle(0, 0, (int) Swidth, (int) Sheight));

		loopy:
//		for (int xPixel : width) { // Read screen up and down then +1 pixel right
//			for (int yPixel : height) {
			
			 for (int yPixel : height){//read screen left to right, then down
				  for (int xPixel : width) {

				int clr = image.getRGB(xPixel, yPixel);
				int red = (clr & 0x00ff0000) >> 16;
				int green = (clr & 0x0000ff00) >> 8;
				int blue = (clr & 0x000000ff);
				
				int clr2 = image.getRGB(xPixel, yPixel);
				int red2 = (clr & 0x00ff0000) >> 16;
				int green2 = (clr & 0x0000ff00) >> 8;
				int blue2 = (clr & 0x000000ff);
				
				if ( (red == r && green == g && blue == b) && (red2 == r2 && green2 == g2 && blue2 == b2) ) {
					print("\n" + 
							"Found color of (r = " + red + ", g = " + green + ", b = " + blue + ") ("
							+ "r2 = " + red2 + ", g2 = " + green2 + ", b2 = " + blue2
							+ ") at " + xPixel + ", " + yPixel );

					found = true;
					windowFoundX = xPixel;
					windowFoundY = yPixel;

					break loopy;
				}
				if (debugging)
					print(xPixel + ", " + yPixel);
				timesChecked++;
			}
			timesChecked++;
		}
		print("\nFinished nested enhanced for loop \n");

		if (found)
			print("FeelsGoodMan, found it at " + windowFoundX + ", " + windowFoundY);
		else
			print("i didnt find it :(");

		if (debugging)
			print("Total number of pixels read = " + timesChecked);
	}



	public static boolean check(Coordinate coord, Color color2) throws AWTException {
		Robot robot = new Robot();
		int r, g, b;

		int x = coord.x;
		int y = coord.y;

		Color color = robot.getPixelColor(x, y);
		color2.getRGB();
		
		if(secondMonitorInUse())
			x = +secondMonitorWidth;
		
		r = color2.getRed();
		g = color2.getGreen();
		b = color2.getBlue();

		if ((r == color.getRed()) && (g == color.getGreen()) && (b == color.getBlue())) {

			printRGB(r, g, b);

			return true;
		} else {
			if (Main.colourdebuggingfalse)
				Main.print("Didn't find colour of - not listed " + " \n" + "-------------------" + "\n\n");
			return false;
		}
	}

	public static boolean check(Coordinate coord, Color color2, String str) throws AWTException {
		Robot robot = new Robot();
		int r, g, b;

		int x = coord.x;
		int y = coord.y;

		Color color = robot.getPixelColor(x, y);
		color2.getRGB();

		if(secondMonitorInUse())
			x = +secondMonitorWidth;
		
		r = color2.getRed();
		g = color2.getGreen();
		b = color2.getBlue();

		if ((r == color.getRed()) && (g == color.getGreen()) && (b == color.getBlue())) {

			printRGB(str, r, g, b);

			return true;
		} else {
			if (Main.colourdebuggingfalse)
				Main.print("Didn't find colour of " + str + " \n" + "-------------------" + "\n\n");
			return false;
		}
	}

	public static boolean check(Coordinate coord, int r, int g, int b, String str) throws AWTException {
		int x = coord.x;
		int y = coord.y;

		Robot robot = new Robot();
		Color color = robot.getPixelColor(x, y);
		
		
		if(secondMonitorInUse())
			x = +secondMonitorWidth;

		if ((r == color.getRed()) && (g == color.getGreen()) && (b == color.getBlue())) {

			printRGB(str, r, g, b);

			return true;
		} else {
			if (Main.colourdebuggingfalse)
				Main.print("Didn't find colour of " + str + " \n" + "-------------------" + "\n\n");
			return false;
		}
	}

	public static boolean check(int x, int y, int r, int g, int b, String str) throws AWTException {
		Date date = new Date();
		Robot robot = new Robot();
		Color color = robot.getPixelColor(x, y);
		
		if(secondMonitorInUse())
			x = +secondMonitorWidth;

		if ((r == color.getRed()) && (g == color.getGreen()) && (b == color.getBlue())) {

			printRGB(str, r, g, b);

			return true;
		} else {
			if (Main.colourdebuggingfalse)
				Main.print("Didn't find colour of " + str + " \n" + "-------------------" + "\n\n");
			return false;
		}
	}

	public static boolean check(int x, int y, Color color2, String str) throws AWTException {
		// Date date = new Date();
		Robot robot = new Robot();
		int r, g, b;
		Color color = robot.getPixelColor(x, y);
		color2.getRGB();
		
		if(secondMonitorInUse())
			x = +secondMonitorWidth;

		r = color2.getRed();
		g = color2.getGreen();
		b = color2.getBlue();

		if ((r == color.getRed()) && (g == color.getGreen()) && (b == color.getBlue())) {
			printRGB(r, g, b);
			
			return true;
		} else {
			if (Main.colourdebuggingfalse)
				Main.print("Didn't find colour of " + str + " \n" + "-------------------" + "\n\n");
			return false;
		}
	}

	public static boolean check(int x, int y, Color color2) throws AWTException {
		// Date date = new Date();
		Robot robot = new Robot();
		int r, g, b;
		Color color = robot.getPixelColor(x, y);
		color2.getRGB();
		
		if(secondMonitorInUse())   
			x = +secondMonitorWidth;

		r = color2.getRed();
		g = color2.getGreen();
		b = color2.getBlue();

		if ((r == color.getRed()) && (g == color.getGreen()) && (b == color.getBlue())) {

			printRGB(r, g, b);

				/* + "Time - " + date.toString() + "\n" */
			
			return true;
		} else {
			if (Main.colourdebuggingfalse)
				Main.print("Didn't find colour of - not listed " + " \n" + "-------------------" + "\n\n");
			return false;
		}
	}

	public static void printRGB(String str, int r, int g, int b) {
		if (Main.colourdebugging) {
			Main.print("Found colour - " + str + "\n" + "  Red    = " + r + "  Green = " + g
					+ "  Blue  = " + b + "\n");
		}
	}
	
	public static void printRGB( int r, int g, int b) {
		if (Main.colourdebugging) {
			Main.print("Found colour - " + "Untitled" + "\n" + "  Red   = " + r + "  Green = " + g
					+ "  Blue  = " + b + "\n");
		}
	}
	
	public static Color intToColor(int rgb) {
		int red = (rgb & 0x00ff0000) >> 16;
		int green = (rgb & 0x0000ff00) >> 8;
		int blue = (rgb & 0x000000ff);

		return new Color(red, green, blue);
	}
	
	public int colorToInt(Color color) {	
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
				
		return 0xFF000000 | red | green | blue;
	}
	
	public static String intToString(int rgb) {
		int red = (rgb & 0x00ff0000) >> 16;
		int green = (rgb & 0x0000ff00) >> 8;
		int blue = (rgb & 0x000000ff);

		return "R: " + red + " G: " + green + " B: " + blue + " ";
	}
	
	public static Coordinate getColorCoord(Color col) throws AWTException {
		Robot r = new Robot();
		Coordinate coord = null;
		int x = 0, y = 0;
		
		BufferedImage img2 = r.createScreenCapture(new Rectangle(0, 0, (int) Swidth, (int) Sheight));
		int screenWidth = img2.getWidth();
		int screenHeight = img2.getHeight();
		
		for(int width = 0; width < Swidth; width++) {
			for(int height = 0; height < Sheight; height++) {
				
				if(img2.getRGB(width, height) == col.getRGB()) {
					coord = new Coordinate(width,height);
					break;
				}
			
			}
		}
		return coord;
		
	}
	
}
