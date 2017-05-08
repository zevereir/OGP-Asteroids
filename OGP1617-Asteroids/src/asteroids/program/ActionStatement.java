package asteroids.program;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

abstract class ActionStatement extends MyStatement {

	@Override
	public void evaluate(Program program, List<MyExpression> actualArgs){
		setStatementProgram(program);
		
		while (getStatementProgram().getTimeLeft() >= getDecrementTime()){	
			getStatementProgram().addTime(-getDecrementTime());
			this.execute(program);
		}
		
		
		
		
	}
	
	protected abstract void execute(Program program);
	
	protected double getDecrementTime(){
		return decrement_time;
	}
	
	private double decrement_time = 0.2;
	
	private SourceLocation sourcelocation;
	
	protected void setSourceLocation(SourceLocation location){
		sourcelocation = location;
	}
	
	protected SourceLocation getSourceLocation(){
		return sourcelocation;
	}
}
