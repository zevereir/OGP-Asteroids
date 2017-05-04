package asteroids.program;

import java.util.ArrayList;
import java.util.List;

class SequenceStatement extends MyStatement {
	
	public SequenceStatement(List<MyStatement> statements) {
		
	}
	@Override
	public void evaluate() {
		for (MyStatement statement : statements) {
			statement.evaluate();
		}
	}

	private List<MyStatement> statements = new ArrayList<MyStatement>();
}
