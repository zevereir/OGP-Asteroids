package asteroids.program;

import java.util.List;

class LogicalNegationExpression extends MyExpression {

	/// CONSTRUCTOR ///

	public LogicalNegationExpression(MyExpression operand) {
		setOperand(operand);
	}


	/// BASIC PROPERTIES ///
	
	private MyExpression operand;
	

	/// GETTERS ///

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function)
			throws IllegalArgumentException {
		setExpressionProgram(program);

		if (canHaveAsLogicalNegationOperand(getOperand(), actualArgs, function)) {
			return !(Boolean) getOperand().getExpressionResult(program, actualArgs, function);
		} else
			throw new IllegalArgumentException();
	}

	protected MyExpression getOperand() {
		return operand;
	}

	
	/// SETTERS ///

	protected void setOperand(MyExpression operand) throws IllegalArgumentException {
		this.operand = operand;
	}

	
	/// CHECKERS ///

	protected boolean canHaveAsLogicalNegationOperand(MyExpression operand, List<MyExpression> actualArgs,
			MyFunction function) {
		return (operand.getExpressionResult(getExpressionProgram(), actualArgs, function) instanceof Boolean);

	}

	
	/// HELP FUNCTIONS ///
	
	protected void assignExpressionToParameter(List<MyExpression> actualArgs) {
		if (getOperand() instanceof ParameterExpression)
			setOperand(actualArgs.get(((ParameterExpression) getOperand()).getParameterNumber() - 1));
	}

}
