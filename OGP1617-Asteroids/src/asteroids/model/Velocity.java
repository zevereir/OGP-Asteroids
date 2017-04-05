package asteroids.model;

import be.kuleuven.cs.som.annotate.Value;
/**
 * A value class that describes velocity.
 * 
 * @version 5th of April
 * @authors Sieben Bocklandt and Ruben Broekx
 *
 */
@Value
public class Velocity {
	
	/**
	 * Initializes a velocity.
	 * 
	 * @param 	xVelocity
	 * 			the x-component
	 * @param 	yVelocity
	 * 			the y-component
	 * 
	 * @effect 	the x and y component will be set on the given values
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
	 * initializes a velocity with default values 0.
	 * 
	 * @effect 	uses the normal constructor
	 * 			@see implementation
	 */
	public Velocity() {
		this(0, 0);
	}
	
	
	/// GETTERS /// 

	/**
	 * returns the velocity as an array.
	 * 
	 * @return 	the array
	 * 		  | result == {xVelocity, yVelocity}
	 */
	public double[] getVelocityArray() {
		double[] result = { xVelocity, yVelocity };
		return result;
	}
	
	/**
	 * Return the x-component of the velocity.
	 * 
	 * @return 	the x-component
	 * 			@see Implementation
	 */
	public double getVelocityX() {
		return xVelocity;
	}
	
	/**
	 * Return the y-component of the velocity.
	 * 
	 * @return 	the y-component
	 * 			@see Implementation
	 */
	public double getVelocityY() {
		return yVelocity;
	}
	
	
	/// SETTERS /// 
	
	/**
	 * Set the x-component of the velocity.
	 * 
	 * @param 	newVelocityX
	 * 			The new value
	 * 
	 * @post 	the new x-component will be equal to newVelocityX 
	 * 		  | new.getVelocityX == newvelocityX
	 */
	public void setVelocityX(double newVelocityX) {
		xVelocity = newVelocityX;
	}
	
	/**
	 * Set the y-component of the velocity.
	 * 
	 * @param 	newVelocityY
	 * 			The new value
	 * @post 	the new y-component will be equal to newVelocityY
	 * 		  | new.getVelocityY == newVelocityY
	 */
	public void setVelocityY(double newVelocityY) {
		yVelocity = newVelocityY;
	}

}
