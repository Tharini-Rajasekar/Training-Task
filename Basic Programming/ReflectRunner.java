package test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReflectRunner {

	public static void main(String...args) {
		final Logger log=Logger.getLogger(ReflectRunner.class.getName());
		try {
			Class<?> task= Class.forName("sample.Model");
			Constructor<?> defaultConst=task.getConstructor();
			Object obj1=defaultConst.newInstance();
			log.info("Default Constructor: "+obj1);
			Constructor <?>overloadedConst=task.getConstructor(String.class,int.class);
			Object obj2=overloadedConst.newInstance("Hello",1);
			log.info("OverLoaded Constructor: "+obj2);
			Method getterMethod1 = task.getMethod("getName");
			log.info("Name: "+getterMethod1.invoke(obj1));
			log.info("Name: "+getterMethod1.invoke(obj2));
			Method setterMethod=task.getMethod("setNumber",int.class);
			setterMethod.invoke(obj1, 100);
			setterMethod.invoke(obj2, 200);
			Method getterMethod2 = task.getMethod("getNumber");
			log.info("Number: "+getterMethod2.invoke(obj1));
			log.info("Number: "+getterMethod2.invoke(obj2));
			
		} 
		catch (ClassNotFoundException | NoSuchMethodException | SecurityException | 
				InstantiationException | IllegalAccessException |IllegalArgumentException 
				| InvocationTargetException e) {
			log.log(Level.SEVERE,"Exception stack trace:",e);
		} 
		

	}

}
