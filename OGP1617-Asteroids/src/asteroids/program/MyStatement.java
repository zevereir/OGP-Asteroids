package asteroids.program;

import asteroids.model.Ship;


public abstract class MyStatement {
	
	/// GETTERS ///
	protected Program getStatementProgram(){
		return this.program;
	}
	
	protected Ship getStatementShip(){
		return this.getStatementProgram().getProgramShip();
	}
	/// SETTERS ///
	protected void setStatementProgram(Program program){
		this.program = program;
	}
	
	
	/// CONNECTIONS WITH OTHER CLASSES ///
	private Program program = null;

	protected abstract void evaluate();	
	
}
