package asteroids.program;

public abstract class NameExpression extends MyExpression {

	/// CONSTRUCTOR ///

	public NameExpression(String name) {
		setName(name);
	}
	

	/// BASIC PROPERTIES ///
	
	private String name;

	
	/// GETTERS ///

	protected String getName() {
		return name;
	}

	
	/// SETTERS ///

	protected void setName(String name) throws IllegalArgumentException {
		this.name = name;
	}
	
}
