package asteroids.program;

import asteroids.part3.programs.SourceLocation;

class ThrustOnAction extends ActionStatement {

	/// CONSTRUCTOR ///
	
	public ThrustOnAction(SourceLocation location) {
		setSourceLocation(location);
	}

	
	/// EXECUTE ///
	
	@Override
	public void execute(Program program) {
		this.getStatementShip().setThrusterActive(true);
	}

}
