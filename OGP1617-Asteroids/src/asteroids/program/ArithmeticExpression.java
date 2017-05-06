package asteroids.program;

import asteroids.model.Entity;

abstract class ArithmeticExpression extends MyExpression {

	protected abstract int getNbOperands();
	
	public boolean canHaveAsNbOperands(int nbOperands) {
		return nbOperands > 0;
	}
	
	public boolean canHaveAsArithmeticOperand(Program program, MyExpression expression){
		return (expression.getExpressionResult(program) != null && !(expression.getExpressionResult(program) instanceof Entity));			
	}
	
}
