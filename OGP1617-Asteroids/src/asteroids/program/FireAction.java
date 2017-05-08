package asteroids.program;

import asteroids.part3.programs.SourceLocation;

class FireAction extends ActionStatement {

	public FireAction(SourceLocation location) {
		setSourceLocation(location);
	}
	@Override
	public void execute(Program program) {
		this.getStatementShip().fireBullet();
		setStatementProgram(program);
	}
	
}
