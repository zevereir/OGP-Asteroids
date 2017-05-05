package asteroids.program;

class MultiplicationExpression extends BinaryArithmeticExpression {
	protected MultiplicationExpression(ArithmeticExpression leftExpression, ArithmeticExpression rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	@Override
	protected Object getExpressionResult(Program program) {
		return (double)getLeftOperand().getExpressionResult(program) * (double)getRightOperand().getExpressionResult(program);
	}
	

}
