package properties;
	
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import util.Helper;
import util.InvalidInputException;

public class propertiesOperation {

	    public Properties createPropFile() {
	        return new Properties();
	    }
	    public <K,V> Map<K,V> getHashMap(){
			Map<K,V> hashMap= new HashMap<>();
			return hashMap;
		}
	    public <K,V> Map<K,V> addElement(Map<K,V> hashMap, K key, V value) throws InvalidInputException {
			Helper.nullCheck(hashMap);
		    hashMap.put(key, value);
			return hashMap;	
		}
	    public <K, V> Properties setProp(Properties prop, Map<K,V> hashMap) {
	        prop.putAll(hashMap);
	        return prop;
	    }
	    public String getLocation(File file) {
			return file.getPath();
		}
	    public void storeProp(String filePath, Properties prop) throws IOException {
	       try( FileOutputStream output = new FileOutputStream(filePath)){
	           prop.store(output, "Properties");
	       }
	    }
		public Properties readFile(String filePath) throws IOException {
	    	Properties prop=createPropFile();
	    	try(FileInputStream input=new FileInputStream(filePath)){
	    	    prop.load(input);
	    	    return prop;
	    	}
	    }
}
