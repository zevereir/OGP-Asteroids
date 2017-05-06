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

	protected abstract void evaluate(Program getStatementProgram);	
	
	protected Object evaluateInFunction(Program program){
		return null;
	}
	
	
	
	protected void assignParameters(List<MyExpression> actualArgs){
		
		try {
			if (this instanceof IfElseStatement){

				System.out.println("MyStatement - assignParameters if statement");

				((IfElseStatement)this).getCondition().assignExpressionToParameter(actualArgs);
			}
			else if (this instanceof ReturnStatement){
				if (((ReturnStatement)this).getReturnExpression() instanceof ParameterExpression)
					((ReturnStatement)this).setReturnExpression(actualArgs.get( ((ParameterExpression) (((ReturnStatement)this).getReturnExpression()) ).getParameterNumber()-1));
				else
					((ReturnStatement)this).getReturnExpression().assignExpressionToParameter(actualArgs);
			}
			else if (this instanceof AssignmentStatement){
				((AssignmentStatement)this).getAssignmentExpression().assignExpressionToParameter(actualArgs);
			}
		} catch (IndexOutOfBoundsException indexOutOfBoundsException) {
			throw new RuntimeErrorException(new IllegalAccessError() );
		}
			
	}
}
