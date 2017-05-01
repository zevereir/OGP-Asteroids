package asteroids.program;



class ShipEntity extends EntityExpression {

	protected ShipEntity(EntityExpression operand) throws IllegalArgumentException {
		super(operand);
	}
	
	@Override
	protected Object getExpressionResult() {
		return getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("Ship"));
	}


}
