package asteroids.program;

class PlanetoidEntity extends EntityExpression {

	protected PlanetoidEntity(EntityExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	
	
	@Override
	protected Object getExpressionResult() {
		return getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("Planetoid"));
	}
}
