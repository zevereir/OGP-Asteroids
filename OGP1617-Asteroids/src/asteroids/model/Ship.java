package asteroids.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
			double maxVelocity,double density, boolean thrusterActivity,  double thrusterForce)  {
		super(x,y,xVelocity,yVelocity,radius,orientation,mass,maxVelocity,density);
		setThrusterActive(thrusterActivity);
		setShipThrusterForce(thrusterForce);
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
		 {
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
	public Ship() {
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
		return (4.0/3.0)*Math.PI * Math.pow(getDefaultRadius(),3)*getDefaultShipDensity();
	}
	
	
	public static boolean getDefaultThrusterActivity(){;
		return false;
	}
	
	public static double getDefaultThrusterForce(){
		return 1.1E21;
	}
	
	private double initialFiringVelocity = 250;
	
	private final static double LOWER_SHIP_RADIUS = 10;
	
	public double minimumShipMass(){
		return (4.0/3.0)*Math.PI * Math.pow(this.getEntityRadius(),3)*this.getEntityDensity();
	}
	
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
	
	public double getEntityMass() {
		double bullets_weight = ((Ship)this).getBulletsWeight();
		return this.mass + bullets_weight;	
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
	
	public void setEntityDensity(double density){

		if (isValidDensity(density))
			this.density = density;
		
		else
			this.density = getDefaultShipDensity();
	}
	
	public void setEntityMass(double mass) {
		if (isValidMass(mass))
			this.mass = mass;
		else
			this.mass = minimumShipMass();	
	}
	
	///CHECKERS///
	
	public boolean isValidShipPosition(double x, double y){
		if ((this.getEntityWorld() != null)){
			return entityFitsInWorld(this,this.getEntityWorld());}
		else
			return true;
	}
		
	
	public boolean canHaveAsBullet(Bullet bullet){
		if (this.hasAsBullet(bullet))
			 return false;
		if(bullet.getBulletShip()!=null) 
			return false;
		if (!this.bulletFullyInShip(bullet))
			return false;
		if (bullet.isEntityTerminated())
			return false;
		if (this.isEntityTerminated())
			return false;
		return true;	 
	}
	
	public boolean bulletFullyInShip(Bullet bullet){
		double x_between = Math.abs(bullet.getEntityPosition()[0]-this.getEntityPosition()[0]);
		double y_between = Math.abs(bullet.getEntityPosition()[1]-this.getEntityPosition()[1]);
		double bullet_radius = bullet.getEntityRadius();
		double ship_radius = this.getEntityRadius();
		double distance_between = getEuclidianDistance(x_between, y_between);
		return ((distance_between + bullet_radius) < ship_radius);
		
		
	}
	
	public boolean isValidRadius(double radius) {
		if ((radius < 0) || (radius < LOWER_SHIP_RADIUS) )
			return false;
		else
			return true;		
	}
	
	public boolean isValidDensity(double density) {
		if (( density < getDefaultShipDensity())||(density < 0) )
			return false;		
		return true;
	}
	
	public boolean isValidMass(double mass) {
		if ((mass == Double.NaN) || (mass < minimumShipMass())) 
			return false;		
		return true;
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
		assert isValidOrientation(this.getEntityOrientation() + angle);
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
		 
	public void addOneBulletToShip(Bullet bullet) {
		if (this.canHaveAsBullet(bullet)){
			this.bullets.put(bullet.hashCode(), bullet);
			bullet.setBulletLoaded(this);
			bullet.setEntityOrientation(this.getEntityOrientation());}
		else
			throw new IllegalArgumentException();
	}


	public void addMultipleBulletsToShip(Collection<Bullet> bullets){
		for (Bullet bullet: bullets)
			addOneBulletToShip(bullet);
	}
		 
	
	///REMOVERS///
		 
	public void removeBulletFromShip(Bullet bullet) {
		if (!this.hasAsBullet(bullet)){
			throw new IllegalArgumentException();}
		else{
			this.bullets.remove(bullet.hashCode());
			bullet.setBulletNotLoaded();	
		}
	}

	public void fireBullet(){
		if (! bullets.isEmpty()) {
			
			Map.Entry<Integer,Bullet> entry=bullets.entrySet().iterator().next();
			Bullet bullet = entry.getValue();
			
			bullet.setBulletSourceShip(this);
			this.removeBulletFromShip(bullet);
			
			double[] positionShip = this.getEntityPosition();
			double orientation = this.getEntityOrientation();
			double radiusShip = this.getEntityRadius();
			double radiusBullet = bullet.getEntityRadius();
			double[] positionBullet = {positionShip[0] + Math.cos(orientation)*(radiusShip + radiusBullet+1), 
					positionShip[1] + Math.sin(orientation)*(radiusShip + radiusBullet + 1)};
			
			try {
				bullet.setEntityPosition(positionBullet[0], positionBullet[1]);
			} catch (IllegalArgumentException illegalArgumentException) {
				bullet.Terminate();
			}
			bullet.setEntityOrientation(orientation);
			bullet.setEntityVelocity(initialFiringVelocity*Math.cos(orientation), initialFiringVelocity*Math.sin(orientation));
			this.getEntityWorld().addEntityToWorld(bullet);	
		}
	}
		 
	///MOVE///
	public void move(double dt){
		if (dt < 0)
			throw new IllegalArgumentException();
		
		double vel_x = getEntityVelocityX();
		double vel_y = getEntityVelocityY();
				
		if (this.isThrusterActive()) {
			final double acceleration = this.getShipAcceleration();
			final double orientation = this.getEntityOrientation();
			vel_x += acceleration*Math.cos(orientation)*dt;
			vel_y += acceleration*Math.sin(orientation)*dt;
			this.setEntityVelocity(vel_x, vel_y);
		}

		final double delta_x = vel_x * dt;
		final double delta_y = vel_y * dt;
		this.setEntityPosition(getEntityPositionX() + delta_x, getEntityPositionY() + delta_y);
	}
	///TERMINATE///
	
	public void Terminate() {
		if (this.isEntityFree()){
			setEntityState(State.TERMINATED);}
		else if (this.isEntityInWorld()){
			this.getEntityWorld().removeEntityFromWorld(this);
			setEntityState(State.TERMINATED);}
		for (Bullet bullet:this.getShipBullets()){
			this.removeBulletFromShip(bullet);
			bullet.Terminate();	
			}
		}

	
		 
		///CONNECTIONS WITH OTHER CLASSES///
	
	private final Map<Integer, Bullet> bullets = new HashMap<Integer, Bullet>();
	
	
}
