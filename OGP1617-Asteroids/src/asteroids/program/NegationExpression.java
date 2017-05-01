package asteroids.program;

class NegationExpression extends UnaryArithmeticExpression {

	protected NegationExpression(ArithmeticExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult() {
		return -(double)getOperand().getExpressionResult();
	}
	
	public String getOperatorSymbol() {
		return "-";
	}

	
	
	

}
