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
	
	public ArithmeticExpression getOperand() {
		return operand;
	}
	
	protected void setOperand(ArithmeticExpression operand) {
		this.operand = operand;
	}
	
	private ArithmeticExpression operand;
	
	@Override
	public boolean hasAsSubExpression(ArithmeticExpression expression){
		if (this == expression)
			return true;
		else
			return (getOperand().hasAsSubExpression(expression));				
	}

}
