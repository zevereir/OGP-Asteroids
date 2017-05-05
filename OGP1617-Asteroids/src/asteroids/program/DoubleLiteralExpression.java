package asteroids.program;

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
		protected Object getExpressionResult(Program program) {
			setExpressionProgram(program);
			return getValue();
		}
		
		
		/// SETTERS ///

		protected void setValue(double value) throws IllegalArgumentException{
			this.value = value;
		}

	}
