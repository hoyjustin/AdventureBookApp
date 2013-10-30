/*
 * Copyright (C) <2013>  <Terence Yin Kiu Leung>
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

import java.text.SimpleDateFormat;
import java.util.Calendar;


import c301.AdventureBook.Models.Story;

import com.example.adventurebook.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class CreateStoryActivity extends Activity{
	private static final int ACTIVITY_EDIT_STORY=0;
	
	private EditText mStoryTitle;
	private EditText mStoryDescription;
	private TextView mDate;
	private Button mButtonCreateStory;
	
	private Story someStory;
	String storyTitle;
	String storyDescription;
	String formattedDate;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_story);
		
		mStoryTitle = (EditText) findViewById(R.id.editStoryTitle);

		mStoryDescription = (EditText) findViewById(R.id.editStoryDescription);
		
		Button createStoryButton = (Button) findViewById(R.id.createStoryButton);
		
		setDate();
	
		createStoryButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				createStory();
			}
		});
	}

	/**
	 * Sets the story creation date
	 */

	private void setDate() {
		mDate = (TextView) findViewById(R.id.dateText);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		formattedDate = df.format(c.getTime());
		mDate.setText("Date: " + formattedDate);
	}
	
	private void createStory() {
		getUserText();
		someStory = new Story(storyTitle, storyDescription, "someauthor", formattedDate, 0);
		
        Intent i = new Intent(this, EditStoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("someStory", someStory);
        i.putExtras(bundle);
        startActivityForResult(i, ACTIVITY_EDIT_STORY);
	}
	private void getUserText() {
		
		storyTitle = mStoryTitle.getText().toString();
		storyDescription = mStoryDescription.getText().toString();
	}
	
}
