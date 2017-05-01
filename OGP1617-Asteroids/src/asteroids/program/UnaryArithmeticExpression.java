package asteroids.program;

abstract class UnaryArithmeticExpression extends ArithmeticExpression {

	/// CONSTRUCTOR ///
	
	protected UnaryArithmeticExpression(ArithmeticExpression operand) throws IllegalArgumentException {
		if (! canHaveAsArithmeticOperand(operand))
			throw new IllegalArgumentException();
		
		setOperand(operand);
	}
	
	
	/// GETTERS ///
	
	public final int getNbOperands() {
		return 1;
	}
	
	public ArithmeticExpression getOperand() {
		return operand;
	}
	
	
	/// SETTERS ///
	
	protected void setOperand(ArithmeticExpression operand) {
		this.operand = operand;
	}
	
	
	/// CHECKERS ///
	
	public final boolean canHaveAsNbOperands(int number) {
		return number == 1;
	}
	
	@Override
	public boolean hasAsSubExpression(ArithmeticExpression expression){
		if (this == expression)
			return true;
		else
			return (getOperand().hasAsSubExpression(expression));				
	}
	
	
	/// PROPERTIES ///
	
	private ArithmeticExpression operand;

}
