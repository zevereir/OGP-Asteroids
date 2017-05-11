package asteroids.program;

import java.util.List;

import asteroids.model.Entity;

class RadiusExpression extends OnEntityExpression {
	
	/// CONSTRUCTOR ///
	
	protected RadiusExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}
	
	
	/// GETTERS ///

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		return ((Entity) getOperandResult(program)).getEntityRadius();
	}

}
