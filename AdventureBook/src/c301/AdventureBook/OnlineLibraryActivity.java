package c301.AdventureBook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;


import com.example.adventurebook.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class OnlineLibraryActivity extends Activity{

	ArrayList<dummy_story_class> dummyStories;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.online_library);
		createFakeData();
		populateListView();
	}
	
	private void createFakeData() {
		//Dummy Data:
		
		dummyStories = new ArrayList<dummy_story_class>();
		dummyStories.add(new dummy_story_class("story1","author1","date1",R.drawable.fish));
		dummyStories.add(new dummy_story_class("story2","author2","date2",R.drawable.help));	
	}
	private void populateListView(){
		
		ListView onlineLV = (ListView) findViewById(R.id.online_library_listView);
		ArrayAdapter<dummy_story_class> adapter = new CustomAdapter();
		onlineLV.setAdapter(adapter);
	}
	private class CustomAdapter extends ArrayAdapter<dummy_story_class>{

		public CustomAdapter() {
			super(OnlineLibraryActivity.this, R.layout.online_story_list_row, dummyStories);
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
			
			dummy_story_class currentStory = dummyStories.get(position);
			
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