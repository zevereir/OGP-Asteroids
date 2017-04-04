package asteroids.model;

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
	private final static double GAMMA = 0.01;
	
	
	///GETTERS///
	public Set<Ship> getWorldShips(){
		Set<Ship> result = new HashSet<Ship>();
		result.addAll(this.ships.values());
		return result;
	}
	
	public  Set<Bullet> getWorldBullets(){
		Set<Bullet> result = new HashSet<Bullet>();
		result.addAll(this.bullets.values());
		return result;
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
		String search_position = x+","+y;
		
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
	
	// If a negative distance is given, we convert this into a positive distance, so that the world always will be in the first quadrant.
	// The width of the world may not exceed the predefined limit.
	public void setWorldWidth(double width){
		if (width < 0)
			width = Math.abs(width);
		
		if (width > UPPER_WORLD_BOUND_WIDTH)
			width = UPPER_WORLD_BOUND_WIDTH;	
		
		this.width = width;
	}

	public void setWorldHeight(double height){
		if (height < 0) 
			height = Math.abs(height);
		
		if (height > UPPER_WORLD_BOUND_HEIGHT)
			height = UPPER_WORLD_BOUND_HEIGHT;
		
		this.height = height;
	}
	
	
	///CONNECTIONS WITH OTHER CLASSES///
	private final Map<Integer,Ship> ships = new HashMap<Integer,Ship>();
	private final Map<String,Entity> entity_positions = new HashMap<String,Entity>();
	private final Map<Integer,Bullet> bullets = new HashMap<Integer,Bullet>();
	private Entity collision_entity_1 = null;
	private Entity collision_entity_2 = null;
	 
	
	///ADDERS///
	public void addEntityToWorld(Entity entity) {
		if (canHaveAsEntity(entity)){
			entity.setEntityInWorld(this);
			if (entity instanceof Ship)
				ships.put(((Ship)entity).hashCode(),(Ship)entity);
		 	else 
				bullets.put(((Bullet)entity).hashCode(),(Bullet)entity);
			entity_positions.put(arrayToString(entity.getEntityPosition()),entity);
		} 
		else{
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
		entity_positions.remove(arrayToString(entity.getEntityPosition()));
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
	public boolean canHaveAsEntity(Entity entity){
		if (this.hasAsEntity(entity))
			// The entity already belongs to this world.
			return false;
		
		if (entity.getEntityWorld() != null)
			// The entity already belongs to a different world.
			return false;
		
		if (entity instanceof Ship)
			if (!entity.entityFitsInWorld(this))
				return false;
		
		if (entity instanceof Bullet)
			if(((Bullet)entity).getBulletShip() != null || !entity.entityFitsInWorld(this) )
				// If the bullet belongs to/is in a ship (so the bullet is not in the world), or the bullet's new position is not
				//  within the boundaries of the given position, false will be returned.
				return false;
		
		if (entity.isEntityTerminated())
			// An entity who is in the terminated state, cannot be in a world.
			return false;
		
		if (this.isWorldTerminated())
			// A terminated world cannot have any entities.
			return false;
		
		return true;			
	}
	
	public void evolve(double defaultEvolvingTime, CollisionListener collisionListener) {
		// A world cannot evolve if there are no entities or the evolving time equals zero (which means after evolving, the same
		//  situation will be achieved).
		if (!this.getWorldEntities().isEmpty())	{
					
			// Determine time till the first collision
			double TimeToCollision = getTimeNextCollision();

			double CollisionPositionX = getPositionNextCollision()[0];
			double CollisionPositionY = getPositionNextCollision()[1];

			double[] CollisionArray = {CollisionPositionX, CollisionPositionY};

			// TimeToCollision is smaller than the defaultEvolvingTime which means there will be a collision before 
			//  the defaultEvolvingTime is over.
			if (TimeToCollision <= defaultEvolvingTime) {

				// Clear the out-dated Map 'entity_positions'.
				entity_positions.clear();

				// Update the positions of the entities, along with the 'entity_positions'-Map.
				for (Object entity: getWorldEntities()) {
					// Move the entity over the predetermined time 'TimeToCollision'
					// Method 'move' will check if the given entity 'entity' is one of the entities who will collide, these entities
					//  are: 'entity_1' and 'entity_2' (entity_2 can be null when an entity collides with the world).
					((Entity)entity).move(TimeToCollision);
					
					// Update the Map 'entity_positions' for each entity with its new position.
					entity_positions.put(arrayToString(((Entity)entity).getEntityPosition()), (Entity)entity);
				}
				
				/// Check and execute the type of collision. ///
				
				// --> Collision between two ships:
				if (collision_entity_1 instanceof Ship && collision_entity_2 instanceof Ship){
					if (collisionListener != null)
						collisionListener.objectCollision(collision_entity_1, collision_entity_2,CollisionPositionX,CollisionPositionY);
					
					// Let the collision happen.
					ShipsCollide(collision_entity_1, collision_entity_2); 

					// Update the map 'enity_positions'.
					entity_positions.remove(arrayToString(collision_entity_1.getEntityPosition()));
					entity_positions.remove(arrayToString(collision_entity_2.getEntityPosition()));
					// Let the ships who will collide evolve a little more after the collision. Otherwise, the two ships would keep touching
					//  which would invoke the same collision again the next time 'evolve()' will be invoked.
					collision_entity_1.move(GAMMA*defaultEvolvingTime);
					collision_entity_2.move(GAMMA*defaultEvolvingTime);
					entity_positions.put(arrayToString(collision_entity_1.getEntityPosition()),collision_entity_1);
					entity_positions.put(arrayToString(collision_entity_2.getEntityPosition()),collision_entity_2);
				}
				
				// --> Collision between a ship and the boundary of the world:
				else if (collision_entity_1 instanceof Ship && collision_entity_2 == null){
					if (collisionListener !=null)
						collisionListener.boundaryCollision(collision_entity_1, CollisionPositionX, CollisionPositionY);
					
					// Let the collision happen.
					ShipAndWorldCollide(collision_entity_1,CollisionArray);

					// Update the map 'enity_positions'.
					entity_positions.remove(arrayToString(collision_entity_1.getEntityPosition()));
					// Let the ship who will collide evolve a little more after the collision. Otherwise, the ship would keep touching
					//  the boundary, which would invoke the same collision again the next time 'evolve()' will be invoked.
					collision_entity_1.move(GAMMA*defaultEvolvingTime);
					entity_positions.put(arrayToString(collision_entity_1.getEntityPosition()),collision_entity_1);
				}
				
				// --> Collision between a bullet and the boundary of the world:
				else if (collision_entity_1 instanceof Bullet && collision_entity_2 == null){
					if (collisionListener !=null)
						collisionListener.boundaryCollision(collision_entity_1, CollisionPositionX, CollisionPositionY);
					
					// Let the collision happen.
					BulletAndWorldCollide(collision_entity_1,CollisionArray);

					if (!collision_entity_1.isEntityTerminated()){
						// Update the map 'enity_positions'.
						entity_positions.remove(arrayToString(collision_entity_1.getEntityPosition()));
						// Let the bullet who will collide evolve a little more after the collision. Otherwise, the bullet would keep touching
						//  the boundary, which would invoke the same collision again the next time 'evolve()' will be invoked.
						collision_entity_1.move(GAMMA*defaultEvolvingTime);
						entity_positions.put(arrayToString(collision_entity_1.getEntityPosition()), collision_entity_1);
					}
				}
				
				// --> Collision between a ship and a bullet:
				else{
					double[] position = {CollisionPositionX, CollisionPositionY};
					BulletAndEntityCollide(collision_entity_1, collision_entity_2, collisionListener, position);
				}

				double remainingTime = defaultEvolvingTime - TimeToCollision;

				if (remainingTime >= 0) {
					// Invoke the method 'evolve()' in a recursive way to see if there would be other collisions in the remaining time.
					evolve(remainingTime, collisionListener);
				}
			}

			// TimeToCollision is bigger than the defaultEvolvingTime, which means no collision will take place when we evolve over 
			//  the defaultEvolvingTime. We can safely evolve the whole world (with all its entities) over defaultEvolvingTime.
			else {
				for (Object entity: getWorldEntities())
					((Entity)entity).move(defaultEvolvingTime);
			}
		}
	}
	
	public double getTimeNextCollision() {	
		double minimumCollisionTime = Double.POSITIVE_INFINITY;
		collision_entity_1 = null;
		collision_entity_2 = null;
		
		for (Object entity_1: getWorldEntities()){
			
			double timeTillCollision = ((Entity)entity_1).getTimeCollisionBoundary();
			// Collision of the entity with the boundaries of the world.
			if (timeTillCollision < minimumCollisionTime){
				minimumCollisionTime = timeTillCollision;
				collision_entity_1 = ((Entity)entity_1);
				collision_entity_2 = null;
			}
	
			// Collision of the entity with another entity in the world
			for (Object entity_2: getWorldEntities()){
				if (entity_2.hashCode() > entity_1.hashCode()){
					double delta_t = ((Entity)entity_1).getTimeToCollision((Entity)entity_2);
					if (delta_t < minimumCollisionTime){
						minimumCollisionTime = delta_t;
						collision_entity_1 = ((Entity)entity_1);
						collision_entity_2 = ((Entity)entity_2);
						}
					}			
				}	
			}		
		return minimumCollisionTime;	
	}
		
	public double[] getPositionNextCollision() {
		// Two entities will collide.
		if (collision_entity_1 != null && collision_entity_2 != null){
			return collision_entity_1.getCollisionPosition(collision_entity_2);
		}
		
		// An entity will collide with the boundary of the world.
		else if (collision_entity_1 != null && collision_entity_2 == null){
			return collision_entity_1.getPositionCollisionBoundary();
		}
		
		// No collision will take place
		else {
			double infinity = Double.POSITIVE_INFINITY;
			double[] new_array = {infinity,infinity};
			return new_array;
		}
	}
	
	public void ShipsCollide(Entity entity1, Entity entity2){
		final double position_1X = entity1.getEntityPositionX();
		final double position_1Y = entity1.getEntityPositionY();
		final double position_2X = entity2.getEntityPositionX();
		final double position_2Y = entity2.getEntityPositionY();
		final double velocity_1X = entity1.getEntityVelocityX();
		final double velocity_1Y = entity1.getEntityVelocityY();
		final double velocity_2X = entity2.getEntityVelocityX();
		final double velocity_2Y = entity2.getEntityVelocityY();
		final double radius_1 = entity1.getEntityRadius();
		final double radius_2 = entity2.getEntityRadius();
		final double mass_1 = entity1.getEntityMass();
		final double mass_2 = entity2.getEntityMass();

		final double total_radius = (radius_1 + radius_2);
		final double delta_x = position_2X - position_1X;
		final double delta_y = position_2Y - position_1Y;
		final double delta_rX = position_2X - position_1X;
		final double delta_rY = position_2Y - position_1Y ;
		final double delta_vX = velocity_2X - velocity_1X;
		final double delta_vY = velocity_2Y - velocity_1Y;
		final double delta_v_r = (delta_rX * delta_vX + delta_rY * delta_vY);

		double BigJ = (2 * mass_1 * mass_2 * delta_v_r) / (total_radius * (mass_1 + mass_2));
		double Jx = (BigJ * delta_x) / total_radius;
		double Jy = (BigJ * delta_y) / total_radius;

		entity1.setEntityVelocity(velocity_1X + Jx/mass_1, velocity_1Y + Jy/mass_1);
		entity2.setEntityVelocity(velocity_2X - Jx/mass_2, velocity_2Y - Jy/mass_2);
	}	
		
	
	public void ShipAndWorldCollide(Entity entity, double[] array) {
		double VelocityX = ((Ship)entity).getEntityVelocityX();
		double VelocityY = ((Ship)entity).getEntityVelocityY();
		
		if (collideHorizontalBoundary(entity,array))
			((Ship)entity).setEntityVelocity(VelocityX, -VelocityY);
		
		else
			((Ship)entity).setEntityVelocity(-VelocityX, VelocityY);
	}
	
	public void BulletAndEntityCollide(Entity entity1, Entity entity2, CollisionListener collisionListener, double[] position){
		// If a bullet collides with the ship who fired this bullet, the bullet will be reloaded onto the ship.
		if (entity1 instanceof Bullet && entity2 instanceof Ship && ((Bullet)entity1).getBulletSource() == ((Ship)entity2) ){
			((Bullet)entity1).setPositionWhenColliding(((Ship)entity2).getEntityPositionX(), ((Ship)entity2).getEntityPositionY());
			this.removeEntityFromWorld(entity1);
			((Ship)entity2).addOneBulletToShip((Bullet)entity1);
		} else if (entity2 instanceof Bullet && entity1 instanceof Ship && ((Bullet)entity2).getBulletSource() == ((Ship)entity1) ){
			((Bullet)entity2).setPositionWhenColliding(((Ship)entity1).getEntityPositionX(), ((Ship)entity1).getEntityPositionY());
			this.removeEntityFromWorld(entity2);
			((Ship)entity1).addOneBulletToShip((Bullet)entity2);
		} 
		
		// In all the other cases both entities will terminate each other.
		// These cases are: Or a bullet who will collide with a ship which is not the ship who fired the bullet, or two bullets who will
		//  collide with each other.
		else {
			
			
			if (collisionListener !=null){
				double CollisionPositionX = position[0];
				double CollisionPositionY = position[1];
			
				collisionListener.objectCollision(collision_entity_1,collision_entity_2,CollisionPositionX, CollisionPositionY);}
			
			entity1.Terminate();
			entity2.Terminate();
		}
	}
	public void BulletAndEntityCollide(Entity entity1, Entity entity2){
		BulletAndEntityCollide(entity1, entity2,null,null);
		
	}
	
	
	public void BulletAndWorldCollide(Entity entity,double[] array) {
		// 'counter' will count how many times the bullet has bounced off the boundaries of the world.
		int counter = ((Bullet)entity).getAmountOfBounces();
		
		// When the counter reaches 3, the bullet will be terminated.
		if (counter >= 2)
			entity.Terminate();
		
		// 'counter' is (strict) less than 3: Let the bullet bounce against the boundary.
		else {
			((Bullet)entity).setAmountOfBounces(counter + 1);
			double VelocityX = ((Bullet)entity).getEntityVelocityX();
			double VelocityY = ((Bullet)entity).getEntityVelocityY();
			
			if (collideHorizontalBoundary(entity,array))
				((Bullet)entity).setEntityVelocity(VelocityX, -VelocityY);
			
			else
				((Bullet)entity).setEntityVelocity(-VelocityX, VelocityY);
		}		
	}
	
	public boolean collideHorizontalBoundary(Entity entity, double[] array){
		return( (array[1] < GAMMA*entity.getEntityRadius()) || 
				(array[1] > (entity.getEntityWorld().getWorldHeight() - GAMMA*entity.getEntityRadius())) ); 	
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
		if (state == null){
			throw new IllegalStateException(); }
		else
			this.state = state;
	}
	
	
	///HELP FUNCTIONS///
	public String arrayToString(double[] array){
		return array[0]+","+array[1];
	}
}
		
		



