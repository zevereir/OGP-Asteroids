package asteroids.program;

import java.util.List;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class ProgramFactory implements asteroids.part3.programs.IProgramFactory<MyExpression, MyStatement, MyFunction, Program>{

	public ProgramFactory(){
	
	}
	
	@Override
	public Program createProgram(List<MyFunction> functions, MyStatement main) {
		return new Program(functions, main);
	}

	@Override
	public MyFunction createFunctionDefinition(String functionName, MyStatement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createAssignmentStatement(String variableName, MyExpression expression, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createWhileStatement(MyExpression condition, MyStatement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createBreakStatement(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createReturnStatement(MyExpression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createIfStatement(MyExpression condition, MyStatement ifBody, MyStatement elseBody,SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createPrintStatement(MyExpression value, SourceLocation sourceLocation) {
		MyStatement result = new PrintStatement();
		return result;
	}

	@Override
	public MyStatement createSequenceStatement(List<MyStatement> statements, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	public MyExpression createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		MyExpression result = new VariableExpression(variableName);
		return result;
	}

	public MyExpression createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		MyExpression result = new ParameterExpression(parameterName);
		return result;
	}

	public MyExpression createFunctionCallExpression(String functionName, List<MyExpression> actualArgs,
			SourceLocation sourceLocation) {
		MyExpression result = new FunctionExpression(functionName, actualArgs);
		return result;
	}

	public MyExpression createChangeSignExpression(MyExpression expression, SourceLocation sourceLocation)  {
		
			MyExpression result = new NegationExpression((ArithmeticExpression)expression);
			return result;
	}

	public MyExpression createNotExpression(MyExpression expression, SourceLocation sourceLocation)   {
			MyExpression result = new LogicalNegationExpression(expression);
			return result;
		
	}

	public MyExpression createDoubleLiteralExpression(double value, SourceLocation location) {
		MyExpression result = new DoubleLiteralExpression(value);
		return result;
	}

	public MyExpression createNullExpression(SourceLocation location) {
		MyExpression result = new NullEntity();
		return result;
	}

	public MyExpression createSelfExpression(SourceLocation location) {
		MyExpression result = new SelfEntity();
		return result;
	}

	public MyExpression createShipExpression(SourceLocation location) {
		MyExpression result = new ShipEntity();
		return result;
	}

	public MyExpression createAsteroidExpression(SourceLocation location) {
		MyExpression result = new AsteroidEntity();
		return result;
	}

	public MyExpression createPlanetoidExpression(SourceLocation location) {
		MyExpression result = new PlanetoidEntity();
		return result;
	}

	public MyExpression createBulletExpression(SourceLocation location) {
		MyExpression result = new BulletEntity();
		return result;
	}

	public MyExpression createPlanetExpression(SourceLocation location) {
		MyExpression result = new PlanetEntity();
		return result;
	}

	public MyExpression createAnyExpression(SourceLocation location) {
		MyExpression result = new AnyEntity();
		return result;
	}

	public MyExpression createGetXExpression(MyExpression e, SourceLocation location) {
		MyExpression result = new XPositionExpression((Entity)(e.getExpressionResult()));
		return result;
	}

	public MyExpression createGetYExpression(MyExpression e, SourceLocation location) {
		MyExpression result = new YPositionExpression((Entity)(e.getExpressionResult()));
		return result;
	}

	public MyExpression createGetVXExpression(MyExpression e, SourceLocation location) {
		MyExpression result = new XVelocityExpression((Entity)(e.getExpressionResult()));
		return result;
	}

	public MyExpression createGetVYExpression(MyExpression e, SourceLocation location) {
		MyExpression result = new YVelocityExpression((Entity)(e.getExpressionResult()));
		return result;
	}

	public MyExpression createGetRadiusExpression(MyExpression e, SourceLocation location) {
		MyExpression result = new RadiusExpression((Entity)(e.getExpressionResult()));
		return result;
	}

	public MyExpression createLessThanExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		MyExpression result = new LessThanExpression((ArithmeticExpression)e1,(ArithmeticExpression)e2);
		return result;
	}

	public MyExpression createEqualityExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		MyExpression result = new EqualsToExpression(e1,e2);
		return result;
	}

	public MyExpression createAdditionExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		MyExpression result = new AdditionExpression(e1,e2);
		return result;
	}

	public MyExpression createMultiplicationExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		MyExpression result = new MultiplicationExpression((ArithmeticExpression)e1,(ArithmeticExpression)e2);
		return result;
	}

	public MyExpression createSqrtExpression(MyExpression e, SourceLocation location) {
		MyExpression result = new SquareRootExpression((ArithmeticExpression)e);
		return result;
	}

	public MyExpression createGetDirectionExpression(SourceLocation location) {
		MyExpression result = new DirectionExpression();
		return result;
	}

	@Override
	public MyStatement createThrustOnStatement(SourceLocation location) {
		MyStatement result = new ThrustOnAction();
		return result;
	}

	@Override
	public MyStatement createThrustOffStatement(SourceLocation location) {
		MyStatement result = new ThrustOffAction();
		return result;
	}

	@Override
	public MyStatement createFireStatement(SourceLocation location) {
		MyStatement result = new FireAction();
		return result;
	}

	@Override
	public MyStatement createTurnStatement(MyExpression angle, SourceLocation location) {
		MyStatement result = new TurnAction(angle);
		return result;
	}

	@Override
	public MyStatement createSkipStatement(SourceLocation location) {
		MyStatement result = new SkipAction();
		return result;
	}

}