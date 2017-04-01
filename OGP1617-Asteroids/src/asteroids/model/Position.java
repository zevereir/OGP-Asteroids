package asteroids.model;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class Position {

	public Position(double x,double y){
		this.setPositionX(x);
		this.setPositionY(y);
	}
	
	public Position(){
		this(0,0);		
	}
	
	private double positionX;
	private double positionY;
	
	public double getPositionX() {
		return positionX;
	}
	public void setPositionX(double newPositionX) {
		positionX = newPositionX;
	}
	public double getPositionY() {
		return positionY;
	}
	public void setPositionY(double newPositionY) {
		positionY = newPositionY;
	}
	
	public double[] getPositionArray(){
		double[] result = {positionX, positionY};
		return result;
	}
	
	
	
}
