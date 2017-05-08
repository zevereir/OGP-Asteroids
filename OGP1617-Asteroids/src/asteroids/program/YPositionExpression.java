package asteroids.program;

import java.util.List;

import asteroids.model.Entity;

class YPositionExpression extends OnEntityExpression {
	protected YPositionExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs) {
		setExpressionProgram(program);
		
		return ((Entity)getOperand().getExpressionResult(program, null)).getEntityPositionY();
	}
}
