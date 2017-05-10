package asteroids.program;

import java.util.List;

class DoubleLiteralExpression extends MyExpression {
	/// CONSTRUCTOR ///
	
		public DoubleLiteralExpression(double value) {	
			setValue(value);
		}

		/// PROPERTIES ///
		private double value;
		/// GETTERS ///
		
		
		protected double getValue(){
			return value;
		}

		@Override
		protected Object getExpressionResult(Program program, List<MyExpression> actualArgs,MyFunction function) {
			setExpressionProgram(program);
			
			return getValue();
		}
		
		
		/// SETTERS ///

		protected void setValue(double value) throws IllegalArgumentException{
			this.value = value;
		}
		
		
//		protected void assignExpressionToParameter(List<MyExpression> actualArgs) {
//			if (getValue() instanceof ParameterExpression)
//				setValue(actualArgs.get(((ParameterExpression)getValue()).getParameterNumber()-1));
//		}

	}
