package phms.main;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
//import phms.model.*;
//import phms.dao.*;
import java.sql.*;
/**
* Personal HealthManagement Database Application for CSC 540
* Authors: Webb Chawla, Zachary Delong, Akshit Patel, and Kush Mishra
*
*/
public class PersonalHealthManagementDatabaseApplication {
	static Scanner console;
	static PHMSDao dao;

	public static void main (String[] args) {
		//SETUP DB CONNECTION HERE
		dao = new PHMSDao();
		
		//System.out.println(dao.test());
		console = new Scanner (System.in);
		startMenu();
	}

	private static void menuUI(){
		System.out.println("Start Menu");
		System.out.println("----------");
		System.out.println("1. Login as Patient");
		System.out.println("2. Login as Health Supporter");
		System.out.println("3. Signup");
		System.out.println("4. Exit");
		System.out.println("5. Implemented Queries");
		System.out.println("----------");
		//extra space
		System.out.println();
	}
	
	private static void startMenu(){
		menuUI();
		boolean invalid = true;
		do{
			invalid = true;
			int input;
			try{
				input = Integer.parseInt(console.nextLine());
			} catch (NumberFormatException e){
				input = 0;
			}
			switch (input){
				case 1:
					//sign in as patient
					Patient user = loginUI();
					if (user == null){
						System.out.println("Login Incorrect");
						menuUI();
						invalid = false;
					} else if (user.isSick() == 1) {
						//check if user has at least 1 HS
						patientMenu(user);
					} else {
						patientMenu(user);	
					}
					break;
				case 2:
					//sign in as health supporter
					HealthSupporter hsuser = loginHSUI();
					if (hsuser == null){
						System.out.println("Login Incorrect");
						menuUI();
						invalid = false;
					} else {
						hsMenu(hsuser);	
					}
					break;
				case 3:
					signUp();
					break;
				case 4:
					System.exit(0);
				case 5:
					queries();
					break;
				case 0:
					System.out.println("Invalid input, try again!!!!!");
					menuUI();
					invalid = false;
					break;
			}

		}while(!invalid);
	}

	private static void queries() {
		System.out.println();
		System.out.println("Report Queries");
		System.out.println("----------");
		System.out.println("1. Health Supps Auth in Sept 2016 by patients w/ Heart Disease");
		System.out.println("2. Num patients not complying w/ recomended freq of observations");
		System.out.println("3. List Health Supporters who are also patients");
		System.out.println("4. List Patients who are not sick");
		System.out.println("5. How many patients have different observation time and recording time (of the observation).");
		System.out.println("----------");
		System.out.println();
	}

	private static void signUp() {
		System.out.println("New Patient Menu");
		System.out.println("----------");
		//Patient p = new Patient();
		System.out.println("Enter SSN: ");
		long ssn = Long.parseLong(console.nextLine());
		
		System.out.println("Enter First Name: ");
		String fname = console.nextLine();
		System.out.println("Enter Last Name: ");
		String lname = console.nextLine();
		
		System.out.println("Enter DOB Month (1-12): ");
		int month = Integer.parseInt(console.nextLine());
		System.out.println("Enter DOB Day: ");
		int day = Integer.parseInt(console.nextLine());
		System.out.println("Enter DOB Year: ");
		int year = Integer.parseInt(console.nextLine());
		
		System.out.println("Enter Address: ");
		String address = console.nextLine();
		System.out.println("Enter PhoneNumber: ");
		String phone = console.nextLine();
		System.out.println("Enter Sex (m/f): ");
		String sex = console.nextLine();
		System.out.println("Enter Password: ");
		String pass = console.nextLine();
		
		System.out.println("Sick (Y/N)? ");
		String yn = console.nextLine();
		
		while(!yn.equalsIgnoreCase("y") && !yn.equalsIgnoreCase("n")){
			System.out.println("Enter Y or N? ");
			yn = console.nextLine();
		}
		
		Patient p = new Patient();
		p.setSsn(ssn);
		p.setFname(fname);
		p.setLname(lname);
		
		
		String date = year + "-" + month + "-" + day;
		java.sql.Date dat = java.sql.Date.valueOf(date);
		p.setDOB(dat);
		
		p.setAddress(address);
		p.setPhoneNum(phone);
		p.setSex(sex);
		p.setPassword(pass);
		if (yn.equalsIgnoreCase("y")){
			p.setSick(1);
		} else if (yn.equalsIgnoreCase("n")){
			p.setSick(0);
		}
		
		dao.addNewPatient(p);
		
		System.out.println("New Patient Added!!");
		startMenu();
	}
	
	private static void hsUI(){
		System.out.println("Logged in as Health Supporter");
		System.out.println("---------------------");
		System.out.println("1. Profile");
		System.out.println("2. Diagnoses");
		System.out.println("3. Health Indicator");
		System.out.println("4. Alerts");
		System.out.println("5. Health Supporters");
		System.out.println("6. Logout");
		System.out.println("---------------------");
		System.out.println();
	}
	
	private static void hsMenu(HealthSupporter p){
		hsUI();
		boolean invalid;
		do{
			invalid = true;
			int input;
			try{
				input = Integer.parseInt(console.nextLine());
			} catch (NumberFormatException e){
				input = 0;
			}
			switch (input){
				case 1:

					break;
				case 2:

					break;
				case 3:

					break;
				case 4:

					break;
				case 5:

					break;
				case 6:
					startMenu();
					break;
				case 0:
					System.out.println("Invalid input, try again!!!!!");
					hsUI();
					invalid = false;
					break;
			}
		} while(!invalid);
		
	}

	private static void patientUI(){
		System.out.println("Logged in as Patient");
		System.out.println("---------------------");
		System.out.println("1. Profile");
		System.out.println("2. Diagnoses");
		System.out.println("3. Health Indicator");
		System.out.println("4. Alerts");
		System.out.println("5. Health Supporters");
		System.out.println("6. Logout");
		System.out.println("---------------------");
		System.out.println();
	}
	
	private static void patientMenu(Patient p){
		patientUI();
		boolean invalid;
		do{
			invalid = true;
			int input;
			try{
				input = Integer.parseInt(console.nextLine());
			} catch (NumberFormatException e){
				input = 0;
			}
			switch (input){
				case 1:
					showProfile(p);
					break;
				case 2:
					diseaseMenu(p);
					break;
				case 3:
					viewHOs(p);
					break;
				case 4:

					break;
				case 5:
					patientViewsHS(p);
					break;
				case 6:
					startMenu();
					break;
				case 0:
					System.out.println("Invalid input, try again!!!!!");
					patientUI();
					invalid = false;
					break;
			}
		} while(!invalid);
		
	}
	private static void viewHOs(Patient p){
		
	}
	private static void patientViewsHS(Patient p){
		boolean keep = true;
		while(keep){
			System.out.println("---------------------");
			System.out.println("1. View Health Supporters");
			System.out.println("2. Edit Health Supporters ");
			System.out.println("3. Add Health Supporters ");
			System.out.println("4. Remove Health Supporters ");
			System.out.println("5. Back to Patient Menu ");
			System.out.println("---------------------");
			int input;
			try{
				input = Integer.parseInt(console.nextLine());
			} catch (NumberFormatException e){
				input = 0;
			}
			switch(input){
				case 0:
					System.out.println("Invalid input, back to Patient Menu!!!!!");
					keep = false;
					break;
				case 1:
					viewHS(p);
					break;
				case 2:
					editHS(p);		
					break;
				case 3:
					addHS(p);
					break;
				case 4:
					removeHS(p);
					break;
				case 5:
					keep = false;
					break;
			}
		}
		patientMenu(p);
	}
	
	private static void viewHS(Patient p){
		System.out.println("List of all Health Supporters");
		System.out.println("---------------------");
		ArrayList<HealthSupporter> hs = dao.getPatientsHS(p);
		for(int i = 0; i < hs.size(); i++){
			HealthSupporter h = hs.get(i);
			System.out.println((i+1) + ": " + h.getFname() + " " + h.getLname());
		}
	}
	private static void editHS(Patient p){
		System.out.println("Select a Health Supporters to Edit");
		System.out.println("---------------------");
		ArrayList<HealthSupporter> hs = dao.getPatientsHS(p);
		for(int i = 0; i < hs.size(); i++){
			HealthSupporter h = hs.get(i);
			System.out.println((i+1) + ": " + h.getFname() + " " + h.getLname());
		}
	}
	private static void addHS(Patient p){
		System.out.println("Select a Health Supporters to Add");
		System.out.println("---------------------");
	}
	
	
	private static void removeHS(Patient p){
		System.out.println("Select a Health Supporters to Remove");
		System.out.println("---------------------");
		ArrayList<HealthSupporter> hs = dao.getPatientsHS(p);
		for(int i = 0; i < hs.size(); i++){
			HealthSupporter h = hs.get(i);
			System.out.println((i+1) + ": " + h.getFname() + " " + h.getLname());
		}
		
		int selection = Integer.parseInt(console.nextLine());
		while(selection - 1 >= hs.size()){
			System.out.println("Invalid seletion, choose again");
			selection = Integer.parseInt(console.nextLine());
		}
		selection--;
		HealthSupporter h = hs.get(selection);
		if (dao.removeHSForPatient(p.getSsn(), h)){
			System.out.println("Success, HS removed!");
		};
		System.out.println("---------------------");
	}
	
	private static void diseaseMenu(Patient p){
		boolean keep = true;
		while(keep){
			System.out.println("Make a selection");
			System.out.println("---------------------");
			System.out.println("1. View Diseases");
			System.out.println("2. Add Disease");
			System.out.println("3. Remove Disease");
			System.out.println("4. Back To Patient Menu");
		
			int input;
			try{
				input = Integer.parseInt(console.nextLine());
			} catch (NumberFormatException e){
				input = 0;
			}
			switch(input){
				case 0:
					System.out.println("Invalid input, back to Patient Menu!!!!!");
					keep = false;
					break;
				case 1:
					showPatientDiseases(p);
					break;
				case 2:
					addDisease(p);
					break;
				case 3:
					removeDisease(p);
					break;
				case 4:
					keep = false;
					break;
			}
		}
		patientMenu(p);
		
	}
	private static void addDisease(Patient p){
		ArrayList<String> allDiseases = dao.getAllDiseases();
		int size = allDiseases.size();
		System.out.println("Select A Disease to Add");
		System.out.println("---------------------");
		for (int i = 0; i < size; i++) {
			System.out.println((i+1) + ": "+ allDiseases.get(i));
		}
		System.out.println("---------------------");
		int selection = Integer.parseInt(console.nextLine());
		while(selection - 1 >= size){
			System.out.println("Invalid seletion, choose again");
			selection = Integer.parseInt(console.nextLine());
		}
		selection--;
		String dis = allDiseases.get(selection);
		if(dao.addDiseaseForPatient(p.getSsn(), dis)){
			p.setSick(1);
		}
	}

	private static void removeDisease(Patient p){
		ArrayList<String> patientsDiseases = dao.getPatientsDiseases(p.getSsn());
		System.out.println("Select number for the disease to remove");
		System.out.println("---------------------");
		int s = patientsDiseases.size();
		for (int i = 0; i < s; i++) {
			System.out.println((i+1) + ": "+ patientsDiseases.get(i));
		}
		
		int selection = Integer.parseInt(console.nextLine());
		while(selection - 1 >= s){
			System.out.println("Invalid seletion, choose again");
			selection = Integer.parseInt(console.nextLine());
		}
		selection--;
		String dis = patientsDiseases.get(selection);
		dao.removeDiseaseForPatient(p.getSsn(),dis);
		System.out.println("---------------------");
	}
	
	private static void showPatientDiseases(Patient p){
		if (p.isSick() == 1){
			ArrayList<String> patientsDiseases = dao.getPatientsDiseases(p.getSsn());
			System.out.println(p.getFname() + " " + p.getLname() + "'s Diseases!");
			System.out.println("---------------------");
			int s = patientsDiseases.size();
			if (s == 0){
				System.out.println("No Diseases!");
			} else {
				for (int i = 0; i < s; i++) {
					System.out.println((i+1) + ": "+ patientsDiseases.get(i));
				}
			}
			
			System.out.println("---------------------");
		} else {
			System.out.println(p.getFname() + " " + p.getLname() + " is Well, so no diseases!");
			System.out.println("---------------------");
		}
	}
	
	private static void showProfile(Patient p){
		System.out.println(p.getFname() + " " + p.getLname() + "'s Profile Info");
		System.out.println("---------------------");
		System.out.println("SSN: " + p.getSsn());
		System.out.println("Name: " + p.getFname() + " " + p.getLname());
		java.sql.Date DOB = p.getDOB();
		System.out.println("DOB: " + DOB.toString());
		System.out.println("Address: " + p.getAddress());
		System.out.println("Phone: " + p.getPhoneNum());
		System.out.println("Sex: " + p.getSex());
		
		System.out.println();
		System.out.println("Edit Info? (Y/N)");
		String input = console.nextLine();
		if (input.equalsIgnoreCase("y")){
			editPatientInfo(p);
		} else if (input.equalsIgnoreCase("n")){
			patientMenu(p);
		} else {
			System.out.println("Invalid Input, back to Patient Menu");
			patientMenu(p);
		}
	}
	
	private static void editPatientUI(){
		System.out.println("Edit Patient");
		System.out.println("---------------------");
		System.out.println("1. Set First Name");
		System.out.println("2. Set Last Name");
		System.out.println("3. Set Date Of Birth");
		System.out.println("4. Set Address");
		System.out.println("5. Set Phone Number");
		System.out.println("6. Set Sex");
		System.out.println("7. Set Password");
		System.out.println("8. Set Sick");
		System.out.println("9. Save Changes");
		System.out.println("10. Exit/Leave Changes");
		System.out.println("---------------------");
		System.out.println();
	}
	
	private static void editPatientInfo(Patient p){
		//edit patient info
		boolean stay;
		Patient newP = p;
		do{
			editPatientUI();
			stay = true;
			int input;
			try{
				input = Integer.parseInt(console.nextLine());
			} catch (NumberFormatException e){
				input = 0;
			}
			switch (input){
				case 1:
					System.out.println("Enter new First Name");
					String fname = console.nextLine();
					newP.setFname(fname);
					break;
				case 2:
					System.out.println("Enter new Last Name");
					String lname = console.nextLine();
					newP.setLname(lname);
					break;
				case 3:
					System.out.println("Enter DOB Month (1-12): ");
					int month = Integer.parseInt(console.nextLine());
					System.out.println("Enter DOB Day: ");
					int day = Integer.parseInt(console.nextLine());
					System.out.println("Enter DOB Year: ");
					int year = Integer.parseInt(console.nextLine());
					
					String date = year + "-" + month + "-" + day;
					java.sql.Date dat = java.sql.Date.valueOf(date);
					newP.setDOB(dat);
					break;
				case 4:
					System.out.println("Enter new Address");
					String address = console.nextLine();
					newP.setAddress(address);
					break;
				case 5:
					System.out.println("Enter new Phone Num");
					String phone = console.nextLine();
					newP.setPhoneNum(phone);
					break;
				case 6:
					System.out.println("Enter new Sex (m/f)");
					String sex = console.nextLine();
					newP.setSex(sex);
					break;
				case 7:
					System.out.println("Enter new Password");
					String password = console.nextLine();
					newP.setPassword(password);
					break;
				case 8:
					System.out.println("Sick (Y/N)? ");
					String yn = console.nextLine();
					
					while(!yn.equalsIgnoreCase("y") && !yn.equalsIgnoreCase("n")){
						System.out.println("Enter Y or N? ");
						yn = console.nextLine();
					}
					if (yn.equalsIgnoreCase("y")){
						newP.setSick(1);
					} else if (yn.equalsIgnoreCase("n")){
						newP.setSick(0);
					}
					break;
				case 9:
					dao.editPatient(newP);
					stay = false;
					System.out.println("Patient Updated!");
					patientMenu(newP);
					break;
				case 10:
					stay = false;
					System.out.println("Patient Not Updated!");
					patientMenu(p);
					break;
				case 0:
					System.out.println("Invalid input, try again!!!!!");
					break;
			}
		} while(stay);
	}

	private static HealthSupporter loginHSUI(){
		System.out.println("Health Supporter Login Menu");
		System.out.println("----------");
		System.out.println("Enter SSN");
		long user = Long.parseLong(console.nextLine());

		System.out.println("Enter Password");
		String pass = console.nextLine();
		System.out.println();
		HealthSupporter p;
		p = loginHSAction(user,pass);
		return p;
	}
	
	private static HealthSupporter loginHSAction(long user, String pass){
		return dao.healthSupporterLogin(user,pass);
	}
	
	private static Patient loginUI(){
		System.out.println("Patient Login Menu");
		System.out.println("----------");
		System.out.println("Enter SSN");
		long user = Long.parseLong(console.nextLine());

		System.out.println("Enter Password");
		String pass = console.nextLine();
		System.out.println();
		Patient p;
		p = loginAction(user,pass);
		return p;
//		do{
//			p = loginAction(user,pass);
//			if (p == null) {
//				System.out.println("Login Incorrect");
//				System.out.println("Enter UID");
//				user = console.nextLine();
//
//				System.out.println("Enter Password");
//				pass = console.nextLine();
//			}
//		}while(p == null);
//		return p;
	}

	private static Patient loginAction(long user, String pass){
		return dao.patientLogin(user,pass);
	}




}