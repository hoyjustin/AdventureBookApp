package c301.example.expandablelistviewtest;

import java.util.ArrayList;
import java.util.List;

import com.example.expandablelistviewtest.R;

import c301.example.ItemPage.MainItemPage;
import c301.example.ItemPage.SubItemPage;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {

	private ExpandableListAdapter adpt;
	private ExpandableListView lstView;
	private List<MainItemPage> lstModel;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstView = (ExpandableListView)findViewById(R.id.expList);
        lstModel = new ArrayList<MainItemPage>();
        
        //load model here
        loadSampleData();        
        adpt = new ExpandableListAdapter(this, lstView, lstModel);
        lstView.setAdapter(adpt);
    }
    
    private void loadSampleData(){
    	  	
    	List<SubItemPage> lstdm = new ArrayList<SubItemPage>();
    	SubItemPage dm1 = new SubItemPage();

    	MainItemPage model = new MainItemPage();
    	model.setStartPage("PAGE 1");
    	model.setItems(lstdm);
    	lstModel.add(model);
    	
    	//Page 1
    	dm1 = new SubItemPage();
    	dm1.setEndPage("Page 2");
    	lstdm.add(dm1);
    	
    	dm1 = new SubItemPage();
    	dm1.setEndPage("Page 3");
    	lstdm.add(dm1);
    	
    	//Page 2  	
    	model = new MainItemPage();
    	model.setStartPage("PAGE 2");
    	
    	lstdm = new ArrayList<SubItemPage>();
    	
    	dm1 = new SubItemPage();
    	dm1.setEndPage("Page 3");
    	lstdm.add(dm1);
    	
    	model.setItems(lstdm);
    	lstModel.add(model);
    	
    	//Page 3
    	model = new MainItemPage();
    	model.setStartPage("PAGE 3");
    	
    	lstdm = new ArrayList<SubItemPage>();
    	
    	dm1 = new SubItemPage();
    	dm1.setEndPage("END");
    	lstdm.add(dm1);
    	
    	model.setItems(lstdm);
    	lstModel.add(model);
    	    	    	    	  
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
