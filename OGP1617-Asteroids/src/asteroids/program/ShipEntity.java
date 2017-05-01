package asteroids.program;



class ShipEntity extends EntityExpression {

	protected ShipEntity() throws IllegalArgumentException {
		setOperand(getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("Ship")));
	}
}
