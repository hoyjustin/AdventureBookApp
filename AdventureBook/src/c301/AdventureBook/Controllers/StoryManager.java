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

package c301.AdventureBook.Controllers;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import c301.AdventureBook.Models.Option;
import c301.AdventureBook.Models.Page;
import c301.AdventureBook.Models.Story;


/**
 * This is FileManager that is responsible to load the story model from disk
 * 
 * @author Justin Hoy - Main Creator
 * @author Minhal Syed - minor editor
 * 
 *
 */
public class StoryManager {
	Context activityContext;
	Story currentStory;

	private static StoryManager instance = new StoryManager( );

	public static StoryManager getInstance() {
		if (instance == null) {
			instance = new StoryManager();
		}
		return instance;
	}

	public void initContext(Context someContext){
		this.activityContext = someContext;
	}

	/**
	 * This sets the current story
	 * @param A Story
	 */
	public void setStory(Story story) {
		this.currentStory = story;
	}

	/**
	 * This gets the current Story
	 * @return The current Story
	 */
	public Story getStory() {
		return currentStory;
	}

	/**
	 * Create a story in internal storage.
	 * 
	 * @param story the story to be created
	 * @return 
	 * @return false if story already exists, true otherwise
	 */
	public boolean createStory(String storyTitle, String storyDescription, String storyAuthor, 
			String formattedDate, String show_path, String imageByte, boolean overwrite){
		Story newStory = new Story(storyTitle, storyDescription, storyAuthor,
				formattedDate, show_path, imageByte);
		
		boolean storyExists = saveStory(newStory, overwrite);
		return storyExists;
	}
	
	/**
	 * Delete a story from internal storage.
	 * 
	 * @param story the story to be deleted
	 */
	public void deleteStory(Story story){
		String FILENAME = story.getTitle().toLowerCase() + "-" + story.getAuthor().toLowerCase() + ".sav";
		activityContext.deleteFile(FILENAME);
	}

	/**
	 * Save a story to internal storage.
	 * 
	 * @param saveStory the story to be saved
	 * @param overwrite set overwrite to true(for editing purposes), where the existing file in the storage is overwritten if it already exists
	 * @return false if story already exists, true otherwise
	 */
	public boolean saveStory(Story story, Boolean overwrite) {
		
		String FILENAME = story.getTitle() + "-" + story.getAuthor() + ".sav";
		
		if(checkFileExists(FILENAME) == false || overwrite == true){
			try {
				FileOutputStream fos = activityContext.openFileOutput(FILENAME,
						0);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(story);
					Log.d("Success Save: ", story.getTitle());
				fos.close();
				this.currentStory = story;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Check if file with name FILENAME already exists in the app directory.
	 * 
	 * @param FILENAME
	 * @return true if a file already exists, false otherwise
	 */
	public boolean checkFileExists(String FILENAME) {
		
		String[] files = activityContext.getApplicationContext().fileList();
		
		for (int i = 0; i < files.length; i++) {
			// check if file exists
			if (files[i].toLowerCase().contains(".sav")) {
				if(files[i].toLowerCase().equals(FILENAME)){
					return true;
				}
			}
		}
		return false;
	}

	
	

}

