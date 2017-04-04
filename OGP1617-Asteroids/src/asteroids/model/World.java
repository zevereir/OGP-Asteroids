package asteroids.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import asteroids.part2.CollisionListener;

/**
 * A class that describes worlds. A world has a height and a width and can contain bullets and ships.
 * 
 * @invar the worlds height will be a positive number
 * 			|getWorldHeight >0
 * @invar the worlds width will be a positive number
 * 			|getWorldwidth >0
 * 
 * 
 * @version 4th of April - 01u25
 * @authors Sieben Bocklandt and Ruben Broekx

 */
public class World {

	///CONSTRUCTOR///
	/**
	 * Initializes a new world with given width and height
	 * @param width
	 * 			the width of the world
	 * @param height
	 * 			the height of the world
	 * @effect the width and height will be set
	 * 			@see implementation
	 */
	public World(double width, double height){
		setWorldWidth(width);
		setWorldHeight(height);
	}
	/**
	 * initializes a world with default values
	 * @effect a new world is made with the upperbounds as argument.
	 * 			@see implementation
	 */
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
	/**
	 * Return the ships that are in the world
	 * @return the set of ships
	 * 			@see implementation
	 */
	public Set<Ship> getWorldShips(){
		Set<Ship> result = new HashSet<Ship>();
		result.addAll(this.ships.values());
		return result;
	}
	/**
	 * return the bullets that are in the world
	 * @return the set of bullets
	 * 			@see implementation
	 */
	public  Set<Bullet> getWorldBullets(){
		Set<Bullet> result = new HashSet<Bullet>();
		result.addAll(this.bullets.values());
		return result;
	}
	
	/**
	 * Return the size of the world
	 * @return the size as an array
	 * 			@see implementation
	 */
	public double[] getWorldSize(){
		double width = this.width;
		double height = this.height;
		double[] size_array = {width,height};
		return size_array;
	}
	/**
	 * Return the width of the world
	 * @return the width
	 * 			@see implementation
	 */
	public double getWorldWidth(){
		return this.getWorldSize()[0];
	}
	/**
	 * Return the height of the world
	 * @return the height
	 * 			@see implementation
	 */
	public double getWorldHeight(){
		return this.getWorldSize()[1];
	}
	/**
	 * return the set of all the entities in the world.
	 * @return this set of entities
	 * 			@see implementation
	 */
	public Set<? extends Object> getWorldEntities(){
		Set<Object> result = new HashSet<>();
		result.addAll(this.getWorldBullets());
		result.addAll(this.getWorldShips());		
		return result;
	}
	
	/**
	 * Return the entity positioned at the given position
	 * @param x
	 * 			The x-value of the position 
	 * @param y
	 * 			the y-value of the position
	 * @return the entity that has the given position as position, else null is returned
	 * 			@see implementation
	 */
	public Object getEntityAt(double x, double y){
		String search_position = x+","+y;
		
		if (entity_positions.containsKey(search_position))
			return entity_positions.get(search_position);
		else
			return null;
	}
	
	
	///SETTERS///
	/** 
	 * Set the worlds size
	 * @param width
	 * 			The new width
	 * @param height
	 * 			The new height
	 * @effect the width and height will be seton the new values
	 * 			@see implementation
	 */
	public void setWorldSize(double width, double height){
		setWorldWidth(width);
		setWorldHeight(height);
	}
	
	// If a negative distance is given, we convert this into a positive distance, so that the world always will be in the first quadrant.
	// The width of the world may not exceed the predefined limit.
	/**
	 * Set the worlds width
	 * @param width
	 * 			The new width
	 * @post if the given width is negative, the new width is the absolute value;
	 * 			|new.getWorldWidth() == Math.abs(width)
	 * @post if the given width is too big, it will be set on the upper bound.
	 * 			|new.getWorldWidth() == UPPER_WORLD_BOUND_WIDTH
	 * @post else the width will be set on the given value
	 * 			|new.getWorldWidth() == width
	 */
	public void setWorldWidth(double width){
		if (width < 0)
			width = Math.abs(width);
		
		if (width > UPPER_WORLD_BOUND_WIDTH)
			width = UPPER_WORLD_BOUND_WIDTH;	
		
		this.width = width;
	}
	/**
	 * Set the worlds height
	 * @param height
	 * 			The new height
	 * @post if the given height is negative, the new height is the absolute value;
	 * 			|new.getWorldHeight() == Math.abs(height)
	 * @post if the given height is too big, it will be set on the upper bound.
	 * 			|new.getWorldheight() == UPPER_WORLD_BOUND_HEIGHT
	 * @post else the height will be set on the given value
	 * 			|new.getWorldHeight() == height
	 */
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
	/**
	 * Add a given entity to the world
	 * @param entity
	 * 			The entity that has to be added to the world
	 * @effect the entity's world will be set on this
	 * 			|entity.setEntityInWorld(this);
	 * @effect if the entity is a bullet, the bullet will be added to the bullets of the world
	 * 			@see implementation
	 * @effect if the entity is a ship, the ship will be added to the ships of the world
	 * 			@see implementation
	 * @effect the entity will be added with its position as key into the entity_positions map
	 * 			@see implementation
	 * @throws IllegalArgumentException
	 * 			if the eworld cannot have this entity
	 * 			|(!canHaveAsEntity(entity))
	 *
	 */
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
	
	/**
	 * Removes the given entity from the world
	 * @param entity
	 * 			The entity that has to be removed
	 * @effect the ship or bullet will be removed from the respective set
	 * 			@see implementation
	 * @effect the entity will be removed together with its key from the entity_positions list
	 * 			@see implementation
	 * @effect the entity will be set on state NoWorld
	 * 			@see implementation
	 */
	public void removeEntityFromWorld(Entity entity) {
		if (entity instanceof Ship){
			this.ships.remove(((Ship)entity).hashCode());}
		else if (entity instanceof Bullet){
			this.bullets.remove(((Bullet)entity).hashCode());		
		}
		entity_positions.remove(arrayToString(entity.getEntityPosition()));
		entity.setEntityNoWorld();
	}
	
	
	///HAS///
	/**
	 * Checks whether the world has this entity or not
	 * @param entity
	 * 			The entity that has to be checked
	 * @return the boolean that checks if the world has the entity	
	 * 			@see implementation
	 * 			
	 */
	public boolean hasAsEntity(Entity entity){
		if (entity instanceof Ship)
			return this.ships.containsValue(entity);
		else
			return this.bullets.containsValue(entity);
	}        
	
	
	///CHECKERS///
	/**
	 * Checks if the world can have this entity
	 * @param entity
	 * 			The entity that has to be checked
	 * @return false if the entity already has a world
	 * 			|result == (entity.getEntityWorld() == null)
	 * @return false if the entity is a ship and it doesn't fit in the world
	 * 			|result== (entity instanceof Ship && entity.entityFitsInWorld(this))
	 * @return false if the entity is a bullet and it doesn't fit in the world or it's loaded on a ship.
	 * 			|result== (entity instanceof Bullet && ((Bullet)entity).getBulletShip() == null && entity.entityFitsInWorld(this) )
	 * @return false if the entity or the world is terminated
	 * 			|result == (!entity.isEntityterminated() && !this.isWorldTerminated())
	 */
	public boolean canHaveAsEntity(Entity entity){
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
	
	/**
	 *Evolve the world by a given time, resolving collsions that will happen.
	 * @param defaultEvolvingTime
	 * 			The time the world has to evolve
	 * @param collisionListener
	 * 			An object of the class CollisionListener that is used to draw explosions
	 * @effect The entities in the world that not collide, move dt.
	 * 			|entity.move(defaultCollisionTime)
	 * @effect the entities in the world that collide with each other will collide and the collision will be resolved
	 * 			|@see implementation
	 * 
	 */
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
	/**
	 * Return the time until the next collision
	 * @return the minimum of all the collisions to the boundaries and all the collisions that can happen in a world.
	 * 			@see implementation
	 * @effect if there is a collision, collision_entity_1 (and collision_entity_2 if it's a collision between two entities) will be set
	 * 		   on the entities that collide
	 * 			@see implementation
	 */
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
	/**
	 * Return the position where the next collision will take place.
	 * @return the position, this is the collision position if both collision_entity_1 and collision_entity_2 are not null.
	 * 			The position is the positionCollisionBoundary when only collision_entity_2 is null. When there are no collision, so 
	 * 			both collision_entity_1 and collision_entity_2 are null, an infinity arry is returned
	 * 			@see implementation
	 * 
	 * 
	 */
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
	/**
	 * Let two ships collide with eachother
	 * @param entity1
	 * 			the first ship
	 * @param entity2
	 * 			The second ship
	 * @effect The velocities will be changed (this will be calculated by the formula we use)
	 * 			@see implementation
	 */
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
		
	/**
	 * Let a world and a ship collide
	 * @param entity
	 * 		The ship that collides
	 * @param array
	 * 			The CollisionPosition array
	 * @effect if the bullet collides with a horizontal boundary, the y-component of the velocity will change sign. If the collision happens with a vertical 
	 * 		   boundary, the x-component changes sign
	 * 			@see implementation
	 */
	public void ShipAndWorldCollide(Entity entity, double[] array) {
		double VelocityX = ((Ship)entity).getEntityVelocityX();
		double VelocityY = ((Ship)entity).getEntityVelocityY();
		
		if (collideHorizontalBoundary(entity,array))
			((Ship)entity).setEntityVelocity(VelocityX, -VelocityY);
		
		else
			((Ship)entity).setEntityVelocity(-VelocityX, VelocityY);
	}
	
	/**
	 * Let a bullet and an entity collide
	 * @param entity1
	 * 			The bullet or the entity
	 * @param entity2
	 * 			The entity or the bullet
	 * @param collisionListener
	 * 			The object of the class CollisionListene used to draw explosions
	 * @param position
	 * 			the collision position
	 * @effect If the entity is the source if the bullet, the bullet is reloaded on the ship and removed from the world.
	 * 			@see implementation
	 * @effect
	 * 			If the entity is not the source_ship, they both will be terminated and collisionListener is invoked.
	 * 			@see implementation
	 
	 */
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
	
	
	/**
	 * Let a world and a bullet collide
	 * @param bullet
	 * 			The bullet
	 * @param array
	 * 			The collision position
	 * @effect if the bullet has bounced more than two times, it will be terminated.
	 * 			@see implementation
	 * @effect the bullets bounces will be incremented by 1, if the boundary was horizontal, the y-velocity changed sign.
	 * 		   If the boundary was vertical, the x-velocity changed.
	 * 			@see implementation
	 */
	public void BulletAndWorldCollide(Entity bullet,double[] array) {
		// 'counter' will count how many times the bullet has bounced off the boundaries of the world.
		int counter = ((Bullet)bullet).getAmountOfBounces();
		
		// When the counter reaches 3, the bullet will be terminated.
		if (counter >= 2)
			bullet.Terminate();
		
		// 'counter' is (strict) less than 3: Let the bullet bounce against the boundary.
		else {
			((Bullet)bullet).setAmountOfBounces(counter + 1);
			double VelocityX = ((Bullet)bullet).getEntityVelocityX();
			double VelocityY = ((Bullet)bullet).getEntityVelocityY();
			
			if (collideHorizontalBoundary(bullet,array))
				((Bullet)bullet).setEntityVelocity(VelocityX, -VelocityY);
			
			else
				((Bullet)bullet).setEntityVelocity(-VelocityX, VelocityY);
		}		
	}
	
	/**
	 * Checks if a collsion with a boundary is with a horizontal or a vertical one.
	 * @param entity
	 * 			The entity that collides
	 * @param array
	 * 			the Collision position
	 * @return True if the collision position is +- the height or 0.
	 * 			@see implementation
	 */
	public boolean collideHorizontalBoundary(Entity entity, double[] array){
		return( (array[1] < GAMMA*entity.getEntityRadius()) || 
				(array[1] > (entity.getEntityWorld().getWorldHeight() - GAMMA*entity.getEntityRadius())) ); 	
	}
	
	
	///TERMINATION AND STATES///
	/**
	 * Terminate this world
	 * @effect  the worlds state is set on Terminated and all its entities are set on NoWorld.
	 * 			@see implementation
	 */
	public void Terminate() {
		if (!isWorldTerminated()){
			setWorldState(State.TERMINATED);
			for (Bullet bullet: this.getWorldBullets())
				bullet.setEntityNoWorld();
			for (Ship ship:this.getWorldShips())
				ship.setEntityNoWorld();			 
		}
	}
	
	private State state = State.NOTTERMINATED;

	private static enum State {
		NOTTERMINATED,TERMINATED;	
	}

	/** 
	 * Return the state of the world
	 * @return the state
	 * 			@see implementation
	 */
	public State getState(){
		return this.state;
	}
	
	/**
	 * Checks whether the world is terminated or not
	 * @return the boolean that checks if the world is terminated
	 * 			@see implementation
	 */
	public boolean isWorldTerminated(){
		return this.getState() == State.TERMINATED;
	}
	/**
	 * Checks if the world has a proper state (which is terminated or not terminated)
	 * @return the boolean that checks the proper state
	 * 			@see implementation
	 */
	public boolean hasWorldProperState(){
		return (!isWorldTerminated())^isWorldTerminated();
	}

	/**
	 * Set the state of the world to a given state
	 * @param state
	 * 			The new state
	 * @post the new state will be aqual to the given state
	 * 			|new.getState() == state
	 * @throws IllegalStateException
	 * 			if the given state is null
	 * 			|state == null
	 */
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
		
		



