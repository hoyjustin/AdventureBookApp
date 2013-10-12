package c301.example.expandablelistviewtest;

import java.util.List;

import com.example.expandablelistviewtest.R;

import c301.example.ItemPage.MainItemPage;
import c301.example.ItemPage.SubItemPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context mContext;
	private ExpandableListView mListView;
	private List<MainItemPage> mModel;
	
	public ExpandableListAdapter(Context pContext, ExpandableListView pListView, List<MainItemPage> pModel){
		this.mContext = pContext;
		this.mListView = pListView;
		this.mModel = pModel;
	}
	
	
	public void addItem(SubItemPage item, MainItemPage groupData){
		if(!mModel.contains(groupData)){
			mModel.add(groupData);
		}
		
		int ind = mModel.indexOf(groupData);
		List<SubItemPage> lstItems =  mModel.get(ind).getItems();
		lstItems.add(item);
		mModel.get(ind).setItems(lstItems);
	}
	
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		List<SubItemPage> item = mModel.get(groupPosition).getItems();
		return item.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view,
			ViewGroup parent) {
		SubItemPage item = (SubItemPage)getChild(groupPosition, childPosition);
		if(view == null){
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.list_item, null);
		}
		
		TextView txtEndPage = (TextView)view.findViewById(R.id.txtEndPage);
		txtEndPage.setText(item.getEndPage());
		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mModel.get(groupPosition).getItems().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return mModel.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return mModel.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isLastChild, View view, ViewGroup parent) {
		MainItemPage model =  (MainItemPage)getGroup(groupPosition);
		if(view == null){
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.group_item, null);
		}
		
		TextView txtStartPage = (TextView)view.findViewById(R.id.txtFirstPage);
		txtStartPage.setText(model.getStartPage());
		return view;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

}
