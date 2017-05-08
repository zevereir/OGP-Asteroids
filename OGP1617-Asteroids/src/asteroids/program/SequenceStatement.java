package asteroids.program;

import java.util.ArrayList;
import java.util.List;

class SequenceStatement extends MyStatement {
	
	public SequenceStatement(List<MyStatement> statements) {
		setStatements(statements);
		
	}
	@Override
	public void evaluate(Program program, List<MyExpression> actualArgs) {
		boolean containsBreak = false;
		
		for (MyStatement statement : statements) {
			if(! (statement instanceof BreakStatement) && !containsBreak)
				statement.evaluate(program, actualArgs);				
			else
				containsBreak = true;
		}
	}
	
	private void setStatements(List<MyStatement> statements){
		this.statements = statements;
	}
	
	private List<MyStatement> getStatements() {
		return statements;
	}
	
	public boolean containsBreak() {
		boolean containsBreak = false;
		for (MyStatement statement : statements)
			if(statement instanceof BreakStatement)
				containsBreak = true;
		return containsBreak;
	}
	
//	protected Object evaluateInFunction(Program program) {
//		setStatementProgram(program);
//		
//		for (MyStatement statement : getStatements()) {
//			if (statement instanceof PrintStatement){
//				return statement.evaluate(program);
//			}
//			else {
//				return statement.evaluateInFunction(program);
//			}
//		}
//	}

	private List<MyStatement> statements = new ArrayList<MyStatement>();
	
}