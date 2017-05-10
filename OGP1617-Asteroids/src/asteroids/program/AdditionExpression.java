package asteroids.program;

import java.util.List;

import asteroids.model.Entity;

class AdditionExpression extends BinaryArithmeticExpression {
	
	protected AdditionExpression(MyExpression leftExpression, MyExpression rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs,MyFunction function) {
		setExpressionProgram(program);
		System.out.println("addition, actualArgs: "+actualArgs);
		Double[] parameterArray = getExpressionParameter(actualArgs);
		
		Double leftParameter = parameterArray[0];
		Double rightParameter = parameterArray[1];
		
		Double leftOperand = null;
		Double rightOperand = null;
		
		if (leftParameter != null)
			leftOperand = leftParameter;
		else{
			if (canHaveAsArithmeticOperand(program, actualArgs, getLeftOperand()))
				leftOperand = (double)getLeftOperand().getExpressionResult(program, actualArgs,funtion);
			else
				throw new IllegalArgumentException();
		}
		
		if (rightParameter != null)
			rightOperand = rightParameter;
		else {
			if (canHaveAsArithmeticOperand(program, actualArgs, getRightOperand()))
				rightOperand = (double)getRightOperand().getExpressionResult(program, actualArgs,function);
			else
				throw new IllegalArgumentException();
		}
		
		return leftOperand + rightOperand;
	}

}
