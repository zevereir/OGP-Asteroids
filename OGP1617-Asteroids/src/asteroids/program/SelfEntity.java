package asteroids.program;

class SelfEntity extends EntityExpression {

	
	
	@Override
	protected Object getExpressionResult() {
		return getExpressionShip();
	}

}
