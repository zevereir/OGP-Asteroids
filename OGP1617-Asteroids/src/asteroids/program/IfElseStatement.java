package asteroids.program;

 class IfElseStatement extends MyStatement {

	public IfElseStatement(MyExpression condition, MyStatement ifBody, MyStatement elseBody){
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
	}
	public void evaluate() {
		if ((boolean)condition.getExpressionResult())
			ifBody.evaluate();
		else if (elseBody != null)
			elseBody.evaluate();
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
