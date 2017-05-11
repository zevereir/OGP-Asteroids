package asteroids.program;

import java.util.List;

public abstract class BinaryExpression extends MyExpression {

	/// CONSTRUCTOR ///

	protected BinaryExpression(MyExpression leftExpression, MyExpression rightExpression)
			throws IllegalArgumentException {
		setLeftOperand(leftExpression);
		setRightOperand(rightExpression);
	}


	/// BASIC PROPERTIES ///

	protected MyExpression left_operand = null;
	protected MyExpression right_operand = null;

	
	/// GETTERS ///

	protected MyExpression getLeftOperand() {
		return left_operand;
	}
	
	protected Object getLeftOperandResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		return getLeftOperand().getExpressionResult(program, actualArgs, function);
	}

	protected int getNbOperands() {
		return 2;
	}

	protected MyExpression getRightOperand() {
		return right_operand;
	}
	
	protected Object getRightOperandResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		return getRightOperand().getExpressionResult(program, actualArgs, function);
	}
	

	/// SETTERS ///

	protected void setLeftOperand(MyExpression expression) {
		left_operand = expression;
	}

	protected void setRightOperand(MyExpression expression) {
		right_operand = expression;
	}

	
	/// CHECKERS ///

	protected boolean canHaveAsNbOperands(double number) {
		return number == 2;
	}
	
	/// LOCAL INTERFACE ///
	interface BinaryParameterSolver{
		public Object solveLeftParameter(Program program, List<MyExpression> actualArgs, MyFunction function);
		public Object solveRightParameter(Program program, List<MyExpression> actualArgs, MyFunction function);
		
	
	}
	/// LOCAL CLASS ///
	class BinaryArithmeticExpression implements BinaryParameterSolver, ArithmeticExpression{

		@Override
		public Object solveRightParameter(Program program, List<MyExpression> actualArgs, MyFunction function) {
			Double[] parameterArray = getExpressionParameter(actualArgs, function);
			Double rightParameter = parameterArray[1];
			Double rightOperand = null;	
			if (rightParameter != null)
				rightOperand = rightParameter;
			else {
				if (canHaveAsArithmeticOperand(program, actualArgs, getRightOperand(), function))
					rightOperand = (double) getRightOperandResult(program, actualArgs, function);
				else
					throw new IllegalArgumentException();
			}
			return rightOperand;
		}
		

		@Override
		public Object solveLeftParameter(Program program, List<MyExpression> actualArgs, MyFunction function) {
			Double[] parameterArray = getExpressionParameter(actualArgs, function);
			Double leftParameter = parameterArray[0];
			Double leftOperand = null;	
			if (leftParameter != null)
				leftOperand = leftParameter;
			else {
				if (canHaveAsArithmeticOperand(program, actualArgs, getLeftOperand(), function))
					leftOperand = (double) getLeftOperandResult(program, actualArgs, function);
				else
					throw new IllegalArgumentException();
			}
			return leftOperand;
		}
		

		
		
	}
}
	
	

