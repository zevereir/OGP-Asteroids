package asteroids.program;

import java.util.List;
import java.util.function.Function;

class FunctionExpression extends MyExpression {
	
	/// CONSTRUCTOR ///
	
	public FunctionExpression(String functionName,List<MyExpression> actualArgs) {	
		setFunction(functionName);
		setArguments(actualArgs);
	}

	
	/// PROPERTIES ///
	private MyFunction function;
	
	private List<MyExpression> actualArgs;
	
	
	/// GETTERS ///
	
	protected MyFunction getFunctionFromProgram(String functionName){
		return this.getExpressionProgram().getProgramFunctions().get(functionName);
	}

	protected MyFunction getFunction(){
		return function;
	}
	
	// ----> BEKIJKEN <---- //
	//Als MyFunction is opgelost//
	@Override
	protected Object getExpressionResult() {
		return getFunction().getFunctionBody().evaluateBody();
	}
	
	
	/// SETTERS ///

	protected void setFunction(String functionName){
		this.function = getFunctionFromProgram(functionName);
	}

}
