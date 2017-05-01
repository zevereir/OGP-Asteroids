package asteroids.program;

class VariableExpression extends MyExpression {
	
	/// CONSTRUCTOR ///
	public VariableExpression(MyExpression operand) {
		setVariableOperand(operand);
	}

	/// GETTERS ///
	protected MyExpression getOperand(){
		return operand;
	}

	@Override
	protected Object getExpressionResult() {
		return (double)operand.getExpressionResult();
	}
	/// SETTERS ///

	protected void setVariableOperand(MyExpression operand) throws IllegalArgumentException{
		if (!canHaveAsVariableOperand(operand))
			throw new IllegalArgumentException();
		this.operand = operand;
	}
	/// CHECKERS ///
	protected boolean canHaveAsVariableOperand(MyExpression operand){
		return (operand.getExpressionResult() instanceof Double && operand.getExpressionResult() != null );

	}

	/// PROPERTIES ///
	private MyExpression operand;

}

