package asteroids.program;

abstract class BinaryArithmeticExpression extends ArithmeticExpression {

	
	protected BinaryArithmeticExpression(ArithmeticExpression leftExpression, ArithmeticExpression rightExpression) throws IllegalArgumentException{
			setLeftOperand(leftExpression);
			setRightOperand(rightExpression);			
	}
	
	//GETTERS//
	protected int getNbOperands(){
		return 2;
	}
	protected ArithmeticExpression getLeftOperand(){
		return left_operand;
	}
	
	protected ArithmeticExpression getRightOperand(){
		return right_operand;
	}
	
	//SETTERS//
	protected void setLeftOperand(ArithmeticExpression expression) throws IllegalArgumentException{
		if (canHaveAsArithmeticOperand(expression))
			left_operand = expression;
		else
			throw new IllegalArgumentException();
	}
	protected void setRightOperand(ArithmeticExpression expression) throws IllegalArgumentException{
		if (canHaveAsArithmeticOperand(expression))
			right_operand = expression;
		else
			throw new IllegalArgumentException();
	}
	
	//CHECKERS//
	protected boolean canHaveAsNbOperands(double number){
		return number == 2;
	}
	
	public boolean hasAsSubExpression(ArithmeticExpression expression){
		if (this == expression)
			return true;
		else
			return (getLeftOperand().hasAsSubExpression(expression)||getRightOperand().hasAsSubExpression(expression));				
	}
	

	//PROPERTIES//
	protected ArithmeticExpression left_operand = null;
	protected ArithmeticExpression right_operand = null;

}
