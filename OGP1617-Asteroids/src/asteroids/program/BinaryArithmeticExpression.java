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
		return left_operand;
	}
	
	protected MyExpression getRightOperand(){
		return right_operand;
	}
	
	
	/// SETTERS ///
	
	protected void setLeftOperand(MyExpression expression) throws IllegalArgumentException{
		if (canHaveAsArithmeticOperand(expression))
			left_operand = expression;
		else
			throw new IllegalArgumentException();
	}
	
	protected void setRightOperand(MyExpression expression) throws IllegalArgumentException{
		if (canHaveAsArithmeticOperand(expression))
			right_operand = expression;
		else
			throw new IllegalArgumentException();
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
