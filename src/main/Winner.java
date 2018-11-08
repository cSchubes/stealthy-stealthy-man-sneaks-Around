/* Ahmad Lubis, Alex Krach, Carson Schubert 
 *  Gallatin 3rd
 *  stealthy stealthy man sneaks Around
 */
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Winner extends JPanel implements ActionListener{
	
	private JLabel time;
	private JLabel winning;
	private JButton quit, retry;
	private Stealthy s;
	private Game game;
	private Image img;
	
	/**
	 * a constructor for the final screen that is shown when the game is completed
	 * @param time - the time it took to finish the game
	 * @param stealth - the Stealthy Object
	 * @param g - the Game Object
	 */
	public Winner(String time, Stealthy stealth, Game g){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    try {                
	    	img = ImageIO.read(classLoader.getResourceAsStream("piece of trashy.png"));
	    } 
	    catch (IOException ex)
	    {
	    }
		
		this.time = new JLabel("Final Time: " + time);
		this.time.setFont(new Font("Arial", Font.PLAIN, 45));
		this.time.setAlignmentX(CENTER_ALIGNMENT);
		this.time.setForeground(Color.RED);
		winning = new JLabel("You were the stealthiest of the stealthy men!");
		winning.setFont(new Font("Verdana", Font.PLAIN, 35));
	    winning.setForeground(Color.GREEN);
	    winning.setAlignmentX(CENTER_ALIGNMENT);
		quit = new JButton("Quit");
		quit.setFont(new Font("Verdana", Font.PLAIN, 25));
		quit.setAlignmentX(CENTER_ALIGNMENT);
		quit.addActionListener(this);
		retry = new JButton("Play Again");
		retry.setFont(new Font("Verdana", Font.PLAIN, 25));
		retry.setAlignmentX(CENTER_ALIGNMENT);
		retry.addActionListener(this);
		
		s = stealth;
		game = g;
		
		add(Box.createRigidArea(new Dimension(0, 275)));
		add(winning);
		add(Box.createRigidArea(new Dimension(0, 50)));
		add(this.time);
		add(Box.createRigidArea(new Dimension(0, 50)));
		add(retry);	
		add(Box.createRigidArea(new Dimension(0, 25)));
		add(quit);	
		
		setVisible(true);
	}
	
	/**
	 * a method that sets the time
	 * @param t - A string of the time
	 */
	public void setTime(String t){
		time.setText(t);
	}
	
	/**
	 * A method that resets the level of the Stealthy
	 */
	public void resetStealthy(){
		int l = s.getLevel();
		s = new Stealthy(game);
		s.setLevel(l);
	}
	
	/**
	 * a method that paints the frame
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1000, 750);
		img = img.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		g.drawImage(img, 400, 25, null);
	}

	/**
	 * a method that either resets the game or closes the screen
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(retry)){
			setVisible(false);
			s.setLevel(0);
			Thread t = new Thread(s);
			t.start();
			game.add(s);
		}
		if(e.getSource().equals(quit)){
			game.dispose();
		}
	}
}
