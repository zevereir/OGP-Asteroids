package asteroids.program;

 class ReturnStatement extends MyStatement {
	 public ReturnStatement(MyExpression expression){
		 setExpression(expression);
	 }

	 protected void setExpression(MyExpression expression){
	 this.expression = expression;
	 }
	 
	 private MyExpression expression;
	 
	 public Object evaluateReturn(){	
		 return expression.getExpressionResult();
	 }

	@Override
	protected void evaluate() {
				
	}

}
