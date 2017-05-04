package asteroids.program;


class VariableExpression extends MyExpression {
	
	/// CONSTRUCTOR ///
	
	public VariableExpression(String variableName) {	
		setVariable(variableName);
	}

	/// PROPERTIES ///
	private String variableName;
	/// GETTERS ///
	
	
	protected MyExpression getVariable(String variableName){
		return this.getExpressionProgram().getProgramVariables().get(variableName);
	}

	@Override
	protected Object getExpressionResult() {
		return getVariable(variableName);
	}
	
	
	/// SETTERS ///

	protected void setVariable(String variableName) throws IllegalArgumentException{
		this.variableName = variableName;
	}

}

