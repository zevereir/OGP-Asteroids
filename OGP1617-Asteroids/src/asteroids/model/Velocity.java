package asteroids.model;

import be.kuleuven.cs.som.annotate.Value;
/**
 * A value class that describes velocity.
 * 
 * @version 7th of April
 * @authors Sieben Bocklandt and Ruben Broekx
 *
 */
@Value
public class Velocity {
	
	/**
	 * Initializes a velocity.
	 * 
	 * @param 	xVelocity
	 * 			The x-component.
	 * @param 	yVelocity
	 * 			The y-component.
	 * 
	 * @effect 	The x and y component will be set on the given values.
	 * 			@see implementation
	 */
	public Velocity(double xVelocity, double yVelocity) {
		setVelocityX(xVelocity);
		setVelocityY(yVelocity);
	}
	
	
	/// BASIC PROPERTIES ///
	
	private double xVelocity;
	private double yVelocity;
	
	
	/// DEFAULTS ///
	
	/**
	 * Initializes a velocity with default values 0.
	 * 
	 * @effect 	Uses the normal constructor.
	 * 			@see implementation
	 */
	public Velocity() {
		this(0, 0);
	}
	
	
	/// GETTERS /// 

	/**
	 * Returns the velocity as an array.
	 * 
	 * @return 	The array.
	 * 		  | result == {xVelocity, yVelocity}
	 */
	public double[] getVelocityArray() {
		double[] result = { xVelocity, yVelocity };
		return result;
	}
	
	/**
	 * Return the x-component of the velocity.
	 * 
	 * @return 	The x-component.
	 * 			@see implementation
	 */
	public double getVelocityX() {
		return xVelocity;
	}
	
	/**
	 * Return the y-component of the velocity.
	 * 
	 * @return 	The y-component.
	 * 			@see implementation
	 */
	public double getVelocityY() {
		return yVelocity;
	}
	
	
	/// SETTERS /// 
	
	/**
	 * Set the x-component of the velocity.
	 * 
	 * @param 	newVelocityX
	 * 			The new value.
	 * 
	 * @post 	The new x-component will be equal to newVelocityX.
	 * 		  | new.getVelocityX == newvelocityX
	 */
	public void setVelocityX(double newVelocityX) {
		xVelocity = newVelocityX;
	}
	
	/**
	 * Set the y-component of the velocity.
	 * 
	 * @param 	newVelocityY
	 * 			The new value.
	 * 
	 * @post 	The new y-component will be equal to newVelocityY.
	 * 		  | new.getVelocityY == newVelocityY
	 */
	public void setVelocityY(double newVelocityY) {
		yVelocity = newVelocityY;
	}

}
