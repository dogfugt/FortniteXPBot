/*import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.KeyStroke;

public class Test {
	
	*//**
	 * at the moment this works as a find window class
	*//*
	
	
	 * 
	 * https://stackoverflow.com/questions/1248510/convert-string-to-keyevents
	 * ^-- read that
	 * 
	 * 
	have coordinate.java class say 
	this.x = getFoundWindowX() + x;
	this.y = getFoundWindowY() + y;
	
	//put in checkcolor class
	
	public int getFoundWindowX() {
		return windowFoundX;
	}
	
	public int getFoundWindowY() {
		return windowFoundY;
	}
	
	
	static boolean found,debug = true;
	static int r = 163, g = 73, b = 164;
	
	static int wantedPixelX = 2, wantedPixelY = 2;
	
	static int windowFoundX, windowFoundY;
	
	static int timesChecked;
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static double Sheight = screenSize.getHeight();
	static double Swidth =  screenSize.getWidth();
	
	
	
	static int[] width =  java.util.stream.IntStream.rangeClosed(0, (int) Swidth-1).toArray(),
				height =  java.util.stream.IntStream.rangeClosed(0, (int) Sheight-1).toArray();

	public static void main(String args[]) throws AWTException {


		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(new Rectangle(0, 0, (int) Swidth, (int) Sheight));
		
		loopy:
			for (int yPixel : height) {			
				for (int xPixel : width) {
				
			*read screen left to right, then down
			*
			
			
		for (int xPixel : width) {			//Read screen up and down then +1 pixel right
			for (int yPixel : height) {
				
			    int color = image.getRGB(xPixel, yPixel);
			    int  red   = (color & 0x00ff0000) >> 16;
			    int  green = (color & 0x0000ff00) >> 8;
			    int  blue  = (color & 0x000000ff);
			    if(red == r && green == g && blue == b) {
			    	print("\n"
			    	+ "Found color of (r = " + red + ", g = " + green + ", b = " + blue + ") at "+ xPixel + ", " + yPixel);
					
			    	found = true;
					windowFoundX = xPixel;
					windowFoundY = yPixel;
					
			    	break loopy;
			    }

				print(xPixel + ", " + yPixel);
				
				if(xPixel == wantedPixelX && yPixel == wantedPixelY) {
					print("I found it");
				}
				
				timesChecked++;

			}
			timesChecked++;
		}
		print("\nFinished nested enhanced for loop \n");
		
		
		
		if(found)
			print("feelsgoodman, found it at " + windowFoundX + ", " + windowFoundY);
//			print("I found pixel (" + wantedPixelX + ", " + wantedPixelY + ")");
		else
			print("i didnt find it :(");
		
		print("Total number of pixels read = " + timesChecked);
		
		print("" + getKey('x'));
	}
	
	public static void print(String s) {
		System.out.println(s);
	}
	
	public static int getKey(char key) { //i dont like capital letters
		if(debug)
			print("i asked for key '" + key + "'");
		return ((int)key - 32);
	}
	
	
}*/