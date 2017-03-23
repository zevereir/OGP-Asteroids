package asteroids.model;

import java.util.HashSet;
import java.util.Set;
import asteroids.util.ModelException;
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
	 private final Set<Ship> ships = new HashSet<Ship>();
	 private final Set<Bullet> bullets = new HashSet<Bullet>();
	 
	///ADDERS///
	 
	 public void addShipToWorld(Ship ship) throws ModelException {
		 double x_position = ship.getShipPosition()[0];
		 double y_position = ship.getShipPosition()[1];
		 double radius = ship.getShipRadius();
		 
		 double upper_ship_bound = OMEGA*(this.height-radius);
		 double right_ship_bound = OMEGA*(this.width-radius);
		 
		 if ((radius < x_position) && (radius < y_position) && (upper_ship_bound > x_position) && 
				 (right_ship_bound > y_position)&& (!this.hasAsShip(ship))){
		 	String name = ship.toString();
		 	ships.put(name, ship); }
		  else {
			throw new ModelException("the ship cannot be added to this world");
		 }
		
	 }
	 
	 ///HAS///
	 public boolean hasAsShip(Ship ship){
		 try {
	            return this.ships.get(ship.toString()) == ship;
	        }
	        catch (NullPointerException exc) {
	            assert (ship == null) || (ship.getShipWorld() == null);
	            return false;
	        }
	    }
}
