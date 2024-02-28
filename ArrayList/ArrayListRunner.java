package arrayListtest;
import java.util.List;
import java.util.Iterator;
import java.util.Scanner;
import arraylisttask.ArrayListTask;
import util.InvalidInputException;
import pack.Book;

public class ArrayListRunner {

	public static void main(String[] args) {
		
	  ArrayListTask task = new ArrayListTask();
	  Scanner scanner=new Scanner(System.in);
	  int option=0;
	  while(option>=0){
	   System.out.print("Enter the question number:") ;
	   option=scanner.nextInt();	
	   scanner.nextLine();
	   switch(option) {
		case(1):{
		  try {	
			List<Object> arrList=task.getArrayList();
			System.out.println("New ArrayList: "+arrList+" Size: "+task.getSize(arrList));
		  }
		  catch(InvalidInputException e) {
			  e.printStackTrace();
		  }
		  break;
		}
		case(2):{
		  try {	
			System.out.print("Enter the number of Strings to append: ");
			int count=scanner.nextInt();
			scanner.nextLine();
			List<String> arrList=task.getArrayList();
			while(count>0) {
				System.out.print("Enter the String: ");
				String str=scanner.nextLine();
				arrList=task.addElement(arrList, str);
				count--;
			}
			System.out.println("String ArrayList: "+arrList+" Size: "+task.getSize(arrList));
		  }
		  catch(InvalidInputException e) {
			  e.printStackTrace();
		  }
		  break;
		}
		case(3):{
		  try {	
			System.out.print("Enter the number of Integers to append: ");
			int count=scanner.nextInt();
			List<Integer>  arrList=task.getArrayList();
			while(count>0) {
				System.out.print("Enter your Integer: ");
				int number=scanner.nextInt();
				arrList=task.addElement(arrList, number);
				count--;
			}
			scanner.nextLine();
			System.out.println("String ArrayList: "+arrList+" Size: "+task.getSize(arrList));
		  }
		  catch(InvalidInputException e) {
		    e.printStackTrace();
		  }
		  break;
		}
		case(4):{
		  try {
			  System.out.print("Enter the number of Custom book objects to add: ");
			  int count=scanner.nextInt();
			  scanner.nextLine();
			  List<Object> arrList=task.getArrayList();
			  while(count>0) {
				  System.out.print("Enter your book name: ");
				  String name=scanner.nextLine();
				  System.out.print("Enter your Author name: ");
				  String author=scanner.nextLine();
				  arrList=task.addElement(arrList, new Book(name,author));
				  count--;
			  }
			  System.out.println("String ArrayList: "+arrList+" Size: "+task.getSize(arrList));
		  }
		  catch(InvalidInputException e) {
			    e.printStackTrace();
		  }
		  break;
		}
		case(5):{
			try {
				System.out.print("Enter the number of Strings to append: ");
				int count=scanner.nextInt();
				scanner.nextLine();
				List<Object> arrList=task.getArrayList();
				while(count>0) {
					System.out.print("Enter the String: ");
					String str=scanner.nextLine();
					arrList=task.addElement(arrList, str);
					count--;
				}
				System.out.print("Enter the number of Integers to append: ");
				count=scanner.nextInt();
				while(count>0) {
					System.out.print("Enter your Integer: ");
					int number=scanner.nextInt();
					arrList=task.addElement(arrList, number);
					count--;
				}
				scanner.nextLine();
				System.out.print("Enter the number of Custom book objects to add: ");
				count=scanner.nextInt();
				scanner.nextLine();
				while(count>0) {
				    System.out.print("Enter your book name: ");
					String name=scanner.nextLine();
					System.out.print("Enter your Author name: ");
					String author=scanner.nextLine();
					arrList=task.addElement(arrList, new Book(name,author));
					count--;
				}
				System.out.println("String ArrayList: "+arrList+" Size: "+task.getSize(arrList));
			}
			catch(InvalidInputException e) {
			    e.printStackTrace();
		  }
		  break;
		}
		case(6):{
			try {	
				System.out.print("Enter the number of Strings to append: ");
				int count=scanner.nextInt();
				scanner.nextLine();
				List<String> arrList=task.getArrayList();
				String str;
				while(count>0) {
					System.out.print("Enter the String: ");
					str=scanner.nextLine();
					arrList=task.addElement(arrList, str);
					count--;
				}
				System.out.print("Enter your String to search: ");
				str=scanner.nextLine();
				System.out.println(str+" is present at index "+task.getIndex(arrList, str));
				System.out.println("String ArrayList: "+arrList+" Size: "+task.getSize(arrList));
			  }
			  catch(InvalidInputException e) {
				    e.printStackTrace();
			  }
			  break;
			}
		case(7):{
			try {	
				System.out.print("Enter the number of Strings to append: ");
				int count=scanner.nextInt();
				scanner.nextLine();
				List<String> arrList=task.getArrayList();
				while(count>0) {
					System.out.print("Enter the String: ");
					String str=scanner.nextLine();
					arrList=task.addElement(arrList, str);
					count--;
				}
				int size=task.getSize(arrList);
				int i;
			    Iterator<String> iterator=task.getIterator(arrList);
				for(i=0;i<size;i++) {
					System.out.println(iterator.next());
				}
			}
			catch(InvalidInputException e) {
			    e.printStackTrace();
		    }
		    break;
		}	
		case(8):{
			try {
				System.out.print("Enter the number of Strings to append: ");
				int count=scanner.nextInt();
				scanner.nextLine();
				List<String> arrList=task.getArrayList();
				while(count>0) {
					System.out.print("Enter the String: ");
					String str=scanner.nextLine();
					arrList=task.addElement(arrList, str);
					count--;
				}
				System.out.print("Enter the Index positon: ");
				int index=scanner.nextInt();
				scanner.nextLine();
				System.out.println("Element at index "+index+" is "+task.getElement(arrList, index));
				System.out.println("String ArrayList: "+arrList+", Size: "+task.getSize(arrList));
			}
			catch(InvalidInputException e) {
			    e.printStackTrace();
		    }
		    break;
		}
		case(9):{
			try {
				System.out.print("Enter the number of Strings to append: ");
				int count=scanner.nextInt();
				scanner.nextLine();
				List<String> arrList=task.getArrayList();
				String str;
				while(count>0) {
					System.out.print("Enter the String: ");
					str=scanner.nextLine();
					arrList=task.addElement(arrList, str);
					count--;
				}
				System.out.print("Enter the String to search: ");
				str=scanner.nextLine();
				System.out.println("The first appearance of "+str+" is "+task.getIndex(arrList, str));
				System.out.println("The Last appearance  of "+str+" is "+task.getLastIndex(arrList, str));
			}
			catch(InvalidInputException e) {
			    e.printStackTrace();
		    }
		    break;
		}
		case(10):{
			try {
				System.out.print("Enter the number of Strings to append: ");
				int count=scanner.nextInt();
				scanner.nextLine();
				List<String> arrList=task.getArrayList();
				String str;
				while(count>0) {
					System.out.print("Enter the String: ");
					str=scanner.nextLine();
					arrList=task.addElement(arrList, str);
					count--;
				}
				System.out.println("String ArrayList: "+arrList+", Size: "+task.getSize(arrList));
				System.out.print("How many Strings to add? ");
				count=scanner.nextInt();
				scanner.nextLine();
				int index;
				while(count>0) {
					System.out.print("Enter your String: ");
					str=scanner.nextLine();
					System.out.print("Enter the index: ");
					index=scanner.nextInt();
					scanner.nextLine();
					arrList=task.addElement(arrList, str, index);
					count--;
				}
				System.out.println("String ArrayList: "+arrList+", Size: "+task.getSize(arrList));
			}
			catch(InvalidInputException e) {
			    e.printStackTrace();
		    }
		    break;
		}
		case(11):{
			try {
				System.out.print("Enter the number of Strings to append: ");
				int count=scanner.nextInt();
				scanner.nextLine();
				List<String> arrList=task.getArrayList();
				String str;
				while(count>0) {
					System.out.print("Enter the String: ");
					str=scanner.nextLine();
					arrList=task.addElement(arrList, str);
					count--;
				}
				System.out.print("Enter the begin index: ");
				int begin=scanner.nextInt();
				System.out.print("Enter the end index: ");
				int end=scanner.nextInt();
				System.out.println("Old ArrayList: "+arrList);
				List<String> arrList1=task.getSubArray(arrList, begin, end);
				System.out.println("New ArrayList: "+arrList1);
			}
			catch(InvalidInputException e) {
			    e.printStackTrace();
		    }
		    break;
		}
		case(12):{
			try {
				System.out.print("Enter the number of Strings to append(For ArrayList 1): ");
				int count=scanner.nextInt();
				scanner.nextLine();
				List<String> arrList1=task.getArrayList();
				String str;
				while(count>0) {
					System.out.print("Enter the String: ");
					str=scanner.nextLine();
					arrList1=task.addElement(arrList1, str);
					count--;
				}
				System.out.println("ArrayList 1: "+arrList1+", Size: "+task.getSize(arrList1));
				System.out.print("Enter the number of Strings to append(For ArrayList 2): ");
				count=scanner.nextInt();
				scanner.nextLine();
				List<String> arrList2=task.getArrayList();
				while(count>0) {
					System.out.print("Enter the String: ");
					str=scanner.nextLine();
					arrList2=task.addElement(arrList2, str);
					count--;
				}
				System.out.println("ArrayList2: "+arrList2+", Size: "+task.getSize(arrList2));
				List<String> arrList3=task.addArrayList(arrList1, arrList2);
				System.out.println("ArrayList3: "+arrList3+", Size: "+task.getSize(arrList3));
			}
			catch(InvalidInputException e) {
			    e.printStackTrace();
		    }
		    break;
		}
		case(13):{
			try {
				System.out.print("Enter the number of Strings to append(For ArrayList 1): ");
				int count=scanner.nextInt();
				scanner.nextLine();
				List<String> arrList1=task.getArrayList();
				String str;
				while(count>0) {
					System.out.print("Enter the String: ");
					str=scanner.nextLine();
					arrList1=task.addElement(arrList1, str);
					count--;
				}
				System.out.println("ArrayList 1: "+arrList1+", Size: "+task.getSize(arrList1));
				System.out.print("Enter the number of Strings to append(For ArrayList 2): ");
				count=scanner.nextInt();
				scanner.nextLine();
				List<String> arrList2=task.getArrayList();
				while(count>0) {
					System.out.print("Enter the String: ");
					str=scanner.nextLine();
					arrList2=task.addElement(arrList2, str);
					count--;
				}
				System.out.println("ArrayList2: "+arrList2+", Size: "+task.getSize(arrList2));
				List<String> arrList3=task.addArrayList(arrList2, arrList1);
				System.out.println("ArrayList3: "+arrList3+", Size: "+task.getSize(arrList3));
			}
			catch(InvalidInputException e) {
			    e.printStackTrace();
		    }
		    break;
		}
		case(14):{
			try {
				System.out.print("Enter the number of decimal values to append: ");
				int count=scanner.nextInt();
				List<Double> arrList=task.getArrayList();
				double decimal;
				while(count>0) {
					System.out.print("Enter your Decimal Value: ");
					decimal=scanner.nextDouble();
					arrList=task.addElement(arrList, decimal);
					count--;
				}
				System.out.println("ArrayList: "+arrList+" size: "+task.getSize(arrList));
				System.out.print("Number of decimal elements to be remove: ");
				count=scanner.nextInt();	
				while(count>0) {
					System.out.print("Enter the decimal value to remove: ");
					decimal=scanner.nextDouble();
					arrList=task.removeElement(arrList, decimal);
					count--;
				}
				System.out.println("New Removed ArrayList: "+arrList+" size: "+task.getSize(arrList));
			}
			catch(InvalidInputException e) {
			    e.printStackTrace();
		    }
		    break;
		}
		case(15):{
			try {
				System.out.print("Enter the number of decimal values to append: ");
				int count=scanner.nextInt();
				List<Double> arrList=task.getArrayList();
				double decimal;
				while(count>0) {
					System.out.print("Enter your Decimal Value: ");
					decimal=scanner.nextDouble();
					arrList=task.addElement(arrList, decimal);
					count--;
				}
				System.out.println("ArrayList: "+arrList+" size: "+task.getSize(arrList));
				System.out.print("Number of decimal elements to be remove: ");
				count=scanner.nextInt();
				int index;
				while(count>0) {
					System.out.print("Enter the index position: ");
					index=scanner.nextInt();
					arrList=task.removeElement(arrList, index);
					count--;
				}
				System.out.println("New Removed ArrayList: "+arrList+" size: "+task.getSize(arrList));
			}
			catch(InvalidInputException e) {
			    e.printStackTrace();
		    }
		    break;
		}
		case(16):{
			try {
				System.out.print("Enter the number of Strings to append: ");
				int count=scanner.nextInt();
				scanner.nextLine();
				List<String> arrList1=task.getArrayList();
				String str;
				while(count>0) {
					System.out.print("Enter your String: ");
					str=scanner.nextLine();
					arrList1=task.addElement(arrList1, str);
					count--;
				}
				System.out.print("Number of Elements to remove: ");
				count=scanner.nextInt();
				scanner.nextLine();
				List<String> arrList2=task.getArrayList();
				while(count>0) {
					System.out.print("Enter your String: ");
					str=scanner.nextLine();
					arrList2=task.addElement(arrList2, str);
					count--;
				}
				System.out.println("ArrayList: "+arrList1+" size: "+task.getSize(arrList1));
				arrList1=task.removeArrElement(arrList1, arrList2);
				System.out.println("New ArrayList: "+arrList1+" size: "+task.getSize(arrList1));
			}
			catch(InvalidInputException e) {
			    e.printStackTrace();
		    }
		    break;
		}
		case(17):{
			try {
				System.out.print("Enter the number of Strings to append: ");
				int count=scanner.nextInt();
				scanner.nextLine();
				List<String>  arrList1=task.getArrayList();
				String str;
				while(count>0) {
					System.out.print("Enter your String: ");
					str=scanner.nextLine();
					arrList1=task.addElement(arrList1, str);
					count--;
				}
				System.out.print("Number of Elements to retain: ");
				count=scanner.nextInt();
				scanner.nextLine();
				List<String>  arrList2=task.getArrayList();
				while(count>0) {
					System.out.print("Enter your String: ");
					str=scanner.nextLine();
					arrList2=task.addElement(arrList2, str);
					count--;
				}
				System.out.println("ArrayList: "+arrList1+" size: "+task.getSize(arrList1));
				arrList1=task.retainElements(arrList1, arrList2);
				System.out.println("New ArrayList: "+arrList1+" size: "+task.getSize(arrList1));
			}
			catch(InvalidInputException e) {
			    e.printStackTrace();
		    }
		    break;
		}
		case(18):{
			try {
				System.out.print("Enter the number of longs to append: ");
				int count=scanner.nextInt();
				List<Long> arrList=task.getArrayList();
				long value;
				while(count>0) {
					System.out.print("Enter your Long Value: ");
					value=scanner.nextLong();
					arrList=task.addElement(arrList, value);
					count--;
				}
				
				System.out.println("ArrayList: "+arrList);
				System.out.println("ArrayList Clear: "+task.clearElements(arrList));
			}
			catch(InvalidInputException e) {
			    e.printStackTrace();
		    }
		    break;
		}
		case(19):{
			try {
				System.out.print("Enter the number of Strings to append: ");
				int count=scanner.nextInt();
				scanner.nextLine();
				List<String> arrList=task.getArrayList();
				String str;
				while(count>0) {
					System.out.print("Enter the String: ");
					str=scanner.nextLine();
					arrList=task.addElement(arrList, str);
					count--;
				}
				System.out.print("Enter the string to check: ");
				str=scanner.nextLine();
				if(task.checkElement(arrList, str)) {
					System.out.println("The array contains the string: "+str);
				}
				else {
					System.out.println("The array doesn't contain the string: "+str);
				}	
			}
		    catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
	        break;
		}
		case(20):{
			String str="Check";
			List<String> strArrList = null;
			try {
				strArrList = task.getArrayList();
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}
			try {
			strArrList = task.addElement(strArrList, str);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
			try {
				task.getSize(null);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.addElement(null, str);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }           
            try {
				task.addElement(strArrList, str, 4);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.addElement(strArrList, str, -1);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.addArrayList(null, strArrList);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.addArrayList(strArrList, null);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.getIndex(null, str);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.getLastIndex(null, str);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.getIterator(null);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.getElement(null, 0);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.getElement(strArrList, -1);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.getElement(strArrList, 3);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.getSubArray(null, 0, 1);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.getSubArray(strArrList, -1, 0);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
            	task.getSubArray(strArrList, 0, 1);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
            	task.getSubArray(strArrList, 3, 1);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.removeElement(null, 1.0);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.removeElement(strArrList, 2);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.removeElement(strArrList, "Test");
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
            	task.removeElement(strArrList, -1);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.removeArrElement(null, strArrList);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.removeArrElement(strArrList, null);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.clearElements(null);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.retainElements(null, strArrList);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.retainElements(strArrList, null);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            try {
				task.checkElement(null, str);
			}
			catch(InvalidInputException e) {
		         e.printStackTrace();
	        }
            System.out.println("Check over!");
            break;
		}
		default:{
			System.out.println("Invalid Option!");
		}
	  }
	}  
	scanner.close();
        
	}

}
