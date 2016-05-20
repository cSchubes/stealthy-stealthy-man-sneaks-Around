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

	public void render(Graphics2D g2) {
		g2.setColor(col);
		g2.fill(poly);
	}

	public Shape getBounds() {
		return poly;
	}

	public void reset() {
		lost = false;
		col = Color.BLACK;
		lastTime = currentTime = elapsed = 0;
	}
	
	public void lost(){
		lost = true;
	}
}
