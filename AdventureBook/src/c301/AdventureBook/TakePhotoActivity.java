package c301.AdventureBook;
//Creator: Zhao Zhang

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;

import com.example.adventurebook.R;

public class TakePhotoActivity extends Activity{
	int REQUEST_CODE = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload_media);
		
		Button uploadFromPhone = (Button)findViewById(R.id.fromPhoneButton);
		Button uploadFromWebCam = (Button)findViewById(R.id.fromWebCamButton);
		uploadFromPhone.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				final int SELECT_PHOTO = 100;
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
				photoPickerIntent.setType("image/*");
				startActivityForResult(photoPickerIntent, SELECT_PHOTO);
				
				// TODO Auto-generated method stub
			}
		});
		uploadFromWebCam.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
			
	}
}