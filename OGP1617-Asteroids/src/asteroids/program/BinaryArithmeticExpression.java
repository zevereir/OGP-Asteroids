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
		if (canHaveAsArithmeticOperand(getExpressionProgram(), null, left_operand))
			return left_operand;
		else
			throw new IllegalArgumentException();
	}
	
	protected MyExpression getRightOperand(){
		if (canHaveAsArithmeticOperand(getExpressionProgram(), null, right_operand))
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
	
//	protected MyExpression[] getExpressionParameter(List<MyExpression> actualArgs){
//		
//		MyExpression expressionLeftParameter = null;
//		MyExpression expressionRightParameter = null;
//		
//		if (getLeftOperand() instanceof ParameterExpression) {
//			expressionLeftParameter = (actualArgs.get(((ParameterExpression)getLeftOperand()).getParameterNumber()-1));
//		}
//		if (getRightOperand() instanceof ParameterExpression) {
//			expressionRightParameter = (ParameterExpression) (actualArgs.get(((ParameterExpression)getRightOperand()).getParameterNumber()-1));
//		}		
//		System.out.println("BinaryArithmeticExpression: leftOperand: "+getLeftOperand().getExpressionResult(getExpressionProgram()));
//		System.out.println("BinaryArithmeticExpression: rightOperand: "+getRightOperand().getExpressionResult(getExpressionProgram()));
//
//		System.out.println("BinaryArithmeticExpression: expressionLeftParameter"+expressionLeftParameter);
//		System.out.println("BinaryArithmeticExpression: expressionRightParameter"+expressionRightParameter);
//		
//		MyExpression[] parameterArray = {expressionLeftParameter, expressionRightParameter};
//		
//		return parameterArray;
//	}
	
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
