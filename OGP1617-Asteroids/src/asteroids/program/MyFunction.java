package asteroids.program;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyFunction {
	
	/// CONSTRUCTOR ///
	
	public MyFunction(String functionName, MyStatement body){
		setFunctionName(functionName);
		setBody(body);
	}
	

	
	/// GETTERS ///
	protected String getFunctionName(){
		return functionName;
	}
	protected MyStatement getFunctionBody(){
		return body;
	}
	
	protected Program getFunctionProgram(){
		return this.program;
	}
	
	protected Map<String,Object> getFunctionLocalVariables(){
		return local_variables;
	}
	
	/// ADDERS ///
	
	protected void addLocalVariable(String localVariableName,Object localVariable){
		local_variables.put(localVariableName, localVariable);
	}


	protected void setBody(MyStatement body){
		this.body = body;
	}
	
	protected void setFunctionName(String functionName){
		this.functionName = functionName;
	}
	
	protected void setFunctionProgram(Program program){
		this.program = program;
	}
	
	/// CONNECTIONS WITH OTHER CLASSES ///
	private Program program;
	private String functionName;
	private MyStatement body;
	private Map<String,Object> local_variables = new HashMap<String,Object>();
	
}
