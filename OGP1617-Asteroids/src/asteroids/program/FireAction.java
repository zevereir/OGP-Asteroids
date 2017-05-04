package asteroids.program;

class FireAction extends ActionStatement {

	public FireAction() {
		//
	}
	@Override
	public void execute() {
		this.getStatementShip().fireBullet();
	}
}
