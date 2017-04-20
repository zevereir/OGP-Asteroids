package asteroids.model;



public abstract class MinorPlanet extends Entity{

	protected MinorPlanet(double positionX, double positionY, double velocityX, double velocityY, double radius,
			double orientation, double mass, double maxVelocity, double density) {
		super(positionX, positionY, velocityX, velocityY, radius, orientation, mass, maxVelocity, density);
		
	}
	///DEFAULTS///
	protected final static double LOWER_MINOR_PLANET_RADIUS = 5;
	
	protected static double getDefaultMinorPlanetRadius(){
		return 5;
	}
	
	@Override
	public double getEntityMass() {
		return (4.0 / 3.0) * Math.PI * Math.pow(getEntityRadius(), 3) * getEntityDensity();
	}

	

	
	
	
	@Override
	protected void setEntityMass(double mass) {
		this.mass = mass;
		
	}	
	

	@Override
	protected boolean isValidMass(double mass) {
		return ((mass != Double.NaN) && (mass == MassFormula(getEntityRadius(),getEntityDensity())));
	}

	@Override
	protected boolean isValidRadius(double radius) {
		return (radius >= LOWER_MINOR_PLANET_RADIUS);
		}


	
	///COLLISION///
	protected void entityAndBoundaryCollide(double[] collisionPosition, double defaultEvolvingTime) {
		double VelocityX = this.getEntityVelocityX();
		double VelocityY = this.getEntityVelocityY();

		if (collideHorizontalBoundary(this, collisionPosition))
			this.setEntityVelocity(VelocityX, -VelocityY);

		else
			this.setEntityVelocity(-VelocityX, VelocityY);
	}

}
