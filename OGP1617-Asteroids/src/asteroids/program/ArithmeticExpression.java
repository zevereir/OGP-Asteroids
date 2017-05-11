package asteroids.program;

import java.util.List;

import asteroids.model.Entity;

public interface ArithmeticExpression {
	
	/// CHECKERS ///

	public default boolean canHaveAsArithmeticOperand(Program program, List<MyExpression> actualArgs, MyExpression expression,
			MyFunction function) {
		if (expression instanceof ParameterExpression)
			return true;

		return (expression.getExpressionResult(program, actualArgs, function) != null
				&& !(expression.getExpressionResult(program, actualArgs, function) instanceof Entity));
	}
	
}
