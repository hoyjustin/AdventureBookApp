package com.example.adventurebook;


public class Controller {

	/**
	 * @uml.property   name="onlineLibrary"
	 * @uml.associationEnd   inverse="eSClient:com.example.adventurebook.OnlineLibrary"
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
	 * @uml.property   name="remoteDatabase"
	 * @uml.associationEnd   inverse="eSClient:com.example.adventurebook.RemoteStories"
	 */
	private RemoteStories remoteDatabase;

	/**
	 * Getter of the property <tt>remoteDatabase</tt>
	 * @return  Returns the remoteDatabase.
	 * @uml.property  name="remoteDatabase"
	 */
	public RemoteStories getRemoteDatabase() {
		return remoteDatabase;
	}

	/**
	 * Setter of the property <tt>remoteDatabase</tt>
	 * @param remoteDatabase  The remoteDatabase to set.
	 * @uml.property  name="remoteDatabase"
	 */
	public void setRemoteDatabase(RemoteStories remoteDatabase) {
		this.remoteDatabase = remoteDatabase;
	}

	/**
	 * @uml.property  name="offlineLibrary"
	 * @uml.associationEnd  inverse="controller:com.example.adventurebook.OfflineLibrary"
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
	 * @uml.associationEnd  inverse="controller:com.example.adventurebook.StoryModel"
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

	/**
	 * @uml.property  name="offlineLibraryActivity"
	 * @uml.associationEnd  inverse="controller:com.example.adventurebook.OfflineLibraryActivity"
	 */
	private OfflineLibraryActivity offlineLibraryActivity;

	/**
	 * Getter of the property <tt>offlineLibraryActivity</tt>
	 * @return  Returns the offlineLibraryActivity.
	 * @uml.property  name="offlineLibraryActivity"
	 */
	public OfflineLibraryActivity getOfflineLibraryActivity() {
		return offlineLibraryActivity;
	}

	/**
	 * Setter of the property <tt>offlineLibraryActivity</tt>
	 * @param offlineLibraryActivity  The offlineLibraryActivity to set.
	 * @uml.property  name="offlineLibraryActivity"
	 */
	public void setOfflineLibraryActivity(
			OfflineLibraryActivity offlineLibraryActivity) {
		this.offlineLibraryActivity = offlineLibraryActivity;
	}

}
