package asteroids.program;

import java.util.List;

class IfElseStatement extends MyStatement {

	public IfElseStatement(MyExpression condition, MyStatement ifBody, MyStatement elseBody){
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
	}
	
	protected MyExpression getCondition(){
		return this.condition;
	}
	
	@Override
	public void evaluate(Program program, List<MyExpression> actualArgs) {
		setStatementProgram(program);
	
		if (canHaveAsCondition(condition, actualArgs)){		
			if ((boolean)condition.getExpressionResult(program, actualArgs))
				ifBody.evaluate(program, actualArgs);
			else if (elseBody != null)
				elseBody.evaluate(program, actualArgs);
		}
		else 
			throw new IllegalArgumentException();
	}
	
	public Object evaluateInFunction(Program program, List<MyExpression> actualArgs,MyFunction function){
		setStatementProgram(program);
		
		if ((boolean)getCondition().getExpressionResult(program, actualArgs)) {
			if (ifBody instanceof AssignmentStatement)
				((AssignmentStatement)ifBody).assignLocalVariable(getStatementProgram(),actualArgs,function);
			else
				return ifBody.evaluateInFunction(getStatementProgram(), actualArgs,null);
		}
		else if (elseBody != null) {
			if (elseBody instanceof AssignmentStatement)
				((AssignmentStatement)elseBody).assignLocalVariable(getStatementProgram(),actualArgs,function);
			else
				return elseBody.evaluateInFunction(getStatementProgram(), actualArgs,null);
		}
		return null;
	}
	
	private void setCondition(MyExpression condition){
		this.condition = condition;
	}
	
	private void setIfBody(MyStatement ifBody){
		this.ifBody= ifBody;
	}
	private void setElseBody(MyStatement elseBody){
		this.elseBody= elseBody;
	}
	
	private MyExpression condition;
	private MyStatement ifBody;
	private MyStatement elseBody;

	

}
