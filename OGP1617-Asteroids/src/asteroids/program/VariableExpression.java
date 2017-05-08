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
		Object result = this.getExpressionProgram().getProgramVariables().get(variableName);
		if (result == null)
			throw new IllegalArgumentException();
		else
			return result;
	}
	
	protected Object getFunctionVariable(Program program, List<MyExpression> actualArgs,MyFunction function){	
		setExpressionProgram(program);
		
		Object result = function.getFunctionLocalVariables().get(variableName);
		
		if (result == null)
			return getVariable(variableName);
		else
			return result;
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

