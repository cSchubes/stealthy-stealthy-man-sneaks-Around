/* Ahmad Lubis, Alex Krach, Carson Schubert 
 *  Gallatin 3rd
 *  stealthy stealthy man sneaks Around
 */
package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

public class Door extends GameObject{

	private Color col;
	private boolean closed, vertical, in, right;
	
	/**
	 * A constructor for a Door.
	 * @param x - th x-coord of the door
	 * @param y - the y-coord of the door
	 * @param c - the color of the Door.
	 * @param v - A boolean that determines if the Door is vertical or not.
	 * @param in - a boolean that determines if the door opens in or out.
	 * @param right - a boolean that determines if the door is on the right or left.
	 */
	public Door(int x, int y, Color c, boolean v, boolean in, boolean right){
		super(x, y, 0, ID.Door);
		col = c;
		closed = true;
		vertical = v;
		this.in = in;
		this.right = right;
	}
	
	public void tick() {
		
	}

	/**
	 * A method that draws the door.
	 */
	public void render(Graphics2D g2) {
		g2.setColor(col);
		Rectangle outline = null;
		if(!vertical){
			if(closed){
				outline = new Rectangle(getX(), getY(), 49, 10);
				g2.fill(outline);
			}
			else
			{	
				if(in){
					if(right){
						outline = new Rectangle(getX() + 40, getY()-40, 10, 49);
						g2.fill(outline);
					}
					else{
						outline = new Rectangle(getX(), getY()-40, 10, 49);
						g2.fill(outline);
					}
				}
				else{
					if(right){
						outline = new Rectangle(getX() + 40, getY(), 10, 49);
						g2.fill(outline);
					}
					else{
						outline = new Rectangle(getX(), getY(), 10, 49);
						g2.fill(outline);
					}	
				}
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
	
	/**
	 * A method that gets the bounds of the door.
	 */
	public Shape getBounds() {
		if(!vertical){
			if(closed){
				return new Rectangle(getX(), getY(), 49, 10);
			}
			else
			{	
				if(in){
					if(right){
						 return new Rectangle(getX() + 40, getY()-40, 10, 49);
					}
					else{
						return new Rectangle(getX(), getY()-40, 10, 49);
					}
				}
				else{
					if(right){
						return new Rectangle(getX() + 40, getY(), 10, 49);
					}
					else{
						return new Rectangle(getX(), getY(), 10, 49);
					}	
				}
			}	
		}
		else{
			if(closed){
				return new Rectangle(getX(), getY(), 10, 49);
			}
			else{
				return new Rectangle(getX()-39, getY(), 49, 10);
			}
		}
	}

	/**
	 * a method that closes the door
	 */
	public void reset() {
		closed = true;
	}
	
	/**
	 * a method that returns the color of the door.
	 * @return - the color of the door.
	 */
	public Color getColor(){
		return col;
	}
	
	/**
	 * A method that opens the door.
	 */
	public void open(){
		closed = false;
	}
}
