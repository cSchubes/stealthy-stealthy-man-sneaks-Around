package main;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Game extends JFrame{
	private ArrayList<Stealthy> levels;
	public static MainMenu main;
	
	public Game(){
		setSize(1000, 775);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Stealthy stealth = new Stealthy(this);
		stealth.setFocusable(true);
		
		main = new MainMenu(stealth, this);
		
		add(main);
		setVisible(true);
	}
	
	public void removeAll(){
		this.removeAll();
		add(main);
	}
	
	public static void main (String[] args){
		Game g = new Game();
	}
}
