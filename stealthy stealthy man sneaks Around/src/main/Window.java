package main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = -4810618286807932601L;
	
	public Window(int width, int height, String title, Stealthy pong) {
		JFrame frame = new JFrame(title);
		
		frame.setSize(new Dimension(width, height));
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(pong);
		frame.setVisible(true);
		
		Thread runner = new Thread(pong);
		runner.start();
	}
}
