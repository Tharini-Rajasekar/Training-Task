package task;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
//import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import util.InvalidInputException;
import util.Helper;

public class HashMapTask {
	
	@SuppressWarnings("unchecked")
	public <K,V> Map<K,V> getHashMap() throws InvalidInputException{
		Object obj; 
		try {
		 Class<?> arrayList= Class.forName("java.util.HashMap");
		 Constructor<?> construct=arrayList.getConstructor();
		 obj =construct.newInstance();
		 }
		catch (ClassNotFoundException | NoSuchMethodException | SecurityException | 
				InstantiationException | IllegalAccessException |IllegalArgumentException 
				| InvocationTargetException e) {
			throw new InvalidInputException("Reflection Implementation Error!",e);
		}
		 return (Map<K,V>)obj;
	}
	public <K,V> int getSize(Map<K,V> hashMap) throws InvalidInputException {
		Helper.nullCheck(hashMap);
		return hashMap.size();
	}
	public <K,V> Map<K,V> addElement(Map<K,V> hashMap, K key, V value) throws InvalidInputException {
		Helper.nullCheck(hashMap);
	    hashMap.put(key, value);
		return hashMap;	
	}
	public <K,V> boolean checkKey(Map<K,V> hashMap, K key)throws InvalidInputException{
		Helper.nullCheck(hashMap);
		return hashMap.containsKey(key);
	}
	public <K,V> boolean checkValue(Map<K,V> hashMap, V value)throws InvalidInputException{
		Helper.nullCheck(hashMap);
		return hashMap.containsValue(value);
	}
	public <K,V> V getValue(Map<K,V> hashMap,K key)throws InvalidInputException{
		Helper.nullCheck(hashMap);
		return hashMap.get(key);
	}
	public <K,V> Map<K,V> replaceValues(Map<K,V> hashMap,K key,V oldValue,V newValue)throws InvalidInputException{
		Helper.nullCheck(hashMap);
		hashMap.replace(key, oldValue, newValue);
		return hashMap;
	}
	public <K,V> Map<K,V> replaceValues(Map<K,V> hashMap,K key,V newValue)throws InvalidInputException{
		Helper.nullCheck(hashMap);
		hashMap.replace(key,newValue);
		return hashMap;
	}
	public <K,V> Map<K,V> removeKey(Map<K,V> hashMap,K key) throws InvalidInputException{
		Helper.nullCheck(hashMap);
		hashMap.remove(key);
		return hashMap;	
	}
	public <K,V> Map<K,V> removeKey(Map<K,V> hashMap,K key, V value) throws InvalidInputException{
		Helper.nullCheck(hashMap);
		hashMap.remove(key,value);
		return hashMap;	
	}
	public <K,V> Map<K,V> transferHashMap(Map<K,V> hashMap1,Map<K,V> hashMap2) throws InvalidInputException{
		Helper.nullCheck(hashMap1);
		Helper.nullCheck(hashMap2);
		hashMap1.putAll(hashMap2);
		return hashMap1;
	}
	public <K,V> Map<K,V> clearHashMap(Map<K,V> hashMap) throws InvalidInputException{
		Helper.nullCheck(hashMap);
		hashMap.clear();
		return hashMap;
	}
	public <K,V> V nonExistingKey(Map<K,V> hashMap,K key, V nonExistingValue) throws InvalidInputException{
		return  hashMap.getOrDefault(key, nonExistingValue);
	}
	public <K,V> Iterator<Entry<K, V>> iterateHashMap(Map<K,V> hashMap) throws InvalidInputException{
		Helper.nullCheck(hashMap);
		Iterator<Entry<K, V>> iteration=hashMap.entrySet().iterator();
		return iteration;
	}
}
