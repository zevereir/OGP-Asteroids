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
 * orientation and a radius which defines its circular shape. The thrusteractivity 
 * and thrusterforce define the ships thruster. The mass, density and maximum total velocity are 
 * the ships last properties.
 * 
 * @invar The position is a valid position.
 * 			|isValidPosition(this.getEntityPositionX,this.getEntityPositionY)
 * @invar The velocity is a valid velocity.
 * 			|isValidVelocity(this.getEntityVelocityX,this.getEntityVelocityY)
 * @invar The orientation is a valid orientation.
 * 			|isValidOrientation(this.getEntityOrientation)
 * @invar The radius is a valid radius.
 * 			|isValidRadius(this.getEntityRadius)
 * @invar The mass is a valid mass.
 * 			|isValidMass(this.getEntityMass)
 * @invar The density is a valid density.
 * 			|isValidDensity(this.getEntityDensity)
 * @version 28th of march
 * @authors Sieben Bocklandt and Ruben Broekx
 */
public class Ship extends Entity {

	/// CONSTRUCTORS///
	
	//@note the first constructor will never be used in facade, but we made it to increase 
    // 		the adaptability. In the future, it's possible that each ship has a different maximum velocity
	// 		and then this constructor will be used

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
	 * @param mass
	 *            The mass of the ship in kilograms.
	 * 
	 * @param maxVelocity
	 *            The maximum total velocity of the ship in kilometers per
	 *            second.
	 * @param density
	 *            The density of the ship in kilograms/km^3.
	 * @param thrusterActivity
	 *            The variable that is true when the thruster is active, false
	 *            when it's not.
	 * @param thrusterforce
	 *            The force in newton the thruster of a ship can deliver.
	 *            
	 * @post The position of the ship is equal to the given x and y. They will
	 *       be used as an array {x,y}. 
	 *       |new.getEntityPosition == {x,y}
	 *
	 * @post The total velocity of the ship is lower than it's maximum velocity.
	 *       If the velocity exceeds this limit, xVelocity and yVelocity will be
	 *       set on the projection of the maximum velocity on the x- and y-axis.
	 *       |if (Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2))< this.getEntityMaxVelocity) 
	 *       | new.getEntityVelocity = {xVelocity, yVelocity}
	 *       |else 
	 *       | new.getEntityVelocity = {Math.cos(orientation)*getEntityMaxVelocity, 
	 *       | Math.sin(orientation)*getEntityMaxVelocity}
	 * @post The orientation of the ship is equal to the given orientation.
	 *       |new.getEntityOrientation() == orientation
	 * @post The maximum velocity will be equal to the speed of light, unless
	 *       the given maxVelocity is a positive number and less than the speed
	 *       of light. Then the maximum velocity will be equal to this
	 *       maxVelocity. 
	 *       |if ((maxVelocity < SPEED_OF_LIGHT) && (maxVelocity>0)) 
	 *       | new.getShipMaxVelocity == maxVelocity 
	 *       |else 
	 *       |new.getShipMaxVelocity == SPEED_OF_LIGHT
	 *@post  The density of the ship will be equal to the given density.
	 *		 |new.getEntityDensity() == density
	 *@post  The thrusterActivity of the ship will be equal to the givn thrusteractivity.
	 *		 |new.isThrusterActive == thrusterActivity
	 *@post  The thrusterforce of the ship will be equal to the given thrusterforce.
	 *		 |new.getShipThrusterForce()
	 *		
	 * @throws IllegalArgumentException
	 *       If the given radius is lower than the lower radius. 
	 *       |(radius < LOWER_RADIUS)
	 * @throws IllegalArgumentException
	 *       If x or y is not a number. 
	 *       |(!isValidArray(x,y))
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
	 *         maximum total velocity,density,thrusteractivity and thrusterforce. 
	 *         |@see implementation
	 */
	//NORMAL CONSTRUCTOR//
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation,double mass)
		 {
		this(x, y, xVelocity, yVelocity, radius, orientation, mass,Entity.getDefaultMaxVelocity(), getDefaultShipDensity(),
				getDefaultThrusterActivity(),getDefaultThrusterForce());
	}

	/**
	 * Initializes a new ship with it's default values.
	 * 
	 * @effect The ship is initialized with the default values.
	 *         |@see implementation
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
	 * Return the default radius ofa ship.
	 * 
	 * @return The default radius is equal to ten. 
	 * 			| result== 10
	 */
	@Immutable
	public static double getDefaultRadius() {
		return 10;
	}

	/**
	 * Return the default mass of a ship.
	 * 
	 * @return The default mass.
	 * 			|@see implementation
	 */
	@Immutable
	public static double getDefaultMass() {
		return (4.0/3.0) * Math.PI * Math.pow(getDefaultRadius(),3) * getDefaultShipDensity();
	}
	
	/**
	 * Return the default thrusteractivity of a ship.
	 * @return The default thrusteractivity which is false.
	 * 			|result == false
	 */
	@Immutable
	public static boolean getDefaultThrusterActivity(){;
		return false;
	}
	
	/**
	 * Return the default thrusterforce of a ship.
	 * @return The default thrusterforce is equal to 1.1E21.
	 * 			|result == 1.1E21
	 */
	@Immutable
	public static double getDefaultThrusterForce(){
		return 1.1E21;
	}
	/**
	 * Return the initial velocity at which a ship fires a bullet.
	 * @return The initialFiringVelocity is equal to 250.
	 * 			|result == 250
	 */
	@Immutable
	private double getInitialFiringVelocity(){
		return 250;
	}
	
	private final static double LOWER_SHIP_RADIUS = 10;
	
	/**
	 * Return the default density of a ship;
	 * @return The default density is 1.42E12
	 * 			|result ==1.42E12
	 */
	public static double getDefaultShipDensity(){
		return 1.42E12;
	}
	
	
	/// GETTERS ///	
	/**
	 * Return the thrusterActivity of the ship.
	 * @return the thrusterActivity.
	 * 			@see implementation
	 */
	@Basic
	public boolean isThrusterActive(){
		return this.thruster_activity;
	}
	
	/** 
	 * return the acceleration of the ship computed with Newtons third law.
	 * @return The acceleration.
	 * 			@see implementation
	 */
	public double getShipAcceleration(){
		return (this.getShipTrusterForce()/this.getEntityMass());
	}
	/**
	 * Return the thrusterForce of the ship.
	 * @return the thrusterForce.
	 * 			@see implementation
	 */
	@Basic
	public double getShipTrusterForce(){
		return this.thruster_force;
	}
	
	/**
	 * Return the total weight of the bullets loaded on the ship. This is the sum of the weights of all the bullets.
	 * @return the total weight.
	 * 			|@see implementation
	 */
	public double getTotalBulletsWeight(){
		double weight = 0;
		for (Bullet bullet: this.getShipBullets())
			weight += bullet.getEntityMass();
		return weight;
	}
	
	/**
	 * Return a set containing all the bullets that are loaded on the ship.
	 * @return the set of bullets
	 * 			|@see implementation
	 */
	public Set<Bullet> getShipBullets(){
		Set<Bullet> result = new HashSet<Bullet>();
		result.addAll(this.bullets.values());
		return result;
	}
	
	/**
	 * Return the total number of bullets loaded on the ship.
	 * @return the total number of bullets.
	 * 			|@see implementation
	 */
	public int getNbBulletsOnShip(){
		return this.getShipBullets().size();
	}
	/**
	 * Return the total mass of the ship. This is the sum of the mass of the ship and the total weight of its bullets.
	 * @return the total weight.
	 * 			|@see implementation
	 */
	@Basic
	public double getEntityMass() {
		double bullets_weight = ((Ship)this).getTotalBulletsWeight();
		return this.mass + bullets_weight;	
	}

	
	/// SETTERS ///
	/**
	 * Give the ship a new thrusterActivity.
	 * @param thrusterActivity
	 * 			The new thrusterActivity.
	 * @post The thrusterActivity of the ship will be equal to the given boolean value.
	 * 			|new.isThrusterActive == thrusterActivity.
	 */
	public void setThrusterActive(boolean thrusterActivity){
		this.thruster_activity = thrusterActivity;
	}
	
	/**
	 * Give the ship a new thrusterForce.
	 * @param thrusterForce
	 * 			The new thrusterForce.
	 * @post If the given force is positive,the thrusterActivity of the ship will be equal to the given boolean value.
	 * 			|If (thrusterForce > 0)
	 * 			|new.getShipThrusterForce == thrusterActivity.
	 * @post If the given force is negative, the thrusterforce of the ship will be equal to the default value.
	 * 			|@see implementation
	 */
	public void setShipThrusterForce(double thrusterForce){
		if (thrusterForce < 0)
			thrusterForce = getDefaultThrusterForce();
		
		this.thruster_force = thrusterForce;
	}
	
	/**
	 * Give the ship a new density.
	 * @param density
	 * 			The new density.
	 * @post If the given density is valid, the ships density will be equal to it, else it will be set on the default value.
	 * 			|@see implementation
	 */
	public void setEntityDensity(double density){
		if (isValidDensity(density))
			this.density = density;
		
		else
			this.density = getDefaultShipDensity();
	}
	
	/**
	 * Give the ship a new mass.
	 * @param mass
	 * 			The new mass.
	 * @post If the given mass is valid, the ships mass will be equal to it, else it will be set on the minimum value.
	 * 			|@see implementation
	 */
	public void setEntityMass(double mass) {
		if (isValidMass(mass))
			this.mass = mass;
		else
			this.mass = getMinimumShipMass();	
	}
	
	/** 
	 * Return the minimum mass a ship can have.
	 * @return The minimum mass
	 * 			|@see implementation
	 */
	public double getMinimumShipMass(){
		return (4.0/3.0) * Math.PI * Math.pow(this.getEntityRadius(),3) * this.getEntityDensity();
	}
	
	///MOVE///
	/**
	 * Let a ship move for a given time.
	 * @param dt
	 * 			The time for which the ship has to move.
	 * @param entity1
	 * 			An entity that is colliding.
	 * @param entity2
	 * 			A second entity that is colliding.
	 * 
	 * @post If the ships thruster is active, its acceleration will be used to recalculate the velocity.
	 * 			|@see Implementation
	 * @post After dt seconds, the ship's position will be set on dt times its velocity. If the ship is entity1 or entity2,
	 * 		 the new position will not be checked for collisions.
	 * 			|@see Implementation
	 * 
	 * @throws IllegalArgumentException
	 * 			If the given time is negative.
	 * 			|dt<0
	 */
	public void move(double dt){
		if (dt < 0) {
			System.out.println("MOVE-NEGATIVE DT");
			throw new IllegalArgumentException();	}			
		
		double vel_x = this.getEntityVelocityX();
		double vel_y = this.getEntityVelocityY();

		final double new_x =this.getEntityPositionX()+ vel_x * dt;
		final double new_y =this.getEntityPositionY()+ vel_y * dt;
		
		if (this.isThrusterActive()) {
			final double acceleration = this.getShipAcceleration();
			final double orientation = this.getEntityOrientation();
			double velocity_x = vel_x+ acceleration*Math.cos(orientation)*dt;
			double velocity_y = vel_y+acceleration*Math.sin(orientation)*dt;
			this.setEntityVelocity(velocity_x, velocity_y);
		}
		this.setPositionWhenColliding(new_x, new_y);			
		
	}
	
	
	///CHECKERS///
	/**
	 * Checks if a position is valid.
	 * @param x
	 * 			The x-value of the position.
	 * @param y
	 * 			The y-value of the position
	 * @return False if the ship has a world and the position isn't in this world, else the result is true.
	 * 			|@see Implementation
	 */
	public boolean isValidShipPosition(double x, double y){
		if ((this.getEntityWorld() != null))
			return entityFitsInWorld(this.getEntityWorld());
		
		return true;
	}
	
	/**
	 * Checks if a bullet may be loaded on the ship.
	 * @param bullet
	 * 			The bullet that has to be checked.
	 * @return False if the bullet is already in the ship, has another ship, doens't lie fully in the ship, is terminated or if the ship is terminated.
	 * 			|@see implementation
	 */
	public boolean canHaveAsBullet(Bullet bullet){
		
		if (bullet.getBulletShip() != null)
		
			return false;
		
		if (!this.bulletFullyInShip(bullet)){
			System.out.println("bulletfullyinship");
			return false;}
		
		if (bullet.isEntityTerminated())
			return false;
		
		if (this.isEntityTerminated())
			return false;
		
		return true;	 
	}
	
	/**
	 * Checks if a bullet lies completely in the ship.
	 * @param bullet
	 * 			The bullet that has to be checked.
	 * @return False if the sum of cartesian distance between the centers and the radius of the bullet is greater than the radius of the ship. 
	 * 			|@see Implementation
	 */
	public boolean bulletFullyInShip(Bullet bullet){
		double delta_x = Math.abs(bullet.getEntityPositionX() - this.getEntityPositionX());
		double delta_y = Math.abs(bullet.getEntityPositionY() - this.getEntityPositionY());
		double bullet_radius = bullet.getEntityRadius();
		double ship_radius = this.getEntityRadius();
		double distance_between = getEuclidianDistance(delta_x, delta_y);
		System.out.println(ship_radius-(distance_between + bullet_radius) );
		return ((distance_between + bullet_radius) < ship_radius);	
	}
	
	/**
	 * Checks if a radius is valid.
	 * @param radius
	 * 			The radius that has to be checked.
	 * @return true if the radius is greater than the default lower ship radius.
	 * 			|@see Implementation
	 */
	public boolean isValidRadius(double radius) {
		return (radius >= LOWER_SHIP_RADIUS);
	}
	
	/**
	 * Checks if a radius is valid.
	 * @param density
	 * 			The density that has to be checked.
	 * @return True if the density is greater than the default density.
	 * 			|@see Implementation
	 */
	public boolean isValidDensity(double density) {
		return (density >= getDefaultShipDensity());
	}
	
	/**
	 * Checks if a mass is valid.
	 * @param mass
	 * 			The mass that has to be checked.
	 * @return True if the mass is greater than the minimum mass and the mass is a number.
	 * 			|@see Implementation
	 */
	public boolean isValidMass(double mass) {
		return ( (mass != Double.NaN) && (mass >= getMinimumShipMass()) );
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
	 *      |isVallidRadian(this.getEntityOrientation() + angle)
	 * 
	 * @effect The new orientation of the ship is equal to the sum of the
	 *         orientation and the given angle.
	 *         |this.setEntityOrientation(this.getEntityOrientation() + angle)
	 */
	public void turn(double angle) {
		assert isValidOrientation(this.getEntityOrientation() + angle);
		this.setEntityOrientation(this.getEntityOrientation() + angle);
	}
	
	/**
	 * Set the ships thruster active.
	 * @post the ships thruster is active.
	 * 			|new.isThrusterActive == True
	 */
	public void thrustOn(){
		setThrusterActive(true);
	}
	
	/**
	 * Set the ships thruster inactive.
	 * @post the ships thruster is inactive.
	 * 			|new.isThrusterActive == False
	 */
	public void thrustOff(){
		setThrusterActive(false);
	}
	

	/// HAS ///
	/**
	 * Checks if a bullet is already loaded on the ship.
	 * @param bullet
	 * 			the bullet that has to be checked.
	 * @return False if the bullet is already on the ship.
	 * 			|@see Implementation
	 */
	public boolean hasAsBullet(Bullet bullet){
		return this.bullets.containsValue(bullet);
	}       
	
	
	///ADDERS///
	/**
	 * Load a bullet on the ship.
	 * @param bullet
	 * 			The bullet that has to be loaded.
	 * @post If the ship can have the bullet, the bullet will be loaded.
	 * 			|new.hasAsBullet == true
	 * @throws IllegalArgumentException 
	 * 			If the bullet cannot be loaded on the ship.
	 *			|!canHaveAsBulet(bullet)
	 */
	public void addOneBulletToShip(Bullet bullet) {
		if (this.canHaveAsBullet(bullet)){
			this.bullets.put(bullet.hashCode(), bullet);
			bullet.setBulletLoaded(this);
			bullet.setEntityOrientation(this.getEntityOrientation());
		} else{
			System.out.println("ADD_ONE_BULLET_ERROR");
			throw new IllegalArgumentException();}
	}

	/**
	 * Load multiple bullets on the ship.
	 * @param bullets
	 *			The bullets that have to be loaded.
	 *@effect The bullets will be added one at a time.
	 *			|@see Implementation 
	 *
	 */
	public void addMultipleBulletsToShip(Collection<Bullet> bullets){
		for (Bullet bullet: bullets)
			addOneBulletToShip(bullet);
	}
		 
	
	///REMOVERS///
	/**
	 * Removes a bullet from the ship.
	 * @param bullet
	 * 			The bullet that has to be removed
	 * @post  The bullet isn't loaded on the ship anymore.
	 * 			|!new.hasAsBullet(bullet)
	 * @throws IllegalArgumenException
	 * 			If the bullet wasn't loaded on the ship.
	 * 			|this.hasAsBullet(bullet)
	 */
	public void removeBulletFromShip(Bullet bullet) {
		if (!this.hasAsBullet(bullet)){
			System.out.println("REMOVE_BULLET_EXCEPTION");
			throw new IllegalArgumentException();}
		else{
			this.bullets.remove(bullet.hashCode());
			bullet.setBulletNotLoaded(this);	
		}
	}

	/**
	 * DOCUMENTATION HAS TO BE WRITTEN WHEN TO FUNCCTION IS CORRECT
	 */
	public void fireBullet(){
		if (! bullets.isEmpty()) {
			
			Map.Entry<Integer,Bullet> entry=bullets.entrySet().iterator().next();
			Bullet bullet = entry.getValue();

			this.removeBulletFromShip(bullet);

			double positionShipX = this.getEntityPositionX();
			double positionShipY = this.getEntityPositionY();
			double orientation = this.getEntityOrientation();
			double radiusShip = this.getEntityRadius();
			double radiusBullet = bullet.getEntityRadius();
			double positionBulletX = positionShipX + Math.cos(orientation) * (radiusShip + radiusBullet + 3); 
			double positionBulletY = positionShipY + Math.sin(orientation) * (radiusShip + radiusBullet + 3);
			
			bullet.setPositionWhenColliding(positionBulletX, positionBulletY);
			World world = this.getEntityWorld();
			bullet.setEntityOrientation(orientation);
			bullet.setEntityVelocity(getInitialFiringVelocity()*Math.cos(orientation), getInitialFiringVelocity()*Math.sin(orientation));
			
			if(possibleToFire(bullet, this, world, positionBulletX, positionBulletY, radiusBullet)){
				world.addEntityToWorld(bullet);	
					}
				}
			}
			
		
	

	public boolean possibleToFire(Bullet bullet, Ship ship, World world, double posBulletX, double posBulletY, double radiusBullet) {
		boolean Boolean = true;

		// Check if the new-created bullet is in the world
		
		if (!bullet.entityInBoundaries(world)) {
			bullet.Terminate();
			Boolean = false;
		}

		if (Boolean == true) {
			for (Object entity1: world.getWorldEntities()) {


				// Two entities are overlapping when the distance between the centers is bigger than the sum of the radii of the two.
				if (bullet.overlap((Entity)entity1)) {
					Boolean = false;

					// If entity1 is a bullet:
					if (entity1 instanceof Bullet) {
						// If the bullet overlaps with a bullet from its parent-ship, the newest bullet will not be fired.
						System.out.println("eigen schip: "+ship+" ander schip: "+((Bullet)entity1).getBulletSource());
						if (ship.equals(((Bullet)entity1).getBulletSource())){
							bullet.setPositionWhenColliding(ship.getEntityPositionX(), ship.getEntityPositionY());
							ship.addOneBulletToShip(bullet);
						}

						// If the bullet overlaps with a bullet which does not belong to its parent-ship, the two will be terminated.
						else {
							System.out.println("Terminate TWO");
							bullet.Terminate();
							((Bullet)entity1).Terminate();
						}
					}

					// If entity1 is a ship:
					else if (entity1 instanceof Ship) {
						// If the bullet overlaps with its parent-ship, the bullet will be reloaded.
						if (ship.equals(entity1)) {
							bullet.setPositionWhenColliding(ship.getEntityPositionX(), ship.getEntityPositionY());
							ship.addOneBulletToShip(bullet);
						}

						// If the bullet overlaps with a different ship, the two will be terminated.
						else {
							bullet.Terminate();
							((Ship)entity1).Terminate();
						}
					}

					// This is normally not possible..
					else {
						System.out.println("Error at model.ship in possibleToFire, entity is nor a ship, nor a bullet");
						System.out.println("IMPOSSIBLE");
					}
				}
			}
		}

		System.out.println(Boolean);
		return Boolean;
	}
	///TERMINATE///
	/**
	 * Terminate the ship.
	 * 
	 * @post The ships state will be set on Terminated.
	 * 		 If the ship was in a world, it will be removed from this world. If it had bullets, these bullets will be terminated. 
	 * 		 @see implementation
	 */
	public void Terminate() {
		if (this.isEntityFree()){
			setEntityState(State.TERMINATED);
		} else if (this.isEntityInWorld()){
			this.getEntityWorld().removeEntityFromWorld(this);
			setEntityState(State.TERMINATED);
		}
		
		for (Bullet bullet:this.getShipBullets()){
			this.removeBulletFromShip(bullet);
			bullet.Terminate();	
		}
	}

		 
	///CONNECTIONS WITH OTHER CLASSES///
	// The Map bullet is a Map with as key the hash-code representing the bullet, and as value the bullet itself.
	private final Map<Integer, Bullet> bullets = new HashMap<Integer, Bullet>();
	
	
}


