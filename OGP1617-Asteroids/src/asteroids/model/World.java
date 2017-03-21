package asteroids.model;
import java.util.HashMap;
import java.util.Map;

import banking.shares.Purchase;
import be.kuleuven.cs.som.annotate.*;
/**
 * a class that describes the world 
 * 
 * @version 21_Mar_19u
 * @authors Sieben Bocklandt and Ruben Broekx

 */
public class World {

	///CONSTRUCTOR///
	public World(double width, double height){
		setWorldWidth(width);
		setWorldHeight(height);
	}
	
	public World() {
		this(UPPER_WORLD_BOUND_WIDTH, UPPER_WORLD_BOUND_HEIGHT);
	}
	
	///BASIC PROPERTIES///
	private double width;
	private double height;
	
	
	///DEFAULTS///
	private final static double  UPPER_WORLD_BOUND_WIDTH = Double.MAX_VALUE;
	private final static double  UPPER_WORLD_BOUND_HEIGHT = Double.MAX_VALUE;
	
	
	///GETTERS///
	
	
	public double getWorldWidth(){
		return this.width;
	}
	
	public double getWorldHeight(){
		return this.height;
	}
	
	
	///SETTERS///
	
	public void setWorldWidth(double width){
		if (width > UPPER_WORLD_BOUND_WIDTH || width < 0)
			width = UPPER_WORLD_BOUND_WIDTH;
		this.width = width;
	}
	
	public void setWorldHeight(double height){
		if (height > UPPER_WORLD_BOUND_HEIGHT|| height < 0)
			height = UPPER_WORLD_BOUND_HEIGHT;
		this.height = height;
	}
	
	///CONNECTIONS WITH OTHER CLASSES///
	 private final Map<String, Ship> ships = new HashMap<String, Ship>();
	 private final Map<String, Bullet> bullets = new HashMap<String, Bullet>();
	 
	 
}
