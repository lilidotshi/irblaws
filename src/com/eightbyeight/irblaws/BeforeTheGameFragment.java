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

public class BeforeTheGameFragment extends Fragment implements ExpandableListView.OnChildClickListener{
	private ExpandableListAdapter listAdapter;
	private ExpandableListView listView;
	private Laws mLaws;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	View rootFragment = inflater.inflate(R.layout.before_game_fragment, container, false);
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
					"before_match_laws.json"));
			laws = gsonBuilder.fromJson(isr, Laws.class);
		} catch (IOException e) {
			e.printStackTrace();
		} 

		return laws;
	}


	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		Log.d("Lili's test","Get page "+groupPosition+","+childPosition+","+id);
		String childText = parent.getExpandableListAdapter().getChild(groupPosition, childPosition).toString();
		Log.d("Lili's test","Child text: "+childText);
		
		
		
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
