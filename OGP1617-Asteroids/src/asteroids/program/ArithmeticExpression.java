package asteroids.program;

import java.util.List;

import asteroids.model.Entity;

abstract class ArithmeticExpression extends MyExpression {
	
	/// GETTERS ///
	
	protected abstract int getNbOperands();

	
	/// CHECKERS ///

	public boolean canHaveAsArithmeticOperand(Program program, List<MyExpression> actualArgs, MyExpression expression,
			MyFunction function) {
		if (expression instanceof ParameterExpression)
			return true;

		return (expression.getExpressionResult(program, actualArgs, function) != null
				&& !(expression.getExpressionResult(program, actualArgs, function) instanceof Entity));
	}
	
	public boolean canHaveAsNbOperands(int nbOperands) {
		return nbOperands > 0;
	}

}
