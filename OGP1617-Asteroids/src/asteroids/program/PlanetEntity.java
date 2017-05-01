package asteroids.program;

class PlanetEntity extends EntityExpression {

	protected PlanetEntity() throws IllegalArgumentException {
		setOperand(getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("MinorPlanet")));
		
	}
}
