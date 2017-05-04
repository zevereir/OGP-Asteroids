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
	
	@Override
	public void evaluate() {
		while ((boolean) condition.getExpressionResult())
			body.evaluate();
	}


	private MyExpression condition;
	private MyStatement body;
	
}
