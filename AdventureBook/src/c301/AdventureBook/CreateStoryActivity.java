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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import com.example.adventurebook.R;


public class CreateStoryActivity extends Activity {
	
	private EditText mStoryTitle;
	private EditText mStoryDescription;
	private TextView mDate;
	//private Button mButtonCreateStory;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_story);
		
		mStoryTitle = (EditText) findViewById(R.id.editStoryTitle);
		String storyTitle = mStoryTitle.getText().toString();
		
		mStoryDescription = (EditText) findViewById(R.id.editStoryDescription);
		String storyDescription = mStoryDescription.getText().toString();
		
		setDate();
	}

	/**
	 * Sets the story creation date
	 */
	@SuppressLint("SimpleDateFormat")
	private void setDate() {
		mDate = (TextView) findViewById(R.id.dateText);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		String formattedDate = df.format(c.getTime());
		mDate.setText("Date: " + formattedDate);
	}
	
	public void onClickBtn(View v) {
		
	}
	
}
