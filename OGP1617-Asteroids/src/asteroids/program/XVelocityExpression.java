package asteroids.program;

import asteroids.model.Entity;

class XVelocityExpression extends OnEntityExpression {
	protected XVelocityExpression(Entity operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult() {
		return getOperand().getEntityVelocityX();
	}

}
