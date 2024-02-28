package strbuildertest;
import strbuildertask.StringBuilderTask;
import util.InvalidInputException;
import java.util.Scanner;

public class StringBuilderRunner{

 public static void main(String[] args){
   StringBuilderTask task= new StringBuilderTask();
   Scanner scanner= new Scanner(System.in);
   int option=0;
   while(option>=0){
    System.out.print("Enter the question number:") ;
    option=scanner.nextInt();	
    scanner.nextLine();
    switch(option){
     case(1):{
       try{
          StringBuilder strBuilder=task.getStringBuilder();
          System.out.println("New StringBuilder is Created with length "+task.getLength(strBuilder));
          System.out.print("Enter the number of strings to append: ");
          int count =scanner.nextInt();
          scanner.nextLine();
          while(count>0){
          count--;
          System.out.print("Enter your String: ");
          String sample=scanner.nextLine();
          strBuilder=task.appendStrBuilder(strBuilder, sample);
          System.out.println("Output StringBuilder: "+strBuilder+" ,length: "+task.getLength(strBuilder));
          }
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       break;
     }
     case(2):{
       try{  
          System.out.print("Enter your String: ");
          String str = scanner.nextLine();
          StringBuilder strBuilder=task.getStringBuilder(str);
          System.out.println("Input StringBuilder: "+strBuilder+" ,length: "+task.getLength(strBuilder));
          System.out.print("Enter a character to separate: ");
          String separator=scanner.nextLine();  
          System.out.print("Enter the number of strings to append: ");
          int count =scanner.nextInt();
          scanner.nextLine();
          while(count>0){
          count--;
          System.out.print("Enter your String: ");
          String sample=scanner.nextLine();
          strBuilder=task.appendStrBuilder(strBuilder, separator);
          strBuilder=task.appendStrBuilder(strBuilder, sample);
          System.out.println("Output StringBuilder: "+strBuilder+" ,length: "+task.getLength(strBuilder));
          }
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       break;
     }
     case(3):{
       try{
          System.out.print("Enter your multiple String: ");
          String str = scanner.nextLine();
          System.out.print("Enter the seperator character: ");
          String separator=scanner.nextLine();
          System.out.print("Enter the insert string: ");
          String insertString = scanner.next();
          System.out.print("Enter the position: ");
          int position = scanner.nextInt();
          scanner.nextLine();
          StringBuilder strBuilder=task.getStringBuilder(str);
          System.out.println("Input StringBuilder: "+strBuilder+" ,length: "+task.getLength(strBuilder));
          strBuilder=task.insertStrBuilder(strBuilder,separator,insertString,position);
          System.out.println("Output StringBuilder: "+strBuilder+" ,length: "+task.getLength(strBuilder));
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       break;
     }
     case(4):{
       try{
          System.out.print("Enter your String: ");
          StringBuilder strBuilder=task.getStringBuilder(scanner.nextLine());  
          System.out.print("Enter start position: ");
          int start = scanner.nextInt();
          System.out.print("Enter end position: ");
          int end = scanner.nextInt();
          scanner.nextLine();
          System.out.println("Delete StringBuilder: "+task.deleteStr(strBuilder, start, end));
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       break;
     }
     case(5):{
       try{
          System.out.print("Enter your String: ");
          String str = scanner.nextLine();
          StringBuilder strBuilder=task.getStringBuilder(str);
          System.out.println("Input StringBuilder "+strBuilder+" ,length: "+task.getLength(strBuilder));
          System.out.print("Enter your old character(s): ");
          String oldChar=scanner.nextLine();
          System.out.print("Enter your new Replace character(s): ");
          String newChar=scanner.nextLine();
          strBuilder= task.changeCharacters(strBuilder, oldChar, newChar);
          System.out.println("Output StringBuilder "+strBuilder+" ,length: "+task.getLength(strBuilder));
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       break;
     }
     case(6):{
       try{
          System.out.print("Enter your String: ");
          StringBuilder strBuilder=task.getStringBuilder(scanner.nextLine());
          System.out.println("Input StringBuilder: "+strBuilder+" ,length: "+task.getLength(strBuilder));
          strBuilder=task.reverseStrBuilder(strBuilder);
          System.out.println("Reversed StringBuilder: "+strBuilder+" ,length: "+task.getLength(strBuilder));
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       break;
     }
     case(7):{
       try{
          System.out.print("Enter your String: ");
          StringBuilder strBuilder=task.getStringBuilder(scanner.nextLine());
          System.out.print("Enter minimum character Length: ");
          int minimum= scanner.nextInt();   
          System.out.print("Enter start position: ");
          int start = scanner.nextInt();
          System.out.print("Enter end position: ");
          int end = scanner.nextInt();
          scanner.nextLine();
          System.out.println("Delete StringBuilder: "+task.deleteStr(strBuilder, start, end, minimum));
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       break;
     }
     case(8):{
       try{
          System.out.print("Enter your String: ");
          StringBuilder strBuilder=task.getStringBuilder(scanner.nextLine());
          System.out.print("Enter your replace String: ");
          String replace=scanner.nextLine();
          System.out.print("Enter minimum character Length: ");
          int minimum= scanner.nextInt();   
          System.out.print("Enter start position: ");
          int start = scanner.nextInt();
          System.out.print("Enter end position: ");
          int end = scanner.nextInt();
          scanner.nextLine();
          System.out.println("Input StringBuilder: "+strBuilder+" length: "+task.getLength(strBuilder));
          strBuilder=task.replaceChars(strBuilder,start,end,minimum,replace);
          System.out.println("Replaced StringBuilder: "+strBuilder+" length: "+task.getLength(strBuilder));
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       break;
     }
     case(9):{
       try{
          System.out.print("Enter your String: ");
          StringBuilder strBuilder=task.getStringBuilder(scanner.nextLine());
          System.out.print("Enter the character: ");
          String character=scanner.nextLine();
          System.out.println("New StringBuilder: "+strBuilder+" ,length: "+task.getLength(strBuilder));
          System.out.println("The First occurence of '"+character+"' is at index: "+task.firstIndex(strBuilder, character));
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       break;
     }
     case(10):{
       try{
          System.out.print("Enter your String: ");
          StringBuilder strBuilder=task.getStringBuilder(scanner.nextLine());
          System.out.print("Enter the character: ");
          String character=scanner.nextLine();
          System.out.println("New StringBuilder: "+strBuilder+" ,length: "+task.getLength(strBuilder));
          System.out.println("The Last occurence of '"+character+"' is at Index "+task.lastIndex(strBuilder, character));
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       break;
     }
     case(11):{
        StringBuilder strBuilder=null;
        String str=null;
        String str1="Test";
        StringBuilder strBuilder1=new StringBuilder("Test");
       try{
    	   task.getLength(strBuilder);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.getStringBuilder(str);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.appendStrBuilder(strBuilder, str1);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.insertStrBuilder(strBuilder,str1,str1,0);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.insertStrBuilder(strBuilder1,str1,str1,-1);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.deleteStr(strBuilder,0,1);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.deleteStr(strBuilder1,-1,1);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.deleteStr(strBuilder1,2,1);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.deleteStr(strBuilder1,1,1);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.changeCharacters(strBuilder, str1, str1);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.changeCharacters(strBuilder1, str, str1);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.changeCharacters(strBuilder1, str1, str);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.reverseStrBuilder(strBuilder);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.replaceChars(strBuilder,0,1,10,str1);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.replaceChars(strBuilder1,-1,1,10,str1);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.replaceChars(strBuilder1,2,1,10,str1);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.replaceChars(strBuilder1,1,1,10,str1);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.replaceChars(strBuilder1,2,10,10,str1);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.replaceChars(strBuilder1,1,2,-1,str1);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.replaceChars(strBuilder1,0,1,2,str);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.deleteStr(strBuilder1,2,10,10);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.deleteStr(strBuilder1,1,2,-1);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.firstIndex(strBuilder, str1);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.firstIndex(strBuilder1, str);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.lastIndex(strBuilder, str1);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       try{
          task.lastIndex(strBuilder1, str);
       }
       catch(InvalidInputException e){
          e.printStackTrace();
       }
       System.out.println("Check Over!!!");
       break;
     }
     default:{
          System.out.println("Invalid Option!!!");
     }
   }
  }
  scanner.close();
  System.out.println("scanner closed");
 }
}
