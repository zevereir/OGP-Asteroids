package asteroids.program;

import java.util.ArrayList;
import java.util.List;

class SequenceStatement extends MyStatement {
	
	public SequenceStatement(List<MyStatement> statements) {
		setStatements(statements);
		
	}
	@Override
	public void evaluate() {
		for (MyStatement statement : statements) {
			statement.evaluate();
		}
	}
	
	private void setStatements(List<MyStatement> statements){
		this.statements = statements;
	}
	
	

	private List<MyStatement> statements = new ArrayList<MyStatement>();
}
