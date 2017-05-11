package asteroids.program;

import java.util.List;
import java.util.Random;
import java.util.Set;

import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Ship;

class BulletEntity extends EntityExpression {

	/// CONSTRUCTOR ///
	
	protected BulletEntity() throws IllegalArgumentException {
		//
	}
	
	
	/// GETTERS ///

	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		Set<? extends Entity> bullets = getWorldEntity("Bullet");

		bullets.removeIf(bullet -> !isFiredFromShip((Bullet) bullet));

		int sizeSet = bullets.size();

		if (sizeSet != 0) {
			int randomNumber = new Random().nextInt(sizeSet);
			int i = 0;

			for (Object bullet : bullets) {
				if (i == randomNumber) {
					return bullet;
				}
				i++;
			}
		}

		return null;
	}

	
	/// CHECKERS ///
	
	private boolean isFiredFromShip(Bullet bullet) {
		return (getExpressionShip() == bullet.getBulletSource());
	}

}
