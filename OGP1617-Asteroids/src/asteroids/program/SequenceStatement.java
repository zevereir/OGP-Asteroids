package asteroids.program;

import java.util.ArrayList;
import java.util.List;

class SequenceStatement extends MyStatement {
	
	public SequenceStatement(List<MyStatement> statements) {
		setStatements(statements);
		
	}
	@Override
	public void evaluate(Program program, List<MyExpression> actualArgs) {
		for (MyStatement statement : statements) {
				statement.evaluate(program, actualArgs);				
		}
	}
	
	private void setStatements(List<MyStatement> statements){
		this.statements = statements;
	}
	
	private List<MyStatement> getStatements() {
		return statements;
	}
	
	
	
	protected Object evaluateInFunction(Program program, List<MyExpression> actualArgs,MyFunction function) {
		setStatementProgram(program);
		
		for (MyStatement statement : getStatements()) {
			if (statement instanceof AssignmentStatement)
				((AssignmentStatement)statement).assignLocalVariable(program,actualArgs,function);
			else
				return statement.evaluateInFunction(program,actualArgs,function);
		}
		return null;
	}

	private List<MyStatement> statements = new ArrayList<MyStatement>();
	
}

