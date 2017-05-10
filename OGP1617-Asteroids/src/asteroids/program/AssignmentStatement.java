package asteroids.program;

import java.util.List;

class AssignmentStatement extends MyStatement {
	
	public AssignmentStatement(String variableName, MyExpression expression) {
		setVariableName(variableName);
		setExpression(expression);
	}

	@Override
	public void evaluate(Program program, List<MyExpression> actualArgs) {
		setStatementProgram(program);
		
		if (isValidVariable(getAssignmentVariableName(), getAssignmentExpression(), actualArgs,null))
			getStatementProgram().addVariable(variableName,expression.getExpressionResult(program, actualArgs,null));
		else
			throw new IllegalArgumentException();
	}
	
	public void assignLocalVariable(Program program, List<MyExpression> actualArgs, MyFunction function){
		setStatementProgram(program);
		if (isValidVariable(getAssignmentVariableName(), getAssignmentExpression(), actualArgs,function))
			function.addLocalVariable(variableName,expression.getExpressionResult(program, actualArgs,function));
		else
			throw new IllegalArgumentException();
	}
	/// GETTERS ///
	protected MyExpression getAssignmentExpression(){
		return this.expression;
	}
	
	protected String getAssignmentVariableName(){
		return this.variableName;
	}
	
	/// SETTERS ///
	protected void setVariableName(String variableName){
		this.variableName = variableName;
		
	}
	protected void setExpression(MyExpression expression){
		this.expression = expression;
	}
	
	
	/// CHECKERS ///
	protected boolean isValidVariable(String variableName, MyExpression expression, List<MyExpression> actualArgs,MyFunction function){
		return ((!getStatementProgram().getProgramFunctions().containsKey(variableName)) && 
				(expression.getExpressionResult(getStatementProgram(), actualArgs,function) instanceof Double));
	}
	/// PROPERTIES ///
	
	private MyExpression expression;
	private String variableName;
	

}
