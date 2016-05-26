/* Ahmad Lubis, Alex Krach, Carson Schubert 
 *  Gallatin 3rd
 *  stealthy stealthy man sneaks Around
 */
package main;

import java.awt.Graphics2D;
import java.awt.Shape;

public abstract class GameObject {
	private int x, y;
	private int initX, initY;
	private ID id;
	private int velX, velY;
	private int speed;
	
	/**
	 * A constructor for a GameObject
	 * @param x - the x-coordinate
	 * @param y - the y-coordinate
	 * @param speed - the speed of the object
	 * @param id - the id of the object.
	 */
	public GameObject(int x, int y, int speed, ID id){
		this.x = x;
		this.y = y;
		initX = x;
		initY = y;
		this.id = id;
		this.speed = speed;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics2D g2);
	
	public abstract Shape getBounds();
	
	public abstract void reset();
	
	public int getX() {return x;}
	
	public int getY() {return y;}
	
	public int getVelX() {return velX;}
	
	public int getVelY() {return velY;}
	
	public ID getId() {return id;}
	
	/**
	 * A method that sets the x-coord
	 * @param x - the x-coord that you want to change to
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * a method that changes the y-coordinate
	 * @param y - the new y-coordinate
	 */
	public void setY(int y){
		this.y = y;
	}
	
	/**
	 * A method that sets the xVelocity
	 * @param velX - the new xVelocity.
	 */
	public void setVelX(int velX){
		this.velX = velX;
	}
	
	/**
	 * A method that sets the y Velocity
	 * @param velY - the new yVelocity.
	 */
	public void setVelY(int velY){
		this.velY = velY;
	}
	
	/**
	 * A method that set the ID.
	 * @param id - the new ID.
	 */
	public void setId(ID id){
		this.id = id;
	}
	
	/**
	 * A method that sets the speed.
	 * @param s the new speed
	 */
	public void setSpeed(int s){
		speed = s;
	}
	
	/**
	 * A method that gets the speed.
	 * @return the speed.
	 */
	public int getSpeed(){
		return speed;
	}
	
	/**
	 * A method that returns the initial x.
	 * @return the initial x-coord.
	 */
	public int getInitX(){
		return initX;
	}
	
	/**
	 * A method that gets the initial y.
	 * @return the initial y-coord.
	 */
	public int getInitY(){
		return initY;
	}
}
