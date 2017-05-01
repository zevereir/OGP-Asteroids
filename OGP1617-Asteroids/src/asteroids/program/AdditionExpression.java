package asteroids.program;

class AdditionExpression extends BinaryArithmeticExpression {
	
	protected AdditionExpression(ArithmeticExpression leftExpression, ArithmeticExpression rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	@Override
	protected Object getExpressionResult() {
		return (double)getLeftOperand().getExpressionResult() + (double)getRightOperand().getExpressionResult();
	}

	@Override
	public boolean hasAsSubExpression(MyExpression expression) {
		// TODO Auto-generated method stub
		return false;
	}

}
