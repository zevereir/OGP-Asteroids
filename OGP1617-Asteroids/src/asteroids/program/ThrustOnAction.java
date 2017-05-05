package asteroids.program;

class ThrustOnAction extends ActionStatement {
	
	public ThrustOnAction() {
		//
	}
	@Override
	public void execute(Program program) {
		this.getStatementShip().setThrusterActive(true);
	}

	
}
