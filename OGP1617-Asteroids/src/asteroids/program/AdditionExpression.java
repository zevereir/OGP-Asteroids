package asteroids.program;

import java.util.List;

class AdditionExpression extends BinaryExpression implements ArithmeticExpression {
	
	/// CONSTRUCTOR ///

	protected AdditionExpression(MyExpression leftExpression, MyExpression rightExpression)
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

		return leftOperand + rightOperand;
	}
	
}
