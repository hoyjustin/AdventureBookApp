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

public class Page implements Serializable{

	private String title;
	private List<Option> options;
	private String pageDescription;
	private ArrayList<String> imageBytes;
	private String uuid;
	
	private ArrayList<Annotation> annotations;
	
	
	public Page(String title, String pageDescription, String uuid){
		this.title = title;
		this.pageDescription = pageDescription;
		this.options = new ArrayList<Option>();
		this.annotations = new ArrayList<Annotation>();
		this.imageBytes = new ArrayList<String>();
		this.uuid = uuid;
	}
	
	public void deleteOption(Option option) {
		this.options.remove(option);
	}
	
	public void addOption(Option option) {
		this.options.add(option);
	}

	public void setTitle(String startPage){
		this.title = startPage;
	}
	
	public void setOptions(List<Option> options){
		this.options = options;
	}
	
	public void setPageDescription(String pageDescription) {
		this.pageDescription =  pageDescription;
	}
	
	public String getPageDescription() {
		return pageDescription;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public List<Option> getOptions(){
		return this.options;
	}
	
	public void addAnnotation(Annotation annotation){
		this.annotations.add(annotation);
	}
	
    @Override
    public String toString() {
        return this.title;
    }

	public ArrayList<String> getImageBytes() {
		return imageBytes;
	}

	public void addImageByte(String imageBytes) {
		this.imageBytes.add(imageBytes);
	}
	public ArrayList<Annotation> getAnnotations(){
		return this.annotations; 
	}
	
	public String getuuid(){
		return this.uuid;
	}
    

}
