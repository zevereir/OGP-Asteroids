package asteroids.program;

class SquareRootExpression extends UnaryArithmeticExpression{
	
	protected SquareRootExpression(ArithmeticExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult(Program program) throws IllegalArgumentException{
		if ((double)getOperand().getExpressionResult(program) < 0)
			throw new IllegalArgumentException();
		
		return Math.sqrt((double)getOperand().getExpressionResult(program));
	}
	
}
