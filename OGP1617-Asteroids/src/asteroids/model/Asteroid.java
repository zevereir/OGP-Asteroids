package asteroids.model;

import asteroids.part2.CollisionListener;

public class Asteroid extends MinorPlanet {

	
	public Asteroid(double positionX, double positionY, double velocityX, double velocityY, double radius,
			double orientation, double mass, double maxVelocity, double density) {
		super(positionX, positionY, velocityX, velocityY, radius, orientation, mass, maxVelocity, density);

	}
	
	public Asteroid(double positionX, double positionY, double velocityX, double velocityY, double radius){
		this(positionX, positionY, velocityX, velocityY, radius, getDefaultOrientation(), getDefaultAsteroidMass(),
				getDefaultMaxVelocity(), getDefaultAsteroidDensity());
	}
	
	public Asteroid(){
		this(getDefaultPositionX(),getDefaultPositionY(),getDefaultVelocityX(),getDefaultVelocityY(),getDefaultMinorPlanetRadius());
	}
	
	
	
	///DEFAULTS///
	private static double getDefaultAsteroidDensity(){
		return 2.65E12;
	}
	
	
	/**
	 * Return the default Asteroid mass.
	 * 
	 * @return 	The default mass.
	 * 			@see implementation
	 */
	private static double getDefaultAsteroidMass() {
		return (4.0 / 3.0) * Math.PI * Math.pow(getDefaultMinorPlanetRadius(), 3) * getDefaultAsteroidDensity();
	}
	
	///SETTERS///
		protected void setEntityDensity(double density){
			this.density = getDefaultAsteroidDensity();
		}
		
	///CHECKERS///
	protected boolean isValidDensity(double density){
		return (density == getDefaultAsteroidDensity());
	}
	
	

	/// MOVE ///
	
	/**
	 * Move the asteroid for the given time moveTime.
	 * 
	 * @param 	moveTime
	 * 			The time the asteroid has to move.
	 * 
	 * @post 	After moveTime, the asteroids position will be set on moveTime times its velocity.
	 * 			@see implementation
	 * 
	 * @throws 	IllegalArgumentException
	 * 			If the given time is negative.
	 * 		  | moveTime < 0
	 */
	protected void move(double moveTime) {
		if (moveTime < 0)
			throw new IllegalArgumentException();
		
		double velocityX = this.getEntityVelocityX();
		double velocityY = this.getEntityVelocityY();

		final double collidingPositionX = this.getEntityPositionX() + velocityX * moveTime;
		final double collidingPositionY = this.getEntityPositionY() + velocityY * moveTime;
		
		this.setPositionWithoutChecking(collidingPositionX, collidingPositionY);
	}
	
	///TERMINATE///
	/**
	 * Terminate the asteroid.
	 * 
	 * @effect 	The asteroids state will be set on terminated.
	 * 			@see implementation
	 */
	public void Terminate() {
		if (this.isEntityFree())
			setEntityState(State.TERMINATED);
		
		else if (this.isEntityInWorld()) {
			this.getEntityWorld().removeEntityFromWorld(this);
			setEntityState(State.TERMINATED);
		}
	}
	
	///COLLISIONS///
	protected void entityAndShipCollide(Ship ship,double[] collisionPosition,double defaultEvolvingTime,CollisionListener collisionListener){
		double collisionPositionX = collisionPosition[0];
		double collisionPositionY = collisionPosition[1];
		if (collisionListener != null)
			collisionListener.objectCollision(this, ship,collisionPositionX,collisionPositionY);
		ship.Terminate();
	}
}
