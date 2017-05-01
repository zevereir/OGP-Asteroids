package asteroids.program;

class PlanetEntity extends EntityExpression {

	protected PlanetEntity(EntityExpression operand) throws IllegalArgumentException {
		super(operand);
		
	}

	@Override
	protected Object getExpressionResult() {
		return getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("MinorPlanet"));
	}
}
