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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.UUID;

import android.content.Context;
import android.util.Log;
import c301.AdventureBook.Models.Option;
import c301.AdventureBook.Models.Page;
import c301.AdventureBook.Models.RandomOption;
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
	private Context activityContext;
	private Story currentStory;
	private Page currentPage;
	private Option currentOption;

	private static StoryManager instance = null;

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
	public void setCurrentStory(Story story) {
		this.currentStory = story;
	}

	/**
	 * This gets the current Story
	 * @return The current Story
	 */
	public Story getCurrentStory() {
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
			String formattedDate, String imageByte, boolean overwrite){
		
		Story newStory = new Story(storyTitle, storyDescription, storyAuthor,
				formattedDate, imageByte);
		
		boolean storyExists = saveStory(newStory, overwrite);
		return storyExists;
	}
	
	/**
	 * Save a story to internal storage.
	 * 
	 * @param saveStory the story to be saved
	 * @param overwrite set overwrite to true(for editing purposes), where the existing file in the storage is overwritten if it already exists
	 * @return false if story already exists, true otherwise
	 */
	public boolean saveStory(Story story, Boolean overwrite) {
		
		String FILENAME = story.getFilename();
		
		if(checkFileExists(FILENAME) == false || overwrite == true){
			try {
				FileOutputStream fos = activityContext.openFileOutput(FILENAME,
						0);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(story);
					Log.d("Successful Story Save: ", story.getTitle());
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
		
		for (String fileStr:files) {
			// check if file exists
			if (fileStr.toLowerCase(Locale.getDefault()).contains(".sav")) {
				if(fileStr.toLowerCase(Locale.getDefault()).equals(FILENAME)){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @return the currentPage
	 */
	public Page getPage() {
		return this.currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Page currentPage) {
		this.currentPage = currentPage;
	}

	public void createPage() {
		//New Page
		int pageCount = this.currentStory.getPages().size();
		String generatedUUID = UUID.randomUUID().toString();
		Page page = new Page("NEW PAGE " + "(" + (pageCount + 1) + ")", "", generatedUUID);
		this.currentStory.addPage(page);
		this.currentPage = page;
		saveStory(this.currentStory, true);
	}
	
	/**
	 * Delete a page from the story
	 */
	public void deletePage(Page page) {
		this.currentStory.deletePage(page);
		this.currentPage = null;
		saveStory(this.currentStory, true);
	}
	
	
	/**
	 * @return the currentOption
	 */
	public Option getOption() {
		return currentOption;
	}

	
	/**
	 * @param mOption the currentOption to set
	 */
	public void setCurrentOption(Option option) {
		this.currentOption = option;
	}

	
	public void createOption(String description, Page goToPage) {
		//New Page
		String uuid = goToPage.getPageId();
		Option option = new Option(description, uuid);
		this.currentPage.addOption(option);
		this.currentOption = option;
		saveStory(this.currentStory, true);
	}
	
	
	public void deleteOption(Option option) {
		this.currentPage.getOptions().remove(option);
		if(this.currentPage.getOptions().size() == 1){
			deleteAllRandomOptions();
		}
		this.currentOption = null;
		saveStory(this.currentStory, true);
	}

	public void createRandomOption() {
		RandomOption randomOption = new RandomOption();
		this.currentPage.addOption(randomOption);
		this.currentOption = randomOption;
		saveStory(this.currentStory, true);
	}
	
	public void deleteAllRandomOptions() {
		for(Option option: this.currentPage.getOptions()){
			if(option instanceof RandomOption){
				deleteOption(option);
			}
		}
		saveStory(this.currentStory, true);
	}
	

}

