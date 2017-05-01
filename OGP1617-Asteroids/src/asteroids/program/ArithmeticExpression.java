package asteroids.program;

abstract class ArithmeticExpression extends MyExpression {

	protected abstract int getNbOperands();
	
	public boolean canHaveAsNbOperands(int nbOperands) {
		return nbOperands > 0;
	}
	
	public boolean canHaveAsArithmeticOperand(ArithmeticExpression expression) {
		return (expression != null) && (!expression.hasAsSubExpression(this));
	}
	
	public abstract boolean hasAsSubExpression(ArithmeticExpression expression);
	
	
}
