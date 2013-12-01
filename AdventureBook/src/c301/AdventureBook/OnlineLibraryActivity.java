/*
 * Copyright (C) <2013>  <Minhal Syed>
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import c301.AdventureBook.Controllers.StoryManager;
import c301.AdventureBook.ElasticSearch.ESClient;
import c301.AdventureBook.Models.Story;
import c301.AdventureBook.Models.testCases;

import com.example.adventurebook.R;

/**
 * This is the online library activity. This activity's main purpose is to show
 * all the stories that are present in the WebServer to the user. It also
 * provides the user with an interface to interact with the online stories.
 * Interactions include: viewing story, or downloading story.
 * 
 * @author Minhal Syed - Main Creator
 */
public class OnlineLibraryActivity extends Activity {

	ArrayList<Story> onlineStoryLibrary; // This ArrayList will contain all the
											// online
											// stories that are on the server.

	ArrayAdapter<Story> adapter; // This is an adapter that will be used to
									// populate the listview.

	ESClient client = new ESClient(); // We need a communicator for the Server.

	StoryManager sManagerInst; // Controller for the Stories
	Typeface font;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.online_library);

		font = Typeface.createFromAsset(getAssets(), "fonts/straightline.ttf");
		TextView txt = (TextView) findViewById(R.id.online_lib);  
		txt.setTypeface(font);  
		initializeGlobals();
		
		// First CheckWhether Internet is Connected:
		if (isNetworkConnected()) {
			// Download all Stories from online then populate
			// the list view. Since we need to give the application
			// some time to connect to the Internet, we have to use
			// AsyncTask.
			new getStoriesAndDisplay().execute();
		} else {
			// Else Display Message that Internet is not available and
			// return to Local Library.
			createAlertDialog();
		}
	}
	
	private void initializeGlobals(){
		sManagerInst = StoryManager.getInstance();
		sManagerInst.initContext(this);
	}

	/**
	 * Create a search action in the action bar
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.library_options_menu, menu);

		// Associate searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		final SearchView searchView = (SearchView) menu.findItem(R.id.search)
				.getActionView();

		final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
			/**
			 * This function is called every time a user enters anything on the
			 * search bar.
			 * 
			 */
			@Override
			public boolean onQueryTextChange(String newText) {
				// Do something

				// String keyword =
				// searchView.getQuery().toString().toLowerCase();
				// offlineStoryLibrary =
				// fLoader.loadStoryFileWithKeyword(keyword);

				// populateListView();
				adapter.getFilter().filter(newText);
				return true;
			}

			@Override
			public boolean onQueryTextSubmit(String query) {
				// Do something
				return true;
			}
		};

		searchView.setOnQueryTextListener(queryTextListener);

		return true;
	}

	/**
	 * This function checks whether an Internet connection is available to the
	 * activity.
	 * 
	 * Tutorial from:
	 * http://stackoverflow.com/questions/9570237/android-check-internet
	 * -connection
	 * 
	 * @return boolean
	 */
	private boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		return (cm.getActiveNetworkInfo() != null);
	}

	/**
	 * This function is called when the Return To Local Library Button is
	 * pressed. It starts the Local Library activity and finishes the current
	 * one.
	 * 
	 * @param v
	 */
	public void LaunchLocalLibraryActivity(View v) {
		Intent i = new Intent(this, OfflineLibraryActivity.class);
		startActivity(i);
		finish();
	}

	private void fetchDataFromServer() {
		onlineStoryLibrary = client.getAllStories();
	}

	private class getStoriesAndDisplay extends
			AsyncTask<String, String, String> {

		// Display Loading Spinner on the activity.
		// Tutorial:
		// http://stackoverflow.com/questions/12559461/how-to-show-progress-barcircle-in-an-activity-having-a-listview-before-loading
		LinearLayout linearLayoutForProgress = (LinearLayout) findViewById(R.id.linearLayoutForProgress);

		@Override
		protected void onPreExecute() {
			// SHOW THE SPINNER WHILE LOADING.
			linearLayoutForProgress.setVisibility(View.VISIBLE);
		}

		/**
		 * This function is executed first. This function contacts the server
		 * and downloads all the stories to the phone's memory.
		 */
		@Override
		protected String doInBackground(String... arg0) {
			fetchDataFromServer();
			return null;
		}

		/**
		 * This function is executed when the previous one finishes. Once we
		 * have downloaded all the online stories, we can display them to the
		 * user.
		 */
		protected void onPostExecute(String result) {
			populateListView();

			// HIDE THE SPINNER AFTER LOADING.
			linearLayoutForProgress.setVisibility(View.GONE);
		}

	}

	private void populateListView() {
		final ListView onlineLV = (ListView) findViewById(R.id.online_library_listView);
		adapter = new CustomStoryAdapter(this, R.layout.library_row,
				onlineStoryLibrary);
		onlineLV.setAdapter(adapter);

		registerForContextMenu(onlineLV);

		// tutorial used =
		// http://stackoverflow.com/questions/9097723/adding-a-onclicklistener-to-listview-android

		// When Clicked on the list item, we can return a story.
		onlineLV.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// This is the story object that is returned when a list item is
				// clicked.
				Story story = (Story) onlineLV.getItemAtPosition(position);
				viewStory(story);

			}
		});
	}
	
	private void viewStory(Story story) {
		
		//Tell the Application that we are viewing this story from Online.
		((AdventureBook) this.getApplication()).setIsOnlineParameter(true);
						
		sManagerInst.setCurrentStory(story);
		Intent intent = new Intent(this, ViewStoryActivity.class);
		startActivity(intent);
		
	}

	/**
	 * Create long click menu for the ListView. When LongCliked, we can see
	 * Publish Story, EditStory and DeleteStory functions.
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// tutorial from:
		// http://stackoverflow.com/questions/2321332/detecting-which-selected-item-in-a-listview-spawned-the-contextmenu-android

		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add("Download Story");
		menu.add("Delete Story");
	}

	/**
	 * This function is a context menu listener. If the user presses publish,
	 * delete, or edit story, this listener acts accordingly.
	 */

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();

		// Get the Story that is clicked on the listView.
		Story storyClicked = adapter.getItem(info.position);

		if (item.getTitle() == "Download Story") {
			downloadStory(storyClicked);
		} else if (item.getTitle() == "Delete Story") {

			new deleteStoryTask(storyClicked).execute();
			new getStoriesAndDisplay().execute();
		}
		return true;
	}

	private class deleteStoryTask extends AsyncTask<String, String, String> {

		Story story;

		public deleteStoryTask(Story storyClicked) {
			this.story = storyClicked;
		}

		@Override
		protected String doInBackground(String... arg0) {
			client.deleteStory(story);
			return null;
		}

		protected void onPostExecute(String result) {
			Toast.makeText(OnlineLibraryActivity.this,
					"Delete " + this.story.getTitle(), Toast.LENGTH_SHORT)
					.show();
		}
	}

	/**
	 * This function downloads the online story to the phone's memory.
	 * 
	 * @param storyClicked
	 */
	private void downloadStory(Story storyClicked) {
		sManagerInst.saveStory(storyClicked, true);
		Toast.makeText(this,
				"Downloaded: " + storyClicked.getTitle(), Toast.LENGTH_LONG)
				.show();
	}
	
	/**
	 * This function shows a random story from Online Library.
	 * @param v
	 */
	public void showRandomStory(View v){
		Random r = new Random();
		int choice = r.nextInt(onlineStoryLibrary.size());
		Story randomStory = onlineStoryLibrary.get(choice);
		

		sManagerInst.setCurrentStory(randomStory);
		Intent intent = new Intent(this, ViewStoryActivity.class);
		startActivity(intent);
	}

	/**
	 * This function creates a dialog box that shows internet connection error.
	 * 
	 */
	private void createAlertDialog() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		// set title
		alertDialogBuilder.setTitle("Network Error!");

		// set dialog message
		alertDialogBuilder
				.setMessage(
						"There's no network connection right now.\nReturning to Local Library.")
				.setCancelable(false)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// close current activity and go back to Local Library.
						LaunchLocalLibraryActivity(null);
					}
				});
		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}

}