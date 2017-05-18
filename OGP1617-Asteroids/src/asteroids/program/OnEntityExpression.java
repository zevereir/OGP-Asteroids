package asteroids.program;

import asteroids.model.Entity;

abstract class OnEntityExpression extends MyExpression implements ArithmeticExpression {

	/// CONSTRUCTOR ///

	protected OnEntityExpression(MyExpression operand) throws IllegalArgumentException {
		setOperand(operand);
	}

	
	/// BASIC PROPERTIES ///

	private MyExpression operand;
	

	/// GETTERS ///

	public MyExpression getOperand() {
		return operand;
	}

	protected Entity getOperandResult(Program program) {
		return (Entity) getOperand().getExpressionResult(program);
	}
	
	
	/// SETTERS ///

	protected void setOperand(MyExpression operand) {
		if (canHaveAsOnEntityExpressionOperand(operand))
			this.operand = operand;
		else
			throw new IllegalArgumentException();
	}

	
	/// CHECKERS ///
	
	public boolean canHaveAsOnEntityExpressionOperand(MyExpression expression) {
		return (expression instanceof EntityExpression && !(expression instanceof NullEntity));
	}

}
