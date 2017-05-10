package asteroids.program;

import java.util.List;

import asteroids.model.Entity;

class RadiusExpression extends OnEntityExpression {
	protected RadiusExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs,MyFunction function) {
		setExpressionProgram(program);
		
		return ((Entity)getOperand().getExpressionResult(program, null,null)).getEntityRadius();
	}

}
