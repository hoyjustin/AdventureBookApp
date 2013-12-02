/*
 * Copyright (C) <2013>  <Justin Hoy>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package c301.AdventureBook;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import c301.AdventureBook.Models.Option;
import c301.AdventureBook.Models.Page;

import com.example.adventurebook.R;


/**
 * The adapter for displaying story fragments (pages) and the directly pages its linked to through a tree view.
 * 
 * @author Justin Hoy
 *
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context mContext;
	private List<Page> mModel;
	
	public ExpandableListAdapter(Context pContext, ExpandableListView pListView, List<Page> pModel){
		this.mContext = pContext;
		this.mModel = pModel;
	}
	
	
	/**
	 * Add an goto page to the mainitem within the tree view
	 * 
	 * @param item The options for a page
	 * @param groupData The page
	 *
	 */
	public void addItem(Option item, Page groupData){
		if(!mModel.contains(groupData)){
			mModel.add(groupData);
		}
		
		int ind = mModel.indexOf(groupData);
		List<Option> lstItems =  mModel.get(ind).getOptions();
		lstItems.add(item);
		mModel.get(ind).setOptions(lstItems);
	}
	
	/**
	 * Gets the data for a child item (goto page) 
	 * 
	 * @param groupPosition The parent's position
	 * @param childPosition The goto page's position
	 * @return the data of the child
	 */
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		List<Option> item = mModel.get(groupPosition).getOptions();
		return item.get(childPosition);
	}

	/**
	 * Gets the ID for the given gotopage within the given page.
	 * 
	 * @param groupPosition the position of the group that contains the child
	 * @param childPosition the position of the child within the group for which the ID is wanted
	 * @return the ID associated with the child
	 * 
	 */
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	/**
	 * Gets a View that displays the data for the given gotopage within a given page.
	 * 
	 * @param groupPosition The position of the group that contains the child
	 * @param childPosition	 The position of the child (for which the View is returned) within the group
	 * @param isLastChild Whether the child is the last child within the group
	 * @param view	The old view to reuse, if possible
	 * @return The View corresponding to the gotopage at the specified position
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view,
			ViewGroup parent) {
		Option item = (Option)getChild(groupPosition, childPosition);
		if(view == null){
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.list_item, null);
		}
		
		TextView txtEndPage = (TextView)view.findViewById(R.id.txtEndPage);
		
		//Find All the children of the current Page.
		
		for (Page p : this.mModel){
			if (p.getPageId() == item.getGoToPage()){
				txtEndPage.setText(p.getTitle());
			}
		}
		return view;
	}

	/**
	 * Gets the number of children in a specified group.
	 * 
	 * @param groupPosition	the position of the group for which the children count should be returned
	 * @return the children count in the specified group
	 */
	@Override
	public int getChildrenCount(int groupPosition) {
		return mModel.get(groupPosition).getOptions().size();
	}

	/**
	 * Gets the data associated with the given group.
	 * 
	 * @param groupPosition	the position of the group
	 * @return the data gotopage for the specified group
	 */
	@Override
	public Object getGroup(int groupPosition) {
		return mModel.get(groupPosition);
	}

	/**
	 * Gets the number of groups.
	 * 	 
	 * @return the number of groups
	 */
	@Override
	public int getGroupCount() {
		return mModel.size();
	}

	/**
	 * Gets the number of groups.
	 * 	 
	 * @return the number of groups
	 */
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	/**
	 * Gets the number of groups.
	 * 	 
	 * @return the number of groups
	 */
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
	
	/**
	 * Gets the number of groups.
	 * 	 
	 * @return the number of groups
	 */
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * Gets the number of groups.
	 * 	 
	 * @return the number of groups
	 */
	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

}
