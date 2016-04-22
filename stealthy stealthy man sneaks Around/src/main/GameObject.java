package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class GameObject {
	private int x, y;
	private ID id;
	private int velX, velY;
	
	public GameObject(int x, int y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics2D g2);
	
	public abstract Rectangle getBounds();
	
	public abstract Rectangle getTop();
	
	public abstract Rectangle getBottom();
	
	public abstract void reset();
	
	public int getX() {return x;}
	
	public int getY() {return y;}
	
	public int getVelX() {return velX;}
	
	public int getVelY() {return velY;}
	
	public ID getId() {return id;}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setVelX(int velX){
		this.velX = velX;
	}
	
	public void setVelY(int velY){
		this.velY = velY;
	}
	
	public void setId(ID id){
		this.id = id;
	}
}
