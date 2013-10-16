package com.example.adventurebook;

import java.util.Collection;


public class Sound {

	/**
	 * @uml.property  name="storyFragment"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="sound:com.example.adventurebook.StoryFragment"
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

}
