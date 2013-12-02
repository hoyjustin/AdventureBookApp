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

package c301.AdventureBook;


import com.example.adventurebook.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceView;

/**
 * This is the first activity that is started when the user first opens the app.
 * It shows a simple splash screen before proceeding to the offline library
 * activity where the user will be able to browse available stories. source:
 * http://www.androidhive.info/2013/07/how-to-implement-android-splash-screen-2/
 * 
 * @author Terence Yin Kiu Leung
 * 
 */
public class SplashScreenMainActivity extends Activity {

	// Splash screen timer
	private static int SPLASH_TIME_OUT = 3000; // 1000 = 1 second

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);

		
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// This method will be executed once the timer is over
				// Start OfflineLibraryActivity
				Intent i = new Intent(SplashScreenMainActivity.this,
						OfflineLibraryActivity.class);
				startActivity(i);

				// close this activity
				finish();
			}
		}, SPLASH_TIME_OUT);
	}

}
