package asteroids.program;

import java.util.List;

import asteroids.model.Entity;

abstract class ArithmeticExpression extends MyExpression {

	protected abstract int getNbOperands();
	
	public boolean canHaveAsNbOperands(int nbOperands) {
		return nbOperands > 0;
	}
	
	public boolean canHaveAsArithmeticOperand(Program program, List<MyExpression> actualArgs, MyExpression expression,MyFunction function){
		if (expression instanceof ParameterExpression)
			return true;
		
		return (expression.getExpressionResult(program, actualArgs,function) != null && 
				!(expression.getExpressionResult(program, actualArgs,function) instanceof Entity));			
	}
	
}
