package asteroids.program;

import java.util.List;

class VariableExpression extends MyExpression {
	
	/// CONSTRUCTOR ///
	
	public VariableExpression(String variableName) {	
		setVariable(variableName);
	}

	/// PROPERTIES ///
	private String variableName;
	
	/// GETTERS ///
	
	protected Object getVariable(String variableName){
		return this.getExpressionProgram().getProgramVariables().get(variableName);
	}

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs) {
		setExpressionProgram(program);
		
		return getVariable(variableName);
	}
	
	
	/// SETTERS ///

	protected void setVariable(String variableName) throws IllegalArgumentException{
		this.variableName = variableName;
	}

}

