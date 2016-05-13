package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

public class Wall extends GameObject{
	
	Polygon poly;

	public Wall(int x, int y, int speed, ID id, Polygon p){
		super(x, y, speed, id);
		poly = p;
	}
	
	public void tick() {
		
	}

	public void render(Graphics2D g2) {
		g2.setColor(Color.BLUE);
		g2.fill(poly);
	}

	public Rectangle getBounds() {
		return null;
	}

	public void reset() {
		
	}
}
