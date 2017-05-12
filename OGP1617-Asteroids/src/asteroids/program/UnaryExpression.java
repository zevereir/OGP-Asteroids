package asteroids.program;

import java.util.List;


public abstract class UnaryExpression extends MyExpression {

	/// CONSTRUCTOR ///

	protected UnaryExpression(MyExpression operand) {
		setOperand(operand);
	}

	
	/// BASIC PROPERTIES ///

	private MyExpression operand;

	
	/// GETTERS ///

	public final int getNbOperands() {
		return 1;
	}

	public MyExpression getOperand() {
		return operand;
	}
	
	protected Object getOperandResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		return getOperand().getExpressionResult(program, actualArgs, function);
	}

	
	/// SETTERS ///

	protected void setOperand(MyExpression operand) {
		this.operand = operand;
	}

	
	/// CHECKERS ///

	public final boolean canHaveAsNbOperands(int number) {
		return number == 1;
	}
	
	
	/// LOCAL CLASS ///
	
	class UnaryArithmeticExpression implements UnaryOperandSolver, ArithmeticExpression {

		@Override
		public Object solveOperand(Program program, List<MyExpression> actualArgs, MyFunction function) {
			Double[] parameterArray = getExpressionParameter(actualArgs, function);
			Double parameter = parameterArray[0];
			
			if (parameter != null)
				return parameter;
			else {
				if (canHaveAsArithmeticOperand(program, actualArgs, getOperand(), function))
					return (double) getOperandResult(program, actualArgs, function);
				else
					throw new IllegalArgumentException();
			}
		}
	}
	
	
	/// LOCAL INTERFACE ///
	
	interface UnaryOperandSolver{
		public Object solveOperand(Program program, List<MyExpression> actualArgs, MyFunction function);
	}

}
