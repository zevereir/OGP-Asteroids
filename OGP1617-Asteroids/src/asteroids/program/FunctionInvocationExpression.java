package asteroids.program;

import java.util.ArrayList;
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
	
	protected List<MyExpression> getActualArgs() {		
		return actualArgs;
	}
	
	protected List<MyExpression> updateArgs(Program program, List<MyExpression> oldActualArgs,MyFunction function) {
		List<MyExpression> newActualArgs = new ArrayList<MyExpression>();

		for (MyExpression actualArg : actualArgs)
			newActualArgs.add(actualArg);
			
		for (int i=0; i < newActualArgs.size(); i++) {
			MyExpression actualArg = newActualArgs.get(i);
			
			if (!(actualArg instanceof DoubleLiteralExpression)) {
				Double value = (Double) actualArg.getExpressionResult(program, oldActualArgs,function);
				MyExpression newArg = new DoubleLiteralExpression(value);
				newActualArgs.set(i, newArg);
			}
		}
		
		return newActualArgs;
	}
	
	
	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs,MyFunction function) {	
		setExpressionProgram(program);

		List<MyExpression> newActualArgs = updateArgs(program, actualArgs,function);
		
		return evaluateFunctionBody(getFunction().getFunctionBody(),newActualArgs,getFunction());
	}
	
	protected void setArguments(List<MyExpression> actualArgs){
		this.actualArgs = actualArgs;
	}
	
	
	/// EVALUATE ///
	
	protected Object evaluateFunctionBody(MyStatement body, List<MyExpression> actualArgs,MyFunction function) throws IllegalArgumentException{		
		
		if ((body instanceof ReturnStatement) || (body instanceof SequenceStatement) || (body instanceof IfElseStatement) || (body instanceof WhileStatement)) {
			return body.evaluateInFunction(getExpressionProgram(), actualArgs,function);
		}
		
		else {
			throw new IllegalArgumentException("FunctionInvocationExpression --> Else statement in evaluateFunctionBody");
		}
		

		
		
		
//		for (MyExpression argument : actualArgs)
//		System.out.println(argument.getExpressionResult(getExpressionProgram(), actualArgs));
//	
//	body.assignParameters(actualArgs, getExpressionProgram());
//	
	}
	
		
	/// SETTERS ///

	protected void setFunctionName(String functionName){
		this.functionName = functionName;
	}

}
