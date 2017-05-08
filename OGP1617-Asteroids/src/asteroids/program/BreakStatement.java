package asteroids.program;

import java.util.List;

class BreakStatement extends MyStatement {
	public BreakStatement(){
		
	}
	
	public void evaluate(Program program, List<MyExpression> actualArgs) {
		throw new IllegalAccessError();
	}
	
	public Object evaluateInFunction(Program program, List<MyExpression> actualArgs,MyFunction function){
		throw new IllegalAccessError();
	}
}