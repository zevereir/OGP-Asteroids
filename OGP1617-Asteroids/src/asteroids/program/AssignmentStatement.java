package asteroids.program;

class AssignmentStatement extends MyStatement {
	
	public AssignmentStatement(String variableName, MyExpression expression) {
		setVariableName(variableName);
		setExpression(expression);
	}

	@Override
	public void evaluate(Program program) {
		setStatementProgram(program);
		if (isValidVariableName(variableName))
			getStatementProgram().addVariable(variableName,expression.getExpressionResult(program));
		else
			throw new IllegalArgumentException();
	}
	
	/// SETTERS ///
	protected void setVariableName(String variableName){
		this.variableName = variableName;
		
	}
	protected void setExpression(MyExpression expression){
		this.expression = expression;
		
	}
	
	/// CHECKERS ///
	protected boolean isValidVariableName(String variableName){
		return (!getStatementProgram().getProgramFunctions().containsKey(variableName));
	}
	/// PROPERTIES ///
	
	private MyExpression expression;
	private String variableName;
	

}
