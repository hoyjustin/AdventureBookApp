//Creator: Justin Hoy

package c301.AdventureBook.EditStory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.adventurebook.R;


import c301.AdventureBook.Models.Option;
import c301.AdventureBook.Models.Page;
import c301.AdventureBook.Models.Story;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class EditStoryActivity extends Activity {
    private static final int DELETE_ID = Menu.FIRST + 1;
	
	private static final String FILENAME = "file.sav";
	
	private ExpandableListAdapter adpt;
	private ExpandableListView lstView;
	private TextView txtView;
	private Button createPage;
	private Button returnLocalLib;
	
	private Story someStory;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.adventurebook.R.layout.edit_story);
        
        txtView = (TextView)findViewById(com.example.adventurebook.R.id.storyView);
        lstView = (ExpandableListView)findViewById(R.id.expList);
      
		createPage = (Button) findViewById(R.id.create_new_page);
		returnLocalLib = (Button) findViewById(R.id.return_local_lib);
        
		someStory = loadFromFile();
		
		if(someStory == null){
		someStory = new Story("asd", "bob", "123123", 1);
		tempData(); 
		}
		
        txtView.setText("Title: " + someStory.getTitle() + "\n" +
        		"Author: " + someStory.getAuthor() + "\n" +
        		"Date: " + someStory.getDate() + "\n");
		
        fillData();
        
        createPage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				createPage();
			}
		});
        
        returnLocalLib.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				saveStory();
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

    
	private Story loadFromFile() {

		try {
			FileInputStream fis = openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			while (true) {
				someStory = (Story) ois.readObject();
				Log.d("load", "Success");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return someStory;
	}
    
	private void saveStory() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					0);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(someStory);
				Log.d("save", "Success");
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
