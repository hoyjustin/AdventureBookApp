/*
 * Copyright (C) <2013>  <Justin Hoy>
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
import java.util.List;

public class Page implements Serializable{

	private String title;
	private List<Option> options;
	private String textContent;
	
	public Page(String title, String textContent){
		this.title = title;
		this.textContent = textContent;
	}
	
	public void addOption(Option option) {
		this.options.add(option);
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public void setTitle(String startPage){
		this.title = startPage;
	}
	
	public void setOptions(List<Option> options){
		this.options = options;
	}
	
	public String getTextContent() {
		return textContent;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public List<Option> getOptions(){
		return this.options;
	}
}
