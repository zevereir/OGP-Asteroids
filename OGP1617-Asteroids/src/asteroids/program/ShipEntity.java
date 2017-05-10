package asteroids.program;

import java.util.List;

import asteroids.model.Ship;

class ShipEntity extends EntityExpression {

	/// CONSTRUCTOR ///

	protected ShipEntity() throws IllegalArgumentException {
		//
	}

	
	/// GETTERS ///
	
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		return (Ship) getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("Ship"));
	}
}
