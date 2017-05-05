package asteroids.program;

class LessThanExpression extends BinaryArithmeticExpression {
	protected LessThanExpression(ArithmeticExpression leftExpression, ArithmeticExpression rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	@Override
	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		return (double)getLeftOperand().getExpressionResult(program) < (double)getRightOperand().getExpressionResult(program);
	}



}
