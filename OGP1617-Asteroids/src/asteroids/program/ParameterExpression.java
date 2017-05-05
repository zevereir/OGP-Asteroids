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
	protected Object getExpressionResult() {
		return getParameter();
	}
	
	protected int getParameterNumber(){
		return getParameter().charAt(getParameter().length()-1);
	}
	
	/// SETTERS ///

	protected void setParameter(String ParameterName) throws IllegalArgumentException{
		this.ParameterName = ParameterName;
	}

}
