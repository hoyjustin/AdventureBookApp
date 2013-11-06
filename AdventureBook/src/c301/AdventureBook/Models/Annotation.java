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
public class Annotation implements Serializable{

		private String author;
		private String comment;
		private ArrayList<String> ImagePaths;
		
		/**
		 * This is the constructor for the annotation Object.
		 * An annotation requires an author and a comment, both
		 * as string values. In order to add Images, addImage path
		 * function should be used after.
		 * 
		 * @param author
		 * @param comment
		 */
		public Annotation(String author, String comment){
			this.author = author;
			this.comment = comment;
			this.ImagePaths = new ArrayList<String>();
		}
		
		public void setAuthor(String author){
			this.author = author;
		}
		
		public String getAuthor(){
			return this.author;
		}

		public String getComment() {
			return comment;
		}
		
		public void setComment(String comment) {
			this.comment = comment;
		}
		public ArrayList<String> getImagePaths() {
			return ImagePaths;
		}
		
		public void setImagePaths(ArrayList<String> imagePaths) {
			this.ImagePaths = imagePaths;
		}
		
		public void addImagePath(String ImagePath){
			this.ImagePaths.add(ImagePath);
		}
		
		public String getImagePath(int position){
			return this.ImagePaths.get(position);
			
		}
		
}
