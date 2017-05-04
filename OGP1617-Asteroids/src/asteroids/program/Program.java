package asteroids.program;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import asteroids.model.Ship;



public class Program {
	
	/// CONSTRUCTOR ///
	
	protected Program(List<MyFunction> functions, MyStatement main) {
	this.main = main;
	}
//	
//	public List<Object> execute( double dt) {
//		return Test.getReturnStatementResult();
//	}
	public void execute( double dt) {
		addTime(dt);		
		main.evaluate();
	}
	
	private MyStatement main;
	private double time_left = 0;
	
	/// GETTERS ///
	
	public Ship getProgramShip(){
		return ship;
	}
	
	protected Map<String,MyExpression> getProgramVariables(){
		return variables;
	}
	
	protected Map<String,Double> getProgramConstants(){
		return constants;
	}
	
	protected Map<String,Function<List<MyExpression>,?>> getProgramFunctions(){
		return functions;
	}
	protected double getTimeLeft(){
		return time_left;
	}
	
	/// SETTERS ///
	
	public void setProgramShip(Ship ship){
		this.ship = ship;
	}
	
	/// ADDERS ///
	protected void addVariable(String string, MyExpression expression){
		variables.put(string, expression);
	}
	protected void addFunction(String string, Function<List<MyExpression>,?> function){
		functions.put(string, function);
	}
	protected void addConstant(String string, double constant){
		constants.put(string, constant);
	}
	
	protected void addTime(double dt){
		time_left =+ dt;
	}
	
	
	/// CONNECTIONS WITH OTHER CLASSES ///
	
	private Ship ship = null;
	private Map<String, Function<List<MyExpression>,?>> functions = new HashMap<String, Function<List<MyExpression>,?>>();
	private Map<String, MyExpression> variables = new HashMap<String, MyExpression>();
	private Map<String, Double> constants = new HashMap<String, Double>();
	
}
