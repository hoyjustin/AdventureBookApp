package c301.AdventureBook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
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
		setContentView(R.layout.offline_library);

		populateListView();
	}


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
	
	public void launchNewStoryActivity(View v){
		Intent i = new Intent(this, CreateStoryActivity.class);
		startActivity(i);
	}
	
	public void launchOnlineLibraryActivity(View v){
		Intent i = new Intent(this, OnlineLibraryActivity.class);
		//startActivity(i);
	}


	private void populateListView(){

		ListView offlineLV = (ListView) findViewById(R.id.offline_library_listView);
		ArrayAdapter<Story> adapter = new CustomAdapter();
		offlineLV.setAdapter(adapter);
		registerForContextMenu(offlineLV);		
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add("Publish Online");
		menu.add("Edit Story");
		menu.add("Delete Story");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onContextItemSelected(item);
		
		if (item.getTitle() == "Publish Online"){
			//Do Publish Online Function
		}
		
		else if (item.getTitle() == "Edit Story"){
			//Do Edit Story Function
		}
		else if (item.getTitle() == "Delete Story"){	
			//Do Delete Story Function
		}
		return true;
	}
	
	
	
	private class CustomAdapter extends ArrayAdapter<Story>{

		public CustomAdapter() {
			super(OfflineLibraryActivity.this, R.layout.offline_library_row, offlineStoryLibrary);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//Tutorial used from: https://www.youtube.com/watch?v=WRANgDgM2Zg

			// Make sure we have a view to work with (may have been given null)

			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(R.layout.offline_library_row, parent, false);
			}

			Story currentStory = offlineStoryLibrary.get(position);

			// Fill the view
			ImageView imageView = (ImageView)itemView.findViewById(R.id.storyThumbnailView);
			imageView.setImageResource(currentStory.getImageIcon());
			
			TextView titleText = (TextView) itemView.findViewById(R.id.titleTV);
			titleText.setText(currentStory.getTitle());

			TextView authorText = (TextView) itemView.findViewById(R.id.authorTV);
			authorText.setText(currentStory.getAuthor());

			TextView dateText = (TextView) itemView.findViewById(R.id.dateCreatedTV);
			dateText.setText(currentStory.getDate());

			return itemView;
		}
	}
}