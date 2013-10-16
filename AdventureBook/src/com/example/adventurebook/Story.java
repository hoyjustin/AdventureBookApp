package com.example.adventurebook;

import java.util.Collection;


public class Story {

	/** 
	 * @uml.property name="onlineLibrary"
	 * @uml.associationEnd inverse="story:com.example.adventurebook.OnlineLibrary"
	 */
	private OnlineLibrary onlineLibrary;

	/** 
	 * Getter of the property <tt>onlineLibrary</tt>
	 * @return  Returns the onlineLibrary.
	 * @uml.property  name="onlineLibrary"
	 */
	public OnlineLibrary getOnlineLibrary() {
		return onlineLibrary;
	}

	/** 
	 * Setter of the property <tt>onlineLibrary</tt>
	 * @param onlineLibrary  The onlineLibrary to set.
	 * @uml.property  name="onlineLibrary"
	 */
	public void setOnlineLibrary(OnlineLibrary onlineLibrary) {
		this.onlineLibrary = onlineLibrary;
	}

	/**
	 * @uml.property  name="storyFragment"
	 * @uml.associationEnd  multiplicity="(0 -1)" aggregation="shared" inverse="story:com.example.adventurebook.StoryFragment"
	 */
	private Collection<StoryFragment> storyFragment;

	/**
	 * Getter of the property <tt>storyFragment</tt>
	 * @return  Returns the storyFragment.
	 * @uml.property  name="storyFragment"
	 */
	public Collection<StoryFragment> getStoryFragment() {
		return storyFragment;
	}

	/**
	 * Setter of the property <tt>storyFragment</tt>
	 * @param storyFragment  The storyFragment to set.
	 * @uml.property  name="storyFragment"
	 */
	public void setStoryFragment(Collection<StoryFragment> storyFragment) {
		this.storyFragment = storyFragment;
	}

	/** 
	 * @uml.property name="offlineLibrary"
	 * @uml.associationEnd inverse="story:com.example.adventurebook.OfflineLibrary"
	 */
	private OfflineLibrary offlineLibrary;

	/** 
	 * Getter of the property <tt>offlineLibrary</tt>
	 * @return  Returns the offlineLibrary.
	 * @uml.property  name="offlineLibrary"
	 */
	public OfflineLibrary getOfflineLibrary() {
		return offlineLibrary;
	}

	/** 
	 * Setter of the property <tt>offlineLibrary</tt>
	 * @param offlineLibrary  The offlineLibrary to set.
	 * @uml.property  name="offlineLibrary"
	 */
	public void setOfflineLibrary(OfflineLibrary offlineLibrary) {
		this.offlineLibrary = offlineLibrary;
	}

	/**
	 * @uml.property  name="storyModel"
	 * @uml.associationEnd  inverse="story:com.example.adventurebook.StoryModel"
	 */
	private StoryModel storyModel;

	/**
	 * Getter of the property <tt>storyModel</tt>
	 * @return  Returns the storyModel.
	 * @uml.property  name="storyModel"
	 */
	public StoryModel getStoryModel() {
		return storyModel;
	}

	/**
	 * Setter of the property <tt>storyModel</tt>
	 * @param storyModel  The storyModel to set.
	 * @uml.property  name="storyModel"
	 */
	public void setStoryModel(StoryModel storyModel) {
		this.storyModel = storyModel;
	}

}
