package asteroids.program;

import asteroids.model.Ship;

class ShipEntity extends EntityExpression {

	protected ShipEntity() throws IllegalArgumentException {
		
	}
	
	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		return (Ship)getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("Ship"));
	}
}
