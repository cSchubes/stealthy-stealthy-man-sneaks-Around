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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainMenu extends JPanel implements ActionListener
{
	private JButton startButton;
	private JButton instructionButton;
	private JLabel title;
	private Game f;
	private Stealthy s;
	private Image image;
	
	public MainMenu(Stealthy first, Game f)
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    try {                
	    	image = ImageIO.read(classLoader.getResourceAsStream("background.jpg"));
	    } 
	    catch (IOException ex)
	    {
	    }
		
	    title = new JLabel("stealthy stealthy man sneaks Around");
	    title.setFont(new Font("Verdana", Font.PLAIN, 35));
	    title.setForeground(Color.GREEN);
	    title.setAlignmentX(CENTER_ALIGNMENT);
	    
		startButton = new JButton("Start Game");
		instructionButton = new JButton("Instructions");
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(Box.createRigidArea(new Dimension(470, 60)));
		add(title);
		add(Box.createRigidArea(new Dimension(470, 200)));
		
		startButton.addActionListener(this);
		startButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		startButton.setFocusable(false);
		startButton.setAlignmentX(CENTER_ALIGNMENT);
		instructionButton.addActionListener(this);
		instructionButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		instructionButton.setFocusable(false);
		instructionButton.setAlignmentX(CENTER_ALIGNMENT);

		this.f = f;
		this.s = first;
		
		add(startButton);
		add(Box.createRigidArea(new Dimension(0, 50)));
		add(instructionButton);
				
		setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		JButton pressed = (JButton)event.getSource();
		String action = pressed.getText();
		
		if(action.equals("Start Game"))
		{
			setVisible(false);
			s.setLevel(0);
			Thread t = new Thread(s);
			t.start();
			f.add(s);
		}
		else if(action.equals("Instructions"))
		{
			JOptionPane.showMessageDialog(null, "Hey these are the instructions to the game but not really it's just place holder text I'm sorry I lied to you");
		}
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
	
	public void resetStealthy(){
		int l = s.getLevel();
		s = new Stealthy(f);
		s.setLevel(l);
	}
}