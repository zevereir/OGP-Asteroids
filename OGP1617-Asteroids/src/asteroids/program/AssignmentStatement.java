package asteroids.program;

import java.util.List;

class AssignmentStatement extends MyStatement {
	
	/// CONSTRUCTOR ///

	public AssignmentStatement(String variableName, MyExpression expression) {
		setVariableName(variableName);
		setExpression(expression);
	}

	
	/// BASIC PROPERTIES ///

	private MyExpression expression;
	private String variableName;
	

	/// GETTERS ///
	
	protected MyExpression getAssignmentExpression() {
		return this.expression;
	}

	protected String getAssignmentVariableName() {
		return this.variableName;
	}

	
	/// SETTERS ///

	protected void setExpression(MyExpression expression) {
		this.expression = expression;
	}
	
	protected void setVariableName(String variableName) {
		this.variableName = variableName;

	}

	
	/// CHECKERS ///
	
	protected boolean isValidVariable(String variableName, MyExpression expression, List<MyExpression> actualArgs) {
		return ((!getStatementProgram().getProgramFunctions().containsKey(variableName))
				&& (expression.getExpressionResult(getStatementProgram(), actualArgs, null) instanceof Double));
	}
	
	
	/// HELP FUNCTIONS ///

	public void assignLocalVariable(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setStatementProgram(program);
		function.addLocalVariable(variableName, expression.getExpressionResult(program, actualArgs, function));
	}
	
	
	/// EVALUATION ///

	@Override
	public void evaluate(Program program, List<MyExpression> actualArgs) {
		setStatementProgram(program);

		if (isValidVariable(getAssignmentVariableName(), getAssignmentExpression(), actualArgs))
			getStatementProgram().addVariable(variableName, expression.getExpressionResult(program, actualArgs, null));
		else
			throw new IllegalArgumentException();
	}

}
