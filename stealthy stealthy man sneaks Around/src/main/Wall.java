/* Ahmad Lubis, Alex Krach, Carson Schubert 
 *  Gallatin 3rd
 *  stealthy stealthy man sneaks Around
 */
package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

public class Wall extends GameObject{
	
	private Polygon poly;
	private boolean lost, colorSwitcher;
	private long lastTime, currentTime, elapsed;
	private Color col;

	/**
	 * A construtctor for the wall object
	 * @param x - the x-coord of the wall object
	 * @param y - the y-coord of the wall
	 * @param speed - the speed of the wall
	 * @param p - the Polygon of the wall drawn
	 */
	public Wall(int x, int y, int speed, Polygon p){
		super(x, y, speed, ID.Wall);
		poly = p;
		lost = false;
		lastTime = 0;
		currentTime = 0;
		elapsed = 0;
		col = Color.BLACK;
		colorSwitcher = false;
	}
	
	/**
	 * a method that updates the wall's information
	 */
	public void tick() {
		if(lost){
			currentTime = System.nanoTime();
			elapsed += (currentTime - lastTime);
			if(elapsed >= 500000000){
				if(!colorSwitcher){
					col = Color.RED;
					colorSwitcher = true;
				}
				else{
					col = Color.BLACK;
					colorSwitcher = false;
				}
				elapsed = 0;
			}
			lastTime = System.nanoTime();
		}
	}

	/**
	 * a method that paints the wall
	 */
	public void render(Graphics2D g2) {
		g2.setColor(col);
		g2.fill(poly);
	}

	/**
	 * a wall that gets the bounds of the wall
	 */
	public Shape getBounds() {
		return poly;
	}

	/**
	 * a method that resets the wall
	 */
	public void reset() {
		lost = false;
		col = Color.BLACK;
		lastTime = currentTime = elapsed = 0;
	}
	
	/**
	 * a method that changes the lost boolean
	 */
	public void lost(){
		lost = true;
	}
}
