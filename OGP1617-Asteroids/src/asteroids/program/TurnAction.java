package asteroids.program;

import asteroids.program.ActionStatement;

class TurnAction extends ActionStatement {
	
	public TurnAction(MyExpression angle) {	
		setAngle(angle);
	}

	private MyExpression getAngle() {
		return angle;
	}
	
	private void setAngle(MyExpression angle) {
		this.angle = angle;
	}
	
	public void execute(Program program) {
		try {
			this.getStatementShip().turn((double)getAngle().getExpressionResult(program));
		} catch (AssertionError error) {
			throw new IllegalArgumentException();
		}
	}
	
	private MyExpression angle;

}
