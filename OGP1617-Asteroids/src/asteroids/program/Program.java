package asteroids.program;

import java.awt.geom.IllegalPathStateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;



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
			first_time = false;
			try {
				main.evaluate(this, null);
			} catch (IllegalPathStateException e) {
				System.out.println("catched this one");
			}		
		}
		else{
			try {
				//main.ignoreUntil(this, getSourceLocation());
			} catch (IllegalPathStateException e) {
				System.out.println("Stop everything");
			}		
			
		}
	
		return getPrintOuts();
	}
	
	
	private MyStatement main;
	private double time_left = 0;
	private boolean first_time = true;
	private SourceLocation location;
	private boolean mayExecute = false;
	/// GETTERS ///
	
	public Ship getProgramShip(){
		return ship;
	}
	
	public MyStatement getMain() {
		return main;
	}
	
	protected Map<String,Object> getProgramVariables(){
		return variables;
	}
	
	protected Map<String,MyFunction> getProgramFunctions(){
		return functions;
	}
	
	protected List<Object> getPrintOuts(){
		return print_outs;
	}
	protected double getTimeLeft(){
		return time_left;
	}
	
	protected SourceLocation getSourceLocation(){
		return this.location;
	}
	
	protected boolean getMayExecute(){
		return mayExecute;
	}
	/// SETTERS ///
	
	public void setProgramShip(Ship ship){
		this.ship = ship;
	}
	
	/// ADDERS ///
	protected void addVariable(String string, Object object){
		variables.put(string, object);
	}
	protected void addPrintOut(Object object){
		print_outs.add(object);
	}
	protected void addTime(double dt){
		time_left += dt;
	}
	
	protected void setFunctions(List<MyFunction> functions){
		for (MyFunction function:functions){
			this.functions.put(function.getFunctionName(), function);
			function.setFunctionProgram(this);
		}
	}
	
	protected void setSourceLocation(SourceLocation location){
		this.location = location;
	}
	protected void setMayExecute(){
		mayExecute = true;
	}
	
	protected void setMain(MyStatement main){
		this.main = main;
	}
	/// CONNECTIONS WITH OTHER CLASSES ///
	
	private Ship ship = null;
	private Map<String,MyFunction> functions = new HashMap<String,MyFunction>();
	private Map<String, Object> variables = new HashMap<String, Object>();
	private List<Object> print_outs = new ArrayList<Object>();
	
}
