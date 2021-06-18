package phoneBook;

import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;

import java.util.*;

public class PhoneBook {
	 
	public static void Menu() throws LineUnavailableException, InterruptedException {
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
			System.out.println("11  Update entry");
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
				//creates a person object using last name and first name entered
				Person person = new Person(capitalizeFirst(lastName.trim()), capitalizeFirst(firstName.trim()));
				//requests address details
				System.out.println("Enter entry's street address: ");
				in.nextLine();
				String streetIn = in.nextLine();
				String[] stAdd = streetIn.split(" ");
				String street = "";
				for(String st: stAdd) {
					if(!st.matches("(0|[1-9]\\d*)"))
						street+=capitalizeFirst(st)+" ";
					else 
						street+=st+" ";
				}
				System.out.println("Enter entry's city: ");
				String city = in.nextLine();
				String[] ctAdd = city.split(" ");
				String ct = "";
				for(String c: ctAdd) {
					if(!c.matches("(0|[1-9]\\d*)"))
						ct+=capitalizeFirst(c)+" ";
					else 
						ct+=c+" ";
				}
				System.out.println("Enter entry's state: ");
				String state = in.nextLine();
				state = stateChange(state);
//				if(stateCheck(state) == false){System.out.println("invalid input"); break;}//verifies state syntax is correct
				System.out.print("Enter entry's zip code: ");
				String zip = in.next();
				zip = zip.trim();
				if(zipCheck(zip) == false) {System.out.println("invalid input"); break;}//verifies zip syntax is correct
				System.out.println("Enter entry's phone number: ");
				String phone = in.next();
				phone = phone.trim();
				if(phoneCheck(phone) == false){System.out.println("invalid input"); break;}//verifies phone is correct syntax
				
				//creates a new address using the address details entered
				Address address = new Address(street.trim(),(ct.trim()), state.toUpperCase(), zip, phone);
				Entry entry = new Entry(person, address);
				break;
				
				
			case 2://creates an entry in phonebook from one continuous string
				System.out.print("Enter complete info separated by a comma(last name, first name, street addr., city, state, zip, phone number: ");
				in.nextLine();
				String completeInfo = in.nextLine();
				//splits continuous string into parts 
				String[] infoArray = completeInfo.split(",");
				//separates first string, and last string from the first element in the array
				String fn = ((String)infoArray[0]).substring(0,((String)infoArray[0]).lastIndexOf(" "));
				String ln = ((String)infoArray[0]).substring(((String)infoArray[0]).lastIndexOf(" "),((String)infoArray[0]).length());
				String[] fullName = {ln,fn};
				String streetAdd = infoArray[1].trim();
				stAdd = streetAdd.split(" ");
				street = "";
				for(String st: stAdd) {
					if(!st.matches("(0|[1-9]\\d*)"))
						street+=capitalizeFirst(st)+" ";
					else 
						street+=st+" ";
				}
				city = infoArray[2];
				ctAdd = city.trim().split(" ");
				ct = "";
				for(String c: ctAdd) {
					if(!c.matches("(0|[1-9]\\d*)"))
						ct+=capitalizeFirst(c)+" ";
					else 
						ct+=c+" ";
				}
				person = new Person(capitalizeFirst(fullName[0].trim()),capitalizeFirst(fullName[1].trim()));
				address = new Address(street,ct.trim(),stateChange(infoArray[3].trim()),infoArray[4].trim(),infoArray[5].trim());
				entry = new Entry(person, address);
 				break;
 				
 //the following cases all use the same method .getEntryByParam(S,S) which takes a string to match(st), and a string that defines the method to be used(method) 
			case 3:	//requests a first name to compare while searching all the entries for matches
				System.out.println("Enter first name: ");
				firstName = in.next();
				String st = firstName.trim();
				Entry.getEntryByParam(st, "byFirst");
				break;
				
			case 4://request last name to compare while searching all entries for matches
				System.out.println("Enter last name: ");
				lastName = in.next();
				st = (lastName.trim());
				Entry.getEntryByParam(st, "byLast"); 		
				break;
				
			case 5://request first and last name to find matches
				System.out.println("Enter first name: ");
				firstName = in.next();
				System.out.println("Enter last name: ");
				lastName = in.next();
				st = (firstName.trim()+" "+lastName.trim());
				Entry.getEntryByParam(st, "fullName");
				break;
				
			case 6://requests phone number to find matches
				System.out.print("Enter phone number without spaces: ");
				String num = in.next();
				st = num.trim();
				if(phoneCheck(st))Entry.getEntryByParam(st, "byNumber");
				else System.out.println("Invalid Entry");
				break;
				
			case 7:
				System.out.print("Enter city here: ");
				in.nextLine();
				String mycity = in.nextLine();
				ctAdd = mycity.split(" ");
				ct = "";
				for(String c: ctAdd) {
					if(!c.matches("(0|[1-9]\\d*)"))
						ct+=capitalizeFirst(c)+" ";
					else 
						ct+=c+" ";
				}
				Entry.getEntryByParam(ct.trim(), "byCity");
				break;
				
			case 8:
				System.out.print("Enter state:  ");
				state = in.next();
				st = PhoneBook.stateChange(state.trim());
				Entry.getEntryByParam(st, "byState");
				break;
				
			case 9:
				System.out.print("Enter zip code: ");
				zip = in.next();
				st = zip.trim();
				if(zipCheck(st))Entry.getEntryByParam(st, "byZip");
				else System.out.println("Invalid Zip code");
				break;
				
			case 10:
				Entry.showEntries(1);
				break;
			
			case 11:
				System.out.println();
				break;
				
			case 12: 
				Entry.showEntries(0);
				System.out.println("Enter contact ID number to delete: ");
				int id = in.nextInt();
				Entry.deleteEntry(id-1);
				System.out.println();
				break; 
				
			case 13:
				in.close();
				System.out.print("Exiting");
				for(int i = 0; i <= 30; i++) {
					System.out.print(".");
					try {
					Thread.sleep(50);
					}catch (InterruptedException ie) {
		                ie.printStackTrace();
		            }
				}
				System.out.print("GoodBye!");
				run = false;
				break;
			default:
				System.out.println("Invalid entry. Choose from menu");
				Menu();
			}
		}

	}
	//verifies phone is correct length and digit
	public static boolean phoneCheck(String number) {
		return (number.length()==10 && number.matches("(0|[1-9]\\d*)"));
	}
	//verifies zip code is digits and length(5)
	public static boolean zipCheck(String number) {
		return (number.length()==5 && number.matches("(0|[1-9]\\d*)"));
	}
	//veirfies state is valid
	public static boolean stateCheck(String state) {
		String[] fullStates = {"Alabama", "Alaska", "American Samoa", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "District of Columbia", "Florida", "Georgia", "Guam", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Minor Outlying Islands", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Northern Mariana Islands", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Puerto Rico", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "U.S. Virgin Islands", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
		String[] states = {"AK", "AL", "AR", "AS", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "GU", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MP", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UM", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY"};
		for(int i = 0; i < states.length; i++) {
			if(states[i].equalsIgnoreCase(state.toUpperCase()) || fullStates[i].equalsIgnoreCase(state.toUpperCase())){
				return true;
			}
		}
		return false;
	}
	//changes entries to two letter abbreviation as long as input is a valid form of state
	public static String stateChange(String state) {
		String st = state;
		String[] fullStates = {"Alabama", "Alaska", "American Samoa", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "District of Columbia", "Florida", "Georgia", "Guam", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Northern Mariana Islands", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Puerto Rico", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
		String[] states = {"AL", "AK", "AS", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL", "GA", "GU", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK", "OR", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
		for(int i = 0; i < states.length; i++) {
			if(fullStates[i].equalsIgnoreCase(state)){
				return  states[i].toUpperCase();
			}
		}
		return st.toUpperCase();
	}
	//capitalizes first letter ---applied to street address, city, name etc.
	public static String capitalizeFirst(String string) {
		String first = string.substring(0,1).toUpperCase();
		String last = string.substring(1, string.length());
		return first+last;
	}
	
}


