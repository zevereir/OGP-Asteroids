package asteroids.program;

class AsteroidEntity extends EntityExpression {

	protected AsteroidEntity() throws IllegalArgumentException {
		setOperand(getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("Asteroid")));
	}
	
}
