package strbuildertask;
import util.InvalidInputException;
import util.Helper;

public class StringBuilderTask{

	public StringBuilder getStringBuilder()throws InvalidInputException{
        StringBuilder strBuilder = new StringBuilder();
        return strBuilder;
    }
    public StringBuilder getStringBuilder(String str )throws InvalidInputException{
        Helper.nullCheck(str);
        StringBuilder strBuilder = new StringBuilder(str);
        return strBuilder;
    }
    public  int getLength(StringBuilder strBuilder)throws InvalidInputException{
        Helper.nullCheck(strBuilder);
        return strBuilder.length();
    }
    public StringBuilder appendStrBuilder(StringBuilder strBuilder, String str)throws InvalidInputException{
        Helper.nullCheck(strBuilder);
        return strBuilder.append(str);
    }
   public StringBuilder insertStrBuilder(StringBuilder strBuilder,String separator,String insertString,int position)throws InvalidInputException{
        if(Helper.lessThanCheck(position,0)){
          throw new InvalidInputException("Invalid Position");
        }
        Helper.nullCheck(strBuilder);
        return strBuilder.insert(position,insertString+separator);
   } 
   public void indexCheck(StringBuilder strBuilder,int start, int end)throws InvalidInputException{
        int length=getLength(strBuilder);
        if(Helper.lessThanCheck(start,0) || Helper.lessThanCheck(length,end) || 
           Helper.lessThanCheck(end,start)){
          throw new InvalidInputException("Check your start and end values");
        }
   }
   public StringBuilder deleteStr(StringBuilder strBuilder, int start, int end) throws InvalidInputException{
        indexCheck(strBuilder,start,end);
        return strBuilder.delete(start,end);
   }
   public StringBuilder changeCharacters(StringBuilder strBuilder, String oldChars, String newChars) throws InvalidInputException{
        int length=getLength(strBuilder);
        Helper.nullCheck(oldChars);    
        Helper.nullCheck(newChars);     
        int i=0;
        int index;
        for(i=0;i<length-1;i++){
           index=strBuilder.indexOf(oldChars,i);
           i=index+1;
           strBuilder.replace(index,i,newChars);
        }
        return strBuilder; 
   }
   public StringBuilder reverseStrBuilder(StringBuilder strBuilder) throws InvalidInputException{
        Helper.nullCheck(strBuilder);
        return strBuilder.reverse();
   }
   public void minCharsCheck(StringBuilder strBuilder,int minimum)throws InvalidInputException{
        int length=getLength(strBuilder);
        if(Helper.lessThanCheck(minimum,0)|| Helper.lessThanCheck(length,minimum)){
           throw new InvalidInputException("Check your minimum value");
        }
   }
   public StringBuilder replaceChars(StringBuilder strBuilder,int start,int end,int minimum,String replaceStr) throws InvalidInputException{      
        indexCheck(strBuilder,start,end);
        minCharsCheck(strBuilder,minimum);
        Helper.nullCheck(replaceStr); 
        return strBuilder.replace(start,end,replaceStr);
   }
   public StringBuilder deleteStr(StringBuilder strBuilder, int start, int end,int minimum) throws InvalidInputException{
        minCharsCheck(strBuilder,minimum);
        return deleteStr(strBuilder,start,end);
   }
   public int firstIndex(StringBuilder strBuilder, String str) throws InvalidInputException{
        Helper.nullCheck(strBuilder);
        Helper.nullCheck(str);
        return strBuilder.indexOf(str);
   }
   public int lastIndex(StringBuilder strBuilder, String str) throws InvalidInputException{
        Helper.nullCheck(strBuilder);
        Helper.nullCheck(str);
        return strBuilder.lastIndexOf(str);
   }
}
