package asteroids.model;
import java.lang.Thread.State;

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
	public static double getEuclidianDistance(double xVelocity, double yVelocity) {
		return Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2));
	}

	public boolean entityFitsInWorld(Entity entity, World world){
		double radius = this.getEntityRadius();
		double upper_bound = OMEGA*(world.getWorldHeight()-radius);
		double right_bound = OMEGA*(world.getWorldWidth()-radius);
		double x = entity.getEntityPosition()[0];
		double y = entity.getEntityPosition()[1];		
		return ((0 <x-radius) && (0 < y-radius) && (upper_bound > x) && (right_bound > y));}
	
	
	
	///GETTERS///
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
			return this.mass + bullets_weight;}
		else
			return this.mass;
				
	}
	
	public World getEntityWorld(){
		return this.world;
	}
	///SETTERS///
	//BEKIJKEN//
	public void setEntityPosition(double x, double y) throws ModelException{
		if (!isValidArray(x, y))
			throw new ModelException("Not a valide coordinate");
		if (this instanceof Ship ){
			if (((Ship)this).isValidShipPosition(x,y)){
				double[] position_array = { x, y };
				this.position = position_array;}
			else 
				throw new ModelException("Not a valide coordinate");
		}else if (this instanceof Bullet){
					null;}
		else
			throw new ModelException("Is not a legal entity");
		
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
			if (mass < maximumEntityMass())
				mass = maximumEntityMass();
		}
		else if (this instanceof Bullet){
			if (mass < maximumEntityMass())
				mass = maximumEntityMass();
		} else {
			throw new ModelException("not a legal entity");
		}
		this.mass = mass;
	}
	
	public double maximumEntityMass(){
		return 4/3*Math.PI * Math.pow(this.getEntityRadius(),3)*this.getEntityDensity();
	}
	
	public void setEntityWorld(World world){
		this.world = world;
	}
	
	///CHECKERS///
	
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
	
	///TERMINATION///
	private boolean isTerminated = false;
	
	public boolean isEntityTerminated(){
		return (this.getState() == State.TERMINATED);
	}
	
	public void Terminate(){
		null
	}
	
	///RELATIONS WITH OTHER CLASSES///
	private  World world = null;
	
	
}
	

