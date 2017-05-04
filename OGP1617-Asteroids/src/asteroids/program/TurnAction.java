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
	
	// -------> BEKIJKEN <--------- //
	public void evaluate() {
		this.getStatementShip().turn(getAngle());
	}
	
	private MyExpression angle;

}
