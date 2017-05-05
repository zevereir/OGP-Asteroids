package asteroids.program;

class ThrustOffAction extends ActionStatement {
	
	public ThrustOffAction() {
		//
	}
	@Override
	public void execute(Program program) {
		this.getStatementShip().setThrusterActive(false);
	}
	
}
