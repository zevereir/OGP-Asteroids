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
	
	public void setBrokenTrue(){
		this.isBroken = true;
	}
	
	@Override
	public void evaluate() {
		while ((boolean) condition.getExpressionResult() && !isBroken)
			if (body instanceof BreakStatement)
				setBrokenTrue();
			else if (body instanceof SequenceStatement && ((SequenceStatement)body).containsBreak()) {
				body.evaluate();
				setBrokenTrue();
			}
			else
				body.evaluate();
	}


	private MyExpression condition;
	private MyStatement body;
	private boolean isBroken = false;
	
}
