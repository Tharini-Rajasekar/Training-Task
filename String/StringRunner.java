package stringTester;
import stringTask.StringTask;
import java.util.Scanner;
import java.util.Arrays;
import util.InvalidInputException;


public class StringRunner {
    public static void main (String[] args)
    {
        StringTask task= new StringTask();
        
        Scanner scanner= new Scanner(System.in);
        
        if (args.length > 0) {
           try{
              String input = args[0];
              System.out.println("The length of the string '"+input +"' is "+ task.getLength(input));
           }
          catch(InvalidInputException e){
              e.printStackTrace();
          }
        }
       int option=0;
        while(option>=0){
        System.out.print("Enter the question number:") ;
        option=scanner.nextInt();	
        scanner.nextLine();
        
        switch (option){
        
        case(1):{
              try{
                 System.out.print("Enter the String: ");
                 String inputString=scanner.nextLine();
                 System.out.println("The Character array of "+inputString +
                 " is "+Arrays.toString(task.getCharacterArray(inputString)));
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              break;
        }
        case(2):{
              try{
                 System.out.println("Select a option to get a character from the String\n"
                                 +"1.penultimate character\n2.Any other character");
                 System.out.print("Enter your option:");
              	 int select=scanner.nextInt();
              	 scanner.nextLine();
              	 System.out.print("Enter the String: ");
              	 String inputString=scanner.nextLine();
               	 if(select==1){
                      System.out.println("The last but one character is "
                  	+task.getPenultimateCharacter(inputString));
              	 }
              	 else if (select==2){
                      System.out.print("Enter the Index value:");
                      int position=scanner.nextInt();
                      System.out.println("The character at "+position+" is "
                                     +task.getCharacter(inputString,position));
                 }
             	 else{
                      System.out.println("Invalid option!");
              	 }
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              break;
              
        }
        case(3):{
              try{
              	System.out.print("Enter the String: ");
              	String inputString=scanner.nextLine();
              	System.out.print("Enter the character to count: ");
              	char character=scanner.next().charAt(0);
              	System.out.println("The number of occurrences of '"+character+
              	"' in the input : "+task.getCharacterCount(inputString, character));
              }
              catch(InvalidInputException e){
              	e.printStackTrace();
              }
              break;
              
        }
        case(4):{
              try{
              	System.out.print("Enter the String: ");
              	String inputString=scanner.nextLine();
              	System.out.print("Enter the character: ");
              	char character=scanner.next().charAt(0);
              	System.out.println("The Greatest Position of '"+character+
              	"' is "+ task.getGreatestPosition(inputString, character));
              }
              catch(InvalidInputException e){
              	e.printStackTrace();
              }
              break;
        }
        case(5):{
              try{
              	System.out.print("Enter the String: ");
              	String inputString=scanner.nextLine();
              	System.out.print("Enter the number of character from last to display: ");
              	int numberOfCharacters=scanner.nextInt();
              	scanner.nextLine();
              	System.out.println("The Last "+numberOfCharacters+" characters: "
              	+task.getLastCharacters(inputString, numberOfCharacters));
              }
              catch(InvalidInputException e){
              	e.printStackTrace();
              }
              break;
        }
        case(6):{
              try{
              	System.out.print("Enter the String: ");
              	String inputString=scanner.nextLine();
              	System.out.print("Enter the number character from First to display: ");
              	int numberOfCharacters=scanner.nextInt();
              	scanner.nextLine();
              	System.out.println("The First "+numberOfCharacters+" characters: "
              	+task.getFirstCharacters(inputString,numberOfCharacters));
              }
              catch(InvalidInputException e){
              	e.printStackTrace();
              }
              break;
        }
        case(7):{
               try{
              	System.out.print("Enter the String to modify: ");
              	String inputString=scanner.nextLine();
              	System.out.print("Enter the replacement string:");
              	String replacement=scanner.nextLine();
              	System.out.println("Where to replace?\n1.At the beginning\n2.At the end\n3.Any particular index");
              	System.out.print("Enter your option:");
              	int select=scanner.nextInt();
              	scanner.nextLine();
                if (select==1){
                     System.out.println("The replaced String: "+ task.replaceString(inputString, replacement,0));
                }
                else if(select==2){
                     System.out.println("The replaced String: "+ task.replaceString(inputString, replacement));
                }
                else if(select==3){
                     System.out.print("Enter the index value: ");
                     int index=scanner.nextInt();
                     scanner.nextLine();
                     System.out.println("The replaced String: "+ task.replaceString(inputString, replacement,index));
                }
                else{
                     System.out.println("Wrong option!");
                }
              }
              catch(InvalidInputException e){
           	   e.printStackTrace();
              }
              break;
        }
        case(8):{
              try{
              	System.out.print("Enter the String: ");
              	String inputString=scanner.nextLine();
              	System.out.print("Enter the Prefix to check: ");
              	String prefix=scanner.nextLine();
              	boolean output=task.checkPrefix(inputString,prefix);
              	if (output==true) {
              	     System.out.println("The input string starts with '"+prefix+"' "); 
                }
              	else {
              	     System.out.println("The input string doesn't start with '"+prefix+"' "); 
                }
              }
              catch(InvalidInputException e){
              	e.printStackTrace();
              }
              break;
        }
        case(9):{
              try{
              	System.out.print("Enter the String: ");
              	String inputString=scanner.nextLine();
              	System.out.print("Enter the Suffix to check: ");
              	String suffix=scanner.nextLine();
              	boolean output=task.checkSuffix(inputString,suffix);
              	if (output==true) {
              	     System.out.println("The input string ends with '"+suffix+"' "); 
                }
              	else {
              	     System.out.println("The input string doesn't end with '"+suffix+"' "); 
              	}
              }
              catch(InvalidInputException e){
              	e.printStackTrace();
              }
              break;
        }
        case(10):{
              try{
              System.out.print("Enter the String: ");
              String inputString=scanner.nextLine();
              System.out.println("The input string in uppercase: "
              + task.changeToUpperCase(inputString));
              }
              catch(InvalidInputException e){
              e.printStackTrace();
              }
              break;
        }
        case(11):{
              try{
              	System.out.print("Enter the String: ");
              	String inputString=scanner.nextLine();
              	System.out.println("The input string in lowercase: "
              	+ task.changeToLowerCase(inputString));
              }
              catch(InvalidInputException e){
              	e.printStackTrace();
              }
              break;
        }
        case(12):{
              try{
              	System.out.print("Enter the String: ");
              	String inputString=scanner.nextLine();
              	System.out.println("The Reversed String: "+task.reverseString(inputString));
              }
              catch(InvalidInputException e){
              	e.printStackTrace();
              }
              break;
        }
        case(13):{
              System.out.print("Enter the String: ");
              String inputString=scanner.nextLine();
              System.out.println("The multiple string input: "+ inputString);
              break;
        }
        case(14):{
              try{
              	System.out.print("Enter the String: ");
              	String inputString=scanner.nextLine();
              	System.out.print("Enter the character to remove: ");
              	String condition=scanner.nextLine();
              	System.out.println("The Output String: "+ task.removeCharacter(inputString, condition));
              }
              catch(InvalidInputException e){
              	e.printStackTrace();
              }
              break;
        }
        case(15):{
              try{
              	System.out.print("Enter the String: ");
              	String inputString=scanner.nextLine();
              	System.out.print("Enter the a character to split: ");
              	String condition=scanner.nextLine();
              	System.out.println("The Output String Array: "
              	+Arrays.toString(task.getStringArray(inputString,condition)));
              }
              catch(InvalidInputException e){
              	e.printStackTrace();
              }
              break;
        }
        case(16):{
              try{
              	System.out.print("Enter number of inputs: ");
              	int total=scanner.nextInt();
              	scanner.nextLine();
              	System.out.print("Enter the character/symbol to concatenate: ");
              	String symbol=scanner.nextLine();
              	int i;
              	String[] newString=new String[total];
              	for(i=0;i<total;i++){
                     System.out.print("Enter your String: ");
                     newString[i]=scanner.nextLine();
              	}
              	System.out.println("The Output String: "+ task.joinString(newString,symbol));
              }
              catch(InvalidInputException e){
              	e.printStackTrace();
              }
              break;
        }
        case(17):{
              try{
              	System.out.print("Enter the String1: ");
              	String inputString1=scanner.nextLine();
              	System.out.print("Enter the String2: ");
              	String inputString2=scanner.nextLine();
              	boolean output=task.checkEqualCaseSensitive(inputString1, inputString2);
              	if (output==true) {
              	     System.out.println("The input strings are equal" ); 
              	}
              	else {
              	     System.out.println("The input strings are not equal" ); 
              	}
              }
              catch(InvalidInputException e){
              	e.printStackTrace();
              }
              break;
        }
        case(18):{
              try{
              	System.out.print("Enter the String1: ");
              	String inputString1=scanner.nextLine();
              	System.out.print("Enter the String2: ");
              	String inputString2=scanner.nextLine();
              	boolean output=task.checkEqualCaseInsensitive(inputString1, inputString2);
              	if (output==true) {
                     System.out.println("The input strings are equal" ); 
              	}
              	else {
              	     System.out.println("The input strings are not equal" ); 
              	}
              }
              catch(InvalidInputException e){
              	e.printStackTrace();
              }
              break;
        }
        case(19):{
              try{
              	System.out.print("Enter the String: ");
              	String inputString=scanner.nextLine();
              	System.out.println("The input String: "+inputString);
              	System.out.println("The trimmed string: "+ task.removeEndSpace(inputString));
              }
              catch(InvalidInputException e){
              	e.printStackTrace();
              }
              break;
        }
        case(20):{
              String str=null;
              String[] strArray=null;
              String str1="Test";
              String str2="";
              try{
                 task.replaceString(str1, str, 0);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.replaceString(str1, str2,-1);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.replaceString(str, str1);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }    
              try{
                 task.replaceString(str1, str);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.checkPrefix(str, str1);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.checkPrefix(str1, str);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.checkSuffix(str, str1);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.checkSuffix(str1, str);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.changeToUpperCase(str);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.changeToLowerCase(str);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.reverseString(str);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.removeCharacter(str, str2);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.removeCharacter(str1, str);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.getStringArray(str, str2);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.getStringArray("Sanity Check", str);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.joinString(strArray, str2);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.joinString(new String[]{"Sanity","Check"}, str);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.checkEqualCaseSensitive(str,str1);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.checkEqualCaseSensitive(str1, str);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.checkEqualCaseInsensitive(str, str1);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.checkEqualCaseInsensitive(str1, str);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              try{
                 task.removeEndSpace(str);
              }
              catch(InvalidInputException e){
                 e.printStackTrace();
              }
              System.out.println("Sanity check over!");
              break;

        }
        default:{
              System.out.println("Invalid Option!!!");
        }
      }
     }
     scanner.close();
     System.out.println("Scanner closed");
    }
   }
  