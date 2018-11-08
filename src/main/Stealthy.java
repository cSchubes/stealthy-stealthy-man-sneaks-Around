/* Ahmad Lubis, Alex Krach, Carson Schubert 
 *  Gallatin 3rd
 *  stealthy stealthy man sneaks Around
 */
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
	
	/**
	 * This constructs a new Stealthy
	 * @param frame - The game that this is a part of
	 */
	public Stealthy(Game frame){
		handler = new Handler();
		addKeyListener(this);
		hud = new HUD();
		gameFrame = frame;
		running = true;
		lost = false;
		win = false;
	}

	/**
	 * This runs the game.
	 */
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 80.0;
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
					currentLevel = 0;
					gameFrame.main.setVisible(true);
					gameFrame.main.resetStealthy();
					resetAll();
					resetHUD();
					handler = gameFrame.getHandler(currentLevel);
					hud = gameFrame.getHUD(currentLevel);
					resetAll();
					resetHUD();
					running = false;
				}
				else{
					resetAll();
				}
			}
			
			if(win){
				currentLevel++;
				if(currentLevel == 4){
					gameFrame.dispose();
					gameFrame = new Game();
					gameFrame.winning.setTime(hud.getMinute() + ":" + hud.getTen() + hud.getSecond());
					gameFrame.winning.setVisible(true);
					gameFrame.main.setVisible(false);
					gameFrame.addWin();
					running = false;
				}
				else{
					handler = gameFrame.getHandler(currentLevel);
					int[] clock = {hud.getMinute(), hud.getTen(), hud.getSecond()};
					hud = gameFrame.getHUD(currentLevel);
					hud.setClock(clock);
					win = false;
				}
			}
		}
	}
	
	/**
	 * This method updates the information of every object in this game.
	 */
	public void tick(){
		handler.tick();
		hud.tick();
	}
	
	/**
	 * This method repaints the frame and updates the game graphically.
	 */
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
	
	/**
	 * This method reads in what key is pressed and updates the speed of the player accordingly.
	 * @param e - The key that is pressed.
	 */
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

	/**
	 * This method stops the player from moving when the key is released.
	 * @param e - the key that is released.
	 */
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

	/**
	 * This is an inherited method.
	 */
	public void keyTyped(KeyEvent e) {
		
	}
	
	/**
	 * This resets the information in the handler.
	 */
	public void resetAll(){
		handler.reset();
		//hud.reset();
	}
	
	/**
	 * This method resets the information in the HUD.
	 */
	public void resetHUD(){
		hud.reset();
	}
	
	/**
	 * This method determines the level that the game is on and draws the map accordingly.
	 * @param x - an int that sets which level you are on.
	 */
	public void setLevel(int x){
		handler = gameFrame.getHandler(x);
		hud = gameFrame.getHUD(x);
		currentLevel = x;
	}
	
	/**
	 * a method to return the level number.
	 * @return - the level number.
	 */
	public int getLevel(){
		return currentLevel;
	}
}
