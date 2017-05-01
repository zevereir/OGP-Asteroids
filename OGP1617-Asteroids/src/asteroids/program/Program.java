package asteroids.program;

import java.util.List;

import asteroids.model.Ship;



public class Program {
	
	/// CONSTRUCTOR ///
	
	protected Program(List<MyFunction> functions, MyStatement main) {
		
	}
	
	public List<Object> execute( double dt) {
		return null;
	}
	
	/// GETTERS ///
	public Ship getProgramShip(){
		return this.ship;
	}
	
	/// SETTERS ///
	public void setProgramShip(Ship ship){
		this.ship = ship;
	}
	
	/// CONNECTIONS WITH OTHER CLASSES ///
	private Ship ship = null;
	
}
