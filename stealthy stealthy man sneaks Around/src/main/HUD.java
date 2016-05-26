/* Ahmad Lubis, Alex Krach, Carson Schubert 
 *  Gallatin 3rd
 *  stealthy stealthy man sneaks Around
 */
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class HUD {
	
	private double lastTime, currentTime, elapsed;
	private int minute, ten, one;
	
	/**
	 * The constructor for the HUD.
	 */
	public HUD(){
		minute = 0;
		ten = 0;
		one = -1;
		elapsed = 0;
		lastTime = 0;
		currentTime = 0;
	}
	/**
	 * A tick method that updates the time in the HUD.
	 */
	public void tick(){
		currentTime = System.nanoTime();
		elapsed += (currentTime - lastTime);
		if(elapsed >= 1000000000){
			one += 1;
			if(one == 10){
				ten+=1;
				one = 0;
				if(ten == 6){
					minute += 1;
					ten = 0;
				}
			}
			elapsed = 0;
		}
		lastTime = System.nanoTime();
	}
	
	/**
	 * A method that displays the time ellapsed in the game.
	 * @param g2 - The Graphics object that paints the information.
	 */
	public void render(Graphics2D g2){
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING,
	             RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		g2.setColor(Color.CYAN);
		g2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		g2.drawString(minute + ":" + ten + one, 5, 25);
	}
	
	/**
	 * A method to reset the timer.
	 */
	public void reset(){
		minute = ten = one = 0;
		one = -1;
		lastTime = currentTime = elapsed = 0;
	}
	
	/**
	 * A method to set the clock
	 * @param clock - an int array that holds the time information.
	 */
	public void setClock(int[] clock){
		minute = clock[0];
		ten = clock[1];
		one = clock[2];
	}
	
	/**
	 * A method that returns the minute.
	 * @return - the minute.
	 */
	public int getMinute(){
		return minute;
	}
	
	/**
	 * A method that returns the tens place.
	 * @return - the tens place.
	 */
	public int getTen(){
		return ten;
	}
	
	/**
	 * A method that returns the seconds place.
	 * @return - the seconds place.
	 */
	public int getSecond(){
		return one;
	}
}
