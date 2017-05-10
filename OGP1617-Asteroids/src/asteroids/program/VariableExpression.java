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
	
	protected Object getVariable(String variableName,List<MyExpression> actualArgs,MyFunction function){
		Object result = null;
		if (function != null)		
			result = function.getFunctionLocalVariables().get(variableName);
		
		if (result == null)
			result = this.getExpressionProgram().getProgramVariables().get(variableName);
		
		if (result == null)
			throw new IllegalArgumentException();
		else
			return result;
	}
	

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs,MyFunction function) {
		setExpressionProgram(program);
		
		return getVariable(variableName,actualArgs,function);
	}
	
	
	/// SETTERS ///

	protected void setVariable(String variableName) throws IllegalArgumentException{
		this.variableName = variableName;
	}

}

