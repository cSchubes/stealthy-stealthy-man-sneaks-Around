package main;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

public class Wall extends GameObject{
	
	Polygon poly;

	public Wall(int x, int y, ID id, Polygon p){
		super(x, y, id);
		poly = p;
	}
	
	public void tick() {
		
	}

	public void render(Graphics2D g2) {
		
	}

	public Rectangle getBounds() {
		return null;
	}

	public void reset() {
		
	}
}
