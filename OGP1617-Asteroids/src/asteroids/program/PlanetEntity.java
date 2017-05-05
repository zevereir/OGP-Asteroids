package asteroids.program;

class PlanetEntity extends EntityExpression {

	protected PlanetEntity() throws IllegalArgumentException {
//		setOperand(getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("MinorPlanet")));
//		System.out.println("PlanetEntity, been here, done that, fixed shit");	
	}
	
	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		return getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("MinorPlanet"));
	}
}
