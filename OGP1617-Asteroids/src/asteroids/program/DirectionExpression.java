package asteroids.program;

import asteroids.model.Entity;

class DirectionExpression extends MyExpression {
	/// CONSTRUCTOR ///
		public DirectionExpression() {
			setDirectionOperand(getExpressionShip().getEntityOrientation());
		}

		/// GETTERS ///
		protected double getOperand(){
			return operand;
		}

		@Override
		protected Object getExpressionResult() {
			return getOperand();
		}
		/// SETTERS ///

		protected void setDirectionOperand(double operand) throws IllegalArgumentException{
			this.operand = operand;
		}
		

		/// PROPERTIES ///
		private double operand;

	}

}
