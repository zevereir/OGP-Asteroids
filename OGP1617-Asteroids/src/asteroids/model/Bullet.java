package asteroids.model;

/**
 * a class that describes a bullet that flies away in the blue blue sky.
 * 
 * @version 28th of march
 * @authors Sieben Bocklandt and Ruben Broekx
 */
public class Bullet extends Entity {
	
	///CONSTRUCTOR///
	public Bullet(double x, double y , double xVelocity, double yVelocity, double radius,double orientation
			,double mass,double maxVelocity,double density) {
		
		super(x,y,xVelocity,yVelocity,radius,orientation,mass,maxVelocity,density);
	}
	
	public Bullet(double x, double y , double xVelocity, double yVelocity, double radius){
		this(x,y,xVelocity,yVelocity,radius,Entity.getDefaultOrientation(),getDefaultBulletMass(),
				Entity.getDefaultMaxVelocity(),getDefaultBulletDensity());
	}
	
	public Bullet(){
		this(getDefaultPosition()[0],getDefaultPosition()[1],getDefaultVelocity()[0],getDefaultVelocity()[1],getDefaultRadius());
	}

	
	///DEFAULTS///
	public static double getDefaultRadius(){
		return 1;
	}
	
	public static double getDefaultBulletMass(){
		return (4.0/3.0) * Math.PI * Math.pow(getDefaultRadius(),3) * getDefaultBulletDensity();
	}
	
	private final static double LOWER_BULLET_RADIUS = 1;
	
	static double getDefaultBulletDensity(){ 
		return 7.8E12;
	}
	
	public double bulletMass() {
		return (4.0/3.0)*Math.PI * Math.pow(this.getEntityRadius(),3) * this.getEntityDensity();		
	}
	
	///GETTERS///
	public int getAmountOfBounces(){
		return this.amountOfBounces;
	}
	
	public Ship getBulletShip(){
		return this.ship;
	}
	
	public Ship getBulletSource(){
		return this.source_ship;
		
	}
	
	public double getEntityMass() {
		return this.mass;	
	}
	
	///CHECKERS///
	//---> GOED NAKIJKEN <----//
	public boolean canHaveAsShip(Ship ship){
		return (ship.canHaveAsBullet(this));
	}
	
	// --> Lower_... zal altijd groter zijn dan 0
	//  --> R(bullet) < R(ship) ?
	public boolean isValidRadius(double radius) {		
		return (radius >= LOWER_BULLET_RADIUS);
	}
	
	// The mass density rho of each bullet is the same, namely 7.8·1012kg/km^3.
	public boolean isValidDensity(double density) {
		return (density == getDefaultBulletDensity());
	}
	
	public boolean isValidMass(double mass) {
		return ((mass != Double.NaN) && (mass == bulletMass()));
	}
	
	///SETTERS///
	public void setBulletShip(Ship ship){
		this.ship = ship;
	}
	
	public void setBulletSourceShip(Ship ship){
		this.source_ship = ship;
	}
	
	public void setAmountOfBounces(int amount){
		this.amountOfBounces = amount;
	}
	
	public void setEntityDensity(double density){
		this.density = getDefaultBulletDensity();
		
	}
	
	// Mass of bullet will always be calculated with the same formula, found in the method bulletMass()
	public void setEntityMass(double mass) {
		if (isValidMass(mass))
			this.mass = mass;
		else
			this.mass = bulletMass();	
	}
	
	///MOVE///
	public void move(double dt,Entity entity1, Entity entity2){
		if (dt < 0) {
			System.out.println("Error in model.bullet at move(dt, entity1, entity2), dt < 0");
			throw new IllegalArgumentException();
		}

		double vel_x = this.getEntityVelocityX();
		double vel_y = this.getEntityVelocityY();

		final double positionX = this.getEntityPositionX()+ vel_x * dt;
		final double positionY = this.getEntityPositionY()+ vel_y * dt;
		
		if (this == entity1){
			this.setPositionWhenColliding(positionX, positionY);
		} else if (this == entity2) {	
			this.setPositionWhenColliding(positionX, positionY);
		} else {
			this.setEntityPosition(positionX, positionY);
		}
	}
	
	
	///TERMINATION AND STATES///
	private BulletState state = BulletState.NOTLOADED;

	private static enum BulletState {
		LOADED, NOTLOADED;	
	}

	public BulletState getBulletLoadedState(){
		return this.state;
	}
	public boolean isBulletLoaded(){
		return (this.getBulletLoadedState() == BulletState.LOADED);
	}

	public boolean hasBulletProperState(){
		return isBulletLoaded() ^(!isBulletLoaded());
	}

	public void setBulletLoadedState(BulletState state) {
		if (state == null){
			System.out.println("Error in model.bullet at setBulletLoadedState, state == null");
			throw new IllegalStateException();}
		else
			this.state = state;
	}

	public void setBulletLoaded(Ship ship) {
		assert (!this.isEntityTerminated() && !ship.isEntityTerminated());
		this.setBulletLoadedState(BulletState.LOADED);
		this.setEntityFree();
		this.setBulletShip(ship);
		this.setBulletSourceShip(null);
	}

	public void setBulletNotLoaded(Ship ship) {
		assert (!this.isEntityTerminated());
		this.setBulletLoadedState(BulletState.NOTLOADED);
		this.setBulletShip(null);
		this.setBulletSourceShip(ship);
	}
	
	public void Terminate() {
		if (this.isEntityFree()){
			setEntityState(State.TERMINATED);}
		else if (this.isEntityInWorld()){
			this.getEntityWorld().removeEntityFromWorld(this);
			setEntityState(State.TERMINATED);}
		}
	

	///CONNECTIONS WITH OTHER CLASSES///
	private  Ship ship = null;	
	private  Ship source_ship = null;
	
	
	/// COUNTER ///
	private int amountOfBounces = 0;
}

