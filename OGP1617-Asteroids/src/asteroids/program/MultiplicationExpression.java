package asteroids.program;

import java.util.List;


class MultiplicationExpression extends BinaryExpression implements ArithmeticExpression {
	
	/// CONSTRUCTOR ///
	
	protected MultiplicationExpression(MyExpression leftExpression, MyExpression rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}
	
	
	/// GETTERS ///

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);
		
		BinaryArithmeticExpression solved = new BinaryArithmeticExpression();
		Double leftOperand = (double)solved.solveLeftOperand(program, actualArgs, function);
		Double rightOperand = (double)solved.solveRightOperand(program, actualArgs, function);

		return leftOperand * rightOperand;
	}

}
