/*
 * Copyright (C) <2013>  <Lin Tong>
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

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import c301.AdventureBook.Controllers.StoryManager;
import c301.AdventureBook.Models.Annotation;
import c301.AdventureBook.Models.Page;

import com.example.adventurebook.R;

/**
 * This is the annotation activity. It is run when a user wants to annotate a story fragment.
 * 
 * @author Lin Tong
 *
 */
public class AnnotationActivity extends Activity {
	private EditText author;
	private EditText comment;
	private Annotation someAnnotation;
	String authorAnnotation;
	String commentAnnotation;
	Intent i;
	ImageView image;
	String show_path;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.annotations);
		
		image = (ImageView) findViewById(R.id.imageView1);
		
		author = (EditText) findViewById(R.id.editTextAnnotationAuthor);
		
		comment = (EditText)findViewById(R.id.editTextAnnotationComment);
		
		ImageButton attachImage = (ImageButton)findViewById(R.id.imageButtonAnnotationAttachImage);
		
		image.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						TakePhotoActivity.class);
				startActivityForResult(intent, 1001);
			}
		});
		
		attachImage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goTakePhoto();
			}
		});
		
		Button returnPage = (Button) findViewById(R.id.annotationButtonReturnToPage);
		returnPage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goBackPage();
			}
		});
		
		Button submit = (Button) findViewById(R.id.submit);
		
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createAnnotation();
			}
		});}
	/**
	 * this will create annotation
	 * save as a bundle and return it back to view page
	 */
		private void createAnnotation(){
			getUserInfo();
			someAnnotation = new Annotation(authorAnnotation, commentAnnotation); 
			
			//goBackPage();
			//Bundle bundle = new Bundle();
			//bundle.putSerializable("someAnnotation", someAnnotations);
			//i.putExtras(bundle);
			//startActivityForResult(i,0);
			
			StoryManager sManager = StoryManager.getInstance();
			sManager.initContext(this);
			
			Page currentPage = sManager.getPage();
			sManager.getStory().deletePage(currentPage);

			currentPage.addAnnotation(someAnnotation);
			sManager.getStory().addPage(currentPage);
			
			sManager.saveStory(sManager.getStory(), true);
			
			
		}
		/**
		 * This will jump to takephoto activity
		 */
		private void goTakePhoto(){
			Intent b = new Intent(this, TakePhotoActivity.class);
		}
		/**
		 * this should go back to the view page
		 */
		private void goBackPage(){
			//i = new Intent(this, StoryFragment.class);
		}
		/**
		 * get the user's info for annotation
		 * it will get the author 
		 * it will get the comment
		 */
		private void getUserInfo(){
			authorAnnotation = author.getText().toString();
			commentAnnotation= comment.getText().toString();
			
		}
		/**
		 * this function is going get the photo by using the path
		 * return it and display it on the top of the screen
		 */
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			if (requestCode == 1001 && resultCode == RESULT_OK) {

				show_path = data.getStringExtra("path");

				image.setImageBitmap(BitmapFactory.decodeFile(show_path));

			}
		}
	}
