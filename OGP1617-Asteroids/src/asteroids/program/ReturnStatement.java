package asteroids.program;

 class ReturnStatement extends MyStatement {
	 public ReturnStatement(MyExpression expression){
		 setExpression(expression);
	 }

	 protected MyExpression getReturnExpression(){
		 return this.expression;
	 }
	 
	 protected void setExpression(MyExpression expression){
	 this.expression = expression;
	 }
	 
	 private MyExpression expression;
	 
	 public Object evaluateInFunction(){	
		 return expression.getExpressionResult();
	 }

	@Override
	protected void evaluate(Program program) {
		setStatementProgram(program);
	}

}
