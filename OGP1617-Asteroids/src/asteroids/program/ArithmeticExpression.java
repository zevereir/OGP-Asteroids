package asteroids.program;

abstract class ArithmeticExpression extends MyExpression {

	protected abstract int getNbOperands();
	
	public boolean canHaveAsNbOperands(int nbOperands) {
		return nbOperands > 0;
	}
	
	
}
