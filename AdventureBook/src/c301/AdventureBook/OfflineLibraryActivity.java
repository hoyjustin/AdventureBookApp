package c301.AdventureBook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import c301.AdventureBook.Models.Story;

import com.example.adventurebook.R;

public class OfflineLibraryActivity extends Activity{

	ArrayList<Story> offlineStoryLibrary;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		offlineStoryLibrary = new ArrayList<Story>();
		loadAllFiles();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.online_library);

		populateListView();
	}
	/*
	private void createFakeData() {
		//Dummy Data:



	}*/

	private void loadAllFiles() {

		String[] files = getApplicationContext().fileList();

		Log.d("length", String.valueOf(files.length));
		for (int i=0;i < files.length;i++) {
			// do something with the file
			if (files[i].toLowerCase().contains(".sav")){
				try {
					FileInputStream fis = openFileInput(files[i]);
					ObjectInputStream ois = new ObjectInputStream(fis);
					while (true) {
						Story someStory = (Story) ois.readObject();
						offlineStoryLibrary.add(someStory);
						Log.d("loaded", someStory.getTitle());
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

		return;
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

	//New line added.
}