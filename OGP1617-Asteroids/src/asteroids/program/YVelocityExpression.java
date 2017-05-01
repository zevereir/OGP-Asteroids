package asteroids.program;

import asteroids.model.Entity;

class YVelocityExpression extends OnEntityExpression {
	protected YVelocityExpression(Entity operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult() {
		return getOperand().getEntityVelocityY();
	}

}
