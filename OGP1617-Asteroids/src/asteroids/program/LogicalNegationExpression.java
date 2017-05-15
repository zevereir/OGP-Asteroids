package asteroids.program;

import java.util.List;

class LogicalNegationExpression extends UnaryExpression implements BooleanExpression {

	/// CONSTRUCTOR ///

	public LogicalNegationExpression(MyExpression operand) {
		super(operand);
	}
	

	/// GETTERS ///

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function)
			throws IllegalArgumentException {
		setExpressionProgram(program);

		if (canHaveAsLogicalNegationOperand(getOperand())) {
			return !(Boolean) getOperandResult(program, actualArgs, function);
		} else
			throw new IllegalArgumentException();
	}

	
	/// CHECKERS ///

	protected boolean canHaveAsLogicalNegationOperand(MyExpression operand) {
		return (operand instanceof BooleanExpression);
	}

	
	/// HELP FUNCTIONS ///
	
	protected void assignExpressionToParameter(List<MyExpression> actualArgs) {
		if (getOperand() instanceof ParameterExpression)
			setOperand(actualArgs.get(((ParameterExpression) getOperand()).getParameterNumber() - 1));
	}

}
