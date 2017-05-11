package asteroids.program;

public abstract class Name extends MyExpression {

	/// CONSTRUCTOR ///

	public Name(String name) {
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
