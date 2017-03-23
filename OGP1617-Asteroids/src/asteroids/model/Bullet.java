package asteroids.model;
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
	
	
	///CONNECTIONS WITH OTHER CLASSES///
		private final Ship ship = new Ship();
	
}

