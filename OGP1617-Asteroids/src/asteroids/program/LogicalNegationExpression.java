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
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs) throws IllegalArgumentException {
		setExpressionProgram(program);
		
		if (canHaveAsLogicalNegationOperand(operand, actualArgs)) {
			return !(Boolean)operand.getExpressionResult(program, actualArgs);
		}
		else
			throw new IllegalArgumentException();
	}
	
	
	/// SETTERS ///
	
	protected void setOperand(MyExpression operand) throws IllegalArgumentException{
		this.operand = operand;
	}
	
	
	/// CHECKERS ///
	
	protected boolean canHaveAsLogicalNegationOperand(MyExpression operand, List<MyExpression> actualArgs){
		return (operand.getExpressionResult(getExpressionProgram(), actualArgs) instanceof Boolean);
		
	}
	
	protected void assignExpressionToParameter(List<MyExpression> actualArgs) {
		if (getOperand() instanceof ParameterExpression)
			setOperand(actualArgs.get(((ParameterExpression)getOperand()).getParameterNumber()-1));
	}
	
	
	
	
	/// PROPERTIES ///
	private MyExpression operand;

}
