package asteroids.program;

class SquareRootExpression extends UnaryArithmeticExpression{
	
	protected SquareRootExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult(Program program) throws IllegalArgumentException{
		setExpressionProgram(program);
		
		if ((double)getOperand().getExpressionResult(program) < 0)
			throw new IllegalArgumentException();
		
		return Math.sqrt((double)getOperand().getExpressionResult(program));
	}
	
}
