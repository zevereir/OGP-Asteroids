package asteroids.program;

class NegationExpression extends UnaryArithmeticExpression {

	protected NegationExpression(ArithmeticExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult() {
		// TODO Auto-generated method stub
		return -(double)getOperand().getExpressionResult();
	}
	
	public String getOperatorSymbol() {
		return "-";
	}

	@Override
	public boolean hasAsSubExpression(MyExpression expression) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
