package asteroids.model;

import java.util.HashSet;
import java.util.Set;
import asteroids.util.ModelException;
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
 * @version 21_Mar_19u
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
			double maxVelocity,double density, boolean thrusterActivity,  double thrusterForce) throws ModelException {
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
	
	
	/**
	 * Initializes a new ship with given values and a maximum total velocity
	 * equal to the speed of light.
	 * 
	 * @effect The ship is initialized with the given values and the default
	 *         maximum total velocity. |this(x, y, xVelocity, yVelocity, radius,
	 *         orientation, getDefaultMaxVelocity())
	 */
	//WITHOUT DENSITY,MAX_VELOCITY,THRUSTER_ACTIVITY,THRUSTER_FORCE//
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation,double mass)
			throws ModelException {
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
	public Ship() throws ModelException {
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

	/**
	 * Return the default position of the ship.
	 * 
	 * @return The default position is an array of two rational numbers. |
	 *         result = {x, y}
	 */
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

	public static double getDefaultMass() {
		return 4/3*Math.PI * Math.pow(getDefaultRadius(),3)*getDefaultShipDensity();
	}
	
	
	public static boolean getDefaultThrusterActivity(){;
		return false;
	}
	
	public static double getDefaultThrusterForce(){
		return 1.1E21;
	}
	
	
	/// GETTERS ///

	//WE NEED TO ADD THE WEIGHT OF BULLETS//
		//this will be done when we make the association//
		
	public boolean isThrusterActive(){
		return this.thruster_activity;
	}
	
	public double getShipAcceleration(){
		return (this.thruster_force/this.getEntityMass());
	}
	
	public World getShipWorld(){
		return this.world;
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
	
	
	
	///CHECKERS///
	
	public boolean isValidShipPosition(double x, double y){
		if ((this.getShipWorld() != null)){
			double radius = this.getEntityRadius();
			double upper_ship_bound = OMEGA*(this.getShipWorld().getWorldHeight()-radius);
			double right_ship_bound = OMEGA*(this.getShipWorld().getWorldWidth()-radius);
			return ((0 <x-radius) && (0 < y-radius) && (upper_ship_bound > x) &&	
				 (right_ship_bound > y));}
		else
			return true;		
	}
	/// METHODS ON ONE SHIP///
	/**
	 * Update the ship's position, assuming it moves in dt seconds at its
	 * current velocity.
	 * 
	 * @param dt
	 *            Delta time is the time in which the ship travels at it's
	 *            current velocity.
	 * 
	 * @effect If the given time is nonnegative, the new position will be the
	 *         sum of the original position (in x and y) and the traveled
	 *         distance (velocity(x,y)*dt) in x and y. |if (dt >= 0) |
	 *         this.setShipPosition(this.getShipPosition[0]+delta_x,
	 *         this.getShipPosition[1]+delta_y);
	 * @throws ModelException
	 *             If delta time is negative. |(dt < 0)
	 */
	@Deprecated
	public void move(double dt) throws ModelException {
		if (dt < 0)
			throw new ModelException("Give a positive time please.");

		final double[] velocity = this.getEntityVelocity();
		final double[] position = this.getEntityPosition();
		final double delta_x = velocity[0] * dt;
		final double delta_y = velocity[1] * dt;

		this.setEntityPosition(position[0] + delta_x, position[1] + delta_y);
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
		assert isValidRadian(this.getEntityOrientation() + angle);
		assert this instanceof Ship;
		this.setEntityOrientation(this.getEntityOrientation() + angle);
	}

	/**
	 * Update the ship its velocity based on its current velocity, its
	 * direction, and the given amount.
	 * 
	 * @param amount
	 *            The amount of velocity we want to add or subtract from the
	 *            current velocity.
	 * 
	 * @effect If the amount is negative, it will be set on 0. The velocity of
	 *         the ship will then be set on the x and y value of the velocity it
	 *         already had. |if (amount < 0) |
	 *         this.setShipVelocity(this.getShipVelocity()[0],this.getShipVelocity()[1])
	 * @effect If the amount is positive, the velocity of the ship will be
	 *         updated with the given amount projected on the x- and y-axis. If
	 *         the updated velocity is greater than the speed of light, the
	 *         velocity will be set on the speed of light projected on the x-
	 *         and y-axis. |if (amount>=0) |
	 *         this.setShipVelocity(this.getShipVelocity()[0] +
	 *         amount*Math.cos(this.getShipOrientation()), |
	 *         this.getShipVelocity()[1] +
	 *         amount*Math.cos(this.getShipOrientation()));
	 * 
	 */
	@Deprecated
	public void thrust(double amount) {
		if (amount < 0)
			amount = 0;
		double thrust_x = this.getEntityVelocity()[0] + amount * Math.cos(this.getEntityOrientation());
		double thrust_y = this.getEntityVelocity()[1] + amount * Math.sin(this.getEntityOrientation());

		this.setEntityVelocity(thrust_x, thrust_y);
	}


	public void thrustOn(){
		setThrusterActive(true);
	}
	public void thrustOff(){
		setThrusterActive(false);
	}
	
	/// METHODS ON TWO SHIPS///
	///////BEKIJKEN ALS WE KOPIEREN NAAR ENTITY///
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
	public double getDistanceBetween(Ship otherShip) {
		if (this.equals(otherShip))
			return 0;

		final double[] first_pos = this.getEntityPosition();
		final double[] second_pos = otherShip.getEntityPosition();

		final double distance_centers = Math
				.sqrt(Math.pow(first_pos[0], second_pos[0]) + Math.pow(first_pos[1], second_pos[1]));
		final double distance = distance_centers - (this.getEntityRadius() + otherShip.getEntityRadius());

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
	public boolean overlap(Ship otherShip) {
		if (this.equals(otherShip))
			return true;

		double distance = this.getDistanceBetween(otherShip);
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
	public double getTimeToCollision(Ship otherShip) throws ModelException {
		double[] velocity_1 = this.getEntityVelocity();
		double[] velocity_2 = otherShip.getEntityVelocity();
		double[] position_1 = this.getEntityPosition();
		double[] position_2 = otherShip.getEntityPosition();
		double radius_1 = this.getEntityRadius();
		double radius_2 = otherShip.getEntityRadius();
		double total_radius = (radius_1 + radius_2);

		double[] delta_r = { position_2[0] - position_1[0], position_2[1] - position_1[1] };
		double[] delta_v = { velocity_2[0] - velocity_1[0], velocity_2[1] - velocity_1[1] };
		double delta_r_r = Math.pow(delta_r[0], 2) + Math.pow(delta_r[1], 2);
		double delta_v_v = Math.pow(delta_v[0], 2) + Math.pow(delta_v[1], 2);
		double delta_v_r = (delta_r[0] * delta_v[0] + delta_r[1] * delta_v[1]);
		double d = Math.pow(delta_v_r, 2) - delta_v_v * (delta_r_r - Math.pow(total_radius, 2));

		if (this.overlap(otherShip))
			throw new ModelException("The two ships are overlapping");

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
	public double[] getCollisionPosition(Ship otherShip) throws ModelException {

		double[] velocity_1 = this.getEntityVelocity();
		double[] velocity_2 = otherShip.getEntityVelocity();
		double[] position_1 = this.getEntityPosition();
		double[] position_2 = otherShip.getEntityPosition();
		double radius_1 = this.getEntityRadius();

		double time_till_overlapping = this.getTimeToCollision(otherShip);

		if (this.overlap(otherShip))
			throw new ModelException("The two ships are overlapping");

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
	
	///CONNECTIONS WITH OTHER CLASSES///
	private final Set<Bullet> bullets = new HashSet<Bullet>();
	private final World world = null;
	
}
