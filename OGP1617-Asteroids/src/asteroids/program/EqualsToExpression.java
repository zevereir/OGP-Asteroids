package asteroids.program;

import java.util.List;

class EqualsToExpression extends MyExpression {

	/// CONSTRUCTOR ///

	protected EqualsToExpression(MyExpression leftExpression, MyExpression rightExpression) throws IllegalArgumentException{
		setLeftOperand(leftExpression);
		setRightOperand(rightExpression);			
	}
	
	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs) {
		setExpressionProgram(program);
		
		MyExpression leftExpression;
		MyExpression rightExpression;
		
		if (getLeftOperand() instanceof ParameterExpression) {
			leftExpression = (actualArgs.get(((ParameterExpression)getLeftOperand()).getParameterNumber()-1));
		}
		else 
			leftExpression = getLeftOperand();
			
		if (getRightOperand() instanceof ParameterExpression) {
			rightExpression = (actualArgs.get(((ParameterExpression)getRightOperand()).getParameterNumber()-1));
		}	
		else
			rightExpression = getRightOperand();
			
		return leftExpression.getExpressionResult(program, actualArgs).equals(rightExpression.getExpressionResult(program, actualArgs));
	}

	
	/// GETTERS ///
	
	protected MyExpression getLeftOperand(){
		return left_operand;
	}

	protected MyExpression getRightOperand(){
		return right_operand;
	}

	
	/// SETTERS ///
	
	protected void setLeftOperand(MyExpression expression){
			left_operand = expression;
	}

	protected void setRightOperand(MyExpression expression) {
			right_operand = expression;
	}

	
	/// CHECKERS ///
	
	protected boolean canHaveAsNbOperands(double number){
		return number == 2;
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
