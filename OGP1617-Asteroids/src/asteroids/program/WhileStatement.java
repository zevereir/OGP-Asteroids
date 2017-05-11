package asteroids.program;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

class WhileStatement extends MyStatement {
	
	/// CONSTRUCTOR ///

	public WhileStatement(MyExpression condition, MyStatement body) {
		setCondition(condition);
		setBody(body);
	}
	
	
	/// BASIC PROPERTIES ///

	private MyExpression condition;
	private MyStatement body;
	private boolean broken = false;
	
	
	/// SETTERS ///

	public void setBody(MyStatement body) {
		this.body = body;
	}

	public void setBrokenTrue() {
		this.broken = true;
	}

	public void setCondition(MyExpression condition) {
		this.condition = condition;
	}
	
	
	/// CHECKERS ///

	protected boolean isBroken() {
		return this.broken;
	}
	
	
	/// EVALUATION ///

	@Override
	protected void evaluate(Program program, List<MyExpression> actualArgs) {
		setStatementProgram(program);
		
		if (canHaveAsCondition(condition, actualArgs, null)) {
			while ((boolean) condition.getExpressionResult(program, actualArgs, null) && !isBroken()) {
				try {
					body.evaluate(program, actualArgs);
				} catch (IllegalAccessError error) {
					setBrokenTrue();
				}
			}
		} 
		else
			throw new IllegalArgumentException();
	}

	protected void evaluateWhileInFunction(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setStatementProgram(program);
		
		if (canHaveAsCondition(condition, actualArgs, function)) {
			while ((boolean) condition.getExpressionResult(program, actualArgs, function) && !isBroken()) {
				if (body instanceof AssignmentStatement)
					((AssignmentStatement) body).assignLocalVariable(program, actualArgs, function);
				else {
					try {
						body.evaluateInFunction(program, actualArgs, function);
					} catch (IllegalAccessError error) {
						setBrokenTrue();
					}
				}
			}
		}
		else
			throw new IllegalArgumentException();
	}

	@Override
	protected void skipEvaluationUntilLocation(Program program, List<MyExpression> actualArgs, SourceLocation location) {
		while ((boolean) condition.getExpressionResult(program, actualArgs, null) && !isBroken()) {
			try {
				body.skipEvaluationUntilLocation(program, actualArgs, location);
			} catch (IllegalAccessError error) {
				setBrokenTrue();
			}
		}
	}

}
