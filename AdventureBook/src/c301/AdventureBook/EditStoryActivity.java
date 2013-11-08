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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import c301.AdventureBook.Controllers.FileManager;
import c301.AdventureBook.Models.Option;
import c301.AdventureBook.Models.Page;
import c301.AdventureBook.Models.Story;

import com.example.adventurebook.R;

/**
 * The edit story activity allows the author to edit the contents of a story by
 * adding or removing story fragments.
 * 
 * @author Justin
 *
 */
public class EditStoryActivity extends Activity implements OnMenuItemClickListener, Serializable{

	private static final int DELETE_ID = Menu.FIRST + 1;
	private final static int EDIT_PAGE = 1;
	private final static int DELETE_PAGE = 2;

	private ExpandableListAdapter adpt;
	private ExpandableListView lstView;
	private TextView storyView;
	private TextView pageView;
	private Button createPage;
	private Button returnLocalLib;
	private PopupMenu popupMenu;

	private Story someStory;
	private Page somePage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.example.adventurebook.R.layout.edit_story);

		storyView = (TextView)findViewById(com.example.adventurebook.R.id.storyView);
		pageView = (TextView)findViewById(com.example.adventurebook.R.id.pageView);
		lstView = (ExpandableListView)findViewById(R.id.expList);

		createPage = (Button) findViewById(R.id.create_new_page);
		returnLocalLib = (Button) findViewById(R.id.return_local_lib);

		popupMenu = new PopupMenu(this, findViewById(R.id.expList));
		popupMenu.getMenu().add(Menu.NONE, EDIT_PAGE, Menu.NONE, "Edit Page");
		popupMenu.getMenu().add(Menu.NONE, DELETE_PAGE, Menu.NONE, "Delete Page");
		popupMenu.setOnMenuItemClickListener(this);

		someStory = (Story) getIntent().getSerializableExtra("someStory");

		storyView.setText("Title: " + someStory.getTitle() + "\n" +
				"Description: " + someStory.getDescription() + "\n" +
				"Author: " + someStory.getAuthor() + "\n" +
				"Date: " + someStory.getDate() + "\n");

		fillData();

		createPage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				createPage();
				fillData();
			}
		});

		returnLocalLib.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				Intent i = new Intent(EditStoryActivity.this, OfflineLibraryActivity.class);
				startActivity(i);
			}
		});

		lstView.setOnGroupExpandListener(new OnGroupExpandListener()
		{  
			@Override
			public void onGroupExpand(int position) {
				somePage = (Page)adpt.getGroup(position);
				pageView.setText("Page Selected:    " + somePage.getTitle() + "\n" + "Content:    " +
						somePage.getPageDescription());
				popupMenu.show();


			}
		});


		/*
        lstView.setOnChildClickListener(new OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {

                Option subPage= (Option)adpt.getChild(groupPosition, childPosition);
                // update the text view with the country
                return true;

            }
        });
		 */
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {

		// User selects edit page
		case EDIT_PAGE:
			Intent i = new Intent(EditStoryActivity.this, EditPageActivity.class);
   			Bundle bundle = new Bundle();
   			bundle.putSerializable("somePage", somePage);
   			bundle.putSerializable("someStory", someStory);
   			i.putExtras(bundle);
			startActivity(i);
			break;

		// User selects delete page
		case DELETE_PAGE:
			// Ask for a confirmation from user by:
			// Instantiating an AlertDialog.Builder with its constructor
			AlertDialog.Builder builder = new AlertDialog.Builder(EditStoryActivity.this);

			// Add buttons
			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					// User clicked OK button
					deletePage();
					fillData();
				}
			});
			builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					// User cancelled the dialog
				}
			});

			// Chain together various setter methods to set the dialog characteristics
			builder.setMessage(R.string.delete_page_confirm);
			// Get the AlertDialog from create()
			AlertDialog dialog = builder.create();
			dialog.show();
			break;
		}
		return false;

	}

	/**
	 * Populates the list view with a list of all the pages in the story
	 */
	private void fillData() {
		//load model here
		List<Page> storyPages = someStory.getPages();
		adpt = new ExpandableListAdapter(this, lstView, storyPages);
		lstView.setAdapter(adpt);
	}

	private void createPage() {
		//New Page
		int pageCount = someStory.getPages().size();
		Page model = new Page("NEW PAGE " + "(" + (pageCount + 1) + ")", "");
		someStory.addPage(model);
		FileManager fLoader = new FileManager(EditStoryActivity.this);
		fLoader.saveStory(someStory, true);	
	}

	/**
	 * Delete a page from the story
	 */
	private void deletePage() {
		someStory.deletePage(somePage);
		FileManager fLoader = new FileManager(EditStoryActivity.this);
		fLoader.saveStory(someStory, true);		
	}
	
	@Override
    public void onResume(){
    super.onResume();
		FileManager fLoader = new FileManager(EditStoryActivity.this);	
    	someStory = fLoader.loadStory(someStory);
        fillData();
    }

	/* Do we want a context menu instead?
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, DELETE_ID, 0, R.string.menu_delete);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case DELETE_ID:
                //AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
                //mDbHelper.deletePage(info.id);
                fillData();
                return true;
        }
        return super.onContextItemSelected(item);
    }
	 */

}
