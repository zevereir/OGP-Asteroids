package asteroids.program;

class NegationExpression extends UnaryArithmeticExpression {

	protected NegationExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		return -(double)getOperand().getExpressionResult(program);
	}
	
	public String getOperatorSymbol() {
		return "-";
	}

	
	
	

}
