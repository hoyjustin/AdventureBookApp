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
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import c301.AdventureBook.Models.Annotations;

import com.example.adventurebook.R;

/**
 * This is the annotation activity. It is run when a user wants to annotate a story fragment.
 * 
 * @author Terence
 *
 */
public class AnnotationActivity extends Activity {
	private EditText author;
	private EditText comment;
	private Annotations someAnnotations;
	String authorAnnotation;
	String commentAnnotation;
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.annotations);
		
		author = (EditText) findViewById(R.id.editTextAnnotationAuthor);
		
		comment = (EditText)findViewById(R.id.editTextAnnotationComment);
		
		ImageButton attachImage = (ImageButton)findViewById(R.id.imageButtonAnnotationAttachImage);
		
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
		private void createAnnotation(){
			getUserInfo();
			someAnnotations = new Annotations(authorAnnotation, commentAnnotation, 0); 
			
			goBackPage();
			Bundle bundle = new Bundle();
			bundle.putSerializable("someAnnotation", someAnnotations);
			i.putExtras(bundle);
			startActivityForResult(i,0);
			
		}
		private void goTakePhoto(){
			Intent b = new Intent(this, TakePhotoActivity.class);
		}
		private void goBackPage(){
			i = new Intent(this, StoryFragment.class);
		}
		private void getUserInfo(){
			authorAnnotation = author.getText().toString();
			commentAnnotation= comment.getText().toString();
			
		}
	}
