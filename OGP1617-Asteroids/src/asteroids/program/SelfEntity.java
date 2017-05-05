package asteroids.program;

class SelfEntity extends EntityExpression {

	protected SelfEntity() throws IllegalArgumentException {
		
	}

	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		return getExpressionShip();
	}
	
}
