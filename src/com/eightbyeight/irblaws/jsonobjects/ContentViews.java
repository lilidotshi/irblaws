package com.eightbyeight.irblaws.jsonobjects;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class ContentViews {

	private static final String IMAGE_TYPE = "image";
	private static final String DEFINITION_TYPE = "definition";
	private static final String TEXT_TYPE = "type";
	private String mSection;
	private Context mContext;
	private List<View> views;
	public ContentViews (String section, JSONArray contentArray, Context context){
		mSection = section;
		views = new ArrayList<View>();
		mContext = context;
		parseContent(contentArray);
	}
	
	private void parseContent(JSONArray contentArray){
		for (int i = 0; i < contentArray.length(); i++){
			try {
				JSONObject content = contentArray.getJSONObject(i);
				String type = content.getString("type");
				if (type.equalsIgnoreCase(IMAGE_TYPE)){
					addImageView(content);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void addImageView(JSONObject content){
		ImageView imageView = new ImageView(mContext);
		LinearLayout.LayoutParams layoutParams = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
		imageView.setLayoutParams(layoutParams);
		views.add(imageView);
	}
	
	private void addDefinitionView(JSONObject content){
		
	}
	
	private void addTextView(JSONObject content){
		
	}
	
	public List<View> getViews(){
		return views;
	}
	
	public String getSection(){
		return mSection;
	}
}
