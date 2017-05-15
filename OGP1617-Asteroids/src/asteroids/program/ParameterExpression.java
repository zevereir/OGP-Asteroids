package asteroids.program;

import java.util.List;
class ParameterExpression extends NameExpression implements ArithmeticExpression {

	/// CONSTRUCTOR ///

	public ParameterExpression(String ParameterName) {
		super(ParameterName);
	}

	
	/// GETTERS ///

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		return actualArgs.get(getParameterNumber() - 1).getExpressionResult(program, actualArgs, function);
	}

	protected int getParameterNumber() {
		String parameter = getName();
		
		return Integer.parseInt(parameter.substring(1));
	}

}
