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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.util.Log;
import c301.AdventureBook.Models.Story;


/**
 * This is LibraryManager that is responsible in loading all stories in disk.
 * 
 * 
 * @author Justin Hoy - Main Creator
 * @author Minhal Syed - minor editor
 * 
 *
 */
public class LocalLibraryManager {
	Context activityContext;
	ArrayList<Story> mLibrary;

	// Singleton
	private static LocalLibraryManager instance = null;

	public static LocalLibraryManager getInstance() {
		if (instance == null) {
			instance = new LocalLibraryManager();
		}
		return instance;
	}

	/**
	 * Initializes the manager to the application's context.
	 * 
	 * @param activityContext the activityContext to set
	 */
	public void initContext(Context activityContext) {
		this.activityContext = activityContext;
	}

	/**
	 * @return the activityContext
	 */
	public Context getApplicationContext() {
		return activityContext;
	}

	/**
	 * Load all stories from internal storage and set it as the currentLibrary.
	 * 
	 * @return a list of all Story objects
	 */
	public ArrayList<Story> getCurrentLibrary() {
		updateCurrentLibrary();
		return this.mLibrary;
	}

	/**
	 * Set the managers library to be a list of stories
	 * 
	 * @param currentLibrary the currentLibrary to set
	 */
	public void setCurrentLibrary(ArrayList<Story> currentLibrary) {
		this.mLibrary = currentLibrary;
	}
	
	/**
	 * Delete a story from the library.
	 * 
	 * @param story the story to be deleted
	 */
	public void deleteStory(Story story){
		//String FILENAME = story.getTitle().toLowerCase() + "-" + story.getAuthor().toLowerCase() + ".sav";
		String FILENAME = story.getFilename();
		activityContext.deleteFile(FILENAME);
		updateCurrentLibrary();
	}
	
	/**
	 * Load all stories from internal storage filtered using a given keyword
	 * 
	 * @return a list of all Story objects with the keyword
	 */
	public void updateCurrentLibrary(){
		ArrayList<Story> tempLibrary = new ArrayList<Story>();
		String[] files = activityContext.getApplicationContext().fileList();

		for (String fileStr:files) {
			// do something with the file
			if (fileStr.toLowerCase(Locale.getDefault()).contains(".sav")) {
				try {
					FileInputStream fis = activityContext.openFileInput(fileStr);
					ObjectInputStream ois = new ObjectInputStream(fis);
					while (true) {
						Story someStory = (Story) ois.readObject();
						tempLibrary.add(someStory);
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
		this.mLibrary = tempLibrary;
	}
	
}
