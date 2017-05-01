package asteroids.program;

class MultiplicationExpression extends BinaryArithmeticExpression {
	protected MultiplicationExpression(ArithmeticExpression leftExpression, ArithmeticExpression rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	@Override
	protected Object getExpressionResult() {
		return (double)getLeftOperand().getExpressionResult() * (double)getRightOperand().getExpressionResult();
	}
	

}
