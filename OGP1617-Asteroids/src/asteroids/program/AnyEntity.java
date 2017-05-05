package asteroids.program;

import java.util.Random;
import java.util.Set;

import asteroids.model.Entity;

class AnyEntity extends EntityExpression {

	protected AnyEntity() throws IllegalArgumentException {

	}

	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);

		Set<? extends Object> entities = getExpressionShip().getEntityWorld().getWorldEntities();
		entities.remove(getExpressionShip());

		int sizeSet = entities.size();
		int randomNumber = new Random().nextInt(sizeSet);
		int i = 0;

		if (sizeSet != 0) {
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
