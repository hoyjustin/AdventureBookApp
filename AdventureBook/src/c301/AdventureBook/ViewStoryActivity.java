package c301.AdventureBook;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import c301.AdventureBook.Controllers.LibraryManager;
import c301.AdventureBook.Controllers.StoryManager;
import c301.AdventureBook.Models.Page;
import c301.AdventureBook.Models.Story;

import com.example.adventurebook.R;

/**
 * The view story activity allows the user to view a story upon selection from
 * the library. When the user chooses to begin the story, the first page of 
 * the story is shown.
 * 
 * @author Terence Yin Kiu Leung - minor editor
 *
 */
public class ViewStoryActivity extends Activity {
	private static final int FIRST_PAGE_INDEX = 0;
	private static final int LOCALLIBRARY_ID = 0;
	
	LibraryManager lManager; // Controller for the Library
	StoryManager sManager; // Controller for a story
	Story currentStory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_story);
		
		populateData();
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_story, menu);
        menu.add(0, LOCALLIBRARY_ID, 0, R.string.menu_offline_library);
        return true;
    }
    
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
            case LOCALLIBRARY_ID:
                returnHome();
                return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }
    
    /**
     * Return to the library when the user is finished with viewing story.
     */
    private void returnHome() {
        Intent i = new Intent(this, OfflineLibraryActivity.class);
        startActivity(i);
        finish();
    }
	
	/**
	 * Populates the page with the story data, including the title, author,
	 * description, and image.
	 */
	public void populateData(){

		sManager = StoryManager.getInstance();
		sManager.initContext(this);
		
		currentStory = sManager.getStory();
		
		ImageView imageView = (ImageView)findViewById(R.id.storyThumnail);

		if (currentStory.getImageByte() == null) {
			imageView.setImageResource(R.drawable.default_image);
		} else {
			byte[] decodedString = Base64.decode(currentStory.getImageByte(), Base64.DEFAULT);
			Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length); 
			imageView.setImageBitmap(decodedByte);
		}
		TextView titleText = (TextView) findViewById(R.id.storyTitle);
		titleText.setText(currentStory.getTitle());

		TextView authorText = (TextView)findViewById(R.id.authorTV);
		authorText.setText(currentStory.getAuthor());

		//TextView dateText = (TextView)findViewById(R.id.dateCreatedTV);
		//dateText.setText(currentStory.getDate());
		
		TextView storyDescription = (TextView) findViewById(R.id.storyDescription);
		storyDescription.setText(currentStory.getDescription());
		storyDescription.setKeyListener(null);

	}
	
	/**
	 * Start the view page activity when the "Begin Story" button is clicked.
	 * The first page is displayed when the user begins a new story.
	 * This method is called via android:onClick in the view_story XML.
	 * 
	 */
	public void launchViewPageActivity(View v){
		List<Page> pages = currentStory.getPages();
		// Do nothing if story doesn't contain any pages
		if (pages.size() > 0) {
			Page firstPage = pages.get(FIRST_PAGE_INDEX);
			sManager.setCurrentPage(firstPage);
			Intent i = new Intent(this, ViewPageActivity.class);
			startActivity(i);
		} else {
			Toast.makeText(this, "There are no pages to view!", Toast.LENGTH_LONG).show();
		}
	}

}
