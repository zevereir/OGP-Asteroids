package asteroids.program;

import java.util.List;

class SelfEntity extends EntityExpression {

	protected SelfEntity() throws IllegalArgumentException {
		
	}

	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs,MyFunction function) {
		setExpressionProgram(program);
		return getExpressionShip();
	}
	
}
