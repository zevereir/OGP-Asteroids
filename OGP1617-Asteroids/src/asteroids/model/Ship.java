package asteroids.model;

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
 * @version 9_Mar_17u
 * @authors Sieben Bocklandt and Ruben Broekx
 */
public class Ship {

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
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation,double mass,double density,
			boolean thrusterActivity,double maxVelocity,double thrusterForce) throws ModelException {
		setShipRadius(radius);
		setShipMaxVelocity(maxVelocity);
		setShipPosition(x, y);
		setShipOrientation(orientation);
		setShipDensity(density);
		setShipMass(mass);
		setShipVelocity(xVelocity, yVelocity);
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
	//WITH DENSITY//
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation,double mass,double density)
			throws ModelException {
		this(x, y, xVelocity, yVelocity, radius, orientation,mass,density,getDefaultThrusterActivity(), getDefaultMaxVelocity(),getDefaultThrusterForce());
	}
	
	/**
	 * Initializes a new ship with given values and a maximum total velocity
	 * equal to the speed of light.
	 * 
	 * @effect The ship is initialized with the given values and the default
	 *         maximum total velocity. |this(x, y, xVelocity, yVelocity, radius,
	 *         orientation, getDefaultMaxVelocity())
	 */
	//WITHOUT DENSITY//
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation,double mass)
			throws ModelException {
		this(x, y, xVelocity, yVelocity, radius, orientation,mass,getDefaultDensity(),getDefaultThrusterActivity(),
				getDefaultMaxVelocity(),getDefaultThrusterForce());
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
				getDefaultRadius(), getDefaultOrientation(),getDefaultMass(),getDefaultDensity(), getDefaultThrusterActivity(),getDefaultMaxVelocity(),getDefaultThrusterForce());
	}

	
	/// BASIC PROPERTIES ///

	private double[] position;
	private double[] velocity;
	private double radius;
	private double orientation;
	private double mass;
	private double density;
	private double max_velocity;
	private boolean thruster_activity;
	private double thruster_force;

	
	/// DEFAULTS ///

	private final static double LOWER_RADIUS = 10;
	private final static double SPEED_OF_LIGHT = 300000;
	
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
	 * Return the default orientation of the ship.
	 * 
	 * @return The default orientation is a value between 0 and 2*PI with 0 =
	 *         right, PI/2 = up and so on. | 0 <= result <= 2*PI
	 */
	@Immutable
	public static double getDefaultOrientation() {
		return 0;
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
	 * Return the default maximum total velocity of the ship.
	 * 
	 * @return The default maximum velocity, this will always be equal to the
	 *         speed of light. | result = SPEED_OF_LIGHT
	 * 
	 */
	public static double getDefaultMaxVelocity() {
		return SPEED_OF_LIGHT;
	}
	
	
	public static double getDefaultMass() {
		return 4/3*Math.PI * Math.pow(getDefaultRadius(),3)*getDefaultDensity();
	}
	
	public static double getDefaultDensity() {
		return 1.42E12;
	}

	
	public static boolean getDefaultThrusterActivity(){;
		return false;
	}
	
	public static double getDefaultThrusterForce(){
		return 1.1E21;
	}
	
	
	/// GETTERS ///

	/**
	 * returns the position of the ship as an array.
	 * 
	 * @return The position of the ship. |result = this.position
	 */
	@Basic
	public double[] getShipPosition() {
		return this.position;
	}

	/**
	 * Returns the velocity of the ship as an array.
	 * 
	 * @return The velocity of the ship. |result = this.velocity
	 */
	@Basic
	public double[] getShipVelocity() {
		return this.velocity;
	}

	/**
	 * Returns the radius of the ship.
	 * 
	 * @return The radius of the ship. |result = this.radius
	 */
	@Basic
	public double getShipRadius() {
		return this.radius;
	}

	/**
	 * Returns the orientation of the ship.
	 * 
	 * @return The orientation of the ship. |result = this.orientation
	 */
	@Basic
	public double getShipOrientation() {
		return this.orientation;
	}
	
	//WE NEED TO ADD THE WEIGHT OF BULLETS//
	public double getShipMass() {
		return this.mass;
	}

	
	public double getShipDensity() {
		return this.density;
	}
	/**
	 * Returns the maximum total velocity the ship can reach.
	 * 
	 * @return The maximum velocity of the ship. |result = this.max_velocity
	 */
	@Basic
	public double getShipMaxVelocity() {
		return this.max_velocity;
	}
	
	public boolean isThrusterActive(){
		return this.thruster_activity;
	}
	
	public double getShipAcceleration(){
		return (this.thruster_force/this.getShipMass());
	}

	/// SETTERS ///

	/**
	 * Give the ship a new position.
	 * 
	 * @param x
	 *            The new x-coordinate (horizontal) of the ships position.
	 * @param y
	 *            The new y-coordinate (vertical) of the ships position.
	 * 
	 * @post If the new x and y are both possible values, the new position of
	 *       the ship is {x,y}. |if (isValidArray(x,y)) | new.getShipPosition()
	 *       == {x,y}
	 * 
	 * @throws ModelException
	 *             If x or y is not a number. |(!isValidArray(x,y))
	 * 
	 */
	public void setShipPosition(double x, double y) throws ModelException {
		if (!isValidArray(x, y))
			throw new ModelException("Not a valide coordinate");

		double[] position_array = { x, y };

		this.position = position_array;
	}

	/**
	 * Checks whether an array has two values of the type double.
	 * 
	 * @param x
	 *            The first value of the array that has to be checked.
	 * @param y
	 *            The second value of the array that has to be checked.
	 * 
	 * @return True if both x and y are type Double and not of the type NaN.
	 *         |result = ((! Double.isNaN(x)) && (! Double.isNaN(y)))
	 */
	public boolean isValidArray(double x, double y) {
		return ((!Double.isNaN(x)) && (!Double.isNaN(y)));
	}

	/**
	 * Give the ship a new velocity.
	 * 
	 * @param xVelocity
	 *            The new velocity of the ship projected on the x-axis
	 *            (horizontal).
	 * @param yVelocity
	 *            The new velocity of the ship projected on the y-axis
	 *            (vertical).
	 * 
	 * @post If both xVelocity and yVelocity are possible values, the velocity
	 *       of the ship will be set on {xVelocity,yVelocity}. |if
	 *       (isValidArray(xVelocity,yVelocity)) | new.getShipVelocity() ==
	 *       {xVelocity,yVelocity}
	 * @post If xVelocity is not a number (NaN), it's value will be set on 0.
	 *       |if (Double.isNaN(xVelocity) | xVelocity == 0
	 * @post If yVelocity is not a number (NaN), it's value will be set on 0.
	 *       |if (Double.isNaN(yVelocity) | yVelocity == 0
	 * @post If the total velocity of the ship (the square root of the sum of
	 *       xVelocity squared and yVelocity squared) is greater than its
	 *       maximum velocity, the new velocity of the ship will be its maximum
	 *       velocity projected on the x- and y-axis. |if
	 *       (getTotalVelocity(xVelocity,yVelocity) > this.getShipMaxVelocity())
	 *       | new.getShipVelocity() ==
	 *       {Math.cos(this.getShipOrientation())*this.getShipMaxVelocity(), |
	 *       Math.sin((this.getShipOrientation())*this.getShipMaxVelocity()}
	 */
	public void setShipVelocity(double xVelocity, double yVelocity) {
		if (!isValidArray(xVelocity, yVelocity)) {
			if (Double.isNaN(xVelocity))
				xVelocity = 0;
			if (Double.isNaN(yVelocity))
				yVelocity = 0;
		}

		if (getTotalVelocity(xVelocity, yVelocity) > this.getShipMaxVelocity()) {
			double orientation = this.getShipOrientation();
			double xVel = Math.cos(orientation) * this.getShipMaxVelocity();
			double yVel = Math.sin(orientation) * this.getShipMaxVelocity();

			double[] velocity_array = { xVel, yVel };
			this.velocity = velocity_array;
		}

		else {
			double[] velocity_array = { xVelocity, yVelocity };
			this.velocity = velocity_array;
		}
	}

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
	public double getTotalVelocity(double xVelocity, double yVelocity) {
		return Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2));
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
	public void setShipOrientation(double radians) {
		assert isValidRadian(radians);
		this.orientation = radians;
	}

	/**
	 * Checks wether the given radian is in a correct range.
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

	/**
	 * Gives the ship a new radius.
	 * 
	 * @param radius
	 *            The new radius for the ship that defines it's circular shape.
	 * 
	 * @post If the given radius is greater than a Lower radius, the new radius
	 *       of the ship will be equal to the given radius. |if (radius >
	 *       LOWER_RADIUS) |new.getSHipRadius() == radius
	 * 
	 * @throws ModelException
	 *             If the radius is smaller than the lower_radius. |(radius <
	 *             LOWER_RADIUS)
	 */
	public void setShipRadius(double radius) throws ModelException {
		if (radius < LOWER_RADIUS)
			throw new ModelException(
					"The radius is lower than the underbound of " + LOWER_RADIUS + " km, please try again.");
		this.radius = radius;
	}

	public void setShipDensity(double density) {
		if (density < getDefaultDensity())
			density = getDefaultDensity();
		else
			this.density = density;
			
	}
	
	public void setShipMass(double mass) {
		if (mass < 4/3*Math.PI * Math.pow(this.getShipRadius(),3)*this.getShipDensity() )
			mass = 4/3*Math.PI * Math.pow(this.getShipRadius(),3)*this.getShipDensity();
		else
			this.mass = mass;
	}
	
	
	
	
	/**
	 * Gives the ship a new maximum total velocity.
	 * 
	 * @param limit
	 *            The new maximum velocity.
	 * 
	 * @post If the given limit is negative, 0 or greater than the speed of
	 *       light, the max_velocity will be set on the speed of light. |if
	 *       ((limit<=0) || (limit > SPEED_OF_LIGHT)) | new.getShipMaxVelocity()
	 *       == getDefaultMaxVelocity()
	 * @post If the given limit is a valid limit (positive and lower than speed
	 *       of light), the new maximum velocity will be set on this limit.
	 *       |if(((limit < SPEED_OF_LIGHT)&&(limit > 0)) |
	 *       new.getShipMaxVelocity() = limit;
	 */

	public void setShipMaxVelocity(double limit) {
		if ((limit < SPEED_OF_LIGHT) && (limit > 0))
			this.max_velocity = limit;
		else
			this.max_velocity = getDefaultMaxVelocity();
	}

	
	public void setThrusterActive(boolean thrusterActivity){
		this.thruster_activity = thrusterActivity;
	
	}
	public void setShipThrusterForce(double thrusterForce){
		if (thrusterForce <0)
			thrusterForce = getDefaultThrusterForce();
		this.thruster_force = thrusterForce;
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
	public void move(double dt) throws ModelException {
		if (dt < 0)
			throw new ModelException("Give a positive time please.");

		final double[] velocity = this.getShipVelocity();
		final double[] position = this.getShipPosition();
		final double delta_x = velocity[0] * dt;
		final double delta_y = velocity[1] * dt;

		this.setShipPosition(position[0] + delta_x, position[1] + delta_y);
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
		assert isValidRadian(this.getShipOrientation() + angle);
		this.setShipOrientation(this.getShipOrientation() + angle);
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
	public void thrust(double amount) {
		if (amount < 0)
			amount = 0;
		double thrust_x = this.getShipVelocity()[0] + amount * Math.cos(this.getShipOrientation());
		double thrust_y = this.getShipVelocity()[1] + amount * Math.sin(this.getShipOrientation());

		this.setShipVelocity(thrust_x, thrust_y);
	}


	public void thrustOn(){
		setThrusterActive(true);
	}
	public void thrustOff(){
		setThrusterActive(false);
	}
	
	/// METHODS ON TWO SHIPS///
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

		final double[] first_pos = this.getShipPosition();
		final double[] second_pos = otherShip.getShipPosition();

		final double distance_centers = Math
				.sqrt(Math.pow(first_pos[0], second_pos[0]) + Math.pow(first_pos[1], second_pos[1]));
		final double distance = distance_centers - (this.getShipRadius() + otherShip.getShipRadius());

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
		double[] velocity_1 = this.getShipVelocity();
		double[] velocity_2 = otherShip.getShipVelocity();
		double[] position_1 = this.getShipPosition();
		double[] position_2 = otherShip.getShipPosition();
		double radius_1 = this.getShipRadius();
		double radius_2 = otherShip.getShipRadius();
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

		double[] velocity_1 = this.getShipVelocity();
		double[] velocity_2 = otherShip.getShipVelocity();
		double[] position_1 = this.getShipPosition();
		double[] position_2 = otherShip.getShipPosition();
		double radius_1 = this.getShipRadius();

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
}
