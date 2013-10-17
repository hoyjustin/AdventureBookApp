package com.example.adventurebook;

import java.util.Collection;


public class OnlineLibrary {

	/**
	 * @uml.property   name="eSClient"
	 * @uml.associationEnd   inverse="onlineLibrary:com.example.adventurebook.SController"
	 */
	private SController esClient;

	/** 
	 * Getter of the property <tt>eSClient</tt>
	 * @return  Returns the esClient.
	 * @uml.property  name="eSClient"
	 */
	public SController getESClient() {
		return esClient;
	}

	/** 
	 * Setter of the property <tt>eSClient</tt>
	 * @param eSClient  The esClient to set.
	 * @uml.property  name="eSClient"
	 */
	public void setESClient(SController esClient) {
		this.esClient = esClient;
	}

	/**
	 * @uml.property   name="story"
	 * @uml.associationEnd   multiplicity="(0 -1)" aggregation="shared" inverse="onlineLibrary:com.example.adventurebook.Story"
	 */
	private Collection<Story> story;

	/** 
	 * Getter of the property <tt>story</tt>
	 * @return  Returns the story.
	 * @uml.property  name="story"
	 */
	public Collection<Story> getStory() {
		return story;
	}

	/** 
	 * Setter of the property <tt>story</tt>
	 * @param story  The story to set.
	 * @uml.property  name="story"
	 */
	public void setStory(Collection<Story> story) {
		this.story = story;
	}

}
