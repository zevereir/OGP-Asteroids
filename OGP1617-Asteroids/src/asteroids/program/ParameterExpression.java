package asteroids.program;

import java.util.List;
import java.util.function.Function;

class ParameterExpression extends MyExpression {
	
	/// CONSTRUCTOR ///
	
	public ParameterExpression(String ParameterName) {	
		setParameter(ParameterName);
	}

	/// PROPERTIES ///
	private String ParameterName;
	
	/// GETTERS ///

	protected String getParameter(){
		return this.ParameterName;
	}

	@Override
	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		return getParameter();
	}
	
	protected int getParameterNumber(){
		String parameter = getParameter();		
		return Integer.parseInt(parameter.substring(1));
	}
	
	/// SETTERS ///

	protected void setParameter(String ParameterName) throws IllegalArgumentException{
		this.ParameterName = ParameterName;
	}

//	protected void assignExpressionToParameter(List<MyExpression> actualArgs) {
//		setParameter(actualArgs.get(getParameter()).getParameterNumber()-1);
//	}
	
}
