/*
 * Copyright (C) <2013>  <Lin Tong>
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
 * This is an annotation class. An annotation is any form of review that a user
 * can post to any particular page of the story. An annotation contains an author,
 * a comment, and/or a list of media items.
 * 
 * Current issues: For now, we only have one type of media, and that is the image type.
 * Therefore for now, the list of media items is just imagePaths.
 * 
 * @author Lin Tong
 *
 */
public class Annotations implements Serializable{
	

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