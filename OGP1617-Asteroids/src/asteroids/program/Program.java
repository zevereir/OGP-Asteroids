package asteroids.program;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import asteroids.model.Ship;



public class Program {
	
	/// CONSTRUCTOR ///
	
	protected Program(List<MyFunction> functions, MyStatement main) {
		
	}
	
	public List<Object> execute( double dt) {
		return null;
	}
	
	
	/// GETTERS ///
	
	public Ship getProgramShip(){
		return ship;
	}
	
	protected Map<String,Double> getProgramVariables(){
		return variables;
	}
	
	protected Map<String,Double> getProgramConstants(){
		return constants;
	}
	
	protected Map<String,Function<List<MyExpression>,?>> getProgramFunctions(){
		return functions;
	}
	
	/// SETTERS ///
	
	public void setProgramShip(Ship ship){
		this.ship = ship;
	}
	
	/// ADDERS ///
	protected void addVariable(String string, double variable){
		variables.put(string, variable);
	}
	protected void addFunction(String string, Function<List<MyExpression>,?> function){
		functions.put(string, function);
	}
	protected void addConstant(String string, double constant){
		constants.put(string, constant);
	}
	
	
	/// CONNECTIONS WITH OTHER CLASSES ///
	
	private Ship ship = null;
	private Map<String, Function<List<MyExpression>,?>> functions = new HashMap<String, Function<List<MyExpression>,?>>();
	private Map<String, Double> variables = new HashMap<String, Double>();
	private Map<String, Double> constants = new HashMap<String, Double>();
	
}
