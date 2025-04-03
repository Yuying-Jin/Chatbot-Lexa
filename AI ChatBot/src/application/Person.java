package application;

public class Person {

	private String name;
	private String password;
	
	public Person(String name, String pwd) {
		this.name = name;
		this.password = pwd;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
