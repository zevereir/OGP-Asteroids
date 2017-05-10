package asteroids.program;

import java.util.List;

class SquareRootExpression extends UnaryArithmeticExpression {
	
	/// CONSTRUCTOR ///

	protected SquareRootExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}
	
	
	/// GETTERS ///

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		Double[] parameterArray = getExpressionParameter(actualArgs, function);
		Double Parameter = parameterArray[0];
		Double Operand = null;

		if (Parameter != null)
			Operand = Parameter;
		else {
			if (canHaveAsArithmeticOperand(program, actualArgs, getOperand(), function))
				Operand = (double) getOperand().getExpressionResult(program, actualArgs, function);
			else
				throw new IllegalArgumentException();
		}

		if (Operand >= 0)
			return Math.sqrt(Operand);
		else
			throw new IllegalArgumentException();
	}

}
