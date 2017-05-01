package asteroids.program;

abstract class UnaryArithmeticExpression extends ArithmeticExpression {

	protected UnaryArithmeticExpression(ArithmeticExpression operand) throws IllegalArgumentException {
		if (! canHaveAsArithmeticOperand(operand))
			throw new IllegalArgumentException();
		
		setOperand(operand);
	}
	
	public final int getNbOperands() {
		return 1;
	}
	
	public final boolean canHaveAsNbOperands(int number) {
		return number == 1;
	}
	
	public MyExpression getOperand() {
		return operand;
	}
	
	protected void setOperand(MyExpression operand) {
		this.operand = operand;
	}
	
	private MyExpression operand;
	

}
