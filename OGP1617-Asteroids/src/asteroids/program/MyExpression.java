package asteroids.program;

import java.util.List;

import javax.management.RuntimeErrorException;

import asteroids.model.Ship;

public abstract class MyExpression {

	/// GETTERS ///
	
	protected MyExpression getArgument(MyExpression operand, List<MyExpression> actualArgs) {
		if (operand.equals(getOperand()))
			return (actualArgs.get(((ParameterExpression) getOperand()).getParameterNumber() - 1));
		else if (operand.equals(getLeftOperand()))
			return (actualArgs.get(((ParameterExpression) getLeftOperand()).getParameterNumber() - 1));
		else // The operand equals the getRightOperand()
			return (actualArgs.get(((ParameterExpression) getRightOperand()).getParameterNumber() - 1));
	}
	
	protected Object getArgumentExpression(MyExpression operand, List<MyExpression> actualArgs) {
		return getArgument(operand, actualArgs).getExpressionResult(getExpressionProgram(), actualArgs);
	}

	protected Double[] getExpressionParameter(List<MyExpression> actualArgs, MyFunction function) {
		Double expressionLeftParameter = null;
		Double expressionRightParameter = null;

		try {
			// UNARY
			if (this instanceof UnaryExpression) {
				if (((UnaryExpression) this).getOperand() instanceof ParameterExpression)
					expressionLeftParameter = (Double) getArgumentExpression(getOperand(), actualArgs);
			}

			// BINARY
			if (this instanceof BinaryExpression) {
				if (((BinaryExpression) this).getLeftOperand() instanceof ParameterExpression)
					expressionLeftParameter = (Double) getArgumentExpression(getLeftOperand(), actualArgs);
				
				if (((BinaryExpression) this).getRightOperand() instanceof ParameterExpression)
					expressionRightParameter = (Double) getArgumentExpression(getRightOperand(), actualArgs);
			}

			Double[] parameterArray = { expressionLeftParameter, expressionRightParameter };

			return parameterArray;
		} catch (IndexOutOfBoundsException indexOutOfBoundsException) {
			throw new RuntimeErrorException(new IllegalAccessError());
		}
	}
	
	protected MyExpression getOperand() {
		return null;
	}
	
	protected MyExpression getLeftOperand() {
		return null;
	}
	
	protected MyExpression getRightOperand() {
		return null;
	}
	
	

	protected Program getExpressionProgram() {
		return this.program;
	}

	protected abstract Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function);
	
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs) {
		return getExpressionResult(program, actualArgs, null);
	}
	
	protected Object getExpressionResult(Program program) {
		return getExpressionResult(program, null);
	}

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
