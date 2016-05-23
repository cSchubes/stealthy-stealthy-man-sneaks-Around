package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Stealthy extends JPanel implements Runnable, KeyListener{
	private boolean running;
	private Handler handler;
	private HUD hud;
	private Game gameFrame;
	public static boolean lost;
	private int currentLevel;
	public static boolean win;
	
	public Stealthy(Game frame){
		handler = new Handler();
		addKeyListener(this);
		hud = new HUD();
		gameFrame = frame;
		running = true;
		lost = false;
		win = false;
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
					gameFrame.main.resetStealthy();
					resetAll();
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
			
			if(win){
				currentLevel++;
				handler = gameFrame.getHandler(currentLevel);
				int[] clock = {hud.getMinute(), hud.getTen(), hud.getSecond()};
				hud = gameFrame.getHUD(currentLevel);
				hud.setClock(clock);
				win = false;
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
		//hud.reset();
	}
	
	public void setLevel(int x){
		handler = gameFrame.getHandler(x);
		hud = gameFrame.getHUD(x);
		currentLevel = x;
	}
	
	public int getLevel(){
		return currentLevel;
	}
}
