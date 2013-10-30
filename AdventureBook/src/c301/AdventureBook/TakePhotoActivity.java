package c301.AdventureBook;
//Creator: Zhao Zhang

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;

import com.example.adventurebook.R;
public class TakePhotoActivity extends Activity{
	//int REQUEST_CODE = 0;
	private static final int SELECT_PHOTO = 100;
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
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnIntent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, imageReturnIntent);
		switch(requestCode){
		case SELECT_PHOTO:
			If(resultCode == RESULT_OK);{
				Uri selectedImage = imageReturnIntent.getData();
				String[] filePathColumn = {MediaStore.Images.Media.DATA};
				Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
				cursor.moveToFirst();
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String filePath = cursor.getString(columnIndex);
				cursor.close();
				Bitmap yourSelcetedImage = BitmapFactory.decodeFile(filePath);
				
			}
			
		}
	}
	private void If(boolean b) {
		// TODO Auto-generated method stub
		
	}
}