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
	
	
	/// EVALUATION ///

	@Override
	protected void evaluate(Program program, List<MyExpression> actualArgs) {
		throw new IllegalArgumentException();
	}

	protected Object evaluateInFunction(Program program, List<MyExpression> actualArgs, MyFunction function) {
		if (expression instanceof VariableExpression)
			return ((VariableExpression) expression).getExpressionResult(program, actualArgs, function);
		else
			return expression.getExpressionResult(program, actualArgs, function);
	}

}
