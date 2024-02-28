package pack;

public class Book {
  String title;
  String author;
  public Book(String title) {
	  this.title=title;
  }
  public Book(String title, String author) {
	  this.title=title;
	  this.author=author;
  }
  public String toString() {
	  return title;
  }
}
