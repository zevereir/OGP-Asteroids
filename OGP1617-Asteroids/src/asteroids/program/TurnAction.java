package asteroids.program;

import asteroids.part3.programs.SourceLocation;
import asteroids.program.ActionStatement;

class TurnAction extends ActionStatement {
	
	public TurnAction(MyExpression angle,SourceLocation location) {	
		setAngle(angle);
		setSourceLocation(location);
		
	}

	private MyExpression getAngle() {
		return angle;
	}
	
	private void setAngle(MyExpression angle) {
		this.angle = angle;
	}
	
	public void execute(Program program) {
		try {
			this.getStatementShip().turn((double)getAngle().getExpressionResult(program, null,null));
		} catch (AssertionError error) {
			throw new IllegalArgumentException();
		}
	}
	
	private MyExpression angle;

}
