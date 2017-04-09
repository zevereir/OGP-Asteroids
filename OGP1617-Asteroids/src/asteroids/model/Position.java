package asteroids.model;

import be.kuleuven.cs.som.annotate.Value;
/**
 * A value class that describes position.
 * 
 * @version 7th of April
 * @authors Sieben Bocklandt and Ruben Broekx
 */
@Value
public class Position {
	
	/**
	 * Initializes a position.
	 * 
	 * @param 	positionX
	 * 			The x-component.
	 * @param 	positionY
	 * 			The y-component.
	 * 
	 * @effect 	The x and y component will be set on the given values.
	 * 			@see implementation
	 */
	public Position(double positionX, double positionY) {
		this.setPositionX(positionX);
		this.setPositionY(positionY);
	}
	
	/// BASIC PROPERTIES ///
	
	private double positionX;
	private double positionY;
	
	
	/// DEFAULTS ///
	
	/**
	 * Initializes a position with default values 0.
	 * 
	 * @effect 	Uses the normal constructor.
	 * 			@see implementation
	 */
	public Position() {
		this(0, 0);
	}
	
	
	/// GETTERS ///
	
	/**
	 * Returns the position as an array.
	 * 
	 * @return 	The array.
	 * 		  | result == {positionX, positionY}
	 */
	public double[] getPositionArray() {
		double[] result = { positionX, positionY };
		return result;
	}
	
	/**
	 * Return the x-component of the position.
	 * 
	 * @return 	The x-component.
	 * 			@see implementation
	 */
	public double getPositionX() {
		return positionX;
	}
	
	/**
	 * Return the y-component of the position.
	 * 
	 * @return 	The y-component.
	 * 			@see implementation
	 */
	public double getPositionY() {
		return positionY;
	}
	
	
	/// SETTERS ///
	
	/**
	 * Set the x-component of the position.
	 * 
	 * @param 	newPositionX
	 * 			The new value.
	 * 
	 * @post 	The new x-component will be equal to newPositionX.
	 * 		  | new.getPositionyX == newPositionX
	 */
	public void setPositionX(double newPositionX) {
		positionX = newPositionX;
	}
	
	/**
	 * Set the y-component of the position.
	 * 
	 * @param 	newPositionY
	 * 			The new value.
	 * 
	 * @post 	The new y-component will be equal to newPositionY.
	 * 		  | new.getPositionY == newPositionY
	 */
	public void setPositionY(double newPositionY) {
		positionY = newPositionY;
	}
	
}

