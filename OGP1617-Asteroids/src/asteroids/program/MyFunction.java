package asteroids.program;

import java.util.HashMap;
import java.util.Map;

import asteroids.part3.programs.SourceLocation;

public class MyFunction {
	
	protected Map<String,Object> getFunctionParameters(){
		return parameters;
	}
	
	protected void addParameter(String parameterName,Object parameter){
		parameters.put(parameterName, parameter);
	}
	
	private Map<String,Object> parameters = new HashMap<String,Object>();
	
}
