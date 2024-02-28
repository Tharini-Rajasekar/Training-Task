package sample;

public class Model {
   String name;
   int number;
   public Model() {
	   
   }
   public Model(String str, int num) {
	   name=str;
	   number=num;
   }
   public void setName(String str) {
	   name=str;
   }
   public void setNumber(int num) {
	   number=num;
   }
   public String getName() {
	   return name;
   }
   public int getNumber() {
	   return number;
   }
   public String toString() {
	   return "Name: "+name+" Number: "+number;
   }
}
