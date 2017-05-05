package asteroids.program;

class PlanetoidEntity extends EntityExpression {

	protected PlanetoidEntity() throws IllegalArgumentException {
		//
	}

	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		return getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("Planetoid"));
	}
	
}
