package asteroids.program;

import java.util.List;

class DoubleLiteralExpression extends MyExpression implements ArithmeticExpression {

	/// CONSTRUCTOR ///

	public DoubleLiteralExpression(double value) {
		setValue(value);
	}

	
	/// BASIC PROPERTIES ///
	
	private double value;
	
	
	/// GETTERS ///

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		return getValue();
	}

	protected double getValue() {
		return value;
	}
	

	/// SETTERS ///

	protected void setValue(double value) throws IllegalArgumentException {
		this.value = value;
	}

}
