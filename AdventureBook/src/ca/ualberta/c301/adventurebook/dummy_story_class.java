package ca.ualberta.c301.adventurebook;
//Creator Minhal

public class dummy_story_class {
	//This class was just created for testing the onlineStoryActivity.
	
	String title;
	String author;
	String date;
	int imageIcon;
	
	public dummy_story_class(String title, String author, String date, int imageIcon){
		this.title = title;
		this.author = author;
		this.date = date;
		this.imageIcon = imageIcon;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getImageIcon() {
		return imageIcon;
	}
	public void setImageIcon(int imageIcon) {
		this.imageIcon = imageIcon;
	}

}
