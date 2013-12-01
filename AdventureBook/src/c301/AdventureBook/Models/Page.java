/* Copyright (C) <2013>  <Justin Hoy>
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

//Creator: Justin Hoy

package c301.AdventureBook.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This is the Page Model Class. A page object is part of a story. 
 * A page contains a title, a description, 0 to many images, 0 to many options,
 * and an option for users to view/add annotations to the page.
 *
 */
public class Page implements Serializable{

	private String title;
	private List<Option> options;
	private String pageDescription;
	private ArrayList<String> imageBytes;
	private String pageId;
	private ArrayList<Annotation> annotations;
	
	/**
	 * Constructor for the Page class. It creates a page with he given title
	 * and description. The UUID is an automatically generated random string
	 * that is unique for all pages.
	 * 
	 * @param title the page title
	 * @param pageDescription the page description
	 * @param pageUUID the page UUID
	 */
	public Page(String title, String pageDescription, String pageUUID){
		this.title = title;
		this.pageDescription = pageDescription;
		this.options = new ArrayList<Option>();
		this.annotations = new ArrayList<Annotation>();
		this.imageBytes = new ArrayList<String>();
		this.pageId = pageUUID;
	}
	
	/**
	 * Delete an option from the page.
	 * 
	 * @param option the option to be deleted
	 */
	public void deleteOption(Option option) {
		this.options.remove(option);
	}
	
	/**
	 * Add an option to the page.
	 * 
	 * @param option the option to be added
	 */
	public void addOption(Option option) {
		this.options.add(option);
	}

	/**
	 * Set the page title.
	 * 
	 * @param startPage the title
	 */
	public void setTitle(String startPage){
		this.title = startPage;
	}
	
	/**
	 * Set the page options.
	 * 
	 * @param options the list of options
	 */
	public void setOptions(List<Option> options){
		this.options = options;
	}
	
	/**
	 * Set the page description.
	 * 
	 * @param pageDescription the description
	 */
	public void setPageDescription(String pageDescription) {
		this.pageDescription =  pageDescription;
	}
	
	/**
	 * Get the page description.
	 * 
	 * @return pageDescription the description
	 */
	public String getPageDescription() {
		return pageDescription;
	}
	
	/**
	 * Get the page title.
	 * 
	 * @return title the page title
	 */
	public String getTitle(){
		return this.title;
	}
	
	/**
	 * Get the list of options.
	 * 
	 * @return options the list of options
	 */
	public List<Option> getOptions(){
		return this.options;
	}
	
	/**
	 * Add an annotation.
	 * 
	 * @param annotation the annotation to be added
	 */
	public void addAnnotation(Annotation annotation){
		this.annotations.add(annotation);
	}
	
    @Override
    public String toString() {
        return this.title;
    }

    /**
     * Get the list of image bytes.
     * 
     * @return imageBytes the image bytes
     */
	public ArrayList<String> getImageBytes() {
		return imageBytes;
	}

	/**
	 * Add an image byte.
	 * 
	 * @param imageByte the image byte to be added
	 */
	public void addImageByte(String imageByte) {
		this.imageBytes.add(imageByte);
	}
	
	/**
	 * Remove an image byte.
	 * 
	 * @param position the position of the image byte
	 */
	public void removeImageByte(int position){
		this.imageBytes.remove(position);
	}
	
	/**
	 * Get the list of annotations.
	 * 
	 * @return annotations the list of annotations
	 */
	public ArrayList<Annotation> getAnnotations(){
		return this.annotations; 
	}
	

	/**
	 * This function returns the id of a page. StoryId = a randomly generated UUID provided in the constructor (no
	 * spaces)
	 * 
	 * @return pageId
	 */
	public String getPageId(){
		return this.pageId;

	}
    

}
