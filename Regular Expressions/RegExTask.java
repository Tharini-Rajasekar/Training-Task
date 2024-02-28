package task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.Helper;
import util.InvalidInputException;

public class RegExTask {
	public boolean checkMobileNum(String number) throws InvalidInputException {
		Helper.nullCheck(number);
		String regEx="^[7-9][0-9]{9}$";
		Helper.nullCheck(regEx);
		return number.matches(regEx);
	}
	public boolean checkAlphaNumeric(String input) throws InvalidInputException  {
		Helper.nullCheck(input);
		String regEx="[a-zA-Z0-9]*";
		Helper.nullCheck(regEx);
		return input.matches(regEx);
	}
	public boolean checkStart(String check, String input) throws InvalidInputException {
		Helper.nullCheck(input);
		Helper.nullCheck(check);
		return input.matches("^"+check+".*");
	}
	public boolean checkEnd(String check, String input) throws InvalidInputException {
		Helper.nullCheck(input);
		Helper.nullCheck(check);
		return input.matches(".*"+check+"$");
	}
	public boolean checkContains(String check, String input) throws InvalidInputException {
		Helper.nullCheck(input);
		Helper.nullCheck(check);
		return input.matches(".*"+check+".*");
	}
	public boolean checkEqual(String check, String input) throws InvalidInputException {
		Helper.nullCheck(input);
		Helper.nullCheck(check);
		return input.matches(check);
	}
	public <T> List<T> getList(){
		return new ArrayList<T>();
	}
	public <T> void addElement(List<T> arrayList, T element) throws InvalidInputException{
		Helper.nullCheck(arrayList);
		arrayList.add(element);
	}
	public boolean checkEqualCaseInsensitive(String check, String input) throws InvalidInputException {
		Helper.nullCheck(input);
		Helper.nullCheck(check);
		Pattern pattern=Pattern.compile(check,Pattern.CASE_INSENSITIVE);
		return pattern.matcher(input).matches();
	}
	public boolean checkEmail(String email) throws InvalidInputException{
		Helper.nullCheck(email);
		String regEx="^[[a-zA-Z0-9]+[!#$%&‘*+–/=?^_`.{|}~]{0,1}+[a-zA-Z0-9]]{1,64}+@[a-zA-Z0-9]+[-.]{0,1}+[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$";
		return email.matches(regEx);
	}
    public boolean checkLength(String str,int begin,int end) throws InvalidInputException{
    	String regEx="[a-zA-Z]{"+begin+","+end+"}";
    	return str.matches(regEx);
    }
    public List<String> checkHTMLTags(String input) throws InvalidInputException{
    	 Helper.nullCheck(input);
    	 List<String> list=getList();
    	 Pattern pattern = Pattern.compile("(</?[a-z]*>)");
         Matcher matcher = pattern.matcher(input);
         while (matcher.find()) {
             addElement(list,matcher.group(1));
         }
         return list;
    }
    public <T> Map<T,Integer> getListMaping(List<T> list1,List<T>list2) throws InvalidInputException{
            Map<T,Integer> resultMap = new HashMap<>();
            for (T input : list2) {
                int index = list1.indexOf(input);
                if (index != -1) {
                    resultMap.put(input, index);
                }
            }
            return resultMap;
    }
    public boolean passwordValidation(String input) throws InvalidInputException{
    	Helper.nullCheck(input);
    	String regEx="[[a-z][A-Z]{1,}[0-9]{1,}[!#$%&‘*+–/=?^_`.{|}~@]{1,}]{8,}";
    	return input.matches(regEx);
    }
    

}
