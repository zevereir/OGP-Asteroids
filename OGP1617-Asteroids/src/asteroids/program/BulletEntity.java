package asteroids.program;

import java.util.Random;
import java.util.Set;

import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Ship;

class BulletEntity extends EntityExpression {

	protected BulletEntity() throws IllegalArgumentException {

	}

	private boolean isFiredFromShip(Bullet bullet) {
		return (getExpressionShip() == bullet.getBulletSource());
	}

	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);

		Ship source = getExpressionShip();
		Set<? extends Object> bullets = source.getEntityWorld().getWorldSpecificEntities("Bullet");
		
		// Filter out all the bullets that do not belong to the ship
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

}
