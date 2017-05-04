package asteroids.program;

class AssignmentStatement extends MyStatement {
	
	public AssignmentStatement(String variableName, MyExpression expression) {
		setVariableName(variableName);
		setExpression(expression);
	}

	@Override
	public void evaluate() {
		getStatementProgram().addVariable(variableName,expression);
		
	}
	
	protected void setVariableName(String variableName){
		this.variableName = variableName;
		
	}
	protected void setExpression(MyExpression expression){
		this.expression = expression;
		
	}
	
	private MyExpression expression;
	private String variableName;
	

}
