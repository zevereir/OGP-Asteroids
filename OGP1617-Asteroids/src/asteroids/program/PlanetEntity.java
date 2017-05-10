package asteroids.program;

import java.util.List;

class PlanetEntity extends EntityExpression {

	protected PlanetEntity() throws IllegalArgumentException {

//		setOperand(getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("MinorPlanet")));
	}
	
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs,MyFunction function) {
		setExpressionProgram(program);
		return getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("MinorPlanet"));
	}
}
