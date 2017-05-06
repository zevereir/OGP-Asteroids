package asteroids.program;

import asteroids.model.Entity;

class XPositionExpression extends OnEntityExpression {
	protected XPositionExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		return ((Entity)getOperand().getExpressionResult(program)).getEntityPositionX();
	}

}
