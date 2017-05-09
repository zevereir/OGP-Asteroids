package asteroids.program;

import java.util.List;

abstract class UnaryArithmeticExpression extends ArithmeticExpression {

	/// CONSTRUCTOR ///
	
	protected UnaryArithmeticExpression(MyExpression operand) {
		setOperand(operand);
	}
	
	
	/// GETTERS ///
	
	public final int getNbOperands() {
		return 1;
	}
	
	public MyExpression getOperand() {
//		if (canHaveAsArithmeticOperand(getExpressionProgram(), null, operand))
//			return operand;
//		else
//			throw new IllegalArgumentException();
		return operand;
	}
	
	
	/// SETTERS ///
	
	protected void setOperand(MyExpression operand) {
		this.operand = operand;
	}
	
	
	/// CHECKERS ///
	
	public final boolean canHaveAsNbOperands(int number) {
		return number == 1;
	}
	
//	protected void assignExpressionToParameter(List<MyExpression> actualArgs) {
//		if (getOperand() instanceof ParameterExpression)
//			setOperand(actualArgs.get(((ParameterExpression)getOperand()).getParameterNumber()-1));
//	}
	
	/// PROPERTIES ///
	
	private MyExpression operand;

}
