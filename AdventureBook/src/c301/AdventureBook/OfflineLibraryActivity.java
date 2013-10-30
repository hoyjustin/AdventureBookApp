package c301.AdventureBook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;


import c301.AdventureBook.Models.Story;

import com.example.adventurebook.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class OfflineLibraryActivity extends Activity{

	private static final String FILENAME = "file.sav";

	ArrayList<Story> offlineStoryLibrary;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		offlineStoryLibrary = new ArrayList<Story>();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.online_library);

		Story someStory = loadFromFile();
		offlineStoryLibrary.add(someStory);
		
		populateListView();
	}
	/*
	private void createFakeData() {
		//Dummy Data:
		
			
	
	}*/

	private Story loadFromFile() {

		Story someStory = null;
		try {
			FileInputStream fis = openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			while (true) {
				someStory = (Story) ois.readObject();
				Log.d("load", "Success");
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

	private void populateListView(){
		
		ListView onlineLV = (ListView) findViewById(R.id.online_library_listView);
		ArrayAdapter<Story> adapter = new CustomAdapter();
		onlineLV.setAdapter(adapter);
	}
	private class CustomAdapter extends ArrayAdapter<Story>{

		public CustomAdapter() {
			super(OfflineLibraryActivity.this, R.layout.online_story_list_row, offlineStoryLibrary);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//Tutorial used from: https://www.youtube.com/watch?v=WRANgDgM2Zg
			
			// Make sure we have a view to work with (may have been given null)
			
			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(R.layout.online_story_list_row, parent, false);
			}
			
			Story currentStory = offlineStoryLibrary.get(position);
			
			// Fill the view
			ImageView imageView = (ImageView)itemView.findViewById(R.id.storyImageView);
			imageView.setImageResource(currentStory.getImageIcon());
			
			TextView authorText = (TextView) itemView.findViewById(R.id.authorTV);
			authorText.setText(currentStory.getAuthor());

			TextView dateText = (TextView) itemView.findViewById(R.id.dateCreatedTv);
			dateText.setText(currentStory.getDate());
			
			return itemView;
		}
	}

	
}