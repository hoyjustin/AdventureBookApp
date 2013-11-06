package c301.AdventureBook;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import c301.AdventureBook.Models.Option;
import c301.AdventureBook.Models.Page;
import c301.AdventureBook.Models.Story;

import com.example.adventurebook.R;

public class ViewPageActivity extends Activity {

	private static final int FIRST_PAGE_INDEX = 0;
	private Story story;
	private List<Page> pages;
	private List<Option> options;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpage);
		
		story = (Story) getIntent().getSerializableExtra("someStory");
		List<Page> pages = story.getPages();
		viewFirstPage();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_story, menu);
		return true;
	}
	
	/**
	 * Initialize the first page of the story for the user to view when they
	 * choose a story to view from the library.
	 */
	private void viewFirstPage() {
		Page page = pages.get(FIRST_PAGE_INDEX);
		options = page.getOptions();
		
		// Set the page description
		String pageDescription = page.getPageDescription();
		TextView pageDescriptionTV = (TextView) findViewById(R.id.pageDescriptionTV);
		pageDescriptionTV.setText(pageDescription);
		
		// Set the page options
		ListView optionsListView = (ListView) findViewById(R.id.list_options);
		ArrayAdapter<Option> optionAdapter = new ArrayAdapter<Option>(this, R.layout.list_row, options);
		optionsListView.setAdapter(optionAdapter);
		
	}

}
