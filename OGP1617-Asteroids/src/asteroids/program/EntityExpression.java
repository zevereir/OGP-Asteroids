package asteroids.program;

import java.util.Set;


import asteroids.model.Entity;
import asteroids.model.Ship;


abstract class EntityExpression extends MyExpression {


	/// GETTERS ///

	public String getOperand() {
		return operand;
	}
	
	@Override
	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		return getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities(getOperand()));
	}
	
	protected Entity getClosestEntity(Set<? extends Object> entities){
		Ship ship = getExpressionShip();
		Entity result = null;
		if (!entities.isEmpty()){
			double min_distance = Double.POSITIVE_INFINITY;
			for (Object entity: entities){
			if (((Entity)entity != ship)&& ship.getDistanceBetween((Entity)entity) < min_distance )
				min_distance = ship.getDistanceBetween((Entity)entity); 
				result = (Entity)entity;
			}			
		}
		
		return result;
	}
	

	/// SETTERS ///

	protected void setOperand(String operand) {
		this.operand = operand;
	}

	/// PROPERTIES ///

	private String operand = null;


}

