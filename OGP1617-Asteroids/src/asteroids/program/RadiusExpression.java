package asteroids.program;

import asteroids.model.Entity;

class RadiusExpression extends OnEntityExpression {
	protected RadiusExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult(Program program) {
		return ((Entity)getOperand().getExpressionResult(program)).getEntityRadius();
	}

}
