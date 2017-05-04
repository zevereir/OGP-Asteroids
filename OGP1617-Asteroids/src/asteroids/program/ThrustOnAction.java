package asteroids.program;

class ThrustOnAction extends ActionStatement {
	
	public ThrustOnAction() {
		//
	}
	@Override
	public void execute() {
		this.getStatementShip().setThrusterActive(true);
	}

	
}
