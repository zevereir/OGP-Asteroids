package asteroids.program;



class DirectionExpression extends MyExpression {
	/// CONSTRUCTOR ///
		public DirectionExpression() {
		}

		@Override
		protected Object getExpressionResult(Program program) {
			setExpressionProgram(program);
			
			return getExpressionShip().getEntityOrientation();
		}


}
