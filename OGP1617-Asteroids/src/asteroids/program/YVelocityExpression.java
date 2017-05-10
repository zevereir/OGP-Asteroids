package asteroids.program;

import java.util.List;

import asteroids.model.Entity;

class YVelocityExpression extends OnEntityExpression {
	
	/// CONSTRUCTOR ///
	
	protected YVelocityExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}
	
	
	/// GETTERS ///

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		return ((Entity) getOperand().getExpressionResult(program, null, null)).getEntityVelocityY();
	}

}
