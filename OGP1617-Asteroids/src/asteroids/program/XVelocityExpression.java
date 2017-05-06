package asteroids.program;

import asteroids.model.Entity;

class XVelocityExpression extends OnEntityExpression {
	protected XVelocityExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		
		return ((Entity)getOperand().getExpressionResult(program)).getEntityVelocityX();
	}

}
