package asteroids.program;

import java.util.List;

class LogicalNegationExpression extends UnaryExpression {

	/// CONSTRUCTOR ///

	public LogicalNegationExpression(MyExpression operand) {
		super(operand);
	}
	

	/// GETTERS ///

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function)
			throws IllegalArgumentException {
		setExpressionProgram(program);

		if (canHaveAsLogicalNegationOperand(getOperand(), actualArgs, function)) {
			return !(Boolean) getOperandResult(program, actualArgs, function);
		} else
			throw new IllegalArgumentException();
	}

	
	/// CHECKERS ///

	protected boolean canHaveAsLogicalNegationOperand(MyExpression operand, List<MyExpression> actualArgs, MyFunction function) {
		return (operand.getExpressionResult(getExpressionProgram(), actualArgs, function) instanceof Boolean);

	}

	
	/// HELP FUNCTIONS ///
	
	protected void assignExpressionToParameter(List<MyExpression> actualArgs) {
		if (getOperand() instanceof ParameterExpression)
			setOperand(actualArgs.get(((ParameterExpression) getOperand()).getParameterNumber() - 1));
	}

}
