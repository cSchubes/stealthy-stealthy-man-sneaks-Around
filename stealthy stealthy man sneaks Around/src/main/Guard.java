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
	
	public Guard(int x, int y, int speed, int left, int right, int direction, Handler handler){
		super(x, y, speed, ID.Guard);
		leftBound = left;
		rightBound = right;
		this.direction = direction;
		initD = direction;
		this.handler = handler;
		up = down = this.left = this.right = true;
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
			return new Rectangle(getX() - 1000, getY() - 15, 1040, 50);
		}
		else if(direction == RIGHT)
			return new Rectangle(getX() - 15 , getY() - 15, 1040, 50);
		else if(direction == UP){
			return new Rectangle(getX()-15, getY() - 1000, 50, 1040);
		}
		else
			return new Rectangle(getX()-15, getY() - 15, 50, 1040);
	}
	
	public Point2D.Double getLeftTop(){
		return new Point2D.Double(getX(), getY()+3);
	}
	
	public Point2D.Double getLeftBot(){
		return new Point2D.Double(getX(), getY() + 21);
	}
	
	public Point2D.Double getTopLeft(){
		return new Point2D.Double(getX() + 3, getY());
	}
	
	public Point2D.Double getTopRight(){
		return new Point2D.Double(getX() + 21, getY());
	}
	
	public Point2D.Double getRightTop(){
		return new Point2D.Double(getX() + 25, getY() + 3);
	}
	
	public Point2D.Double getRightBot(){
		return new Point2D.Double(getX() + 25, getY() + 21);
	}
	
	public Point2D.Double getBotLeft(){
		return new Point2D.Double(getX() + 3, getY() + 25);
	}
	
	public Point2D.Double getBotRight(){
		return new Point2D.Double(getX() + 21, getY() + 25);
	}
	
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
