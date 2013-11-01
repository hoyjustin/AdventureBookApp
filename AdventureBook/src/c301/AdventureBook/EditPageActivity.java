//Creator: Justin Hoy

package c301.AdventureBook;

import java.io.Serializable;
import com.example.adventurebook.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EditPageActivity extends Activity implements Serializable{


	private EditText editText;
	private Button createOption;
	private Button savePage;
	private CoverFlow coverFlow;
	ImageAdapter coverImageAdapter;


	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.example.adventurebook.R.layout.edit_page);

		editText = (EditText)findViewById(com.example.adventurebook.R.id.editStoryDescription);
		createOption = (Button) findViewById(R.id.new_option);
		savePage = (Button) findViewById(R.id.save_page);

		coverFlow  = (CoverFlow) findViewById(com.example.adventurebook.R.id.gallery1);
		coverFlow.setAdapter(new ImageAdapter(this));
		coverImageAdapter =  new ImageAdapter(this);

		//coverImageAdapter.createReflectedImages();

		coverFlow.setAdapter(coverImageAdapter);

		coverFlow.setSpacing(25);
		coverFlow.setSelection(2, true);
		coverFlow.setAnimationDuration(1000);

		
		createOption.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent i = new Intent(EditPageActivity.this, EditOptionActivity.class);
				startActivity(i);
			}
		});

		savePage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				finish();
			}
		});
	}


}