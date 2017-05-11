package asteroids.program;

import java.util.List;

abstract class UnaryArithmeticExpression extends ArithmeticExpression {

	/// CONSTRUCTOR ///

	protected UnaryArithmeticExpression(MyExpression operand) {
		setOperand(operand);
	}

	
	/// BASIC PROPERTIES ///

	private MyExpression operand;

	
	/// GETTERS ///

	public final int getNbOperands() {
		return 1;
	}

	public MyExpression getOperand() {
		return operand;
	}
	
	protected Object getOperandResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		return getOperand().getExpressionResult(program, actualArgs, function);
	}

	
	/// SETTERS ///

	protected void setOperand(MyExpression operand) {
		this.operand = operand;
	}

	
	/// CHECKERS ///

	public final boolean canHaveAsNbOperands(int number) {
		return number == 1;
	}

}
