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
	private MyFunction function;
	/// GETTERS ///
	protected MyFunction getFunction(){
		return function;
	}
	
	protected Object getParameter(String ParameterName){
		return getFunction().getFunctionParameters().get(ParameterName);
	}

	// ----> BEKIJKEN NA MYFUNCTION <----- //
	@Override
	protected Object getExpressionResult() {
		return getParameter(ParameterName);
	}
	
	
	/// SETTERS ///

	protected void setParameter(String ParameterName) throws IllegalArgumentException{
		this.ParameterName = ParameterName;
		getFunction().addParameter(ParameterName,Double.NaN);
	}

}
