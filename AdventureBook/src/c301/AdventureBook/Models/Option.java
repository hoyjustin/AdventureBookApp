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

public class Option implements Serializable{
	private String description;
	private Page goToPage;
	
	public Option(String description, Page goToPage){
		this.description = description;
		this.goToPage = goToPage;
	}
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setGoToPage(Page goToPage){
		this.goToPage = goToPage;
	}
	
	public Page getGoToPage(){
		return this.goToPage;
	}
}
