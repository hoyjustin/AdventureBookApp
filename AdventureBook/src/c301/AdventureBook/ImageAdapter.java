package c301.AdventureBook;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import c301.AdventureBook.Models.Page;

import com.example.adventurebook.R;

/**
 * This is the ImageAdapter used for displaying media in the cover flow.
 * 
 * @author Justin Hoy
 *
 */
public class ImageAdapter extends BaseAdapter {
	int mGalleryItemBackground;
	private Context mContext;
	private Page mPage;

	private Bitmap[] bitmaps;

	public ImageAdapter(Context c, Page page) {
		mPage = page;
		mContext = c;
		//change array size when multiple images appear in a page
		//mImages = new ImageView[mImageIds.length];
		ArrayList<String> imageBytes = mPage.getImageBytes();
		if (imageBytes.size() == 0) {
				bitmaps = new Bitmap[1];
				Bitmap originalImage = BitmapFactory.decodeResource(mContext.getResources(), 
						R.drawable.default_image);
				bitmaps[0] = originalImage;
		}
		else{
			bitmaps = new Bitmap[imageBytes.size()];
			for(int i = 0; i < imageBytes.size(); i++){
			byte[] decodedString = Base64.decode(imageBytes.get(i), Base64.DEFAULT);
			Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length); 
			//index = extractBitmap(reflectionGap, index, decodedByte);
			bitmaps[i] = decodedByte;
			}

		}
	}
	/**
	 * get the length of the bitmap
	 */
	public int getCount() {
		return bitmaps.length;
	}
	/**
	 * get the item's position
	 */
	public Object getItem(int position) {
		return position;
	}
	/** get the item Id
	 * 
	 */
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("deprecation")
	public View getView(int position, View convertView, ViewGroup parent) {
	      //Use this code if you want to load from resources
        ImageView i = new ImageView(mContext);
        i.setImageBitmap(bitmaps[position]);
        i.setLayoutParams(new CoverFlow.LayoutParams(400, 400));
        i.setScaleType(ImageView.ScaleType.CENTER_INSIDE); 
        
        //Make sure we set anti-aliasing otherwise we get jaggies
        BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
        drawable.setAntiAlias(true);
        return i;
     
        //return mImages[position];
	}
	/** Returns the size (0.0f to 1.0f) of the views 
	 * depending on the 'offset' to the center. */ 
	public float getScale(boolean focused, int offset) { 
		/* Formula: 1 / (2 ^ offset) */ 
		return Math.max(0, 1.0f / (float)Math.pow(2, Math.abs(offset))); 
	} 

}