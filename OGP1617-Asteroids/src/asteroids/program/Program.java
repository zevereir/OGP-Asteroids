package asteroids.program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import asteroids.model.Ship;



public class Program {
	
	/// CONSTRUCTOR ///
	
	protected Program(List<MyFunction> functions, MyStatement main) {
		setFunctions(functions);
		setMain(main);
		main.setStatementProgram(this);
	}

	public List<Object> execute(double dt) {
		addTime(dt);
		if (first_time){
			main.evaluate(this);
			first_time = false;
		}
		return null;
	}
	
	private MyStatement main;
	private double time_left = 0;
	private boolean first_time = true;
	
	/// GETTERS ///
	
	public Ship getProgramShip(){
		return ship;
	}
	
	protected Map<String,Object> getProgramVariables(){
		return variables;
	}
	
	protected Map<String,MyFunction> getProgramFunctions(){
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
	protected void addVariable(String string, Object object){
		variables.put(string, object);
	}
	
	protected void addTime(double dt){
		time_left =+ dt;
	}
	
	protected void setFunctions(List<MyFunction> functions){
		for (MyFunction function:functions){
			this.functions.put(function.getFunctionName(), function);
		}
	}
	
	protected void setMain(MyStatement main){
		this.main = main;
	}
	/// CONNECTIONS WITH OTHER CLASSES ///
	
	private Ship ship = null;
	private Map<String,MyFunction> functions = new HashMap<String,MyFunction>();
	private Map<String, Object> variables = new HashMap<String, Object>();
	
	
}
