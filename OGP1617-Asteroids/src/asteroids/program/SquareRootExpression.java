package asteroids.program;

import java.util.List;

class SquareRootExpression extends UnaryExpression implements ArithmeticExpression {
	
	/// CONSTRUCTOR ///

	protected SquareRootExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}
	
	
	/// GETTERS ///

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		UnaryArithmeticExpression expression = new UnaryArithmeticExpression();
		Double Operand = (double)expression.solveParameter(program, actualArgs, function);

		if (Operand >= 0)
			return Math.sqrt(Operand);
		else
			throw new IllegalArgumentException();
	}

}
