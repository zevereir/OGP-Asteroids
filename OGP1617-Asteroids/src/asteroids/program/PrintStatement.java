package asteroids.program;

import java.util.List;

class PrintStatement extends MyStatement {

	/// CONSTRUCTOR ///
	
	public PrintStatement(MyExpression expression) {
		setExpression(expression);
	}

	
	/// BASIC PROPERTIES ///
	
	private MyExpression expression;
	
	
	/// SETTERS ///

	private void setExpression(MyExpression expression) {
		this.expression = expression;
	}

	
	/// EVALUATION ///
	
	@Override
	public void evaluate(Program program, List<MyExpression> actualArgs) {
		setStatementProgram(program);

		if (expression instanceof ParameterExpression)
			throw new IllegalArgumentException();

		Object result = expression.getExpressionResult(program, actualArgs, null);

		System.out.println(result);

		getStatementProgram().addPrintOut(result);
	}

	@Override
	public Object evaluateInFunction(Program program, List<MyExpression> actualArgs, MyFunction function) {
		throw new IllegalArgumentException();
	}

}
