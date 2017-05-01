package asteroids.program;

class AdditionExpression extends BinaryArithmeticExpression {
	
	protected AdditionExpression(MyExpression leftExpression, MyExpression rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	@Override
	protected Object getExpressionResult() {
		return (double)getLeftOperand().getExpressionResult() + (double)getRightOperand().getExpressionResult();
	}

}
