/*
 * Copyright (C) <2013> <Minhal Syed,Terence Yin Kiu Leung>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package c301.AdventureBook;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_option);

		this.story = (Story) getIntent().getSerializableExtra("someStory");

		this.pages = (ArrayList<Page>) story.getPages();

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

		pageListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view_clicked,
					int position, long arg3) {

				TextView temp = (TextView) view_clicked;
				gotoPageTV = (TextView) findViewById(R.id.GotoPageTV);
				gotoPageTV.setText(temp.getText().toString());

				goToPage = adapter.getItem(position);
			}

		});
	}

	/**
	 * Method handler when "Save Option" button is clicked. Saves the option
	 * data and returns user to the edit page activity.
	 */
	@SuppressWarnings("unused")
	public void onClickSaveOption(View v) {
		// TODO: save the option

		Intent data = new Intent();
		EditText editOptionDescription = (EditText) findViewById(R.id.editOptionDescription);
		String optionDescription = editOptionDescription.getText().toString();

		gotoPageTV = (TextView) findViewById(R.id.GotoPageTV);

		if (goToPage != null) {
			returnOption = new Option(optionDescription, goToPage);
			Bundle bundle = new Bundle();
			bundle.putSerializable("someOption", returnOption);
			data.putExtra("someOption", returnOption);

			setResult(RESULT_OK, data);
			finish();
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(
					EditOptionActivity.this);

			// Add the buttons
			builder.setPositiveButton(R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// User clicked Cancel button
						}
					});

			// 2. Chain together various setter methods to set the dialog
			// characteristics
			builder.setMessage(R.string.alert_no_goto);

			// 3. Get the AlertDialog from create()
			AlertDialog dialog = builder.create();
			dialog.show();
		}
	}

	/**
	 * Method handler when "Cancel" button is pressed. Returns user to the edit
	 * page activity.
	 */
	@SuppressWarnings("unused")
	public void onClickCancel(View v) {
		finish();
	}

}
