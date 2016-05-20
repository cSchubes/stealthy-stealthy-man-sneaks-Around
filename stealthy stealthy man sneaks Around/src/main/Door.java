package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

public class Door extends GameObject{

	private Color col;
	private boolean closed;
	
	public Door(int x, int y, Color c){
		super(x, y, 0, ID.Door);
		col = c;
		closed = true;
	}
	
	public void tick() {
		
	}

	public void render(Graphics2D g2) {
		g2.setColor(col);
		if(closed)
			g2.fillRect(getX(), getY(), 50, 10);
		else
			g2.fillRect(getX(), getY(), 10, 50);
	}
	
	public Shape getBounds() {
		if(closed)
			return new Rectangle(getX(), getY(), 50, 10);
		else
			return new Rectangle(getX(), getY(), 10, 50);
	}

	public void reset() {
		closed = true;
	}
	
	public Color getColor(){
		return col;
	}
	
	public void open(){
		closed = false;
	}
}
