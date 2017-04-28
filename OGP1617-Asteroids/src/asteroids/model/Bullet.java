package asteroids.model;

import asteroids.part2.CollisionListener;

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
 * @invar   The maximum amount of bounces is valid.
 * 		  |	isValidMaximumBulletBounce(this.getMaximumBulletBounce)
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
	 * @param   maximummBulletBounce
	 * 			The maximum amount a bullet can bounce off a boundary.
	 * 
	 * @effect  A new entity will be made via the constructor of Entity.
	 * 		  	@see implementation
	 * @effect  The maximumBulletBounce will be set.
	 * 			|setMaximumBulletBounce(maximumBulletBounce)
	 */
	private Bullet(double positonX, double positionY, double velocityX, double velocityY, double radius, double orientation,
			double mass, double maxVelocity, double density,double maximumBulletBounce) {
		super(positonX, positionY, velocityX, velocityY, radius, orientation, mass, maxVelocity, density);
		setMaximumBulletBounce(maximumBulletBounce);
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
				Entity.getDefaultMaxVelocity(), getDefaultBulletDensity(),getDefaultMaximumBulletBounce());
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

	/// PROPERTIES///
	
	private double maximum_bullet_bounce;
	
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
	/**
	 * The default maximum amount of bounces.
	 * @return The default amount.
	 * 			@see implementation
	 */
	private static double getDefaultMaximumBulletBounce(){
		return 2;
	}
		
	
	/// GETTERS ///
	
	/**
	 * Return the amount of bounces the bullet already underwent.
	 * 
	 * @return 	The amount of bounces.
	 * 			@see implementation
	 */
	public int getAmountOfBounces() {
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

	
	/**
	 * Return the maximum amount of bounces a bullet can do.
	 * @return the amount
	 * 			@see implementation
	 */
	private double getMaximumBulletBounce(){
		return this.maximum_bullet_bounce;
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
	 * Set the maximum amount of bounces the bullet can do to given amount.
	 * 
	 * @param 	maxBounce
	 * 			The new maximum amount of bounces.
	 * 
	 * @post 	The new amount of bounces will be equal to the given amount. if maxBounce isn't valid, the default value will be used.
	 * 		  	@see implementation	
	 */
	private void setMaximumBulletBounce(double maxBounce){
		if (isValidMaximumBulletBounce(maxBounce))
			maximum_bullet_bounce = maxBounce;
		else
			maximum_bullet_bounce = getDefaultMaximumBulletBounce();
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
	protected void setBulletLoadedState(BulletState state) throws IllegalStateException {
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
	 * @effect	The bullet's loaded state will be set to not loaded, the source ship to ship, the bullet's ship to null and the amount of bounces to 0.
	 * 			@see implementation
	 */
	protected void setBulletNotLoaded(Ship ship) {
		assert (!this.isEntityTerminated());
		
		this.setBulletLoadedState(BulletState.NOT_LOADED);
		this.setBulletShip(null);
		this.setBulletSourceShip(ship);
		this.setAmountOfBounces(0);
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
	 * @post 	If the density is valid, the new density will be equal to the given density. Otherwise, it will be equal to the default density.
	 * 		 	@see implementation
	 */
	protected void setEntityDensity(double density) {
		if (this.isValidDensity(density))
			this.density = density;
		else
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
	/**
	 * Checks if a maximum amount of bounces is valid.
	 * @param maxBounce
	 * 			The amount that has to be checked.
	 * @return true if maxbounce is nonnegative, false in all other cases.
	 * 			@see implementation
	 */
	private boolean isValidMaximumBulletBounce(double maxBounce){
		return maxBounce >= 0;
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
	 * @effect 	The bullet's state will be set on terminated.If the bullet was in a world, it will 
	 * 			be removed from this world. If it was loaded, it will be removed.
	 * 			@see implementation
	 */
	public void Terminate() {
		if (this.isBulletLoaded())
			this.getBulletShip().removeBulletFromShip(this);
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

	///COLLISIONS///
	/**
	 * Let a bullet collide with the boundaries of the world.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 
	 * @param collisionPosition
	 * 			An array that contains the x- and y-value of the position where the collision will happen.
	 * @param defaultEvolvingTime
	 * 			The time until the collision will happen.
	 * @param collisionListener
	 * 			A variable used to visualize the explosions.
	 * 
	 * 
	 * @effect 	If the bullet has bounced more than two times, it will be terminated.
	 * 			|if (counter >= 2)
	 * 			@see implementation
	 * @effect 	The bullet's amount of bounces will be incremented by 1. If the boundary was horizontal, 
	 * 			the y-velocity changes sign. If the boundary was vertical, the x-velocity changes sign.
	 * 			After this, the positionlist in world will be updated.
	 * 			|else
	 * 			@see implementation
	 */
	@Override
	protected void entityAndBoundaryCollide(double[] collisionPosition,double defaultEvolvingTime,CollisionListener collisionListener) {
		// 'counter' will count how many times the bullet has bounced off the boundaries of the world.
				int counter = this.getAmountOfBounces();

				// When the counter reaches 3, the bullet will be terminated.
				if (counter >= getMaximumBulletBounce()){
					if (collisionListener != null){
						double collisionPositionX = collisionPosition[0];
						double collisionPositionY = collisionPosition[1];
						collisionListener.boundaryCollision(this, collisionPositionX, collisionPositionY);}
					this.Terminate();}

				// If 'counter' is (strict) less than 3, the bullet will bounce against the boundary.
				else {
					this.setAmountOfBounces(counter + 1);
					
					double VelocityX = this.getEntityVelocityX();
					double VelocityY = this.getEntityVelocityY();
					//The bullet collides in one of the corners
					if (collideHorizontalBoundary(this, collisionPosition) && collideVerticalBoundary(this, collisionPosition) )
						this.setEntityVelocity(-VelocityX, -VelocityY);
					// The bullet will collide with an horizontal boundary.
					else if (collideHorizontalBoundary(this, collisionPosition))
						this.setEntityVelocity(VelocityX, -VelocityY);

					// The bullet will collide with an vertical boundary.
					else if (collideVerticalBoundary(this, collisionPosition))
						this.setEntityVelocity(-VelocityX, VelocityY);
					World world = this.getEntityWorld();
					world.updatePositionListAfterCollision(this, defaultEvolvingTime);
				}
	}
	/**
	 * A method that resolves the collision between a ship and a bullet.
	 * @param ship
	 * 			The ship that will collide with the entity where the method is invoked on.
	 * @param collisionPosition
	 * 			An array that contains the x- and y-value of the position where the collision will happen.
	 * @param defaultEvolvingTime
	 * 			The time until the collision will happen.
	 * @param collisionListener
	 * 			A variable used to visualize the explosions.
	 * @effect the collision will be resolved by using the entityAndBulletCollide method on ship.
	 * 			@see implementation
	 */
	@Override
	protected void entityAndShipCollide(Ship ship,double[] collisionPosition,double defaultEvolvingTime,CollisionListener collisionListener) {
		ship.entityAndBulletCollide(this,collisionPosition,collisionListener);
		
	}

}




