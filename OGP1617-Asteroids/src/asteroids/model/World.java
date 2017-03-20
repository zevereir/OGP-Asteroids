package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
/**
 * a class that describes the world 
 * @author sieben

 */
public class World {

	///CONSTRUCTOR///
	public World(double width, double height){
		setWorldWidth(width);
		setWorldHeight(height);
		
	}
	
	public World() {
		this(UPPER_WORLD_BOUND,UPPER_WORLD_BOUND);
	}
	
	///BASIC PROPERTIES///
	private double width;
	private double height;
	
	
	///DEFAULTS///
	private final static double  UPPER_WORLD_BOUND = Double.MAX_VALUE;
	
	
	///SETTERS///
	
	public void setWorldWidth(double width){
		if (width > UPPER_WORLD_BOUND || width < 0)
			width = UPPER_WORLD_BOUND;
		this.width = width;
	}
	
	public void setWorldHeight(double height){
		if (height > UPPER_WORLD_BOUND || height < 0)
			height = UPPER_WORLD_BOUND;
		this.height = height;
	}
	
}
