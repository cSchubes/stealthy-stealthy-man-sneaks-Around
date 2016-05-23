package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

public class Door extends GameObject{

	private Color col;
	private boolean closed, vertical;
	
	public Door(int x, int y, Color c, boolean v){
		super(x, y, 0, ID.Door);
		col = c;
		closed = true;
		vertical = v;
	}
	
	public void tick() {
		
	}

	public void render(Graphics2D g2) {
		g2.setColor(col);
		Rectangle outline = null;
		if(!vertical){
			if(closed){
				outline = new Rectangle(getX(), getY(), 49, 10);
				g2.fill(outline);
			}
			else{
				outline = new Rectangle(getX(), getY(), 10, 49);
				g2.fill(outline);
			}
				
		}
		else{
			if(closed){
				outline = new Rectangle(getX(), getY(), 10, 49);
				g2.fill(outline);
			}
			else{
				outline = new Rectangle(getX()-39, getY(), 49, 10);
				g2.fill(outline);
			}
		}
		g2.setColor(new Color(139, 69, 19));
		g2.draw(outline);
	}
	
	public Shape getBounds() {
		if(!vertical){
			if(closed)
				return new Rectangle(getX(), getY(), 50, 10);
			else
				return new Rectangle(getX(), getY(), 10, 50);
		}
		else{
			if(closed)
				return new Rectangle(getX(), getY(), 10, 50);
			else
				return new Rectangle(getX()-38, getY(), 50, 10);
		}
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
