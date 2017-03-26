package asteroids.model;
import asteroids.model.Entity.State;
import asteroids.util.ModelException;
import be.kuleuven.cs.som.annotate.*;

/**
 * a class that describes a bullet that flies away in the blue blue sky.
 * 
 * @version 21_Mar_19u
 * @authors Sieben Bocklandt and Ruben Broekx
 */
public class Bullet extends Entity {
	
	///CONSTRUCTOR///
	public Bullet(double x, double y , double xVelocity, double yVelocity, double radius,double orientation
			,double mass,double maxVelocity,double density) throws ModelException{
		
		super(x,y,xVelocity,yVelocity,radius,orientation,mass,maxVelocity,density);
		}
	
	public Bullet(double x, double y , double xVelocity, double yVelocity, double radius) throws ModelException{
		this(x,y,xVelocity,yVelocity,radius,Entity.getDefaultOrientation(),getDefaultBulletMass(),
				Entity.getDefaultMaxVelocity(),Entity.getDefaultBulletDensity());
	}
	
	public Bullet() throws ModelException{
		this(getDefaultPosition()[0],getDefaultPosition()[1],getDefaultVelocity()[0],getDefaultVelocity()[1],getDefaultRadius());
	}

	
	///DEFAULTS///
	public static double getDefaultRadius(){
		return 2;
	}
	
	public static double getDefaultBulletMass(){
		return 4/3*Math.PI*Math.pow(getDefaultRadius(),3)*getDefaultBulletDensity();
	}
	
	
	///GETTERS///
	
	public double getBulletMass(){
		return (4/3*Math.PI*Math.pow(this.getEntityRadius(),3)*getEntityDensity());
	}
	
	public Ship getBulletShip(){
		return this.ship;
	}
	
	///CHECKERS///
	public boolean hasWorldAndShip(){
		return ((this.isBulletLoaded()) && (this.isEntityInWorld()));
	}
	
	
	//GOED NAKIJKEN//
	public boolean canHaveAsShip(Ship ship){
		return ((ship.canHaveAsBullet(this)) || (this.isEntityInWorld()));
	}
	
	///TERMINATION AND STATES///
	
	
		
		private State state = State.NOTLOADED;
	
		private static enum State {
			LOADED,NOTLOADED;	
		}
		
		public State getBulletLoadedState(){
			return this.state;
		}
		public boolean isBulletLoaded(){
			return (this.getBulletLoadedState() == State.LOADED);
		}
		
		public boolean hasBulletProperState(){
			return isBulletLoaded() ^(!isBulletLoaded());
		}
		
		public void setBulletLoadedState(State state) throws ModelException{
			if (state == null)
				throw new ModelException("this is not a valid state");
			else
				this.state = state;
		}
		
		public void setBulletLoaded() throws ModelException{
			assert (!this.isEntityTerminated());
			this.setBulletLoadedState(State.LOADED);			
		}
		
		public void setBulletNotLoaded() throws ModelException{
			assert (!this.isEntityTerminated());
			this.setBulletLoadedState(State.NOTLOADED);
		}
	
		
		///CONNECTIONS WITH OTHER CLASSES///
		private final Ship ship = null;
	
}

