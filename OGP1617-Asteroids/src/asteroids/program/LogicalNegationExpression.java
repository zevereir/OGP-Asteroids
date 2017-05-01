package asteroids.program;

class LogicalNegationExpression extends MyExpression {
	/// CONSTRUCTOR ///
	public LogicalNegationExpression(MyExpression operand) {
		setLogicalNegationOperand(operand);
	}
	
	/// GETTERS ///
	protected MyExpression getOperand(){
		return operand;
	}
	
	@Override
	protected Object getExpressionResult() {
		return !(Boolean)operand.getExpressionResult();
	}
	/// SETTERS ///
	
	protected void setLogicalNegationOperand(MyExpression operand) throws IllegalArgumentException{
		if (!canHaveAsLogicalNegationOperand(operand))
			throw new IllegalArgumentException();
		this.operand = operand;
	}
	/// CHECKERS ///
	protected boolean canHaveAsLogicalNegationOperand(MyExpression operand){
		return (operand.getExpressionResult() instanceof Boolean);
		
	}
	
	/// PROPERTIES ///
	private MyExpression operand;

}
