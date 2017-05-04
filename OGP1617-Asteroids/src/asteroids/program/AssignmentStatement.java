package asteroids.program;

class AssignmentStatement extends MyStatement {
	
	public AssignmentStatement(String variableName, MyExpression expression) {
		setAssignment(variableName,expression);
	}

	@Override
	public void evaluate() {
		// TODO Auto-generated method stub
		
	}
	
	protected setAssignment(String variableName, MyExpression expression){
		getStatementProgram().addVariable(variableName,expression);
		
	}

	
	
	

}
