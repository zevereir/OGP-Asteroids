package asteroids.program;

import java.util.List;

abstract class BinaryArithmeticExpression extends ArithmeticExpression {


	/// CONSTRUCTOR ///
	
	protected BinaryArithmeticExpression(MyExpression leftExpression, MyExpression rightExpression) throws IllegalArgumentException{
			setLeftOperand(leftExpression);
			setRightOperand(rightExpression);			
	}
	
	
	/// GETTERS ///
	
	protected int getNbOperands(){
		return 2;
	}
	
	protected MyExpression getLeftOperand(){
		if (canHaveAsArithmeticOperand(getExpressionProgram(), left_operand))
			return left_operand;
		else
			throw new IllegalArgumentException();
	}
	
	protected MyExpression getRightOperand(){
		if (canHaveAsArithmeticOperand(getExpressionProgram(), right_operand))
			return right_operand;
		else
			throw new IllegalArgumentException();
	}
	
	
	/// SETTERS ///
	
	protected void setLeftOperand(MyExpression expression) {
		
			left_operand = expression;
		
	}
	
	protected void setRightOperand(MyExpression expression){
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
	
//	@Override
//	public boolean hasAsSubExpression(MyExpression expression){
//		if (this == expression)
//			return true;
//		else
//			return (getLeftOperand().hasAsSubExpression(expression)||getRightOperand().hasAsSubExpression(expression));				
//	}
//	

	/// PROPERTIES ///
	
	protected MyExpression left_operand = null;
	protected MyExpression right_operand = null;

}
