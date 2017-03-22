package asteroids.model;
import asteroids.util.ModelException;
import be.kuleuven.cs.som.annotate.*;

public abstract class Entity {
	
	///CONSTRUCTOR///
	protected Entity(double x, double y, double xVelocity, double yVelocity, double radius,double maxVelocity
			,double density) throws ModelException{
		setEntityRadius(radius);
		setEntityMaxVelocity(maxVelocity);
		setEntityPosition(x,y);
		setEntityVelocity(xVelocity,yVelocity);
		setEntityDensity(density);
		
	}
	

	///BASIC PROPERTIES///
	private double[] position;
	private double[] velocity;
	private double radius;
	private double max_velocity;
	private double density;
	
	///DEFAULTS///
	private final static double SPEED_OF_LIGHT = 300000;
	private final static double LOWER_BULLET_RADIUS = 1;
	private final static double LOWER_SHIP_RADIUS = 10;
	
	
	
	public static double getDefaultMaxVelocity(){
		return SPEED_OF_LIGHT;
	}
	public static double getDefaultShipDensity() {
		return 1.42E12;
	}
	
	public static double getDefaultBulletDensity() {
		return 7.8E12;
	}
	
	///HELP METHODS///
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
	static double getTotalVelocity(double xVelocity, double yVelocity) {
		return Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2));
	}
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
		if (this instanceof Ship)
			return ((Ship)this).getShipOrientation();
		else
			return ((Bullet)this).getBulletOrientation();
	}
	
	public double getEntityMaxVelocity(){
		return this.max_velocity;
	}
	
	public double getEntityDensity(){
		return this.density;
	}
	
	
	
	///SETTERS///
	//BEKIJKEN//
	public void setEntityPosition(double x, double y) throws ModelException{
		if (!isValidArray(x, y))
			throw new ModelException("Not a valide coordinate");

		double[] position_array = { x, y };

		this.position = position_array;
	}
	
	public void setEntityVelocity(double xVelocity, double yVelocity){
		if (!isValidArray(xVelocity, yVelocity)) {
			if (Double.isNaN(xVelocity))
				xVelocity = 0;
			if (Double.isNaN(yVelocity))
				yVelocity = 0;
		}

		if (getTotalVelocity(xVelocity, yVelocity) > this.getEntityMaxVelocity()) {
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
		if (radius < 0)
			throw new ModelException("The given radius is negative");
		else 
			if (this instanceof Bullet)
				if (radius <LOWER_BULLET_RADIUS)
					throw new ModelException("the given radius is lower than its minimum");
		if (this instanceof Ship)
			if (radius <LOWER_SHIP_RADIUS)
				throw new ModelException("the given radius is lower than its minimum");
				
		this.radius = radius;
				
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
}
	

