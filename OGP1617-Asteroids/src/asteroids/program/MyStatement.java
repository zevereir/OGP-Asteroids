package asteroids.program;

import java.util.List;

import javax.management.RuntimeErrorException;

import asteroids.model.Ship;


public abstract class MyStatement {
	
	/// GETTERS ///
	
	protected Program getStatementProgram(){
		return this.program;
	}
	
	protected Ship getStatementShip(){
		return this.getStatementProgram().getProgramShip();
	}
	
	
	/// SETTERS ///
	
	protected void setStatementProgram(Program program){
		this.program = program;
	}
	
	
	/// CONNECTIONS WITH OTHER CLASSES ///
	private Program program = null;

	protected abstract void evaluate(Program getStatementProgram, List<MyExpression> actualArgs);
	
	protected Object evaluateInFunction(Program program, List<MyExpression> actualArgs,MyFunction function){
		return null;
	}
	
	protected boolean canHaveAsCondition(MyExpression condition, List<MyExpression> actualArgs){
		return (condition.getExpressionResult(getStatementProgram(), actualArgs) instanceof Boolean);
	}
	
	
	
//	protected void assignParameters(List<MyExpression> actualArgs, MyExpression[] parameterArray, Program program){
//
//		MyExpression leftParameter = parameterArray[0];
//		MyExpression rightParameter = parameterArray[1];
//		
//		try {
//			if (this instanceof IfElseStatement){
//				((IfElseStatement)this).getCondition().getExpressionParameter(actualArgs);
//			}
//			else if (this instanceof ReturnStatement){
//				if (((ReturnStatement)this).getReturnExpression() instanceof ParameterExpression)
//					((ReturnStatement)this).setReturnExpression(actualArgs.get( ((ParameterExpression) (((ReturnStatement)this).getReturnExpression()) ).getParameterNumber()-1));
//				else
//					((ReturnStatement)this).getReturnExpression().getExpressionParameter(actualArgs);
//			}
////			else if (this instanceof AssignmentStatement){
////				((AssignmentStatement)this).getAssignmentExpression().assignExpressionToParameter(actualArgs);
////			}
//		} catch (IndexOutOfBoundsException indexOutOfBoundsException) {
//			throw new RuntimeErrorException(new IllegalAccessError() );
//		}
//			
//	}
}
