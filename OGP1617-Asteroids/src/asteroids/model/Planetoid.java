package asteroids.model;

import asteroids.part2.CollisionListener;

public class Planetoid extends MinorPlanet {

	public Planetoid(double positionX, double positionY, double velocityX, double velocityY, double radius,
			double orientation, double mass, double maxVelocity, double density,double totalTraveledDistance) {
		super(positionX, positionY, velocityX, velocityY, radius, orientation, mass, maxVelocity, density);
		setPlanetoidInitialRadius(radius);
		setPlanetoidTotalTraveledDistance(totalTraveledDistance);

	}
	
	public Planetoid(double positionX, double positionY, double velocityX, double velocityY, double radius,double totalTraveledDistance){
		this(positionX, positionY, velocityX, velocityY, radius, getDefaultOrientation(), getDefaultPlanetoidMass(),
				getDefaultMaxVelocity(), getDefaultPlanetoidDensity(),totalTraveledDistance);
	}
	
	public Planetoid(){
		this(getDefaultPositionX(),getDefaultPositionY(),getDefaultVelocityX(),getDefaultVelocityY(),getDefaultMinorPlanetRadius(),getDefaultPlanetoidTraveledDistance());
	}
	
	
	///PROPERTIES///
	protected double totalTraveledDistance;
	protected double initial_radius;
	
	
	///DEFAULTS///
	private static double getDefaultPlanetoidDensity(){
		return 0.917E12;
	}
	
	
	/**
	 * Return the default Planetoid mass.
	 * 
	 * @return 	The default mass.
	 * 			@see implementation
	 */
	private static double getDefaultPlanetoidMass() {
		return (4.0 / 3.0) * Math.PI * Math.pow(getDefaultMinorPlanetRadius(), 3) * getDefaultPlanetoidDensity();
	}
	
	private static double getDefaultPlanetoidTraveledDistance(){
		return 0;
	}
	///GETTERS///
	public double getPlanetoidTotalTraveledDistance(){
		return totalTraveledDistance;
	}
	protected double getPlanetoidInitialRadius(){
		return this.initial_radius;
	}
	///SETTERS///
	protected void setEntityDensity(double density){
		this.density = getDefaultPlanetoidDensity();
	}
	protected void setPlanetoidTotalTraveledDistance(double totalTraveledDistance){
		if (isValidTraveledDistance(totalTraveledDistance))
			this.totalTraveledDistance = totalTraveledDistance;
		else
			throw new IllegalArgumentException();
	}
	
	protected void setPlanetoidInitialRadius(double radius){
		initial_radius = radius;
	}
	
	///CHECKERS///
	protected boolean isValidDensity(double density){
		return (density == getDefaultPlanetoidDensity());
	}

	protected boolean isValidTraveledDistance(double totalTraveledDistance){
		return (totalTraveledDistance>=0);
	}

	/// MOVE ///
	
	/**
	 * Move the bullet for the given time moveTime.
	 * 
	 * @param 	moveTime
	 * 			The time the bullet has to move.
	 * 
	 * @post 	After moveTime, the bullet's position will be set on moveTime times its velocity.
	 * 			@see implementation
	 * 
	 * @throws 	IllegalArgumentException
	 * 			If the given time is negative.
	 * 		  | moveTime < 0
	 */
	protected void move(double moveTime) {
		if (moveTime < 0)
			throw new IllegalArgumentException();
		shrink(moveTime);
		double velocityX = this.getEntityVelocityX();
		double velocityY = this.getEntityVelocityY();

		final double collidingPositionX = this.getEntityPositionX() + velocityX * moveTime;
		final double collidingPositionY = this.getEntityPositionY() + velocityY * moveTime;
		
		this.setPositionWithoutChecking(collidingPositionX, collidingPositionY);
	}
	
	private void shrink(double time){
		double x_distance = getEntityVelocityX()*time;
		double y_distance = getEntityVelocityY()*time;
		double total_traveled_distance = getPlanetoidTotalTraveledDistance()+getEuclidianDistance(x_distance,y_distance);
		this.setPlanetoidTotalTraveledDistance(total_traveled_distance);
		double new_radius = getPlanetoidInitialRadius() - (10E-6)*getPlanetoidTotalTraveledDistance();
		if (isValidRadius(new_radius))
			setEntityRadius(new_radius);
		else
			Terminate();
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
				if (getEntityRadius() >=30){
					planetoidDivision();}
				else
					this.getEntityWorld().removeEntityFromWorld(this);
				setEntityState(State.TERMINATED);
			}
		}
		
		public void planetoidDivision(){
			//Calculating the child properties
			double total_child_velocity = 1.5*getEuclidianDistance(getEntityVelocityX(),getDefaultVelocityY());
			double child_radius = getEntityRadius()/2;
			double child1_orientation = Math.random()*Math.PI;
			double child2_orientation = (child1_orientation+Math.PI);
			
			double child1_velocityX = total_child_velocity*Math.cos(child1_orientation);
			double child1_velocityY = total_child_velocity*Math.sin(child1_orientation); 
			double child2_velocityX = total_child_velocity*Math.cos(child2_orientation); 
			double child2_velocityY = total_child_velocity*Math.sin(child2_orientation); 
			
			double child1_positionX = getEntityPositionX()+ child_radius*Math.cos(child1_orientation);
			double child1_positionY = getEntityPositionY()+ child_radius*Math.sin(child1_orientation); 
			double child2_positionX = getEntityPositionX()+ child_radius*Math.cos(child2_orientation); 
			double child2_positionY = getEntityPositionY()+ child_radius*Math.sin(child2_orientation); 
			
			//Making the children
			Asteroid child1 = new Asteroid(child1_positionX,child1_positionY,child1_velocityX,child1_velocityY,child_radius);
			Asteroid child2 = new Asteroid(child2_positionX,child2_positionY,child2_velocityX,child2_velocityY,child_radius);
			child1.setEntityOrientation(child1_orientation);
			child2.setEntityOrientation(child2_orientation);
			
			
			//Adding them to the world
			World world = getEntityWorld();
			this.getEntityWorld().removeEntityFromWorld(this);
			try {
				world.addEntityToWorld(child1);
				child1.move(0.00001);
				
				
			} catch (IllegalArgumentException illegalArgumentException) {
			child1.Terminate();
			}
			try {
				world.addEntityToWorld(child2);
			} catch (IllegalArgumentException illegalArgumentException) {
				
			child2.Terminate();
			}
				
		}
		
		///COLLISIONS///
		protected void entityAndShipCollide(Ship ship,double[] collisionPosition,double defaultEvolvingTime,CollisionListener collisionListener){
			World world = ship.getEntityWorld();
			double radius = ship.getEntityRadius();
			double random_x = radius+Math.random()*(world.getWorldWidth()-radius);
			double random_y = radius+Math.random()*(world.getWorldHeight()-radius);
			try {
				ship.setEntityPosition(random_x, random_y);
			} catch (IllegalArgumentException illegalArgumentException) {
				double collisionPositionX = collisionPosition[0];
				double collisionPositionY = collisionPosition[1];
				if (collisionListener != null)
					collisionListener.objectCollision(this, ship,collisionPositionX,collisionPositionY);
				ship.Terminate();
			}
		}
}

