package asteroids.program;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

@SuppressWarnings("hiding")
class WhileStatement<BooleanExpression> extends MyStatement {
	
	/// CONSTRUCTOR ///

	public WhileStatement(BooleanExpression condition, MyStatement body) {
		setCondition(condition);
		setBody(body);
	}
	
	
	/// BASIC PROPERTIES ///

	private BooleanExpression condition;
	private MyStatement body;
	private boolean broken = false;
	
	
	/// SETTERS ///

	public void setBody(MyStatement body) {
		this.body = body;
	}

	public void setBrokenTrue() {
		broken = true;
	}

	public void setCondition(BooleanExpression condition) {
		this.condition = condition;
	}
	
	
	/// CHECKERS ///

	protected boolean isNotBroken() {
		return !broken;
	}
	
	
	/// EVALUATION ///

	@Override
	protected void evaluate(Program program, List<MyExpression> actualArgs) {
		setStatementProgram(program);
		
		
		while ((boolean) ((MyExpression) condition).getExpressionResult(program, actualArgs) && isNotBroken()) {
			try {
				body.evaluate(program, actualArgs);
			} catch (IllegalAccessError error) {
				setBrokenTrue();
			}
		}
		
	}

	protected void evaluateWhileInFunction(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setStatementProgram(program);


		while ((boolean) ((MyExpression) condition).getExpressionResult(program, actualArgs, function) && isNotBroken()) {
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

	@Override
	protected void skipEvaluationUntilLocation(Program program, List<MyExpression> actualArgs, SourceLocation location) {
		while ((boolean) ((MyExpression) condition).getExpressionResult(program, actualArgs) && isNotBroken()) {
			try {
				body.skipEvaluationUntilLocation(program, actualArgs, location);
			} catch (IllegalAccessError error) {
				setBrokenTrue();
			}
		}
	}

}
