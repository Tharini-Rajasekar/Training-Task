package arraylisttask;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import util.InvalidInputException;
import util.Helper;

public class ArrayListTask {

	@SuppressWarnings("unchecked")
	public <T> List<T> getArrayList() throws InvalidInputException{
		Object obj; 
		try {
		 Class<?> arrayList= Class.forName("java.util.ArrayList");
		 Constructor<?> construct=arrayList.getConstructor();
		 obj =construct.newInstance();
		 }
		catch (ClassNotFoundException | NoSuchMethodException | SecurityException | 
				InstantiationException | IllegalAccessException |IllegalArgumentException 
				| InvocationTargetException e) {
			throw new InvalidInputException("Reflection Implementation Error!",e);
		}
		 return (List<T>)obj;
	}
	public <T> int getSize(List<T> arrayList) throws InvalidInputException{
		Helper.nullCheck(arrayList);
		return arrayList.size();
	}
	public <T> List<T> addElement(List<T> arrayList, T element) throws InvalidInputException{
		Helper.nullCheck(arrayList);
		arrayList.add(element);
		return arrayList;
	}
	public <T> List<T> addElement(List<T> arrayList, T element, int index) throws InvalidInputException{
		int size=getSize(arrayList);
		Helper.indexCheck(size,index);
		arrayList.add(index,element);
		return arrayList;
	}
	public <T> List<T> addArrayList(List<T> arrayList1, List<T> arrayList2) throws InvalidInputException{
		Helper.nullCheck(arrayList1);
		Helper.nullCheck(arrayList2);
		arrayList1.addAll(arrayList2);
		return arrayList1;
	}
	public <T> int getIndex(List<T> arrayList, T element) throws InvalidInputException{
		Helper.nullCheck(arrayList);
		return arrayList.indexOf(element);
	}
	public <T> int getLastIndex(List<T> arrayList, T element) throws InvalidInputException{
		Helper.nullCheck(arrayList);
		return arrayList.lastIndexOf(element);
	}
	public <T> Iterator<T> getIterator(List<T> arrayList) throws InvalidInputException{
		Helper.nullCheck(arrayList);
		Iterator<T> iterator=arrayList.iterator();
		return iterator;
	}
	public <T> T getElement(List<T> arrayList, int index) throws InvalidInputException{
		int size=getSize(arrayList);
		Helper.indexCheck(size,index);
		return arrayList.get(index);
	}
	public <T> List<T> getSubArray(List<T> arrayList, int begin, int end) throws InvalidInputException{
		int size=getSize(arrayList);
		Helper.indexCheck(size,begin);
		Helper.indexCheck(size,end);
		if(Helper.lessThanCheck(end, begin)) {
			throw new InvalidInputException("Invalid begin and end");
		}
		return arrayList.subList(begin, end);
	}
	public <T> List<T> removeElement(List<T> arrayList, T element) throws InvalidInputException{
		Helper.nullCheck(arrayList);
		arrayList.remove(element);
		return arrayList;
	}
	public <T> List<T> removeElement(List<T> arrayList, int index) throws InvalidInputException{
		int size=getSize(arrayList);
		Helper.indexCheck(size,index);
		arrayList.remove(index);
		return arrayList;
	}
	public <T> List<T>  removeArrElement(List<T> arrayList1, List<T> arrayList2) throws InvalidInputException{
		Helper.nullCheck(arrayList1);
		Helper.nullCheck(arrayList2);
		arrayList1.removeAll(arrayList2);
		return arrayList1;
	}
	public <T> List<T>  retainElements(List<T>  arrayList1, List<T>  arrayList2) throws InvalidInputException{
		Helper.nullCheck(arrayList1);
		Helper.nullCheck(arrayList2);
		arrayList1.retainAll(arrayList2);
		return arrayList1;
	}
	public <T> List<T> clearElements(List<T> arrayList) throws InvalidInputException{
		Helper.nullCheck(arrayList);
		arrayList.clear();
		return arrayList;
	}
	public <T> boolean checkElement(List<T> arrayList, T element) throws InvalidInputException{
		Helper.nullCheck(arrayList);
		return arrayList.contains(element);
	}
	public <T>void insertList(List<List<T>> listOfList,List<T>list) throws InvalidInputException{
		Helper.nullCheck(list);
		Helper.nullCheck(listOfList);
		listOfList.add(list);
	}
}
