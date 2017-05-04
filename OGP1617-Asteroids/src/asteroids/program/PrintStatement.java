package asteroids.program;

class PrintStatement extends MyStatement {

	public PrintStatement(MyExpression expression) {
		setExpression(expression);
	}
	
	private void setExpression(MyExpression expression) {
		this.expression = expression;
	}
	
	private MyExpression expression;
	
	@Override
	public void evaluate() {
		System.out.println(expression.getExpressionResult());
	}

	

}
