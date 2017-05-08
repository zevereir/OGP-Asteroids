package asteroids.program;

import java.util.List;

abstract class ActionStatement extends MyStatement {

	@Override
	public void evaluate(Program program, List<MyExpression> actualArgs){
		setStatementProgram(program);
		if (getStatementProgram().getTimeLeft() >= getDecrementTime()){
			getStatementProgram().addTime(-getDecrementTime());
			this.execute(program);
		}
		else{
			///TE FIXEN (DIE DAMES BRADA) => BOLLEN ZUIGT///
		}
	}
	
	protected abstract void execute(Program program);
	
	protected double getDecrementTime(){
		return decrement_time;
	}
	
	private double decrement_time = 0.2;

}
