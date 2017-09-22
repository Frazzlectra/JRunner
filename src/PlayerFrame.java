import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PlayerFrame extends EntFrame implements KeyListener, ActionListener {
	private static final long serialVersionUID = 1L;
	
	public static boolean jumpbool; 
	public boolean playgame;
	
	//this class only
	private Point pt;
	//private int maxX = 1920;
	//private int min = 0; 
	private final int MAXY = 1080;
	private double jumpMultiplier = -12; 
	private int newy;
	//private double xvel = 0;
	private double yvel= 0;
	private double gravity = .2;
	private JPanel btnPanel;
	

	public PlayerFrame(){	
		//Initial size and frame setup
		this.setName("Player");
		this.setSize(400, 300);
		this.setLocation(20, 500);
		this.addKeyListener(this);
		
		//inside the JFrame
		this.setLayout(new GridLayout(2, 1));
		JButton play = new JButton("Play");
		JButton quit = new JButton("End");
		JButton pause = new JButton("Pause");
		play.addActionListener(this);
		quit.addActionListener(this);
		pause.addActionListener(this);
		btnPanel = new JPanel();
		btnPanel.add(play);
		btnPanel.add(pause);
		btnPanel.add(quit);
		this.add(btnPanel);
		
	}
	
	public boolean getPlaygame(){return playgame;}//returns play game bool so it can be retrieved in the main so it knows to play the game
	//Method for giving the player gravity when they jump
	public void playerGravity(){
		pt = this.getLocation();
		if (pt.y + 100 + this.getHeight() <= MAXY) {
			//this is probably wrong and should most likely be multiplied but I'm bad so fuck it
			yvel += gravity;
			yLocation();
		}
	}
	//Method for setting the players new location called for both gravity and Jumping
	public void yLocation(){
		newy = (int) (pt.y + yvel);
		this.setLocation(pt.x, newy);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		pt = this.getLocation();
		if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
			//if the player is on the bottom jump
			if (pt.y + 100 + this.getHeight() >= MAXY) {
				yvel = jumpMultiplier;
				yLocation();
			}
		}
	}

	//only here for listeners sake
	@Override
	public void keyTyped(KeyEvent e) {/* TODO Auto-generated method stub*/}

	@Override
	public void keyReleased(KeyEvent e) {/* TODO Auto-generated method stub*/}
	
	//For JFrame Buttons
	@Override
	public void actionPerformed(ActionEvent e){
		String action = e.getActionCommand();
		switch (action) {//this switch case is used to identify which button was pressed and react accordingly
		case "Play":
			playgame = true;
			break;
		case "End":
			System.exit(EXIT_ON_CLOSE);
			break;
		case "Pause":
			playgame = false;
			break;
		default:
			break;
		}
	}
}
