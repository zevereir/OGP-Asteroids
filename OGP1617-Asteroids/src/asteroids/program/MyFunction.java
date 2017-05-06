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
	
//	protected Map<String,Object> getFunctionParameters(){
//		return parameters;
//	}
	
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
	
	/// ADDERS ///
	
//	protected void addParameter(String parameterName,Object parameter){
//		parameters.put(parameterName, parameter);
//	}
	

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
//	private Map<String,Object> parameters = new HashMap<String,Object>();
	
}
