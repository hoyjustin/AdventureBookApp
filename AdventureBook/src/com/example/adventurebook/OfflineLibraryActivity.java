package com.example.adventurebook;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class OfflineLibraryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_local_stories);
		populateListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void populateListView(){
		/*This is just a test.*/
		
		ListView myStories = (ListView) findViewById(R.id.myStorylistView);
		
		String [] stories = {"Story1","Story2","Story3"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_row, stories);  

		myStories.setAdapter(adapter);
		
	}

	/**
	 * @uml.property  name="controller"
	 * @uml.associationEnd  inverse="offlineLibraryActivity:com.example.adventurebook.Controller"
	 */
	private Controller controller;

	/**
	 * Getter of the property <tt>controller</tt>
	 * @return  Returns the controller.
	 * @uml.property  name="controller"
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * Setter of the property <tt>controller</tt>
	 * @param controller  The controller to set.
	 * @uml.property  name="controller"
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	

	
}
