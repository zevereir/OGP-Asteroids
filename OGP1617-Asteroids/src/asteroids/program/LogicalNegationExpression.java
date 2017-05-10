package asteroids.program;

import java.util.List;

class LogicalNegationExpression extends MyExpression {
	
	/// CONSTRUCTOR ///
	
	public LogicalNegationExpression(MyExpression operand) {
		setOperand(operand);
	}
	
	
	/// GETTERS ///
	
	protected MyExpression getOperand(){
		return operand;
	}
	
	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs,MyFunction function) throws IllegalArgumentException {
		setExpressionProgram(program);
		
		if (canHaveAsLogicalNegationOperand(operand, actualArgs,function)) {
			return !(Boolean)operand.getExpressionResult(program, actualArgs,function);
		}
		else
			throw new IllegalArgumentException();
	}
	
	
	/// SETTERS ///
	
	protected void setOperand(MyExpression operand) throws IllegalArgumentException{
		this.operand = operand;
	}
	
	
	/// CHECKERS ///
	
	protected boolean canHaveAsLogicalNegationOperand(MyExpression operand, List<MyExpression> actualArgs,MyFunction function){
		return (operand.getExpressionResult(getExpressionProgram(), actualArgs,function) instanceof Boolean);
		
	}
	
	protected void assignExpressionToParameter(List<MyExpression> actualArgs) {
		if (getOperand() instanceof ParameterExpression)
			setOperand(actualArgs.get(((ParameterExpression)getOperand()).getParameterNumber()-1));
	}
	
	
	
	
	/// PROPERTIES ///
	private MyExpression operand;

}
