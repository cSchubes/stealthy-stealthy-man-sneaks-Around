package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Stealthy extends JPanel implements Runnable, KeyListener{
	private boolean running;
	private Handler handler;
	private HUD hud;
	private JFrame gameFrame;
	//private Stealthy nextLevel;
	
	public Stealthy(JFrame frame){
		handler = new Handler();
		addKeyListener(this);
		handler.addObject(new Player(150, 250, 3, ID.Player));
		int xpoints[] = {0, 1000, 1000, 0};
		int ypoints[] = {0, 0, 50, 50};
		Polygon p = new Polygon(xpoints, ypoints, 4);
		handler.addObject(new Wall(0, 0, 0, ID.Wall, p));
		hud = new HUD();
		gameFrame = frame;
		running = true;
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
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i<handler.objects.size(); i++){
			GameObject temp = handler.objects.get(i);
			
			if(temp.getId() == ID.Player){
				if(key == KeyEvent.VK_LEFT) {
					temp.setVelX(-temp.getSpeed());
				}
				if(key == KeyEvent.VK_RIGHT) {
					temp.setVelX(temp.getSpeed());
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
