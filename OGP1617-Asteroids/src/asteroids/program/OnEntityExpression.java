package asteroids.program;

import asteroids.model.Entity;

abstract class OnEntityExpression extends MyExpression {

	/// CONSTRUCTOR ///

	protected OnEntityExpression(MyExpression operand) throws IllegalArgumentException {
		setOperand(operand);
	}

	
	/// BASIC PROPERTIES ///

	private MyExpression operand;
	

	/// GETTERS ///

	public MyExpression getOperand() {
		if (canHaveAsOnEntityExpressionOperand(getExpressionProgram(), operand))
			return operand;
		else
			throw new IllegalArgumentException();
	}
	
	protected Entity getOperandResult(Program program) {
		return (Entity)getOperand().getExpressionResult(program, null, null);
	}
	
	
	/// SETTERS ///

	protected void setOperand(MyExpression operand) {
		this.operand = operand;
	}

	
	/// CHECKERS ///
	
	public boolean canHaveAsOnEntityExpressionOperand(Program program, MyExpression expression) {
		return (expression.getExpressionResult(program, null, null) != null
				&& expression.getExpressionResult(program, null, null) instanceof Entity);
	}

}
