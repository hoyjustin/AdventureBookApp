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

package c301.AdventureBook;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import c301.AdventureBook.Models.Option;
import c301.AdventureBook.Models.Page;
import c301.AdventureBook.Models.Story;

import com.example.adventurebook.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import c301.AdventureBook.TakePhotoActivity;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.TextView;

/**
 * The edit page activity allows the author to edit the contents of a page
 * within a story.
 * 
 * @author Justin Hoy
 *
 */
public class EditPageActivity extends Activity implements Serializable {

	private static final int EDIT_OPTION = 0;
	
	int PHOTO_ACTIVITY_REQUEST = 1001;
	
	private EditText mEditPageDes;
	private EditText mEditPageTitle;
	private Button mButtonCreateOption;
	private Button mButtonSavePage;
	private CoverFlow coverFlow;

	private ImageAdapter coverImageAdapter;
	private CustomAdapter adpt;
	private ListView optionsList;
	
	private String someTitle;
	private String someDescription;
	private Story someStory;
	private Page somePage;
	private List<Option> options;

	// int my_current_position = 0;
	
	private Story story;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.example.adventurebook.R.layout.edit_page);
		
		somePage = (Page) getIntent().getSerializableExtra("somePage");
		someStory = (Story) getIntent().getSerializableExtra("someStory");
		
		options = somePage.getOptions();

		mEditPageTitle = (EditText)findViewById(com.example.adventurebook.R.id.editPageTitle);
		mEditPageDes = (EditText)findViewById(com.example.adventurebook.R.id.editPageDescription);

		story = (Story) getIntent().getSerializableExtra("someStory");
		
		mButtonCreateOption = (Button) findViewById(R.id.new_option);
		mButtonSavePage = (Button) findViewById(R.id.save_page);
		
		mEditPageTitle.setText(somePage.getTitle());
		mEditPageDes.setText(somePage.getPageDescription());

		coverFlow  = (CoverFlow) findViewById(com.example.adventurebook.R.id.gallery1);
		coverFlow.setAdapter(new ImageAdapter(this));
		coverImageAdapter =  new ImageAdapter(this);
		//coverImageAdapter.createReflectedImages();
		coverFlow.setAdapter(coverImageAdapter);
		coverFlow.setSpacing(25);
		coverFlow.setSelection(2, true);
		coverFlow.setAnimationDuration(1000);
		
		optionsList = (ListView) findViewById(R.id.options_list);
		
		//somePage.addOption(new Option());
		fillData();
		
        coverFlow.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3)
            {
                // TODO Auto-generated method stub
            	Intent intent = new Intent(view.getContext(),TakePhotoActivity.class);
            	//my_current_position = position;
            	startActivityForResult(intent, PHOTO_ACTIVITY_REQUEST);
            	
            }
        });

		mButtonCreateOption.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent i = new Intent(EditPageActivity.this, EditOptionActivity.class);
	   			Bundle bundle = new Bundle();
	   			bundle.putSerializable("someStory", someStory);
	   			i.putExtras(bundle);
				startActivity(i);
				
				//somePage = someStory.getPage(somePage);
				somePage.addOption(new Option("Do something", somePage));
				FileLoader fLoader = new FileLoader(EditPageActivity.this);
				fLoader.saveStory(someStory, true);	
			}
		});

		mButtonSavePage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				someTitle = mEditPageTitle.getText().toString();
				someDescription = mEditPageDes.getText().toString();
				
				//somePage = someStory.getPage(somePage);
				somePage.setTitle(someTitle);
				somePage.setPageDescription(someDescription);
				
				FileLoader fLoader = new FileLoader(EditPageActivity.this);
				fLoader.saveStory(someStory, true);	
				
				EditText editPageDescription = (EditText) findViewById(R.id.editPageDescription);
				String pageDescripion = editPageDescription.getText().toString();
				Page page = new Page("test title", pageDescripion);
				story.addPage(page);
				
				finish();
			}
		});
		
	}

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_OPTION) {
                if (resultCode == RESULT_OK) {
                        // Retrieve the option description that the user entered in EditOptionActivity
                        // after they click SaveOption
                        String someOpt = data.getExtras().getString("someOption");
                		Option someOption = (Option) getIntent().getSerializableExtra("someOption");

        				somePage.addOption(new Option("Do Something", somePage));
        				FileLoader fLoader = new FileLoader(EditPageActivity.this);
        				fLoader.saveStory(someStory, true);
                		
                        //editStoryDescription.setText(optionDescription);
                }
        }
        else if(requestCode == PHOTO_ACTIVITY_REQUEST) {
                if (resultCode == RESULT_OK) {
                        //String show_path;
                        //show_path = data.getStringExtra("path");
                        //coverImageAdapter.editAdapter(show_path, my_current_position);
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
		options = somePage.getOptions();
		adpt = new CustomAdapter(this, optionsList, options);
		optionsList.setAdapter(adpt);
    }


	/**
	 * Populates the list view with a list of all the pages in the story
	 */
	private void fillData() {
		//load model here
		options = somePage.getOptions();
		adpt = new CustomAdapter(this, optionsList, options);
		optionsList.setAdapter(adpt);
	}
	
	private class CustomAdapter extends ArrayAdapter<Option> {

		public CustomAdapter(EditPageActivity editPageActivity, ListView optionsList, List<Option> options) {
			super(EditPageActivity.this, R.layout.option_row,
					options);
		}


		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(
						R.layout.option_row, parent, false);
			}

			Option currentOption = options.get(position);

			Button optionDes = (Button) itemView.findViewById(R.id.option_description);
			optionDes.setText(currentOption.getDescription());

			Button edit = (Button) itemView.findViewById(R.id.edit_button);
			
			return itemView;
		}
	}
}
