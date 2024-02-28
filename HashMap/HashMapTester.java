package test;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import pack.Book;
import task.HashMapTask;
import util.InvalidInputException;

public class HashMapTester {

	public static void main(String[] args) {
		
	 HashMapTask task=new HashMapTask();
	 Scanner scanner=new Scanner(System.in);
	 int option=1;
	 while(option>0) {
		 System.out.print("Enter your question number:");
		 option=scanner.nextInt();
		 scanner.nextLine();
	  switch(option) {	
		case(1):{
			try {
				Map<Object,Object> hashMap=task.getHashMap();
				System.out.println("New HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;
		}
		case(2):{
			System.out.print("Enter number of entries: ");
			int count=scanner.nextInt();
			scanner.nextLine();
			String key;
			String value;
			try {
			  Map<String,String> hashMap=task.getHashMap();
			  while(count>0) {
				System.out.print("Enter your key: ");
				key=scanner.nextLine();
				System.out.print("Enter your value: ");
				value=scanner.nextLine();
				hashMap=task.addElement(hashMap, key, value);
				count--;
			    }
			  System.out.println("New HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
		    }
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;
		}
		case(3):{
			System.out.print("Enter number of entries: ");
			int count=scanner.nextInt();
			int key;
			int value;
			try {
				Map<Integer,Integer> hashMap=task.getHashMap();
			  while(count>0) {
				System.out.print("Enter your key: ");
				key=scanner.nextInt();
				System.out.print("Enter your value: ");
				value=scanner.nextInt();
				hashMap=task.addElement(hashMap, key, value);
				count--;
			    }
			  scanner.nextLine();
			  System.out.println("New HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
		    }
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;
		}
		case(4):{
			System.out.print("Enter number of entries: ");
			int count=scanner.nextInt();
			scanner.nextLine();
			
			String key;
			int value;
			try {
				Map<String,Integer> hashMap=task.getHashMap();
			  while(count>0) {
				System.out.print("Enter your key: ");
				key=scanner.nextLine();
				System.out.print("Enter your value: ");
				value=scanner.nextInt();
				scanner.nextLine();
				hashMap=task.addElement(hashMap, key, value);
				count--;
			    }
			  
			  System.out.println("New HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
		    }
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;
		}
		case(5):{
			System.out.print("Enter number of entries: ");
			int count=scanner.nextInt();
			scanner.nextLine();
			
			String key;
			String title;
			try {
				Map<String,Object> hashMap=task.getHashMap();
				  while(count>0) {
					System.out.print("Enter your key: ");
					key=scanner.nextLine();
					System.out.print("Enter your Book title: ");
					title=scanner.nextLine();
					hashMap=task.addElement(hashMap, key, new Book(title));
					count--;
				    }
				  
				  System.out.println("New HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
			    }
				catch(InvalidInputException e) {
					e.printStackTrace();
				}
				break;
		}
		case(6):{
			try {
			Map<String,String> hashMap=task.getHashMap();
				hashMap=task.addElement(hashMap, "key1", "value1");	
				hashMap=task.addElement(hashMap, "key2", null);	
			    hashMap=task.addElement(hashMap, "Key3", "value3");	
			    System.out.println("New HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
		    }
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;
		}
		case(7):{
			try {
			Map<String,String> hashMap=task.getHashMap();
				hashMap=task.addElement(hashMap, "key1", "value1");	
				hashMap=task.addElement(hashMap, null, "value2");	
			    hashMap=task.addElement(hashMap, "Key3", "value3");	
			    System.out.println("New HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
		    }
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;
		}
		case(8):{
			System.out.print("Enter number  of entries: ");
			int count=scanner.nextInt();
			scanner.nextLine();
			
			String key;
			String value;
			try {
				Map<String,String> hashMap=task.getHashMap();
			  while(count>0) {
				System.out.print("Enter your key: ");
				key=scanner.nextLine();
				System.out.print("Enter your value: ");
				value=scanner.nextLine();
				hashMap=task.addElement(hashMap, key, value);
				count--;
			    }
			  System.out.println("Enter your key to check: ");
			  key=scanner.nextLine();
			  if(task.checkKey(hashMap,key)) {
				  System.out.println("The key: "+key+" is present in the hash map");
			  }
			  else {
				  System.out.println("The key: "+key+" isn't present in the hash map");
			  }
		    }
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;
		}
		case(9):{
			System.out.print("Enter number of entries: ");
			int count=scanner.nextInt();
			scanner.nextLine();
			String key;
			String value;
			try {
				Map<String,String> hashMap=task.getHashMap();
			  while(count>0) {
				System.out.print("Enter your key: ");
				key=scanner.nextLine();
				System.out.print("Enter your value: ");
				value=scanner.nextLine();
				hashMap=task.addElement(hashMap, key, value);
				count--;
			    }
			  System.out.println("Enter your value to check: ");
			  value=scanner.nextLine();
			  if(task.checkValue(hashMap,value)) {
				  System.out.println("The value: "+value+" is present in the hash map");
			  }
			  else {
				  System.out.println("The value: "+value+" isn't present in the hash map");
			  }
		    }
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;
		}
		case(10):{
			System.out.print("Enter number of entries: ");
			int count=scanner.nextInt();
			scanner.nextLine();
			String key;
			String value;
			try {
				Map<String,String> hashMap=task.getHashMap();
			  while(count>0) {
				System.out.print("Enter your key: ");
				key=scanner.nextLine();
				System.out.print("Enter your value: ");
				value=scanner.nextLine();
				hashMap=task.addElement(hashMap, key, value);
				count--;
			    }
			  System.out.print("Enter your key (to change value at): ");
			  key=scanner.nextLine();
			  System.out.print("Enter your new value: ");
			  value=scanner.nextLine();
			  System.out.println("New HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
			  hashMap=task.replaceValues(hashMap, key, value);
			  System.out.println("New replaced HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;
		}
		case(11):{
			System.out.print("Enter number of entries: ");
			int count=scanner.nextInt();
			scanner.nextLine();
			String key;
			String value;
			try {
				Map<String,String> hashMap=task.getHashMap();
			  while(count>0) {
				System.out.print("Enter your key: ");
				key=scanner.nextLine();
				System.out.print("Enter your value: ");
				value=scanner.nextLine();
				hashMap=task.addElement(hashMap, key, value);
				count--;
			    }
			  System.out.print("Enter a key to search: ");
			  key=scanner.nextLine();
			  if(task.checkKey(hashMap, key)) {
				  System.out.println("The Key '"+key+"' is present in the hashmap");
			  }
			  else {
				  System.out.println("The Key '"+key+"' is not present in the hashmap");
			  }
			  System.out.println("So, The value at key '"+key+"' is "+task.getValue(hashMap, key));
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;
		}
		case(12):{
			System.out.print("Enter number of entries: ");
			int count=scanner.nextInt();
			scanner.nextLine();
			String key;
			String value;
			try {
				Map<String,String> hashMap=task.getHashMap();
			  while(count>0) {
				System.out.print("Enter your key: ");
				key=scanner.nextLine();
				System.out.print("Enter your value: ");
				value=scanner.nextLine();
				hashMap=task.addElement(hashMap, key, value);
				count--;
			    }
			  System.out.print("Enter a key to search: ");
			  key=scanner.nextLine();
			  if(task.checkKey(hashMap, key)) {
				  System.out.println("The Key '"+key+"' is present in the hashmap");
			  }
			  else {
				  System.out.println("The Key '"+key+"' is not present in the hashmap");
			  }
			  System.out.println("So, The value at key '"+key+"' is "+task.getValue(hashMap, key));
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;
		}
		case(13):{
			System.out.print("Enter number of entries: ");
			int count=scanner.nextInt();
			scanner.nextLine();
			String key;
			String value;
			try {
				Map<String,String> hashMap=task.getHashMap();
			  while(count>0) {
				System.out.print("Enter your key: ");
				key=scanner.nextLine();
				System.out.print("Enter your value: ");
				value=scanner.nextLine();
				hashMap=task.addElement(hashMap, key, value);
				count--;
			    }
			  System.out.println("HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
			  System.out.print("Enter a key: ");
			  key=scanner.nextLine();
			  System.out.println("The value at "+key+" is "+task.nonExistingKey(hashMap,key,"zoho"));
			  System.out.println("New HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;
		}
		case(14):{
			System.out.print("Enter number of entries: ");
			int count=scanner.nextInt();
			scanner.nextLine();
			String key;
			String value;
			try {
				Map<String,String> hashMap=task.getHashMap();
			  while(count>0) {
				System.out.print("Enter your key: ");
				key=scanner.nextLine();
				System.out.print("Enter your value: ");
				value=scanner.nextLine();
				hashMap=task.addElement(hashMap, key, value);
				count--;
			    }
			  System.out.println("New HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
			  System.out.print("Enter the no. of keys to remove: ");
			  count=scanner.nextInt();
			  scanner.nextLine();
			  while(count>0) {
			    System.out.print("Enter your key to be removed: ");
			    key=scanner.nextLine();
			    hashMap=task.removeKey(hashMap, key);
			    count--;
			  }
			  System.out.println("New HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;
		}
		case(15):{
			System.out.print("Enter number of entries: ");
			int count=scanner.nextInt();
			scanner.nextLine();
			String key;
			String value;
			try {
				Map<String,String> hashMap=task.getHashMap();
			  while(count>0) {
				System.out.print("Enter your key: ");
				key=scanner.nextLine();
				System.out.print("Enter your value: ");
				value=scanner.nextLine();
				hashMap=task.addElement(hashMap, key, value);
				count--;
			    }
			  System.out.println("New HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
			  System.out.print("Enter the no. of keys to remove: ");
			  count=scanner.nextInt();
			  scanner.nextLine();
			  while(count>0) {
			    System.out.print("Enter your key to be removed: ");
			    key=scanner.nextLine();
			    System.out.print("Enter corresponding value: ");
				value=scanner.nextLine();
			    hashMap=task.removeKey(hashMap, key,value);
			    count--;
			  }
			  System.out.println("New HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;
		}
		case(16):{
			System.out.print("Enter number of entries: ");
			int count=scanner.nextInt();
			scanner.nextLine();
			String key;
			String value;
			try {
				Map<String,String> hashMap=task.getHashMap();
			  while(count>0) {
				System.out.print("Enter your key: ");
				key=scanner.nextLine();
				System.out.print("Enter your value: ");
				value=scanner.nextLine();
				hashMap=task.addElement(hashMap, key, value);
				count--;
			    }
			  System.out.println("New HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
			  System.out.print("Enter the number of values to replace: ");
			  count=scanner.nextInt();
			  scanner.nextLine();
			  while(count>0) {
			    System.out.print("Enter the replacement key: ");
			    key=scanner.nextLine();
			    System.out.print("Enter new replace value: ");
				value=scanner.nextLine();
			    hashMap=task.replaceValues(hashMap, key,value);
			    count--;
			  }
			  System.out.println("New HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;
		}
		case(17):{
			System.out.print("Enter number of entries: ");
			int count=scanner.nextInt();
			scanner.nextLine();
			String key;
			String value;
			try {
				Map<String,String> hashMap=task.getHashMap();
			  while(count>0) {
				System.out.print("Enter your key: ");
				key=scanner.nextLine();
				System.out.print("Enter your value: ");
				value=scanner.nextLine();
				hashMap=task.addElement(hashMap, key, value);
				count--;
			    }
			  System.out.println("New HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
			  System.out.print("Enter the number of values to replace: ");
			  count=scanner.nextInt();
			  scanner.nextLine();
			  String oldValue;
			  while(count>0) {
			    System.out.print("Enter the replacement key: ");
			    key=scanner.nextLine();
			    System.out.print("Enter old value: ");
				oldValue=scanner.nextLine();
			    System.out.print("Enter new replace value: ");
				value=scanner.nextLine();
			    hashMap=task.replaceValues(hashMap, key,oldValue,value);
			    count--;
			  }
			  System.out.println("New HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;
		}
		case(18):{
			System.out.print("Enter number of entries for HashMap1: ");
			int count=scanner.nextInt();
			scanner.nextLine();
			String key;
			String value;
			try {
				Map<String,String> hashMap1=task.getHashMap();
				Map<String,String> hashMap2=task.getHashMap();
			  while(count>0) {
				System.out.print("Enter your key: ");
				key=scanner.nextLine();
				System.out.print("Enter your value: ");
				value=scanner.nextLine();
				hashMap1=task.addElement(hashMap1, key, value);
				count--;
			  }
			    System.out.print("Enter number of entries for HashMap2: ");
				count=scanner.nextInt();
				scanner.nextLine();
			  while(count>0) {
				System.out.print("Enter your key: ");
				key=scanner.nextLine();
				System.out.print("Enter your value: ");
				value=scanner.nextLine();
				hashMap2=task.addElement(hashMap2, key, value);
				count--;
			  }
			  System.out.println("HashMap 1: "+hashMap1+" Size: "+task.getSize(hashMap1));
			  System.out.println("HashMap 2: "+hashMap2+" Size: "+task.getSize(hashMap2));
			  hashMap1=task.transferHashMap(hashMap1,hashMap2);
			  System.out.println("HashMap 1: "+hashMap1+" Size: "+task.getSize(hashMap1));
			  System.out.println("HashMap 2: "+hashMap2+" Size: "+task.getSize(hashMap2));
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;	
		}
		case(19):{
			System.out.print("Enter number of entries: ");
			int count=scanner.nextInt();
			scanner.nextLine();
			String key;
			String value;
			try {
				Map<String,String> hashMap=task.getHashMap();
			  while(count>0) {
				System.out.print("Enter your key: ");
				key=scanner.nextLine();
				System.out.print("Enter your value: ");
				value=scanner.nextLine();
				hashMap=task.addElement(hashMap, key, value);
				count--;
			    }
			  Iterator<Entry<String, String>> iterateMap=task.iterateHashMap(hashMap);
			  System.out.println("key=value");
			  while(iterateMap.hasNext()) {
				  System.out.println(iterateMap.next());	  
			  }
		    }
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;
		}
		case(20):{
			System.out.print("Enter number of entries: ");
			int count=scanner.nextInt();
			scanner.nextLine();
			String key;
			String value;
			try {
				Map<String,String> hashMap=task.getHashMap();
			  while(count>0) {
				System.out.print("Enter your key: ");
				key=scanner.nextLine();
				System.out.print("Enter your value: ");
				value=scanner.nextLine();
				hashMap=task.addElement(hashMap, key, value);
				count--;
			    }
			  System.out.println("New HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
			  hashMap=task.clearHashMap(hashMap);
			  System.out.println("Cleared HashMap: "+hashMap+" Size: "+task.getSize(hashMap));
		    }
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			break;
		}
		case(21):{
			String key="key";
			String value="value";
			try {
				task.getSize(null);
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			try {
				task.addElement(null, key, value);
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			try {
				task.checkKey(null, key);
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			try {
				task.checkValue(null, value);
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			try {
				task.getValue(null, key);
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			try {
				task.replaceValues(null, key, "old", "new");
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			try {
				task.replaceValues(null, key,value);
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			try {
				task.removeKey(null, key);
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			try {
				task.removeKey(null,key ,value);
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			try {
				task.transferHashMap(null, task.getHashMap());
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			try {
				task.transferHashMap(task.getHashMap(),null);
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			try {
				task.clearHashMap(null);
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			try {
				task.nonExistingKey(null, key, value);
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			try {
				task.iterateHashMap(null);
			}
			catch(InvalidInputException e) {
				e.printStackTrace();
			}
			System.out.println("Check over!");
			break;
		}
		default:{
			System.out.println("Invalid input");
		}
		
	}
  }
  scanner.close();
 }
}
