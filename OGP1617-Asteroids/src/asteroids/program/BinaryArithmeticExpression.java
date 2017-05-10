package asteroids.program;

abstract class BinaryArithmeticExpression extends ArithmeticExpression {

	/// CONSTRUCTOR ///

	protected BinaryArithmeticExpression(MyExpression leftExpression, MyExpression rightExpression)
			throws IllegalArgumentException {
		setLeftOperand(leftExpression);
		setRightOperand(rightExpression);
	}


	/// BASIC PROPERTIES ///

	protected MyExpression left_operand = null;
	protected MyExpression right_operand = null;

	
	/// GETTERS ///

	protected MyExpression getLeftOperand() {
		return left_operand;
	}

	protected int getNbOperands() {
		return 2;
	}

	protected MyExpression getRightOperand() {
		return right_operand;
	}
	

	/// SETTERS ///

	protected void setLeftOperand(MyExpression expression) {
		left_operand = expression;
	}

	protected void setRightOperand(MyExpression expression) {
		right_operand = expression;
	}

	
	/// CHECKERS ///

	protected boolean canHaveAsNbOperands(double number) {
		return number == 2;
	}

}
