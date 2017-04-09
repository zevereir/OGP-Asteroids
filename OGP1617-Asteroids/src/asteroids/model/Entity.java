package asteroids.model;


import be.kuleuven.cs.som.annotate.*;
 
/**
 * A class that describes and modifies all the entities. An entity can be a ship or bullet.
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
public abstract class Entity {

	/// CONSTRUCTOR /// 
	
	/**
	 * Initialize an entity; bullet or ship, with the given parameters. 
	 * 
	 * @param 	positionX
	 * 			The x-value of the entity's position.
	 * @param 	positionY
	 * 			The y-value of the entity's position.
	 * @param 	velocityX
	 * 			The x-value of the entity's velocity.
	 * @param 	velocityY
	 * 			The y-value of the entity's velocity.
	 * @param 	radius
	 * 			The radius of the entity.
	 * @param 	orientation
	 * 			The orientation of the entity.
	 * @param 	mass
	 * 			The mass of the entity.
	 * @param 	maxvelocity
	 * 			The maximum total velocity of the entity.
	 * @param 	density
	 * 			The density of the entity.
	 * 
	 * @effect 	The properties will be set on their given values.
	 * 			@see implementation
	 * 			
	 ***/
	protected Entity(double positionX, double positionY, double velocityX, double velocityY, double radius,
			double orientation, double mass, double maxVelocity, double density) {
		setEntityRadius(radius);
		setEntityOrientation(orientation);
		setEntityMaxVelocity(maxVelocity);
		setEntityPosition(positionX, positionY);
		setEntityVelocity(velocityX, velocityY);
		setEntityDensity(density);
		setEntityMass(mass);
	}

	
	/// BASIC PROPERTIES ///
	
	protected Position position = new Position();
	protected Velocity velocity = new Velocity();
	protected double radius;
	protected double orientation;
	protected double max_velocity;
	protected double density;
	protected double mass;

	
	/// CONSTANTS ///
	
	private final static double SPEED_OF_LIGHT = 300000;
	public final static double OMEGA = 0.99;
	public final static double BETA = 1.01;

	
	/// DEFAULTS ///

	/**
	 * Returns the default maximum velocity.
	 * 
	 * @return 	The default maximum velocity.
	 * 			@see implementation
	 */
	public static double getDefaultMaxVelocity() {
		return SPEED_OF_LIGHT;
	}

	/**
	 * Return the default orientation.
	 * 
	 * @return 	The default orientation.
	 * 			@see implementation
	 */
	@Immutable
	public static double getDefaultOrientation() {
		return 0;
	}

	/**
	 * Return the default x-position.
	 * 
	 * @return 	The default x-position.
	 * 			@see implementation
	 */
	@Immutable
	public static double getDefaultPositionX() {
		return 0;
	}

	/**
	 * Return the default y-position.
	 * 
	 * @return 	The default y-position.
	 * 			@see implementation
	 */
	@Immutable
	public static double getDefaultPositionY() {
		return 0;
	}

	/**
	 * Return the default x-velocity.
	 * 
	 * @return 	The default x-velocity.
	 * 			@see implementation
	 */
	@Immutable
	public static double getDefaultVelocityX() {
		return 0;
	}

	/**
	 * Return the default y-velocity.
	 * 
	 * @return 	The default y-velocity.
	 * 			@see implementation
	 */
	@Immutable
	public static double getDefaultVelocityY() {
		return 0;
	}

	
	/// GETTERS ///

	/**
	 * Calculate the position, if there is one, of the collision between two
	 * entities.
	 * 
	 * @param  	otherEntity
	 *         	The other entity.
	 *            
	 * @return 	Null if the time until the collision is positive infinity.
	 * 			@see implementation
	 * @return	The position of collision, which  is calculated by moving the ships
	 *         	at their respective velocities for the time until collision. 
	 *         	delta_x is the difference of the x-coordinates of the two ships when they
	 *         	are on their collision positions. delta_y is the difference of the
	 *         	y-coordinates at the same moment. 
	 *         	omega is the angle between the delta_y (vertical) and the connection
	 *         	between the two centers.
	 *			The actual position_colliside will be calculated using omega, the radius and the center
	 *			of the entity the method is invoked on (this) at the moment it will collide.
	 *        	@see implementation
	 */
	public double[] getCollisionPosition(Entity entity) {
		double position1X = this.getEntityPositionX();
		double position1Y = this.getEntityPositionY();
		double position2X = entity.getEntityPositionX();
		double position2Y = entity.getEntityPositionY();
		
		double velocity1X = this.getEntityVelocityX();
		double velocity1Y = this.getEntityVelocityY();
		double velocity2X = entity.getEntityVelocityX();
		double velocity2Y = entity.getEntityVelocityY();
		
		double radius1 = this.getEntityRadius();

		double time_till_overlapping = this.getTimeToCollision(entity);

		if (time_till_overlapping == Double.POSITIVE_INFINITY)
			return null;

		else {
			double collidingPosition1X = position1X + velocity1X * time_till_overlapping;
			double collidingPosition1Y = position1Y + velocity1Y * time_till_overlapping;
			double collidingPosition2X = position2X + velocity2X * time_till_overlapping;
			double collidingPosition2Y = position2Y + velocity2Y * time_till_overlapping;
			
			double delta_x = (collidingPosition2X - collidingPosition1X);
			double delta_y = (collidingPosition2Y - collidingPosition1Y);

			double omega;

			if (delta_x > 0)
				omega = Math.atan(delta_y / delta_x);
			
			else if (delta_x == 0 && delta_y > 0) 
				omega = Math.PI / 2;
			
			else if (delta_x == 0 && delta_y < 0) 
				omega = 3 * Math.PI / 2;
			
			else
				omega = Math.atan(delta_y / delta_x) + Math.PI;

			double[] position_collide = { collidingPosition1X + radius1 * Math.cos(omega),
					collidingPosition1Y + radius1 * Math.sin(omega) };
			
			return position_collide;
		}
	}

	/**
	 * Calculate the distance between two entities.
	 * 
	 * @param	otherEntity
	 *			The other entity.
	 *            
	 * @return	If this (the entity the method is invoked on) and otherEntity are the
	 *			same entity, the distance between equals zero. 
	 *			@see implementation
	 * @return	The distance between the two entities if they're not the same. This
	 *			is calculated by subtracting the sum of the radii of the entities
	 *			from the distance between the centers. 
	 *			@see implementation
	 */
	public double getDistanceBetween(Entity entity) {
		if (this.equals(entity))
			return 0;

		final double position1X = this.getEntityPositionX();
		final double position1Y = this.getEntityPositionY();
		final double position2X = entity.getEntityPositionX();
		final double position2Y = entity.getEntityPositionY();
		
		final double radius1 = this.getEntityRadius();
		final double radius2 = entity.getEntityRadius();
		final double total_radius = radius1 + radius2;
		
		final double delta_x = Math.abs(position1X - position2X);
		final double delta_y = Math.abs(position1Y - position2Y);
		final double distance_centers = getEuclidianDistance(delta_x, delta_y);
		final double distance = distance_centers - OMEGA * total_radius;

		return distance;
	}

	/**
	 * Returns the entity's density.
	 * 
	 * @return 	The density.
	 * 			@see implementation
	 */
	public double getEntityDensity() {
		return this.density;
	}

	/**
	 * Returns the entity's mass.
	 * 
	 * @return 	The mass.
	 * 			@see implementation in the abstract methods
	 */
	public abstract double getEntityMass();

	/**
	 * Returns the entity's maximum total velocity.
	 * 
	 * @return 	The maximum velocity.
	 * 			@see implementation
	 */
	public double getEntityMaxVelocity() {
		return this.max_velocity;
	}

	/**
	 * Returns the entity's orientation.
	 * 
	 * @return 	The orientation.
	 * 			@see implementation
	 */
	public double getEntityOrientation() {
		return this.orientation;
	}
	
	/**
	 * Returns the entity's position.
	 * 
	 * @return 	The position of the entity.
	 * 			@see implementation
	 */
	public double[] getEntityPosition() {
		return this.position.getPositionArray();
	}

	/**
	 * Returns the entity's x-position value.
	 * 
	 * @return 	The x-position of the position.
	 * 			@see implementation
	 */
	public double getEntityPositionX() {
		return this.position.getPositionX();
	}

	/**
	 * Returns the entity's y-position value.
	 * 
	 * @return 	The y-position of the position.
	 * 			@see implementation
	 */
	public double getEntityPositionY() {
		return this.position.getPositionY();
	}

	/**
	 * Returns the entity's radius.
	 * 
	 * @return 	The radius.
	 * 			@see implementation
	 */
	public double getEntityRadius() {
		return this.radius;
	}

	/**
	 * Returns the entity's velocity.
	 * 
	 * @return 	The velocity of the entity.
	 * 			@see implementation
	 */
	public double[] getEntityVelocity() {
		return this.velocity.getVelocityArray();
	}

	/**
	 * Returns the entity's x-velocity value.
	 * 
	 * @return 	The x-value of the velocity.
	 * 			@see implementation
	 */
	public double getEntityVelocityX() {
		return this.velocity.getVelocityX();
	}

	/**
	 * Returns the entity's y-velocity value.
	 * 
	 * @return the y-value of the velocity.
	 * 			@see implementation
	 */
	public double getEntityVelocityY() {
		return this.velocity.getVelocityY();
	}
	
	/**
	 * returns the world where the entity's in.
	 * 
	 * @return 	The world.
	 * 			@see implementation
	 */
	public World getEntityWorld() {
		return this.world;
	}
	
	/**
	 * Returns the hypotenuse of the triangle.
	 * 
	 * @param 	a
	 *          The horizontal/vertical side of the triangle.
	 * @param 	b
	 *          The vertical/horizontal side of the triangle.
	 * 
	 * @return	The Euclidian distance: the square root of the sum of a squared and b squared. 
	 *			@see implementation
	 */
	public static double getEuclidianDistance(double a, double b) {
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}

	/**
	 * Returns the position where the entity will collide with the boundary.
	 * 
	 * @return	Null if the time till collision equals POSITIVE_INFINITY.
	 * 			@see implementation
	 * @return	The collision position of the given entity when it's colliding with a boundary of the world.
	 * 			This would be after the given entity moved over a time-laps of getTimeCollisionBoundary().
	 *		  | move(getTimeCollisionBoundary)
	 * 		  | result[0] == (getEntityPosition + radius) || 
	 * 		  |	  result[1] == (getEntityPosition + radius) || 
	 * 		  |	  result[0] == (getEntityPosition - radius) || 
	 * 		  |	  result[1] == (getEntityPosition - radius)  
	 * 
	 * @throws	IllegalAccesError
	 * 			It should be impossible, once in the else-statement, to not-touch with one of the
	 * 			boundaries of the world. Even though this is the case, the IlligalAccesError will
	 * 			be thrown.
	 * 			@see implementation
	 */

	public double[] getPositionCollisionBoundary() {
		double time = getTimeCollisionBoundary();
		double collidingPositionX = 0;
		double collidingPositionY = 0;

		if (time == Double.POSITIVE_INFINITY)
			return null;
		
		else {
			double positionX = this.getEntityPositionX();
			double positionY = this.getEntityPositionY();
		
			double velocityX = this.getEntityVelocityX();
			double velocityY = this.getEntityVelocityY();
			
			double width = this.getEntityWorld().getWorldWidth();
			double height = this.getEntityWorld().getWorldHeight();
			
			double radius = this.getEntityRadius();

			collidingPositionX = Math.abs(positionX + time * velocityX);
			collidingPositionY = Math.abs(positionY + time * velocityY);

			// Right boundary
			if ((collidingPositionX + OMEGA * radius) <= width && width <= (collidingPositionX + BETA * radius))
				collidingPositionX += radius;
			
			// Left boundary
			else if ((collidingPositionX - BETA * radius) <= 0 && 0 <= (collidingPositionX - OMEGA * radius))
				collidingPositionX -= radius;
			
			// Upper boundary
			else if ((collidingPositionY + OMEGA * radius) <= height && height <= (collidingPositionY + BETA * radius))
				collidingPositionY += radius;
			
			// Lower boundary
			else if ((collidingPositionY - BETA * radius) <= 0 && 0 <= (collidingPositionY - OMEGA * radius))
				collidingPositionY -= radius;
			
			// If none of the above statements were correct, there was a fault!
			else {
				throw new IllegalAccessError();
			}
		}
		
		double[] new_position = { collidingPositionX, collidingPositionY };
		return new_position;
	}

	/**
	 * returns the state the entity is in.
	 * 
	 * @return 	The state.
	 * 			@see implementation
	 */
	public State getState() {
		return this.state;
	}

	/**
	 * get the minimum time until the entity collides with a boundary.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 * 
	 * @return 	Positive infinity if the entity has no world or a proper state. 
	 * 			@see implementation
	 * @return 	The time till collision with one of the boundaries of the world.
	 * 			If the entity has no velocity, the entity will not collide with a boundary. In this case 
	 * 			POSITIVE_INFINITY will be returned.
	 *			@see implementation
	 */
	public double getTimeCollisionBoundary() {
		if (!this.isEntityInWorld() && this.hasEntityProperState())
			return Double.POSITIVE_INFINITY;
		
		else {
			double positionX = this.getEntityPositionX();
			double positionY = this.getEntityPositionY();
		
			double velocityX = this.getEntityVelocityX();
			double velocityY = this.getEntityVelocityY();
			
			double width = this.getEntityWorld().getWorldWidth();
			double height = this.getEntityWorld().getWorldHeight();
			
			double radius = this.getEntityRadius();

			double distanceTillRightBoundary = (width - (positionX + radius));
			double distanceTillLeftBoundary = (positionX - radius);
			double distanceTillUpperBoundary = (height - (positionY + radius));
			double distanceTillLowerBoundary = (positionY - radius);

			double timeCollisionRight = Double.POSITIVE_INFINITY;
			double timeCollisionLeft = Double.POSITIVE_INFINITY;
			double timeCollisionUp = Double.POSITIVE_INFINITY;
			double timeCollisionDown = Double.POSITIVE_INFINITY;

			// Calculate the time, if so, till the collision will happen with each boundary.
			if (velocityX > 0)
				timeCollisionRight = Math.abs(distanceTillRightBoundary / velocityX);
			
			if (velocityX < 0)
				timeCollisionLeft = Math.abs(distanceTillLeftBoundary / velocityX);
			
			if (velocityY > 0)
				timeCollisionUp = Math.abs(distanceTillUpperBoundary / velocityY);
			
			if (velocityY < 0)
				timeCollisionDown = Math.abs(distanceTillLowerBoundary / velocityY);

			// Check the time until the entity will collide with the first boundary.
			if (Math.min(timeCollisionLeft, timeCollisionRight) < Math.min(timeCollisionUp, timeCollisionDown))
				return Math.min(timeCollisionLeft, timeCollisionRight);
			
			else if (Math.min(timeCollisionLeft, timeCollisionRight) > Math.min(timeCollisionUp, timeCollisionDown))
				return Math.min(timeCollisionUp, timeCollisionDown);
			
			// If the entity it's velocity equals zero, it will never collide with a boundary.
			else
				return Double.POSITIVE_INFINITY;
		}
	}

	/**
	 * Calculate the time until, if ever, the first collision between two entities will take place.
	 * 
	 * @param	otherEntity
	 *			The other entity.
	 * 
	 * @post	The amount of seconds until the collision will take place is calculated. 
	 * 			This means that if the two entities travel this amount of time at their
	 * 			respective velocity, the distance between them will equal 0 (they collide). 
	 *		  | this.move(getTimeToCollision(Entity otherEntity)) 
	 *		  | otherEntity.move(getTimeToCollision(Entity otherEntity))
	 *		  | this.getDistanceBetween(otherEntity) == 0
	 * 
	 * @return	If the collision won't take place, Double.POSITIVE_INFINITY will be
	 *			returned. If the collision happens, the time until it happens is returned.
	 *         
	 * @throws	IllegalArgumentException
	 *			If the two entities overlap.
	 *		  | (this.overlap(otherEntity))
	 */
	public double getTimeToCollision(Entity entity) {
		if ((!this.isEntityInWorld() && this.hasEntityProperState())
				|| (!entity.isEntityInWorld() && entity.hasEntityProperState()))
			throw new IllegalArgumentException();

		double position1X = this.getEntityPositionX();
		double position1Y = this.getEntityPositionY();
		double position2X = entity.getEntityPositionX();
		double position2Y = entity.getEntityPositionY();

		double velocity1X = this.getEntityVelocityX();
		double velocity1Y = this.getEntityVelocityY();
		double velocity2X = entity.getEntityVelocityX();
		double velocity2Y = entity.getEntityVelocityY();

		double radius1 = this.getEntityRadius();
		double radius2 = entity.getEntityRadius();
		double total_radius = (radius1 + radius2);

		double delta_rX = position2X - position1X;
		double delta_rY = position2Y - position1Y;

		double delta_vX = velocity2X - velocity1X;
		double delta_vY = velocity2Y - velocity1Y;

		double delta_r_r = Math.pow(delta_rX, 2) + Math.pow(delta_rY, 2);
		double delta_v_v = Math.pow(delta_vX, 2) + Math.pow(delta_vY, 2);
		double delta_v_r = (delta_rX * delta_vX + delta_rY * delta_vY);

		double d = Math.pow(delta_v_r, 2) - delta_v_v * (delta_r_r - Math.pow(total_radius, 2));

		if (this.overlap(entity))
			throw new IllegalArgumentException();

		else if (delta_v_r >= 0)
			return Double.POSITIVE_INFINITY;

		else if (d <= 0)
			return Double.POSITIVE_INFINITY;

		else
			return Math.abs((delta_v_r + Math.sqrt(d)) / delta_v_v);
	}
	
	
	/// SETTERS ///

	/**
	 * Set the density of the entity.
	 * 
	 * @param 	density
	 * 			The new density.
	 * 
	 * @post 	The new density will be equal to the given density.
	 * 			@see implementation in the abstract methods
	 */
	public abstract void setEntityDensity(double density);

	/**
	 * set the entity's state to NO_WORLD.
	 * 
	 * @pre 	The entity is not terminated.
	 * 			@see implmentation
	 * 
	 * @effect 	The entity's world will be set on null and the state to NO_WORLD.
	 * 			@see implementation
	 */
	public void setEntityFree() {
		assert (!this.isEntityTerminated());
		this.setEntityState(State.NO_WORLD);
		this.setEntityWorld(null);
	}

	/**
	 * Set the entity's state to IN_WORLD.
	 * 
	 * @param 	world
	 * 			The world the entity will be set in.
	 * 
	 * @pre 	The entity is not terminated.
	 * 			@see implementation
	 * 
	 * @effect 	The state changes to IN_WORLD and its world will be set to the given world.
	 * 			@see implementation
	 */
	public void setEntityInWorld(World world) {
		assert (!this.isEntityTerminated());
		this.setEntityState(State.IN_WORLD);
		this.setEntityWorld(world);
	}

	/**
	 * Set the mass of the entity.
	 * 
	 * @param 	mass
	 * 			The new mass.
	 * 
	 * @post 	The new mass will be equal to the given mass.
	 * 			@see implementation in the abstract methods
	 */
	public abstract void setEntityMass(double mass);

	/**
	 * Set the maximum total velocity.
	 * 
	 * @param 	newMaxVelocity
	 * 			The new maximum velocity.
	 * 
	 * @post 	The maximum velocity will be equal to the given velocity, when it's not valid, 
	 * 			it will be set to the speed of the light.
	 * 			@see implementation
	 */
	public void setEntityMaxVelocity(double newMaxVelocity) {
		if ((0 < newMaxVelocity) && (newMaxVelocity < SPEED_OF_LIGHT))
			this.max_velocity = newMaxVelocity;

		else
			this.max_velocity = SPEED_OF_LIGHT;
	}
	
	/**
	 * Gives the entity a new orientation.
	 * 
	 * @param 	orientation
	 *          The new orientation in radians.
	 * 
	 * @pre 	The given orientation is a valid orientation for the entity. 
	 * 			@see implementation
	 * 
	 * @post 	The new orientation will be equal to the given orientation.
	 *        | new.getEntityOrientation() == orientation
	 */
	public void setEntityOrientation(double orientation) {
		assert isValidOrientation(orientation);

		this.orientation = orientation;
	}
	
	/**
	 * Set the position of an entity.
	 * 
	 * @param 	positionX
	 * 			The new x-position.
	 * @param 	positionY
	 * 			The new y-position.
	 * 
	 * @post	The position will be set on the new position.
	 * 		  | new.getEntityPositionX() == positionX
	 * 		  | new.getEntityPositionY() == positionY
	 * 
	 * @throws 	IllegalArgumentException
	 * 			if the position is not valid.
	 * 			@see implementation
	 */
	public void setEntityPosition(double positionX, double positionY) {
		if (!isValidPosition(positionX, positionY))
			throw new IllegalArgumentException();

		this.position.setPositionX(positionX);
		this.position.setPositionY(positionY);
	}

	/**
	 * Set the radius of an entity.
	 * 
	 * @param 	radius
	 * 			The new radius.
	 * 
	 * @post	The new radius will be equal to the given radius.
	 * 		  | new.getEntityRadius() == radius
	 * 
	 * @throws 	IllegalArgumentException
	 * 			if the radius is not valid.
	 * 			@see implementation
	 */
	public void setEntityRadius(double radius) {
		if (isValidRadius(radius))
			this.radius = radius;
		
		else
			throw new IllegalArgumentException();
	}

	/**
	 * set the entity's state.
	 * 
	 * @param 	state
	 * 			The new entity's state.
	 * 
	 * @post 	The new state will be equal to the given state.
	 * 		  | new.getState() == state
	 * 
	 * @throws 	IllegalArgumentException
	 * 			If the given state is null.
	 * 			@see implementation
	 */
	public void setEntityState(State state) {
		if (state == null)
			throw new IllegalArgumentException();
		
		else
			this.state = state;
	}

	/**
	 * Set the velocity of the entity on the given velocity.
	 * 
	 * @param 	velocityX
	 * 			The new x-value of the velocity.
	 * @param 	velocityY
	 * 			the new y-value of the velocity.
	 * 
	 * @post 	The new velocity will be equal to the given velocity
	 * 		  | new.getEntityVelocityX() == velocityX
	 * 		  | new.getEntityVelocityY() == velocityY
	 * @post 	If one or both of the given parameters is not a number, the respective parameter is set to zero.
	 * 			@see implementation
	 * @post 	If the total velocity is greater than the maximum total velocity. The maximum velocity will
	 * 			be mapped with the orientation.
	 * 			@see implementation.
	 */
	public void setEntityVelocity(double velocityX, double velocityY) {
		if (!isValidVelocity(velocityX, velocityY)) {
			if (Double.isNaN(velocityX))
				velocityX = 0;

			if (Double.isNaN(velocityY))
				velocityY = 0;

			// Scale the velocity if it exceeds the limit.
			if (getEuclidianDistance(velocityX, velocityY) > this.getEntityMaxVelocity()) {
				double orientation = this.getEntityOrientation();

				velocityX = Math.cos(orientation) * this.getEntityMaxVelocity();
				velocityY = Math.sin(orientation) * this.getEntityMaxVelocity();
			}
		}

		this.velocity.setVelocityX(velocityX);
		this.velocity.setVelocityY(velocityY);
	}

	/**
	 * Set a world to the entity.
	 * 
	 * @param 	world
	 * 			The new world.
	 * 
	 * @post 	The new world will be equal to the given world.
	 * 		  | new.getEntityWorld() == world
	 */
	public void setEntityWorld(World world) {
		this.world = world;
	}

	/**
	 * Set the position without checking if the given position is a valid position.
	 * 
	 * @param 	x
	 * 			The new x-position.
	 * @param 	y
	 * 			The new y-position.
	 * 
	 * @post 	The new position will be equal to the given values.
	 * 		  | new.getEntityPosition == {x, y}
	 */
	public void setPositionWithoutChecking(double positionX, double positionY) {
		this.position.setPositionX(positionX);
		this.position.setPositionY(positionY);
	}


	/// CHECKERS ///

	/**
	 * Checks if an entity as a proper state.
	 * 
	 * @return 	The boolean that checks whether the entity's state is IN_WORLD, NO_WORLD or Terminated.
	 * 			@see implementation
	 */
	public boolean hasEntityProperState() {
		return (isEntityInWorld() ^ isEntityFree() ^ isEntityTerminated());
	}

	/**
	 * Checks if an entity has the NO_WORLD state.
	 * 
	 * @return 	The boolean that checks if the entity has the NO_WORLD state.
	 * 			@see implementation
	 */
	public boolean isEntityFree() {
		return this.getState() == State.NO_WORLD;
	}

	/**
	 * Checks if an entity has the IN_WORLD state.
	 * 
	 * @return 	The boolean that checks if the entity has the IN_WORLD state.
	 * 			@see implementation
	 */
	public boolean isEntityInWorld() {
		return (this.getState() == State.IN_WORLD);
	}

	/**
	 * Checks if an entity has the Terminated state.
	 * 
	 * @return 	The boolean that checks if the entity has the Terminated state.
	 * 			@see implementation
	 */
	public boolean isEntityTerminated() {
		return this.getState() == State.TERMINATED;
	}

	/**
	 * Checks if a given density is valid.
	 * 
	 * @param 	density
	 * 			The density that has to be checked.
	 * 
	 * @return 	The boolean that checks whether the given density is valid or not.
	 * 			@see implementation in the abstract methods
	 */
	public abstract boolean isValidDensity(double density);

	/**
	 * Checks if a given mass is valid.
	 * 
	 * @param 	mass
	 * 			The mass that has to be checked.
	 * 
	 * @return 	The boolean that checks of the mass is valid.
	 * 			@see implementation in the abstract methods
	 */
	public abstract boolean isValidMass(double mass);

	/**
	 * Checks if a given orientation is valid.
	 * 
	 * @param 	orientation
	 * 			The orientation that has to be checked.
	 * 
	 * @return	the boolean that checks if an orientation is valid.
	 * 			@see implementation
	 */
	public boolean isValidOrientation(double orientation) {
		return ((0 <= orientation) && (orientation < 2 * Math.PI));
	}

	/**
	 * Checks whether a position is valid or not.
	 * 
	 * @param 	positionX
	 * 			The x-value of the position that has to be checked.
	 * @param 	positionY
	 * 			the y-value of the position that has to be checked.
	 * 
	 * @return 	A boolean (true or false) that checks whether the entity fits in its world.
	 * 			@see implementation
	 */
	public boolean isValidPosition(double positionX, double positionY) {
		if ((Double.isNaN(positionX)) || (Double.isNaN(positionY)))
			return false;

		if ((this.getEntityWorld() != null))
			return this.entityFitsInWorld(this.getEntityWorld());

		return true;
	}

	/**
	 * Checks if a given radius is valid.
	 * 
	 * @param 	radius
	 * 			The radius that has to be checked.
	 * 
	 * @return 	The boolean that checks if the radius is valid.
	 * 			@see implementation in the abstract methods
	 */
	public abstract boolean isValidRadius(double radius);

	/**
	 * Checks if a velocity is valid.
	 * 
	 * @param 	velocityX
	 * 			The x-value of the velocity that has to be checked.
	 * @param 	velocityY
	 * 			The y-value of the velocity that has to be checked.
	 * 
	 * @return 	False if one of the two values is not a number or if the total velocity
	 * 			(calculated with the formula of the Euclidian distance) exceeds the maximum velocity.
	 * 			@see implementation
	 */
	public boolean isValidVelocity(double velocityX, double velocityY) {
		if ((Double.isNaN(velocityX)) || (Double.isNaN(velocityY)))
			return false;

		if (getEuclidianDistance(velocityX, velocityY) > this.getEntityMaxVelocity())
			return false;

		return true;
	}

	
	/// HELP FUNCTIONS ///
	
	/**
	 * Checks if an entity could fit in a given world.
	 * 
	 * @param 	world
	 * 			The world where the entity will be checked in.
	 * 
	 * @return 	A boolean that checks if the entity can be in the world.
	 * 			@see implementation			
	 */
	public boolean entityFitsInWorld(World world) {
		if (!this.entityLiesInBoundaries(world) || this.entityOverlappingInWorld(world) != null)
			return false;
		
		return true;
	}

	/**
	 * Checks whether an entity lies in the boundaries of the world.
	 * 
	 * @param 	world
	 * 			The world where the entity will be checked in.
	 * 
	 * @return 	The boolean that checks if a ship lies in the boundaries of the world.
	 * 			@see implementation
	 */
	public boolean entityLiesInBoundaries(World world) {
		double radius = this.getEntityRadius();
		double upper_bound = (world.getWorldHeight() - OMEGA * radius);
		double lower_bound = OMEGA * radius;
		double right_bound = (world.getWorldWidth() - OMEGA * radius);
		double left_bound = OMEGA * radius;

		double positionX = this.getEntityPositionX();
		double positionY = this.getEntityPositionY();

		// Check if the entity fits into the boundaries of the world.
		return ((positionX > left_bound) && (right_bound > positionX) && (positionY > lower_bound)
				&& (upper_bound > positionY));
	}

	/**
	 * Checks whether an entity is overlapping with an entity in the given world.
	 * 
	 * @param 	world
	 * 			The world where the entity will be checked in.
	 * 
	 * @return 	The boolean that checks if the entity is overlapping with something in the world.
	 * 			@see implementation
	 */
	public Entity entityOverlappingInWorld(World world) {
		// Check if the entity is not overlapping with another entity.
		for (Object entity : world.getWorldEntities())
			if (this.overlap((Entity) entity) && !this.equals(entity))
				return (Entity) entity;
		
		return null;

	}

	
	/// MOVE ///
	
	/**
	 * Moves the entity over the given time "moveTime".
	 * 
	 * @param 	moveTime
	 * 			The time the entity has to move.
	 * 
	 * @effect 	The entity will be moved.
	 * 			@see implementation in the abstract methods
	 */
	public abstract void move(double moveTime);

	
	/// TERMINATION AND STATES ///
	
	/**
	 * Terminate the entity.
	 * 
	 * @effect 	The entity will be terminated.
	 *			@see implementation in the abstract methods
	 */
	public abstract void Terminate();

	private State state = State.NO_WORLD;

	protected static enum State {
		NO_WORLD, IN_WORLD, TERMINATED;
	}
	

	/// OVERLAP ///

	/**
	 * Returns a boolean saying if the two entities are overlapping.
	 *
	 * @param	otherEntity
	 * 			The other entity.
	 * 
	 * @return	True if the distance between the two entities is negative.
	 *		  | result == (this.getDistanceBetween(otherEntity) < 0)
	 */
	public boolean overlap(Entity entity) {
		if (this.equals(entity))
			return true;

		double distance = this.getDistanceBetween(entity);

		return (distance < 0);
	}

	/// RELATIONS WITH OTHER CLASSES ///
	
	private World world = null;
}
