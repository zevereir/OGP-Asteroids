package asteroids.program;

import java.util.ArrayList;
import java.util.List;

class SequenceStatement extends MyStatement {
	
	public SequenceStatement(List<MyStatement> statements) {
		
	}
	
	public void evaluate() {
		for (MyStatement statement : statements) {
			if (statement.evaluateWithReturn() == null) 
				statement.evaluate();
		}
	}
	
	public void evaluateWithReturn() {
		for (MyStatement statement : statements) {
			if (statement.evaluateWithReturn() != null) 
				statement.evaluateWithReturn();
		}
	}


	private List<MyStatement> statements = new ArrayList<MyStatement>();
}
