package asteroids.program;

import java.util.List;

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
	 
	 public Object evaluateInFunction(Program program, List<MyExpression> actualArgs){	
		 return expression.getExpressionResult(program, actualArgs);
	 }

	@Override
	protected void evaluate(Program program, List<MyExpression> actualArgs) {
		setStatementProgram(program);
	}

}
