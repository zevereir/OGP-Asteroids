package asteroids.model;
/**
 * A class that describes bullets and their properties. A bullet has a position and
 * a velocity, both are described in a cartesian xy-field. It also has an
 * orientation and a radius which defines its circular shape. The mass, density and maximum total
 * velocity are the last properties.
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
public class Bullet extends Entity {
	
	/// CONSTRUCTOR ///
	
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
	public Bullet(double positonX, double positionY, double velocityX, double velocityY, double radius, double orientation,
			double mass, double maxVelocity, double density) {
		super(positonX, positionY, velocityX, velocityY, radius, orientation, mass, maxVelocity, density);
	}

	/**
	 * Initializes a new bullet with a given position, velocity and radius, the rest will be default.
	 * @effect A new bullet will be initialized with a default orientation, mass, density and maximum velocity.
	 * 			|@see Implementation 
	 */
	public Bullet(double positonX, double positionY, double velocityX, double velocityY, double radius) {
		this(positonX, positionY, velocityX, velocityY, radius, Entity.getDefaultOrientation(), getDefaultBulletMass(),
				Entity.getDefaultMaxVelocity(), getDefaultBulletDensity());
	}

	/**
	 * Initializes a bullet with all default properties.
	 * @effect A new bullet with all default values.
	 * 			|@see Implementation
	 */
	public Bullet() {
		this(getDefaultPositionX(), getDefaultPositionY(), getDefaultVelocityX(), getDefaultVelocityY(),
				getDefaultRadius());
	}
	
	
	/// CONSTANTS ///
	
	private final static double LOWER_BULLET_RADIUS = 1;

	
	/// COUNTERS ///
	
	private int amountOfBounces = 0;
	
	
	/// DEFAULTS ///
	
	/** 
	 * Return the default bullet density.
	 * @see implementation
	 */
	public static double getDefaultBulletDensity() {
		return 7.8E12;
	}
	
	/**
	 * Return the default bullet mass.
	 * @see Implementation
	 */
	public static double getDefaultBulletMass() {
		return (4.0 / 3.0) * Math.PI * Math.pow(getDefaultRadius(), 3) * getDefaultBulletDensity();
	}

	/**
	 * Return the default radius for a bullet.
	 * @see implementation
	 */
	public static double getDefaultRadius() {
		return 1;
	}
		
	
	/// GETTERS ///
	
	/**
	 * Return the amount of bounces the bullet already did.
	 * @see Implementation
	 */
	public int getAmountOfBounces() {
		return this.amountOfBounces;
	}
	
	/**
	 * Return the ship where the bullet is loaded on.
	 * @see Implementation
	 */
	public Ship getBulletShip() {
		return this.ship;
	}

	/**
	 * Return the source ship of the bullet.
	 * @see Implementation
	 */
	public Ship getBulletSource() {
		return this.source_ship;
	}

	/** 
	 * Return the mass of the bullet.
	 * @see Implementation
	 */
	public double getEntityMass() {
		return this.mass;
	}
	
	
	/// SETTERS ///
	
	public void setAmountOfBounces(int amount) {
		this.amountOfBounces = amount;
	} 
	public void setBulletShip(Ship ship) {
		this.ship = ship;
	}

	public void setBulletSourceShip(Ship ship) {
		this.source_ship = ship;
	}

	public void setEntityDensity(double density) {
		this.density = getDefaultBulletDensity();
	}

	// Mass of bullet will always be calculated with the same formula, found in
	// the method bulletMass()
	public void setEntityMass(double mass) {
		if (isValidMass(mass))
			this.mass = mass;
		
		else
			this.mass = BulletMassFormula();
	}
	
	
	/// CHECKERS ///
	
	/**
	 * Check if the bullet can be loaded on a given ship.
	 * @param ship
	 * 			The ship that has to be checked
	 * @see Implementation
	 */
	public boolean canHaveAsShip(Ship ship) {
		return (ship.canHaveAsBullet(this));
	}
	
	// The mass density rho of each bullet is the same, namely 7.8·1012kg/km^3.
	public boolean isValidDensity(double density) {
		return (density == getDefaultBulletDensity());
	}
	
	public boolean isValidMass(double mass) {
		return ((mass != Double.NaN) && (mass == BulletMassFormula()));
	}

	public boolean isValidRadius(double radius) {
		return (radius >= LOWER_BULLET_RADIUS);
	}

	
	/// HELP FUNCTIONS ///	
	
	/**
	 * Return the mass of a bullet computed by the mass-formula.
	 * @return ...
	 * 			|@see Implementation
	 */
	public double BulletMassFormula() {
		return (4.0 / 3.0) * Math.PI * Math.pow(this.getEntityRadius(), 3) * this.getEntityDensity();
	}
	
	
	/// MOVE ///
	
	public void move(double dt) {
		if (dt < 0)
			throw new IllegalArgumentException();
		
		double velocityX = this.getEntityVelocityX();
		double velocityY = this.getEntityVelocityY();

		final double collidingPositionX = this.getEntityPositionX() + velocityX * dt;
		final double collidingPositionY = this.getEntityPositionY() + velocityY * dt;
		
		this.setPositionWhenColliding(collidingPositionX, collidingPositionY);
	}

	
	/// TERMINATION AND STATES ///
	
	private BulletState state = BulletState.NOTLOADED;

	private static enum BulletState {
		LOADED, NOTLOADED;
	}

	public BulletState getBulletLoadedState() {
		return this.state;
	}

	public boolean hasBulletProperState() {
		return (isBulletLoaded() ^ !isBulletLoaded());
	}

	public boolean isBulletLoaded() {
		return (this.getBulletLoadedState() == BulletState.LOADED);
	}

	public void setBulletLoaded(Ship ship) {
		assert (!this.isEntityTerminated() && !ship.isEntityTerminated());
		
		this.setBulletLoadedState(BulletState.LOADED);
		this.setEntityFree();
		this.setBulletShip(ship);
		this.setBulletSourceShip(null);
	}

	public void setBulletLoadedState(BulletState state) {
		if (state == null)
			throw new IllegalStateException();
		
		else
			this.state = state;
	}

	public void setBulletNotLoaded(Ship ship) {
		assert (!this.isEntityTerminated());
		
		this.setBulletLoadedState(BulletState.NOTLOADED);
		this.setBulletShip(null);
		this.setBulletSourceShip(ship);
	}

	public void Terminate() {
		if (this.isEntityFree())
			setEntityState(State.TERMINATED);
		
		else if (this.isEntityInWorld()) {
			this.getEntityWorld().removeEntityFromWorld(this);
			setEntityState(State.TERMINATED);
		}
	}
	

	/// CONNECTIONS WITH OTHER CLASSES ///
	
	private Ship ship = null;
	private Ship source_ship = null;
	

}




