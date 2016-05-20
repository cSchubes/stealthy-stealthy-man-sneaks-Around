package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;

public class Key extends GameObject{
	
	private Color col;

	public Key(int x, int y, Color c){
		super(x, y, 0, ID.Key);
		col = c;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g2) {
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING,
	             RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setRenderingHints(rh);
		g2.setColor(col);
		g2.fillOval(getX(), getY(), 12, 12);
		g2.fillRect(getX()+4, getY()+12, 4, 15);
		g2.fillRect(getX()+2, getY()+16, 2, 2);
		g2.fillRect(getX()+2, getY()+22, 2, 2);
		g2.setColor(Color.WHITE);
		g2.fillOval(getX() + 4, getY()+4, 4, 4);
		g2.setColor(Color.BLACK);
		g2.drawOval(getX(), getY(), 12, 12);
		g2.drawRect(getX()+4, getY()+12, 4, 15);
		g2.drawRect(getX()+2, getY()+16, 2, 2);
		g2.drawRect(getX()+2, getY()+22, 2, 2);
		g2.drawOval(getX() + 4, getY()+4, 4, 4);
	}

	@Override
	public Shape getBounds() {
		return new Rectangle(getX(), getY(), 12, 27);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
	public Color getColor(){
		return col;
	}

}
