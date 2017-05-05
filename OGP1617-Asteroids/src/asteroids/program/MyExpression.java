package asteroids.program;

import java.util.List;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public abstract class MyExpression {
	
	protected abstract Object getExpressionResult();
	
	
	
	/// GETTERS ///
	protected Program getExpressionProgram(){
		return this.program;
	}
	
	protected Ship getExpressionShip(){
		return this.getExpressionProgram().getProgramShip();
	}
	/// SETTERS ///
	protected void setExpressionProgram(Program program){
		this.program = program;
	}
	/// CONNECTIONS WITH OTHER CLASSES ///
	private Program program = null;
	
	
	
	
	
	
	protected void assignExpressionToParameter(List<MyExpression> actualArgs) {
		//
	}

}
