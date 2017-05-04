package asteroids.program;

import java.util.HashMap;
import java.util.Map;

import asteroids.part3.programs.SourceLocation;

public class MyFunction {
	
	public MyFunction(String functionName, MyStatement body){
		setFunctionName(functionName);
		setBody(body);
	}
	
	protected Map<String,Object> getFunctionParameters(){
		return parameters;
	}
	
	protected void addParameter(String parameterName,Object parameter){
		parameters.put(parameterName, parameter);
	}
	protected void setBody(MyStatement body){
		this.body = body;
	}
	protected void setFunctionName(String functionName){
		this.functionName = functionName;
	}
	
	
	
	
	private String functionName;
	private MyStatement body;
	private Map<String,Object> parameters = new HashMap<String,Object>();
	
}
