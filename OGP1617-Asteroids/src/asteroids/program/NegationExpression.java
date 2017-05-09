package asteroids.program;

import java.util.List;

class NegationExpression extends UnaryArithmeticExpression {

	protected NegationExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs) {
		setExpressionProgram(program);
		
		Double[] parameterArray = getExpressionParameter(actualArgs);

		Double Parameter = parameterArray[0];
		
		Double Operand = null;
		
		if (Parameter != null)
			Operand = Parameter;
		else{
			if (canHaveAsArithmeticOperand(program, actualArgs, getOperand()))
				Operand = (double)getOperand().getExpressionResult(program, actualArgs);
			else
				throw new IllegalArgumentException();
		}
		
		return -Operand;
	}

}
