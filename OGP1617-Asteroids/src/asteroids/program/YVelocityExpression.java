package asteroids.program;

import asteroids.model.Entity;

class YVelocityExpression extends OnEntityExpression {
	protected YVelocityExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		
		return ((Entity)getOperand().getExpressionResult(program)).getEntityVelocityY();
	}

}
