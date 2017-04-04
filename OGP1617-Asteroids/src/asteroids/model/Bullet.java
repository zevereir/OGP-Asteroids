package asteroids.model;

import jdk.internal.dynalink.linker.LinkerServices.Implementation;

/**
 * A class that describes bullets and their properties. A bullet has a position and
 * a velocity, both are described in a cartesian xy-field. It also has an
 * orientation and a radius which defines its circular shape. The mass, density and maximum total
 * velocity are the last properties.
 * 

 * @version 4th of April - 01u25
 * @authors Sieben Bocklandt and Ruben Broekx
 */
public class Bullet extends Entity {
	
	///CONSTRUCTOR///
	/**
	 * Initializes a new bullet with given parameters.
	 * @param x
	 * 			The x-value of the position in kilometers.
	 * @param y
	 * 			The y-value of the position in kilometers.
	 * @param xVelocity
	 * 			The x-value of the velocity in kilometers per second.
	 * @param yVelocity
	 * 			The y-value of the velocity in kilometers per second.
	 * @param radius
	 * 			The radius in kilometers.
	 * @param orientation
	 * 			The orientation in radians.
	 * @param mass
	 * 			The mass in kilograms.
	 * @param maxVelocity
	 * 			The maximum velocity in kilometers per second.
	 * @param density
	 * 			The density in kilograms per km^3.
	 * @effect  A new entity will be made via the constructor of Entity.
	 * 			|@see Implementation.
	 */
	public Bullet(double x, double y , double xVelocity, double yVelocity, double radius,double orientation
			,double mass,double maxVelocity,double density) {
		
		super(x,y,xVelocity,yVelocity,radius,orientation,mass,maxVelocity,density);
	}
	/**
	 * Initializes a new bullet with a given position, velocity and radius, the rest will be default.
	 * @effect A new bullet will be initialized with a default orientation, mass, density and maximum velocity.
	 * 			|@see Implementation 
	 */
	public Bullet(double x, double y , double xVelocity, double yVelocity, double radius){
		this(x,y,xVelocity,yVelocity,radius,Entity.getDefaultOrientation(),getDefaultBulletMass(),
				Entity.getDefaultMaxVelocity(),getDefaultBulletDensity());
	}
	/**
	 * Initializes a bullet with all default properties.
	 * @effect A new bullet with all default values.
	 * 			@see Implementation
	 */
	public Bullet(){
		this(getDefaultPosition()[0],getDefaultPosition()[1],getDefaultVelocity()[0],getDefaultVelocity()[1],getDefaultRadius());
	}

	
	///DEFAULTS///
	/**
	 * Return the default radius for a bullet.
	 * @return the default radius which is equal to 1.
	 * 			|result == 1
	 */
	public static double getDefaultRadius(){
		return 1;
	}
	/**
	 * Return the default bullet mass.
	 * @return the default mass 
	 * 			@see Implementation
	 */
	public static double getDefaultBulletMass(){
		return (4.0/3.0) * Math.PI * Math.pow(getDefaultRadius(),3) * getDefaultBulletDensity();
	}
	
	private final static double LOWER_BULLET_RADIUS = 1;
	
	/** 
	 * Return the default bullet density.
	 * @return the default density for a bullet, which is equal to 7.8E12.
	 * 			|result == 7.8E12
	 */
	static double getDefaultBulletDensity(){ 
		return 7.8E12;
	}
	
	
	
	///GETTERS///
	/**
	 * Return the mass of a bullet computed by the mass-formula.
	 * @return The mass of a bullet computed by the bullet mass formula
	 * 			@see Implementation
	 */
	public double getBulletMassFormula() {
		return (4.0/3.0)*Math.PI * Math.pow(this.getEntityRadius(),3) * this.getEntityDensity();		
	}
	
	/**
	 * Return the amount of bounces the bullet already did.
	 * @return the amount of bounces
	 * 			@see Implementation
	 */
	public int getAmountOfBounces(){
		return this.amountOfBounces;
	}
	
	/**
	 * Return the ship where the bullet is loaded on.
	 * @return the bullets ship
	 * 			@see Implementation
	 */
	public Ship getBulletShip(){
		return this.ship;
	}
	/**
	 * Return the source ship of the bullet.
	 * @return the bulelts source
	 * 			@see Implementation
	 */
	public Ship getBulletSource(){
		return this.source_ship;
		
	}
	/** Return the mass of the bullet.
	 * @return the bulets mass
	 * 			@see Implementation
	 */
	public double getEntityMass() {
		return this.mass;	
	}
	
	///CHECKERS///
	/**
	 * Check if the bullet can be loaded on a given ship.
	 * @param ship
	 * 			The ship that has to be checked
	 * @return the boolean that checks if the ship can have the bullet.
	 * 			@see Implementation
	 */
	public boolean canHaveAsShip(Ship ship){
		return (ship.canHaveAsBullet(this));
	}
	
	// --> Lower_... zal altijd groter zijn dan 0
	//  --> R(bullet) < R(ship) ?
	/**
	 * Checks if a radius is valid for this bullet.
	 * @param radius
	 * 			The radius that has to be checked.
	 * @return the boolean that checks the radius
	 * 			@see Implementation
	 */
	public boolean isValidRadius(double radius) {		
		return (radius >= LOWER_BULLET_RADIUS);
	}
	
	// The mass density rho of each bullet is the same, namely 7.8·1012kg/km^3.
	/**
	 * Checks if a density is valid for this bullet.
	 * @param density
	 * 			the density that has to be checked.
	 * @return the boolean that checks the density
	 * 			@see Implementation
	 */
	public boolean isValidDensity(double density) {
		return (density == getDefaultBulletDensity());
	}
	/**
	 * Checks if a mass is valid for this bullet.
	 * @param mass
	 * 			The mass that has to be checked.
	 * @return the boolean that checks the mass
	 * 			@see Implementation
	 */
	public boolean isValidMass(double mass) {
		return ((mass != Double.NaN) && (mass == getBulletMassFormula()));
	}
	
	///SETTERS///
	/**
	 * Set the bullet to a ship, this means that this bullet is now associated with this ship.
	 * @param ship
	 * 			The ship that will be associated with the bullet
	 * @post the bullets ship will be equal to the given ship.
	 * 			|new.getBulletShip() == ship
	 */
	public void setBulletShip(Ship ship){
		this.ship = ship;
	}
	/**
	 * Set the bullets source to a ship.
	 * @param ship
	 * 			The ship that's the bullets source
	 * @post the new bullet source will be equal to the given ship
	 * 			|new.getBulletSource() == ship
	 */
	public void setBulletSourceShip(Ship ship){
		this.source_ship = ship;
	}
	/**
	 * Set the amount of bounces the bullet already did to a given amount.
	 * @param amount
	 * 			The new amount of bounces.
	 * @post the new amount of bounces will be equal to the given amount
	 * 			|new.getAmountOfBounces() == amount		
	 */
	public void setAmountOfBounces(int amount){
		this.amountOfBounces = amount;
	}
	/**
	 * Set the bullets density to a given density.
	 * @param density
	 * 			The bullets new density.
	 * @post the new density will be equal to the given density
	 * 			|new.getEntityDensity == density 
	 */
	public void setEntityDensity(double density){
		this.density = density;
		
	}
	
	// Mass of bullet will always be calculated with the same formula, found in the method bulletMass()
	/**
	 * Set the mass of the bullet to a given value.
	 * @param mass
	 * 			The new mass of the bullet.
	 * @post the new mass will be equal to the given mass, if the mass isn't valid, the default mass will be used.
	 * 			@see Implementation
	 */
	public void setEntityMass(double mass) {
		if (isValidMass(mass))
			this.mass = mass;
		else
			this.mass = getBulletMassFormula();	
	}
	
	///MOVE/// 
	/**
	 * Move the bullet for dt time.
	 * @param dt
	 * 			The time the bullet has to move.
	 * @post After dt seconds, the bullets position will be set on dt times its velocity. 
	 * 			@see Implementation
	 * 
	 * @throws IllegalArgumentException
	 * 			If the given time is negative.
	 * 			|dt<0
	 */
	public void move(double dt){
		if (dt < 0) {
			throw new IllegalArgumentException();
		}
		
		double vel_x = this.getEntityVelocityX();
		double vel_y = this.getEntityVelocityY();

		final double new_x =this.getEntityPositionX()+ vel_x * dt;
		final double new_y =this.getEntityPositionY()+ vel_y * dt;
		
		this.setPositionWhenColliding(new_x, new_y);
		
	}
	
	
	///TERMINATION AND STATES///
	
	private BulletState state = BulletState.NOTLOADED;

	private static enum BulletState {
		LOADED, NOTLOADED;	
	}
	/**
	 * Returns the current loaded state of the bullet. This state can be loaded (=on a ship) or notloaded
	 * @return the loaded state of the bullet
	 * 			@see Implementation
	 */
	public BulletState getBulletLoadedState(){
		return this.state;
	}
	/**
	 * Checks whether a bullet is loaded on a ship or not.
	 * @return the boolean that checks if the loaded state is loaded.
	 * 			@see Implementation
	 */
	public boolean isBulletLoaded(){
		return (this.getBulletLoadedState() == BulletState.LOADED);
	}
	/**
	 * Checks whether a bullet has a proper loaded state.
	 * @return the boolean that checks if the bullet ghas a proper state
	 * 			@see Implementation
	 */
	public boolean hasBulletProperState(){
		return isBulletLoaded() ^(!isBulletLoaded());
	}
	/**
	 * Set a bullets state to a given loaded state.
	 * @param state
	 * 			The new loaded state of the bullet.
	 * @post  the new bullet loaded state will be equal to the given state
	 * 			|new.getBulletLoadedState() == state
	 * @throws IllegalStateException
	 * 			when the given state is null.
	 * 			@see Implementation
	 */
	public void setBulletLoadedState(BulletState state) {
		if (state == null){
			
			throw new IllegalStateException();}
		else
			this.state = state;
	}
	/**
	 * Set a bullet loaded.
	 * @param ship
	 * 			The ship the bullet will be loaded on.
	 * @pre The ship and the bullet are both not terminated.
	 * 			@see Implementation
	 * @effect the bullets loaded state will be set on loaded, its normal state on NoWorld. The ship will be set on ship and the source on null.
	 * 			@see Implementation
	 * 
	 */
	public void setBulletLoaded(Ship ship) {
		assert (!this.isEntityTerminated() && !ship.isEntityTerminated());
		this.setBulletLoadedState(BulletState.LOADED);
		this.setEntityNoWorld();
		this.setBulletShip(ship);
		this.setBulletSourceShip(null);
	}
	/**
	 * Set a bullet not loaded. 
	 * @param ship
	 * 			The ship the bullet came from.
	 * @pre The bullet is not terminated.
	 * 			@see Implementation
	 * @effect	the bullets loaded state will be set on notloaded, the source ship on ship and the bullets ship on null.
	 * 			@see Implementation
	 */
	public void setBulletNotLoaded(Ship ship) {
		assert (!this.isEntityTerminated());
		this.setBulletLoadedState(BulletState.NOTLOADED);
		this.setBulletShip(null);
		this.setBulletSourceShip(ship);
	}
	/**
	 * Terminate the bullet.
	 * @effect The bullets state will be set on terminated
	 * 			@see Implementation
	 */
	public void Terminate() {
		if (this.isEntityNoWorld()){
			setEntityState(State.TERMINATED);}
		else if (this.isEntityInWorld()){
			this.getEntityWorld().removeEntityFromWorld(this);
			setEntityState(State.TERMINATED);}
		}
	

	///CONNECTIONS WITH OTHER CLASSES///
	private  Ship ship = null;	
	private  Ship source_ship = null;
	
	
	/// COUNTER ///
	private int amountOfBounces = 0;
}




