package asteroids.program;

import java.util.List;

class BreakStatement extends MyStatement {
	public BreakStatement(){
		
	}
	
	public void evaluate(Program program, List<MyExpression> actualArgs) {
		setStatementProgram(program);
		throw new IllegalAccessError();
	}
	
}
