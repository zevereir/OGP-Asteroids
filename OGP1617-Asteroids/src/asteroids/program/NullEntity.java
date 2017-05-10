package asteroids.program;

import java.util.List;

class NullEntity extends EntityExpression {

	
	
	protected NullEntity() throws IllegalArgumentException {
		
	}

	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs,MyFunction function) {
		return null;
	}
	
}
