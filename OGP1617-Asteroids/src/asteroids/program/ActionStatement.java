package asteroids.program;

abstract class ActionStatement extends MyStatement {

	
	public void evaluate(){
		if (getStatementProgram().getTimeLeft() >= decrement_time){
			getStatementProgram().addTime(-decrement_time);
			this.execute();
		}
		else{
			///TE FIXEN (DIE DAMES BRADA) => BOLLEN ZUIGT///
		}
	}
	
	protected abstract void execute();
	private double decrement_time = 0.2;

}
