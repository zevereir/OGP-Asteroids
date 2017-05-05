package asteroids.program;

class FireAction extends ActionStatement {

	public FireAction() {
		//
	}
	@Override
	public void execute(Program program) {
		this.getStatementShip().fireBullet();
		setStatementProgram(program);
	}
}
