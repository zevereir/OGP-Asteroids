package asteroids.program;

import java.util.List;

import javax.management.RuntimeErrorException;

import asteroids.model.Ship;

public abstract class MyExpression {

	/// GETTERS ///

	protected Double[] getExpressionParameter(List<MyExpression> actualArgs, MyFunction function) {
		Double expressionLeftParameter = null;
		Double expressionRightParameter = null;

		try {
			// UNARY
			if (this instanceof UnaryExpression) {
				if (((UnaryExpression) this).getOperand() instanceof ParameterExpression)
					expressionLeftParameter = (Double) (actualArgs.get(((ParameterExpression) 
							((UnaryExpression) this).getOperand()).getParameterNumber() - 1)).
							getExpressionResult(getExpressionProgram(), actualArgs, function);
			}

			// BINARY
			if (this instanceof BinaryExpression) {
				if (((BinaryExpression) this).getLeftOperand() instanceof ParameterExpression) {
					expressionLeftParameter = (Double) (actualArgs.get(((ParameterExpression) 
							((BinaryExpression) this).getLeftOperand()).getParameterNumber() - 1))
							.getExpressionResult(getExpressionProgram(), actualArgs, function);
				}
				if (((BinaryExpression) this).getRightOperand() instanceof ParameterExpression) {
					expressionRightParameter = (Double) (actualArgs.get(((ParameterExpression) 
							((BinaryExpression) this).getRightOperand()).getParameterNumber() - 1))
							.getExpressionResult(getExpressionProgram(), actualArgs, function);
				}
			}

			Double[] parameterArray = { expressionLeftParameter, expressionRightParameter };

			return parameterArray;
		} catch (IndexOutOfBoundsException indexOutOfBoundsException) {
			throw new RuntimeErrorException(new IllegalAccessError());
		}
	}

	protected Program getExpressionProgram() {
		return this.program;
	}

	protected abstract Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function);

	protected Ship getExpressionShip() {
		return this.getExpressionProgram().getProgramShip();
	}

	
	/// SETTERS ///

	protected void setExpressionProgram(Program program) {
		this.program = program;
	}

	
	/// RELATIONS WITH OTHER CLASSES ///

	private Program program = null;

}
