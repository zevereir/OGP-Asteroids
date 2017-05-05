package asteroids.program;

class AdditionExpression extends BinaryArithmeticExpression {
	
	protected AdditionExpression(MyExpression leftExpression, MyExpression rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	@Override
	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		return (double)getLeftOperand().getExpressionResult(program) + (double)getRightOperand().getExpressionResult(program);
	}


}
