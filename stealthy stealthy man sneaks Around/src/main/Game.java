package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Polygon;

import javax.swing.JFrame;

public class Game extends JFrame{
	private Handler[] levels;
	private HUD[] levelsHUD;
	public static MainMenu main;
	
	public Game(){
		setSize(1000, 775);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("stealthy stealthy man sneaks Around");
		
		Stealthy stealth = new Stealthy(this);
		stealth.setFocusable(true);
		
		this.setResizable(false);
		
		levels = new Handler[6];
		levelsHUD = new HUD[6];
		
		main = new MainMenu(stealth, this);
		createLevelOne();
		createLevelTwo();
		createLevelThree();
		
		setLayout(new BorderLayout());
		
		add(main);
		setVisible(true);
	}
	
	public void removeAll(){
		this.removeAll();
		add(main);
	}
	
	public static void main (String[] args){
		Game g = new Game();
	}
	
	public void createLevelOne(){
		Handler h = new Handler();
		int wallOneXpoints[] = {0, 1000, 1000, 200, 200, 50, 50, 200, 200, 50, 50, 0};
		int wallOneYpoints[] = {0, 0, 50, 50, 400, 400, 450, 450, 650, 650, 750, 750};
		int wallTwoXpoints[] = {250, 850, 850, 550, 550, 600, 600, 500, 500, 250};
		int wallTwoYpoints[] = {100, 100, 500, 500, 300, 300, 250, 250, 500, 500};
		int wallThreeXpoints[] = {100, 100, 250, 250, 650, 650, 800, 800, 700, 700, 900, 900, 1000, 1000};
		int wallThreeYpoints[] = {750, 700, 700, 550, 550, 700, 700, 650, 650, 550, 550, 100, 100, 750};
		Polygon p = new Polygon(wallOneXpoints, wallOneYpoints, 12);
		Polygon p2 = new Polygon(wallTwoXpoints, wallTwoYpoints, 10);
		Polygon p3 = new Polygon(wallThreeXpoints, wallThreeYpoints, 14);
		h.addObject(new Player(65, 675, 2, h, Player.RIGHT_WIN));
		h.addObject(new Wall(0, 0, 0, p2));
		h.addObject(new Wall(0, 0, 0, p));
		h.addObject(new Wall(0, 0, 0, p3));
		h.addObject(new Key(570, 260, Color.YELLOW));
		h.addObject(new Door(983, 50, Color.YELLOW, true));
		Guard one = new Guard(260, 63, 1, 250, 800, Guard.RIGHT, h);
		one.setVelX(1);
		Guard two = new Guard(800, 513, 2, 250, 750, Guard.LEFT, h);
		two.setVelX(-2);
		h.addObject(one);
		h.addObject(two);
		levels[0] = h;
		HUD hud = new HUD();
		levelsHUD[0] = hud;
	}
	
	public void createLevelTwo(){
		Handler h = new Handler();
		int wallOneXpoints[] = {0,900,900,0} ;
		int wallOneYpoints[] = {0,0,50,50};
		int wallTwoXpoints[] = {950,1000,1000,0,0,50,50,950,950};
		int wallTwoYpoints[] = {0,0,1000,1000,100,100,950,950,0};
		int wallThreeXpoints[] = {100,300,300,100};
		int wallThreeYpoints[] = {300,300,400,400};
		int wallFourXpoints[] = {800,900,900,750,750,800};
		int wallFourYpoints[] = {300,300,650,650,450,450};
		int wallFiveXpoints[] = {100,900,900,750,750,700,700,450,450,400,400,100,100,350,350,100,100};
		int wallFiveYpoints[] = {100,100,250,250,400,400,650,650,550,550,650,650,450,450,250,250,100};
		int wallSixXpoints[] = {0,1000,1000,0};
		int wallSixYpoints[] = {700,700,750,750};
		
		Polygon p = new Polygon(wallOneXpoints, wallOneYpoints, 4);
		Polygon p2 = new Polygon(wallTwoXpoints, wallTwoYpoints, 9);
		Polygon p3 = new Polygon(wallThreeXpoints, wallThreeYpoints, 4);
		Polygon p4 = new Polygon(wallFourXpoints,wallFourYpoints,6);
		Polygon p5 = new Polygon(wallFiveXpoints,wallFiveYpoints,17);
		Polygon p6 = new Polygon(wallSixXpoints,wallSixYpoints,4);
		h.addObject(new Player(15, 65, 2, h, Player.TOP_WIN));
		h.addObject(new Wall(0, 0, 0, p2));
		h.addObject(new Wall(0, 0, 0, p));
		h.addObject(new Wall(0, 0, 0, p3));
		h.addObject(new Wall(0, 0, 0, p4));
		h.addObject(new Wall(0, 0, 0, p5));
		h.addObject(new Wall(0, 0, 0, p6));
		h.addObject(new Key(650, 60, Color.YELLOW));
		Guard one = new Guard(250, 63, 1, 240, 600, Guard.RIGHT, h);
		one.setVelX(1);
		Guard two = new Guard(63, 115, 2, 75, 650, Guard.DOWN, h);
		two.setVelY(2);
		h.addObject(one);
		h.addObject(two);
		levels[1] = h;
		HUD hud = new HUD();
		levelsHUD[1] = hud;
	}
	
	public void createLevelThree(){
		Handler h = new Handler();
		int wallOneXpoints[] = {0,450,450,300,300,150,150,250,250,450,450,300,300,450,450,0} ;
		int wallOneYpoints[] = {0,0,200,200,150,150,200,200,250,250,600,600,650,650,750,750};
		int wallTwoXpoints[] = {500,1000,1000,500,500,750,750,500,500,950,950,500};
		int wallTwoYpoints[] = {0,0,750,750,550,550,350,350,250,250,200,200};
		int wallThreeXpoints[] = {500,700,700,500};
		int wallThreeYpoints[] = {400,400,500,500};
		Polygon p = new Polygon(wallOneXpoints, wallOneYpoints, 16);
		Polygon p2 = new Polygon(wallTwoXpoints, wallTwoYpoints, 12);
		Polygon p3 = new Polygon(wallThreeXpoints, wallThreeYpoints, 4);
		h.addObject(new Player(465, 725, 2, h, 0));
		h.addObject(new Wall(0, 0, 0, p2));
		h.addObject(new Wall(0, 0, 0, p));
		h.addObject(new Wall(0, 0, 0, p3));
		//h.addObject(new Key(250, 350, Color.YELLOW));
		Guard one = new Guard(250, 63, 1, 240, 500, Guard.RIGHT, h);
		one.setVelX(1);
		levels[2] = h;
		HUD hud = new HUD();
		levelsHUD[2] = hud;
	}
	
	public Handler getHandler(int x){
		return levels[x];
	}
	
	public HUD getHUD(int x){
		return levelsHUD[x];
	}
}
