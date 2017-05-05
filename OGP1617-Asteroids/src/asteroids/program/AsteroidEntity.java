package asteroids.program;

class AsteroidEntity extends EntityExpression {

	protected AsteroidEntity() throws IllegalArgumentException {
	
	}

	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		return getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("Asteroid"));
	}
	
}
