package asteroids.program;

import asteroids.model.Entity;

abstract class OnEntityExpression extends MyExpression {

	/// CONSTRUCTOR ///

	protected OnEntityExpression(Entity operand) throws IllegalArgumentException {
		setOperand(operand);
	}


	/// GETTERS ///

	public Entity getOperand() {
		return operand;
	}
	
	
	/// SETTERS ///

	protected void setOperand(Entity operand) {
		if (! canHaveAsOnEntityExpressionOperand(operand))
			throw new IllegalArgumentException();
		this.operand = operand;
	}
	


	/// CHECKERS ///

	public boolean canHaveAsOnEntityExpressionOperand(Entity expression){
		return ((expression != null) && (expression instanceof Entity));			
	}


	/// PROPERTIES ///

	private Entity operand;


	


}

