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
	
	public Set<? extends Object> getWorldEntities(){
		Set<Object> result = new HashSet<>();
		Set<Bullet> bullets = this.getWorldBullets();
		Set<Ship> ships = this.getWorldShips();
		result.addAll(bullets);
		result.addAll(ships);		
		return result;
	}
	
	
	public Object getEntityAt(double x, double y){
		double[] search_position = {x,y};
		
		for (Object entity: getWorldEntities()){
			if (((Entity)entity).getEntityPosition() == search_position){
				return ((Entity)entity);
			}	
		}
		return null;
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
	private Entity collision_entity_1 = null;
	private Entity collision_entity_2 = null;
	 
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
			(Entity.entityFitsInWorld(entity,this)));
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
	
	
	public double getTimeNextCollision() throws ModelException{
		double min_time = Double.POSITIVE_INFINITY;
		for (Object entity_1: getWorldEntities()){
			for (Object entity_2: getWorldEntities()){
				if (entity_2.hashCode()>entity_1.hashCode()){
					double delta_t = ((Entity)entity_1).getTimeToCollision((Entity)entity_2);
					if (delta_t < min_time){
						min_time = delta_t;
						collision_entity_1 = ((Entity)entity_1);
						collision_entity_2 = ((Entity)entity_2);
						}
					else if (delta_t == Double.POSITIVE_INFINITY){
						double dt =((Entity)entity_1).getTimeCollisionBoundary();
						if (dt < min_time){
							min_time = dt;
							collision_entity_1 = ((Entity)entity_1);
							collision_entity_2 = null;
						}
					}			
				}
			}
		}
		return min_time;
	}
		
	
	public double[] getPositionNextCollision() throws ModelException{
		if (collision_entity_1 != null && collision_entity_2!= null){
			return collision_entity_1.getCollisionPosition(collision_entity_2);
		}
		else if (collision_entity_1 != null && collision_entity_2 == null){
			return collision_entity_1.getPositionCollisionBoundary();
		}
		else {
			double infinity = Double.POSITIVE_INFINITY;
			double[] new_array = {infinity,infinity};
			return new_array;
		}
	}
	
	
	public void ShipsCollide(Entity entity1, Entity entity2){
		final double[] velocity_1 = entity1.getEntityVelocity();
		final double[] position_1 = entity1.getEntityPosition();
		final double radius_1 = entity1.getEntityRadius();
		final double mass_1 = entity1.getEntityMass();
		
		if (entity2 == null) {
			// Zie Sieben zijn stuk code om te zien of hij botst met horizontale of verticale
			if (horizontale)
				entity1.setEntityVelocity(velocity_1[0], -velocity_1[1]);
			else
				entity1.setEntityVelocity(-velocity_1[0], velocity_1[1]);
		} else {
			final double[] velocity_2 = entity2.getEntityVelocity();
			final double[] position_2 = entity2.getEntityPosition();
			final double radius_2 = entity2.getEntityRadius();
			final double mass_2 = entity2.getEntityMass();
			
			final double total_radius = (radius_1 + radius_2);
			final double delta_x = position_2[0]-position_1[0];
			final double delta_y = position_2[1]-position_1[1];
			final double[] delta_r = { position_2[0] - position_1[0], position_2[1] - position_1[1] };
			final double[] delta_v = { velocity_2[0] - velocity_1[0], velocity_2[1] - velocity_1[1] };
			double delta_v_r = (delta_r[0] * delta_v[0] + delta_r[1] * delta_v[1]);
			
			double BigJ = (2 * mass_1 * mass_2 * delta_v_r) / (total_radius * (mass_1 + mass_2));
			double Jx = (BigJ * delta_x) / total_radius;
			double Jy = (BigJ * delta_y) / total_radius;
			
			entity1.setEntityVelocity(velocity_1[0] + Jx/mass_1, velocity_1[1] + Jy/mass_1);
			entity2.setEntityVelocity(velocity_2[0] - Jx/mass_2, velocity_2[1] - Jy/mass_2);
		}	
	}
	
	public void BulletAndEntityCollide(Entity entity1, Entity entity2) throws ModelException {
		entity1.Terminate();
		entity2.Terminate();
	}
	
	
	public void BullidAndWorldCollide(Entity entity1, Entity entity2) throws ModelException{
		if (entity2 != null)
			throw new ModelException("Bullet will not collide with the world");
		int counter = ((Bullet)entity1).amountOfBounces;
		if (counter >= 2)
			entity1.Terminate();
		else {
			((Bullet)entity1).amountOfBounces = counter + 1;
			double[] Velocity = ((Bullet)entity1).getEntityVelocity();
			// Zie Sieben zijn stuk code om te zien of hij botst met horizontale of verticale
			if (horizontale)
				((Bullet)entity1).setEntityVelocity(Velocity[0], -Velocity[1]);
			else
				((Bullet)entity1).setEntityVelocity(-Velocity[0], Velocity[1]);
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
		
		
