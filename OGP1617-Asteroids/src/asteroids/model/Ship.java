package asteroids.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import asteroids.util.ModelException;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class that describes ships and their properties. A ship has a position and
 * a velocity, both are described in a cartesian xy-field. It also has an
 * orientation and a radius which defines its circular shape. The maximum total
 * velocity is the last property.
 * 
 * @invar The orientation of the ship will be a valid orientation. This means
 *        that the orientation will be a value between 0 and 2*PI, 0 included.
 *        |(isValidRadian(orientation))
 *
 * @version 28th of march
 * @authors Sieben Bocklandt and Ruben Broekx
 */
public class Ship extends Entity {

	/// CONSTRUCTORS///
	
	//@note the first constructor will never be used in facade, but we made it to increase 
    // the adaptability. In the future, it's possible that each ship has a different maximum velocity
	// and then this constructor will be used

	/**
	 * Initializes a new ship with given parameters.
	 * 
	 * @param x
	 *            The horizontal position of the ship in kilometers.
	 * @param y
	 *            The vertical position of the ship in kilometers.
	 * @param xVelocity
	 *            The horizontal starting velocity of the ship in kilometers per
	 *            second.
	 * @param yVelocity
	 *            The vertical starting velocity of the ship in kilometers per
	 *            second.
	 * @param radius
	 *            The radius that defines the circular shape of the ship in
	 *            kilometers.
	 * @param orientation
	 *            The orientation of the ship in radians.
	 * @param maxVelocity
	 *            The maximum total velocity of the ship in kilometers per
	 *            second.
	 * 
	 * @post The x-coordinate is a number (type double). | !Double.isNaN(x)
	 * @post The y-coordinate is a number (type double). | !Double.isNaN(y)
	 * @post The position of the ship is equal to the given x and y. They will
	 *       be used as an array {x,y}. |new.getShipPosition == {x,y}
	 * @post The x-coordinate of the starting velocity is a number (type
	 *       double). | !Double.isNaN(xVelocity)
	 * @post The y-coordinate of the starting velocity is a number (type
	 *       double). | !Double.isNaN(yVelocity)
	 * @post The total velocity of the ship is lower than it's maximum velocity.
	 *       If the velocity exceeds this limit, xVelocity and yVelocity will be
	 *       set on the projection of the maximum velocity on the x- and y-axis.
	 *       |if (Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2))<
	 *       getShipMaxVelocity) | new.getShipVelocity = {xVelocity, yVelocity}
	 *       |else | new.getShipVelocity =
	 *       {Math.cos(orientation)*getShipMaxVelocity, |
	 *       Math.sin(orientation)*getShipMaxVelocity}
	 * @post The radius of the ship will be a positive value and greater than a
	 *       given lower radius. |If (radius > LOWER_RADIUS) |
	 *       new.getShipRadius() = radius
	 * @pre The orientation of the ship will be a value between 0 and 2*PI, 0
	 *      included. If the ship is facing right, it's orientation is 0 radians
	 *      and if it's facing up, it's orientation will be PI/2.
	 *      |isValidRadian(orientation)
	 * @post The orientation of the ship is equal to the given orientation.
	 *       |new.getShipOrientation() == orientation
	 * @post The maximum velocity will be equal to the speed of light, unless
	 *       the given maxVelocity is a positive number and less than the speed
	 *       of light. Then the maximum velocity will be equal to this
	 *       maxVelocity. |if ((maxVelocity < SPEED_OF_LIGHT) && (maxVelocity
	 *       >0)) | new.getShipMaxVelocity == maxVelocity |else |
	 *       new.getShipMaxVelocity == SPEED_OF_LIGHT
	 *
	 * @throws ModelException
	 *             If the given radius is lower than the lower radius. |(radius
	 *             < LOWER_RADIUS)
	 * @throws ModelException
	 *             If x or y is not a number. |(!isValidArray(x,y))
	 */
	//ALL VALUES//
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass, 
			double maxVelocity,double density, boolean thrusterActivity,  double thrusterForce) throws ModelException {
		super(x,y,xVelocity,yVelocity,radius,orientation,mass,maxVelocity,density);
		setThrusterActive(thrusterActivity);
		setShipThrusterForce(thrusterForce);
		addMultipleBulletsToShip(makeFifteenBullets());
	}

	
	/**
	 * Initializes a new ship with given values and a maximum total velocity
	 * equal to the speed of light.
	 * 
	 * @effect The ship is initialized with the given values and the default
	 *         maximum total velocity. |this(x, y, xVelocity, yVelocity, radius,
	 *         orientation, getDefaultMaxVelocity())
	 */
	//NORMAL CONSTRUCTOR//
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation,double mass)
			throws ModelException {
		this(x, y, xVelocity, yVelocity, radius, orientation, mass,Entity.getDefaultMaxVelocity(),
				Entity.getDefaultShipDensity(),getDefaultThrusterActivity(),getDefaultThrusterForce());
	}

	/**
	 * Initializes a new ship with it's default values.
	 * 
	 * @effect The ship is initialized with the default values.
	 *         |this(getDefaultPosition()[0], getDefaultPosition()[1],
	 *         getDefaultVelocity()[0], | getDefaultVelocity()[1],
	 *         getDefaultRadius(), getDefaultOrientation(),
	 *         getDefaultMaxVelocity());
	 */
	//ALL DEFAULT//
	public Ship() throws ModelException {
		this(getDefaultPosition()[0], getDefaultPosition()[1], getDefaultVelocity()[0], getDefaultVelocity()[1],
				getDefaultRadius(), Entity.getDefaultOrientation(),getDefaultMass());
	}

	
	/// BASIC PROPERTIES ///
	private boolean thruster_activity;
	private double thruster_force;
	
	/// DEFAULTS ///
	/**
	 * Return the default radius of the ship.
	 * 
	 * @return The default radius is a non-negative value. | result >= 0
	 */
	@Immutable
	public static double getDefaultRadius() {
		return 1;
	}


	public static double getDefaultMass() {
		return 4/3*Math.PI * Math.pow(getDefaultRadius(),3)*getDefaultShipDensity();
	}
	
	
	public static boolean getDefaultThrusterActivity(){;
		return false;
	}
	
	public static double getDefaultThrusterForce(){
		return 1.1E21;
	}
	
	private double initialFiringVelocity = 250;
	
	/// GETTERS ///

		
	public boolean isThrusterActive(){
		return this.thruster_activity;
	}
	
	public double getShipAcceleration(){
		return (this.thruster_force/this.getEntityMass());
	}
	
	public double getBulletsWeight(){
		double weight = 0;
		for (Bullet bullet: this.getShipBullets())
			weight += bullet.getEntityMass();
		return weight;
	}
	
	public Set<Bullet> getShipBullets(){
		Set<Bullet> result = new HashSet<Bullet>();
		result.addAll(this.bullets.values());
		return result;
				}
	
	public int getNbBulletsOnShip(){
		return this.getShipBullets().size();
	}
	

	
	/// SETTERS ///
	
	public void setThrusterActive(boolean thrusterActivity){
		this.thruster_activity = thrusterActivity;
	
	}
	public void setShipThrusterForce(double thrusterForce){
		if (thrusterForce <0)
			thrusterForce = getDefaultThrusterForce();
		this.thruster_force = thrusterForce;
	}
	
	
	///CHECKERS///
	
	public boolean isValidShipPosition(double x, double y){
		if ((this.getEntityWorld() != null)){
			return entityFitsInWorld(this,this.getEntityWorld());}
		else
			return true;
	}
		
	// --> ZEKER NOG EENS GOED NAKIJKEN (OVERLAP,TERMINATE,...)!!!!! <-- //
	public boolean canHaveAsBullet(Bullet bullet){
		 return ((!this.hasAsBullet(bullet)) &&(bullet.getBulletShip()==null) && this.bulletFullyInShip(bullet));
				 
	}
	
	public boolean bulletFullyInShip(Bullet bullet){
		double x_between = Math.abs(bullet.getEntityPosition()[0]-this.getEntityPosition()[0]);
		double y_between = Math.abs(bullet.getEntityPosition()[1]-this.getEntityPosition()[1]);
		double bullet_radius = bullet.getEntityRadius();
		double ship_radius = this.getEntityRadius();
		double distance_between = getEuclidianDistance(x_between, y_between);
		return ((distance_between + bullet_radius) < ship_radius);
		
		
	}
	
	/**
	 * Update the direction of the ship by adding an angle in radians to its
	 * current direction. The angle may be negative.
	 * 
	 * @param angle
	 *            The given angle which will determine the difference in
	 *            orientation.
	 * 
	 * @pre The new orientation will be between 0 and 2*PI,0 included
	 *      |isVallidRadian(this.getShipOrientation() + angle)
	 * 
	 * @effect The new orientation of the ship is equal to the sum of the
	 *         orientation and the given angle.
	 *         |this.setShipOrientation(this.getShipOrientation() + angle)
	 */
	public void turn(double angle) {
		assert isValidRadian(this.getEntityOrientation() + angle);
		assert this instanceof Ship;
		this.setEntityOrientation(this.getEntityOrientation() + angle);
	}

	
	public void thrustOn(){
		setThrusterActive(true);
	}
	
	public void thrustOff(){
		setThrusterActive(false);
	}
	

	/// HAS ///
	
	public boolean hasAsBullet(Bullet bullet){
		return this.bullets.containsValue(bullet);
	}       
	
	
	///ADDERS///
		 
	public void addOneBulletToShip(Bullet bullet) throws ModelException{
		if (this.canHaveAsBullet(bullet)){
			this.bullets.put(bullet.hashCode(), bullet);
			bullet.setBulletLoaded(this);
			bullet.setEntityOrientation(this.getEntityOrientation());}
		else
			throw new ModelException("this bullet can not be loaded on this ship");
	}


	public void addMultipleBulletsToShip(Collection<Bullet> bullets) throws ModelException{
		for (Bullet bullet: bullets)
			addOneBulletToShip(bullet);
	}
		 
	
	///REMOVERS///
		 
	public void removeBulletFromShip(Bullet bullet) throws ModelException{
		if (!this.hasAsBullet(bullet)){
			throw new ModelException("this ship doesn't have this bullet");}
		else{
			this.bullets.remove(bullet.hashCode());
			bullet.setBulletNotLoaded();	
		}
	}

	public void fireBullet() throws ModelException{
		if (! bullets.isEmpty()) {
			Map.Entry<Integer,Bullet> entry=bullets.entrySet().iterator().next();
			Bullet bullet = entry.getValue();

			bullet.setBulletSourceShip(this);
			this.getEntityWorld().addEntityToWorld(bullet);
			this.removeBulletFromShip(bullet);

			double[] positionShip = this.getEntityPosition();
			double orientation = this.getEntityOrientation();
			double radiusShip = this.getEntityRadius();
			double radiusBullet = bullet.getEntityRadius();

			double[] positionBullet = {positionShip[0] + Math.cos(orientation)*(radiusShip + radiusBullet+1), 
					positionShip[1] + Math.sin(orientation)*(radiusShip + radiusBullet + 1)};
			
			bullet.setEntityPosition(positionBullet[0], positionBullet[1]);
			bullet.setEntityOrientation(orientation);
			bullet.setEntityVelocity(initialFiringVelocity*Math.cos(orientation), initialFiringVelocity*Math.sin(orientation));

			if (!entityFitsInWorld(bullet, this.getEntityWorld()))
				bullet.Terminate();
		}
	}
		 
	
	/// HELP FUNCTIONS///
		 public Set<Bullet> makeFifteenBullets() throws ModelException{
			Set<Bullet> result = new HashSet<>();
			double x_position = this.getEntityPosition()[0];
			double y_position = this.getEntityPosition()[1];
			for (int i=0; i <15; i++){
				 Bullet bullet = new Bullet(x_position,y_position,0,0,0.5*this.getEntityRadius());
				 result.add(bullet);}
			return result; 
		 }
	
		 
		///CONNECTIONS WITH OTHER CLASSES///
	
	private final Map<Integer, Bullet> bullets = new HashMap<Integer, Bullet>();
	
	
}
