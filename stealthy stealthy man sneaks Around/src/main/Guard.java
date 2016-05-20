package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;

public class Guard extends GameObject {
	
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	
	private int leftBound, rightBound;
	private int direction;
	private int initD;
	
	public Guard(int x, int y, int speed, int left, int right, int direction){
		super(x, y, speed, ID.Guard);
		leftBound = left;
		rightBound = right;
		this.direction = direction;
		initD = direction;
	}
	
	public void tick() {
		if(getVelX()>0){
			if(getX()>rightBound){
				setVelX(-getSpeed());
				direction = LEFT;
			}
		}
		else if(getVelX()<0){
			if(getX()<leftBound){
				setVelX(getSpeed());
				direction = RIGHT;
			}
		}
		setX(getX() + getVelX());
		setY(getY() + getVelY());
	}

	public void render(Graphics2D g2) {
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING,
	             RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		g2.setColor(Color.RED);
		g2.fillRect(getX(), getY(), 25, 25);
		g2.setColor(Color.BLACK);
		if(direction == RIGHT){
			g2.fillOval(getX() + 15, getY() + 6, 5, 5);
			g2.fillOval(getX() + 15, getY() + 16, 5, 5);
		}
		else{
			g2.fillOval(getX() + 5, getY() + 6, 5, 5);
			g2.fillOval(getX() + 5, getY() + 16, 5, 5);
		}
	}

	public Shape getBounds() {
		return new Rectangle(5,5,5,5);
	}

	public void reset() {
		setX(getInitX());
		setY(getInitY());
		direction = initD;
	}
	
	public Rectangle getVision(){
		if(direction == LEFT){
			return new Rectangle(getX() - 1000, getY() - 15, 1050, 50);
		}
		else
			return new Rectangle(getX() - 25 , getY() - 15, 1050, 50);
	}
}
