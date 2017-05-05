package asteroids.program;

import java.util.ArrayList;
import java.util.List;

class SequenceStatement extends MyStatement {
	
	public SequenceStatement(List<MyStatement> statements) {
		setStatements(statements);
		
	}
	@Override
	public void evaluate() {
		boolean containsBreak = false;
		
		for (MyStatement statement : statements) {
			if(! (statement instanceof BreakStatement) && !containsBreak)
				statement.evaluate();				
			else
				containsBreak = true;
		}
	}
	
	private void setStatements(List<MyStatement> statements){
		this.statements = statements;
	}
	
	public boolean containsBreak() {
		boolean containsBreak = false;
		for (MyStatement statement : statements)
			if(statement instanceof BreakStatement)
				containsBreak = true;
		return containsBreak;
	}

	private List<MyStatement> statements = new ArrayList<MyStatement>();
	
}