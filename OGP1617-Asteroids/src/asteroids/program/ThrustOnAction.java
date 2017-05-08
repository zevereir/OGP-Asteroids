package asteroids.program;

import asteroids.part3.programs.SourceLocation;

class ThrustOnAction extends ActionStatement {
	
	public ThrustOnAction(SourceLocation location) {
		setSourceLocation(location);
		
	}
	@Override
	public void execute(Program program) {
		this.getStatementShip().setThrusterActive(true);
	}

	
}
