package c301.AdventureBook;
/**
 * Creator: Terence Yin Kiu Leung
 * 
 * source: http://www.androidhive.info/2013/07/how-to-implement-android-splash-screen-2/
 */


import com.example.adventurebook.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class SplashScreenMainActivity extends Activity {

	// Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		
		new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreenMainActivity.this, OfflineLibraryActivity.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
	}
	
}
