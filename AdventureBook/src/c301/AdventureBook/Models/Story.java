/*
 * Copyright (C) <2013>  <Minhal Syed>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package c301.AdventureBook.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.adventurebook.R;

/**
 * This is a Story Model Class. A Story is an object that contains title,
 * description, author, date, thumbnail picture, and list of Page objects. 
 * 
 * @author Minhal Syed
 *
 */
public class Story implements Serializable{
	
	private String title;
	private String description;
	private String author;
	private String date;
	private String imagePath;
	public List<Page> pages = new ArrayList<Page>();
	
	
	/**
	 * This is the constructor function for the Story class.
	 * It creates a new Story object with information provided.
	 * This constructors makes a new story with no pages. To
	 * add pages to the story, add page function should be called.
	 *   
	 * @param title title of the Story.
	 * @param description description of the Story.
	 * @param author author of the Story.
	 * @param date date on which the Story was first created.
	 * @param imagePath path of the thumnail picture of the Story.
	 */
	public Story(String title, 	String description, String author, String date, String imagePath){
		this.title = title;
		this.description = description;
		this.author = author;
		this.date = date;
		this.imagePath = imagePath;
	}
	
	/**
	 * This function returns the description of the story.
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This function sets the description of the story.
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This function returns the created date of the story.
	 * @return date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * This function sets the creation date of the story.
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * This function returns the title of the story.
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * This function sets the title of the story.
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * This function returns the author of the story.
	 * @return String author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * This function sets the author of the story.
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * This function returns the imagePath of the thumbnail photo of the Story.
	 * @return
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * This function sets the imagePath of the thumbnail photo of the story.
	 * @param imagePath
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * This function returns all the pages of the story.
	 * @return
	 */
	public List<Page> getPages() {
		return this.pages;
	}
	/**
	 * This function adds a page to the pages list of the story.
	 * @param page
	 */
	public void addPage(Page page) {
		this.pages.add(page);
	}
	/**
	 * This function sets all the pages of a story.
	 * @param pages the pages to set
	 */
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}
	/**
	 * This function deletes a particular page from the story.
	 * @param somePage
	 */
	public void deletePage(Page somePage) {
		this.pages.remove(somePage);
	}
	

}
