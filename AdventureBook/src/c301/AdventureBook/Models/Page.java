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

/**
 * This is the Page model class. There can be many pages within a story. 
 * The model allows authors to set a page title and description, and add
 * options to the page. 
 * 
 * @author tyleung
 *
 */
public class Page implements Serializable{

	private String title;
	private List<Option> options;
	private String pageDescription;
	
	/**
	 * The constructor for the Page model class.
	 * It creates a new page with the given title and description.
	 * It also initializes the options list.
	 * 
	 * @param title the title of the page
	 * @param pageDescription the page description
	 */
	public Page(String title, String pageDescription){
		this.title = title;
		this.pageDescription = pageDescription;
		this.options = new ArrayList<Option>();
	}
	

	public void deleteOption(Option option) {
		this.options.remove(option);
	}
	

	/**
	 * Adds an option to the options list
	 * @param option the option to be added
	 */

	public void addOption(Option option) {
		this.options.add(option);
	}

	/**
	 * Set the page title
	 * @param pageTitle the title
	 */
	public void setTitle(String pageTitle){
		this.title = pageTitle;
	}
	
	/**
	 * Set the options list
	 * @param options the list of options
	 */
	public void setOptions(List<Option> options){
		this.options = options;
	}
	
	/**
	 * Set the page description
	 * @param pageDescription the page description
	 */
	public void setPageDescription(String pageDescription) {
		this.pageDescription =  pageDescription;
	}
	
	/**
	 * Get the page description
	 * @return pageDescription the page description
	 */
	public String getPageDescription() {
		return pageDescription;
	}
	
	/**
	 * Get the page title
	 * @return title the page title
	 */
	public String getTitle(){
		return this.title;
	}
	
	/**
	 * Get the options list
	 * @return options the options list
	 */
	public List<Option> getOptions(){
		return this.options;
	}
	
    @Override
    public String toString() {
        return this.title;
    }
    

}
