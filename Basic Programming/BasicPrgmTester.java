package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;
import task.BasicPrgmTask;
import task.BasicPrgmTask.RainbowColor;
import util.InvalidInputException;
import sample.Sample;
import sample.SingletonClass;
import sample.Model;


public class BasicPrgmTester {
	static final Logger log=Logger.getLogger(BasicPrgmTester.class.getName());
	public static void main(String...args) {
		
         BasicPrgmTask newTask=new BasicPrgmTask();
		 Scanner scanner=new Scanner(System.in);
		 try {
			 FileHandler fileHandler =new FileHandler("MyLog.log",true);
			 fileHandler.setFormatter(new SimpleFormatter());
			 log.addHandler(fileHandler);
		 }
		 catch(IOException e) {
				log.log(Level.SEVERE,"Exception stack trace:",e);
		}
		 
		 int option=1;
		 while(option>0) {
			 System.out.print("Enter your question number:");
			option=scanner.nextInt();
			scanner.nextLine();
		    switch(option) {	
			case(1):{
			  try {	
				System.out.print("Enter your file name: ");
				String fileName=scanner.nextLine();
				String userDir=System.getProperty("user.dir");
				File newFile=newTask.createFile(userDir,fileName);
				System.out.print("Enter the no. of statements");
				int count=scanner.nextInt();
				scanner.nextLine();
				List<String> list=new ArrayList<String>();
				for(int i=0;i<count;i++) {
					System.out.println("Enter your statement to write:");
					list.add(scanner.nextLine());
				  }
				newTask.writeToFile(newFile, list);
				}
				catch(IOException e) {
					log.log(Level.SEVERE,"Exception stack trace:",e);
				}
				break;
			}
			case(2):{
				System.out.print("Enter number of entries: ");
				int count=scanner.nextInt();
				scanner.nextLine();
				Properties prop=newTask.createPropFile();
				String key;
				String value;
				Map<String,String> hashMap=newTask.getHashMap();
				try {
				  while(count>0) {
					System.out.print("Enter your key: ");
					key=scanner.nextLine();
					System.out.print("Enter your value: ");
					value=scanner.nextLine();
					hashMap=newTask.addElement(hashMap, key, value);
					count--;
				    }
				  prop=newTask.setProp(prop,hashMap );
				  System.out.print("Enter your newFile name to store: ");
				  String fileName=scanner.nextLine();
				  newTask.storeProp(fileName, prop);	
				  log.info("Stored!");
				}
				catch(IOException e) {
					log.log(Level.SEVERE,"Exception stack trace:",e);
				} 
				catch (InvalidInputException e) {
					log.log(Level.SEVERE,"Exception stack trace:",e);
				}
				break;
			}
			case(3):{
				System.out.print("Enter your File name to read: ");
				String fileName=scanner.nextLine();
				try {
				   Properties prop=newTask.readFile(fileName);
				   System.out.println("The Elements are: "+prop);
				}
				catch(IOException e) {
					log.log(Level.SEVERE,"Exception stack trace:",e);
				}
				break;
			}
			case(4):{
				System.out.print("Enter your Directory path: ");
				String path=scanner.nextLine();
				if(newTask.createDirectory(path)) {
					System.out.print("Directory created.");
				}
				else {
					System.out.print("Failed to create Directory.");
				}
				int choice=1;
				while(choice>0) {
					System.out.print("Enter your question to repeat(1-3): ");
					choice=scanner.nextInt();
					scanner.nextLine();
				  switch(choice) {
					case(1):{
						try {	
							log.info("Enter your file name: ");
							String fileName=scanner.nextLine();
							File newFile=newTask.createFile(path,fileName);
							System.out.print("Enter the no. of statements");
							int count=scanner.nextInt();
							scanner.nextLine();
							List<String> list=new ArrayList<String>();
							for(int i=0;i<count;i++) {
								System.out.print("Enter your statement to write:");
								list.add(scanner.nextLine());
							  }
							newTask.writeToFile(newFile, list);
							}
							catch(IOException e) {
								log.log(Level.SEVERE,"Exception stack trace:",e);
							}
							break;
					}
					case(2):{
						System.out.print("Enter number of entries: ");
						int count=scanner.nextInt();
						scanner.nextLine();
						Properties prop=newTask.createPropFile();
						String key;
						String value;
						Map<String,String> hashMap=newTask.getHashMap();
						try {
						  while(count>0) {
							System.out.print("Enter your key: ");
							key=scanner.nextLine();
							System.out.print("Enter your value: ");
							value=scanner.nextLine();
							hashMap=newTask.addElement(hashMap, key, value);
							count--;
						    }
						  prop=newTask.setProp(prop,hashMap );
						  System.out.print("Enter your newFile name to store: ");
						  String fileName=scanner.nextLine();
						  File newFile=newTask.createFile(path,fileName);
						  String filePath=newTask.getLocation(newFile);
						  newTask.storeProp(filePath, prop);	
						  log.info("Stored!");
						}
						catch(IOException e) {
							log.log(Level.SEVERE,"Exception stack trace:",e);
						}
						catch (InvalidInputException e) {
							log.log(Level.SEVERE,"Exception stack trace:",e);
						}
						break;
					}
					case(3):{
						System.out.print("Enter your File name to read: ");
						String fileName=scanner.nextLine();
						
						try {
						   File newFile=newTask.createFile(path,fileName);
						   String filePath=newTask.getLocation(newFile);
						   Properties prop=newTask.readFile(filePath);
						   System.out.println("The Elemnts are: "+prop);
						}
						catch(IOException e) {
							log.log(Level.SEVERE,"Exception stack trace:",e);
						}
						break;
					}
					default:{
						System.out.println("Invalid Input!");
					}
				  }
				}
		
			  break;
			}
			case(5):{
				System.out.print("Enter any string: ");
			    String str=scanner.nextLine();
				Sample task5=new Sample(str);
				System.out.println("The object is: "+task5);
				break;
			}
			case(6):{
				System.out.print("Enter any string: ");
			    String str=scanner.nextLine();
			    System.out.print("Enter any number: ");
			    int num=scanner.nextInt();
				Model task6=new Model(str,num);
				System.out.println("The object is- "+task6);
				break;
			}
			case(7):{
				Model task7=new Model();
				System.out.print("Enter any string: ");
			    String str=scanner.nextLine();
			    task7.setName(str);
			    System.out.print("Enter any number: ");
			    int num=scanner.nextInt();
			    task7.setNumber(num);
			    System.out.println("The String is "+task7.getName());
			    System.out.println("The Integer is "+task7.getNumber());
			}
			case(9):{
			    for (RainbowColor color : RainbowColor.values()) {
			    	System.out.println("Color code of " + color + " is " + color.getColorCode() +
			                           ", using ordinal: " + color.ordinal());
			    }
			    break;  
			}
		/*	case(10):{
				SingletonEnum obj1=SingletonClass.getInstance();
				System.identityHashCode(obj1);
				log.info("obj1: "+System.identityHashCode(obj1));
				SingletonEnum obj2=SingletonClass.getInstance();
				log.info("obj1: "+System.identityHashCode(obj2));
				SingletonEnum obj3=SingletonClass.getInstance();
				log.info("obj1: "+System.identityHashCode(obj3));
				break;
			}*/
			case(11):{
				int choice=1;
				while(choice>0) {
					System.out.print("Enter your question to repeat(1-4): ");
					choice=scanner.nextInt();
					scanner.nextLine();
				  switch(choice) {
					case(1):{
						System.out.println("Current Date and time: "+newTask.getDateTime());
						break;
					}
					case(2):{
						System.out.println("Current time in millis: "+newTask.timeInMilli());
						break;
					}
					case(3):{
						Set<String> zoneIds=newTask.getAvailableZones();
						System.out.println("Avaliable Zone Ids are:");
						for(String zoneId:zoneIds) {
							log.info(zoneId);
						}
						System.out.print("Enter your new time zone: ");
						String zone1=scanner.nextLine();
						System.out.print("Enter your new time zone: ");
						String zone2=scanner.nextLine();
						System.out.println(zone1+" : "+newTask.getZoneDateTime(newTask.getZoneId(zone1)));
						System.out.println(zone2+" : "+newTask.getZoneDateTime(newTask.getZoneId(zone2)));
						break;
					}
					case(4):{
						System.out.print("Enter millis: ");
						long milli=scanner.nextLong();
						scanner.nextLine();
						System.out.print("Enter your new time zone: ");
						String zone=scanner.nextLine();
						System.out.println("Day for the given time: "+ newTask.getDay(milli,zone));
						System.out.println("Month for the given time: " + newTask.getMonth(milli,zone));
						System.out.println("Year for the given time: " + newTask.getYear(milli,zone));
						break;
					}
				  }
				}	
				
				//Day light savings
				//
				break;
			}
			case(13):{
			
				try {
				 Class<?> singleton= Class.forName("sample.SingletonClass");
				 Constructor<?> construct=singleton.getDeclaredConstructor();
				 construct.setAccessible(true);
				 Object obj=construct.newInstance();
				 System.out.println("obj: "+System.identityHashCode(obj));
				 }
				catch (ClassNotFoundException | NoSuchMethodException | SecurityException | 
						IllegalArgumentException |InstantiationException |
						IllegalAccessException | InvocationTargetException  e) {
					log.log(Level.SEVERE,"Exception stack trace:",e);
				}
				break;
				
			}
			case(14):{
				serializeSingleton();

		       
		        SingletonClass singleton = deserializeSingleton();

		        System.out.println("Deserialized object is same instance as the original singleton: " 
		        + (singleton == SingletonClass.getInstance()));	
				break;
			}
			default:{
				System.out.println("Invalid Input!");
			}
		}
    }
    scanner.close();
  }
	 private static void serializeSingleton() {
	        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("singleton.ser"))) {
	            SingletonClass singleton = SingletonClass.getInstance();
	            out.writeObject(singleton);
	        } catch (IOException e) {
	        	log.log(Level.SEVERE,"Exception stack trace:",e);
	        }
	    }
	 private static SingletonClass deserializeSingleton() {
	        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("singleton.ser"))) {
	            return (SingletonClass) in.readObject();
	        } catch (IOException | ClassNotFoundException e) {
	        	log.log(Level.SEVERE,"Exception stack trace:",e);
	            return null;
	        }
	 }
}
