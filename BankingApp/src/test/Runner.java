package test;

import java.util.Scanner;

import task.TableCreator;
import util.ApplicationException;
import util.Helper;

public class Runner {
    public static void main(String[] args) {
//       System.out.println(TableCreator.buildUserTable());
//       System.out.println(TableCreator.buildBranchTable());
//       System.out.println(TableCreator.buildCustomerTable());
//       System.out.println(TableCreator.buildEmployeeTable());
//       System.out.println(TableCreator.buildAccountDetails());
//       System.out.println(TableCreator.buildTranscationDetails());
    	Scanner scanner=new Scanner(System.in);
    	for(int i=0;i<6;i++) {
    		System.out.print("Enter your Password: ");
    		String pwd=scanner.nextLine();
    		try {
    		System.out.println(passwordValidation(pwd));
    		}
    		catch(ApplicationException e) {
    			e.printStackTrace();
    		}
    	}
    }


private static boolean passwordValidation(String input) throws ApplicationException{
	Helper.nullCheck(input);
	String regEx = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!#$%&‘*+–/=?^_`.{|}~@]).{8,}$";
	return input.matches(regEx);
}
}