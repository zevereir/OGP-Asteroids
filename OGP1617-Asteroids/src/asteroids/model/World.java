package asteroids.model;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import asteroids.part2.CollisionListener;
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
	private final static double OMEGA = 0.99;
	
	
	///GETTERS///
	
	
	public Set<Ship> getWorldShips(){
		return this.ships;
	}
	
	public Set<Bullet> getWorldBullets(){
		return this.bullets;
	}
	
	public double[] getWorldSize(){
		double width = this.width;
		double height = this.height;
		double[] size_array = {width,height};
		return size_array;
	}
	///SETTERS///
	
	// --> RUBEN <-- //
	// --> Negative values become positive values, containing the same absolute value! <-- //
	public void setWorldWidth(double width){
		if (width > UPPER_WORLD_BOUND_WIDTH)
			width = UPPER_WORLD_BOUND_WIDTH;
		else if (width < 0) {
			width = Math.abs(width);
			if (width > UPPER_WORLD_BOUND_WIDTH)
				width = UPPER_WORLD_BOUND_WIDTH;
		}
		this.width = width;
	}
	// --> RUBEN <-- //
	public void setWorldHeight(double height){
		if (height > UPPER_WORLD_BOUND_HEIGHT)
			height = UPPER_WORLD_BOUND_HEIGHT;
		else if (height < 0) {
			height = Math.abs(height);
			if (height > UPPER_WORLD_BOUND_HEIGHT)
				height = UPPER_WORLD_BOUND_HEIGHT;
		}
		this.height = height;
	}
	
	///CONNECTIONS WITH OTHER CLASSES///
	private final Set<Ship> ships = new HashSet<Ship>();
	private final Set<Bullet> bullets = new HashSet<Bullet>();
	 
	///ADDERS///
	
	public void addEntityToWorld(Entity entity) throws ModelException {
		if (canHaveAsEntity(entity)){
			if (entity instanceof Ship){
				ships.add((Ship)entity);
		 		((Ship)entity).setEntityInWorld(this); 
		 	} else {
				bullets.add((Bullet)entity);
				((Bullet)entity).setEntityInWorld(this);
			}
		} else{
			throw new ModelException("the entity cannot be added to this world");
		}
	}
	 
	
	///REMOVERS///
	public void removeEntityFromWorld(Entity entity){
		if (entity instanceof Ship){
			this.ships.remove((Ship)entity);}
		else if (entity instanceof Bullet){
			this.bullets.remove((Bullet)entity);		
		}
	}
	///HAS///
	public boolean hasAsEntity(Entity entity){
		if (entity instanceof Ship)
			return this.ships.contains(entity);
		else
			return this.bullets.contains(entity);
	}        
	
	///CHECKERS///
	
	
	// --> OVERLAP,TERMINATE,... NOG BEKIJKEN <-- //
	public boolean canHaveAsEntity(Entity entity){
		return ((!this.hasAsEntity(entity)) &&(entity.getEntityWorld()==null) &&
			(entity.entityFitsInWorld(entity,this)));
	}
	
	
	// RUBEN //
	// dt = evolving time (a predetermined value)
	public void evolve(double dt, CollisionListener collisionListener) throws ModelException {
		int TimeToCollision = 0;
		
		// STEP 1: Predict for all entities the time to collision
		for (All entities in world) {
			// STEP 2: Determine the smallest (from all entities) TimeToCollision
		}
		if (TimeToCollision < dt) {
			// Advance all ships and bullets 'TimeToCollision' seconds (= time till first collision)
			// Remark! Be aware of the thruster whom can change the velocity of a ship.
				
			// STEP 3: Resolve the collision
			
			// STEP 4: Subtract 'TimeToCollision' from dt and go back to step 1
		} else {
			// STEP 5: Advance all ships and bullets dt seconds
			for (All entities in world) {
				// Advance the entity using its current position and velocity
				// Remark! Be aware of the thruster whom can change the velocity of a ship.
			}
		}
	}
	 
	 
	///TERMINATION AND STATES///
		
		
		
		
	public void Terminate() throws ModelException{
		if (!isWorldTerminated()){
			setWorldState(State.TERMINATED);
			for (Bullet bullet: this.getWorldBullets())
				bullet.setEntityFree();
			for (Ship ship:this.getWorldShips())
				ship.setEntityFree();			 
		}
	}
	 

	private State state = State.NOTTERMINATED;

	private static enum State {
		NOTTERMINATED,TERMINATED;	
	}

	public State getState(){
		return this.state;
	}

	public boolean isWorldTerminated(){
		return this.getState() == State.TERMINATED;
	}

	public boolean hasWorldProperState(){
		return (!isWorldTerminated())^isWorldTerminated();
	}

	public void setWorldState(State state) throws ModelException{
		if (state == null)
			throw new ModelException("this is not a valid state");
		else
			this.state = state;
	}
}
		
		
