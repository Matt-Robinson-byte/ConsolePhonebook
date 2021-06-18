package phoneBook2;

import java.util.Arrays;

public class Actions {
	public static Contact[] contactsArray = new Contact[100];
	public static String[] sortedArray = new String[100];
	public static void addContact(Contact contact) {
		for(int i = 0; i < contactsArray.length; i++) {
			if(contactsArray[i]==null) {
				contactsArray[i] = contact;
				break;
			}
		}
	}
	
	public static void displayContacts() {
		String res = "";
		for(int i = 0; i < contactsArray.length; i++) {
			if(contactsArray[i]!=null) {
				res = contactsArray[i].toString()+"\n";
				sortedArray[i] = res;
			}
		}
		System.out.println(res);
	}
	public static void displaySorted() {
		Arrays.sort(sortedArray);
		for(String st:sortedArray) {
			System.out.println(st);
		}
	}
	
	public static void searchByOption(String searchParameter, String searchMethod) {
		String method = "";
		switch(searchParameter) {
			case "byFirst":
				method = "getFirstName()";
				break;
			case "byLast":
				break;
			case "byState":
				break;
			case "byCity":
				break;
			case "byZip":
				break;
			case "byTel":
				break;
		}
		for(int i = 0; i < contactsArray.length; i++) {
			if(method == "")System.out.println("hello");
		}
	}
	
	
}
