package asteroids.program;

import java.util.List;


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
	protected Object getExpressionResult(Program program) {	
		setExpressionProgram(program);
		return evaluateFunctionBody(getFunction().getFunctionBody(),actualArgs);
	}
	
	protected void setArguments(List<MyExpression> actualArgs){
		this.actualArgs = actualArgs;
	}
	
	/// EVALUATE ///
	protected static Object evaluateFunctionBody(MyStatement body, List<MyExpression> actualArgs) throws IllegalArgumentException{

		body.assignParameters(actualArgs);

		if (body instanceof ReturnStatement)
			return ((ReturnStatement)body).evaluateInFunction();

		else if (body instanceof IfElseStatement)
			return ((IfElseStatement)body).evaluateInFunction();

		else {
			return new IllegalArgumentException("FunctionInvocationExpression --> Else statement in evaluateFunctionBody");
		}
	}
	
		
	/// SETTERS ///

	protected void setFunction(String functionName){
		this.function = getFunctionFromProgram(functionName);
	}

}
