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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import c301.AdventureBook.Models.Option;
import c301.AdventureBook.Models.Page;
import c301.AdventureBook.Models.Story;

import com.example.adventurebook.R;


public class EditStoryActivity extends Activity implements OnMenuItemClickListener, Serializable{

	private static final int DELETE_ID = Menu.FIRST + 1;
    private final static int ONE = 1;
    private final static int TWO = 2;
	
	private ExpandableListAdapter adpt;
	private ExpandableListView lstView;
	private TextView txtView;
	private Button createPage;
	private Button returnLocalLib;
	private PopupMenu popupMenu;
	
	private Story someStory;
	//private Story someStory2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.adventurebook.R.layout.edit_story);
        
        txtView = (TextView)findViewById(com.example.adventurebook.R.id.storyView);
        lstView = (ExpandableListView)findViewById(R.id.expList);
      
		createPage = (Button) findViewById(R.id.create_new_page);
		returnLocalLib = (Button) findViewById(R.id.return_local_lib);
		
		popupMenu = new PopupMenu(this, findViewById(R.id.expList));
		popupMenu.getMenu().add(Menu.NONE, ONE, Menu.NONE, "Edit Page");
		popupMenu.getMenu().add(Menu.NONE, TWO, Menu.NONE, "Delete Page");
		popupMenu.setOnMenuItemClickListener(this);
		
	    someStory = (Story) getIntent().getSerializableExtra("someStory");
		
        txtView.setText("Title: " + someStory.getTitle() + "\n" +
        		"Description: " + someStory.getDescription() + "\n" +
        		"Author: " + someStory.getAuthor() + "\n" +
        		"Date: " + someStory.getDate() + "\n");
		
        //fillData();
        
        createPage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				createPage();
				FileLoader fLoader = new FileLoader(EditStoryActivity.this);
				fLoader.saveStory(someStory, true);		
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
				Page mainPage= (Page)adpt.getGroup(position);
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
           TextView tv = (TextView) findViewById(R.id.storyView);
           switch (item.getItemId()) {
           case ONE:
                  tv.setText("ONE");
                  Intent i = new Intent(EditStoryActivity.this, EditPageActivity.class);
                  startActivity(i);
                  break;
           case TWO:
                  tv.setText("TWO");
                  
               // 1. Instantiate an AlertDialog.Builder with its constructor
                  AlertDialog.Builder builder = new AlertDialog.Builder(EditStoryActivity.this);

               // Add the buttons
                  builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 // User clicked OK button
                             }
                         });
                  builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 // User cancelled the dialog
                             }
                         });

                  // 2. Chain together various setter methods to set the dialog characteristics
                  builder.setMessage(R.string.delete_page_confirm);

                  // 3. Get the AlertDialog from create()
                  AlertDialog dialog = builder.create();
                  dialog.show();
                  break;
           }
           return false;

    }
    
    private void fillData() {
	//load model here
	List<Page> storyPages = someStory.getPages();
    
    adpt = new ExpandableListAdapter(this, lstView, storyPages);
    
    lstView.setAdapter(adpt);
	}

	//fill story with temp data
	private void tempData(){
    	  	
    	List<Option> lstdm = new ArrayList<Option>();
    	Option dm1 = new Option();

    	//Page 1
    	Page model = new Page();
    	model.setTitle("PAGE 1");
    	model.setOptions(lstdm);
    	
    	dm1 = new Option();
    	dm1.setGoToPage("Page 2");
    	lstdm.add(dm1);
    	
    	dm1 = new Option();
    	dm1.setGoToPage("Page 3");
    	lstdm.add(dm1);
    	
    	someStory.addPage(model);
 

    	//Page 2  	
    	model = new Page();
    	model.setTitle("PAGE 2");
    	
    	lstdm = new ArrayList<Option>();
    	
    	dm1 = new Option();
    	dm1.setGoToPage("Page 3");
    	lstdm.add(dm1);
    	
    	model.setOptions(lstdm);
    	someStory.addPage(model);
    	
    	//Page 3
    	model = new Page();
    	model.setTitle("PAGE 3");
    	
    	lstdm = new ArrayList<Option>();
    	
    	dm1 = new Option();
    	dm1.setGoToPage("END");
    	lstdm.add(dm1);
    	
    	model.setOptions(lstdm);
    	someStory.addPage(model);
    	    	    	    	  
    }

    private void createPage() {
    	List<Option> lstdm = new ArrayList<Option>();
    	Option dm1 = new Option();
    	
    	//New Page
    	Page model = new Page();
    	model.setTitle("NEW PAGE");
    	lstdm = new ArrayList<Option>();
    	
    	dm1 = new Option();
    	dm1.setGoToPage("END");
    	lstdm.add(dm1);
    	
    	model.setOptions(lstdm);
    	someStory.addPage(model);
    	
        fillData();
    	
    }
    
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
	
}