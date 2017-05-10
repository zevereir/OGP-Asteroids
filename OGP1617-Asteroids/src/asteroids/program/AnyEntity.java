package asteroids.program;

import java.util.List;
import java.util.Random;
import java.util.Set;

import asteroids.model.Entity;

class AnyEntity extends EntityExpression {

	protected AnyEntity() throws IllegalArgumentException {

	}

	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs,MyFunction function) {
		setExpressionProgram(program);

		Set<? extends Object> entities = getExpressionShip().getEntityWorld().getWorldEntities();
		
		int sizeSet = entities.size();

		if (sizeSet != 0) {
			int randomNumber = new Random().nextInt(sizeSet);
			int i = 0;
			
			for (Object entity : entities) {
				if (i == randomNumber) {
					return entity;
				}
				i++;
			}
		}

		return null;
	}
}
