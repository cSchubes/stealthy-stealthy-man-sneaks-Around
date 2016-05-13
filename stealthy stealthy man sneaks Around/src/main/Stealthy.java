package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Stealthy extends JPanel implements Runnable{
	private boolean running;
	private Handler handler;
	private HUD hud;
	private JFrame gameFrame;
	//private Stealthy nextLevel;
	
	public Stealthy(JFrame frame){
		handler = new Handler();
		handler.addObject(new Player(150,250, ID.Player));
		hud = new HUD();
		gameFrame = frame;
		running = true;
		frame.add(this);
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}	
	}
	
	public void tick(){
		handler.tick();
		hud.tick();
	}
	
	public void render(){
		repaint();
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.ORANGE);
		g2.fillRect(0, 0, 1000, 750);
		
		handler.render(g2);
		hud.render(g2);
		
		g2.dispose();
	}
}
