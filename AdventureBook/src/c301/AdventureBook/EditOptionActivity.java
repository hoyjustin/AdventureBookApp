/*
 * Creator: Minhal Syed, Terence Yin Kiu Leung*/
package c301.AdventureBook;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import c301.AdventureBook.Models.Option;
import c301.AdventureBook.Models.Page;
import c301.AdventureBook.Models.Story;

import com.example.adventurebook.R;

public class EditOptionActivity extends Activity {

	ArrayAdapter<Page> adapter;
	ListView pageListView;
	TextView gotoPageTV;
	Story story;
	ArrayList<Page> pages;
	Page goToPage;
	Option returnOption;
	
	// For now: we can just add Dummy Pages
	//String[] pages = { "Page1", "Page2", "Page3", "Page4", "Page5",
		//	"Page6", "Page7" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_option);
		
		
		this.story = (Story) getIntent().getSerializableExtra("someStory");
		
		this.pages = (ArrayList<Page>) story.getPages();
		
		//this.pages = new ArrayList<Page>();
		
		//this.pages.add(new Page("Title1", "description1"));
		//this.pages.add(new Page("Title2", "description2"));
		//this.pages.add(new Page("Title3", "description3"));
		
				
		
		populateListView();
		registerForClicks();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void populateListView() {
		// This function will put all the available pages in the listView
		pageListView = (ListView) findViewById(R.id.list_of_goto_pages);
		
		this.adapter = new ArrayAdapter<Page>(this, R.layout.list_row, pages);

		pageListView.setAdapter(adapter);
	}

	private void registerForClicks() {
		// TODO Auto-generated method stub
		pageListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view_clicked,
					int position, long arg3) {
				// TODO Auto-generated method stub

				TextView temp = (TextView) view_clicked;
				gotoPageTV = (TextView) findViewById(R.id.GotoPageTV);
				gotoPageTV.setText(temp.getText().toString());
				
				goToPage = adapter.getItem(position);
			}

		});
	}
	
	
	/**
	 * Method handler when "Save Option" button is clicked.
	 * Saves the option data and returns user to the edit page activity.
	 */
	@SuppressWarnings("unused")
	public void onClickSaveOption(View v){
		//TODO: save the option
		
		
		Intent data = new Intent();
		EditText editOptionDescription = (EditText) findViewById(R.id.editOptionDescription);
		String optionDescription = editOptionDescription.getText().toString();
		
		gotoPageTV = (TextView) findViewById(R.id.GotoPageTV);
		
		returnOption = new Option(optionDescription, goToPage);
		
		data.putExtra("someOption", returnOption);
		
		setResult(RESULT_OK, data);
		
		finish();
	}
	
	/**
	 * Method handler when "Cancel" button is pressed.
	 * Returns user to the edit page activity.
	 */
	@SuppressWarnings("unused")
	public void onClickCancel(View v) {
		finish();
	}

}
