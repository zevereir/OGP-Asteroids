package asteroids.program;

import java.util.List;

class SquareRootExpression extends UnaryArithmeticExpression{
	
	protected SquareRootExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs) {
		setExpressionProgram(program);
		
		MyExpression[] parameterArray = getExpressionParameter(actualArgs);

		MyExpression Parameter = parameterArray[0];
		
		MyExpression Operand = null;
		
		if (Parameter != null)
			Operand = Parameter;
		else
			Operand = getOperand();

		return Math.sqrt((double) Operand.getExpressionResult(program, actualArgs));
	}
	
}
