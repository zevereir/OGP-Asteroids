package asteroids.program;

import asteroids.part3.programs.SourceLocation;

class ThrustOffAction extends ActionStatement {
	
	public ThrustOffAction(SourceLocation location) {
		setSourceLocation(location);

	}
	@Override
	public void execute(Program program) {
		this.getStatementShip().setThrusterActive(false);
	}
	
}
