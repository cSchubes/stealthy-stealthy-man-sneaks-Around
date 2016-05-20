package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Stealthy extends JPanel implements Runnable, KeyListener{
	private boolean running;
	private Handler handler;
	private HUD hud;
	private Game gameFrame;
	public static boolean lost;
	//private Stealthy nextLevel;
	
	public Stealthy(Game frame){
		handler = new Handler();
		addKeyListener(this);
		int wallOneXpoints[] = {0, 850, 850, 200, 200, 50, 50, 200, 200, 50, 50, 0};
		int wallOneYpoints[] = {0, 0, 50, 50, 400, 400, 450, 450, 650, 650, 750, 750};
		int wallTwoXpoints[] = {250, 850, 850, 550, 550, 600, 600, 500, 500, 250};
		int wallTwoYpoints[] = {100, 100, 500, 500, 300, 300, 250, 250, 500, 500};
		int wallThreeXpoints[] = {100, 100, 250, 250, 650, 650, 800, 800, 700, 700, 900, 900, 1000, 1000};
		int wallThreeYpoints[] = {750, 700, 700, 550, 550, 700, 700, 650, 650, 550, 550, 0, 0, 750};
		Polygon p = new Polygon(wallOneXpoints, wallOneYpoints, 12);
		Polygon p2 = new Polygon(wallTwoXpoints, wallTwoYpoints, 10);
		Polygon p3 = new Polygon(wallThreeXpoints, wallThreeYpoints, 14);
		handler.addObject(new Player(65, 675, 2, handler));
		handler.addObject(new Wall(0, 0, 0, p2));
		handler.addObject(new Wall(0, 0, 0, p));
		handler.addObject(new Wall(0, 0, 0, p3));
		handler.addObject(new Key(570, 260, Color.YELLOW));
		handler.addObject(new Door(850, 0, Color.YELLOW));
		Guard one = new Guard(260, 65, 1, 250, 800, Guard.RIGHT);
		one.setVelX(1);
		Guard two = new Guard(740, 515, 2, 250, 750, Guard.LEFT);
		two.setVelX(-2);
		handler.addObject(one);
		handler.addObject(two);
		hud = new HUD();
		gameFrame = frame;
		running = true;
		lost = false;
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running && !Thread.interrupted()){
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

			if(lost){
				int result = JOptionPane.showConfirmDialog(null, "You tripped the alarm! Head back to your cell...  \nTry for another escape?", "You lose!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(result == 1){
					gameFrame.main.setVisible(true);
					running = false;
				}
				else{
					/*
					gameFrame.dispose();
					gameFrame = new Game();
					gameFrame.main.setVisible(false);
					Stealthy s = new Stealthy(gameFrame);
					Thread t = new Thread(s);
					t.start();
					gameFrame.removeAll();
					*/
					resetAll();
				}
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
		g2.setColor(Color.WHITE);
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
				if(key == KeyEvent.VK_UP){
					temp.setVelY(-temp.getSpeed());
				}
				if(key == KeyEvent.VK_DOWN){
					temp.setVelY(temp.getSpeed());
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i<handler.objects.size(); i++){
			GameObject temp = handler.objects.get(i);
			
			if(temp.getId() == ID.Player){
				if(key == KeyEvent.VK_LEFT) {
					temp.setVelX(0);
				}
				if(key == KeyEvent.VK_RIGHT) {
					temp.setVelX(0);
				}
				if(key == KeyEvent.VK_UP){
					temp.setVelY(0);
				}
				if(key == KeyEvent.VK_DOWN){
					temp.setVelY(0);
				}
			}
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	public void resetAll(){
		handler.reset();
		hud.reset();
		
	}
}
