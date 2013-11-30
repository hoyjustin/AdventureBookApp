/*
 * Copyright (C) <2013>  <Minhal Syed>
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

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import c301.AdventureBook.Models.Story;

import com.example.adventurebook.R;

/**
 * This is the CustomStoryAdapter Class. This custom adapter is used to populate
 * the ListView of the Offline and of the Online Library.
 * 
 * @author Minhal Syed
 * 
 */

public class CustomStoryAdapter extends ArrayAdapter<Story> {
	Context context;
	int layout_row_id;
	Typeface font;
	Typeface font2;

	ArrayList<Story> storedlibrary; // This is storedLibrary that is
									// initially passed to this class.
									// This library never changes. Unless
									// An element is deleted.

	ArrayList<Story> library; // This is the FilteredLibrary that
								// is displayed on the ListView.
								// This library changes if stories
								// are deleted or if someone filters
								// the result.

	public CustomStoryAdapter(Context context, int layout_row_id,
			ArrayList<Story> library) {

		super(context, layout_row_id, library);

		this.context = context;
		this.layout_row_id = layout_row_id;
		this.library = library;
		this.storedlibrary = new ArrayList<Story>(library);
		font = Typeface
				.createFromAsset(context.getAssets(), "fonts/straightline.ttf");
	}

	@Override
	public int getCount() {
		return library.size();
	}

	@Override
	public Story getItem(int position) {
		return library.get(position);
	}

	@Override
	public long getItemId(int position) {
		return library.indexOf(getItem(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Tutorial used from: https://www.youtube.com/watch?v=WRANgDgM2Zg

		// Make sure we have a view to work with (may have been given null)
		View itemView = convertView;
		if (itemView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			itemView = inflater.inflate(layout_row_id, parent, false);
		}

		Story currentStory = library.get(position);

		// Fill the view
		ImageView imageView = (ImageView) itemView
				.findViewById(R.id.storyThumbnailView);

		if (currentStory.getImageByte() == null) {
			imageView.setImageResource(R.drawable.default_image);
		} else{
			
			byte[] decodedString = Base64.decode(currentStory.getImageByte(),
					Base64.DEFAULT);
			Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString,
					0, decodedString.length);
			imageView.setImageBitmap(decodedByte);
		} 
		
		TextView titleText = (TextView) itemView.findViewById(R.id.titleTV);
		titleText.setText(currentStory.getTitle());
		titleText.setTypeface(font);

		TextView authorText = (TextView) itemView.findViewById(R.id.authorTV);
		authorText.setText(currentStory.getAuthor());
		authorText.setTypeface(font);

		TextView dateText = (TextView) itemView
				.findViewById(R.id.dateCreatedTV);
		dateText.setText(currentStory.getDate());
		dateText.setTypeface(font);

		return itemView;
	}

	/**
	 * This function changes the list to be displayed based on the search
	 * criteria.
	 * 
	 */
	// Tutorial used from:
	// http://stackoverflow.com/questions/17962675/android-custom-listview-adapter-filter-not-going-back-to-original-list

	@Override
	public Filter getFilter() {
		return new Filter() {
			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				library = (ArrayList<Story>) results.values;
				CustomStoryAdapter.this.notifyDataSetChanged();
			}

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {

				FilterResults results = new FilterResults();

				if (constraint == null || constraint.length() == 0) {
					// No filter implemented we return all the list
					results.values = storedlibrary;
					results.count = storedlibrary.size();

				} else {
					// We perform filtering operation
					// Filtering is done based on story title or story author.
					// We can extend this search criteria more if we want.

					ArrayList<Story> filteredStories = new ArrayList<Story>();

					String query = constraint.toString().toLowerCase();
					library = storedlibrary;
					for (Story story : library) {

						String story_title = story.getTitle().toLowerCase();
						String story_author = story.getAuthor().toLowerCase();
						String story_description = story.getDescription()
								.toLowerCase();
						
						if ((story_title.contains(query))
								|| (story_author.contains(query))
								|| (story_description.contains(query))) {

							filteredStories.add(story);
						}
					}
					results.values = filteredStories;
					results.count = filteredStories.size();
				}
				return results;
			}
		};
	}
}
