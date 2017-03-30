package asteroids.model;

public class Velocity {

	double xVel;
	double yVel;
	
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
