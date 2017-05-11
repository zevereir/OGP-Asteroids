package asteroids.program;

import java.util.List;

class ReturnStatement extends MyStatement {

	/// CONSTRUCTOR ///

	public ReturnStatement(MyExpression expression) {
		setReturnExpression(expression);
	}

	
	/// BASIC PROPERTIES ///
	
	private MyExpression expression;
	
	
	/// GETTERS ///

	protected MyExpression getReturnExpression() {
		return this.expression;
	}

	
	/// SETTERS ///
	
	protected void setReturnExpression(MyExpression expression) {
		this.expression = expression;
	}
	
	/// CHECKERS ///
	
	protected boolean containsStatement(String name){
		return getClass().getSimpleName().equals(name);
	}
	
	/// EVALUATION ///

	@Override
	protected void evaluate(Program program, List<MyExpression> actualArgs) {
		throw new IllegalArgumentException();
	}

	protected Object evaluateInFunction(Program program, List<MyExpression> actualArgs, MyFunction function) {
		Object result;
		if (expression instanceof VariableExpression)
			result = ((VariableExpression) expression).getExpressionResult(program, actualArgs, function);
		else
			result = expression.getExpressionResult(program, actualArgs, function);

		return result;
	}

}
