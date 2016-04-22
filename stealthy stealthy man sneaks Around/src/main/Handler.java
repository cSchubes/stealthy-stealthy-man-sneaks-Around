package main;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Handler {
	ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public void tick(){
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
}
