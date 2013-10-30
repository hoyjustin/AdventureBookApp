//Creator: Justin Hoy

package c301.AdventureBook.Models;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{

	private String title;
	private List<Option> options;
	
	public void setTitle(String startPage){
		this.title = startPage;
	}
	
	public void setOptions(List<Option> options){
		this.options = options;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public List<Option> getOptions(){
		return this.options;
	}
}
