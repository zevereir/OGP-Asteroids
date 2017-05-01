package asteroids.program;

class NegationExpression extends UnaryArithmeticExpression {
	
	public public NegationExpression(ArithmeticExpression operand) {
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
	
	
	

}
