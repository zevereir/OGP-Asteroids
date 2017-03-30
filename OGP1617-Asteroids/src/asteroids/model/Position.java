package asteroids.model;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class Position {

	public Position(double x,double y){
		this.setX(x);
		this.setY(y);
	}
	
	public Position(){
		this(0,0);		
	}
	
	private double X;
	private double Y;
	
	public double getX() {
		return X;
	}
	public void setX(double x) {
		X = x;
	}
	public double getY() {
		return Y;
	}
	public void setY(double y) {
		Y = y;
	}
	
	public double[] getPositionArray(){
		double[] result = {X,Y};
		return result;
	}
	
	
	
}
