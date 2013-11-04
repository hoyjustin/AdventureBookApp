//Creator: Justin Hoy

package c301.AdventureBook.Models;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{

	private String title;
	private List<Option> options;
	private String textContent;
	
	public Page(String title, String textContent){
		this.title = title;
		this.textContent = textContent;
	}
	

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public void setTitle(String startPage){
		this.title = startPage;
	}
	
	public void setOptions(List<Option> options){
		this.options = options;
	}
	
	public String getTextContent() {
		return textContent;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public List<Option> getOptions(){
		return this.options;
	}
}
