package asteroids.program;

class WhileStatement extends MyStatement {
	 
	public WhileStatement(MyExpression condition, MyStatement body) {
		setCondition(condition);
		setBody(body);
	}
	
	public void setCondition(MyExpression condition) {
		this.condition = condition;
	}
	
	public void setBody(MyStatement body) {
		this.body = body;
	}
	
	public void setBroken(){
		this.isBroken = true;
	}
	
	@Override
	public void evaluate() {
		if (body instanceof BreakStatement)
			body.setWhile(this);
		while ((boolean) condition.getExpressionResult() && !isBroken)
			body.evaluate();
	}


	private MyExpression condition;
	private MyStatement body;
	private boolean isBroken = false;
	
}
