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
	public void evaluate(Program program){
		setStatementProgram(program);
		Object result = expression.getExpressionResult(program);
		System.out.println("PrintStatement's fault (please don't hit me): "+result);
		getStatementProgram().addPrintOut(result);
	}
	
}
