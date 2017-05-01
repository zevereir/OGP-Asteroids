package asteroids.program;

import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.model.World;

abstract class EntityExpression extends MyExpression {

	/// CONSTRUCTOR ///

	protected EntityExpression(EntityExpression operand) throws IllegalArgumentException {
		if (! canHaveAsEntityExpressionOperand(operand))
			throw new IllegalArgumentException();

		setOperand(operand);
	}


	/// GETTERS ///

	public EntityExpression getOperand() {
		return operand;
	}
	
	protected Entity getClosestEntity(Set<? extends Object> entities){
		Ship ship = getExpressionShip();
		Entity result = null;
		if (!entities.isEmpty()){
			double min_distance = Double.POSITIVE_INFINITY;
			for (Object entity: entities){
			if (ship.getDistanceBetween((Entity)entity) < min_distance)
				min_distance = ship.getDistanceBetween((Entity)entity); 
				result = (Entity)entity;
				}			
			}
		return result;
	}
	

	/// SETTERS ///

	protected void setOperand(EntityExpression operand) {
		this.operand = operand;
	}
	


	/// CHECKERS ///

	public boolean canHaveAsEntityExpressionOperand(EntityExpression expression){
		return true;			
	}


	/// PROPERTIES ///

	private EntityExpression operand;


}

