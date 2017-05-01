package asteroids.program;

class PlanetoidEntity extends EntityExpression {

	protected PlanetoidEntity() throws IllegalArgumentException {
		setOperand(getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("Planetoid")));
	}

}
