package asteroids.program;

import java.util.List;
import java.util.function.Function;

class FunctionExpression extends MyExpression {
	
	/// CONSTRUCTOR ///
	
	public FunctionExpression(String functionName,List<MyExpression> actualArgs) {	
		setFunction(functionName,actualArgs);
	}

	
	/// PROPERTIES ///
	
	private String functionName;
	private List<MyExpression> actualArgs;
	
	
	/// GETTERS ///
	
	protected MyFunction  getFunction(String functionName){
		return this.getExpressionProgram().getProgramFunctions().get(functionName);
	}

	
	// ----> BEKIJKEN <---- //
	//Als MyFunction is opgelost//
	@Override
	protected Object getExpressionResult() {
		return getFunction(functionName).execute();
	}
	
	
	/// SETTERS ///

	protected void setFunction(String functionName,List<MyExpression> actualArgs) throws IllegalArgumentException{
		this.functionName = functionName;
		getExpressionProgram().addFunction(functionName);
	}

}
