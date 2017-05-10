package asteroids.program;

import asteroids.part3.programs.SourceLocation;

class ThrustOffAction extends ActionStatement {

	/// CONSTRUUCTOR ///
	
	public ThrustOffAction(SourceLocation location) {
		setSourceLocation(location);

	}
	
	
	/// EXECUTE ///

	@Override
	public void execute(Program program) {
		this.getStatementShip().setThrusterActive(false);
	}

}
