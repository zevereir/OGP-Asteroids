package asteroids.model;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class Velocity {
	public Velocity(double xVel, double yVel){
		setXVel(xVel);
		setYVel(yVel);
	}
	public Velocity(){
		this(0,0);
	}

	private double xVel;
	private double yVel;
	
	public double getXVel() {
		return xVel;
	}
	public void setXVel(double x) {
		xVel = x;
	}
	public double getYVel() {
		return yVel;
	}
	public void setYVel(double y) {
		yVel = y;
	}
	public double[] getVelocityArray(){
		double[] result = {xVel,yVel};
		return result;
	}

}
