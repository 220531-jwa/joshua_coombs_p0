package dev.joshua_coombs.models;


public class Client {
	private int id;
	private String firstName;
	private String lastName;
	
	public Client() {
		super();
	}
	
	public Client(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getId() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Client [id=" + id +  ", first_name=" + firstName + ", last_name=" + lastName + "]";
	}
}
