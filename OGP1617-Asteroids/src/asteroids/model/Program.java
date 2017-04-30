package asteroids.model;

public class Program {
	
	/*
	 * The time it takes to: turn, enabling/disabling the thruster, firing a bullet or doing nothing.
	 */
	private final double defaultExecutionTime = 0.2;
	
	
	/// ACTION STATEMENTS ///
	
	/*
	 *  
	 */
	private void turn(double executionTime) {
		if (executionTime > defaultExecutionTime) {
			// The method
			
			executionTime -= defaultExecutionTime;
		}
		else {
			// Put the method on HOLD
		}
	}
	
	/*
	 * 
	 */
	private void enableThruster(double executionTime) {
		if (executionTime > defaultExecutionTime) {
			// The method
			
			executionTime -= defaultExecutionTime;
		}
		else {
			// Put the method on HOLD
		}
	}
	
	/*
	 * 
	 */
	private void disableThruster(double executionTime) {
		if (executionTime > defaultExecutionTime) {
			// The method
			
			executionTime -= defaultExecutionTime;
		}
		else {
			// Put the method on HOLD
		}
	}
	
	/*
	 * 
	 */
	private void fireBullet(double executionTime) {
		if (executionTime > defaultExecutionTime) {
			// The method
			
			executionTime -= defaultExecutionTime;
		}
		else {
			// Put the method on HOLD
		}
	}
	
	/*
	 * 
	 */
	private void rest(double executionTime) {
		if (executionTime > defaultExecutionTime) {
			executionTime -= defaultExecutionTime;
		}
		else {
			// Put the method on HOLD
		}
	}
}
