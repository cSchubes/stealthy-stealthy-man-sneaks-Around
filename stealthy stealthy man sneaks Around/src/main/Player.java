package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Player extends GameObject{
	
	public static int LEFT_WIN = 1;
	public static int RIGHT_WIN = 2;
	public static int TOP_WIN = 3;
	public static int BOT_WIN = 4;
	
	private Handler handler;
	private boolean left, right, up, down;
	private ArrayList<Key> keys;
	private boolean lost;
	private long currentTime, lastTime, elapsed;
	private int counter, side;
		
	public Player(int x, int y, int speed, Handler handler, int side){
		super(x, y, speed, ID.Player);
		this.handler = handler;
		left = right = up = down = true;
		keys = new ArrayList<Key>();
		lost = false;
		currentTime = lastTime = elapsed = 0;
		counter = 0;
		this.side = side;
	}
	
	public void tick(){
		if(lost){
			currentTime = System.nanoTime();
			elapsed += (currentTime - lastTime);
			if(elapsed >= 1000000000){
				if(counter > 250)
					Stealthy.lost = true;
				counter++;
			}
			lastTime = System.nanoTime();
		}
		collision();
		win();
		clamp();
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
		g2.setColor(Color.GREEN);
		g2.fillRect(getX(), getY(), 25, 25);
	}

	public Shape getBounds() {
		return new Rectangle(getX(), getY(), 25, 25);
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

	public void reset() {
		setX(getInitX());
		setY(getInitY());
		setVelX(0);
		setVelY(0);
		lost = false;
		Stealthy.lost = false;
		counter = 0;
		for(Key k: keys)
			handler.addObject(k);
		keys.clear();
	}
	
	public void clamp(){
		if(getX()>1000
				){
			right = false;
		}
		if(getX()<0){
			left = false;
		}
		if(getY()>725){
			down = false;
		}
		if(getY()<0){
			up = false;
		}
	}
	
	public void collision(){
		boolean tempUp = true;
		boolean tempDown = true;
		boolean tempLeft = true;
		boolean tempRight = true;
		for(int i = 0; i<handler.objects.size(); i++){
			GameObject temp = handler.objects.get(i);
				if(temp.getId() == ID.Guard){
					Guard tempG = (Guard) temp;
					if(tempG.getVision().contains(getTopRight())||tempG.getVision().contains(getTopLeft())||tempG.getVision().contains(getBotLeft())||tempG.getVision().contains(getBotRight())||tempG.getVision().contains(getLeftTop())||tempG.getVision().contains(getLeftBot())||tempG.getVision().contains(getRightTop())||tempG.getVision().contains(getRightBot())){
						lost = true;
						ArrayList<Wall> turn = handler.getWalls();
						for(Wall w:turn){
							w.lost();
						}
					}
				}
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
				if(temp.getId() == ID.Key){
					if(temp.getBounds().intersects((Rectangle)getBounds())){
						keys.add((Key)temp);
						handler.objects.remove(i);
						openDoors();
					}
				}
			}
	}
	
	public void openDoors(){
		Key test = keys.get(keys.size()-1);
		for(int i = 0; i<handler.objects.size(); i++){
			GameObject temp = handler.objects.get(i);
			if(temp.getId() == ID.Door){
				if(((Door)temp).getColor().equals(test.getColor()))
					((Door)temp).open();
			}
		}
	}
	
	public void win(){
		if(side == LEFT_WIN){
			if(getX()<0)
				Stealthy.win = true;
		}
		else if(side == RIGHT_WIN){
			if(getX()>975)
				Stealthy.win = true;
		}
		else if(side == TOP_WIN){
			if(getY() < 0)
				Stealthy.win = true;
		}
		else
			if(getY()>725)
				Stealthy.win = true;
	}
}
