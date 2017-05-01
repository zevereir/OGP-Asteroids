package asteroids.program;

import asteroids.model.Entity;

class RadiusExpression extends OnEntityExpression {
	protected RadiusExpression(Entity operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult() {
		return getOperand().getEntityRadius();
	}

}
