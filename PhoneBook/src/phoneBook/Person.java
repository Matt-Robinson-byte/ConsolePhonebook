package phoneBook;

public class Person extends PhoneBook {
	private String lastName;
	private String firstName;
	public Person(String lastName, String firstName) {
		setLastName(lastName);
		setFirstName(firstName);
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
