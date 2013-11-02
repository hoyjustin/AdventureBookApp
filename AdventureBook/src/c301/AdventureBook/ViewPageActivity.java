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
import com.example.adventurebook.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ViewPageActivity extends Activity implements Serializable{
 
	private static final int EDIT_OPTION = 0;
	private EditText editStoryDescription;
	private Button mButtonCreateOption;
	private Button mButtonSavePage;
	private CoverFlow coverFlow;
	ImageAdapter coverImageAdapter;


	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.example.adventurebook.R.layout.view_page);

		editStoryDescription = (EditText)findViewById(com.example.adventurebook.R.id.editStoryDescription);
		mButtonCreateOption = (Button) findViewById(R.id.new_option);
		mButtonSavePage = (Button) findViewById(R.id.save_page);

		coverFlow  = (CoverFlow) findViewById(com.example.adventurebook.R.id.gallery1);
		coverFlow.setAdapter(new ImageAdapter(this));
		coverImageAdapter =  new ImageAdapter(this);

		//coverImageAdapter.createReflectedImages();

		coverFlow.setAdapter(coverImageAdapter);

		coverFlow.setSpacing(25);
		coverFlow.setSelection(2, true);
		coverFlow.setAnimationDuration(1000);

		
		mButtonCreateOption.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(ViewPageActivity.this, EditOptionActivity.class);
				startActivityForResult(intent, EDIT_OPTION);
			}
		});

		mButtonSavePage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				finish();
			}
		});
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == EDIT_OPTION) {
			if (resultCode == RESULT_OK) {
				// Retrieve the option description that the user entered in EditOptionActivity
				// after they click SaveOption
				String optionDescription = data.getExtras().getString("option_description");
				editStoryDescription.setText(optionDescription);
			}
		}
	}
	


}