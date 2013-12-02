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

//Creator: Justin Hoy

package c301.AdventureBook.Models;

import java.io.Serializable;
import java.util.UUID;

/**
 * This is the Option model class. It allows the author to set the option
 * description and goto page when that option is clicked. An option object can
 * be modified by the author when editing a page. Users viewing the story can
 * select an option to move to the next page.
 * 
 * @author tyleung
 * 
 */
public class Option implements Serializable {
	private String description;
	private String gotoPageid;

	/**
	 * The constructor for the Option model class. It creates a new option given
	 * the description and
	 * 
	 * @param description
	 *            the description
	 * @param goToPage
	 *            the goto page
	 */
	public Option(String description, String goToPage) {
		this.description = description;
		this.gotoPageid = goToPage;
	}

	/**
	 * Get the option description
	 * 
	 * @return description the option description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the option description
	 * 
	 * @param description
	 *            the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Set the goto page
	 * 
	 * @param goToPage
	 *            the goto page
	 */
	public void setGoToPage(String goToPage) {
		this.gotoPageid = goToPage;
	}

	/**
	 * Get the goto page
	 * 
	 * @return goToPage the goto page
	 */
	public String getGoToPage() {
		return this.gotoPageid;
	}

	/**
	 * Returns the option description.
	 */
	@Override
	public String toString() {
		return this.description;
	}
}
