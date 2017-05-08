package asteroids.program;

import java.util.List;

import asteroids.util.ModelException;


class FunctionExpression extends MyExpression {
	
	/// CONSTRUCTOR ///
	
	public FunctionExpression(String functionName,List<MyExpression> actualArgs) {	
		setFunctionName(functionName);
		System.out.println("New function invoked");
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
	
	protected List<MyExpression> getActualArgs() {
		return actualArgs;
	}
	
	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs) {	
		setExpressionProgram(program);
		
		return evaluateFunctionBody(getFunction().getFunctionBody(),getActualArgs());
	}
	
	protected void setArguments(List<MyExpression> actualArgs){
		this.actualArgs = actualArgs;
	}
	
	/// EVALUATE ///
	protected Object evaluateFunctionBody(MyStatement body, List<MyExpression> actualArgs) throws IllegalArgumentException{		
		
//		for (MyExpression argument : actualArgs)
//			System.out.println(argument.getExpressionResult(getExpressionProgram(), actualArgs));
//		
//		body.assignParameters(actualArgs, getExpressionProgram());
//		
		if (body instanceof ReturnStatement) {
			return ((ReturnStatement)body).evaluateInFunction(getExpressionProgram(), actualArgs);
		}
		else if (body instanceof IfElseStatement) {
			return ((IfElseStatement)body).evaluateInFunction(getExpressionProgram(), actualArgs);
		}
//		else if (body instanceof SequenceStatement) {
//			return ((SequenceStatement)body).evaluateInFunction(getExpressionProgram());
//		}
		else {
			return new IllegalArgumentException("FunctionInvocationExpression --> Else statement in evaluateFunctionBody");
		}
	}
	
		
	/// SETTERS ///

	protected void setFunctionName(String functionName){
		this.functionName = functionName;
	}

}
