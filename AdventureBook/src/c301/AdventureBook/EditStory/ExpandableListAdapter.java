package c301.AdventureBook.EditStory;

import java.util.List;

import com.example.adventurebook.R;

import c301.AdventureBook.Models.Option;
import c301.AdventureBook.Models.Page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context mContext;
	private ExpandableListView mListView;
	private List<Page> mModel;
	
	public ExpandableListAdapter(Context pContext, ExpandableListView pListView, List<Page> pModel){
		this.mContext = pContext;
		this.mListView = pListView;
		this.mModel = pModel;
	}
	
	
	public void addItem(Option item, Page groupData){
		if(!mModel.contains(groupData)){
			mModel.add(groupData);
		}
		
		int ind = mModel.indexOf(groupData);
		List<Option> lstItems =  mModel.get(ind).getOptions();
		lstItems.add(item);
		mModel.get(ind).setOptions(lstItems);
	}
	
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		List<Option> item = mModel.get(groupPosition).getOptions();
		return item.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view,
			ViewGroup parent) {
		Option item = (Option)getChild(groupPosition, childPosition);
		if(view == null){
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.list_item, null);
		}
		
		TextView txtEndPage = (TextView)view.findViewById(R.id.txtEndPage);
		txtEndPage.setText(item.getGoToPage());
		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mModel.get(groupPosition).getOptions().size();
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
		Page model =  (Page)getGroup(groupPosition);
		if(view == null){
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.group_item, null);
		}
		
		TextView txtStartPage = (TextView)view.findViewById(com.example.adventurebook.R.id.txtFirstPage);
		txtStartPage.setText(model.getTitle());
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
