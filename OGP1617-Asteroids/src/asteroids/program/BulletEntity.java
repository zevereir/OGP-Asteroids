package asteroids.program;

import java.util.Set;

import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Ship;

class BulletEntity extends EntityExpression {

	protected BulletEntity() throws IllegalArgumentException {
//		Ship source = getExpressionShip();
//		Set<? extends Object> entities = source.getEntityWorld().getWorldEntities();
//		@SuppressWarnings("unchecked")
//		Set<Bullet> bulletsFromSource = (Set<Bullet>) entities.stream().filter(e -> e instanceof Bullet && (isFiredFromShip((Bullet)e)));
//		Object operand = bulletsFromSource.stream().skip((int)(bulletsFromSource.size() * Math.random())).findFirst();
//		setOperand((Entity)operand);
	}
	
	private boolean isFiredFromShip(Bullet bullet){
		return (getExpressionShip() == bullet.getBulletSource());
	}
	
	
	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		
		Ship source = getExpressionShip();
		Set<? extends Object> entities = source.getEntityWorld().getWorldEntities();
		
		@SuppressWarnings("unchecked")
		Set<Bullet> bulletsFromSource = (Set<Bullet>) entities.stream().filter(e -> e instanceof Bullet && (isFiredFromShip((Bullet)e)));
		
		Object operand = bulletsFromSource.stream().skip((int)(bulletsFromSource.size() * Math.random())).findFirst();

		return operand;
	}

	
	
	


}
