package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Helper {
	public static void nullCheck(Object obj) throws ApplicationException{
	     if(obj==null){
	       throw new ApplicationException("Invalid Input");  
	     }
	    }
	    public static boolean lessThanCheck(int length1,int length2){
	          if (length1<length2){
	             return true;
	          }
	          return false;
	    }
	    public static boolean lessThanEqualCheck(int length1,int length2){
	          if (length1<=length2){
	             return true;
	          }
	          return false;
	    }
	    public static void indexCheck(int size,int index) throws ApplicationException {
			if(lessThanCheck(index, 0)||lessThanCheck(size, index)) {
				throw new ApplicationException("Invalid Index");
			}
		}
	    public static StringBuilder getStringBuilder()throws ApplicationException{
	        StringBuilder strBuilder = new StringBuilder();
	        return strBuilder;
	    }
	    public static StringBuilder getStringBuilder(String str )throws ApplicationException{
	        Helper.nullCheck(str);
	        StringBuilder strBuilder = new StringBuilder(str);
	        return strBuilder;
	    }
	    public static int getLength(StringBuilder strBuilder)throws ApplicationException{
	        Helper.nullCheck(strBuilder);
	        return strBuilder.length();
	    }
		public static <T> List<T> getArrayList() throws ApplicationException{
			 return new ArrayList<T>();
		}
		public static <T> int getSize(List<T> arrayList) throws ApplicationException{
			Helper.nullCheck(arrayList);
			return arrayList.size();
		}
		public static <T> List<T> addElement(List<T> arrayList, T element) throws ApplicationException{
			Helper.nullCheck(arrayList);
			arrayList.add(element);
			return arrayList;
		}
		public static <T> T getElement(List<T> arrayList, int index) throws ApplicationException{
			int size=getSize(arrayList);
			Helper.indexCheck(size,index);
			return arrayList.get(index);
		}
		public static <T>void insertList(List<List<T>> listOfList,List<T>list) throws ApplicationException{
			Helper.nullCheck(list);
			Helper.nullCheck(listOfList);
			listOfList.add(list);
		}
		public static <K,V> Map<K,V> getHashMap() throws ApplicationException{
			 return new HashMap<K,V>();
		}
		public static <K,V> int getSize(Map<K,V> hashMap) throws ApplicationException {
			Helper.nullCheck(hashMap);
			return hashMap.size();
		}
		public static <K,V> Map<K,V> addElement(Map<K,V> hashMap, K key, V value) throws ApplicationException {
			Helper.nullCheck(hashMap);
		    hashMap.put(key, value);
			return hashMap;	
		}
}

