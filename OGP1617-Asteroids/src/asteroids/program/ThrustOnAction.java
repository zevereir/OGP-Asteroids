package asteroids.program;

class ThrustOnAction extends ActionStatement {
	
	public ThrustOnAction() {
		//
	}

	public void evaluate() {
		this.getStatementShip().setThrusterActive(true);
	}

	
}
