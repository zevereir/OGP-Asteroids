package asteroids.model;


import be.kuleuven.cs.som.annotate.*;
 
/**
 * A class that describes and modifies all the entities. An entity can be a ship or bullet.
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
 * @version 4th of April - 01u25
 * @authors Sieben Bocklandt and Ruben Broekx

 */
public abstract class Entity {
	
	///CONSTRUCTOR///
	 /**
	 * @param positionX
	 * 			The x-value of the entity's position.
	 * @param positionY
	 * 			The y-value of the entity's position.
	 * @param xVelocity
	 * 			The x-value of the entity's velocity.
	 * @param yVelocity
	 * 			The y-value of the entity's velocity.
	 * @param radius
	 * 			The radius of the entity.
	 * @param orientation
	 * 			The orientation of the entity.
	 * @param mass
	 * 			The mass of the entity.
	 * @param maxvelocity
	 * 			The maximum total velocity of the entity.
	 * @param density
	 * 			The density of the entity.
	 * 
	 * @effect The properties will be set on their given values.
	 * 			@see Implementation
	 * 			
	 ***/
	protected Entity(double positionX, double positionY, double xVelocity, double yVelocity, double radius,double orientation,
			double mass,double maxVelocity,double density) {
		setEntityRadius(radius);
		setEntityOrientation(orientation);
		setEntityMaxVelocity(maxVelocity);
		setEntityPosition(positionX, positionY);
		setEntityVelocity(xVelocity, yVelocity);
		setEntityDensity(density);
		setEntityMass(mass);
	}
	

	///BASIC PROPERTIES///
	protected Position position = new Position();
	protected Velocity velocity = new Velocity();
	protected double radius;
	protected double orientation;
	protected double max_velocity;
	protected double density;
	protected double mass;
	
	///DEFAULTS///
	private final static double SPEED_OF_LIGHT = 300000;

	public final static double OMEGA = 0.99;
	public final static double BETA = 1.01;
	
	/**
	 * Returns the default maximum total velocity.
	 * @return the default maximum velocity
	 * 			@see Implementation
	 */
	@Immutable
	public static double getDefaultMaxVelocity(){
		return SPEED_OF_LIGHT;
	}
	/**
	 * Return the default position.
	 * @return the default position
	 * 			@see Implementation
	 */
	@Immutable
	public static double[] getDefaultPosition() {
		double[] array = {0, 0};
		return array;
	}

	/**
	 * Return the default velocity.
	 * 
	 * @return the default velocity
	 *         @see Implementation
	 */
	@Immutable
	public static double[] getDefaultVelocity() {
		double[] array = {0, 0};
		return array;
	}
	
	/**
	 * Return the default orientation.
	 * 
	 * @return the default orientation
	 * 			@see implementation
	 */
	@Immutable
	public static double getDefaultOrientation() {
		return 0;
	}

	
	///HELP METHODS///
	/**
	 * Returns the total velocity using the euclidian formula.
	 * 
	 * @param xVelocity
	 *            The x-coordinate of the velocity.
	 * @param yVelocity
	 *            The y-coordinate of the velocity.
	 * 
	 * @return The euclidian distance: the square root of the sum of a
	 *         squared and b squared. 
	 *         @see Implementation
	 */
	public static double getEuclidianDistance(double a, double b) {
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}
	/**
	 * Checks if an entity could fit in a given world.
	 * @param world
	 * 			The world where the entity will be checked in.
	 * @return the boolean that checks if the entity can be in the world
	 * 			@see Implementation			
	 */
	public boolean entityFitsInWorld(World world){
		if (!this.entityInBoundaries(world) || this.entityOverlappingInWorld(world)!=null){
			return false;
		}
		return true;
	}
	/**
	 * Checks whether an entity lies in the boundaries of the world.
	 * @param world
	 * 			The world where the entity will be checked in.
	 * @return the boolean that checks if a ship lies in the boundaries of the world
	 * 			@see Implementation
	 */
	public boolean entityInBoundaries( World world){
		double radius = this.getEntityRadius();
		double upper_bound = (world.getWorldHeight() - OMEGA*radius);
		double lower_bound = OMEGA*radius;
		double right_bound = (world.getWorldWidth() - OMEGA*radius);
		double left_bound = OMEGA*radius;
		
		double positionX = this.getEntityPositionX();
		double positionY = this.getEntityPositionY();		
		
		// Check if the entity fits into the boundaries of the world.
		return ((positionX > left_bound) && (right_bound > positionX) && (positionY > lower_bound) && (upper_bound > positionY));	
			
	}
	/**
	 * Checks whether an entity is overlapping with an entity in the given world.
	 * @param world
	 * 			The world where the entity will be checked in.
	 * @return the boolean that checks if the entity is overlapping with something in the world.
	 * 			@see Implementation
	 */
	public Entity entityOverlappingInWorld(World world){
		// Check if the entity is not overlapping with another entity.
		for (Object otherEntity: world.getWorldEntities()){
			if ( this.overlap((Entity)otherEntity) && !this.equals(otherEntity) )
				return (Entity)otherEntity;
		}
		return null;
		
	}

	/// GETTERS ///
	/**
	 * Returns the entity's position.
	 * @return the position of the entity
	 * 			@see Implementation
	 */
	public double[] getEntityPosition(){
		return this.position.getPositionArray();
	}
	/**
	 * Returns the entity's x-position value.
	 * @return the x-value of the position
	 * 			@see implementation
	 */
	public double getEntityPositionX(){
		return this.position.getPositionX();
	}
	/**
	 * Returns the entity's y-position value.
	 * @return the y-value of the position
	 * 			@see implementation
	 */
	public double getEntityPositionY(){
		return this.position.getPositionY();
	}
	/**
	 * Returns the entity's velocity.
	 * @return the velocity of the entity
	 * 			@see Implementation
	 */
	public double[] getEntityVelocity(){
		return this.velocity.getVelocityArray();
	}
	/**
	 * Returns the entity's x-velocity value.
	 * @return the x-value of the velocity
	 * 			@see implementation
	 */
	public double getEntityVelocityX() {
		return this.velocity.getVelocityX();
	}
	/**
	 * Returns the entity's y-velocity value.
	 * @returnthe y-value of the velocity
	 * 			@see implementation
	 */
	public double getEntityVelocityY() {
		return this.velocity.getVelocityY();
	}
	/**
	 * Returns the entity's radius.
	 * @return the radius
	 * 			@see Implementation
	 */
	public double getEntityRadius(){
		return this.radius;
	}
	/**
	 * Returns the entity's orientation.
	 * @return the orientation
	 * 			@see Implementation
	 */
	public double getEntityOrientation(){
		return this.orientation;	
	}
	/**
	 * returns the entity's maximum total velocity.
	 * @return the maximum velocity
	 * 			@see Implementation
	 */
	public double getEntityMaxVelocity(){
		return this.max_velocity;
	}
	/**
	 * Returns the entity's density.
	 * @return the density
	 * 			@see Implementation
	 */
	public double getEntityDensity(){
		return this.density;
	}
	/**
	 * Returns the entity's mass.
	 * @return the mass
	 * 			@see Implementation abstract methods.
	 */
	public abstract double getEntityMass();
	/**
	 * returns the world where the entity's in.
	 * @return the world
	 * 			@see Implementation
	 */
	public World getEntityWorld(){
		return this.world;
	}
	
	/// SETTERS ///
	// --> BEKIJKEN <-- //
	/**
	 * Set the position of an entity when it's not colliding.
	 * @param positionX
	 * 			The new x-position
	 * @param positionY
	 * 			The new y-position.
	 * @postthe the position will be set on the new position
	 * 			|new.getEntityPositionX() == positionX
	 * 			|new.getEntityPositionY() == positionY
	 * @throws IllegalArgumentException
	 * 			if the postion is not valid
	 * 			@see Implementation
	 */
	public void setEntityPosition(double positionX, double positionY) {
		if (!isValidPosition(positionX, positionY)){
			throw new IllegalArgumentException();}
		
		this.position.setPositionX(positionX);
		this.position.setPositionY(positionY);
	}
	/**
	 * Checks whether a position is valid or not.
	 * @param positionX
	 * 			The x-value of the position that has to be checked.
	 * @param positionY
	 * 			the y-value of the position that has to be checked.
	 * @return false if one of the two values is not a number
	 * 			@see Implementation
	 * @return a boolean that checks whether the entity fits in its world
	 * 			@see implementation
	 * 			
	 */
	public boolean isValidPosition(double positionX, double positionY){
		if ((Double.isNaN(positionX)) || (Double.isNaN(positionY)))
			return false;
		
		if ((this.getEntityWorld() != null)) 
			return this.entityFitsInWorld(this.getEntityWorld());
		
		return true;
	}
	/**
	 * Set the velocity of the entity on the given velocity.
	 * @param xVelocity
	 * 			The new x-value of the velocity.
	 * @param yVelocity
	 * 			the new y-value of the velocity.
	 * @post the new velocity will be equal to the given velocity
	 * 			|new.getEntityVelocityX() == xVelocity
	 * 			|new.getEntityVelocityY() == yVelocity
	 * @post if one or both of the given parameters is not a number, the respective parameter is set on 0.
	 * 			@see Implementation
	 * @post if the total velocity is greater than the maximum total velocity. The maximum velocity will
	 * 			be mapped with the orientation.
	 * 			@see Implementation.
	 */
	public void setEntityVelocity(double xVelocity, double yVelocity){
		if (!isValidVelocity(xVelocity, yVelocity)){
			if (Double.isNaN(xVelocity))
				xVelocity = 0;
			if (Double.isNaN(yVelocity))
				yVelocity = 0;
			
			// Scale the velocity if it exceeds the limit.
			if (getEuclidianDistance(xVelocity, yVelocity) > this.getEntityMaxVelocity()) {
				double orientation = this.getEntityOrientation();
				xVelocity = Math.cos(orientation) * this.getEntityMaxVelocity();
				yVelocity = Math.sin(orientation) * this.getEntityMaxVelocity();
			}
		}
	
		this.velocity.setVelocityX(xVelocity);
		this.velocity.setVelocityY(yVelocity);
	}
	/**
	 * Checks if a velocity is valid.
	 * @param xVelocity
	 * 			The x-value of the velocity that has to be checked.
	 * @param yVelocity
	 * 			The y-value of the velocity that has to be checked.
	 * @return false if one of the two values is not a number
	 * 			@see Implementation
	 * @return false if the euclideandistance of the two values is greater than the maximum velocity
	 * 			@see implementation
	 */
	public boolean isValidVelocity(double xVelocity, double yVelocity) {
		if ((Double.isNaN(xVelocity)) || (Double.isNaN(yVelocity)))
			return false;
		
		if (getEuclidianDistance(xVelocity, yVelocity) > this.getEntityMaxVelocity())
			return false;
		
		return true;
	}
	/**
	 * Set the radius of an entity.
	 * @param radius
	 * 			The new radius.
	 * @post	the new radius will be equal to the given radius 
	 * 			|new.getEntityRadius() == radius.
	 * @throws illegalArgumentException
	 * 			if the radius is not valid
	 * 			@see Implementation
	 */
	public void setEntityRadius(double radius) {
		if (isValidRadius(radius))
			this.radius = radius;
		else{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Checks if a givn radius is valid.
	 * @param radius
	 * 			The radius that has to be checked.
	 * @return the boolean that checks if the radius is valid
	 * 			@see Implementation (of the abstract methods)
	 */
	public abstract boolean isValidRadius(double radius);	
	
	/**
	 * Gives the entity a new orientation.
	 * 
	 * @param orientation
	 *            The new orientation in radians.
	 * 
	 * @pre The given orientation is a valid orientation for the entity. 
	 * 			@see Implementation
	 * 
	 * @post The new orientation will be equal to the given orientation.
	 *       |new.getEntityOrientation() == orientation
	 */
	public void setEntityOrientation(double orientation){
		assert isValidOrientation(orientation);
		this.orientation = orientation;
	}
	/**
	 * Checks if a given orientation is valid.
	 * @param orientation
	 * 			The orientation that has to be checked.
	 * @return	the boolean that checks if an orientation is valid
	 * 			@see Implementation
	 */
	public boolean isValidOrientation(double orientation){
		return ( (0 <= orientation) && (orientation < 2 * Math.PI) );		
	}
	
	/**
	 * Set the maximum total velocity.
	 * @param newMaxVelocity
	 * 			The new maximum velocity.
	 * @post the aximum velocity will be equal to the given velocity, when it's not valid, it will be set o the speed of the light.
	 * 			@see Implementation
	 */
	public void setEntityMaxVelocity(double newMaxVelocity){
		if ( (0 < newMaxVelocity) && (newMaxVelocity < SPEED_OF_LIGHT) )
			this.max_velocity = newMaxVelocity;
		else
			this.max_velocity = SPEED_OF_LIGHT;
	}
	/**
	 * Set the density of the entity.
	 * @param density
	 * 			the new density
	 * @post the new density will be equal to the given density
	 * 			@see Implementation (of the abstract methods)
	 */
	public abstract void setEntityDensity(double density);
	/**
	 * Checks if a given density is valid.
	 * @param density
	 * 			The density that has to be checked.
	 * @return the boolean that checks whether the given density is valid or not.
	 * 			@see Implementation (of the abstract methods)
	 */
	public abstract boolean isValidDensity(double density); 
	/**
	 * Set the mass of the entity.
	 * @param mass
	 * 			the new mass
	 * @post the new mass will be equal to the given mass
	 * 			@see Implementation (of the abstract methods)
	 */
	public abstract void setEntityMass(double mass); 
	/**
	 * Checks if a given mass is valid.
	 * @param mass
	 * 			The mass that has to be checked.
	 * @return the boolean that checks of the mass is valid
	 * 			@see Implementation (of the abstract methods)
	 */
	public abstract boolean isValidMass(double mass);
	/**
	 * Set a world to the entity.
	 * @param world
	 * 			The new world.
	 * @post the new world will be equl to the given world
	 * 			|new.getEntityWorld()==world
	 */
	public void setEntityWorld(World world){
		this.world = world;
	}
	
	///MOVE///
	/**
	 * Moves the entity dt seconds.
	 * @param dt
	 * 			The time the entity has to move 
	 * @effect the entity will be moved
	 * 			@see implementation (of the abstract methods)
	 */
	public abstract void move(double dt);
	
	///TERMINATION AND STATES///
	/**
	 * Terminate the entity
	 *@effect the entity will be terminated
	 *			@see implementation (of the abstract methods)
	 */
	public abstract void Terminate(); 
	
	private State state = State.NO_WORLD;
	
	protected static enum State {
		NO_WORLD,IN_WORLD,TERMINATED;	
	}
	/**
	 * returns the state the entity is in.
	 * @return the state
	 * 			@see implementation
	 */
	public State getState(){
		return this.state;
	}
	/**
	 * checks if an entity has the InWorld state
	 * @return the boolean that checks if the entity has the InWorld state.
	 * 			@see implementation
	 */
	public boolean isEntityInWorld(){
		return (this.getState() == State.IN_WORLD);
	}
	/**
	 * checks if an entity has the NoWorld state
	 * @return the boolean that checks if the entity has the NoWorld state.
	 * 			@see implementation
	 */
	public boolean isEntityNoWorld(){
		return this.getState() == State.NO_WORLD;
	}
	/**
	 * checks if an entity has the Terminated state
	 * @return the boolean that checks if the entity has the Terminated state.
	 * 			@see implementation
	 */
	public boolean isEntityTerminated(){
		return this.getState() == State.TERMINATED;
	}
	/**
	 * Checks if an entity as a proper state.
	 * 
	 * @return the boolean that checks whether the entity's state is InWorld, NoWorld or Terminated.
	 * 			@see implementation
	 */
	public boolean hasEntityProperState(){
		return ( isEntityInWorld() ^ isEntityNoWorld() ^ isEntityTerminated() );
	}
	/**
	 * set the entity's state
	 * @param state
	 * 			The new entity's state
	 * @post the new state will be equal to the given state
	 * 			|new.getState() == state
	 * @throws IllegalArgumentException
	 * 			if the given state is null
	 * 			 @see implementation
	 */
	public void setEntityState(State state) {
		if (state == null){
			throw new IllegalArgumentException();}
		else
			this.state = state;
	}
	/**
	 * Set the entity's state to InWorld
	 * @param world
	 * 			the world the entity will be set in
	 * @pre the entity is not terminated
	 * 			@see implementation
	 * @effect the state changes to in_world and its world will be set to world.
	 * 			@see implementation
	 */
	public void setEntityInWorld(World world){
		assert (!this.isEntityTerminated());
		this.setEntityState(State.IN_WORLD);	
		this.setEntityWorld(world);
	}
	/**
	 * set the entity's state to NoWorld
	 * @pre the entity is not terminated
	 * 			@see implmentation
	 * @effect the entity's world will be set on null and the state to no world
	 * 			@see implementation
	 */
	public void setEntityNoWorld() {
		assert (!this.isEntityTerminated());
		this.setEntityState(State.NO_WORLD);
		this.setEntityWorld(null);
	}
	
	
	///METHODS ON TWO ENTITIES///
	/**
	 * Calculate the distance between two entities.
	 * 
	 * @param  otherEntity
	 *         The other entity.
	 *            
	 * @return If this (the entity the method is invoked on) and otherEntity are the
	 *         same entity, the distance between is 0. 
	 *         @see implementation
	 * @return The distance between the two entities if they're not the same. This
	 *         is calculated by subtracting the sum of the radius' of the entities
	 *         from the distance between the centers. 
	 *         @see implementation
	 */
	public double getDistanceBetween(Entity otherEntity) {
		if (this.equals(otherEntity))
			return 0;

		final double first_posX = this.getEntityPositionX();
		final double first_posY = this.getEntityPositionY();
		final double second_posX = otherEntity.getEntityPositionX();
		final double second_posY = otherEntity.getEntityPositionY();
		final double first_radius = this.getEntityRadius();
		final double second_radius = otherEntity.getEntityRadius();
		final double total_radius = first_radius + second_radius;
		final double delta_x = Math.abs(first_posX - second_posX);
		final double delta_y = Math.abs(first_posY - second_posY);
		final double distance_centers = getEuclidianDistance(delta_x, delta_y);
		final double distance = distance_centers - OMEGA * total_radius;
		
		return distance;
	}
	
	
	/**
	 * Returns a boolean saying if the two entities are overlapping.
	 *
	 * @param  otherEntity
	 *         The other entity.
	 * 
	 * @return Return True if the distance between the two entities is negative.
	 *         |result == (this.getDistanceBetween(otherEntity) < 0)
	 */
	public boolean overlap(Entity otherEntity) {
		if (this.equals(otherEntity))
			return true;

		double distance = this.getDistanceBetween(otherEntity);
		
		return (distance < 0);
	}
	
	/**
	 * Calculate the number of seconds until, if ever, the first collision
	 * between two entities will take place.
	 * 
	 * @param  otherEntity
	 *         The other entity.
	 * 
	 * @post   The amount of seconds until the collision will take place is
	 *         calculated. This means that if the two entities travel this amount of
	 *         seconds at their respective velocity, the distance between them
	 *         will be 0 (they collide). 
	 *         |this.move(getTimeToCollision(Entity otherEntity)) 
	 *         |otherEntity.move(getTimeToCollision(Entity otherEntity))
	 *         |this.getDistanceBetween(otherEntity) == 0
	 * 
	 * @return If the collision won't take place, Double.POSITIVE_INFINITY will
	 *         be returned.
	 * @return If the collision happens, the time until it happens is returned.
	 *         
	 * @throws ModelException
	 *         If the two entities overlap.
	 *         |(this.overlap(otherEntity))
	 */
	public double getTimeToCollision(Entity otherEntity) {
		if ( (!this.isEntityInWorld() && this.hasEntityProperState() ) || 
				( !otherEntity.isEntityInWorld() && otherEntity.hasEntityProperState() ) ){
			
			throw new IllegalArgumentException();				
		}

		double position_1X = this.getEntityPositionX();
		double position_1Y = this.getEntityPositionY();
		double position_2X = otherEntity.getEntityPositionX();
		double position_2Y = otherEntity.getEntityPositionY();
		double velocity_1X = this.getEntityVelocityX();
		double velocity_1Y = this.getEntityVelocityY();
		double velocity_2X = otherEntity.getEntityVelocityX();
		double velocity_2Y = otherEntity.getEntityVelocityY();
		double radius_1 = this.getEntityRadius();
		double radius_2 = otherEntity.getEntityRadius();
		double total_radius = (radius_1 + radius_2);

		double delta_rX = position_2X - position_1X;
		double delta_rY = position_2Y - position_1Y;
		double delta_vX = velocity_2X - velocity_1X;
		double delta_vY = velocity_2Y - velocity_1Y;
		double delta_r_r = Math.pow(delta_rX, 2) + Math.pow(delta_rY, 2);
		double delta_v_v = Math.pow(delta_vX, 2) + Math.pow(delta_vY, 2);
		double delta_v_r = (delta_rX * delta_vX + delta_rY * delta_vY);
		double d = Math.pow(delta_v_r, 2) - delta_v_v * (delta_r_r - Math.pow(total_radius, 2));
		
		if (this.overlap(otherEntity)){
			throw new IllegalArgumentException();
		} 
			
		else if (delta_v_r >= 0)
			return Double.POSITIVE_INFINITY;

		else if (d <= 0)
			return Double.POSITIVE_INFINITY;
		else
			return Math.abs((delta_v_r + Math.sqrt(d)) / delta_v_v);
	}

	/**
	 * Calculate the position, if there is one, of the collision between two
	 * entities.
	 * 
	 * @param  otherEntity
	 *         The other entity
	 *            
	 * @return Null if the time until the collision is positive infinity. 
	 * 		   |if (this.getTimeToCollision(otherEntity) == POSITIVE_INFINITY) 
	 *         |result = null
	 * @return The position of collision is calculated by moving the ships at
	 *         their respective velocities for the time until collision. Delta x
	 *         is the difference of the x-coordinates of the two ships when they
	 *         are on their collision positions. Delta y is the difference of
	 *         the y-coordinates on the same moment. 
	 *         Omega, the angle between the vertical delta y and the connection
	 *         between the two centers is used to project the distance between 
	 *         the collision point and the center on the x- and y-axis. 
	 *         This distance is the radius of the ship where the method is invoked on.
	 *         The sum of these projections with their respective positions (x and y of the
	 *         array) is the collision position.
	 *        @see implementation
	 * 
	 * @throws ModelException
	 *         If the two entities are overlapping.
	 *         |(this.overlap(otherEntity))
	 */
	public double[] getCollisionPosition(Entity otherEntity){
		double velocity_1X = this.getEntityVelocityX();
		double velocity_1Y = this.getEntityVelocityY();
		double velocity_2X = otherEntity.getEntityVelocityX();
		double velocity_2Y = otherEntity.getEntityVelocityY();
		double position_1X = this.getEntityPositionX();
		double position_1Y = this.getEntityPositionY();
		double position_2X = otherEntity.getEntityPositionX();
		double position_2Y = otherEntity.getEntityPositionY();
		double radius_1 = this.getEntityRadius();

		double time_till_overlapping = this.getTimeToCollision(otherEntity);
		

		if (time_till_overlapping == Double.POSITIVE_INFINITY)
			return null;

		else {
			double pos_collide1X = position_1X + velocity_1X * time_till_overlapping;
			double pos_collide1Y = position_1Y + velocity_1Y * time_till_overlapping;
			double pos_collide2X = position_2X + velocity_2X * time_till_overlapping;
			double pos_collide2Y =position_2Y + velocity_2Y * time_till_overlapping;
			double delta_x = (pos_collide2X - pos_collide1X);
			double delta_y = (pos_collide2Y - pos_collide1Y);
			
			double omega;

			if (delta_x > 0) {
				omega = Math.atan(delta_y / delta_x);
			}else if(delta_x == 0 && delta_y > 0){
				omega = Math.PI/2;
			}else if(delta_x == 0 && delta_y < 0){
				omega = 3*Math.PI/2;
			} else {
				omega = Math.atan(delta_y / delta_x) + Math.PI;
			}

			double[] position_collide = { pos_collide1X + radius_1 * Math.cos(omega),
					pos_collide1Y + radius_1 * Math.sin(omega) };
			return position_collide;
		}
	}
	
	// This method will place the entity at any given position, even when this position is an illegal position.
	/**
	 * Set the position of a colliding entity, without using isValidPosition.
	 * @param x
	 * 			The new x-position
	 * @param y
	 * 			the new y-position
	 * @post the new position will be equal to the given values.
	 * 			|new.getEntityPosition == {x,y}
	 */
	public void setPositionWhenColliding(double x, double y){
		this.position.setPositionX(x);
		this.position.setPositionY(y);
	}
		/**
		 * get the minimum time until the entity collides with a boundary
		 * 
		 * @return positive infinity if the entity has no world and a proper state.
		 * 			@see implementation
		 * @return if we move the entity for the result, it will collide with one of the boundaries
		 * 			|this.move(result)
		 * 			|this.getEntityPositionX - radius == 0 || this.getEntityPositionX() + this.getEntityradius == this.getEntityWorld.getWorldWidth()||
		 * 			|this.getEntityPositionY - radius == 0 || this.getEntityPositionY() + this.getEntityradius == this.getEntityWorld.getWorldHeight()
		 */
	public double getTimeCollisionBoundary(){
		if (!this.isEntityInWorld() && this.hasEntityProperState()){
			return Double.POSITIVE_INFINITY;
		} else {
			double positionX = this.getEntityPositionX();
			double positionY = this.getEntityPositionY();
			double velocityX = this.getEntityVelocityX();
			double velocityY = this.getEntityVelocityY();
			double width = this.getEntityWorld().getWorldWidth();
			double height = this.getEntityWorld().getWorldHeight();
			double radius = this.getEntityRadius();
			
			double x_distance_right = (width - (positionX + radius));
			double y_distance_up = (height - (positionY + radius));	
			double x_distance_left =(positionX - radius);
			double y_distance_down = (positionY - radius);
			
			// Calculate the time till the collision will happen with each boundary
			double timeCollisionRight = Double.POSITIVE_INFINITY;
			double timeCollisionLeft = Double.POSITIVE_INFINITY;
			double timeCollisionUp = Double.POSITIVE_INFINITY;
			double timeCollisionDown = Double.POSITIVE_INFINITY;
			
			if (velocityX > 0)
				timeCollisionRight = Math.abs(x_distance_right / velocityX);
			if (velocityX < 0)
				timeCollisionLeft = Math.abs(x_distance_left/velocityX);
			if (velocityY > 0)
				timeCollisionUp = Math.abs(y_distance_up /velocityY);
			if (velocityY < 0)
				timeCollisionDown = Math.abs(y_distance_down/velocityY);
			
			if ( Math.min(timeCollisionLeft, timeCollisionRight) < Math.min(timeCollisionUp, timeCollisionDown) )
				return Math.min(timeCollisionLeft, timeCollisionRight);
			else if ( Math.min(timeCollisionLeft, timeCollisionRight) > Math.min(timeCollisionUp, timeCollisionDown) )
				return Math.min(timeCollisionUp, timeCollisionDown);
			else 
				return Double.POSITIVE_INFINITY;
		}
	} 
	/**
	 * Returns the position where the entity will collide with the boundary
	 * @return if we move the entity for gettimeCollisionBoundary() seconds, the position we return will be the position its on after this move added or detracted with the radius.
	 * 			|move(getTimeCollisionBoundary)
	 * 			|result[0] == getEntityPosition+radius || result[1] == getEntityPosition+radius || result[0] == getEntityPosition-radius || result[1] == getEntityPosition-radius  
	 */
	public double[] getPositionCollisionBoundary(){
		double time = getTimeCollisionBoundary();
		double new_x = 0;
		double new_y = 0;
		
		if (time == Double.POSITIVE_INFINITY){
			// The entity will never collide with a boundary
			return null;
		} else{
			double positionX = this.getEntityPositionX();
			double positionY = this.getEntityPositionY();
			double velocityX = this.getEntityVelocityX();
			double velocityY = this.getEntityVelocityY();
			double width = this.getEntityWorld().getWorldWidth();
			double height = this.getEntityWorld().getWorldHeight();
			double radius = this.getEntityRadius();
			
			new_x = Math.abs(positionX+time*velocityX);
			new_y = Math.abs(positionY+time*velocityY);
			
			// Right boundary
			if ((new_x + OMEGA*radius) <= width && width <= (new_x + BETA*radius) )
				new_x += radius;
			// Left boundary
			else if ((new_x - BETA*radius) <= 0 && 0 <= (new_x - OMEGA*radius) )
				new_x -= radius;
			// Upper boundary
			else if ((new_y + OMEGA*radius) <= height && height <= (new_y + BETA*radius) )
				new_y += radius;
			// Lower boundary
			else if ((new_y - BETA*radius) <= 0 && 0 <= (new_y - OMEGA*radius) )
				new_y -= radius;
			// If none of the above statements were correct, there was a fault!
			else {
				return null;
			}
		}
		double[] new_position = {new_x, new_y};
		return new_position;
	}
	
	
	///RELATIONS WITH OTHER CLASSES///
	private  World world = null;
}
	




