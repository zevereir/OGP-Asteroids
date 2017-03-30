package asteroids.model;
import be.kuleuven.cs.som.annotate.*;

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
				Entity.getDefaultMaxVelocity(),Entity.getDefaultBulletDensity());
	}
	
	public Bullet(){
		this(getDefaultPosition()[0],getDefaultPosition()[1],getDefaultVelocity()[0],getDefaultVelocity()[1],getDefaultRadius());
	}

	
	///DEFAULTS///
	public static double getDefaultRadius(){
		return 2;
	}
	
	public static double getDefaultBulletMass(){
		return 4/3*Math.PI*Math.pow(getDefaultRadius(),3)*getDefaultBulletDensity();
	}
	
	private final static double LOWER_BULLET_RADIUS = 1;
	
	public double getDefaultDensity(){ 
		return 7.8E12;
	}
	
	public double bulletMass() {
		return (4.0/3.0)*Math.PI * Math.pow(this.getEntityRadius(),3)*this.getEntityDensity();		
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
	
	public boolean isValidRadius(double radius) {
		if ((radius < 0) || (radius < LOWER_BULLET_RADIUS) )
			return false;
		else
			return true;		
	}
	
	public boolean isValidDensity(double density) {
		if (( density < getDefaultBulletDensity())||(density < 0) )
			return false;
		else		
		return true;
	}
	
	public boolean isValidMass(double mass) {
		if ((mass == Double.NaN) || (mass != bulletMass()))
			return false;		
		return true;
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
	
	public void setEntityMass(double mass) {
		if (isValidMass(mass))
			this.mass = mass;
		else
				this.mass = bulletMass();
		
	}
	
	///TERMINATION AND STATES///
	
	private BulletState state = BulletState.NOTLOADED;

	private static enum BulletState {
		LOADED,NOTLOADED;	
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
		if (state == null)
			throw new IllegalStateException();
		else
			this.state = state;
	}

	public void setBulletLoaded(Ship ship) {
		assert (!this.isEntityTerminated() && !ship.isEntityTerminated());
		this.setBulletLoadedState(BulletState.LOADED);
		this.setBulletShip(ship);
	}

	public void setBulletNotLoaded() {
		assert (!this.isEntityTerminated());
		this.setBulletLoadedState(BulletState.NOTLOADED);
		this.setBulletShip(null);
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

