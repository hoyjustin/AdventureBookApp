/*
 * Copyright (C) <2013>  <Zhao Zhang>
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
//Creator: Zhao Zhang
//Done

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.adventurebook.R;

/**
 * Take Photo Activity allow user to take a photo from camera, or select a photo from phone's gallery.
 * It will pass a path of photo to other class
 * @author Zhao Zhang
 *
 */
public class TakePhotoActivity extends Activity{
	//int REQUEST_CODE = 0;
	private static final int SELECT_PHOTO = 100;
	private static final int TAKE_PHOTO = 101;
	static Uri capturedImageUri=null;
	String show_path;
	int select_result = 0;
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
				//Intent photoPickerIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);				
				//photoPickerIntent.setType("image/*");
				//photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(photoPickerIntent, SELECT_PHOTO);
				//startActivityForResult(Intent.createChooser(photoPickerIntent,"Select Picture"), SELECT_PHOTO);				
				// TODO Auto-generated method stub
			}
		});
		uploadFromWebCam.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String curentDateandTime = sdf.format(new Date());
				String newFilePath = Environment.getExternalStorageDirectory() + "/"+curentDateandTime+".jpg"; 
				Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				File file = new File(Environment.getExternalStorageDirectory(),  curentDateandTime+ ".jpg");
				show_path = newFilePath;
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				capturedImageUri = Uri.fromFile(file);
				cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
				startActivityForResult(cameraIntent, TAKE_PHOTO);
				// TODO Auto-generated method stub

			}
		});
		uploadConfirm.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(select_result == 1){
					saveAndFinish();
				}

			}

		});


	}
	private void saveAndFinish() {
		final EditText my_path = (EditText) findViewById(R.id.editpath);
		my_path.setText(show_path);
		Intent intent = new Intent();		
		intent.putExtra("path", show_path);
		setResult(RESULT_OK, intent);
		finish();

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnIntent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, imageReturnIntent);
		if(imageReturnIntent != null){
			switch(requestCode){			
			//Bitmap photo = (Bitmap) imageReturnIntent.getExtras().get("imageReturnIntent");

			//ImageView test = (ImageView) findViewById(R.id.upload_photo_view);
			//test.setImageBitmap(photo);

			//try{
			//	String imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/name.jpg";
			//	FileOutputStream out = new FileOutputStream(imageFilePath);
			//	photo.compress(Bitmap.CompressFormat.JPEG, 90, out);
			//} catch(Exception e) {
			//	e.printStackTrace();
			//}

			case SELECT_PHOTO:
				if(resultCode == RESULT_OK);{
					//Uri selectedImage = imageReturnIntent.getData();
					//
					//try {
					//	InputStream imageStream = getContentResolver().openInputStream(selectedImage);
					//	Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
					//	ImageView test2 = (ImageView) findViewById(R.id.upload_photo_view);
					//	test2.setImageBitmap(yourSelectedImage);
					//} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					//	e.printStackTrace();
					//}


					if(imageReturnIntent.getData() != null){
						try {
							Uri selectedImage = imageReturnIntent.getData();
							String[] filePathColumn = {MediaStore.Images.Media.DATA};
							Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
							cursor.moveToFirst();
							int columnIndex =  cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
							String filePath = cursor.getString(columnIndex);
							cursor.close();
							ImageView test = (ImageView) findViewById(R.id.upload_photo_view);
							test.setImageBitmap(BitmapFactory.decodeFile(filePath));
							show_path = filePath;
							select_result = 1;
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}

				}
				break;
			case TAKE_PHOTO:
				If(resultCode == RESULT_OK);{
					try {
						ImageView test = (ImageView) findViewById(R.id.upload_photo_view);
						Bitmap bitmap = MediaStore.Images.Media.getBitmap( getApplicationContext().getContentResolver(),  capturedImageUri);
						test.setImageBitmap(bitmap);
						select_result = 1;
						break;
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			}
		}

	}
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
	}
	private void If(boolean b) {
		// TODO Auto-generated method stub

	}
}

