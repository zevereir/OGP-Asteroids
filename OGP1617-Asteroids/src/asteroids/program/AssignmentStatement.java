package asteroids.program;

class AssignmentStatement extends MyStatement {
	
	public AssignmentStatement(String variableName, MyExpression expression) {
		setVariableName(variableName);
		setExpression(expression);
	}

	@Override
	public void evaluate(Program program) {
		setStatementProgram(program);
		if (isValidVariable(getAssignmentVariableName(),getAssignmentExpression()))
			getStatementProgram().addVariable(variableName,expression.getExpressionResult(program));
		else
			throw new IllegalArgumentException();
	}
	
	/// GETTERS ///
	protected MyExpression getAssignmentExpression(){
		return this.expression;
	}
	
	protected String getAssignmentVariableName(){
		return this.variableName;
	}
	
	/// SETTERS ///
	protected void setVariableName(String variableName){
		this.variableName = variableName;
		
	}
	protected void setExpression(MyExpression expression){
		this.expression = expression;
		
	}
	
	
	/// CHECKERS ///
	protected boolean isValidVariable(String variableName,MyExpression expression){
		return ((!getStatementProgram().getProgramFunctions().containsKey(variableName)) &&(expression.getExpressionResult(getStatementProgram()) instanceof Double));
	}
	/// PROPERTIES ///
	
	private MyExpression expression;
	private String variableName;
	

}
