package c301.AdventureBook;

import android.app.Application;

public class AdventureBook extends Application{
/**
 * This class is basically check is the application online or off line by
 * using a global variable.
 * 
 * variable used IsOnline
 */
	private boolean IsOnline = false;
	
	public void setIsOnlineParameter(boolean bool){
		this.IsOnline = bool;
	}
	
	public boolean getIsOnlineParameter(){
		return this.IsOnline;
	/**
	 *  @return IsOnline
	 */
	}
	
}
