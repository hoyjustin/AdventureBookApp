package c301.AdventureBook;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import c301.AdventureBook.Models.Story;

import com.example.adventurebook.R;

public class OnlineLibraryActivity extends Activity{

	ArrayList<Story> offlineStoryLibrary;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.online_library);
		createFakeData();
		populateListView();
	}
	
	private void createFakeData() {
		// TODO Auto-generated method stub

		//offlineStoryLibrary.add(someStory);

	}
	private void populateListView(){
		
		ListView onlineLV = (ListView) findViewById(R.id.online_library_listView);
		ArrayAdapter<Story> adapter = new CustomAdapter();
		onlineLV.setAdapter(adapter);
	}
	private class CustomAdapter extends ArrayAdapter<Story>{

		public CustomAdapter() {
			super(OnlineLibraryActivity.this, R.layout.complex_story_list_row, offlineStoryLibrary);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//Tutorial used from: https://www.youtube.com/watch?v=WRANgDgM2Zg
			
			// Make sure we have a view to work with (may have been given null)
			
			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(R.layout.complex_story_list_row, parent, false);
			}
			
			Story currentStory = offlineStoryLibrary.get(position);
			
			// Fill the view
			ImageView imageView = (ImageView)itemView.findViewById(R.id.storyImageView);
			imageView.setImageBitmap(BitmapFactory.decodeFile(currentStory.getImagePath()));

			
			TextView authorText = (TextView) itemView.findViewById(R.id.authorTV);
			authorText.setText(currentStory.getAuthor());

			TextView dateText = (TextView) itemView.findViewById(R.id.dateCreatedTv);
			dateText.setText(currentStory.getDate());
			
			return itemView;
		}
	}

	
}