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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import c301.AdventureBook.Controllers.FileManager;
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

	ESClient client = new ESClient(); // We need a communicator for the Server.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.online_library);

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
		}
	}

	private void populateListView() {
		ListView onlineLV = (ListView) findViewById(R.id.online_library_listView);
		ArrayAdapter<Story> adapter = new CustomStoryAdapter(this,
				R.layout.library_row, onlineStoryLibrary);
		onlineLV.setAdapter(adapter);
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