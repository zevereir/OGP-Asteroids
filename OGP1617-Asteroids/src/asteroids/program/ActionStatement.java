package asteroids.program;

abstract class ActionStatement extends MyStatement {

	@Override
	public void evaluate(Program program){
		setStatementProgram(program);
		if (getStatementProgram().getTimeLeft() >= decrement_time){
			getStatementProgram().addTime(-decrement_time);
			this.execute(program);
		}
		else{
			///TE FIXEN (DIE DAMES BRADA) => BOLLEN ZUIGT///
		}
	}
	
	protected abstract void execute(Program program);
	private double decrement_time = 0.2;

}
