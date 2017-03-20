package asteroids.model;
import asteroids.util.ModelException;
import be.kuleuven.cs.som.annotate.*;

/**
 * a class that describes a bullet that flys away in the blue blue sky
 * @author sieben
 *
 */
public class Bullet {
	
	///CONSTRUCTOR///
	public Bullet(double x, double y , double xVelocity, double yVelocity, double radius,double max_velocity) throws ModelException{
		setBulletPosition(x,y);
		setBulletVelocity(xVelocity,yVelocity);
		setBulletRadius(radius);
		setBulletMaxVelocity(max_velocity);
	}
	
	public Bullet(double x, double y , double xVelocity, double yVelocity, double radius) throws ModelException{
		this(x,y,xVelocity,yVelocity,radius,getDefaultMaxVelocity());
	}

	
	///BASIC PROPERTIES///
	private double[] position;
	private double[] velocity;
	private double radius;
	private double max_velocity;
	
	///DEFAULTS///
	private final static double SPEED_OF_LIGHT = 300000;
	private final static double LOWER_BULLET_RADIUS = 1;
	private final static double BULLET_DENSITY = 7.8E12;
	
	public static double getDefaultMaxVelocity(){
		return SPEED_OF_LIGHT;
	}
	
	
	
	///GETTERS///
	
	public double[] getBulletPosition(){
		return this.position;
	}
	
	public double[] getBulletVelocity(){
		return this.velocity;
	}
	
	public double getBulletRadius(){
		return this.radius;
	}
	///Need to be done when we implement the ship-association///
	public double getBulletOrientation(){
		null
	}
	
	public double getBulletMaxVelocity(){
		return this.max_velocity;
	}
	public double getBulletDensity(){
		return BULLET_DENSITY;
	}
	public double getBulletMass(){
		return (4/3*Math.PI*Math.pow(this.getBulletRadius(),3)*getBulletDensity());
	}
	///SETTERS///
	
	public void setBulletPosition(double x, double y){
		null
	}
	
	public void setBulletVelocity(double xVelocity, double yVelocity){
		if (!Ship.isValidArray(xVelocity, yVelocity)) {
			if (Double.isNaN(xVelocity))
				xVelocity = 0;
			if (Double.isNaN(yVelocity))
				yVelocity = 0;
		}

		if (Ship.getTotalVelocity(xVelocity, yVelocity) > this.getBulletMaxVelocity()) {
			double orientation = this.getBulletOrientation();
			double xVel = Math.cos(orientation) * this.getBulletMaxVelocity();
			double yVel = Math.sin(orientation) * this.getBulletMaxVelocity();

			double[] velocity_array = { xVel, yVel };
			this.velocity = velocity_array;
		}

		else {
			double[] velocity_array = { xVelocity, yVelocity };
			this.velocity = velocity_array;
		}
	}
	
	
	public void setBulletRadius(double radius) throws ModelException {
		if (radius < LOWER_BULLET_RADIUS)
			throw new ModelException(
					"The radius is lower than the underbound of " + LOWER_BULLET_RADIUS + " km, please try again.");
		this.radius = radius;
	}
	
	
	
	public void setBulletMaxVelocity(double limit) {
		if ((limit < SPEED_OF_LIGHT) && (limit > 0))
			this.max_velocity = limit;
		else
			this.max_velocity = getDefaultMaxVelocity();
	}
}

