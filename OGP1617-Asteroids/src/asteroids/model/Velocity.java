package asteroids.model;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class Velocity {
	public Velocity(double xVelocity, double yVelocity){
		setVelocityX(xVelocity);
		setVelocityY(yVelocity);
	}
	public Velocity(){
		this(0,0);
	}

	private double xVelocity;
	private double yVelocity;
	
	public double getVelocityX() {
		return xVelocity;
	}
	public void setVelocityX(double newVelocityX) {
		xVelocity = newVelocityX;
	}
	public double getVelocityY() {
		return yVelocity;
	}
	public void setVelocityY(double newVelocityY) {
		yVelocity = newVelocityY;
	}
	public double[] getVelocityArray(){
		double[] result = {xVelocity, yVelocity};
		return result;
	}

}
