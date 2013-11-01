//Creator: Justin Hoy

package c301.AdventureBook.Models;

import java.io.Serializable;

public class Option implements Serializable{
	private String goToPage;		
	
	public void setGoToPage(String goToPage){
		this.goToPage = goToPage;
	}
	
	public String getGoToPage(){
		return this.goToPage;
	}
}
