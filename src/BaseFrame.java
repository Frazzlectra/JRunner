
public class BaseFrame extends EntFrame {
	private static final long serialVersionUID = 1L;
	//veriables
	final int INIT_SPEED = 10;
	int baseW;
	int baseH; 
	double speed;
	
	
	public BaseFrame(){
		baseW = 990;
		baseH = 150;	
		speed = INIT_SPEED;
		this.setSize(baseW, baseH);
	}
	//Move the platforms as well as the obstacles 
	public void Move(){
		if(this.getLocation().x + this.getWidth() >= 0){			
			this.setLocation((int) (this.getLocation().x - speed), this.getLocation().y);
		}
		else{
			this.setLocation(1920, this.getLocation().y);
		}
	}
	public void MoveSpeedUp(double speed){
		this.speed = speed;
	}
}
