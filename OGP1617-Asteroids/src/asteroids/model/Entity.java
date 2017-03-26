package asteroids.model;

import asteroids.util.ModelException;
import be.kuleuven.cs.som.annotate.*;

public abstract class Entity {
	
	///CONSTRUCTOR///
	protected Entity(double x, double y, double xVelocity, double yVelocity, double radius,double orientation,
			double mass,double maxVelocity,double density) throws ModelException{
		setEntityRadius(radius);
		setEntityOrientation(orientation);
		setEntityMaxVelocity(maxVelocity);
		setEntityPosition(x,y);
		setEntityVelocity(xVelocity,yVelocity);
		setEntityDensity(density);
		setEntityMass(mass);
	}
	

	///BASIC PROPERTIES///
	private double[] position;
	private double[] velocity;
	private double radius;
	private double orientation;
	private double max_velocity;
	private double density;
	private double mass;
	
	///DEFAULTS///
	private final static double SPEED_OF_LIGHT = 300000;
	private final static double LOWER_BULLET_RADIUS = 1;
	private final static double LOWER_SHIP_RADIUS = 10;
	
	
	public final static double OMEGA = 0.99;
	
	public static double getDefaultMaxVelocity(){
		return SPEED_OF_LIGHT;
	}
	public static double getDefaultShipDensity() {
		return 1.42E12;
	}
	
	public static double getDefaultBulletDensity() {
		return 7.8E12;
	}
	

	@Immutable
	public static double[] getDefaultPosition() {
		double[] array = { 0, 0 };
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
		double[] array = { 0, 0 };
		return array;
	}
	
	
	/**
	 * Return the default orientation of the ship.
	 * 
	 * @return The default orientation is a value between 0 and 2*PI with 0 =
	 *         right, PI/2 = up and so on. | 0 <= result <= 2*PI
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

	public static boolean entityFitsInWorld(Entity entity, World world){
		double radius = entity.getEntityRadius();
		double upper_bound = OMEGA*(world.getWorldSize()[1]-radius);
		double right_bound = OMEGA*(world.getWorldSize()[0]-radius);
		double x = entity.getEntityPosition()[0];
		double y = entity.getEntityPosition()[1];		
		return ((0 <x-radius) && (0 < y-radius) && (upper_bound > x) && (right_bound > y));}
	
	
	
	/// GETTERS ///
	public double[] getEntityPosition(){
		return this.position;
	}
	
	public double[] getEntityVelocity(){
		return this.velocity;
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
	
	public double getEntityMass(){
		if (this instanceof Ship){
			double bullets_weight = ((Ship)this).getBulletsWeight();
			return this.mass + bullets_weight;
		} else
			return this.mass;	
	}
	
	public World getEntityWorld(){
		return this.world;
	}
	
	/// SETTERS ///
	// --> BEKIJKEN <-- // 
	public void setEntityPosition(double x, double y) throws ModelException{
		if (!isValidArray(x, y))
			throw new ModelException("Not a valide coordinate");	
		else if ((this).isValidEntityPosition(x,y)){
			double[] position_array = { x, y };
			this.position = position_array;}
		else 
				throw new ModelException("Not a valide coordinate");	
	}
	
	public void setEntityVelocity(double xVelocity, double yVelocity){
		if (!isValidArray(xVelocity, yVelocity)) {
			if (Double.isNaN(xVelocity))
				xVelocity = 0;
			if (Double.isNaN(yVelocity))
				yVelocity = 0;
		}
		if (getEuclidianDistance(xVelocity, yVelocity) > this.getEntityMaxVelocity()) {
			double orientation = this.getEntityOrientation();
			double xVel = Math.cos(orientation) * this.getEntityMaxVelocity();
			double yVel = Math.sin(orientation) * this.getEntityMaxVelocity();

			double[] velocity_array = { xVel, yVel };
			this.velocity = velocity_array;
		}

		else {
			double[] velocity_array = { xVelocity, yVelocity };
			this.velocity = velocity_array;
		}
	}
	
	
	public void setEntityRadius(double radius) throws ModelException{
		if ((radius < 0) || (this instanceof Bullet && radius <LOWER_BULLET_RADIUS) || (this instanceof Ship && radius <LOWER_SHIP_RADIUS))
			throw new ModelException("The given radius is not possible");
		this.radius = radius;	
	}
	
	
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
		assert isValidRadian(orientation);
		if (this instanceof Ship)
			this.orientation = orientation;
		else
			this.orientation = ((Bullet)this).getBulletShip().getEntityOrientation();
	}

	
	public void setEntityMaxVelocity(double maxVelocity){
		if ((maxVelocity < SPEED_OF_LIGHT) && (maxVelocity > 0))
			this.max_velocity = maxVelocity;
		else
			this.max_velocity = SPEED_OF_LIGHT;
	}
	
	public void setEntityDensity(double density){
		if ((this instanceof Ship) && (density <getDefaultShipDensity()))
				density = getDefaultShipDensity();
		else if (density<0)
			density = getDefaultBulletDensity();
		this.density = density;
			
	}
	
	public void setEntityMass(double mass) throws ModelException{
		if (this instanceof Ship){	
			if (mass < minimumEntityMass())
				mass = minimumEntityMass();
		}else if (this instanceof Bullet){
			mass = minimumEntityMass();
		} else {
			throw new ModelException("not a legal entity");
		}
		this.mass = mass;
	}
	
	public double minimumEntityMass(){
		return 4/3*Math.PI * Math.pow(this.getEntityRadius(),3)*this.getEntityDensity();
	}
	
	public void setEntityWorld(World world){
		this.world = world;
	}
	
	
	/// CHECKERS ///
	
	/**
	 * Checks whether an array has two values of the type double.
	 * 
	 * @param 	x
	 *            The first value of the array that has to be checked.
	 * @param 	y
	 *            The second value of the array that has to be checked.
	 * 
	 * @return 	True if both x and y are type Double and not of the type NaN.
	 *         |result = ((! Double.isNaN(x)) && (! Double.isNaN(y)))
	 */
	static boolean isValidArray(double x, double y) {
		return ((!Double.isNaN(x)) && (!Double.isNaN(y)));
	}
	
	/**
	 * Checks whether the given radian is in a correct range.
	 * 
	 * @param radian
	 *            The radians that has to be checked.
	 * 
	 * @return True if radian is greater or equal than 0 and lower than 2*PI
	 *         |result = ((0<=radian) && (radian<2*Math.PI))
	 */
	public static boolean isValidRadian(double radian) {
		return ((0 <= radian) && (radian < 2 * Math.PI));
	}
	
	public boolean isValidEntityPosition(double x, double y){
		if ((this.getEntityWorld() != null)){
			return Entity.entityFitsInWorld(this,this.getEntityWorld());}
		else
			return true;
	}
	
	// RUBEN //
	public void move(double dt) throws ModelException {
		if (dt < 0)
			throw new ModelException("Give a positive time please.");
		
		final double[] velocity = this.getEntityVelocity();
		double vel_x = velocity[0];
		double vel_y = velocity[1];
				
		if (this instanceof Ship && ((Ship)this).isThrusterActive()) {
			final double acceleration = ((Ship)this).getShipAcceleration();
			final double orientation = ((Ship)this).getEntityOrientation();
			vel_x += acceleration*Math.cos(orientation)*dt;
			vel_y += acceleration*Math.sin(orientation)*dt;
			this.setEntityVelocity(vel_x, vel_y);
		}

		final double[] position = this.getEntityPosition();
		final double delta_x = vel_x * dt;
		final double delta_y = vel_y * dt;

		this.setEntityPosition(position[0] + delta_x, position[1] + delta_y);
	}
	
	
	///TERMINATION AND STATES///
	
	public void Terminate() throws ModelException{
		if (this.isEntityFree()){
			setEntityState(State.TERMINATED);}
			else if (this.isEntityInWorld()){
				this.getEntityWorld().removeEntityFromWorld(this);
				setEntityState(State.TERMINATED);}
		if (this instanceof Ship){
			for (Bullet bullet:((Ship)this).getShipBullets()){
				bullet.Terminate();
			}
		}
	}
	
	private State state = State.FREE;
	
	//FREE = GEEN WORLD, INWORLD = WEL EEN WORLD
	private static enum State {
		FREE,INWORLD,TERMINATED;	
	}
	
	public State getState(){
		return this.state;
	}
	public boolean isEntityInWorld(){
		return (this.getState() == State.INWORLD);
	}
	
	public boolean isEntityFree(){
		return this.getState() == State.FREE;
	}
	public boolean isEntityTerminated(){
		return this.getState() == State.TERMINATED;
	}
	
	public boolean hasEntityProperState(){
		return isEntityInWorld() ^ isEntityFree() ^ isEntityTerminated();
	}
	
	public void setEntityState(State state) throws ModelException{
		if (state == null)
			throw new ModelException("this is not a valid state");
		else
			this.state = state;
	}
	
	public void setEntityInWorld(World world) throws ModelException{
		assert (!this.isEntityTerminated());
		this.setEntityState(State.INWORLD);	
		this.setEntityWorld(world);
	}
	
	public void setEntityFree() throws ModelException{
		assert (!this.isEntityTerminated());
		this.setEntityState(State.FREE);
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

		final double[] first_pos = this.getEntityPosition();
		final double[] second_pos = otherEntity.getEntityPosition();
		final double x_distance = Math.abs(first_pos[0]-second_pos[0]);
		final double y_distance = Math.abs(first_pos[1]-second_pos[1]);
		final double distance_centers = getEuclidianDistance(x_distance, y_distance);
		final double distance = distance_centers - (this.getEntityRadius() + otherEntity.getEntityRadius());

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
	public double getTimeToCollision(Entity otherEntity) throws ModelException {
		double[] velocity_1 = this.getEntityVelocity();
		double[] velocity_2 = otherEntity.getEntityVelocity();
		double[] position_1 = this.getEntityPosition();
		double[] position_2 = otherEntity.getEntityPosition();
		double radius_1 = this.getEntityRadius();
		double radius_2 = otherEntity.getEntityRadius();
		double total_radius = (radius_1 + radius_2);

		double[] delta_r = { position_2[0] - position_1[0], position_2[1] - position_1[1] };
		double[] delta_v = { velocity_2[0] - velocity_1[0], velocity_2[1] - velocity_1[1] };
		double delta_r_r = Math.pow(delta_r[0], 2) + Math.pow(delta_r[1], 2);
		double delta_v_v = Math.pow(delta_v[0], 2) + Math.pow(delta_v[1], 2);
		double delta_v_r = (delta_r[0] * delta_v[0] + delta_r[1] * delta_v[1]);
		double d = Math.pow(delta_v_r, 2) - delta_v_v * (delta_r_r - Math.pow(total_radius, 2));

		if (this.overlap(otherEntity))
			throw new ModelException("The two enties are overlapping");

		else if (delta_v_r > 0)
			return Double.POSITIVE_INFINITY;

		else if (d <= 0)
			return Double.POSITIVE_INFINITY;
		else
			return -(delta_v_r + Math.sqrt(d)) / delta_v_v;
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
	public double[] getCollisionPosition(Entity otherEntity) throws ModelException {

		double[] velocity_1 = this.getEntityVelocity();
		double[] velocity_2 = otherEntity.getEntityVelocity();
		double[] position_1 = this.getEntityPosition();
		double[] position_2 = otherEntity.getEntityPosition();
		double radius_1 = this.getEntityRadius();

		double time_till_overlapping = this.getTimeToCollision(otherEntity);

		if (this.overlap(otherEntity))
			throw new ModelException("The two entities are overlapping");

		else if (time_till_overlapping == Double.POSITIVE_INFINITY)
			return null;

		else {
			double[] pos_collide1 = { position_1[0] + velocity_1[0] * time_till_overlapping,
					position_1[1] + velocity_1[1] * time_till_overlapping };
			double[] pos_collide2 = { position_2[0] + velocity_2[0] * time_till_overlapping,
					position_2[1] + velocity_2[1] * time_till_overlapping };

			double delta_x = (pos_collide2[0] - pos_collide1[0]);
			double delta_y = (pos_collide2[1] - pos_collide1[1]);
			double omega;

			if (delta_x > 0) {
				omega = Math.atan(delta_y / delta_x);
			} else {
				omega = Math.atan(delta_y / delta_x) + Math.PI;
			}

			double[] position_collide = { pos_collide1[0] + radius_1 * Math.cos(omega),
					pos_collide1[1] + radius_1 * Math.sin(omega) };

			return position_collide;
		}

	}
	
	public double getTimeCollisionBoundary(){
		if (this.isEntityFree() || this.isEntityTerminated()){
			return Double.POSITIVE_INFINITY;}
		else {
			double[] position = this.getEntityPosition();
			double[] velocity = this.getEntityVelocity();
			double[] size = this.getEntityWorld().getWorldSize();
			double radius = this.getEntityRadius();
			double x_distance = Math.abs(size[0] - position[0]-radius);
			double y_distance = Math.abs(size[1] - position[1]-radius);
			
			double dtx = (x_distance / velocity[0]);
			double dty = (y_distance / velocity[1]);
			
			if (dtx > dty){
				return dty;}
			else if (dty > dtx){
				return dtx;}
			else {
				return Double.POSITIVE_INFINITY;}
		
		}
		
		
	} 
	
	public double[] getPositionCollisionBoundary(){
		double time = getTimeCollisionBoundary();
		double new_x = 0;
		double new_y = 0;
		if (time == Double.POSITIVE_INFINITY){
			new_x = Double.POSITIVE_INFINITY;
			new_y = Double.POSITIVE_INFINITY;}
		else{
			double[] position = this.getEntityPosition();
			double[] velocity = this.getEntityVelocity();
			double radius = this.getEntityRadius();
			double[] size = this.getEntityWorld().getWorldSize();
			new_x = position[0]+time*velocity[0];
			new_y = position[1]+time*velocity[1];
			if (Math.abs(size[0] - position[0]-radius) ==0)
				new_x += radius;
			else if ((Math.abs(size[0] - position[0]+radius) == size[0]))
				new_x -= radius;
			else if ((Math.abs(size[1] - position[1]-radius)==0))
					new_y += radius;
			else
				new_y -= radius;
		}
		double[] new_position = {new_x,new_y};
		return new_position;
	}
	
	
	///RELATIONS WITH OTHER CLASSES///
	private  World world = null;
	
	
}
	

