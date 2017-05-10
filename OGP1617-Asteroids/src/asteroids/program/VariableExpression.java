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
		

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs,MyFunction function) {
		setExpressionProgram(program);
		Object result = null;
		if (function != null)		
			result = function.getFunctionLocalVariables().get(variableName);
		
		if (result == null)
			result = this.getExpressionProgram().getProgramVariables().get(variableName);
	
		if (result == null)
			throw new IllegalArgumentException();
		else {
			System.out.println("VariableExpression, variable: "+result);
			return result;
		}
	}
	
	
	/// SETTERS ///

	protected void setVariable(String variableName) throws IllegalArgumentException{
		this.variableName = variableName;
	}

}

