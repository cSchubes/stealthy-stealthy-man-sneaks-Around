/* Ahmad Lubis, Alex Krach, Carson Schubert 
 *  Gallatin 3rd
 *  stealthy stealthy man sneaks Around
 */
package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Point2D;

public class Guard extends GameObject {
	
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	public static final int UP = 4;
	
	private int leftBound, rightBound;
	private int direction;
	private int initD;
	private boolean up, down, left, right;
	private Handler handler;
	private int visionExtent;
	
	/**
	 * the constructor for a guard
	 * @param x - the x-coord
	 * @param y - the y-coord
	 * @param speed - the speed of the guard
	 * @param left - if the guard is looking left
	 * @param right - if the guard is looking right
	 * @param direction - the direction of the guard's movement
	 * @param handler - the Handler that you are adding this to
	 * @param v - the extent of the guard's vision
	 */
	public Guard(int x, int y, int speed, int left, int right, int direction, Handler handler, int v){
		super(x, y, speed, ID.Guard);
		leftBound = left;
		rightBound = right;
		this.direction = direction;
		initD = direction;
		this.handler = handler;
		up = down = this.left = this.right = true;
		visionExtent = v;
	}
	
	/**
	 * a method that updates the guard's information
	 */
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
		else if(getVelY()>0){
			if(getY()>rightBound){
				setVelY(-getSpeed());
				direction = UP;
			}
		}
		else if(getVelY()<0){
			if(getY()<leftBound){
				setVelY(getSpeed());
				direction = DOWN;
			}
		}
		collision();
		if(left && getVelX()<0)
			setX(getX()+getVelX());
		if(right && getVelX()>0)
			setX(getX()+getVelX());
		if(up && getVelY()<0)
			setY(getY()+getVelY());
		if(down && getVelY()>0)
			setY(getY()+getVelY());
	}

	/**
	 * a method that paints the guard
	 */
	public void render(Graphics2D g2) {
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING,
	             RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		g2.setColor(Color.RED);
		g2.fillRect(getX(), getY(), 25, 25);
		g2.setColor(Color.BLACK);
		if(direction == RIGHT){
			g2.fillOval(getX() + 15, getY() + 5, 5, 5);
			g2.fillOval(getX() + 15, getY() + 15, 5, 5);
		}
		else if(direction == LEFT){
			g2.fillOval(getX() + 5, getY() + 5, 5, 5);
			g2.fillOval(getX() + 5, getY() + 15, 5, 5);
		}
		else if(direction == DOWN){
			g2.fillOval(getX() + 5, getY() + 15, 5, 5);
			g2.fillOval(getX() + 15, getY() + 15, 5, 5);
		}
		else{
			g2.fillOval(getX() + 5, getY() + 5, 5, 5);
			g2.fillOval(getX() + 15, getY() + 5, 5, 5);
		}
	}

	/**
	 * a method that gets the bounds of the guard
	 */
	public Shape getBounds() {
		return new Rectangle(5,5,5,5);
	}

	/**
	 * a method that resets the guard's coodinates
	 */
	public void reset() {
		setX(getInitX());
		setY(getInitY());
		direction = initD;
	}
	
	/**
	 * a method that gets the vision of the guard
	 * @return a rectangle that represents the vision of the guard
	 */
	public Rectangle getVision(){
		if(direction == LEFT){
			return new Rectangle(getX() - visionExtent, getY() - 12, 30 + visionExtent, 50);
			
		}
		else if(direction == RIGHT)
			return new Rectangle(getX() - 5 , getY() - 12, 30 + visionExtent, 50);
		else if(direction == UP){
			return new Rectangle(getX()-12, getY() - visionExtent, 50, 30 + visionExtent);
		}
		else
			return new Rectangle(getX()-12, getY() - 5, 50, 30 + visionExtent);
	}
	
	/**
	 * a method that gets the left top
	 * @return the point of the left top.
	 */
	public Point2D.Double getLeftTop(){
		return new Point2D.Double(getX(), getY()+3);
	}
	
	/**
	 * a method that gets the left bottom
	 * @return a point of the left bottom
	 */
	public Point2D.Double getLeftBot(){
		return new Point2D.Double(getX(), getY() + 21);
	}
	
	/**
	 * a method that gets the top left
	 * @return - the top left point
	 */
	public Point2D.Double getTopLeft(){
		return new Point2D.Double(getX() + 3, getY());
	}
	
	/**
	 * a method that gets the top right point
	 * @return the top right point
	 */
	public Point2D.Double getTopRight(){
		return new Point2D.Double(getX() + 21, getY());
	}
	
	/**
	 * a method that gets the right top
	 * @return the right top point.
	 */
	public Point2D.Double getRightTop(){
		return new Point2D.Double(getX() + 25, getY() + 3);
	}
	
	/**
	 * a method that gets the right bottom point
	 * @return the right bottom point
	 */
	public Point2D.Double getRightBot(){
		return new Point2D.Double(getX() + 25, getY() + 21);
	}
	
	/**
	 * a method that gets the bottom left point
	 * @return the bottom left point
	 */
	public Point2D.Double getBotLeft(){
		return new Point2D.Double(getX() + 3, getY() + 25);
	}
	
	/**
	 * a method that gets the bottom right point
	 * @return the bottom right point.
	 */
	public Point2D.Double getBotRight(){
		return new Point2D.Double(getX() + 21, getY() + 25);
	}
	
	/**
	 * a method that checks if the guard collides with any other GameObjects.
	 */
	public void collision(){
		boolean tempUp = true;
		boolean tempDown = true;
		boolean tempLeft = true;
		boolean tempRight = true;
		for(int i = 0; i<handler.objects.size(); i++){
			GameObject temp = handler.objects.get(i);
			if (temp.getId() == ID.Wall || temp.getId() == ID.Door){
				if(temp.getBounds().contains(getTopRight())||temp.getBounds().contains(getTopLeft())){
					up = false;
					tempUp = false;
				}
				else if(tempUp)
					up = true;
				if(temp.getBounds().contains(getBotLeft())||temp.getBounds().contains(getBotRight())){
					down = false;
					tempDown = false;
				}
				else if(tempDown)
					down = true;
				if(temp.getBounds().contains(getLeftTop())||temp.getBounds().contains(getLeftBot())){
					left = false;
					tempLeft = false;
				}
				else if(tempLeft)
					left = true;
				if(temp.getBounds().contains(getRightTop())||temp.getBounds().contains(getRightBot())){
					right = false;
					tempRight = false;
				}
				else if(tempRight)
					right = true;
			}
		}
	}
}
