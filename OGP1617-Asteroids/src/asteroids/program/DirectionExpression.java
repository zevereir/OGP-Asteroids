package asteroids.program;

import asteroids.model.Entity;

class DirectionExpression extends OnEntityExpression {
	protected DirectionExpression(Entity operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult() {
		return getExpressionShip().getEntityOrientation();
	}

}
