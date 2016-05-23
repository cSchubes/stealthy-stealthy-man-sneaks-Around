package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class HUD {
	
	private double lastTime, currentTime, elapsed;
	private int minute, ten, one;
	
	public HUD(){
		minute = 0;
		ten = 0;
		one = -1;
		elapsed = 0;
		lastTime = 0;
		currentTime = 0;
	}
	
	public void tick(){
		currentTime = System.nanoTime();
		elapsed += (currentTime - lastTime);
		if(elapsed >= 1000000000){
			one += 1;
			if(one == 10){
				ten+=1;
				one = 0;
				if(ten == 6){
					minute += 1;
					ten = 0;
				}
			}
			elapsed = 0;
		}
		lastTime = System.nanoTime();
	}
	
	public void render(Graphics2D g2){
		g2.setColor(Color.CYAN);
		g2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		g2.drawString(minute + ":" + ten + one, 200, 25);
	}
	
	public void reset(){
		minute = ten = one = 0;
		one = -1;
		lastTime = currentTime = elapsed = 0;
	}
	
	public void setClock(int[] clock){
		minute = clock[0];
		ten = clock[1];
		one = clock[2];
	}
	
	public int getMinute(){
		return minute;
	}
	
	public int getTen(){
		return ten;
	}
	
	public int getSecond(){
		return one;
	}
}
