package main;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Game extends JFrame{
	private ArrayList<Stealthy> levels;
	
	public Game(){
		setSize(1000, 750);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Stealthy stealth = new Stealthy(this);
		stealth.setFocusable(true);
		add(stealth);
		Thread t = new Thread(stealth);
		t.start();
		setVisible(true);
	}
	
	public static void main (String[] args){
		Game g = new Game();
	}
}
