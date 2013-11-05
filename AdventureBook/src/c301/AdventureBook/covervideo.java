package c301.AdventureBook;


import com.example.adventurebook.R;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

public class covervideo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.cover);
		VideoView vv = (VideoView)this.findViewById(R.id.videoView);
		String fileName = "android.resource://" + getPackageName() + "/" + R.raw.startvideo;
		vv.setVideoURI(Uri.parse(fileName));
		vv.start();
		vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
	        @Override
	        public void onCompletion(MediaPlayer mp){
	            // invoke your activity here
	        	endImage();
	        } 
	    });

	}

	private void endImage(){
		Intent i = new Intent(this,coverimage.class);
		startActivity(i);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
