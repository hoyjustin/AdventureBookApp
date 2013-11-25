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
		private String imageByte;
		
		/**
		 * This is the constructor for the annotation Object.
		 * An annotation requires an author and a comment, both
		 * as string values. In order to add Images, addImage path
		 * function should be used after.
		 * 
		 * @param author is the author for the annotaion
		 * @param comment is the comment that use can post their feedback
		 */
		public Annotation(String author, String comment){
			this.author = author;
			this.comment = comment;
		}
		
		public void setIllustration(String ImageByte){
			this.imageByte = ImageByte;
		}

		/**
		 * Get the imagePaths's postion
		 * @return imagepath's position
		 */
		public String getIllustration(){
			return this.imageByte;
			
		}

		/**
		 * Set the Author
		 * @param author the annotation author
		 */
		public void setAuthor(String author){
			this.author = author;
		}
		/**
		 * Get the author
		 * @return author 
		 */
		public String getAuthor(){
			return this.author;
		}
		/**
		 * Get the  comment
		 * @return comment
		 */
		public String getComment() {
			return comment;
		}
		/**
		 * Set the Comment
		 * @param comment the annotation 
		 */
		public void setComment(String comment) {
			this.comment = comment;
		}

		
		
}
