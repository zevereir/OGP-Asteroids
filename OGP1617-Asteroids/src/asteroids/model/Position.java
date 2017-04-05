package asteroids.model;

import be.kuleuven.cs.som.annotate.Value;
/**
 * A value class that describes position.
 * 
 * @version th of April
 * @authors Sieben Bocklandt and Ruben Broekx
 */
@Value
public class Position {
	
	/**
	 * Initializes a position.
	 * 
	 * @param 	x
	 * 			the x-component
	 * @param 	y
	 * 			the y-component
	 * 
	 * @effect 	the x and y component will be set on the given values
	 * 			@see implementation
	 */
	public Position(double x, double y) {
		this.setPositionX(x);
		this.setPositionY(y);
	}
	
	/// BASIC PROPERTIES ///
	
	private double positionX;
	private double positionY;
	
	
	/// DEFAULTS ///
	
	/**
	 * initializes a position with default values 0.
	 * 
	 * @effect 	uses the normal constructor
	 * 			@see implementation
	 */
	public Position() {
		this(0, 0);
	}
	
	
	/// GETTERS ///
	
	/**
	 * returns the position as an array.
	 * 
	 * @return 	the array
	 * 		  | result == {positionX, positionY}
	 */
	public double[] getPositionArray() {
		double[] result = { positionX, positionY };
		return result;
	}
	
	/**
	 * Return the x-component of the position.
	 * 
	 * @return 	the x-component
	 * 			@see Implementation
	 */
	public double getPositionX() {
		return positionX;
	}
	
	/**
	 * Return the y-component of the position.
	 * 
	 * @return 	the y-component
	 * 			@see Implementation
	 */
	public double getPositionY() {
		return positionY;
	}
	
	
	/// SETTERS ///
	
	/**
	 * Set the x-component of the position.
	 * 
	 * @param 	newPositionX
	 * 			The new value
	 * 
	 * @post 	the new x-component will be equal to newPositionX 
	 * 		  | new.getPositionyX == newPositionX
	 */
	public void setPositionX(double newPositionX) {
		positionX = newPositionX;
	}
	
	/**
	 * Set the y-component of the position.
	 * 
	 * @param 	newPositionY
	 * 			The new value
	 * 
	 * @post 	the new y-component will be equal to newPositionY
	 * 		  | new.getPositionY == newPositionY
	 */
	public void setPositionY(double newPositionY) {
		positionY = newPositionY;
	}
	
}
