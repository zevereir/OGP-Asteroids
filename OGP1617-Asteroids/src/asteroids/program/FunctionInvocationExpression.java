package asteroids.program;

import java.util.List;

import asteroids.util.ModelException;


class FunctionExpression extends MyExpression {
	
	/// CONSTRUCTOR ///
	
	public FunctionExpression(String functionName,List<MyExpression> actualArgs) {	
		setFunctionName(functionName);
		setArguments(actualArgs);
	}

	
	/// PROPERTIES ///
	private String functionName;
	private List<MyExpression> actualArgs;
	
	
	/// GETTERS ///
	
	protected MyFunction getFunction() {
		if (getExpressionProgram().getProgramFunctions().containsKey(functionName))
			return getExpressionProgram().getProgramFunctions().get(getFunctionName());
		
		throw new IllegalArgumentException();
	}

	protected String getFunctionName(){
		return functionName;
	}
	
	@Override
	protected Object getExpressionResult(Program program) {	
		setExpressionProgram(program);
		
		return evaluateFunctionBody(getFunction().getFunctionBody(),actualArgs);
	}
	
	protected void setArguments(List<MyExpression> actualArgs){
		this.actualArgs = actualArgs;
	}
	
	/// EVALUATE ///
	protected Object evaluateFunctionBody(MyStatement body, List<MyExpression> actualArgs) throws IllegalArgumentException{
		body.assignParameters(actualArgs);
		
		if (body instanceof ReturnStatement) {
			return ((ReturnStatement)body).evaluateInFunction(getExpressionProgram());
		}
		else if (body instanceof IfElseStatement) {
			return ((IfElseStatement)body).evaluateInFunction(getExpressionProgram());
		}
		else {
			return new IllegalArgumentException("FunctionInvocationExpression --> Else statement in evaluateFunctionBody");
		}
	}
	
		
	/// SETTERS ///

	protected void setFunctionName(String functionName){
		this.functionName = functionName;
	}

}
