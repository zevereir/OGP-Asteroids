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
		
		Double[] parameterArray = getExpressionParameter(actualArgs);

		Double leftParameter = parameterArray[0];
		Double rightParameter = parameterArray[1];
		
		Double leftOperand = null;
		Double rightOperand = null;
		
		if (leftParameter != null)
			leftOperand = leftParameter;
		else{
			if (canHaveAsArithmeticOperand(program, actualArgs, getLeftOperand()))
				leftOperand = (double)getLeftOperand().getExpressionResult(program, actualArgs);
			else
				throw new IllegalArgumentException();
		}
		
		if (rightParameter != null)
			rightOperand = rightParameter;
		else {
			if (canHaveAsArithmeticOperand(program, actualArgs, getRightOperand()))
				rightOperand = (double)getRightOperand().getExpressionResult(program, actualArgs);
			else
				throw new IllegalArgumentException();
		}
		
		return leftOperand < rightOperand;
	}

}
