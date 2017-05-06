package asteroids.program;

class AdditionExpression extends BinaryArithmeticExpression {
	
	protected AdditionExpression(MyExpression leftExpression, MyExpression rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	@Override
	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		
		if (canHaveAsOperands(program, getLeftOperand(), getRightOperand()))
			return (double)getLeftOperand().getExpressionResult(program) + (double)getRightOperand().getExpressionResult(program);
		
		throw new IllegalArgumentException();
	}

	private boolean canHaveAsOperands(Program program, MyExpression leftOperand, MyExpression rightOperand) {
		return (canHaveAsArithmeticOperand(program, leftOperand) && canHaveAsArithmeticOperand(program, rightOperand));
	}

}
