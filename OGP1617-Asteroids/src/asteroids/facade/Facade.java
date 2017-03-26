package asteroids.facade;

import java.util.Collection;
import java.util.Set;

import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part2.CollisionListener;
import asteroids.util.ModelException;


/**
 * A class that can be used to connect the IFacade class with the class Ship.
 * 
 * @version 21_Mar_21u
 * @authors Sieben Bocklandt and Ruben Broekx
 */
public class Facade implements asteroids.part2.facade.IFacade  {
		
	/// METHODS /// 
	
	/**
	 * Returns a Ship with default values.
	 * 
	 * @post 	The ship has a circular shape with radius 1. 
	 * 			| ship.getShipRadius() == 1;
	 * @post 	The position of the ship will be {0,0}
	 * 			| ship.getShipPosition() = {0,0};
	 * @post 	The ship's orientation will be equal to 0, which means it's facing right.
	 *  		| ship.getShipOrientation() = 0;
	 * @post 	The ship's velocity will be {0,0}.
	 * 			| ship.getShipVelocity() = {0,0};
	 * @post    The ship's maximum velocity will be equal to the speed of light.
	 * 			|ship.getShipMaximumVelocity == SPEED_OF_LIGHT
	 * 
	 * @return  A ship with default values will be returned.
	 * 			|result = Ship()
	 * 
	 * @throws  Modelexception
	 * 			If the default radius or the default position is not correct.
	 * 			|(getDefaultRadius <LOWER_RADIUS || !isValidArray(getDefaultPosition))
	 */
	@Override
	public Ship createShip() throws ModelException {
		Ship ship = new Ship();
		return ship;
	}
	
	/**
	 * Returns a ship with given parameters. 
	
	 * @return A new ship with given properties and a default maximum velocity will be returned.
	 * 		   |result = Ship(x, y, xVelocity, yVelocity, radius, orientation)
	 */
	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass)
			throws ModelException {
		Ship ship = new Ship(x, y, xVelocity, yVelocity, radius, orientation, mass);
		return ship;
	}

	/**
	 * 	Returns the position of the ship as an array of length 2, with the x-coordinate at index 0 and the
	 *  y-coordinate at index 1.
	 *  
	 *  @param 	ship
	 *  		The ship whose location is asked .
	 *  
	 *  @return The ship's position.
	 *  		|result =ship.getShipPosition()
	 */
	@Override
	public double[] getShipPosition(Ship ship) {
		return ship.getEntityPosition();
	}
	
	/**
	 * Return the velocity of ship as an array of length 2, with the velocity along the X-axis at index 0 
	 *  and the velocity along the Y-axis at index 1.
	 * 
	 * @param 	ship
	 * 			The ship whose velocity is asked.
	 * 
	 * @return  The ship's velocity.
	 *  		|result =ship.getShipVelocity()
	 */
	@Override
	public double[] getShipVelocity(Ship ship) {

		return ship.getEntityVelocity();
	}
	
	/**
	 * Return the radius of the ship.
	 * 
	 * @param 	ship
	 * 			The ship whose radius is asked.
	 * 
	 * @return  The ship's radius.
	 *  		|result =ship.getShipRadius()
	 */
	@Override
	public double getShipRadius(Ship ship) {

		return ship.getEntityRadius();
	}
	
	/**
	 * Return the orientation of the ship in radians.
	 * 
	 * @param 	ship
	 * 			The ship whose orientation is asked.
	 * 
	 * @return  The ship's orientation.
	 *  		|result =ship.getShipOrientation()
	 */
	@Override
	public double getShipOrientation(Ship ship) {
		return ship.getEntityOrientation();
	}

	/**
	 * Update the ship its velocity based on its current velocity, its direction, and the given amount.
	 * 
	 * @effect The ship will accalerate with amount.
	 * 		   |ship.thrust(amount)			
	 */
	@Override
	public void thrust(Ship ship, double amount) {
		ship.thrust(amount);
	}
	
	/**
	 * Update the ship it's position, assuming it moves in dt seconds at its current velocity.
	 * 
	 * @effect The ship will be moved for dt seconds at it's velocity.
	 * 		   |ship.move(dt)
	 * 
	 */
	@Override
	public void move(Ship ship, double dt) throws ModelException {
		ship.move(dt);
	}

	/**
	 * Update the direction of the ship by adding an angle in radians to its current direction.
	 * 
	 * @effect  The ship will turn with the given angle.
	 * 			|ship.turn(angle)
	 */
	@Override
	public void turn(Ship ship, double angle) {
		ship.turn(angle);
	}
	
	/**
	 * Calculate the distance between two ships.
	 * 
	 * @return The distance between these ships.
	 * 		   |result = ship1.getDistanceBetween(ship2)
	 */
	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2) {
		return ship1.getDistanceBetween(ship2);
	}	

	/**
	 * Returns a boolean saying if the two ships are overlapping.
	 * 
	 * @return	Return True if the two ships overlap. 
	 * 			|result = ship1.overlap(ship2)
	 */
	@Override
	public boolean overlap(Ship ship1, Ship ship2) {
		return ship1.overlap(ship2);
	}	

	/**
	 * Calculates the number of seconds until, if ever, the first collision between two ships will take place.
	 * 
	 * @return The seconds until collision or null if they never collide.
	 *		   |result= ship1.getTimeToCollision(ship2)
	 */
	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		return ship1.getTimeToCollision(ship2);
	}

	/**
	 * Calculates the position, if there is one, of the collision between two ships.
	 * 
	 * @return The position where the ships will collide. If they don't, positive infinity is returned.
	 * 		   |result = ship1.getCollisionPosition
	 */
	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		return ship1.getCollisionPosition(ship2);
	}

	@Override
	public void terminateShip(Ship ship) throws ModelException {
		ship.Terminate();
		
	}

	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		return ship.isEntityTerminated();
	}

	@Override
	public double getShipMass(Ship ship) throws ModelException {
		return ship.getEntityMass();
	}

	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		return ship.getEntityWorld();
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		return ship.isThrusterActive();
	}
	@Override
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		// TODO Auto-generated method stub
		ship.setThrusterActive(active);
	}

	@Override
	public double getShipAcceleration(Ship ship) throws ModelException {
		return ship.getShipAcceleration();
	}

	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		Bullet bullet = new Bullet(x, y, xVelocity, yVelocity, radius);
		return bullet;
	}

	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		bullet.Terminate();
		
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		return bullet.isEntityTerminated();
	}

	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		return bullet.getEntityPosition();
	}

	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		return bullet.getEntityVelocity();
	}

	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		return bullet.getEntityRadius();
	}

	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		return bullet.getEntityMass();
	}

	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		return bullet.getEntityWorld();
	}

	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		return bullet.getBulletShip();
	}

	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		return bullet.getBulletSource();
	}

	@Override
	public World createWorld(double width, double height) throws ModelException {
		World world = new World(width, height);
		return world;
	}

	@Override
	public void terminateWorld(World world) throws ModelException {
		world.Terminate();
		
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		return world.isWorldTerminated();
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		return world.getWorldSize();

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		return world.getWorldShips();
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		return world.getWorldBullets();
	}

	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		world.addEntityToWorld(ship);
	}

	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		world.removeEntityFromWorld(ship);
		
	}

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		world.addEntityToWorld(bullet);
		
	}

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		world.removeEntityFromWorld(bullet);
		
	}

	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		ship.getShipBullets();
	}

	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		return ship.getNbBulletsOnShip();
	}

	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		ship.addOneBulletToShip(bullet);
		
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		ship.addMultipleBulletsToShip(bullets);		
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		ship.removeBulletFromShip(bullet);
		
	}

	@Override
	public void fireBullet(Ship ship) throws ModelException {
		ship.fireBullet();
		
	}

	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		return 
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		return ((Entity)entity1).getTimeToCollision((Entity)entity2);
	}

	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		return ((Entity)entity1).getCollisionPosition(((Entity)entity2));
	}

	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		return world.getTimeNextCollision();
	}

	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		return world.getPositionNextCollision();
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		world.evolve(dt,collisionListener);
		
	}

	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
	
		return world.getEntityAt(x, y);
	}

	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		return world.getWorldEntities();
	}
}
