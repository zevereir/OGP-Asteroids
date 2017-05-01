package asteroids.program;

import asteroids.model.Entity;

class XPositionExpression extends OnEntityExpression {
	protected XPositionExpression(Entity operand) throws IllegalArgumentException {
		super(operand);
		
	}

	@Override
	protected Object getExpressionResult() {
		return getOperand().getEntityPositionX();
	}

}
