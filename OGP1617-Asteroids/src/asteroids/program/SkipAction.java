package asteroids.program;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

class SkipAction extends ActionStatement {
	public SkipAction(SourceLocation location){
		setSourceLocation(location);
	}

	@Override
	protected void execute(Program program) {
		//nothing has to be done//	YOU WRONG MOTHERFUCKER
	}

}
