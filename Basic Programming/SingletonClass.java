package sample;
import java.io.Serializable;

public class SingletonClass implements Serializable{
	private static final long serialVersionUID = 1L;
	private SingletonClass() {
    	if(SingletonHelper.instance!=null) {
			throw new IllegalStateException("Instance already created");
		}
	}
	private static class SingletonHelper{
		private static final SingletonClass instance=new SingletonClass();
	}
	public static SingletonClass getInstance() {
		return SingletonHelper.instance;
	}
    protected Object readResolve() {
    	return getInstance();
    }
    @Override protected Object clone() throws CloneNotSupportedException{
    	throw new CloneNotSupportedException("Cloning of Singleton is not allowed");
    }
}