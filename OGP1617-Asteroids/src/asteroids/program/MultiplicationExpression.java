package asteroids.program;

class MultiplicationExpression extends BinaryArithmeticExpression {
	protected MultiplicationExpression(MyExpression leftExpression, MyExpression rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	@Override
	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);

		Object leftOperand = getLeftOperand().getExpressionResult(program);
		Object rightOperand = getRightOperand().getExpressionResult(program);
		
		return (double)leftOperand * (double)rightOperand;
	}
	

}
