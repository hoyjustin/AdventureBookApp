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

package c301.AdventureBook.EditStory;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.adventurebook.R;


import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class CreateStoryActivity extends Activity{
	
	private EditText mStoryTitle;
	private EditText mStoryDescription;
	private TextView mDate;
	private Button mButtonCreateStory;
	
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

	private void setDate() {
		mDate = (TextView) findViewById(R.id.dateText);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		String formattedDate = df.format(c.getTime());
		mDate.setText("Date: " + formattedDate);
	}
	
//
//	/**
//	 * @uml.property  name="sController"
//	 * @uml.associationEnd  multiplicity="(1 1)" inverse="createStoryActivity:com.example.adventurebook.SController"
//	 */
//	private SController sController = new ca.ualberta.c301.adventurebook.SController();
//
//	/**
//	 * Getter of the property <tt>sController</tt>
//	 * @return  Returns the sController.
//	 * @uml.property  name="sController"
//	 */
//	public SController getSController() {
//		return sController;
//	}
//
//	/**
//	 * Setter of the property <tt>sController</tt>
//	 * @param sController  The sController to set.
//	 * @uml.property  name="sController"
//	 */
//	public void setSController(SController sController) {
//		this.sController = sController;
//	}
	
}
