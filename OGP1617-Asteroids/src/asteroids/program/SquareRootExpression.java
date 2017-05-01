package asteroids.program;

class SquareRootExpression extends UnaryArithmeticExpression{
	
	protected SquareRootExpression(ArithmeticExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult() throws IllegalArgumentException{
		if ((double)getOperand().getExpressionResult() < 0)
			throw new IllegalArgumentException();
		
		return Math.sqrt((double)getOperand().getExpressionResult());
	}
	
}
