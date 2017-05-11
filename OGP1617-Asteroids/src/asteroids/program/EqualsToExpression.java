package asteroids.program;

import java.util.List;

class EqualsToExpression extends BinaryExpression {

	/// CONSTRUCTOR ///

	protected EqualsToExpression(MyExpression leftExpression, MyExpression rightExpression) throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	
	/// GETTERS ///
	
	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		Double leftParameter = null;
		Double rightParameter = null;
		Object leftOperand = null;
		Object rightOperand = null;

		if (getLeftOperand() instanceof ParameterExpression) {
			leftParameter = (Double) (actualArgs.get(((ParameterExpression) getLeftOperand()).getParameterNumber() - 1))
					.getExpressionResult(program, actualArgs, function);
		}
		if (getRightOperand() instanceof ParameterExpression) {
			rightParameter = (Double) (actualArgs
					.get(((ParameterExpression) getRightOperand()).getParameterNumber() - 1))
							.getExpressionResult(program, actualArgs, function);
		}

		if (leftParameter != null)
			leftOperand = leftParameter;
		else
			leftOperand = getLeftOperandResult(program, actualArgs, function);

		if (rightParameter != null)
			rightOperand = rightParameter;
		else
			rightOperand = getRightOperandResult(program, actualArgs, function);

		return leftOperand.equals(rightOperand);
	}

	
	/// CHECKERS ///

	protected boolean canHaveAsNbOperands(double number) {
		return number == 2;
	}

	
	/// HELP FUNCTIONS ///
	
	protected void assignExpressionToParameter(List<MyExpression> actualArgs) {
		if (getLeftOperand() instanceof ParameterExpression)
			setLeftOperand(actualArgs.get(((ParameterExpression) getLeftOperand()).getParameterNumber() - 1));

		if (getRightOperand() instanceof ParameterExpression)
			setRightOperand(actualArgs.get(((ParameterExpression) getRightOperand()).getParameterNumber() - 1));
	}

	
	/// PROPERTIES ///

	protected MyExpression left_operand = null;
	protected MyExpression right_operand = null;

}
