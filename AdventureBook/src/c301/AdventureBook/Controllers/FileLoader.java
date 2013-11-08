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
import c301.AdventureBook.Models.Story;


/**
 * This is FileLoader class of the
 * 
 * @author Justin Hoy - Main Creator
 * @author Minhal Syed - minor editor
 * 
 *
 */
public class FileLoader {
	Context activityContext;
	Story someStory;
	
	public FileLoader(Context someContext){
		this.activityContext = someContext;
	}
	
	/**
	 * Load the story with the given filename from internal storage.
	 * 
	 * @param FILENAME the name of the file to be loaded
	 * @return a Story object
	 */
	public Story loadFromFile(String FILENAME) {
		
		try {
			FileInputStream fis = activityContext.openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			while (true) {
				someStory = (Story) ois.readObject();
				Log.d("Success Load", someStory.getTitle());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return someStory;
	}
    
	public ArrayList<Story> loadAllStoryFiles() {
		return loadStoryFileWithKeyword("");
	}
	
	
	public Story loadStory(Story loadStory) {
		String FILENAME = loadStory.getTitle() + "-" + loadStory.getAuthor() + ".sav";
		Story someStory = loadFromFile(FILENAME);
		return someStory;
	}
	
	public ArrayList<Story> loadStoryFileWithKeyword(String KeyWord){
		ArrayList<Story> allStories = new ArrayList<Story>();
		String[] files = activityContext.getApplicationContext().fileList();

		for (int i = 0; i < files.length; i++) {
			// do something with the file
			if (files[i].toLowerCase().contains(KeyWord) && files[i].toLowerCase().contains(".sav")) {
				try {
					FileInputStream fis = activityContext.openFileInput(files[i]);
					ObjectInputStream ois = new ObjectInputStream(fis);
					while (true) {
						Story someStory = (Story) ois.readObject();
						allStories.add(someStory);
						Log.d("Success Load", someStory.getTitle());
					}

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return allStories;
		
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
	 * @return false if no save was made, true otherwise
	 */
	public boolean saveStory(Story saveStory, boolean overwrite) {
		
		String FILENAME = saveStory.getTitle() + "-" + saveStory.getAuthor() + ".sav";
		
		if(checkFileExists(FILENAME) == false){
			try {
				FileOutputStream fos = activityContext.openFileOutput(FILENAME,
						0);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(saveStory);
					Log.d("Success Save", saveStory.getTitle());
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		else{
			if(overwrite == true){
				try {
					FileOutputStream fos = activityContext.openFileOutput(FILENAME,
							0);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(saveStory);
						Log.d("Success Save", saveStory.getTitle());
					fos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
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
