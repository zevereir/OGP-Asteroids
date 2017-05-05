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
	
	protected Map<String,Object> getFunctionParameters(){
		return parameters;
	}
	
	/// GETTERS ///
	protected String getFunctionName(){
		return functionName;
	}
	protected MyStatement getFunctionBody(){
		return body;
	}
	
	/// EVALUATE ///
	protected Object evaluateBody(List<MyExpression> actualArgs){
		if (getFunctionBody() instanceof ReturnStatement)
			return ((ReturnStatement)getFunctionBody()).evaluateInFunction();
		
		else if (getFunctionBody() instanceof IfElseStatement)
			return ((IfElseStatement)getFunctionBody()).evaluateInFunction();
		
		// NOT POSSIBLE
		else 
			return null;
	}
	
	/// ADDERS ///
	
	protected void addParameter(String parameterName,Object parameter){
		parameters.put(parameterName, parameter);
	}
	

	protected void setBody(MyStatement body){
		this.body = body;
	}
	
	protected void setFunctionName(String functionName){
		this.functionName = functionName;
	}
	
	
	/// CONNECTIONS WITH OTHER CLASSES ///
	
	private String functionName;
	private MyStatement body;
	private Map<String,Object> parameters = new HashMap<String,Object>();
	
}
