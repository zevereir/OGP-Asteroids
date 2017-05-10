package asteroids.program;

import java.util.List;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public abstract class MyStatement {

	/// GETTERS ///

	protected Program getStatementProgram() {
		return this.program;
	}

	protected Ship getStatementShip() {
		return this.getStatementProgram().getProgramShip();
	}

	
	/// SETTERS ///

	protected void setStatementProgram(Program program) {
		this.program = program;
	}
	
	
	/// CHECKERS ///

	protected boolean canHaveAsCondition(MyExpression condition, List<MyExpression> actualArgs, MyFunction function) {
		return (condition.getExpressionResult(getStatementProgram(), actualArgs, function) instanceof Boolean);
	}

	
	/// EVALUATION ///

	protected abstract void evaluate(Program program, List<MyExpression> actualArgs);

	protected Object evaluateInFunction(Program program, List<MyExpression> actualArgs, MyFunction function) {
		return null;
	}

	public void skipEvaluationUntilLocation(Program program, List<MyExpression> actualArgs, SourceLocation location) {
		// Nothing
	}

	
	/// RELATIONS WITH OTHER CLASSES ///
	
	private Program program = null;
	
}
