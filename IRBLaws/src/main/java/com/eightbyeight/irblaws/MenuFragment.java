package com.eightbyeight.irblaws;

import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.eightbyeight.irblaws.adapters.ExpandableListAdapter;
import com.eightbyeight.irblaws.jsonobjects.Laws;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MenuFragment extends Fragment implements ExpandableListView.OnChildClickListener{
	private ExpandableListAdapter listAdapter;
	private ExpandableListView listView;
	private String fileName;
	private Laws mLaws;
	
	public static final MenuFragment newInstance(String fileName){
		MenuFragment f = new MenuFragment();
		Bundle args = new Bundle(1);
		args.putString("fileName", fileName);
		f.setArguments(args);
		return f;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	View rootFragment = inflater.inflate(R.layout.before_game_fragment, container, false);
    	fileName = getArguments().getString("fileName");
    	mLaws = parseJson();
    	listView = (ExpandableListView)rootFragment.findViewById(R.id.expandableListView1);
    	listAdapter = new ExpandableListAdapter(getActivity(),mLaws);
    	listView.setOnChildClickListener(this);
    	listView.setAdapter(listAdapter);
    	
        return rootFragment;
    }
	
	private Laws parseJson(){
		Gson gsonBuilder = new GsonBuilder().create();
		Laws laws = null;
		try {
			InputStreamReader isr = new InputStreamReader(getActivity().getAssets().open(
					fileName));
			laws = gsonBuilder.fromJson(isr, Laws.class);
		} catch (IOException e) {
			e.printStackTrace();
		} 

		return laws;
	}


	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		String childText = parent.getExpandableListAdapter().getChild(groupPosition, childPosition).toString();
		
		Bundle args = new Bundle();
		args.putSerializable("laws", mLaws);
		args.putInt("lawnumber", groupPosition);
		args.putInt("sectionnumber", childPosition);
		args.putString("sectiontitle", childText);
		Intent readerIntent = new Intent(getActivity(),ReaderActivity.class);
		readerIntent.putExtras(args);
		startActivity(readerIntent);

		return true;
	}
}
