package asteroids.program;

import java.util.List;

class NegationExpression extends UnaryArithmeticExpression {

	protected NegationExpression(MyExpression operand) throws IllegalArgumentException {
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

		return -(double) Operand.getExpressionResult(program, actualArgs);
	}

}
