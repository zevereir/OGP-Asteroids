package asteroids.program;

import java.util.List;

import asteroids.model.Ship;

class ShipEntity extends EntityExpression {

	protected ShipEntity() throws IllegalArgumentException {
		
	}
	
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs) {
		setExpressionProgram(program);
		
		return (Ship)getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("Ship"));
	}
}
