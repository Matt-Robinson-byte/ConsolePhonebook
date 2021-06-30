package phoneBook;

import java.awt.Toolkit;
import java.io.File;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import java.util.*;

//import java.lang.reflect.Array;

public class Entry{
	
	public static Object[] addressArray = new Object[100];
	public static Object[] personArray = new Object[100];
	private Person person;
	private Address address;
	

	
	public Entry(Person person, Address address) {
		this.person = person;
		this.address = address;
		setEntry(this.person,this.address);
	}
	
	public void setEntry(Person person, Address address) {
		this.person = person;
		this.address = address;
		for (int i = 0; i < personArray.length; i++) {
			if(personArray[i] == null) {
			personArray[i] = ((Object)this.person);
			addressArray[i] = ((Object)this.address);
			break;
			}
		}
	} 
	
	public static void deleteEntry(int i) {
		personArray [i] = null;
		addressArray[i] = null;
	}
	public static void getEntryByParam(String param, String method) throws LineUnavailableException, InterruptedException {
		String findBy="";
		String st="";
		String res = "";
		//Timed printout of dots---just for fun
		System.out.println("Searching");
		for(int i = 0; i <= 20; i++) {
			System.out.print(".");
			try {
			Thread.sleep(50);
			}catch (InterruptedException ie) {
                ie.printStackTrace();
            }
		}System.out.println("Matches: ");
		
		for(int i = 0; i<personArray.length;i++) {
			if((((Person)personArray[i])!=null )) {
				Person t = ((Person) personArray[i]);
				Address a = ((Address) addressArray[i]);
				switch (method){
				
					case "byLast":
						findBy = (String) t.getLastName();
						st = findBy.trim();
						break;
						
					case "byFirst":
						findBy = (String) t.getFirstName();
						st = findBy.trim();
						break;
						
					case "fullName":
						String[] fullName = param.split(" ");
						String fn =(String) t.getFirstName().trim();
						String ln = (String) t.getLastName().trim();
						if(fn.equalsIgnoreCase(fullName[0]) && ln.equalsIgnoreCase(fullName[1])) {
							res += ("\nContact ID#-- "+ (i+1) +","+ "Last name-: "+t.getLastName() + ", "+"First name: "+ t.getFirstName() +", " +"Address---: "+a.getStreet()+", "+"City------: "+a.getCity()+", "+"State-----: "+a.getState()+", "+"Zip code--: "+a.getZip()+", "+"Tel-------: "+a.getPhone()+"\n");
							
						}
						
						
					case "byCity":
						findBy = (String) a.getCity();
						st = findBy.trim();
						break;
						
					case "byState":
						findBy = (String) a.getState();
						st = findBy.trim();
						
						break;
					
					case "byZip":
						findBy = (String) a.getZip();
						st = findBy.trim();
						break;
						
					case "byNumber":
						findBy = (String) a.getPhone();
						st = findBy.trim();
						break;
						
					default:
						System.out.println("No worky worky");
				}
				if(!method.equals("fullName") && st.equalsIgnoreCase(param)) {
					res += ("\nContact ID#-- "+ (i+1) +","+ "Last name-: "+t.getLastName() + ", "+"First name: "+ t.getFirstName() +", " +"Address---: "+a.getStreet()+", "+"City------: "+a.getCity()+", "+"State-----: "+a.getState()+", "+"Zip code--: "+a.getZip()+", "+"Tel-------: "+a.getPhone()+"\n");
					
				}
			}
		}retroDisplay(res,1);
		
	}
	
//change to return String
	public static void showEntries(int from) throws LineUnavailableException, InterruptedException {
		String res = "";
		for(int i = 0; i < personArray.length; i++) {
			if((((Person)personArray[i])!=null && ((Address)addressArray[i])!=null)) {
				Person t = ((Person) personArray[i]);
				Address a = ((Address) addressArray[i]);
				res += ("\nContact ID#-- "+ (i+1) +","+ "Last name-: "+t.getLastName() + ", "+"First name: "+ t.getFirstName() +", " +"Address---: "+a.getStreet()+", "+"City------: "+a.getCity()+", "+"State-----: "+a.getState()+", "+"Zip code--: "+a.getZip()+", "+"Tel-------: "+a.getPhone()+"\n");
			}
		}
		//return res;
		retroDisplay(res,from);
	}
	
	public static void retroDisplay(String res, int from) throws LineUnavailableException, InterruptedException {
		
		Scanner in = new Scanner(System.in);
		String[] strArr = res.split(",");
		for(String st: strArr) {
			String[] letterArr = st.trim().split("");
			for(String letter: letterArr) {
				if(!letter.equals(" ")) {
					//play();
					System.out.print(letter);
					Thread.sleep(15);
				
				}else {
					System.out.print(letter);
					Thread.sleep(15);
				}
			}
			System.out.println();
			try {
			Thread.sleep(100);
			}catch (InterruptedException ie) {
                ie.printStackTrace();
            }
		}
		if(from > 0) {
			System.out.println();
			System.out.print("Enter any key and press ENTER to return to MENU: ");
			String x = in.next();
			System.out.println();
		}
	}
	
	public static void play(int reps) {
		String filename = "C:\\Users\\Matt's PC\\eclipse-newWorkspace\\PhoneBook\\src\\sound.wav"; // fill in file name here

		int EXTERNAL_BUFFER_SIZE = 500000;

		File soundFile = new File(filename);

		if (!soundFile.exists()) {
			System.err.println("Wave file not found: " + filename);
			return;
		}

		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		AudioFormat format = audioInputStream.getFormat();

		SourceDataLine auline = null;

//Describe a desired line
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);

			// Opens the line with the specified format,
			// causing the line to acquire any required
			// system resources and become operational.
			auline.open(format);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		// Allows a line to engage in data I/O
		auline.start();

		int nBytesRead = 0;
		byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

		try {
			
				while (nBytesRead != -1) {
					nBytesRead = audioInputStream.read(abData, 0, abData.length);
					if (nBytesRead >= 0) {
						// Writes audio data to the mixer via this source data line
						// NOTE : A mixer is an audio device with one or more lines
						auline.write(abData, 0, nBytesRead);
					}
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} finally {
			// Drains queued data from the line
			// by continuing data I/O until the
			// data line's internal buffer has been emptied
			auline.drain();

			// Closes the line, indicating that any system
			// resources in use by the line can be released
			auline.close();
		}
	}
	
	
}










