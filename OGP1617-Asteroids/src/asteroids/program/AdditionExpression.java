package asteroids.program;

class AdditionExpression extends BinaryArithmeticExpression {
	
	protected AdditionExpression(MyExpression leftExpression, MyExpression rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	@Override
	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		
		Object leftOperand = getLeftOperand().getExpressionResult(program);
		Object rightOperand = getRightOperand().getExpressionResult(program);
		
		return (double)leftOperand + (double)rightOperand;
	}



}
