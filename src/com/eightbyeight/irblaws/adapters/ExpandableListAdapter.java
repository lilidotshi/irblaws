package com.eightbyeight.irblaws.adapters;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.eightbyeight.irblaws.R;
import com.eightbyeight.irblaws.jsonobjects.Laws;

public class ExpandableListAdapter extends BaseExpandableListAdapter{

	private Context mContext;
	private Laws mLaws;
    
	public ExpandableListAdapter(Context context, Laws laws) {
		mContext = context;
		mLaws = laws;
	}

	@Override
	public int getGroupCount() {
		return mLaws.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mLaws.getLaws().get(groupPosition).getContent().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return mLaws.getLaws().get(groupPosition).getLawName();
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mLaws.getLaws().get(groupPosition).getContent().get(childPosition).getSectionName();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater)mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }
 
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
 
        return convertView;	
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final String childText = (String) getChild(groupPosition,childPosition);
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_item, null);
		}
		TextView listChild = (TextView)convertView.findViewById(R.id.lblListItem);
		listChild.setText(childText);
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
