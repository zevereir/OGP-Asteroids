package asteroids.program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import asteroids.model.Ship;



public class Program {
	
	/// CONSTRUCTOR ///
	
	protected Program(List<MyFunction> functions, MyStatement main) {
		this.functions = functions;
		this.main = main;
	}

	public List<Object> execute( double dt) {
		addTime(dt);
		if (first_time){
			main.evaluate();
			first_time = false;}
		return null;
		
	}
	
	private MyStatement main;
	private double time_left = 0;
	private boolean first_time = true;
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
	
	protected List<MyFunction> getProgramFunctions(){
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
	protected void addFunction(MyFunction function){
		functions.add(function);
	}
	protected void addConstant(String string, double constant){
		constants.put(string, constant);
	}
	
	protected void addTime(double dt){
		time_left =+ dt;
	}
	
	
	/// CONNECTIONS WITH OTHER CLASSES ///
	
	private Ship ship = null;
	private List<MyFunction> functions = new ArrayList<MyFunction>();
	private Map<String, MyExpression> variables = new HashMap<String, MyExpression>();
	private Map<String, Double> constants = new HashMap<String, Double>();
	
}
