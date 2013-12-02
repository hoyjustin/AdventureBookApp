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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import c301.AdventureBook.Controllers.LocalLibraryManager;
import c301.AdventureBook.Controllers.StoryManager;
import c301.AdventureBook.Models.Story;

import com.example.adventurebook.R;

/**
 * The create story activity allows the author to create a new story. When the
 * story is created successfully, it is stored as a file in the phone's internal
 * storage. *
 * 
 * @author Terence Yin Kiu Leung
 * @author Justin Hoy - Minor Editor
 * @author Zhao Zhang - Minor Editor
 */
public class EditStoryInfoActivity extends Activity {
	private static final int PHOTO_ACTIVITY_REQUEST = 1001;

	private EditText mStoryTitle;
	private EditText mStoryAuthor;
	private EditText mStoryDescription;
	private TextView mDate;

	LocalLibraryManager lManagerInst;
	StoryManager sManagerInst;

	String storyTitle;
	String storyAuthor;
	String storyDescription;
	String formattedDate;
	ImageView image;
	String imageByte;
	Typeface font;

	Story currentStory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_story_info);

		image = (ImageView) findViewById(R.id.imageView1);
		mStoryTitle = (EditText) findViewById(R.id.editStoryTitle);
		mStoryDescription = (EditText) findViewById(R.id.editStoryDescription);
		mStoryAuthor = (EditText) findViewById(R.id.authorText);
		mDate = (TextView) findViewById(R.id.dateText);

		Button saveChanges = (Button) findViewById(R.id.createStoryButton);

		font = Typeface.createFromAsset(getAssets(), "fonts/straightline.ttf");
		saveChanges.setText("Save Changes");
		saveChanges.setTypeface(font);
		initManager();
		setInfo();
		image.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						TakePhotoActivity.class);
				startActivityForResult(intent, PHOTO_ACTIVITY_REQUEST);
			}
		});
		saveChanges.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				editStory();
			}
		});
	}

	private void initManager() {
		sManagerInst = StoryManager.getInstance();
		sManagerInst.initContext(this);
		lManagerInst = LocalLibraryManager.getInstance();
		lManagerInst.initContext(this);
	}
	
	/**
	 * Display the Story's Previous Info
	 */
	@SuppressLint("SimpleDateFormat")
	private void setInfo() {

		currentStory = sManagerInst.getCurrentStory();

		if (currentStory.getImageByte()!= null){
			imageByte = currentStory.getImageByte();
			byte[] decodedString = Base64.decode(imageByte, Base64.DEFAULT);
			Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length); 
			image.setImageBitmap(decodedByte);
		}
		
		
		mStoryTitle.setText(currentStory.getTitle());
		mStoryDescription.setText(currentStory.getDescription());
		mStoryAuthor.setText(currentStory.getAuthor());
		mDate.setText(currentStory.getDate());
	}
	
	/**
	 * Saves the edited story to file and exits activity.
	 */
	private void editStory() {
		getUserText();

		// Make sure that the user inputs a nonempty story title
		if (storyTitle.length() == 0) {
			Toast.makeText(this, "Story title cannot be blank!",
					Toast.LENGTH_SHORT).show();
		}
		else if (storyAuthor.length() == 0) {
			Toast.makeText(this, "Story's author cannot be blank!",
					Toast.LENGTH_SHORT).show();

		}
		else {
			writeEditedStory();
			boolean saveSuccess = sManagerInst.saveStory(currentStory, true);

			if (saveSuccess == true) {
				Toast.makeText(this, "Story Info Edited: " + storyTitle,
						Toast.LENGTH_LONG).show();
				finish();
			}
		}
	}

	private void writeEditedStory() {
		lManagerInst.deleteStory(currentStory);
		currentStory.setTitle(storyTitle);
		currentStory.setAuthor(storyAuthor);
		currentStory.setDescription(storyDescription);
		if (imageByte != null) {
			currentStory.setImageByte(imageByte);
		}
	}

	/**
	 * Get the user inputed story title, description, and author name, and
	 * stores them into class variables
	 */
	private void getUserText() {
		storyTitle = mStoryTitle.getText().toString();
		storyAuthor = mStoryAuthor.getText().toString();
		storyDescription = mStoryDescription.getText().toString();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == PHOTO_ACTIVITY_REQUEST && resultCode == RESULT_OK) {

			imageByte = data.getStringExtra("imagebyte");

			byte[] decodedString = Base64.decode(imageByte, Base64.DEFAULT);
			Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString,
					0, decodedString.length);
			image.setImageBitmap(decodedByte);

		}
	}
}
