package com.eightbyeight.irblaws;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.eightbyeight.irblaws.jsonobjects.Content;
import com.eightbyeight.irblaws.jsonobjects.Law;
import com.eightbyeight.irblaws.jsonobjects.Laws;
import com.eightbyeight.irblaws.jsonobjects.Section;

/**
 * Generic content fragment for displaying a user provided array of views
 * @author lilishi
 *
 */
public class ContentFragment extends Fragment {
	private static final String IMAGE_TYPE = "image";
	private static final String DEFINITION_TYPE = "definition";
	private static final String TEXT_TYPE = "type";
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	View rootFragment = inflater.inflate(R.layout.content_fragment, container, false);
    	Bundle bundle = this.getArguments();
    	Laws laws = (Laws) bundle.get("laws");
    	String sectiontitle = bundle.getString("sectiontitle");
    	List<View> viewList = createViewsForSection(laws,sectiontitle);
    	LinearLayout contentLayout = (LinearLayout) rootFragment.findViewById(R.id.contentLayout);
    	for (View v : viewList){
    		contentLayout.addView(v);
    	}
        return rootFragment;
    }

	private List<View> createViewsForSection(Laws laws, String sectiontitle) {
		Law law = laws.getLaws().get(0);
		Section sectionContent = null;
		for (Section section : law.getContent()){
			if (section.getSectionName().equalsIgnoreCase(sectiontitle)){
				sectionContent = section;
				break;
			}
		}
		
		if (sectionContent != null){
			return createViews(sectionContent);
		} else {
			return new ArrayList<View>();
		}
	}

	private List<View> createViews(Section sectionContent) {
		ArrayList<View> views = new ArrayList<View>();
		for (Content content : sectionContent.getSectionContents()){
			if (content.getType().equalsIgnoreCase(IMAGE_TYPE)){
				views.add(createImageView(content));
			}
		}
		return views;
	}

	private View createImageView(Content content) {
		ImageView imageView = new ImageView(getActivity());
		LinearLayout.LayoutParams layoutParams = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
		layoutParams.topMargin = 5;
		imageView.setLayoutParams(layoutParams);
		int resID = getResources().getIdentifier(content.getValue() , "drawable", getActivity().getPackageName());
		imageView.setImageResource(resID);
		return imageView;
	}
}
