/* Ahmad Lubis, Alex Krach, Carson Schubert 
 *  Gallatin 3rd
 *  stealthy stealthy man sneaks Around
 */
package main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Handler {
	List<GameObject> objects = new CopyOnWriteArrayList<GameObject>();
	 
	/**
	 * This method calls each of the game's object's tick method, which updates their information.
	 */
	public void tick()
	{
		for(GameObject g:objects)
			g.tick();
	}
	
	/**
	 * This method repaints all of the GameObjects onto the screen.
	 * @param g2 - The Graphics2D object that paints each object.
	 */
	public void render(Graphics2D g2){
		for(GameObject g:objects)
			g.render(g2);
	}
	
	/**
	 * A method to add a GameObject into the List of GameObjects.
	 * @param g - the GameObject that you are adding.
	 */
	public void addObject(GameObject g){
		objects.add(g);
	}
	
	/**
	 * A method that removes a specified GameObject from the List.
	 * @param g - the GameObject that you wish to remove.
	 * @return - the GameObject that you are removing.
	 */
	public GameObject removeObject(GameObject g){
		for(int i = 0; i<objects.size(); i++){
			if(objects.get(i).equals(g))
				return objects.remove(i);
		}
		return null;
	}
	
	/**
	 * A method that returns a specified GameObject
	 * @param g - the GameObject that you want to get.
	 * @return - the GameObject that you want to get.
	 */
	public GameObject getObject(GameObject g){
		for(int i = 0; i<objects.size(); i++){
			if(objects.get(i).equals(g))
				return objects.get(i);
		}
		return null;
	}
	
	/**
	 * A method that returns an ArrayList of Walls.
	 * @return - an ArrayList of Walls.
	 */
	public ArrayList<Wall> getWalls(){
		ArrayList<Wall> ret = new ArrayList<Wall>();
		for(int i = 0; i<objects.size(); i++){
			if(objects.get(i).getId() == ID.Wall)
				ret.add((Wall)objects.get(i));
		}
		return ret;
	}
	
	/**
	 * A method that resets all of the GameObjects in the Handler's list.
	 */
	public void reset(){
		for(int i = 0; i<objects.size(); i++){
			GameObject temp = objects.get(i);
			temp.reset();
		}
	}
}
