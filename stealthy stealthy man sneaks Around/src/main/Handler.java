package main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Handler {
	List<GameObject> objects = new CopyOnWriteArrayList<GameObject>();
	
	public void tick()
	{
		for(GameObject g:objects)
			g.tick();
	}
	
	public void render(Graphics2D g2){
		for(GameObject g:objects)
			g.render(g2);
	}
	
	public void addObject(GameObject g){
		objects.add(g);
	}
	
	public GameObject removeObject(GameObject g){
		for(int i = 0; i<objects.size(); i++){
			if(objects.get(i).equals(g))
				return objects.remove(i);
		}
		return null;
	}
	
	public GameObject getObject(GameObject g){
		for(int i = 0; i<objects.size(); i++){
			if(objects.get(i).equals(g))
				return objects.get(i);
		}
		return null;
	}
	
	public ArrayList<Wall> getWalls(){
		ArrayList<Wall> ret = new ArrayList<Wall>();
		for(int i = 0; i<objects.size(); i++){
			if(objects.get(i).getId() == ID.Wall)
				ret.add((Wall)objects.get(i));
		}
		return ret;
	}
	
	public void reset(){
		for(int i = 0; i<objects.size(); i++){
			GameObject temp = objects.get(i);
			temp.reset();
		}
	}
}
