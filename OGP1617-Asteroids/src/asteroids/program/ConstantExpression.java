package asteroids.program;

class ConstantExpression extends MyExpression {
	
	/// CONSTRUCTOR ///
	public ConstantExpression(MyExpression operand) {
		if (!canHaveAsConstantOperand(operand))
			throw new IllegalArgumentException();
		this.operand = operand;
	}

	/// GETTERS ///
	protected MyExpression getOperand(){
		return operand;
	}

	@Override
	protected Object getExpressionResult() {
		return (double)operand.getExpressionResult();
	}
	
	/// CHECKERS ///
	protected boolean canHaveAsConstantOperand(MyExpression operand){
		return (operand.getExpressionResult() instanceof Double && operand.getExpressionResult() != null );

	}

	/// PROPERTIES ///
	private final MyExpression operand;

}
