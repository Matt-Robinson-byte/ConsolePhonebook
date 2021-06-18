package phoneBook2;

import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;

public class Menu {
	public static void Menu() {
		int function;
		Scanner in = new Scanner(System.in);
		boolean run = true;
		//displays menu in a while() loop which is terminated when boolean run is changed to false in case 11
		while(run) {
			System.out.println("*****************PHONEBOOK****************");
			System.out.println("1. Add new Entry");
			System.out.println("2. Add new Copy/Paste entry");
			System.out.println("3. Search by first name");
			System.out.println("4. Search by last name");
			System.out.println("5. Search by full name");
			System.out.println("6. Search by telephone number ");
			System.out.println("7. Search by city");
			System.out.println("8. Search by state");
			System.out.println("9. Search by zip code");
			System.out.println("10. Display complete phonebook");
			System.out.println("11  Display sorted alphatically");
			System.out.println("12  Delete entry");
			System.out.println("13. Exit");
			System.out.println("\n");
			System.out.println("Enter your option: ");
			String func = in.next();
			
			try {
				function = Integer.parseInt(func);
			}catch(NumberFormatException ei){
				function = 0;
			}
			switch (function) {
			//asks for details and creates a new entry
			case 1:
				//requests last name and first name to create a person
				System.out.print("Enter last name: ");
				in.nextLine();
				String lastName = in.nextLine();
				
				System.out.print("Enter first name: ");
				String firstName = in.next();
				
				System.out.println("Enter entry's street address: ");
				in.nextLine();
				String street= in.nextLine();
				
				System.out.println("Enter entry's city: ");
				String city = in.nextLine();
			
				
				System.out.println("Enter entry's state: ");
				String state = in.nextLine();
				
//				if(stateCheck(state) == false){System.out.println("invalid input"); break;}//verifies state syntax is correct
				System.out.print("Enter entry's zip code: ");
				String zip = in.next();
				zip = zip.trim();
				System.out.println("Enter entry's phone number: ");
				String phone = in.next();
				phone = phone.trim();
				Contact contact = new Contact(lastName, firstName, street, city, state, zip, phone);
				Actions.addContact(contact);
				break;
				
		
			case 10:
				Actions.displayContacts();
				break;
			
			case 11:
				Actions.displaySorted();
			default:
				System.out.println("Invalid entry. Choose from menu");
				Menu();
			}
		}
	}
}