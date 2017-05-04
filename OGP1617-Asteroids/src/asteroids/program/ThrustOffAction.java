package asteroids.program;

class ThrustOffAction extends ActionStatement {
	
	public ThrustOffAction() {
		//
	}
	@Override
	public void execute() {
		this.getStatementShip().setThrusterActive(false);
	}
	
}
