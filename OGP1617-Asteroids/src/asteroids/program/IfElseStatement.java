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
		
		if ((boolean)condition.getExpressionResult(program))
			ifBody.evaluate(program);
		else if (elseBody != null)
			elseBody.evaluate(program);
	}
	
	public Object evaluateInFunction(Program program){
		setStatementProgram(program);
		if ((boolean)getCondition().getExpressionResult(program))
			return ifBody.evaluateInFunction(getStatementProgram());
		else if (elseBody != null)
			return elseBody.evaluateInFunction(getStatementProgram());
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
