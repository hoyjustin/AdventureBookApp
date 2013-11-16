package c301.AdventureBook;
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


//being test now.
//function as upload image from path to service
//download part haven't finish

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

public class HttpUploader extends AsyncTask<String, Void, String> {
	@Override
	protected String doInBackground(String... path) {
		// TODO Auto-generated method stub
		String outPut = null;
		for (String sdPath : path) {
			Bitmap bitmapOrg = BitmapFactory.decodeFile(sdPath);
			ByteArrayOutputStream imageByte = new ByteArrayOutputStream();

			double width = bitmapOrg.getWidth();
			double height = bitmapOrg.getHeight();
			double ratio = 400 / width;
			int newheight = (int) (ratio * height);
			bitmapOrg = Bitmap.createScaledBitmap(bitmapOrg, 400, newheight,
					true);
			bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 95, imageByte);
			byte[] bytefile = imageByte.toByteArray();
			String bytefile64 = Base64.encodeToString(bytefile, Base64.DEFAULT);
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("image", bytefile64));
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(
						"http://cmput301.softwareprocess.es:8080/cmput301f13t11/");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				outPut = EntityUtils.toString(entity);
				Log.i("GET RESPONSE:", outPut);
				Log.e("log_tag ******", "able to connect to service");
				bitmapOrg.recycle();

			} catch (Exception e) {
				Log.e("log_tage ******", "Error in connection:" + e.toString());
			}
		}
		return outPut;
	}
}
