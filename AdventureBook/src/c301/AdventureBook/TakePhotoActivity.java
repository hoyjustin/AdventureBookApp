package c301.AdventureBook;
//Creator: Zhao Zhang

import java.io.FileOutputStream;

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
import android.widget.ImageView;
import android.os.Environment;

import com.example.adventurebook.R;
public class TakePhotoActivity extends Activity{
	//int REQUEST_CODE = 0;
	private static final int SELECT_PHOTO = 100;
	private static final int TAKE_PHOTO = 101;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload_media);
		
		Button uploadFromPhone = (Button)findViewById(R.id.fromPhoneButton);
		Button uploadFromWebCam = (Button)findViewById(R.id.fromWebCamButton);
		Button uploadConfirm = (Button)findViewById(R.id.confirmButton);
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
				Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(cameraIntent, TAKE_PHOTO);
				// TODO Auto-generated method stub
				
			}
		});
		uploadConfirm.setOnClickListener(new OnClickListener(){

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
				ImageView test = (ImageView) findViewById(R.id.upload_photo_view);
			    test.setImageBitmap(yourSelcetedImage);
			}
		case TAKE_PHOTO:
			if(resultCode == RESULT_OK);{
				Bitmap photo = (Bitmap) imageReturnIntent.getExtras().get("imageReturnIntent");
				ImageView test = (ImageView) findViewById(R.id.upload_photo_view);
				test.setImageBitmap(photo);
				
				try{
					String imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/name.jpg";
					FileOutputStream out = new FileOutputStream(imageFilePath);
					photo.compress(Bitmap.CompressFormat.JPEG, 90, out);
				} catch(Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}
	}
	private void If(boolean b) {
		// TODO Auto-generated method stub
		
	}
}