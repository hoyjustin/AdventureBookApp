package c301.AdventureBook.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.adventurebook.R;

public class Annotations implements Serializable{
	//This class was just created for testing the onlineStoryActivity.
	

	private String description;
	private String author;
	//int imageIcon;
	public List<Annotation> annotations = new ArrayList<Annotation>();
	int imageIcon;
	
	public Annotations(String author, String description, int imageIcon){
		this.description = description;
		this.author = author;
		this.imageIcon = R.drawable.fish;

	}
	public String getDescription() {
		return description;
	}

	public int getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(int imageIcon) {
		this.imageIcon = imageIcon;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}
	public void addAnnotation(Annotation x) {
		this.annotations.add(x);
	}
}