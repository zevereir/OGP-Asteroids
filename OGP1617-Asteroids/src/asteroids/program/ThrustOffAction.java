package asteroids.program;

class ThrustOffAction extends ActionStatement {
	
	public ThrustOffAction() {
		//
	}

	public void evaluate() {
		this.getStatementShip().setThrusterActive(false);
	}
	
}
