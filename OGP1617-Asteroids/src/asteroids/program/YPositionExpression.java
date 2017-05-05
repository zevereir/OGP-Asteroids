package asteroids.program;

import asteroids.model.Entity;

class YPositionExpression extends OnEntityExpression {
	protected YPositionExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		return ((Entity)getOperand().getExpressionResult(program)).getEntityPositionY();
	}
}
