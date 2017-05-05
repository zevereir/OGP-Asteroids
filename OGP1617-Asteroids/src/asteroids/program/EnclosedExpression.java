package asteroids.program;

class EnclosedExpression extends MyExpression {

	@Override
	protected Object getExpressionResult(Program program) {
		setExpressionProgram(program);
		return null;
	}

}
