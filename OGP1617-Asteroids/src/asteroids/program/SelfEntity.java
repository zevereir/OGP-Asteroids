package asteroids.program;

class SelfEntity extends EntityExpression {

	protected SelfEntity() throws IllegalArgumentException {
		setOperand(getExpressionShip());
	}

	

}
