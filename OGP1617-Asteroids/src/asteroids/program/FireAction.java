package asteroids.program;

class FireAction extends ActionStatement {

	public FireAction() {
		//
	}
	
	public void evaluate() {
		this.getStatementShip().fireBullet();
	}
}
