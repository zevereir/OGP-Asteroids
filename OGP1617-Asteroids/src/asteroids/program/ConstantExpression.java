package asteroids.program;


class ConstantExpression extends MyExpression {
	
	/// CONSTRUCTOR ///
	
	public ConstantExpression(String ConstantName) {	
		setConstant(ConstantName);
	}

	/// PROPERTIES ///
	private String ConstantName;
	/// GETTERS ///
	
	
	protected double getConstant(String ConstantName){
		return this.getExpressionProgram().getProgramConstants().get(ConstantName);
	}

	@Override
	protected Object getExpressionResult() {
		return getConstant(ConstantName);
	}
	
	
	/// SETTERS ///

	protected void setConstant(String ConstantName) throws IllegalArgumentException{
		this.ConstantName = ConstantName;
		getExpressionProgram().addConstant(ConstantName,Double.NaN);
	}

}

