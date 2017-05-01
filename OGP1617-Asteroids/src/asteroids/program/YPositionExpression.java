package asteroids.program;

import asteroids.model.Entity;

class YPositionExpression extends OnEntityExpression {
	protected YPositionExpression(Entity operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult() {
		return getOperand().getEntityPositionY();
	}
}
