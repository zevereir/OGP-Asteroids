package asteroids.program;

class SquareRootExpression extends UnaryArithmeticExpression{
	
	protected SquareRootExpression(ArithmeticExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasAsSubExpression(MyExpression expression) {
		// TODO Auto-generated method stub
		return false;
	}

}
