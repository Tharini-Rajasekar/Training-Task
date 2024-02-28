package task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import util.Helper;
import util.InvalidInputException;

public class BasicPrgmTask {
	
	public File createFile(String path,String fileName) throws IOException{
		File newFile=new File(path,fileName);
		newFile.createNewFile();
		return newFile;
	}
	public String getLocation(File file) {
		return file.getPath();
	}
	public void writeAll(File file, List<String> list) throws IOException {
        Files.write(file.toPath(), list);
    }
	public  File writeToFile(File file,List<String> list) throws IOException{
		try(BufferedWriter writer=new BufferedWriter(new FileWriter(file))){
			for(String content:list) {
				writer.write(content);
				writer.newLine();
			}
		}
		return file;
	}
	public boolean createDirectory(String path) {
		File newdirectory=new File(path);
		return newdirectory.mkdirs();
	}
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
    public enum RainbowColor {
        VIOLET(1), 
        INDIGO(2), 
        BLUE(3), 
        GREEN(4), 
        YELLOW(5), 
        ORANGE(6), 
        RED(7);
        private final int colorCode;
        
        RainbowColor(int colorCode) {
            this.colorCode = colorCode;
        }

        public int getColorCode() {
            return colorCode;
        }
    }
    public LocalDateTime getDateTime() {
    	LocalDateTime myObj = LocalDateTime.now();
        return myObj;
    }
    public long timeInMilli() {
    	return System.currentTimeMillis();
    }
    public Set<String> getAvailableZones() {
    	return ZoneId.getAvailableZoneIds();
    }
    public ZoneId getZoneId(String zone) {
    	return ZoneId.of(zone);
    }
    public ZonedDateTime getZoneDateTime(ZoneId zone) {
    	return ZonedDateTime.now(zone);
    }
    public ZonedDateTime getDateTime(long millis,String zone) {
    	ZoneId exactZone=getZoneId(zone); 
    	Instant instant=Instant.ofEpochMilli(millis);
    	return ZonedDateTime.ofInstant(instant, exactZone);
    }
    public DayOfWeek getDay(long millis,String zone) {
    	return  getDateTime(millis,zone).getDayOfWeek();
    }
    public Month getMonth(long millis,String zone) {
        return  getDateTime(millis,zone).getMonth();
    }
    public int getYear(long millis,String zone) {
        return  getDateTime(millis,zone).getYear();   
    }
}