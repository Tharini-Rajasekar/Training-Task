package test;

import java.util.List;
import java.util.Scanner;
import task.RegExTask;
import util.InvalidInputException;

public class RegExRunner {

	public static void main(String[] args) {

		Scanner scanner=new Scanner(System.in);
		RegExTask run =new RegExTask();
		int option=1;
		while(option>0) {
			System.out.print("Enter your Question number: ");
			option=scanner.nextInt();
			scanner.nextLine();
			switch(option) {
			 case(1):{
				System.out.print("Enter your Mobile number: ");
				String input=scanner.nextLine();
				try {
					if(run.checkMobileNum(input)) {
						System.out.println("Mobile Number validated");
					}
					else {
						System.out.println("oops! Incorrect Mobile Number");
					}
				}
				catch(InvalidInputException e ) {
					e.printStackTrace();
				}
				break;
			 }
			 case(2):{
				 System.out.print("Enter Input: ");
				 String input=scanner.nextLine();
				 try {
					 if(run.checkAlphaNumeric(input)) {
						 System.out.println("Yay! Input is Alpha Numeric");
					 }
					 else {
						 System.out.println("oops! Provide Alpha Numeric input");
					 }
				 }
				 catch(InvalidInputException e ) {
					 e.printStackTrace();
				 }
				 break;
			 }
			 case(3):{
				 System.out.print("Enter your Input String: ");
				 String input=scanner.nextLine();
				 System.out.print("Enter Matching String: ");
				 String check=scanner.nextLine();
				 try {
					 if(run.checkStart(check, input)) {
						 System.out.println(input+" starts with "+check);
					 }
					 if(run.checkEnd(check, input)) {
						 System.out.println(input+" ends with "+check);
					 }
					 if(run.checkContains(check, input)) {
						 System.out.println(input+" contains "+check);
					 }
					 if(run.checkEqual(check, input)) {
						 System.out.println(input+" equals "+check);
					 }
					 else {
						 System.out.println("No match found");
					 }
				 }
				 catch(InvalidInputException e ) {
					 e.printStackTrace();
				 }
				 break;
			 }
			 case(4):{
				 System.out.print("Enter number of Strings:");
				 int count=scanner.nextInt();
				 scanner.nextLine();
				 List<String> list=run.getList();
				 String str;
				 try {
					 while(count>0) {
						 System.out.print("Enter your String: ");
						 str=scanner.nextLine();
						 run.addElement(list,str); 
						 count--;
					 }
					 System.out.print("Enter your Matching String: ");
					 String check=scanner.nextLine();
					 for(String input:list) {
						 if(run.checkEqualCaseInsensitive(check, input)) {
							 System.out.println("Match Found: "+input);
						 }
					 }
				 }
				 catch(InvalidInputException e ) {
					 e.printStackTrace();
				 }
				 break;
			 }
			 case(5):{
					System.out.print("Enter your Email ID: ");
					String input=scanner.nextLine();
					try {
						if(run.checkEmail(input)) {
							System.out.println("Email ID is validated");
						}
						else {
							System.out.println("oops! Incorrect Email ID");
						}
					}
					catch(InvalidInputException e ) {
						e.printStackTrace();
					}
					break;
				 }
			 case(6):{
				 System.out.print("Enter number of Strings:");
				 int count=scanner.nextInt();
				 scanner.nextLine();
				 List<String> list=run.getList();
				 String str;
				 try {
					 while(count>0) {
						 System.out.print("Enter your String: ");
						 str=scanner.nextLine();
						 run.addElement(list,str); 
						 count--;
					 }
					 System.out.println("Enter your range to check.");
					 System.out.print("Start: ");
					 int start=scanner.nextInt();
					 System.out.print("End: ");
					 int end=scanner.nextInt();
					 for(String input:list) {
						 if(run.checkLength(input, start, end)) {
							 System.out.println(input+" is within the length.");
						 }
						 else {
							 System.out.println(input+" is not within the given length range.");
						 }
					 }
				 }
				 catch(InvalidInputException e ) {
						e.printStackTrace();
					}
					break; 
			 }
			 case(7):{
				 try {
					 System.out.print("Enter number of Strings for List1:");
					 int count=scanner.nextInt();
					 scanner.nextLine();
					 List<String> list1=run.getList();
					 String str;
					 while(count>0) {
						 System.out.print("Enter your String: ");
						 str=scanner.nextLine();
						 run.addElement(list1,str); 
						 count--;
					 }
					 System.out.print("Enter number of Strings for List2:");
					 count=scanner.nextInt();
					 scanner.nextLine();
					 List<String> list2=run.getList();
					 while(count>0) {
						 System.out.print("Enter your String: ");
						 str=scanner.nextLine();
						 run.addElement(list2,str); 
						 count--;
					 }
					 System.out.println(run.getListMaping(list1, list2));
				 }
				 catch(InvalidInputException e ) {
					 e.printStackTrace();
				 }
				 break;
			 }
			 case(8):{
				 System.out.println("Enter your Raw HTML String: ");
				 String input=scanner.nextLine();
				 try {
					 List<String> htmlTags=run.checkHTMLTags(input);
					 System.out.println("The HTML Tags");
					 for(String tag:htmlTags) {
						 System.out.println(tag);
					 }
				 }

				 catch(InvalidInputException e ) {
					 e.printStackTrace();
				 }
				 break;
			 }
			 case(9):{
				 System.out.print("Enter your password: ");
				 String password=scanner.nextLine();
				 try {
					 if(run.passwordValidation(password)) {
						 System.out.println("Password Validated");
					 }
					 else {
						 System.out.println("Password should be of eight characters with atleast one upperCase, special characterand number");
					 }
				 }
				 catch(InvalidInputException e ) {
					 e.printStackTrace();
				 }
				 break;
			 }
			 default:{
				 System.out.println("Invalid Input");
			 }
			}
		}
		scanner.close();
	}

}
