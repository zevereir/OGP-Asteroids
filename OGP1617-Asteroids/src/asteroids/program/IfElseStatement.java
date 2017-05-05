package asteroids.program;

 class IfElseStatement extends MyStatement {

	public IfElseStatement(MyExpression condition, MyStatement ifBody, MyStatement elseBody){
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
	}
	
	protected MyExpression getCondition(){
		return this.condition;
	}
	
	@Override
	public void evaluate(Program program) {
		setStatementProgram(program);
		
		if ((boolean)condition.getExpressionResult())
			ifBody.evaluate(program);
		else if (elseBody != null)
			elseBody.evaluate(program);
	}
	
	public Object evaluateInFunction(){
		if ((boolean)getCondition().getExpressionResult())
			return ifBody.evaluateInFunction();
		else if (elseBody != null)
			return elseBody.evaluateInFunction();
		return null;
	}
	
	private void setCondition(MyExpression condition){
		this.condition = condition;
	}
	
	private void setIfBody(MyStatement ifBody){
		this.ifBody= ifBody;
	}
	private void setElseBody(MyStatement elseBody){
		this.elseBody= elseBody;
	}
	
	private MyExpression condition;
	private MyStatement ifBody;
	private MyStatement elseBody;

	

}
