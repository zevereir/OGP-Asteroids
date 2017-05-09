package asteroids.program;

import java.util.List;

import javax.management.RuntimeErrorException;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public abstract class MyExpression {

	protected abstract Object getExpressionResult(Program program, List<MyExpression> actualArgs);
	

	/// GETTERS ///
	
	protected Program getExpressionProgram(){
		return this.program;
	}
	
	protected Ship getExpressionShip(){
		return this.getExpressionProgram().getProgramShip();
	}
	
	
	/// SETTERS ///
	
	protected void setExpressionProgram(Program program){
		this.program = program;
	}
	
	
	/// CONNECTIONS WITH OTHER CLASSES ///
	
	private Program program = null;
	
	
//	protected MyExpression[] getExpressionParameter(List<MyExpression> actualArgs) {
//		MyExpression[] nullArray = {null, null};
//		
//		return nullArray;
//	}

	protected MyExpression[] getExpressionParameter(List<MyExpression> actualArgs){
		
		MyExpression expressionLeftParameter = null;
		MyExpression expressionRightParameter = null;
		
		try {
			// UNARY
			if (this instanceof UnaryArithmeticExpression) {
				if (((UnaryArithmeticExpression)this).getOperand() instanceof ParameterExpression)
					expressionLeftParameter = (actualArgs.get(((ParameterExpression)((UnaryArithmeticExpression)this).
							getOperand()).getParameterNumber()-1));
			}
			
			// BINARY
			if (this instanceof BinaryArithmeticExpression) {
				if (((BinaryArithmeticExpression)this).getLeftOperand() instanceof ParameterExpression) {
					expressionLeftParameter = (actualArgs.get(((ParameterExpression) ((BinaryArithmeticExpression)this).
							getLeftOperand()).getParameterNumber()-1));
				}
				if (((BinaryArithmeticExpression)this).getRightOperand() instanceof ParameterExpression) {
					expressionRightParameter = (actualArgs.get(((ParameterExpression) ((BinaryArithmeticExpression)this).
							getRightOperand()).getParameterNumber()-1));
				}	
			}
			
//			System.out.println("MyExpression leftParameter: "+expressionLeftParameter.getExpressionResult(getExpressionProgram(), actualArgs));
//			System.out.println("MyExpression rightParameter: "+expressionRightParameter.getExpressionResult(getExpressionProgram(), actualArgs));

			MyExpression[] parameterArray = {expressionLeftParameter, expressionRightParameter};
			
			return parameterArray;
		} catch (IndexOutOfBoundsException indexOutOfBoundsException) {
			throw new RuntimeErrorException(new IllegalAccessError() );
		}
	}
	
}
