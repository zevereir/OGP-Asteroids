package asteroids.program;

import java.util.List;

class LessThanExpression extends BinaryArithmeticExpression {
	protected LessThanExpression(MyExpression leftExpression, MyExpression rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs) {
		setExpressionProgram(program);
		
		MyExpression[] parameterArray = getExpressionParameter(actualArgs);

		MyExpression leftParameter = parameterArray[0];
		MyExpression rightParameter = parameterArray[1];
		
		MyExpression leftOperand = null;
		MyExpression rightOperand = null;
		
		if (leftParameter != null)
			leftOperand = leftParameter;
		else
			leftOperand = getLeftOperand();
		
		if (rightParameter != null)
			rightOperand = rightParameter;
		else
			rightOperand = getRightOperand();
		
		return (double)leftOperand.getExpressionResult(program, actualArgs) < (double)rightOperand.getExpressionResult(program, actualArgs);
	}

}
