package c301.AdventureBook;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import c301.AdventureBook.Controllers.StoryManager;
import c301.AdventureBook.Models.Page;
import c301.AdventureBook.Models.Story;

import com.example.adventurebook.R;


public class GalleryActivity extends Activity{

	private static final int PHOTO_ACTIVITY_REQUEST = 1001;
	
	private StoryManager sManagerInst;
	private Page currentPage;
	private Story currentStory;
	private GridView gridview;
	private Button mButtonAddPic;
	private Button mButtonReturn;
	private Typeface font;
	private ArrayList<String> imageBytes;
	
	private String show_path;
	private String imageByte;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery);

		sManagerInst = StoryManager.getInstance();
		currentPage = sManagerInst.getPage();
		currentStory = sManagerInst.getCurrentStory();
		imageBytes = currentPage.getImageBytes();

		gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this, imageBytes));
		mButtonAddPic = (Button) findViewById(R.id.add_pic);
		mButtonReturn = (Button) findViewById(R.id.return_from_gallery);
		font = Typeface.createFromAsset(getAssets(), "straightline.ttf");  
		mButtonAddPic.setTypeface(font);  
		mButtonReturn.setTypeface(font);  

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Toast.makeText(GalleryActivity.this, "" + position, Toast.LENGTH_SHORT).show();
			}
		});
		mButtonAddPic.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(GalleryActivity.this,
						TakePhotoActivity.class);
				startActivityForResult(intent, PHOTO_ACTIVITY_REQUEST);
			}
		});
		mButtonReturn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				finish();
			}
		});
	}
	
	@Override
	//test image
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == PHOTO_ACTIVITY_REQUEST && resultCode == RESULT_OK) {
			show_path = data.getStringExtra("path");
			imageByte = data.getStringExtra("imagebyte");
			currentPage.addImageByte(imageByte);
			currentStory.replacePage(currentPage);
			sManagerInst.setCurrentPage(currentPage);
			sManagerInst.saveStory(currentStory, true);
		}
	}

	public void onResume()
	{  // After a pause OR at startup
		super.onResume();
		//Refresh your stuff here
		imageBytes = currentPage.getImageBytes();
		gridview.setAdapter(new ImageAdapter(this, imageBytes));
	}

	public class ImageAdapter extends BaseAdapter {
		private Context mContext;
		private Bitmap[] bitmaps;

		public ImageAdapter(Context c, ArrayList<String> imageBytes) {
			this.mContext = c;
			bitmaps = new Bitmap[imageBytes.size()];

			if (imageBytes.size() == 0) {
				bitmaps = new Bitmap[1];
				Bitmap originalImage = BitmapFactory.decodeResource(mContext.getResources(), 
						R.drawable.default_image);
				bitmaps[0] = originalImage;
			}
			
			for(int i = 0; i < imageBytes.size(); i++){
				byte[] decodedString = Base64.decode(imageBytes.get(i), Base64.DEFAULT);
				Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length); 
				//index = extractBitmap(reflectionGap, index, decodedByte);
				bitmaps[i] = decodedByte;
			}
		}

		public int getCount() {
			return bitmaps.length;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		// create a new ImageView for each item referenced by the Adapter
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) {  // if it's not recycled, initialize some attributes
				imageView = new ImageView(mContext);
				imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(8, 10, 10, 8);
			} else {
				imageView = (ImageView) convertView;
			}

			imageView.setImageBitmap(bitmaps[position]);
			return imageView;
		}

	}

}
