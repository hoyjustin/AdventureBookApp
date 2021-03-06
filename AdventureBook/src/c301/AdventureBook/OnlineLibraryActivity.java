/*
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

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
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
import c301.AdventureBook.Controllers.CacheManager;
import c301.AdventureBook.Controllers.StoryManager;
import c301.AdventureBook.ElasticSearch.ESClient;
import c301.AdventureBook.Models.Story;

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

	ArrayList<Story> onlineStoryLibrary = new ArrayList<Story>(); // This
																	// ArrayList
																	// will
																	// contain
																	// all the
	// online
	// stories that are on the server.

	ArrayAdapter<Story> adapter; // This is an adapter that will be used to
	// populate the listview.

	ESClient client = new ESClient(); // We need a communicator for the Server.

	StoryManager sManagerInst; // Controller for the Stories
	CacheManager cManagerInst;
	Typeface font;
	boolean networkConnected;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.online_library);

		font = Typeface.createFromAsset(getAssets(), "fonts/straightline.ttf");
		TextView txt = (TextView) findViewById(R.id.online_lib);
		txt.setTypeface(font);
		initializeGlobals();
		attemptNetworkConnection();
	}

	/**
	 * Attempt to connect to the network. If there is no connection, shows a
	 * message and returns to the offline library.
	 */
	private void attemptNetworkConnection() {
		// First CheckWhether Internet is Connected:
		networkConnected = isNetworkConnected();
		if (networkConnected) {
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

	/**
	 * Initiate instances of the CacheManager and StoryManager for the current
	 * activity.
	 */
	private void initializeGlobals() {
		cManagerInst = CacheManager.getInstance();
		cManagerInst.initApplicationContext(this.getApplicationContext());
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
		//SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
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
	 * Checks whether an Internet connection is available to the activity.
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

	/**
	 * Fetches data from the server.
	 * 
	 */
	private void fetchDataFromServer() {
		onlineStoryLibrary = client.getAllStories();
		cManagerInst.cacheData(onlineStoryLibrary);
	}

	/**
	 * Displays a loading spinner while the application fetches the stories 
	 * from the server in the background.
	 * 
	 */
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
			if (networkConnected) {
				fetchDataFromServer();
			} else {
				onlineStoryLibrary = cManagerInst.getCacheLibrary();
			}
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

	/**
	 * Populates the list view for the online library and sets the click
	 * listener for viewing the stories.
	 */
	private void populateListView() {
		final ListView onlineLV = (ListView) findViewById(R.id.online_library_listView);
		adapter = new CustomStoryAdapter(this, R.layout.library_row,
				onlineStoryLibrary);
		onlineLV.setAdapter(adapter);

		registerForContextMenu(onlineLV);

		// tutorial used =
		// http://stackoverflow.com/questions/9097723/adding-a-onclicklistener-to-listview-android

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

	/**
	 * Starts the ViewStoryActivity for the story that was clicked.
	 * 
	 * @param story
	 *            the story that was clicked.
	 */
	private void viewStory(Story story) {

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
		// (Add this code only if we allow user to delete from online library)
		// menu.add("Delete Story");

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
		}
		// (Add this code only if we allow user to delete from online library)

		// else if (item.getTitle() == "Delete Story") {
		// if (networkConnected) {
		// new deleteStoryTask(storyClicked).execute();
		// }
		// else{
		// cManagerInst.deleteCachedStory(storyClicked);
		// }
		// new getStoriesAndDisplay().execute();
		// }
		return true;
	}

	// (Add this code only if we allow user to delete from online library)

	// private class deleteStoryTask extends AsyncTask<String, String, String> {
	//
	// Story story;
	//
	// public deleteStoryTask(Story storyClicked) {
	// this.story = storyClicked;
	// }
	// @Override
	// protected String doInBackground(String... arg0) {
	// client.deleteStory(story);
	// return null;
	// }
	//
	// protected void onPostExecute(String result) {
	// Toast.makeText(OnlineLibraryActivity.this,
	// "Deleted " + this.story.getTitle(), Toast.LENGTH_SHORT)
	// .show();
	// }
	// }

	/**
	 * This function downloads the online story to the phone's memory.
	 * 
	 * @param storyClicked
	 */
	private void downloadStory(Story storyClicked) {
		sManagerInst.saveStory(storyClicked, true);
		Toast.makeText(this, "Downloaded: " + storyClicked.getTitle(),
				Toast.LENGTH_LONG).show();
	}

	/**
	 * This function shows a random story from Online Library.
	 * 
	 * @param v
	 */
	public void showRandomStory(View v) {
		Random r = new Random();
		if (onlineStoryLibrary.size() != 0) {
			int choice = r.nextInt(onlineStoryLibrary.size());
			Story randomStory = onlineStoryLibrary.get(choice);
			sManagerInst.setCurrentStory(randomStory);
			Intent intent = new Intent(this, ViewStoryActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(OnlineLibraryActivity.this,
					"You should have a story in online library first!",
					Toast.LENGTH_LONG).show();
		}
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
						"There's no network connection! Library will now be Loaded from Cached Data.")
				.setCancelable(false)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// close current activity and go back to Local Library.
						new getStoriesAndDisplay().execute();
					}

				});
		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}
}
