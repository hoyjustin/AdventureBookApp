package c301.AdventureBook.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.adventurebook.R;


public class Story implements Serializable{
	//This class was just created for testing the onlineStoryActivity.
	
	private String title;
	private String description;
	private String author;
	private String date;
	int imageIcon;
	public List<Page> pages = new ArrayList<Page>();
	
	public Story(String title, 	String description, String author, String date, int imageIcon){
		this.title = title;
		this.description = description;
		this.author = author;
		this.date = date;
		this.imageIcon = R.drawable.fish;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(List<Page> pages) {
		this.pages = pages;
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

	public List<Page> getPages() {
		return this.pages;
	}
	public void addPage(Page x) {
		this.pages.add(x);
	}

}
