package main;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Game extends JFrame{
	private ArrayList<Stealthy> levels;
	
	public Game(){
		setSize(1000, 750);
		setLocationRelativeTo(null);
		
		
		setVisible(true);
	}
	
	public static void main (String[] args){
		new Game();
	}
}
