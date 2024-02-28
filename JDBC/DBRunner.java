package runner;

import java.util.Map;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;
import java.util.List;
import task.DBConnector;
import task.DBTask;
import task.Dependent;
import task.Employee;
import util.Helper;
import util.ApplicationException;

public class DBRunner {
	private static final Logger log = Logger.getLogger(DBTask.class.getName());
	
	public static void main(String[] args) {

		DBTask task= new DBTask();
		DBConnector object=new DBConnector();
		Scanner scanner=new Scanner(System.in);
		System.out.println("Select your Database Service Provider: \n1.MYSQL \n2.POSTGRESQL");
		System.out.print("Enter your Preference: ");
		int service=scanner.nextInt();
		scanner.nextLine();
		object.setServiceProvider(service);
		System.out.print("Enter your Host Name: ");
		String host=scanner.nextLine();
		object.setHost(host);
		System.out.print("Enter your Port: ");
		int port=scanner.nextInt();
		scanner.nextLine();
		object.setPort(port);
		System.out.print("Enter your Database to use: ");
		String databaseName=scanner.nextLine();
		object.setDatabaseName(databaseName);
		System.out.print("Enter your User Name: ");
		String userName=scanner.nextLine();
		object.setUserName(userName);
		System.out.print("Enter your Password: ");
		String password=scanner.nextLine();
		object.setPassword(password);
		try {
			task.setConnection(object);
		System.out.println("...Connected...");
		int option=1;
		while(option>0) {
			System.out.print("Enter your question number:");
			option=scanner.nextInt();
			scanner.nextLine();
			switch(option) {	
			case(1):{
				try {
					System.out.print("Enter your Table Name: ");
					String tableName=scanner.nextLine();
					task.createTable(tableName);
					System.out.println("Table created");
				}
				catch(ApplicationException e) {
					logCause(e);
					System.out.println(e.getMessage());
				}
				break;
			}
			case(2):{
				try {
					System.out.print("Enter your Table Name: ");
					String tableName=scanner.nextLine();
					System.out.println("Data Entry For: \n1.All fields\n2.Custom fields");
					System.out.print("Select your preference: ");
					int select=scanner.nextInt();
					scanner.nextLine();
					List<List<String>> listOfList=Helper.getArrayList();
					switch(select){
					case (1): {
						try {
							System.out.print("Enter number of records to insert: ");
							int count=scanner.nextInt();
							scanner.nextLine();
							List<Employee> result=Helper.getArrayList();
							while(count>0) {
								Employee employee=new Employee();
								System.out.print("Enter Employee Name: ");
								employee.setName(scanner.nextLine());
								System.out.print("Enter Employee's Department: ");
								employee.setDepartment(scanner.nextLine());
								System.out.print("Enter Employee's Email: ");
								employee.setEmail(scanner.nextLine());
								System.out.print("Enter Employee's Mobile: ");
								employee.setMobile(scanner.nextLong());
								scanner.nextLine();
								Helper.addElement(result,employee);
								count--;
							}
							task.insertAll(tableName, result);
						}
						catch (ApplicationException e) {
							logCause(e);
							System.out.println(e.getMessage());
						}
						break;
					}
					case(2):{
						System.out.print("Enter the number of columns: ");
						int count=scanner.nextInt();
						scanner.nextLine();
						String name;
						List<String> fieldName = Helper.getArrayList();
						while(count>0) {
							System.out.print("Enter your column name: ");
							name=scanner.nextLine();
							Helper.addElement(fieldName,name);
							count--;
						}
						System.out.print("Enter Number of records: ");
						count=scanner.nextInt();
						scanner.nextLine();
						String input;
						for (int i=1;i<=count;i++) {
							List<String> value=Helper.getArrayList();
							System.out.println("Record No: "+i);
							for(String str:fieldName) {
								System.out.print("Enter the "+str+": ");
								input=scanner.nextLine();
								Helper.addElement(value,input);
							}
							Helper.insertList(listOfList,value);
						}
						task.insert(tableName,fieldName,listOfList);
						break;
					}
					}
				}
				catch (ApplicationException e) {
					logCause(e);
					System.out.println(e.getMessage());
				}	
				break;
			}
			case(3):{
				System.out.print("Enter your Table Name: ");
				String tableName=scanner.nextLine();
				System.out.print("Enter number of search parameter: ");
				int count=scanner.nextInt();
				scanner.nextLine();
				try {
					Map<String,String> record=Helper.getHashMap();
					String operator=null;
					if(count>1) {
						System.out.print("Enter Relation of each search parameter: ");
						operator=scanner.nextLine();
					}
					String name;
					String value;
					while(count>0) {
						System.out.print("Enter the Parameter name: ");
						name=scanner.nextLine();
						System.out.print("Enter the Parameter value: ");
						value=scanner.nextLine();
						Helper.addElement(record,name,value);
						count--;
					}
					List<Map<String,String>> result=task.getRecord(tableName,record,operator);
					for(Map<String,String> output: result) {
						System.out.println(output);
					}
				}
				catch (ApplicationException e) {
					logCause(e);
					System.out.println(e.getMessage());
				}
				break;
			}
			case(4):{
				System.out.print("Enter your Table Name: ");
				String tableName=scanner.nextLine();
				System.out.print("Enter number of set parameter: ");
				int count=scanner.nextInt();
				scanner.nextLine();
				String name;
				String value;
				try {
					Map<String,String> setData=Helper.getHashMap();
					while(count>0) {
						System.out.print("Enter the Parameter name: ");
						name=scanner.nextLine();
						System.out.print("Enter the Parameter value: ");
						value=scanner.nextLine();
						Helper.addElement(setData,name,value);
						count--;
					}
					System.out.print("Enter your Number of condition: ");
					count=scanner.nextInt();
					scanner.nextLine();
					String operator = null;
					if(count>1) {
						System.out.print("Enter your Logical operator: ");
						operator=scanner.nextLine();
					}
					Map<String,String> conditionData=Helper.getHashMap();
					while(count>0) {
					System.out.print("Enter the record column name to update:");
					name=scanner.nextLine();
					System.out.print("Enter the column value: ");
					value=scanner.nextLine();
					Helper.addElement(conditionData,name,value);
					}
					task.updateRecord(tableName, setData, conditionData,operator);
				}
				catch (ApplicationException e) {
					logCause(e);
					System.out.println(e.getMessage());
				}
				break;
			}
			case(5):{
				System.out.print("Enter your Table Name: ");
				String tableName=scanner.nextLine();
				System.out.print("Enter the number of record to retrive: ");
				int limit=scanner.nextInt();
				scanner.nextLine();
				int count=0;
				try {
					List<Map<String, String>> dataList =task.getRecords(tableName, limit);
					for (Map<String, String> data : dataList) {
						count++;
						System.out.println("Employee Data - Record "+count);
						for (Map.Entry<String, String> entry : data.entrySet()) {
							System.out.println(entry.getKey() + ": " + entry.getValue());
						}
					}
				}
				catch (ApplicationException e) {
					logCause(e);
					System.out.println(e.getMessage());
				}
				break;
			}
			case(6):{
				System.out.print("Enter your Table Name: ");
				String tableName=scanner.nextLine();
				System.out.print("Enter the number of record to retrive: ");
				int limit=scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter your Column Name to Order: ");
				String column=scanner.nextLine();
				System.out.println("Select to Order By \n1.Descending order:  \n2.Ascending order");
				int select=scanner.nextInt();
				scanner.nextLine();
				int count=0;
				try {
					List<Map<String, String>> dataList =Helper.getArrayList();
					if(select==1) {
						dataList =task.sortRecordDes(tableName, limit, column);
					}
					else {
						dataList =task.sortRecordAsc(tableName, limit, column);
					}
					for (Map<String, String> data : dataList) {
						count++;
						System.out.println("Employee Data - Record "+count);
						for (Map.Entry<String, String> entry : data.entrySet()) {
							System.out.println(entry.getKey() + ": " + entry.getValue());
						}
					}
				}
				catch (ApplicationException e) {
					logCause(e);
					System.out.println(e.getMessage());
				}
				break;
			}
			case(7):{
				System.out.print("Enter your Table Name: ");
				String tableName=scanner.nextLine();
				System.out.print("Enter number of delete parameters: ");
				int count=scanner.nextInt();
				scanner.nextLine();
				String relation=null;
				try {
					Map<String,String> condition=Helper.getHashMap();
					String key;
					String value;
					while(count>0) {
						System.out.print("Enter the Parameter name: ");
						key=scanner.nextLine();
						System.out.print("Enter the Parameter value: ");
						value=scanner.nextLine();
						Helper.addElement(condition,key,value);
						count--;
					}
					if(count>1) {
						System.out.print("Enter the Parameters Filter(AND/OR): ");
						relation=scanner.nextLine();
					}
					task.deleteRecord(tableName,condition,relation);
				}
				catch (ApplicationException e) {
					logCause(e);
					System.out.println(e.getMessage());
				}
				break;
			}
			case(8):{
				System.out.print("Enter your option: ");
				int select=scanner.nextInt();
				scanner.nextLine();
				switch(select) {
				case(2):{
					try {
						System.out.print("Enter your Table Name: ");
						String tableName=scanner.nextLine();
						System.out.print("Enter number of records to insert: ");
						int count=scanner.nextInt();
						scanner.nextLine();
						List<Employee> result=Helper.getArrayList();
						while(count>0) {
							Employee employee=new Employee();
							System.out.print("Enter Employee Name: ");
							employee.setName(scanner.nextLine());
							System.out.print("Enter Employee's Department: ");
							employee.setDepartment(scanner.nextLine());
							System.out.print("Enter Employee's Email: ");
							employee.setEmail(scanner.nextLine());
							System.out.print("Enter Employee's Mobile: ");
							employee.setMobile(scanner.nextLong());
							scanner.nextLine();
							Helper.addElement(result,employee);
							count--;
						}
						task.insertAll(tableName, result);
					}
					catch (ApplicationException e) {
						logCause(e);
						System.out.println(e.getMessage());
					}
					break;
				}
				case(3):{
					System.out.print("Enter Table Name: ");
					String name=scanner.nextLine();
					System.out.print("Enter the number of conditions: ");
					int number=scanner.nextInt();
					scanner.nextLine();
					String operator=null;
					String key;
					String value;
					if(number>1) {
						System.out.print("Enter the relation operator(AND/OR): ");
						operator=scanner.nextLine();
					}
					try {
						Map<String,String> condition=Helper.getHashMap();
						while(number>0) {
							System.out.print("Enter the column name: ");
							key=scanner.nextLine();
							System.out.print("Enter the value: ");
							value=scanner.nextLine();
							Helper.addElement(condition,key,value);
							number--;
						}
						List<Employee> result = task.getRecord(condition,name,operator);
						int count=0;
						for(Employee employee: result) {
							count++;
							System.out.println("Employee :"+count);
							System.out.println("Emp-ID: "+employee.getId());
							System.out.println("Name: "+employee.getName());
							System.out.println("Department: "+employee.getDepartment());
							System.out.println("Email: "+employee.getEmail());
							System.out.println("Mobile: "+employee.getMobile());
						}
					}
					catch (ApplicationException e) {
						logCause(e);
						System.out.println(e.getMessage());
					}
					break;
				}
				case(5):{
					System.out.print("Enter your Table Name: ");
					String tableName=scanner.nextLine();
					System.out.print("Enter number of Records: ");
					int limit=scanner.nextInt();
					scanner.nextLine();
					try {
						List<Employee> result=task.getRecords(limit,tableName);
						int count=0;
						for(Employee employee: result) {
							count++;
							System.out.println("Employee :"+count);
							System.out.println("ID: "+employee.getId());
							System.out.println("Name: "+employee.getName());
							System.out.println("Department: "+employee.getDepartment());
							System.out.println("Email: "+employee.getEmail());
							System.out.println("Mobile: "+employee.getMobile());
						}
					}
					catch (ApplicationException e) {
						logCause(e);
						System.out.println(e.getMessage());
					}
					break;
				}
				case(6):{
					System.out.print("Enter your Table Name: ");
					String tableName=scanner.nextLine();
					System.out.print("Enter number of Records: ");
					int limit=scanner.nextInt();
					scanner.nextLine();
					System.out.print("Enter your Column Name to Order: ");
					String column=scanner.nextLine();
					System.out.println("Select to Order By \n1.Descending order:  \n2.Ascending order");
					int order=scanner.nextInt();
					scanner.nextLine();
					try {
						List<Employee> result=null;
						if(order==1) {
							result=task.sortDesc(tableName, limit, column);
						}
						else if(order==2) {
							result=task.sortAsc(tableName,limit, column);
						}
						else {
							System.out.println("Wrong option!");
						}
						int count=0;
						for(Employee employee: result) {
							count++;
							System.out.println("Employee :"+count);
							System.out.println("ID: "+employee.getId());
							System.out.println("Name: "+employee.getName());
							System.out.println("Department: "+employee.getDepartment());
							System.out.println("Email: "+employee.getEmail());
							System.out.println("Mobile: "+employee.getMobile());
						}
					}
					catch (ApplicationException e) {
						logCause(e);
						System.out.println(e.getMessage());
					}
					break;
				}
				}
				break;
			}
			case(9):{
				try{
					System.out.println("Creating child table...");
					System.out.print("Enter your Employee Table Name: ");
					String parentTable=scanner.nextLine();
					System.out.print("Enter your Dependent Table Name: ");
					String childTable=scanner.nextLine();
					task.createChildTable(childTable,parentTable);
					System.out.println("Table created");
				}
				catch(ApplicationException e) {
					logCause(e);
					System.out.println(e.getMessage());
				}
				break;
			}
			case(10):{
				try {
					System.out.print("Enter your Dependent Table Name: ");
					String tableName=scanner.nextLine();
					System.out.print("Enter number of records to insert: ");
					int count=scanner.nextInt();
					scanner.nextLine();
					List<Dependent> result=Helper.getArrayList();
					while(count>0) {
						Dependent dependent=new Dependent();
						System.out.print("Enter Employee ID: ");
						dependent.setEmpId(scanner.nextInt());
						scanner.nextLine();
						System.out.print("Enter Dependent Name: ");
						dependent.setName(scanner.nextLine());
						System.out.print("Enter Dependent's Age: ");
						dependent.setAge(scanner.nextInt());
						scanner.nextLine();
						System.out.print("Enter Dependent's Relation: ");
						dependent.setRelation(scanner.nextLine());
						Helper.addElement(result,dependent);
						count--;
					}
					task.insertInChild(tableName,result);
				}
				catch (ApplicationException e) {
					logCause(e);
					System.out.println(e.getMessage());
				}
				break;
			}
			case(11):{
				System.out.print("Enter your Employee Table Name: ");
				String parentTable=scanner.nextLine();
				System.out.print("Enter your Dependent Table Name: ");
				String childTable=scanner.nextLine();
				System.out.print("Enter Employee ID: ");
				int id=scanner.nextInt();
				scanner.nextLine();
				try {
					List<Dependent> result=task.getJoinRecord(parentTable,childTable,id);
					int count=0;
					for(Dependent dependent: result) {
						count++;
						System.out.println("Dependent :"+count);
						System.out.println("Employee ID: "+dependent.getEmpId());
						System.out.println("Dependent ID: "+dependent.getDepId());
						System.out.println("Name: "+dependent.getName());
						System.out.println("Age: "+dependent.getAge());
						System.out.println("Relation: "+dependent.getRelation());
					}
				} 
				catch (ApplicationException e) {
					logCause(e);
					System.out.println(e.getMessage());
				}
				break;
			}
			case(12):{
				System.out.print("Enter your Employee Table Name: ");
				String parentTable=scanner.nextLine();
				System.out.print("Enter your Dependent Table Name: ");
				String childTable=scanner.nextLine();
				System.out.print("Enter Column Name to Sort By: ");
				String column=scanner.nextLine();
				System.out.print("Enter the number of records: ");
				int limit=scanner.nextInt();
				scanner.nextLine();
				try {
					Map<Integer,List<Dependent>> result=task.getJoinRecord(parentTable,childTable,column,limit);
					System.out.println(result);
					int count=0;
					System.out.print("Enter your Employee ID: ");
					int id=scanner.nextInt();
					scanner.nextLine();
					List<Dependent> record=result.get(id);
					for(Dependent dependent: record) {
						count++;
						System.out.println("Dependent: "+count);
					//	System.out.println("Employee ID: "+dependent.getEmpId());
						System.out.println("Dependent ID: "+dependent.getDepId());
						System.out.println("Name: "+dependent.getName());
						System.out.println("Age: "+dependent.getAge());
						System.out.println("Relation: "+dependent.getRelation());
					}
				} 
				catch (ApplicationException e) {
					logCause(e);
					System.out.println(e.getMessage());
				}
				break;
			}
			default:{
				System.out.println("Invalid Input!!");
			}
			}
		}
		}
		catch (ApplicationException e) {
			logCause(e);
			System.out.println(e.getMessage());
		}
		try {
			task.disconnectDB();
		} 
		catch (ApplicationException e) {
			logCause(e);
			System.out.println(e.getMessage());
		}

		scanner.close();
	}
	private static void logCause(Exception exp) {
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler("DBLog.txt", true);
			fileHandler.setFormatter(new SimpleFormatter());
			log.setLevel(Level.SEVERE);
			log.setUseParentHandlers(false);
			log.addHandler(fileHandler);
			log.log(Level.SEVERE, "Exception occurred: " + exp.getMessage(), exp.getCause());
		}
		catch (SecurityException | IOException e) {
			e.printStackTrace();
		} 
		finally {
			if (fileHandler != null) {
				fileHandler.close();
			}
		}
	}	
}
