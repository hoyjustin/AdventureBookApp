/*
 * Copyright (C) <2013>  <Chenlei Zhang, Minhal Syed>
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

package c301.AdventureBook.ElasticSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;
import c301.AdventureBook.Models.Option;
import c301.AdventureBook.Models.Page;
import c301.AdventureBook.Models.Story;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * This is the Elastic Search Client Class. This class is responsible for
 * communicating with WebServer. This class can be used to insert, delete,
 * modify, and search stories on the WebServer.
 * 
 * This code has been taken and modified from:
 * https://github.com/rayzhangcl/ESDemo
 * 
 * @author Chenlei Zhang - Original Owner
 * @author Minhal Syed - Modified Original Code
 */
public class ESClient {

	// Http Connector
	private HttpClient httpclient = new DefaultHttpClient();

	// JSON Utilities
	private Gson gson = new Gson();

	public static final String WEBSERVICE_URI = "http://cmput301.softwareprocess.es:8080/cmput301f13t11/";
	public static final String STORIES_FOLDER = "stories/";
	public static final String SEARCH_PRETTY = "_search?pretty=1&q=";
	public static final int MAX_STORIES = 20;
	/**
	 * This function stores a story on the WebServer based on the story's ID. If
	 * a new story with the same Id is inserted, the previous one is
	 * overwritten.
	 * 
	 * Consumes the POST/Insert operation of the service
	 * 
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public void insertStory(Story story) throws IOException {

		HttpPost httpPost = new HttpPost(WEBSERVICE_URI + STORIES_FOLDER
				+ story.getStoryId());

		StringEntity stringentity = null;

		try {
			stringentity = new StringEntity(gson.toJson(story));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpPost.setHeader("Accept", "application/json");

		httpPost.setEntity(stringentity);

		HttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String status = response.getStatusLine().toString();
		System.out.println(status);
		HttpEntity entity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				entity.getContent()));
		String output;
		System.err.println("Output from Server -> ");
		while ((output = br.readLine()) != null) {
			System.err.println(output);
		}

	}

	/**
	 * Consumes the Get operation of the service
	 * 
	 * Given a StoryId, It returns a StoryObject from The Server.
	 * 
	 * @param StoryID
	 * @return Story -- Returns Null if no Story Found
	 */
	public Story getStory(String StoryID) {

		Story story = null;
		try {
			HttpGet getRequest = new HttpGet(WEBSERVICE_URI + STORIES_FOLDER
					+ StoryID);

			getRequest.addHeader("Accept", "application/json");

			HttpResponse response = httpclient.execute(getRequest);

			String status = response.getStatusLine().toString();
			System.out.println(status);

			String json = getEntityContent(response);

			// We have to tell GSON what type we expect
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Story>>() {
			}.getType();
			// Now we expect to get a Story response
			ElasticSearchResponse<Story> esResponse = gson.fromJson(json,
					elasticSearchResponseType);

			// We get the Story from it
			story = esResponse.getSource();

			// getRequest.releaseConnection();

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return story;
	}

	/**
	 * This function is a private function and should not be called by anyone.
	 * This function generates a URI that is needed in order to query some story
	 * information from the WebServer.
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String getQueryHttpURI(String str)
			throws UnsupportedEncodingException {
		String queryHttpURI = null;
		queryHttpURI = WEBSERVICE_URI + STORIES_FOLDER + SEARCH_PRETTY
				+ java.net.URLEncoder.encode(str, "UTF-8")+"&size=" + MAX_STORIES;
		return queryHttpURI;
	}

	/**
	 * This function returns all the stories on the server, as an ArrayList of
	 * Stories.
	 * 
	 * @return allStories
	 */
	public ArrayList<Story> getAllStories() {
		ArrayList<Story> allStories = null;
		allStories = this.searchStories("*");
		Log.d("DEBUG", "" + allStories.size());
		return allStories;
	}

	/**
	 * search by keywords
	 */
	private ArrayList<Story> searchStories(String Keyword) {
		try {
			ArrayList<Story> filteredStories = new ArrayList<Story>();

			HttpGet searchRequest = new HttpGet(this.getQueryHttpURI(Keyword));

			searchRequest.setHeader("Accept", "application/json");
			HttpResponse response = httpclient.execute(searchRequest);
			String status = response.getStatusLine().toString();
			System.out.println(status);

			String json = getEntityContent(response);

			Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Story>>() {
			}.getType();

			ElasticSearchSearchResponse<Story> esResponse = gson.fromJson(json,
					elasticSearchSearchResponseType);
			System.err.println(esResponse);
			for (ElasticSearchResponse<Story> r : esResponse.getHits()) {
				Story story = r.getSource();
				filteredStories.add(story);
			}
			// searchRequest.releaseConnection();

			return filteredStories;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * advanced search (logical operators)
	 */
	/*
	 * public void searchsearchRecipes(String str) throws
	 * ClientProtocolException, IOException { HttpPost searchRequest = new
	 * HttpPost(
	 * "http://cmput301.softwareprocess.es:8080/cmput301f13t11/_search?pretty=1"
	 * ); String query =
	 * "{\"query\" : {\"query_string\" : {\"default_field\" : \"ingredients\",\"query\" : \""
	 * + str + "\"}}}"; StringEntity stringentity = new StringEntity(query);
	 * 
	 * searchRequest.setHeader("Accept", "application/json");
	 * searchRequest.setEntity(stringentity);
	 * 
	 * HttpResponse response = httpclient.execute(searchRequest); String status
	 * = response.getStatusLine().toString(); System.out.println(status);
	 * 
	 * String json = getEntityContent(response);
	 * 
	 * Type elasticSearchSearchResponseType = new
	 * TypeToken<ElasticSearchSearchResponse<Recipe>>() { }.getType();
	 * ElasticSearchSearchResponse<Recipe> esResponse = gson.fromJson(json,
	 * elasticSearchSearchResponseType); System.err.println(esResponse); for
	 * (ElasticSearchResponse<Recipe> r : esResponse.getHits()) { Recipe recipe
	 * = r.getSource(); System.err.println(recipe); }
	 * searchRequest.releaseConnection(); }
	 */

	/**
	 * update a field in a recipe
	 */
	/*
	 * public void updateRecipes(String str) throws ClientProtocolException,
	 * IOException { HttpPost updateRequest = new HttpPost(
	 * "http://cmput301.softwareprocess.es:8080/testing/lab02/1/_update");
	 * String query = "{\"script\" : \"ctx._source." + str + "}"; StringEntity
	 * stringentity = new StringEntity(query);
	 * 
	 * updateRequest.setHeader("Accept", "application/json");
	 * updateRequest.setEntity(stringentity);
	 * 
	 * HttpResponse response = httpclient.execute(updateRequest); String status
	 * = response.getStatusLine().toString(); System.out.println(status);
	 * 
	 * String json = getEntityContent(response);
	 * //updateRequest.releaseConnection(); }
	 */
	public void deleteURI(String URI) {
		try {

			HttpDelete httpDelete = new HttpDelete(URI);
			httpDelete.addHeader("Accept", "application/json");

			HttpResponse response = httpclient.execute(httpDelete);

			String status = response.getStatusLine().toString();
			System.out.println(status);

			HttpEntity entity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					entity.getContent()));
			String output;
			System.err.println("Output from Server -> ");
			while ((output = br.readLine()) != null) {
				System.err.println(output);
			}

			// EntityUtils.consume(entity); httpDelete.releaseConnection();

		} catch (Exception e) {

		}
	}

	/**
	 * Delete a Story Object from WebServer. A story is deleted based on its
	 * storyId.
	 * 
	 */
	public void deleteStory(Story story) {
		if (this.storyExists(story)) {
			String URI = WEBSERVICE_URI + STORIES_FOLDER + story.getStoryId();
			this.deleteURI(URI);
		}
	}

	/**
	 * get the http response and return json string
	 */
	String getEntityContent(HttpResponse response) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(response.getEntity().getContent())));
		String output;
		System.err.println("Output from Server -> ");
		String json = "";
		while ((output = br.readLine()) != null) {
			System.err.println(output);
			json += output;
		}
		System.err.println("JSON:" + json);
		return json;
	}

	/**
	 * Given an input Story object, this function checks whether the input story
	 * is present on the WebServer or not.
	 * 
	 * @param story
	 * @return boolean storyExists
	 */
	private boolean storyExists(Story story) {
		boolean storyExists = false;

		try {
			HttpGet getRequest = new HttpGet(WEBSERVICE_URI + STORIES_FOLDER
					+ story.getStoryId());
			getRequest.addHeader("Accept", "application/json");
			HttpResponse response = httpclient.execute(getRequest);
			String json = getEntityContent(response);

			// We have to tell GSON what type we expect
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Story>>() {
			}.getType();
			// Now we expect to get a Story response
			ElasticSearchResponse<Story> esResponse = gson.fromJson(json,
					elasticSearchResponseType);
			// We get the Story from it
			storyExists = esResponse.getExtists();
			// getRequest.releaseConnection();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return storyExists;
	}

	/*
	 * // Main Test public static void main(String[] args) {
	 * 
	 * ESClient client = new ESClient();
	 * 
	 * Story aStory = testCases.initializeStory(); Story bStory =
	 * testCases.initializeStory2();
	 * 
	 * client.insertStory(aStory); //client.insertStory(bStory);
	 * 
	 * // search by keywords
	 * 
	 * ArrayList<Story> searchedStories = client.getAllStories();
	 * 
	 * for (Story cStory : searchedStories) {
	 * System.out.println(cStory.toString()); }
	 * 
	 * }
	 */
}
