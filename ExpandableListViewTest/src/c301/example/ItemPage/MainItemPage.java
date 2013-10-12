package c301.example.ItemPage;

import java.util.List;

public class MainItemPage {
	private String startPage;
	private List<SubItemPage> Items;
	
	public void setStartPage(String startPage){
		this.startPage = startPage;
	}
	
	public void setItems(List<SubItemPage> items){
		this.Items = items;
	}
	
	public String getStartPage(){
		return this.startPage;
	}
	
	public List<SubItemPage> getItems(){
		return this.Items;
	}
}
