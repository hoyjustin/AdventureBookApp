package c301.AdventureBook;
//Creator: Zhao Zhang

import android.os.Bundle;



import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.adventurebook.R;

public class EditPageActivity extends Activity {
	private static final int PHOTO_ACTIVITY_REQUEST = 1001;
	ImageView image;
	String show_path;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_page);
		image = (ImageView) findViewById(R.id.edit_page_image);
		EditText et = (EditText) findViewById(R.id.addPageDescription);
		image.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    Intent intent = new Intent(getApplicationContext(), TakePhotoActivity.class);
			    startActivityForResult(intent, PHOTO_ACTIVITY_REQUEST);
			}
		});
		Button saveMyPage = (Button) findViewById(R.id.savePageButton);
		saveMyPage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				saveAndFinish();
				
			}
		});
		

	}
	private void saveAndFinish() {
		EditText et = (EditText) findViewById(R.id.addPageDescription);
		String noteText = et.getText().toString();
		Intent intent = new Intent();		
		intent.putExtra("path", show_path);
		intent.putExtra("text", noteText);
		setResult(RESULT_OK, intent);
		finish();
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == PHOTO_ACTIVITY_REQUEST && resultCode == RESULT_OK){
			show_path = data.getStringExtra("path");
			image.setImageBitmap(BitmapFactory.decodeFile(show_path));
			
		}
	}
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
	}

}