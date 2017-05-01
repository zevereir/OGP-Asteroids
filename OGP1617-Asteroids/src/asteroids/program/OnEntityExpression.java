package asteroids.program;

class OnEntityExpression extends MyExpression {

	/// CONSTRUCTOR ///

	protected OnEntityExpression(EntityExpression operand) throws IllegalArgumentException {
		if (! canHaveAsOnEntityExpressionOperand(operand))
			throw new IllegalArgumentException();

		setOperand(operand);
	}


	/// GETTERS ///

	public EntityExpression getOperand() {
		return operand;
	}
	
	
	/// SETTERS ///

	protected void setOperand(EntityExpression operand) {
		this.operand = operand;
	}
	


	/// CHECKERS ///

	public boolean canHaveAsOnEntityExpressionOperand(EntityExpression expression){
		return expression != null;			
	}


	/// PROPERTIES ///

	private EntityExpression operand;


	


}

