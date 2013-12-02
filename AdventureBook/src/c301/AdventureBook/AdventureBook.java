package c301.AdventureBook;

import android.app.Application;

/**
 * Checks whether or not the applicatin is online or offline.
 *
 */
public class AdventureBook extends Application{

	private boolean IsOnline = false;
	
	/**
	 * Sets the flag according to the given boolean value.
	 * 
	 * @param bool
	 */
	public void setIsOnlineParameter(boolean bool){
		this.IsOnline = bool;
	}
	
	/**
	 * Get the flag state.
	 * 
	 * @return isOnline a boolean value
	 */
	public boolean getIsOnlineParameter(){
		return this.IsOnline;
	}
	
}
