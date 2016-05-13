package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{
	
	private int speed;
		
	public Player(int x, int y, int speed, ID id){
		super(x, y, speed, id);
		//setVelX(2);
		speed = 3;
	}
	
	public void tick() {
		setX(getX()+getVelX());
		setY(getY()+getVelY());
	}

	public void render(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect(getX(), getY(), 50, 50);
	}

	public Rectangle getBounds() {
		return null;
	}

	public void reset() {
		
	}
}
