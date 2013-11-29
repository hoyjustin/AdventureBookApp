package c301.AdventureBook;

import android.app.Application;

public class AdventureBook extends Application{

	private boolean IsOnline = false;
	
	public void setIsOnlineParameter(boolean bool){
		this.IsOnline = bool;
	}
	
	public boolean getIsOnlineParameter(){
		return this.IsOnline;
	}
	
}
