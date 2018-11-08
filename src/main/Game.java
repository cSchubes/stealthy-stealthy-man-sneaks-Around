/* Ahmad Lubis, Alex Krach, Carson Schubert 
 *  Gallatin 3rd
 *  stealthy stealthy man sneaks Around
 */

package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Polygon;

import javax.swing.JFrame;

public class Game extends JFrame{
	private Handler[] levels;
	private HUD[] levelsHUD;
	public static MainMenu main;
	public static Winner winning;
	
	/**
	 * The constructor for the GameObject.
	 */
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
		winning = new Winner("asdfd", stealth, this);
		winning.setVisible(true);
		createLevelOne();
		createLevelTwo();
		createLevelThree();
		createLevelFive();
		
		setLayout(new BorderLayout());
		
		add(winning);
		add(main);
		setVisible(true);
	}
	
	/**
	 * a method that removes all objects from the game.
	 */
	public void removeAll(){
		this.removeAll();
		add(main);
		add(winning);
	}
	
	/** a method to remove the main menu.
	public void removeMain(){
		remove(main);
	}
	
	/**
	 * the method that adds the mai menu.
	 */
	public void addMain(){
		add(main);
	}
	
	/**
	 * the method that adds the winning screen.
	 */
	public void addWin(){
		add(winning);
	}
	
	/**
	 * the method that runs the game.
	 */
	public static void main (String[] args){
		Game g = new Game();
	}
	
	/**
	 * a method that creates level one.
	 */
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
		h.addObject(new Door(983, 50, Color.YELLOW, true, false, false));
		Guard one = new Guard(260, 63, 1, 250, 800, Guard.RIGHT, h, 1000);
		one.setVelX(1);
		Guard two = new Guard(800, 513, 2, 250, 750, Guard.LEFT, h, 1000);
		two.setVelX(-2);
		h.addObject(one);
		h.addObject(two);
		levels[0] = h;
		HUD hud = new HUD();
		levelsHUD[0] = hud;
	}
	
	/**
	 * a method that creates level two and its walls.
	 */
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
		h.addObject(new Key(800, 60, Color.YELLOW));
		h.addObject(new Door(900, 0, Color.YELLOW, false, false, false));
		Guard one = new Guard(113, 63, 1, 100, 750, Guard.RIGHT, h, 1000);
		one.setVelX(1);
		Guard two = new Guard(63, 263, 2, 63, 313, Guard.RIGHT, h, 300);
		Guard three = new Guard(300, 413, 2, 63, 313, Guard.LEFT, h, 300);
		Guard four = new Guard(913, 65, 2, 63, 653, Guard.DOWN, h, 1000);
		four.setVelY(2);
		two.setVelX(2);
		three.setVelX(-2);
		h.addObject(one);
		h.addObject(two);
		h.addObject(three);
		h.addObject(four);
		levels[1] = h;
		HUD hud = new HUD();
		levelsHUD[1] = hud;
	}
	
	/**
	 * a method that creates level three.
	 */
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
		h.addObject(new Player(465, 685, 2, h, Player.TOP_WIN));
		h.addObject(new Wall(0, 0, 0, p2));
		h.addObject(new Wall(0, 0, 0, p));
		h.addObject(new Wall(0, 0, 0, p3));
		h.addObject(new Key(316, 611, Color.MAGENTA));
		h.addObject(new Key(166, 161, new Color(0, 100, 0)));
		h.addObject(new Key(913, 213, Color.CYAN));
		h.addObject(new Key(716, 440, Color.YELLOW));
		h.addObject(new Door(450, 100, Color.MAGENTA, false, false, false));
		h.addObject(new Door(450, 50, new Color(0, 100, 0), false, false, false));
		Guard one = new Guard(470, 363, 2, 465, 700, Guard.RIGHT, h, 1000);
		Guard two = new Guard(690, 513, 2, 465, 700, Guard.LEFT, h, 1000);
		Guard three = new Guard(263, 213, 2, 260, 875, Guard.RIGHT, h, 1000);
		one.setVelX(2);
		two.setVelX(-2);
		three.setVelX(2);
		h.addObject(three);
		h.addObject(one);
		h.addObject(two);
		levels[2] = h;
		HUD hud = new HUD();
		levelsHUD[2] = hud;
	}
	
	/**
	 * a method that creates the fourth level (the original level four was cut).
	 */
	public void createLevelFive(){
		Handler handler = new Handler();
		int wallOneXpoints[] ={0,450,450,350,350,50,50,350,350,450,450,0} ;
		int wallOneYpoints[] = {0,0,350,350,50,50,700,700,400,400,750,750};
		int wallTwoXpoints[] = {550,1000,1000,550,550,650,650,950,950,650,650,550};
		int wallTwoYpoints[] = {0,0,750,750,400,400,700,700,50,50,350,350};
		int wallThreeXpoints[] = {100,175,175,225,225,300,300,225,225,175,175,100};
		int wallThreeYpoints[] = {100,100,300,300,100,100,650,650,450,450,650,650};
		int wallFourXpoints[] = {700,775,775,825,825,900,900,825,825,775,775,700};
		int wallFourYpoints[] = {100,100,300,300,100,100,650,650,450,450,650,650};
		
		Polygon p = new Polygon(wallOneXpoints, wallOneYpoints, 12);
		Polygon p2 = new Polygon(wallTwoXpoints, wallTwoYpoints, 12);
		Polygon p3 = new Polygon(wallThreeXpoints, wallThreeYpoints, 12);
		Polygon p4 = new Polygon(wallFourXpoints,wallFourYpoints,12);
		handler.addObject(new Player(485, 685, 2, handler, Player.TOP_WIN));
		handler.addObject(new Wall(0, 0, 0, p2));
		handler.addObject(new Wall(0, 0, 0, p));
		handler.addObject(new Wall(0, 0, 0, p3));
		handler.addObject(new Wall(0, 0, 0, p4));
		handler.addObject(new Key(195, 465, Color.CYAN));
		handler.addObject(new Door(175, 100, Color.CYAN, false, false, false));
		handler.addObject(new Key(195, 265, Color.MAGENTA));
		handler.addObject(new Door(775, 100, Color.MAGENTA, false, false, false));
		handler.addObject(new Key(795, 265, Color.ORANGE));
		handler.addObject(new Door(775, 639, Color.ORANGE, false, true, false));
		handler.addObject(new Key(795, 465, Color.YELLOW));
		handler.addObject(new Door(450, 0, Color.YELLOW, false, false, false));
		handler.addObject(new Door(500, 0, Color.YELLOW, false, false, true));
		Guard one = new Guard(63, 63, 2, 60, 300, Guard.RIGHT, handler, 125);
		Guard two = new Guard(263, 663, 2, 60, 300, Guard.LEFT, handler, 125);
		Guard three = new Guard(450, 363, 2, 313, 663, Guard.RIGHT, handler, 200);
		Guard four = new Guard(900, 63, 2, 665, 913, Guard.LEFT, handler, 125);
		Guard five = new Guard(668, 663, 2, 665, 913, Guard.RIGHT, handler, 125);
		four.setVelX(-2);
		five.setVelX(2);
		three.setVelX(2);
		one.setVelX(2);
		two.setVelX(-2);
		handler.addObject(one);
		handler.addObject(two);
		handler.addObject(three);
		handler.addObject(four);
		handler.addObject(five);
		levels[3] = handler;
		levelsHUD[3] = new HUD();
	}
	
	/**
	 * a method that gets the handler of the level
	 * @param x the level that you wish to access
	 * @return the handler of the level.
	 */
	public Handler getHandler(int x){
		return levels[x];
	}
	
	/**
	 * a method that gets the HUD of a level
	 * @param x - the level of the HUD
	 * @return the HUD of the level.
	 */
	public HUD getHUD(int x){
		return levelsHUD[x];
	}
}
