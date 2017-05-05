package asteroids.program;

import java.util.List;

class EqualsToExpression extends MyExpression {

	/// CONSTRUCTOR ///

	protected EqualsToExpression(MyExpression leftExpression, MyExpression rightExpression) throws IllegalArgumentException{
		setLeftOperand(leftExpression);
		setRightOperand(rightExpression);			
	}
	
	@Override
	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		return getLeftOperand().equals(getRightOperand());
	}

	
	/// GETTERS ///
	
	protected MyExpression getLeftOperand(){
		return left_operand;
	}

	protected MyExpression getRightOperand(){
		return right_operand;
	}

	
	/// SETTERS ///
	
	protected void setLeftOperand(MyExpression expression) throws IllegalArgumentException{
		if (canHaveAsOperand(expression))
			left_operand = expression;
		else
			throw new IllegalArgumentException();
	}

	protected void setRightOperand(MyExpression expression) throws IllegalArgumentException{
		if (canHaveAsOperand(expression))
			right_operand = expression;
		else
			throw new IllegalArgumentException();
	}

	
	/// CHECKERS ///
	
	protected boolean canHaveAsNbOperands(double number){
		return number == 2;
	}
	
	protected boolean canHaveAsOperand(MyExpression operand) throws IllegalArgumentException {
		return false;
	}

	protected void assignExpressionToParameter(List<MyExpression> actualArgs){
		if (getLeftOperand() instanceof ParameterExpression)
			setLeftOperand(actualArgs.get(((ParameterExpression)getLeftOperand()).getParameterNumber()-1));
			
		if (getRightOperand() instanceof ParameterExpression)
			setRightOperand(actualArgs.get(((ParameterExpression)getRightOperand()).getParameterNumber()-1));
		
	}

	/// PROPERTIES ///
	
	protected MyExpression left_operand = null;
	protected MyExpression right_operand = null;

}
