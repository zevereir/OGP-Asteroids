package asteroids.program;

import asteroids.part3.programs.SourceLocation;

class SkipAction extends ActionStatement {

	/// CONSTRUCTOR ///
	
	public SkipAction(SourceLocation location) {
		setSourceLocation(location);
	}

	
	/// EXECUTE ///
	
	@Override
	protected void execute(Program program) {
		//
	}

}
