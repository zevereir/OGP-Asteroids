package asteroids.program;

class NullEntity extends EntityExpression {

	
	
	protected NullEntity() throws IllegalArgumentException {
		
	}

	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		return null;
	}
	
}
