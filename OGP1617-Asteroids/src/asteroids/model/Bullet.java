package asteroids.model;

/**
 * A class that describes bullets and their properties. A bullet has a position and
 * a velocity, both are described in a Cartesian x-y-field. A bullet also has an
 * orientation and a radius which defines its circular shape. 
 * The mass, density and maximum total velocity are the last properties.
 * 
 * @invar 	The position is a valid position.
 * 		  | isValidPosition(this.getEntityPositionX,this.getEntityPositionY)
 * @invar 	The velocity is a valid velocity.
 * 		  | isValidVelocity(this.getEntityVelocityX,this.getEntityVelocityY)
 * @invar 	The orientation is a valid orientation.
 * 		  | isValidOrientation(this.getEntityOrientation)
 * @invar 	The radius is a valid radius.
 * 		  | isValidRadius(this.getEntityRadius)
 * @invar 	The mass is a valid mass.
 * 		  | isValidMass(this.getEntityMass)
 * @invar 	The density is a valid density.
 * 		  | isValidDensity(this.getEntityDensity)
 * 
 * @version 8th of April
 * @authors Sieben Bocklandt and Ruben Broekx
 */
public class Bullet extends Entity {
	
	/// CONSTRUCTOR ///
	
	/**
	 * Initializes a new bullet with given parameters.
	 * 
	 * @param 	positionX
	 * 			The x-value of the position in kilometers.
	 * @param 	positionY
	 * 			The y-value of the position in kilometers.
	 * @param 	velocityX
	 * 			The x-value of the velocity in kilometers per second.
	 * @param 	velocityY
	 * 			The y-value of the velocity in kilometers per second.
	 * @param 	radius
	 * 			The radius in kilometers.
	 * @param 	orientation
	 * 			The orientation in radians.
	 * @param 	mass
	 * 			The mass in kilograms.
	 * @param 	maxVelocity
	 * 			The maximum velocity in kilometers per second.
	 * @param 	density
	 * 			The density in kilograms per km^3.
	 * 
	 * @effect  A new entity will be made via the constructor of Entity.
	 * 		  	@see implementation
	 */
	private Bullet(double positonX, double positionY, double velocityX, double velocityY, double radius, double orientation,
			double mass, double maxVelocity, double density) {
		super(positonX, positionY, velocityX, velocityY, radius, orientation, mass, maxVelocity, density);
	}

	/**
	 * Initializes a new bullet with a given position, velocity and radius, the rest will be default.
	 * 
	 * @param 	positionX
	 * 			The x-value of the position in kilometers.
	 * @param 	positionY
	 * 			The y-value of the position in kilometers.
	 * @param 	velocityX
	 * 			The x-value of the velocity in kilometers per second.
	 * @param 	velocityY
	 * 			The y-value of the velocity in kilometers per second.
	 * @param 	radius
	 * 			The radius in kilometers.
	 * 
	 * @effect 	A new bullet will be initialized with a default orientation, mass, density and maximum velocity.
	 * 		  	@see implementation 
	 */
	public Bullet(double positonX, double positionY, double velocityX, double velocityY, double radius) {
		this(positonX, positionY, velocityX, velocityY, radius, Entity.getDefaultOrientation(), getDefaultBulletMass(),
				Entity.getDefaultMaxVelocity(), getDefaultBulletDensity());
	}

	/**
	 * Initializes a bullet with all default properties.
	 * 
	 * @effect 	A new bullet with all default values
	 * 			@see implementation
	 */
	public Bullet() {
		this(getDefaultPositionX(), getDefaultPositionY(), getDefaultVelocityX(), getDefaultVelocityY(),
				getDefaultBulletRadius());
	}
	
	
	/// CONSTANTS ///
	
	/**
	 * The smallest radius a bullet can have.
	 */
	private final static double LOWER_BULLET_RADIUS = 1;

	
	/// COUNTERS ///
	
	/**
	 * An integer that counts the amount of bounces with the boundaries the bullet already underwent.
	 * When initiated, the bullet has not yet bounced, so the integer is zero.
	 */
	private int amountOfBounces = 0;
	
	
	/// DEFAULTS ///
	
	/** 
	 * Return the default bullet density.
	 * 
	 * @return 	The default density for a bullet, which is equal to 7.8E12.
	 * 		  | result == 7.8E12
	 */
	private static double getDefaultBulletDensity() {
		return 7.8E12;
	}
	
	/**
	 * Return the default bullet mass.
	 * 
	 * @return 	The default mass.
	 * 			@see implementation
	 */
	private static double getDefaultBulletMass() {
		return MassFormula(getDefaultBulletRadius(), getDefaultBulletDensity());
	}

	/**
	 * Return the default radius for a bullet.
	 * 
	 * @return 	The default radius which is equal to 1.
	 *			@see implementation
	 */
	private static double getDefaultBulletRadius() {
		return 1;
	}
		
	
	/// GETTERS ///
	
	/**
	 * Return the amount of bounces the bullet already underwent.
	 * 
	 * @return 	The amount of bounces.
	 * 			@see implementation
	 */
	protected int getAmountOfBounces() {
		return this.amountOfBounces;
	}

	/**
	 * Returns the current loaded state of the bullet. This state can be loaded, this is when it is on a ship,
	 * or not loaded, when the bullet is not in its parent-ship.
	 * 
	 * @return 	The loaded state of the bullet.
	 * 			@see implementation
	 */
	private BulletState getBulletLoadedState() {
		return this.state;
	}
	
	/**
	 * Return the ship where the bullet is loaded on.
	 * 
	 * @return 	The bullet's ship.
	 * 			@see implementation
	 */
	public Ship getBulletShip() {
		return this.ship;
	}

	/**
	 * Return the source ship of the bullet.
	 * 
	 * @return 	The bullet's source.
	 * 			@see implementation
	 */
	public Ship getBulletSource() {
		return this.source_ship;
	}

	/** Return the mass of the bullet.
	 * 
	 * @return 	The bullet's mass.
	 * 			@see implementation
	 */
	public double getEntityMass() {
		return this.mass;
	}
	
	
	/// SETTERS ///
	
	/**
	 * Set the amount of bounces the bullet already underwent to a given amount.
	 * 
	 * @param 	amount
	 * 			The new amount of bounces.
	 * 
	 * @post 	The new amount of bounces will be equal to the given amount.
	 * 		  | new.getAmountOfBounces() == amount		
	 */
	protected void setAmountOfBounces(int amount) {
		this.amountOfBounces = amount;
	} 

	/**
	 * Set a bullet loaded.
	 * 
	 * @param 	ship
	 * 			The ship the bullet will be loaded on.
	 * 
	 * @pre 	The ship and the bullet are both not terminated.
	 * 			@see implementation
	 * 
	 * @effect 	The bullet's loaded state will be set on loaded, its normal state on NO_WORLD. 
	 * 			The ship will be set on ship and the source on null.
	 * 			@see implementation
	 */
	protected void setBulletLoaded(Ship ship) {
		assert (!this.isEntityTerminated() && !ship.isEntityTerminated());
		
		this.setBulletLoadedState(BulletState.LOADED);
		this.setEntityFree();
		this.setBulletShip(ship);
		this.setBulletSourceShip(null);
	}

	/**
	 * Set a bullet's state to a given loaded state.
	 * 
	 * @param 	state
	 * 			The new loaded state of the bullet.
	 * 
	 * @post  	The new bullet loaded state will be equal to the given state.
	 * 		  | new.getBulletLoadedState() == state
	 * 
	 * @throws 	IllegalStateException
	 * 			when the given state is null.
	 * 			@see implementation
	 */
	protected void setBulletLoadedState(BulletState state) {
		if (state == null)
			throw new IllegalStateException();
		
		else
			this.state = state;
	}

	/**
	 * Set a bullet not loaded. 
	 * 
	 * @param 	ship
	 * 			The ship the bullet came from.
	 * 
	 * @pre 	The bullet is not terminated.
	 * 			@see implementation
	 * 
	 * @effect	The bullet's loaded state will be set to not loaded, the source ship to ship and the bullet's ship to null.
	 * 			@see implementation
	 */
	protected void setBulletNotLoaded(Ship ship) {
		assert (!this.isEntityTerminated());
		
		this.setBulletLoadedState(BulletState.NOT_LOADED);
		this.setBulletShip(null);
		this.setBulletSourceShip(ship);
	}
	
	/**
	 * Associate the bullet with a given ship.
	 * 
	 * @param 	ship
	 * 			The ship that will be associated with the bullet.
	 * 
	 * @post 	The bullet's ship will be equal to the given ship.
	 * 		  | new.getBulletShip() == ship
	 */
	protected void setBulletShip(Ship ship) {
		this.ship = ship;
	}

	/**
	 * Set a given ship as the source of the bullet.
	 * 
	 * @param 	ship
	 * 			The ship that's the bullet's source.
	 * 
	 * @post 	The new bullet source will be equal to the given ship.
	 * 		  | new.getBulletSource() == ship
	 */
	protected void setBulletSourceShip(Ship ship) {
		this.source_ship = ship;
	}

	/**
	 * Set the bullet's density to a given density.
	 * 
	 * @param 	density
	 * 			The bullet's new density.
	 * 
	 * @post 	The new density will be equal to the given density.
	 * 		  | new.getEntityDensity == density 
	 */
	protected void setEntityDensity(double density) {
		this.density = getDefaultBulletDensity();
	}

	/**
	 * Set the mass of the bullet to a given value.
	 * 
	 * @param 	mass
	 * 			The new mass of the bullet.
	 * 
	 * @post 	The new mass will be equal to the given mass, if the mass isn't valid, the default mass will be used.
	 * 			@see implementation
	 */
	protected void setEntityMass(double mass) {
		if (isValidMass(mass))
			this.mass = mass;
		
		else
			this.mass = MassFormula(getEntityRadius(),getEntityDensity());
	}
	
	
	/// CHECKERS ///
	
	/**
	 * Check if the bullet can be loaded on a given ship.
	 * 
	 * @param 	ship
	 * 			The ship that has to be checked.
	 * 
	 * @return 	The boolean that checks if the ship can have the bullet.
	 * 			@see implementation
	 */
	protected boolean canHaveAsShip(Ship ship) {
		return (ship.canHaveAsBullet(this));
	}

	/**
	 * Checks whether a bullet has a proper loaded state.
	 * 
	 * @return 	The boolean that checks if the bullet has a proper state.
	 * 			@see implementation
	 */
	protected boolean hasBulletProperState() {
		return (isBulletLoaded() ^ !isBulletLoaded());
	}

	/**
	 * Checks whether a bullet is loaded on a ship or not.
	 * 
	 * @return 	The boolean that checks if the loaded state is loaded.
	 * 			@see implementation
	 */
	private boolean isBulletLoaded() {
		return (this.getBulletLoadedState() == BulletState.LOADED);
	}
	
	/**
	 * Checks if a density is valid for this bullet.
	 * 
	 * @param 	density
	 * 			The density that has to be checked.
	 * 
	 * @return 	The boolean that checks the density.
	 * 			@see implementation
	 */
	protected boolean isValidDensity(double density) {
		return (density == getDefaultBulletDensity());
	}
	
	/**
	 * Checks if a mass is valid for this bullet.
	 * 
	 * @param 	mass
	 * 			The mass that has to be checked.
	 * 
	 * @return 	The boolean that checks the mass.
	 * 			@see implementation
	 */
	protected boolean isValidMass(double mass) {
		return ((mass != Double.NaN) && (mass == MassFormula(getEntityRadius(),getEntityDensity())));
	}
	
	/**
	 * Checks if a radius is valid for this bullet.
	 * 
	 * @param 	radius
	 * 			The radius that has to be checked.
	 * 
	 * @return 	The boolean that checks the radius.
	 * 			@see implementation
	 */
	protected boolean isValidRadius(double radius) {
		return (radius >= LOWER_BULLET_RADIUS);
	}

	

	
	
	
	/// MOVE ///
	
	/**
	 * Move the bullet for the given time moveTime.
	 * 
	 * @param 	moveTime
	 * 			The time the bullet has to move.
	 * 
	 * @post 	After moveTime, the bullet's position will be set on moveTime times its velocity.
	 * 			@see implementation
	 * 
	 * @throws 	IllegalArgumentException
	 * 			If the given time is negative.
	 * 		  | moveTime < 0
	 */
	protected void move(double moveTime) {
		if (moveTime < 0)
			throw new IllegalArgumentException();
		
		double velocityX = this.getEntityVelocityX();
		double velocityY = this.getEntityVelocityY();

		final double collidingPositionX = this.getEntityPositionX() + velocityX * moveTime;
		final double collidingPositionY = this.getEntityPositionY() + velocityY * moveTime;
		
		this.setPositionWithoutChecking(collidingPositionX, collidingPositionY);
	}

	
	/// TERMINATION AND STATES ///
	
	/**
	 * The state of the bullet is initiated as NOT_LOADED.
	 */
	private BulletState state = BulletState.NOT_LOADED;

	/***
	 * The states a bullet can be in. 
	 * LOADED: the bullet is loaded on a ship.
	 * NOT_LOADED: the bullet is not loaded on a ship.
	 * These states are called loaded states, cause they're a different sort of states than the normal states 
	 * (IN_WORLD, NO_WORLD and TERMINTED). 
	 */
	private static enum BulletState {
		LOADED, NOT_LOADED;
	}

	/**
	 * Terminate the bullet.
	 * 
	 * @effect 	The bullet's state will be set on terminated.
	 * 			@see implementation
	 */
	public void Terminate() {
		if (this.isEntityFree())
			setEntityState(State.TERMINATED);
		
		else if (this.isEntityInWorld()) {
			this.getEntityWorld().removeEntityFromWorld(this);
			setEntityState(State.TERMINATED);
		}
	}
	

	/// CONNECTIONS WITH OTHER CLASSES ///
	
	/**
	 * The ship the bullet is loaded on. When the bullet is initiated, it's not loaded on a ship so the ship is null.
	 */
	private Ship ship = null;
	
	/**
	 * The ship the bullet is fired from. When the bullet is initiated, it's not fired so the source is null.
	 */
	private Ship source_ship = null;
}




