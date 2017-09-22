/**
 * 
 */

/**
 * @author bhbro
 *
 */
public class ObsticleFrame extends BaseFrame {
	private static final long serialVersionUID = -1879542643793190828L;

	public ObsticleFrame(){
		this.setName("Obsticle");
		this.setSize(200, 200);
		this.setLocation( 1920 - 200, 1080 - 290 );
	}
	public void ObsticleRemoval(){
		
		//ToDo: need to set up somewhere else to impliment
		if (this.getLocation().x <= 0) {
			this.dispose();		
		}
	}

}
