package asteroids.program;

 class ReturnStatement extends MyStatement {
	 public ReturnStatement(MyExpression value){
		 setExpression(value);
	 }

	 protected void setExpression(MyExpression expression){
	 this.expression = expression;
	 }
	 
	 private MyExpression expression;
	 
	 public Object getReturnStatementResult(){
		 return expression.getExpressionResult();
	 }
}
