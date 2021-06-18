package phoneBook2;

public class Contact {
	String firstName;
	String lastName;
	String streetAddr;
	String city;
	String state;
	String zipCode;
	String phoneNumber;
	public Contact(String firstName, String lastName, String streetAddr, String city, String state, String zipCode, String phoneNumber){
		setFirstName(firstName);
		setLastName(lastName);
		setStreetAddr(streetAddr);
		setCity(city);
		setState(state);
		setZipCode(zipCode);
		setPhoneNumber(phoneNumber);
	}
	@Override
	public String toString() {
		return "first name ---- : " + firstName +"\n"+ ", last name ---- : " + lastName +"\n"+ ", Address ---- : " + streetAddr + "\n"+", city ---- : "
				+ city +"\n"+ ", state ---- : " + state + "\n"+", zipCode ---- : " + zipCode + "\n"+", phoneNumber ---- : " + phoneNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = capitalizeFirst(firstName);
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = capitalizeFirst(lastName);
	}
	public String getStreetAddr() {
		return streetAddr;
	}
	public void setStreetAddr(String streetAddr) {
		this.streetAddr = streetAddr;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = capitalizeFirst(city);
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public static String capitalizeFirst(String string) {
		String first = string.substring(0,1).toUpperCase();
		String last = string.substring(1, string.length());
		return first+last;
	}
}
