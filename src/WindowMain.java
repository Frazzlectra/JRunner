import java.awt.Point;
import java.util.Random;


public class WindowMain {
	static int obsticleCallCounter = 0;
	static boolean obsticleTrue = false;
	static boolean whileTrue = true;
	//for increasing the speed of the platforms and obstacle
	static double speed;
	static double speedIncrease = .09;
	public static void main(String[] args) throws InterruptedException {
		playGame();//start game
	}
	
	public static void playGame() throws InterruptedException {
		//could set to screen size using Dimension screenSize = Toolkit().getScreenSize(); and int height = screensize.height
				//

				//BaseFrames
				BaseFrame base1 = new BaseFrame();
				base1.setLocation(0, 985);
				//base 2
				BaseFrame base2 = new BaseFrame();
				base2.setLocation(960, 985);
				//base 3 
				BaseFrame base3 = new BaseFrame();
				base3.setLocation(1920, 985);			
				//Player Window
				PlayerFrame player = new PlayerFrame();
				//obstacle frame to be generated in the while loop
				ObsticleFrame obsticle = null;

				while(whileTrue){
					player.requestFocus(); //If statement doesn't work properly without this
					if (player.getPlaygame()) {
						//Generating obstacle
						if (!obsticleTrue) {
							obsticle = ObsticleGeneration(obsticle);
							if (obsticleTrue) {								
								BaseSpeedUP(base1, base2, base3, obsticle);
							}
						}	
						else if (obsticleTrue && obsticle != null) {
							if (obsticle.getLocation().x + obsticle.getWidth() == 0) {
								obsticle.ObsticleRemoval();
								obsticleTrue = false;
							}
							obsticle.Move();
							playerCollision(player, obsticle);
						}
						//
						player.requestFocus();
						player.setAlwaysOnTop(true);
						player.playerGravity();
						base1.Move();
						base2.Move();
						base3.Move();
						Thread.sleep(10);
					}
				}
	}
	//Sets the Obstacles to appear at regular but slightly random intervals (If I ever implement the speeding up over time effect this will probably have to be adjusted to suit that)
	public static ObsticleFrame ObsticleGeneration(ObsticleFrame obsticle){
		int obsticleRnd;
		int obsticalCallNum = 10;//for creating intervals between when an obstacle is generated 
		Random rnd = new Random();
		//
		if (obsticleCallCounter == obsticalCallNum) {//if CallNum is equal to the callCounter then try to generate if not add 1 to the callCounter till they are equal
			obsticleRnd = rnd.nextInt(3);					
			if (obsticleRnd >= 2) {
				//chance to generate an obstacle... 
				obsticle = new ObsticleFrame();	
				obsticleTrue = true;
			}
			obsticleCallCounter = 0;
		}
		else{
			obsticleCallCounter++; 
			obsticle = null;
		}
		return obsticle;
	}
	
	//This Method handles player collisions with the Obstacles 
	public static void playerCollision(PlayerFrame player, ObsticleFrame obsticle){
		Point obPt = obsticle.getLocation();
		Point plyPt = player.getLocation();
		//set whileTrue to falls to end the while loop
		if(obPt.x <= plyPt.x + player.getWidth() - 10 && obPt.y <= plyPt.y + player.getHeight()){
			whileTrue = false;
		}
	}
	
	public static void BaseSpeedUP(BaseFrame base1, BaseFrame base2, BaseFrame base3, ObsticleFrame obsticle){
		//Not really a good system but I'll ask john later -- Increases the speed of each base object 
		speed = base1.speed + base1.speed * speedIncrease;
		base1.MoveSpeedUp(speed);
		base2.MoveSpeedUp(speed);
		base3.MoveSpeedUp(speed);
		obsticle.MoveSpeedUp(speed);
	}
}