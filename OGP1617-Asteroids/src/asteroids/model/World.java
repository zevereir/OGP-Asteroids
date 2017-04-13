package asteroids.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import asteroids.part2.CollisionListener;

/**
 * A class that describes worlds. A world has a height and a width and can contain bullets and ships.
 * 
 * @invar 	The world's height will be a positive number.
 * 		  | getWorldHeight > 0
 * @invar 	The world's width will be a positive number.
 * 		  | getWorldwidth > 0
 * 
 * 
 * @version 9th of April
 * @authors Sieben Bocklandt and Ruben Broekx

 */
public class World {

	/// CONSTRUCTOR ///
	
	/**
	 * Initializes a new world with given width and height.
	 * 
	 * @param 	width
	 * 			The width of the world.
	 * @param 	height
	 * 			The height of the world.
	 * 
	 * @effect 	The width and height will be set.
	 * 			@see implementation
	 */
	public World(double width, double height) {
		setWorldSize(width,height);
	}

	/**
	 * Initializes a world with the default size.
	 * 
	 * @effect 	A new world with the maximum size is made.
	 * 			@see implementation
	 */
	public World() {
		this(UPPER_WORLD_BOUND_WIDTH, UPPER_WORLD_BOUND_HEIGHT);
	}

	
	/// BASIC PROPERTIES ///

	private double width;
	private double height;

	
	/// CONSTANTS ///
	
	/**
	 * The maximum width a world can have.
	 */
	private final static double UPPER_WORLD_BOUND_WIDTH = Double.MAX_VALUE;
	
	/**
	 * The maximum height a world can have.
	 */
	private final static double UPPER_WORLD_BOUND_HEIGHT = Double.MAX_VALUE;
	
	/**
	 * A constant that is used to "correct" the errors that occur when using double values.
	 */
	private final static double GAMMA = 0.01;

	
	/// GETTERS ///

	/**
	 * Return the entity positioned at the given position.
	 * 
	 * @param 	positionX
	 * 			The x-value of the position.
	 * @param 	positionY
	 * 			The y-value of the position.
	 * 
	 * @return 	Null if there is no entity at the given position.
	 * 			@see implementation
	 * @return 	The entity that is at the given position, if there is an entity at this position.
	 * 			@see implementation
	 */
	public Object getEntityAt(double positionX, double positionY) {
		String searchPosition = (positionX + "," + positionY);

		if (entity_positions.containsKey(searchPosition))
			return entity_positions.get(searchPosition);
		
		else
			return null;
	}

	/**
	 * Return the position where the next collision will take place.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 * 
	 * @return 	The position of collision between two entities, this is the position if both collision_entity_1 and 
	 * 			collision_entity_2 are not null.
	 * 			@see implementation
	 * @return	The position of collision between an entity and a boundary of the world, this is the position 
	 * 			when collision_entity_1 is not null, but collision_entity_2 is. 
	 * 			@see implementation
	 * @return 	An array of two with both values set to POSITIVE_INFINITY, this is when there are no collisions, so 
	 * 			both collision_entity_1 and collision_entity_2 are null.
	 * 			@see implementation
	 */
	public double[] getPositionNextCollision() {
		// Two entities will collide.
		if (collision_entity_1 != null && collision_entity_2 != null)
			return collision_entity_1.getCollisionPosition(collision_entity_2);

		// An entity will collide with the boundary of the world.
		else if (collision_entity_1 != null && collision_entity_2 == null)
			return collision_entity_1.getPositionCollisionBoundary();

		// No collision will take place
		else {
			double infinity = Double.POSITIVE_INFINITY;
			double[] new_array = { infinity, infinity };
			return new_array;
		}
	}

	/** 
	 * Return the state of the world.
	 * 
	 * @return 	The state.
	 * 			@see implementation
	 */
	private State getState() {
		return this.state;
	}

	/**
	 * Return the time until the next collision.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 * 
	 * @effect 	If there is a collision, collision_entity_1 (and collision_entity_2 if it's a collision between two entities) 
	 * 			will be set on the entities that collide. collision_entity_2 will be null if the first collision that happens,
	 * 			is a collision with the world.
	 * 			@see implementation
	 * 
	 * @return 	The time till the first collision in a world will happen.
	 * 			@see implementation
	 */
	public double getTimeNextCollision() {
		double minimumCollisionTime = Double.POSITIVE_INFINITY;
		
		collision_entity_1 = null;
		collision_entity_2 = null;

		for (Object entity_1 : getWorldEntities()) {
			double timeTillCollision = ((Entity) entity_1).getTimeCollisionBoundary();
			
			// Collision of the entity with the boundaries of the world.
			if (timeTillCollision < minimumCollisionTime) {
				minimumCollisionTime = timeTillCollision;
				collision_entity_1 = ((Entity) entity_1);
				collision_entity_2 = null;
			}

			// Collision of the entity with another entity in the world
			for (Object entity_2 : getWorldEntities()) {
				if (entity_2.hashCode() > entity_1.hashCode()) {
					double delta_t = ((Entity) entity_1).getTimeToCollision((Entity) entity_2);
					if (delta_t < minimumCollisionTime) {
						minimumCollisionTime = delta_t;
						collision_entity_1 = ((Entity) entity_1);
						collision_entity_2 = ((Entity) entity_2);
					}
				}
			}
		}
		return minimumCollisionTime;
	}

	/**
	 * Return the bullets that are in the world.
	 * 
	 * @return 	The set of bullets in the world.
	 * 			@see implementation
	 */
	public Set<Bullet> getWorldBullets() {
		Set<Bullet> result = new HashSet<Bullet>();

		result.addAll(this.bullets.values());

		return result;
	}
	
	/**
	 * Return the ships that are in the world.
	 * 
	 * @return 	The set of ships in the world.
	 * 			@see implementation
	 */
	public Set<Ship> getWorldShips() {
		Set<Ship> result = new HashSet<Ship>();

		result.addAll(this.ships.values());

		return result;
	}

	/**
	 * Return the set of all the entities in the world.
	 * 
	 * @return 	The set of entities in the world.
	 * 			@see implementation
	 */
	public Set<? extends Object> getWorldEntities() {
		Set<Object> result = new HashSet<>();

		result.addAll(this.getWorldBullets());
		result.addAll(this.getWorldShips());

		return result;
	}

	/**
	 * Return the height of the world.
	 * 
	 * @return 	The height of the world.
	 * 			@see implementation
	 */
	protected double getWorldHeight() {
		return this.getWorldSize()[1];
	}

	/**
	 * Return the width of the world.
	 * 
	 * @return 	The width of the world.
	 * 			@see implementation
	 */
	protected double getWorldWidth() {
		return this.getWorldSize()[0];
	}


	/**
	 * Return the size of the world.
	 * 
	 * @return 	The size of the world, expressed as an array.
	 * 			@see implementation
	 */
	public double[] getWorldSize() {
		double width = this.width;
		double height = this.height;
		double[] size_array = { width, height };

		return size_array;
	}

	
	/// SETTERS ///

	/**
	 * Set the world's height.
	 * 
	 * @param 	height
	 * 			The new height.
	 * 
	 * @post 	If the given height is negative, the new height is the absolute value of the given height.
	 * 		  | new.getWorldHeight() == Math.abs(height)
	 * @post 	If the given height is too big, the height of the world will be set to the upper bound.
	 * 		  | new.getWorldHeight() == UPPER_WORLD_BOUND_HEIGHT
	 * @post 	In all the other cases the given height will be a valid height and will be set as the 
	 * 			new height of the world.
	 * 		  | new.getWorldHeight() == height
	 */
	private void setWorldHeight(double height) {
		if (height < 0)
			height = Math.abs(height);

		if (height > UPPER_WORLD_BOUND_HEIGHT)
			height = UPPER_WORLD_BOUND_HEIGHT;

		this.height = height;
	}

	/** 
	 * Set the world's size.
	 * 
	 * @param 	width
	 * 			The new width.
	 * @param 	height
	 * 			The new height.
	 * 
	 * @effect 	The width and height will be set on the new values.
	 * 			@see implementation
	 */
	private void setWorldSize(double width, double height) {
		setWorldWidth(width);
		setWorldHeight(height);
	}

	/**
	 * Set the state of the world to a given state.
	 * 
	 * @param 	state
	 * 			The new state.
	 * 
	 * @post 	The new state will be equal to the given state.
	 * 		  | new.getState() == state
	 * 
	 * @throws 	IllegalStateException()
	 * 			if the given state is null.
	 * 		  | state == null
	 */
	private void setWorldState(State state) {
		if (state == null)
			throw new IllegalStateException();
		
		else
			this.state = state;
	}
	
	/**
	 * Set the world's width.
	 * 
	 * @param 	width
	 * 			The new width.
	 * 
	 * @post 	If the given width is negative, the new width is the absolute value of the given width.
	 * 		  | new.getWorldWidth() == Math.abs(width)
	 * @post 	If the given width is too big, the width of the world will be set to the upper bound.
	 * 		  | new.getWorldWidth() == UPPER_WORLD_BOUND_WIDTH
	 * @post 	In all the other cases the given width will be a valid width and will be set as the 
	 * 			new width of the world.
	 * 		  | new.getWorldWidth() == width
	 */
	private void setWorldWidth(double width) {
		if (width < 0)
			width = Math.abs(width);

		if (width > UPPER_WORLD_BOUND_WIDTH)
			width = UPPER_WORLD_BOUND_WIDTH;

		this.width = width;
	}


	
	/// CHECKERS ///

	/**
	 * Checks if the world can have this entity.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 * 
	 * @param 	entity
	 * 			The entity that has to be checked.
	 * 
	 * @return 	False if the world already has this entity.
	 * 			@see implementation
	 * @return 	False if the entity already has a world.
	 * 			@see implementation
	 * @return 	False if the entity is a ship and it does not fit in the world.
	 *			@see implementation
	 * @return 	False if the entity is a bullet and it doesn't fit in the world or it's loaded on a ship.
	 *			@see implementation
	 * @return 	False if the entity or the world is terminated.
	 *			@see implementation
	 */
	protected boolean canHaveAsEntity(Entity entity) {
		// The entity already belongs to this world.
		if (this.hasAsEntity(entity))
			return false;

		// The entity already belongs to a different world.
		if (entity.getEntityWorld() != null)
			return false;

		// The entity is a ship and does not fit in the world.
		if (entity instanceof Ship)
			if (!entity.entityFitsInWorld(this))
				return false;

		// If the bullet belongs to a ship (which means the bullet is in a ship, and not in the world) or
		// the bullet's new position is not within the boundaries of the world, false will be returned.
		if (entity instanceof Bullet && (((Bullet) entity).getBulletShip() != null || !entity.entityFitsInWorld(this)))
			return false;

		// An entity who is in the terminated state, cannot be in a world.
		if (entity.isEntityTerminated())
			return false;

		// A terminated world cannot have any entities.
		if (this.isWorldTerminated())
			return false;

		return true;
	}

	/**
	 * Checks whether an given entity lies in the world.
	 * 
	 * @param 	entity
	 * 			The entity that has to be checked.
	 * 
	 * @return 	The boolean that checks if the world has the entity.
	 * 			@see implementation
	 */
	protected boolean hasAsEntity(Entity entity) {
		if (entity instanceof Ship)
			return this.ships.containsValue(entity);

		else
			return this.bullets.containsValue(entity);
	}

	/**
	 * Checks if the world has a proper state (which is terminated or not terminated).
	 * 
	 * @return 	The boolean that checks the proper state.
	 * 			@see implementation
	 */
	protected boolean hasWorldProperState() {
		return (!isWorldTerminated()) ^ isWorldTerminated();
	}

	/**
	 * Checks whether the world is terminated or not.
	 * 
	 * @return 	The boolean that checks if the world is terminated.
	 * 			@see implementation
	 */
	public boolean isWorldTerminated() {
		return this.getState() == State.TERMINATED;
	}


	/// HELP FUNCTIONS ///
	
	/**
	 * This method is used to create the keys in the map "entity_positions".
	 * 
	 * @param	array
	 * 			An array, which will always represent a position in this class.
	 * 			
	 * @return	A position in form of a string.
	 */
	private String arrayToString(double[] array) {
		return (array[0] + "," + array[1]);
	}

	
	/// ADDERS ///

	/**
	 * Add a given entity to the world.
	 * 
	 * @param 	entity
	 * 			The entity that has to be added to the world.
	 * 
	 * @effect 	The entity's world will be set on "this", which represents this world.
	 * 		  | entity.setEntityInWorld(this)
	 * @effect 	If the entity is a bullet, the bullet will be added to the bullets of the world.
	 * 			@see implementation
	 * @effect 	If the entity is a ship, the ship will be added to the ships of the world.
	 * 			@see implementation
	 * @effect 	The entity will be added with its position as key into the entity_positions map.
	 * 			@see implementation
	 * 
	 * @throws 	IllegalArgumentException
	 * 			If the world cannot have this entity.
	 * 		  | (!canHaveAsEntity(entity))
	 *
	 */
	public void addEntityToWorld(Entity entity) {
		if (canHaveAsEntity(entity)) {
			entity.setEntityInWorld(this);

			if (entity instanceof Ship)
				ships.put(((Ship) entity).hashCode(), (Ship) entity);

			else
				bullets.put(((Bullet) entity).hashCode(), (Bullet) entity);

			entity_positions.put(arrayToString(entity.getEntityPosition()), entity);
		} 
		else
			throw new IllegalArgumentException();
	}

	
	/// REMOVERS ///
	
	/**
	 * Removes the given entity from the world.
	 * 
	 * @param 	entity
	 * 			The entity that has to be removed.
	 * 
	 * @effect 	The ship or bullet will be removed from the respective set.
	 * 			@see implementation
	 * @effect 	The entity will be removed together with its key from the entity_positions list.
	 * 			@see implementation
	 * @effect 	The entity will be set on state NO_WORLD.
	 * 			@see implementation
	 * @throws IllegalArgumentException
	 * 			If the world doesn't have the entity.
	 * 			@see implementation
	 */
	public void removeEntityFromWorld(Entity entity) {
		
		if (!this.getWorldEntities().contains(entity))
			throw new IllegalArgumentException();
		
		else if (entity instanceof Ship)
			this.ships.remove(((Ship) entity).hashCode());

		else if (entity instanceof Bullet)
			this.bullets.remove(((Bullet) entity).hashCode());

		entity_positions.remove(arrayToString(entity.getEntityPosition()));
		entity.setEntityFree();
	}


	/// EVOLVE ///
	
	/**
	 * Evolve the world by the given time "defaultEvolvingTime" and resolve collisions that will happen.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 * @note	The method does not have a specification. See the assignment for more information.
	 */
	public void evolve(double defaultEvolvingTime, CollisionListener collisionListener) {
		// A world cannot evolve if there are no entities or the evolving time equals zero 
		// (which would mean that after evolving, the same situation will be achieved).
		if (!this.getWorldEntities().isEmpty()) {

			// Determine time till the first collision.
			double timeToCollision = getTimeNextCollision();

			double collisionPositionX = getPositionNextCollision()[0];
			double collisionPositionY = getPositionNextCollision()[1];

			double[] collisionArray = { collisionPositionX, collisionPositionY };

			// timeToCollision is smaller than the defaultEvolvingTime which means there will be a 
			// collision before the defaultEvolvingTime is over.
			if (timeToCollision <= defaultEvolvingTime) {

				// Clear the out-dated Map 'entity_positions'.
				entity_positions.clear();

				// Update the positions of the entities, along with the 'entity_positions'-Map.
				for (Object entity : getWorldEntities()) {
					// Move the entity over the predetermined time 'timeToCollision'
					// The method 'move' will check if the given entity 'entity' is one of the 
					// entities who will collide, these entities are: 'entity_1' and 'entity_2' 
					// (entity_2 can be null when an entity, entity_1, collides with the world).
					((Entity) entity).move(timeToCollision);

					// Update the Map 'entity_positions' for each entity with its new position.
					entity_positions.put(arrayToString(((Entity) entity).getEntityPosition()), (Entity) entity);
				}

				// Check and execute the type of collision.
				letCollisionHappen(collisionArray, defaultEvolvingTime, collisionListener);				
				
				double remainingTime = defaultEvolvingTime - timeToCollision;

				if (remainingTime >= 0) {
					// Invoke the method 'evolve()' in a recursive way to see if there would be other 
					// collisions in the remaining time.
					evolve(remainingTime, collisionListener);
				}
				else
					throw new IllegalArgumentException();
			}

			// timeToCollision is bigger strict than the defaultEvolvingTime, which means no collision will 
			// take place when we evolve over the defaultEvolvingTime. We can safely evolve the whole world
			// (with all its entities) over defaultEvolvingTime.
			else {
				for (Object entity : getWorldEntities())
					((Entity) entity).move(defaultEvolvingTime);
			}
		}
	}
		
	
	/// COLLISION-FUNCTIONS ///
	/**
	 * A variable that contains an entity when there's a collision in the future.
	 */
	private Entity collision_entity_1 = null;
	
	/**
	 * A variable that contains an entity when there's a collision between two entity's in the future.
	 */
	private Entity collision_entity_2 = null;

	/**
	 * Let a bullet and an entity collide.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 * 
	 * @param 	entity1
	 * 			The bullet or the entity that will collide.
	 * @param 	entity2
	 * 			The entity if entity1 is a bullet, or the bullet otherwise, that will collide.
	 * @param 	collisionListener
	 * 			The object of the class CollisionListener used to draw explosions.
	 * @param 	collisionArray
	 * 			The collision position.
	 * 
	 * @effect 	If the entity is the source of the bullet, the bullet will be reloaded onto the ship and removed from the world.
	 * 			@see implementation
	 * @effect 	If the entity is not the source_ship, both the bullet and the entity will be terminated and collisionListener 
	 * 			will be invoked.
	 * 			@see implementation
	 */
	private void BulletAndEntityCollide(Entity entity1, Entity entity2, CollisionListener collisionListener, double[] collisionArray) {
		double position1X = ((Entity)entity1).getEntityPositionX();
		double position1Y = ((Entity)entity1).getEntityPositionY();
		double position2X = ((Entity)entity2).getEntityPositionX();
		double position2Y = ((Entity)entity2).getEntityPositionY();
		
		// If a bullet collides with the ship who fired this bullet, the bullet will be reloaded onto the ship.
		if (entity1 instanceof Bullet && entity2 instanceof Ship && ((Bullet) entity1).getBulletSource() == ((Ship) entity2)) {
			((Bullet) entity1).setPositionWithoutChecking(position2X, position2Y);
			this.removeEntityFromWorld(entity1);
			((Ship) entity2).addOneBulletToShip((Bullet) entity1);
		} else if (entity2 instanceof Bullet && entity1 instanceof Ship && ((Bullet) entity2).getBulletSource() == ((Ship) entity1)) {
			((Bullet) entity2).setPositionWithoutChecking(position1X, position1Y);
			this.removeEntityFromWorld(entity2);
			((Ship) entity1).addOneBulletToShip((Bullet) entity2);
		}

		// In all the other cases both entities will terminate each other. These cases are: Or a bullet who will collide
		// with a ship which is not the ship who fired the bullet, or two bullets who will collide with each other.
		else {
			if (collisionListener != null) {
				double collisionPositionX = collisionArray[0];
				double collisionPositionY = collisionArray[1];

				collisionListener.objectCollision(collision_entity_1, collision_entity_2, collisionPositionX, collisionPositionY);
			}
			entity1.Terminate();
			entity2.Terminate();
		}
	}

	/**
	 * Let a bullet collide with the boundaries of the world.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 * 
	 * @param 	bullet
	 * 			The bullet that will collide with the boundary.
	 * @param 	collisionArray
	 * 			The collision position.
	 * 
	 * @effect 	If the bullet has bounced more than two times, it will be terminated.
	 * 			@see implementation
	 * @effect 	The bullet's amount of bounces will be incremented by 1. If the boundary was horizontal, 
	 * 			the y-velocity changes sign. If the boundary was vertical, the x-velocity changes sign.
	 * 			@see implementation
	 */
	private void BulletAndWorldCollide(Bullet bullet, double[] collisionArray) {
		// 'counter' will count how many times the bullet has bounced off the boundaries of the world.
		int counter = bullet.getAmountOfBounces();

		// When the counter reaches 3, the bullet will be terminated.
		if (counter >= 2)
			bullet.Terminate();

		// If 'counter' is (strict) less than 3, the bullet will bounce against the boundary.
		else {
			bullet.setAmountOfBounces(counter + 1);
			
			double VelocityX = bullet.getEntityVelocityX();
			double VelocityY = bullet.getEntityVelocityY();

			// The bullet will collide with an horizontal boundary.
			if (collideHorizontalBoundary(bullet, collisionArray))
				bullet.setEntityVelocity(VelocityX, -VelocityY);

			// The bullet will collide with an vertical boundary.
			else
				bullet.setEntityVelocity(-VelocityX, VelocityY);
		}
	}

	/**
	 * Checks if a collision with a boundary is with a horizontal boundary.
	 * 
	 * @param 	entity
	 * 			The entity that collides.
	 * @param 	collisionArray
	 * 			The collision position.
	 * 
	 * @return 	True if the collision position is approximately equal to 0 (the lower-boundary) or equal to the
	 * 			height of the world (the upper-boundary).
	 * 			@see implementation
	 */
	private boolean collideHorizontalBoundary(Entity entity, double[] collisionArray) {
		boolean crossesLowerBoundary = (collisionArray[1] < GAMMA * entity.getEntityRadius());
		boolean crossesUpperBoundary = (collisionArray[1] > (entity.getEntityWorld().getWorldHeight() - GAMMA * entity.getEntityRadius()));
		
		return (crossesLowerBoundary || crossesUpperBoundary);
	}
	
	/**
	 * Let a ship collide with the boundaries of the world.
	 * 
	 * @param 	ship
	 * 			The ship that collides.
	 * @param 	collisionArray
	 * 			The position where the collision will take place.
	 * 
	 * @effect	If the bullet collides with a horizontal boundary, the y-component of the velocity will change sign. 
	 * 			If the collision happens with a vertical boundary, the x-component changes sign
	 * 			@see implementation
	 */
	private void ShipAndWorldCollide(Ship ship, double[] collisionArray) {
		double VelocityX = ship.getEntityVelocityX();
		double VelocityY = ship.getEntityVelocityY();

		if (collideHorizontalBoundary(ship, collisionArray))
			ship.setEntityVelocity(VelocityX, -VelocityY);

		else
			ship.setEntityVelocity(-VelocityX, VelocityY);
	}

	/**
	 * Let two ships collide with each other.
	 * 
	 * @param 	entity1
	 * 			the first ship.
	 * @param 	entity2
	 * 			The second ship.
	 * 
	 * @effect 	The velocities will be changed (this will be calculated by the provide formula).	
	 * 			@see implementation
	 */
	private void ShipsCollide(Entity entity1, Entity entity2) {
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
		final double total_radius = (radius_1 + radius_2);
		
		final double mass_1 = entity1.getEntityMass();
		final double mass_2 = entity2.getEntityMass();

		final double delta_x = position_2X - position_1X;
		final double delta_y = position_2Y - position_1Y;
		
		final double delta_rX = position_2X - position_1X;
		final double delta_rY = position_2Y - position_1Y;
		
		final double delta_vX = velocity_2X - velocity_1X;
		final double delta_vY = velocity_2Y - velocity_1Y;
		
		final double delta_v_r = (delta_rX * delta_vX + delta_rY * delta_vY);

		double BigJ = (2 * mass_1 * mass_2 * delta_v_r) / (total_radius * (mass_1 + mass_2));
		double Jx = (BigJ * delta_x) / total_radius;
		double Jy = (BigJ * delta_y) / total_radius;

		entity1.setEntityVelocity(velocity_1X + Jx / mass_1, velocity_1Y + Jy / mass_1);
		entity2.setEntityVelocity(velocity_2X - Jx / mass_2, velocity_2Y - Jy / mass_2);
	}
	
	/**
	 * Execute certain algorithms based on the type of collision.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 * 
	 * @param 	collisionArray
	 * 			The position of collision between an entity and the world, or two entities.
	 * @param 	defaultEvolvingTime
	 * 			The amount of time the world has to evolve.
	 * @param 	collisionListener
	 * 			An object of the class CollisionListener that is used to draw explosions.
	 * 
	 * @effect 	Resolving the collision of the entities.
	 * 			@see implementation
	 */
	private void letCollisionHappen(double[] collisionArray, double defaultEvolvingTime, CollisionListener collisionListener) {
		double collisionPositionX = collisionArray[0];
		double collisionPositionY = collisionArray[1];
		
		// COLLISION BETWEEN TWO SHIPS:
		if (collision_entity_1 instanceof Ship && collision_entity_2 instanceof Ship) {
			if (collisionListener != null)
				collisionListener.objectCollision(collision_entity_1, collision_entity_2, collisionPositionX, collisionPositionY);

			// Let the collision happen.
			ShipsCollide(collision_entity_1, collision_entity_2);

			// Remove the out-dated positions of the entities out of the map entity_positions.
			entity_positions.remove(arrayToString(collision_entity_1.getEntityPosition()));
			entity_positions.remove(arrayToString(collision_entity_2.getEntityPosition()));
			
			// Let the ships who will collide evolve a little more after the collision. Otherwise, 
			// the two ships would keep touching which would invoke the same collision again the next 
			// time the method evolve() will be invoked.
			collision_entity_1.move(GAMMA * defaultEvolvingTime);
			collision_entity_2.move(GAMMA * defaultEvolvingTime);
			
			// Add the new position with the related entities to the map entity_positions.
			entity_positions.put(arrayToString(collision_entity_1.getEntityPosition()), collision_entity_1);
			entity_positions.put(arrayToString(collision_entity_2.getEntityPosition()), collision_entity_2);
		}

		// COLLISION BETWEEN A SHIP AND THE BOUNDARY OF THE WORLD:
		else if (collision_entity_1 instanceof Ship && collision_entity_2 == null) {
			if (collisionListener != null)
				collisionListener.boundaryCollision(collision_entity_1, collisionPositionX, collisionPositionY);

			// Let the collision happen.
			ShipAndWorldCollide((Ship)collision_entity_1, collisionArray);

			// Remove the out-dated positions of the entities out of the map entity_positions.
			entity_positions.remove(arrayToString(collision_entity_1.getEntityPosition()));
			
			// Let the ship who will collide evolve a little more after the collision. Otherwise, 
			// the ship would keep touching with the boundary which would invoke the same collision 
			// again the next time the method evolve() will be invoked.
			collision_entity_1.move(GAMMA * defaultEvolvingTime);
			
			// Add the new position with the related entities to the map entity_positions.
			entity_positions.put(arrayToString(collision_entity_1.getEntityPosition()), collision_entity_1);
		}

		// COLLISION BETWEEN A BULLET AND THE BOUNDARY OF THE WORLD:
		else if (collision_entity_1 instanceof Bullet && collision_entity_2 == null) {
			if (collisionListener != null)
				collisionListener.boundaryCollision(collision_entity_1, collisionPositionX, collisionPositionY);

			// Let the collision happen.
			BulletAndWorldCollide((Bullet)collision_entity_1, collisionArray);

			if (!collision_entity_1.isEntityTerminated()) {

				// Remove the out-dated positions of the entities out of the map entity_positions.
				entity_positions.remove(arrayToString(collision_entity_1.getEntityPosition()));
			
				// Let the bullet who will collide evolve a little more after the collision. Otherwise, 
				// the bullet would keep touching the boundary, which would invoke the same collision
				// again the next time 'evolve()' will be invoked.
				collision_entity_1.move(GAMMA * defaultEvolvingTime);
				
				// Add the new position with the related entities to the map entity_positions.
				entity_positions.put(arrayToString(collision_entity_1.getEntityPosition()), collision_entity_1);
			}
		}

		// COLLISION BETWEEN A SHIP AND A BULLET OR BETWEEN TWO BULLETS:
		else {
			// In this case, at least one of the two entities will be removed from the world. In the case if one of the entities
			// is a bullet which collides with its parent-ship, it will be reloaded back onto this ship. In all the other cases, 
			// both entities will be terminated. This means it will not be necessary to evolve a little further to make sure the
			// entities aren't colliding any more, because this will never be the case.
			BulletAndEntityCollide(collision_entity_1, collision_entity_2, collisionListener, collisionArray);
		}
	}

	
	/// TERMINATION AND STATES ///
	
	/**
	 * Terminate this world.
	 * 
	 * @effect  The world's state is set to Terminated and all the entities it contains are set to the state NO_WORLD.
	 * 			@see implementation
	 */
	public void Terminate() {
		if (!isWorldTerminated()) {
			setWorldState(State.TERMINATED);
			
			for (Bullet bullet : this.getWorldBullets())
				bullet.setEntityFree();
			
			for (Ship ship : this.getWorldShips())
				ship.setEntityFree();
		}
	}

	/**
	 * The state of the world is initiated as NOT_TERMINATED.
	 */
	private State state = State.NOT_TERMINATED;

	/**
	 * The two states of a world
	 * NOT_TERMINATED: the world is not terminated.
	 * TERMINATED: the world doesn't exist anymore, so it's terminated.
	 */
	private static enum State {
		NOT_TERMINATED, TERMINATED;
	}
	

	/// RELATIONS WITH OTHER CLASSES ///
	
	/**
	 * The map ships is a map with as key the hash-code representing the ship, and as value the ship itself. 
	 * It contains all the ships that belong to the world.
	 */
	private final Map<Integer, Ship> ships = new HashMap<Integer, Ship>();
	
	/**
	 * The map bullets is a map with as key the hash-code representing the bullet, and as value the bullet itself.
	 * It contains all the bullets that belong to the world.
	 */
	private final Map<Integer, Bullet> bullets = new HashMap<Integer, Bullet>();
	
	/** 
	 * The map entity_positions is a map with as key the string "x,y" representing the position of the entity and as 
	 * value the entity itself.
	 */
	private final Map<String, Entity> entity_positions = new HashMap<String, Entity>();
}

