package asteroids.program;

import java.util.List;

import asteroids.model.Entity;

class XVelocityExpression extends OnEntityExpression {
	
	/// CONSTRUCTOR ///
	
	protected XVelocityExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	
	/// GETTERS ///
	
	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		return getOperandResult(program).getEntityVelocityX();
	}

}
