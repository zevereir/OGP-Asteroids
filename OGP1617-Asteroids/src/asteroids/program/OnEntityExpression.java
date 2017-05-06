package asteroids.program;

import asteroids.model.Entity;

abstract class OnEntityExpression extends MyExpression {

	/// CONSTRUCTOR ///

	protected OnEntityExpression(MyExpression operand) throws IllegalArgumentException {
		setOperand(operand);
	}


	/// GETTERS ///

	public MyExpression getOperand() {
		if (canHaveAsOnEntityExpressionOperand(this.getExpressionProgram(), operand))
			return operand;
		else
			throw new IllegalArgumentException();
	}
	
	
	/// SETTERS ///

	protected void setOperand(MyExpression operand) {
//		if (! canHaveAsOnEntityExpressionOperand(operand))
//			throw new IllegalArgumentException();
		this.operand = operand;
	}
	


	/// CHECKERS ///
//
	public boolean canHaveAsOnEntityExpressionOperand(Program program,MyExpression expression){
		return (expression.getExpressionResult(program) != null && expression.getExpressionResult(program) instanceof Entity);			
	}


	/// PROPERTIES ///

	private MyExpression operand;

}

