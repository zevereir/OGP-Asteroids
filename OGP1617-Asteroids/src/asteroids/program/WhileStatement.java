package asteroids.program;

import java.util.List;

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
	public void evaluate(Program program, List<MyExpression> actualArgs) {
		setStatementProgram(program);
		if (canHaveAsCondition(condition, actualArgs)){
			while ((boolean) condition.getExpressionResult(program, actualArgs) && !isBroken){
				if (body instanceof BreakStatement)
					setBrokenTrue();
				else if (body instanceof SequenceStatement && ((SequenceStatement)body).containsBreak()) {
					body.evaluate(program, actualArgs);
					setBrokenTrue();
				}
				else
					body.evaluate(program, actualArgs);
			}
		}
		else
			throw new IllegalArgumentException();
	}
	
//	protected boolean canHaveAsCondition(MyExpression condition){
//		return (condition.getExpressionResult(getStatementProgram()) instanceof Boolean);
//	}

	private MyExpression condition;
	private MyStatement body;
	private boolean isBroken = false;

	
}
