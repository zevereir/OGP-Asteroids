package asteroids.program;



class ShipEntity extends EntityExpression {

	protected ShipEntity() throws IllegalArgumentException {
		
	}
	
	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		return getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("Ship"));
	}
}
