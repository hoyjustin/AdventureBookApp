package com.team11.projectpart2;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final MediaPlayer bsong  =MediaPlayer.create(MainActivity.this, R.raw.bmusic);
		bsong.start();
		Button createB = (Button)findViewById(R.id.button1);
		createB.setOnClickListener(new View.OnClickListener(){

			public void onClick(View view)
			{
				bsong.pause();

			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
