package c301.AdventureBook.Models;
import java.io.Serializable;
import java.util.List;
public class Annotation implements Serializable{

		private String author;
		//private List<Option> options;
		
		public void setAuthor(String startAnnotation){
			this.author = startAnnotation;
		}
		
		public String getAuthor(){
			return this.author;
		}

}
