package c301.AdventureBook;

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

public class FileLoader {
	Story someStory;
	Context activityContext;
	
	FileLoader(Context someContext){
		this.activityContext = someContext;
	}
	
	
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
		return someStory;
	}
    
	public ArrayList<Story> loadAllStoryFiles() {
		ArrayList<Story> allStories = new ArrayList<Story>();
		String[] files = activityContext.getApplicationContext().fileList();

		Log.d("length", String.valueOf(files.length));
		for (int i = 0; i < files.length; i++) {
			// do something with the file
			if (files[i].toLowerCase().contains(".sav")) {
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
	
	
	public void saveStory(String FILENAME, Story saveStory) {
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
	}
	
}
