package asteroids.program;

import java.util.List;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

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

	
	protected void evaluate(){
		this.evaluate();
	}
	
	protected Object evaluateWithReturn(){
		return this.evaluateWithReturn();
	}
	
}
