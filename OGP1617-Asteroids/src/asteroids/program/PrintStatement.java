package asteroids.program;

import java.util.List;

class PrintStatement extends MyStatement {

	public PrintStatement(MyExpression expression) {
		setExpression(expression);
	}
	
	private void setExpression(MyExpression expression) {
		this.expression = expression;
	}
	
	private MyExpression expression;
	
	@Override
	public void evaluate(Program program, List<MyExpression> actualArgs){
		setStatementProgram(program);
		if (expression instanceof ParameterExpression)
			throw new IllegalArgumentException();
		Object result = expression.getExpressionResult(program, null);
		System.out.println("PrintStatement's fault (please don't hit me): "+result);
		getStatementProgram().addPrintOut(result);
	}
	
}
