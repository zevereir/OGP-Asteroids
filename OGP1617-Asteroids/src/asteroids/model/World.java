package asteroids.model;

//import be.kuleuven.cs.som.annotate.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import asteroids.part2.CollisionListener;

/**
 * a class that describes the world 
 * 
 * @version 28th of march
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
		Set<Ship> result = new HashSet<Ship>();
		result.addAll(this.ships.values());
		return  result;
	}
	
	public  Set<Bullet> getWorldBullets(){
		Set<Bullet> result = new HashSet<Bullet>();
		result.addAll(this.bullets.values());
		return  result;
		
	}
	
	public double[] getWorldSize(){
		double width = this.width;
		double height = this.height;
		double[] size_array = {width,height};
		return size_array;
	}
	public double getWorldWidth(){
		return this.getWorldSize()[0];
	}
	public double getWorldHeight(){
		return this.getWorldSize()[1];
	}
	
	public Set<? extends Object> getWorldEntities(){
		Set<Object> result = new HashSet<>();
		result.addAll(this.getWorldBullets());
		result.addAll(this.getWorldShips());		
		return result;
	}
	

	
	public Object getEntityAt(double x, double y){
		double[] search_position = {x,y};
		System.out.println(entity_positions.keySet());
		if (entity_positions.containsKey(search_position))
				return entity_positions.get(search_position);
		else
			return null;
	}
	
	
			
	///SETTERS///
	public void setWorldSize(double width, double height){
		setWorldWidth(width);
		setWorldHeight(height);
	}
	
	
	public void setWorldWidth(double width){
		if (width < 0) {
		width = Math.abs(width);}
		if (width > UPPER_WORLD_BOUND_WIDTH)
			width = UPPER_WORLD_BOUND_WIDTH;	
		
		this.width = width;
	}

	public void setWorldHeight(double height){
		if (height < 0) {
			height = Math.abs(height);
		}
		if (height > UPPER_WORLD_BOUND_HEIGHT)
			height = UPPER_WORLD_BOUND_HEIGHT;
		this.height = height;
	}
	
	///CONNECTIONS WITH OTHER CLASSES///
	private final Map<Integer,Ship> ships = new HashMap<Integer,Ship>();
	private final Map<double[],Entity> entity_positions = new HashMap<double[],Entity>();
	private final Map<Integer,Bullet> bullets = new HashMap<Integer,Bullet>();
	private Entity collision_entity_1 = null;
	private Entity collision_entity_2 = null;
	 
	///ADDERS///
	
	public void addEntityToWorld(Entity entity) {
		if (canHaveAsEntity(entity)){
			entity.setEntityInWorld(this);
			if (entity instanceof Ship){
				ships.put(((Ship)entity).hashCode(),(Ship)entity);
		 	} else {
				bullets.put(((Bullet)entity).hashCode(),(Bullet)entity);
			}
			entity_positions.put(entity.getEntityPosition(),entity);
		} else{
			throw new IllegalArgumentException() ;
		}
	}
	
	///REMOVERS///
	public void removeEntityFromWorld(Entity entity) {
		if (entity instanceof Ship){
			this.ships.remove(((Ship)entity).hashCode());}
		else if (entity instanceof Bullet){
			this.bullets.remove(((Bullet)entity).hashCode());		
		}
		entity_positions.remove(entity.getEntityPosition());
		entity.setEntityFree();
	}
	///HAS///
	public boolean hasAsEntity(Entity entity){
		if (entity instanceof Ship)
			return this.ships.containsValue(entity);
		else
			return this.bullets.containsValue(entity);
	}        
	
	///CHECKERS///
	
	
	// --> OVERLAP,TERMINATE,... NOG BEKIJKEN <-- //
	public boolean canHaveAsEntity(Entity entity){
		if (this.hasAsEntity(entity))
			return false;
		if (entity.getEntityWorld()!=null)
			return false;
		if (entity instanceof Ship){
			if (!Entity.entityFitsInWorld(entity,this))
				return false;}
		if (entity instanceof Bullet){
			if(((Bullet)entity).getBulletSource() == null){
				if (!Entity.entityFitsInWorld(entity,this))
					return false;}
			}
		if (entity.isEntityTerminated())
			return false;
		if (this.isWorldTerminated())
			return false;
		return true;			
	}
	
	
	// dt = evolving time (a predetermined value)
		public void evolve(double dt, CollisionListener collisionListener) {
		
					
			//This function does nothing if there are no entities
			if (!this.getWorldEntities().isEmpty())	{
			// Determine time till the first collision
			double TimeToCollision = getTimeNextCollision();
			double CollisionPositionX = getPositionNextCollision()[0];
			double CollisionPositionY = getPositionNextCollision()[1];
			double[] CollisionArray = {CollisionPositionX,CollisionPositionY};
			
			// TimeToCollision is smaller than the evolve-time
			if (TimeToCollision < dt) {
				// Update the positions of the entities, along with the 'entity_positions'-Map
				// Clear the out-dated Map 'entity_positions'
				entity_positions.clear();
				for (Object entity: getWorldEntities()) {
					// Move the entity over the predefined time 'TimeToCollision'
					// Method 'move' will check if the given entity 'entity' is one of the entities who will collide, these entities
					//  are: 'entity_1' and 'entity_2' (entity_2 can be null when the entity collides with the world)
					((Entity)entity).move(TimeToCollision,collision_entity_1,collision_entity_2);
					// Update the Map 'entity_positions' for each entity with its new position
					entity_positions.put(((Entity)entity).getEntityPosition(), (Entity)entity);
				}
				
			
				// Check and execute the type of collision
				if (collision_entity_1 instanceof Ship && collision_entity_2 instanceof Ship){
					if (collisionListener !=null)
						collisionListener.objectCollision(collision_entity_1, collision_entity_2,CollisionPositionX,CollisionPositionY);
					ShipsCollide(collision_entity_1, collision_entity_2); 
					
					collision_entity_1.move((1-OMEGA)*TimeToCollision);
					collision_entity_2.move((1-OMEGA)*TimeToCollision);}
				else if (collision_entity_1 instanceof Ship && collision_entity_2 == null){
					if (collisionListener !=null)
						collisionListener.boundaryCollision(collision_entity_1, CollisionPositionX, CollisionPositionY);
					ShipAndWorldCollide(collision_entity_1,CollisionArray);
					collision_entity_1.move((1-OMEGA)*TimeToCollision);}
				else if (collision_entity_1 instanceof Bullet && collision_entity_2 == null){
					if (collisionListener !=null)
						collisionListener.boundaryCollision(collision_entity_1, CollisionPositionX, CollisionPositionY);
					BulletAndWorldCollide(collision_entity_1,CollisionArray);
					collision_entity_1.move((1-OMEGA)*TimeToCollision);}
				else{
					if (collisionListener !=null)
						collisionListener.objectCollision(collision_entity_1,collision_entity_2,CollisionPositionX, CollisionPositionY);
					BulletAndEntityCollide(collision_entity_1, collision_entity_2);
				}
				
				double newTime = dt - TimeToCollision;
					
				// Invoke the method evolve in a recursive way, make sure that the thrusters will be turned off, otherwise the velocity
				//  will keep incrementing
				evolve(newTime, collisionListener);}
				
				 
			
			// TimeToCollision is bigger than the evolve-time, which means no collision will take place when we evolve over the dt-time
			else {
				for (Object entity: getWorldEntities())	{
					
					((Entity)entity).move(dt);
				}
			}
			}
	}
	
	
	public double getTimeNextCollision() {
		double min_time = Double.POSITIVE_INFINITY;
		
		for (Object entity_1: getWorldEntities()){
			double dt = ((Entity)entity_1).getTimeCollisionBoundary();
			
			if (dt < min_time){
				min_time = dt;
				collision_entity_1 = ((Entity)entity_1);
				collision_entity_2 = null;
				}
			for (Object entity_2: getWorldEntities()){
				if (entity_2.hashCode() > entity_1.hashCode()){
					double delta_t = ((Entity)entity_1).getTimeToCollision((Entity)entity_2);
					if (delta_t < min_time){
						min_time = delta_t;
						collision_entity_1 = ((Entity)entity_1);
						collision_entity_2 = ((Entity)entity_2);
					}
				}			
			}	
		}
		return min_time;
	}
		
	
	public double[] getPositionNextCollision() {
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
		final double[] velocity_2 = entity2.getEntityVelocity();
		final double[] position_1 = entity1.getEntityPosition();
		final double[] position_2 = entity2.getEntityPosition();
		final double radius_1 = entity1.getEntityRadius();
		final double radius_2 = entity2.getEntityRadius();
		final double mass_1 = entity1.getEntityMass();
		final double mass_2 = entity2.getEntityMass();

		final double total_radius = (radius_1 + radius_2);
		final double delta_x = position_2[0]-position_1[0];
		final double delta_y = position_2[1]-position_1[1];
		final double[] delta_r = { position_2[0] - position_1[0], position_2[1] - position_1[1] };
		final double[] delta_v = { velocity_2[0] - velocity_1[0], velocity_2[1] - velocity_1[1] };
		final double delta_v_r = (delta_r[0] * delta_v[0] + delta_r[1] * delta_v[1]);

		double BigJ = (2 * mass_1 * mass_2 * delta_v_r) / (total_radius * (mass_1 + mass_2));
		double Jx = (BigJ * delta_x) / total_radius;
		double Jy = (BigJ * delta_y) / total_radius;

		entity1.setEntityVelocity(velocity_1[0] + Jx/mass_1, velocity_1[1] + Jy/mass_1);
		entity2.setEntityVelocity(velocity_2[0] - Jx/mass_2, velocity_2[1] - Jy/mass_2);
	}	
		
	
	public void ShipAndWorldCollide(Entity entity,double[] array) {
		double[] Velocity = ((Ship)entity).getEntityVelocity();
		if (collideHorizontalBoundary(entity,array))
			((Ship)entity).setEntityVelocity(Velocity[0], -Velocity[1]);
		else
			((Ship)entity).setEntityVelocity(-Velocity[0], Velocity[1]);
	}
	
	public void BulletAndEntityCollide(Entity entity1, Entity entity2){
		if (entity1 instanceof Bullet && entity2 instanceof Bullet){
			entity1.Terminate();
			entity2.Terminate();
		} else if (entity1 instanceof Bullet && entity2 instanceof Ship && ((Bullet)entity1).getBulletShip() == ((Ship)entity2) ){
			((Ship)entity2).addOneBulletToShip((Bullet)entity1);
		} else if (entity2 instanceof Bullet && entity1 instanceof Ship && ((Bullet)entity2).getBulletShip() == ((Ship)entity1) ){
			((Ship)entity1).addOneBulletToShip((Bullet)entity2);
		} else {
			entity1.Terminate();
			entity2.Terminate();
		}
	}
			
	
	public void BulletAndWorldCollide(Entity entity,double[] array) {
		int counter = ((Bullet)entity).getAmountOfBounces();
		if (counter >= 2)
			entity.Terminate();
		else {
			((Bullet)entity).setAmountOfBounces(counter + 1);
			double[] Velocity = ((Bullet)entity).getEntityVelocity();
			if (collideHorizontalBoundary(entity,array))
				((Bullet)entity).setEntityVelocity(Velocity[0], -Velocity[1]);
			else
				((Bullet)entity).setEntityVelocity(-Velocity[0], Velocity[1]);
		}		
	}
	
	
	public boolean collideHorizontalBoundary(Entity entity, double[] array){
		return(array[1]<(1-OMEGA)*entity.getEntityRadius() || 
				array[1]> OMEGA*entity.getEntityWorld().getWorldHeight()); 	
	}
	 
	 
	///TERMINATION AND STATES///

	public void Terminate() {
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

	public void setWorldState(State state) {
		if (state == null)
			throw new IllegalStateException();
		else
			this.state = state;
	}
}
		
		
