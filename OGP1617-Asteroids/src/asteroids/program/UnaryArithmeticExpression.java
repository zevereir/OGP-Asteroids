package asteroids.program;

import java.util.List;

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
	
	public MyExpression getOperand() {
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
	
	protected void assignExpressionToParameter(List<MyExpression> actualArgs) {
		if (getOperand() instanceof ParameterExpression)
			setOperand(actualArgs.get(((ParameterExpression)getOperand()).getParameterNumber()-1));
	}
	
	/// PROPERTIES ///
	
	private ArithmeticExpression operand;

}
