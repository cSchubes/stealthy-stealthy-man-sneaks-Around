package main;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Stealthy extends Canvas implements Runnable{
	private boolean running;
	private Handler handler;
	private HUD hud;
	private JFrame gameFrame;
	private Stealthy nextLevel;
	
	public Stealthy(JFrame frame, Stealthy next){
		handler = new Handler();
		hud = new HUD();
		gameFrame = frame;
		nextLevel = next;
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
			while(delta >= 1)
				delta--;
			if (running){
				tick();
				render();
			}
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
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();
		
		handler.render(g2);
		hud.render(g2);
		
		g2.dispose();
		bs.show();
	}
}
