package asteroids.program;

class AsteroidEntity extends EntityExpression {

	protected AsteroidEntity(EntityExpression operand) throws IllegalArgumentException {
		super(operand);
	}
	
	@Override
	protected Object getExpressionResult() {
		return getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("Asteroid"));
	}

}
