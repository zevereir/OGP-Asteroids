package asteroids.program;

class MultiplicationExpression extends BinaryArithmeticExpression {
	protected MultiplicationExpression(MyExpression leftExpression, MyExpression rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	@Override
	protected Object getExpressionResult() {
		return (double)getLeftOperand().getExpressionResult() * (double)getRightOperand().getExpressionResult();
	}

}
