package asteroids.model;


import be.kuleuven.cs.som.annotate.*;
 
/**
 * a class that describes and modifies all the entities.
 * 
 * @version 28th of march
 * @authors Sieben Bocklandt and Ruben Broekx

 */
public abstract class Entity {
	
	///CONSTRUCTOR///
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
	
	public static double getDefaultMaxVelocity(){
		return SPEED_OF_LIGHT;
	}
	
	@Immutable
	public static double[] getDefaultPosition() {
		double[] array = {0, 0};
		return array;
	}

	/**
	 * Return the default velocity of the ship.
	 * 
	 * @return The default velocity is an array of two rational numbers. |
	 *         result = (xVelocity, yVelocity)
	 */
	@Immutable
	public static double[] getDefaultVelocity() {
		double[] array = {0, 0};
		return array;
	}
	
	/**
	 * Return the default orientation of the ship.
	 * 
	 * @return The default orientation is a value between 0 and 2*PI with 0 =
	 *         right, PI/2 = up and so on. 
	 *         | 0 <= result <= 2*PI
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
	 * @return The total velocity: the square root of the sum of xVelocity
	 *         squared and yVelocity squared. |result
	 *         =Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2))
	 */
	public static double getEuclidianDistance(double a, double b) {
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}

	public boolean entityFitsInWorld(World world){
		if (!this.entityInBoundaries(world) || this.entityOverlappingInWorld(world)!=null){
			System.out.println("Model.entity, entityFitsInWorld: entity does not fit in the world, or is overlapping with other entity");
			return false;
		}
		return true;
	}
	
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
	public Entity entityOverlappingInWorld(World world){
		// Check if the entity is not overlapping with another entity.
		for (Object otherEntity: world.getWorldEntities()){
			if ( this.overlap((Entity)otherEntity) && !this.equals(otherEntity) )
				return (Entity)otherEntity;
		}
		return null;
		
	}

	/// GETTERS ///
	public double[] getEntityPosition(){
		return this.position.getPositionArray();
	}
	
	public double getEntityPositionX(){
		return this.position.getPositionX();
	}
	
	public double getEntityPositionY(){
		return this.position.getPositionY();
	}
	
	public double[] getEntityVelocity(){
		return this.velocity.getVelocityArray();
	}
	
	public double getEntityVelocityX() {
		return this.velocity.getVelocityX();
	}
	
	public double getEntityVelocityY() {
		return this.velocity.getVelocityY();
	}
	
	public double getEntityRadius(){
		return this.radius;
	}
	
	public double getEntityOrientation(){
		return this.orientation;	
	}
	
	public double getEntityMaxVelocity(){
		return this.max_velocity;
	}
	
	public double getEntityDensity(){
		return this.density;
	}
	
	public abstract double getEntityMass();
	
	public World getEntityWorld(){
		return this.world;
	}
	
	/// SETTERS ///
	// --> BEKIJKEN <-- // 
	public void setEntityPosition(double positionX, double positionY) {
		if (!isValidPosition(positionX, positionY)){
			System.out.println("Model.entity, setEntityPosition: given position is not a valid position");
			throw new IllegalArgumentException();}
		
		this.position.setPositionX(positionX);
		this.position.setPositionY(positionY);
	}
	
	public boolean isValidPosition(double positionX, double positionY){
		if ((Double.isNaN(positionX)) || (Double.isNaN(positionY)))
			return false;
		
		if ((this.getEntityWorld() != null)) 
			return this.entityFitsInWorld(this.getEntityWorld());
		
		return true;
	}
	
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
	
	public boolean isValidVelocity(double xVelocity, double yVelocity) {
		if ((Double.isNaN(xVelocity)) || (Double.isNaN(yVelocity)))
			return false;
		
		if (getEuclidianDistance(xVelocity, yVelocity) > this.getEntityMaxVelocity())
			return false;
		
		return true;
	}
	
	public void setEntityRadius(double radius) {
		if (isValidRadius(radius))
			this.radius = radius;
		else{
			System.out.println("Model.entity, setEntityRadius: given radius is not a valid radius");
			throw new IllegalArgumentException();
		}
	}
	
	public abstract boolean isValidRadius(double radius);	
	
	/**
	 * Gives the ship a new orientation.
	 * 
	 * @param radians
	 *            The new orientation in radians.
	 * 
	 * @pre Radians is a valid orientation for the ship. |
	 *      isValidRadian(radians)
	 * 
	 * @post The new orientation will be equal to the given radians.
	 *       |new.getShipOrientation() == radians
	 */
	public void setEntityOrientation(double orientation){
		assert isValidOrientation(orientation);
		this.orientation = orientation;
	}

	public boolean isValidOrientation(double orientation){
		return ( (0 <= orientation) && (orientation < 2 * Math.PI) );		
	}
	
	public void setEntityMaxVelocity(double newMaxVelocity){
		if ( (0 < newMaxVelocity) && (newMaxVelocity < SPEED_OF_LIGHT) )
			this.max_velocity = newMaxVelocity;
		else
			this.max_velocity = SPEED_OF_LIGHT;
	}
	
	public abstract void setEntityDensity(double density);
	
	public abstract boolean isValidDensity(double density); 
	
	public abstract void setEntityMass(double mass); 
	
	public abstract boolean isValidMass(double mass);

	public void setEntityWorld(World world){
		this.world = world;
	}
	
	///MOVE///
	public abstract void move(double dt);
	
	///TERMINATION AND STATES///
	public abstract void Terminate(); 
	
	private State state = State.NO_WORLD;
	
	protected static enum State {
		NO_WORLD,IN_WORLD,TERMINATED;	
	}
	
	public State getState(){
		return this.state;
	}
	public boolean isEntityInWorld(){
		return (this.getState() == State.IN_WORLD);
	}
	
	public boolean isEntityFree(){
		return this.getState() == State.NO_WORLD;
	}
	public boolean isEntityTerminated(){
		return this.getState() == State.TERMINATED;
	}
	
	public boolean hasEntityProperState(){
		return ( isEntityInWorld() ^ isEntityFree() ^ isEntityTerminated() );
	}
	
	public void setEntityState(State state) {
		if (state == null){
			System.out.println("Model.entity, setEntityState: state == null");
			throw new IllegalArgumentException();}
		else
			this.state = state;
	}
	
	public void setEntityInWorld(World world){
		assert (!this.isEntityTerminated());
		this.setEntityState(State.IN_WORLD);	
		this.setEntityWorld(world);
	}
	
	public void setEntityFree() {
		assert (!this.isEntityTerminated());
		this.setEntityState(State.NO_WORLD);
		this.setEntityWorld(null);
	}
	
	
	///METHODS ON TWO ENTITIES///
	/**
	 * Calculate the distance between two ships.
	 * 
	 * @param  otherShip
	 *         The other ship.
	 *            
	 * @return If this (the ship the method is invoked on) and otherShip are the
	 *         same ship, the distance between is 0. 
	 *         |if (this == otherShip)
	 *         |return 0
	 * @return The distance between the two ships if they're not the same. This
	 *         is calculated by subtracting the sum of the radius' of the ships
	 *         from the distance between the centers. 
	 *         |first_pos = this.getShipPosition(); 
	 *         |second_pos = otherShip.getShipPosition(); 
	 *         |result = Math.sqrt(Math.pow(first_pos[0], second_pos[0]) +
	 *         Math.pow(first_pos[1], second_pos[1])) | - (this.getShipRadius()
	 *         + otherShip.getShipRadius())
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
	 * Returns a boolean saying if the two ships are overlapping.
	 *
	 * @param  otherShip
	 *         The other ship.
	 * 
	 * @return Return True if the distance between the two ships is negative.
	 *         |result = (this.getDistanceBetween(otherShip) < 0)
	 */
	public boolean overlap(Entity otherEntity) {
		if (this.equals(otherEntity))
			return true;

		double distance = this.getDistanceBetween(otherEntity);
		
		return (distance < 0);
	}
	
	/**
	 * Calculate the number of seconds until, if ever, the first collision
	 * between two ships will take place.
	 * 
	 * @param  otherShip
	 *         The other ship.
	 * 
	 * @post   The amount of seconds until the collision will take place is
	 *         calculated. This means that if the two ships travel this amount of
	 *         seconds at their respective velocity, the distance between them
	 *         will be 0 (they collide). 
	 *         |this.move(getTimeToCollision(Ship otherShip)) 
	 *         |otherShip.move(getTimeToCollision(Ship otherShip))
	 *         |this.getDistanceBetween(otherShip) == 0
	 * 
	 * @return If the collision won't take place, Double.POSITIVE_INFINITY will
	 *         be returned.
	 * @return If the collision happens, the time until it happens is returned.
	 *         
	 * @throws ModelException
	 *         If the two ships overlap.
	 *         |(this.overlap(otherShip))
	 */
	public double getTimeToCollision(Entity otherEntity) {
		if ( (!this.isEntityInWorld() && this.hasEntityProperState() ) || 
				( !otherEntity.isEntityInWorld() && otherEntity.hasEntityProperState() ) ){
			
			System.out.println("Model.entity, getTimeToCollision: One of the entities does not lie in the world, or doesn't have a proper state");
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
			System.out.println("Model.entity, getTimeToCollision: There are entities overlapping");
			System.out.println("Position = "+otherEntity.getEntityPositionX()+", "+otherEntity.getEntityPositionY());
			System.out.println("Center world: "+this.getEntityWorld().getWorldWidth()/2+", "+this.getEntityWorld().getWorldHeight()/2);
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
	 * ships.
	 * 
	 * @param  otherShip
	 *         The other ship
	 *            
	 * @return Null if the time until the collision is positive infinity. 
	 * 		   |if (this.getTimeToCollision(otherShip) == POSITIVE_INFINITY) 
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
	 *         |pos_collide1 = {position_1[0] + velocity_1[0]*time_till_overlapping, position_1[1] +
	 *         | velocity_1[1]*time_till_overlapping}
	 *         |pos_collide2 = {position_2[0] + velocity_2[0]*time_till_overlapping,
	 *         | position_2[1] + velocity_2[1]*time_till_overlapping}; 
	 *         |delta_x = (pos_collide2[0] - pos_collide1[0]); |delta_y = (pos_collide2[1]- pos_collide1[1]); 
	 *         |if (delta_x > 0) 
	 *         | omega = Math.atan(delta_y/delta_x); 
	 *         |else 
	 *         | omega = Math.atan(delta_y/delta_x) + Math.PI; 
	 *         |position_collide = {pos_collide1[0] + radius_1*Math.cos(omega), pos_collide1[1] +
	 *         |radius_1*Math.sin(omega)}; 
	 *         |result = position_collide;
	 * 
	 * @throws ModelException
	 *         If the two ships are overlapping.
	 *         |(this.overlap(otherShip))
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
	public void setPositionWhenColliding(double x, double y){
		this.position.setPositionX(x);
		this.position.setPositionY(y);
	}
		
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
				System.out.println("model.Entity, getPositionCollisionBoundary: 'Impossible' else-statement is invoked!");
				System.out.println("Should have bounced with a boundary!");
				System.out.println("new_x: "+new_x+", new_y: "+new_y+", the radius: "+radius);
				System.out.println("width: "+width+", height: "+height+", the radius: "+radius);
				return null;
			}
		}
		double[] new_position = {new_x, new_y};
		return new_position;
	}
	
	
	///RELATIONS WITH OTHER CLASSES///
	private  World world = null;
}
	




