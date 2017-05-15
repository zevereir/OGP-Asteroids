package asteroids.program;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

class IfElseStatement<BooleanExpression> extends MyStatement{

	/// CONSTRUCTOR ///
	
	public IfElseStatement(BooleanExpression condition, MyStatement ifBody, MyStatement elseBody) {
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
	}
	
	
	/// BASIC PROPERTIES ///

	private BooleanExpression condition;
	private MyStatement ifBody;
	private MyStatement elseBody;
	
	
	/// GETTERS ///

	protected BooleanExpression getCondition() {
		return this.condition;
	}
	
	
	/// SETTERS ///

	private void setCondition(BooleanExpression condition) {
		this.condition = condition;
	}

	private void setElseBody(MyStatement elseBody) {
		this.elseBody = elseBody;
	}

	private void setIfBody(MyStatement ifBody) {
		this.ifBody = ifBody;
	}
	
	
	/// CHECKERS ///
	
	protected boolean containsStatement(String name){
		boolean contains = false;
		if (ifBody.getClass().getSimpleName().equals(name) && ifBody.containsStatement(name))
			contains = true;
		
		if (elseBody != null && contains == false)
			if (elseBody.getClass().getSimpleName().equals(name) && elseBody.containsStatement(name))
				contains = true;
	
		return contains;
	}
	
	
	/// EVALUATION ///

	@Override
	protected void evaluate(Program program, List<MyExpression> actualArgs) {
		setStatementProgram(program);
			if ((boolean) ((MyExpression) condition).getExpressionResult(program, actualArgs))
				ifBody.evaluate(program, actualArgs);
			else if (elseBody != null)
				elseBody.evaluate(program, actualArgs);
	}

	protected Object evaluateInFunction(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setStatementProgram(program);

		if ((boolean) ((MyExpression) getCondition()).getExpressionResult(program, actualArgs, function)) {
			if (ifBody instanceof AssignmentStatement)
				((AssignmentStatement) ifBody).assignLocalVariable(getStatementProgram(), actualArgs, function);
			else
				return ifBody.evaluateInFunction(getStatementProgram(), actualArgs, function);
		} 
		else if (elseBody != null) {
			if (elseBody instanceof AssignmentStatement)
				((AssignmentStatement) elseBody).assignLocalVariable(getStatementProgram(), actualArgs, function);
			else
				return elseBody.evaluateInFunction(getStatementProgram(), actualArgs, function);
		}
		return null;
	}

	@Override
	protected void skipEvaluationUntilLocation(Program program, List<MyExpression> actualArgs, SourceLocation location) {
		if ((boolean) ((MyExpression) condition).getExpressionResult(program, actualArgs))
			ifBody.skipEvaluationUntilLocation(program, actualArgs, location);
		else if (elseBody != null)
			elseBody.skipEvaluationUntilLocation(program, actualArgs, location);
	}

}
