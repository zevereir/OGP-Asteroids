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
	
	
	/// LOCAL CLASS ///
	
	class BinaryArithmeticExpression implements BinaryOperandSolver, ArithmeticExpression{
		public Object solveRightOperand(Program program, List<MyExpression> actualArgs, MyFunction function) {
			Double[] parameterArray = getExpressionParameter(actualArgs, function);
			Double rightParameter = parameterArray[1];
			
			if (rightParameter != null)
				return rightParameter;
			else {
				if (canHaveAsArithmeticOperand(program, actualArgs, getRightOperand(), function))
					return (double) getRightOperandResult(program, actualArgs, function);
				else
					throw new IllegalArgumentException();
			}
		}
		
		public Object solveLeftOperand(Program program, List<MyExpression> actualArgs, MyFunction function) {
			Double[] parameterArray = getExpressionParameter(actualArgs, function);
			Double leftParameter = parameterArray[0];
				
			if (leftParameter != null)
				return leftParameter;
			else {
				if (canHaveAsArithmeticOperand(program, actualArgs, getLeftOperand(), function))
					return (double) getLeftOperandResult(program, actualArgs, function);
				else
					throw new IllegalArgumentException();
			}
		}
	}
	
	
	/// LOCAL INTERFACE ///
	
	interface BinaryOperandSolver{
		public Object solveLeftOperand(Program program, List<MyExpression> actualArgs, MyFunction function);
		public Object solveRightOperand(Program program, List<MyExpression> actualArgs, MyFunction function);
	}
}
	
	

