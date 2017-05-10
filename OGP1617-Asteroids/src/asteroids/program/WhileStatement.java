package asteroids.program;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

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
		this.broken = true;
	}
	
	protected boolean isBroken(){
		return this.broken;
	}
	
	@Override
	public void evaluate(Program program, List<MyExpression> actualArgs) {
		setStatementProgram(program);
		if (canHaveAsCondition(condition, actualArgs,null)){
			while ((boolean) condition.getExpressionResult(program, actualArgs,null) && !isBroken()){
				try {
					body.evaluate(program, actualArgs);
				} catch (IllegalAccessError error) {
					setBrokenTrue();
				}
			}
		}
		else
			throw new IllegalArgumentException();
	}
	
	public Object evaluateInFunction(Program program, List<MyExpression> actualArgs,MyFunction function){
		setStatementProgram(program);
		if (canHaveAsCondition(condition, actualArgs,function)){
			while ((boolean) condition.getExpressionResult(program, actualArgs,function) && !isBroken()){
				try {
					return body.evaluateInFunction(program, actualArgs,function);
				} catch (IllegalAccessError error) {
					setBrokenTrue();
					return null;
				}
			}
			return null;
		}
		else
			throw new IllegalArgumentException();
	}
	
	@Override
	public void ignoreUntil(Program program, List<MyExpression> actualArgs ,MyFunction function SourceLocation location) {
		while ((boolean) condition.getExpressionResult(program, actualArgs,function) && !isBroken()){
			try {
				body.ignoreUntil(program, actualArgs,function ,location);
			} catch (IllegalAccessError error) {
				setBrokenTrue();
			}
		}
	}
	
//	protected boolean canHaveAsCondition(MyExpression condition){
//		return (condition.getExpressionResult(getStatementProgram()) instanceof Boolean);
//	}

	private MyExpression condition;
	private MyStatement body;
	private boolean broken = false;
	
}
