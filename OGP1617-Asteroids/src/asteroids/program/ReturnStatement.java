package asteroids.program;

 class ReturnStatement extends MyStatement {
	 public ReturnStatement(MyExpression expression){
		 setReturnExpression(expression);
	 }

	 protected MyExpression getReturnExpression(){
		 return this.expression;
	 }
	 
	 protected void setReturnExpression(MyExpression expression){
		 this.expression = expression;
	 }
	 
	 private MyExpression expression;
	 
	 public Object evaluateInFunction(Program program){	
		 System.out.println("ReturnStatement, "+expression.getExpressionResult(program));
		 return expression.getExpressionResult(program);
	 }

	@Override
	protected void evaluate(Program program) {
		setStatementProgram(program);
	}

}
