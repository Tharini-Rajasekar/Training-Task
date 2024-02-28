package stringTask;
import util.InvalidInputException;
import util.Helper;
public class StringTask{
  public void throwException(String input) throws InvalidInputException{
         throw new InvalidInputException("Invalid Input : "+input); 
  } 
  public int getLength(String str) throws InvalidInputException{ 
      Helper.nullCheck(str);
      return str.length();
 }
 public int getLength(String[] strArray) throws InvalidInputException{ 
      Helper.nullCheck(strArray);
      return strArray.length;
}
  public char[] getCharacterArray(String str) throws InvalidInputException{
         Helper.nullCheck(str);
         return str.toCharArray();
  }
  public char getPenultimateCharacter(String str)throws  InvalidInputException{
         int length=getLength(str);
         if(Helper.lessThanCheck(length,2)){
              throwException("Input String Length is less than 2");
         }
         return str.charAt(length-2);              
  }
  public char getCharacter(String str, int position)throws InvalidInputException{
         int length=getLength(str);
         if(Helper.lessThanEqualCheck(length,position) || Helper.lessThanCheck(position,0)){
             throwException("Index is out of bound, check position");
         }
         return str.charAt(position);       
  }
  public int getCharacterCount(String str, char character)throws InvalidInputException{
         int length=getLength(str);
         int i,count=0;
         for(i=0;i<length;i++){
            if(str.charAt(i)==character){
               count ++;
            }
         }
         return count;
   }
   public int getGreatestPosition(String str, char character)throws InvalidInputException{
          Helper.nullCheck(str);
          int position= str.lastIndexOf(character);
          if(Helper.lessThanCheck(position,0)){
             throwException("Character not found");
          }
          return position;
   }
   public String subString(String str, int begin, int end){
          return str.substring(begin,end);
   }
   public int getCharactersCheck(String str, int numberOfCharacters)throws InvalidInputException{
          int length=getLength(str);
          if(Helper.lessThanEqualCheck(length,0)|| Helper.lessThanCheck(length,numberOfCharacters) || Helper.lessThanCheck(numberOfCharacters,0)){
             throwException("Invalid Input string");
          }
          return length;
   }
   public String getLastCharacters(String str, int numberOfCharacters)throws InvalidInputException{
          int length=getCharactersCheck(str, numberOfCharacters);
          return subString(str,length-numberOfCharacters,length);
   }
   public String getFirstCharacters(String str, int numberOfCharacters) throws InvalidInputException{
          getCharactersCheck(str, numberOfCharacters);
          return subString(str,0,numberOfCharacters);
   }
   public String replaceString(String str, String replace, int index) throws InvalidInputException{           
          if (Helper.lessThanCheck(index,0)){
              throwException("Negative Index Value!");
          } 
          int length=getLength(str);
          int replaceLength =getLength(replace);               //Tharini  rSt=uvai tharuva
          String newString=subString(str,0,index)+replace;
          if(Helper.lessThanEqualCheck(length,index+replaceLength)){
             return newString;
          }           
                
          return newString+subString(str,index+replaceLength,length);
   }
   public String replaceString(String str, String replace)throws InvalidInputException{
          int length=getLength(str);
          int replaceLength=getLength(replace);
          int difference=length-replaceLength;
          if(Helper.lessThanEqualCheck(difference,0)){
             return replaceString(str, replace, 0); 
          }
          return replaceString(str, replace, difference);
   }
   public boolean checkPrefix(String str, String prefix)throws InvalidInputException{
          Helper.nullCheck(str);
          Helper.nullCheck(prefix);
          return str.startsWith(prefix) ; 
   }
   public boolean checkSuffix(String str, String suffix)throws InvalidInputException{
          Helper.nullCheck(str);
          Helper.nullCheck(suffix);
          return str.endsWith(suffix); 
   }
   public String changeToUpperCase(String str)throws InvalidInputException{
          Helper.nullCheck(str);
          return str.toUpperCase();
   }
   public String changeToLowerCase(String str) throws InvalidInputException{
          Helper.nullCheck(str);
          return str.toLowerCase();
   }
   public String reverseString(String str) throws InvalidInputException{
          int length=getLength(str);
          int i;
          char[] strChar= str.toCharArray();
          char[] reversedChar=new char[length];
          for (i=0;i<length;i++){
             reversedChar[i]=strChar[length-1-i];
          }
          return new String(reversedChar);
   }
   public String removeCharacter(String str, String condition) throws InvalidInputException{
          Helper.nullCheck(str);
          Helper.nullCheck(condition);
          String concatenatedString="";
          int i=0;
          String[] stringArray=str.split(condition);   //str.split(Pattern.quote(condition)) input without using '\'
          int arrLength=getLength(stringArray);
          for(i=0;i<arrLength;i++){  
              concatenatedString=concatenatedString.concat(stringArray[i]);
          }
          return concatenatedString; 
   }
   public String[] getStringArray(String str, String condition)throws InvalidInputException{
          Helper.nullCheck(str);
          Helper.nullCheck(condition);
          return str.split(condition);
   }
   public String joinString(String[] stringArray, String symbol)throws InvalidInputException{
          Helper.nullCheck(stringArray);
          Helper.nullCheck(symbol);
          return String.join(symbol, stringArray);
   }
   public boolean checkEqualCaseSensitive(String str1, String str2)throws InvalidInputException{
          Helper.nullCheck(str1);
          Helper.nullCheck(str2);
          return str1.equals(str2); 
   }
   public boolean checkEqualCaseInsensitive(String str1, String str2)throws InvalidInputException{
          Helper.nullCheck(str1);
          Helper.nullCheck(str2);
          return str1.equalsIgnoreCase(str2); 
   }
   public String removeEndSpace(String str)throws InvalidInputException{
          Helper.nullCheck(str);
          return str.trim();
   }
}
