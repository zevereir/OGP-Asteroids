package asteroids.program;

import java.util.List;
import java.util.function.Function;

class ParameterExpression extends MyExpression {

	/// CONSTRUCTOR ///

	public ParameterExpression(String ParameterName) {
		setParameter(ParameterName);
	}
	

	/// BASIC PROPERTIES ///
	
	private String ParameterName;

	
	/// GETTERS ///

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		return actualArgs.get(getParameterNumber() - 1).getExpressionResult(program, actualArgs, function);
	}

	protected String getParameter() {
		return this.ParameterName;
	}

	protected int getParameterNumber() {
		String parameter = getParameter();
		
		return Integer.parseInt(parameter.substring(1));
	}

	
	/// SETTERS ///

	protected void setParameter(String ParameterName) throws IllegalArgumentException {
		this.ParameterName = ParameterName;
	}

}
