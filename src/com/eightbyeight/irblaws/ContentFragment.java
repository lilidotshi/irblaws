package com.eightbyeight.irblaws;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.VideoView;

import com.eightbyeight.irblaws.jsonobjects.Content;
import com.eightbyeight.irblaws.jsonobjects.Law;
import com.eightbyeight.irblaws.jsonobjects.Laws;
import com.eightbyeight.irblaws.jsonobjects.Section;

/**
 * Generic content fragment for displaying a user provided array of views
 * @author lilishi
 *
 */
public class ContentFragment extends Fragment{
	private static final String IMAGE_TYPE = "image";
	private static final String DEFINITION_TYPE = "definition";
	private static final String TEXT_TYPE = "text";
	private static final String SECTION_HEADER_TYPE = "sectionheader";
	private static final String VIDEO_TYPE = "video";
	private static final String AMENDMENT_TYPE = "amendment";
	private String mSectionHeader;
	private int mLawNumber;
	private ArrayList<VideoView> mVideoViews = new ArrayList<VideoView>();
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	View rootFragment = inflater.inflate(R.layout.content_fragment, container, false);
    	Bundle bundle = this.getArguments();
    	Laws laws = (Laws) bundle.get("laws");
    	mLawNumber = bundle.getInt("lawnumber");
    	mSectionHeader = bundle.getString("sectiontitle");
    	List<View> viewList = createViewsForSection(laws,mLawNumber,mSectionHeader);
    	LinearLayout contentLayout = (LinearLayout) rootFragment.findViewById(R.id.contentLayout);
    	for (View v : viewList){
    		contentLayout.addView(v);
    	}
        return rootFragment;
    }

	private List<View> createViewsForSection(Laws laws, int lawNumber, String sectiontitle) {
		Law law = laws.getLaws().get(lawNumber);
		Section sectionContent = null;
		for (int i = 0; i < law.getContent().size(); i++){
			Section section = law.getContent().get(i);
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
			} else if (content.getType().equalsIgnoreCase(DEFINITION_TYPE)){
				views.add(createDefinitionView(content));
			} else if (content.getType().equalsIgnoreCase(TEXT_TYPE)){
				views.add(createTextView(content));
			} else if (content.getType().equalsIgnoreCase(SECTION_HEADER_TYPE)){
				views.add(createSectionHeader(content));
			} else if (content.getType().equalsIgnoreCase(VIDEO_TYPE)){
				views.add(createVideoView(content));
			} else if (content.getType().equalsIgnoreCase(AMENDMENT_TYPE)){
				TextView amendmentView = (TextView) createTextView(content);
				amendmentView.setBackgroundResource(R.drawable.blue_rounded);
				views.add(amendmentView);
			}
		}
		return views;
	}

	//For section headers
	private View createSectionHeader(Content content) {
		TextView textView = new TextView(getActivity());
		textView.setBackgroundResource(R.drawable.grey_rounded);
		textView.setText(mSectionHeader);
		textView.setTextAppearance(getActivity(), R.style.bold);
		textView.setTextColor(Color.WHITE);
		LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.topMargin = 20;
		params.leftMargin = 20;
		params.rightMargin = 20;
		params.bottomMargin = 20;
		textView.setLayoutParams(params);
		return textView;
	}
	
	//For images
	private View createImageView(Content content) {
		ImageView imageView = new ImageView(getActivity());
		imageView.setAdjustViewBounds(true);
		LinearLayout.LayoutParams layoutParams = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
		layoutParams.topMargin = 5;
		imageView.setLayoutParams(layoutParams);
		int resID = getResources().getIdentifier(content.getValue() , "drawable", getActivity().getPackageName());
		imageView.setImageResource(resID);
		return imageView;
	}
	
	//For definitions
	private View createDefinitionView(Content content){
		TextView textView = new TextView(getActivity());
		textView.setBackgroundResource(R.drawable.green_rounded);
		textView.setText(Html.fromHtml(content.getValue()));
		textView.setTextColor(Color.WHITE);
		LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.topMargin = 20;
		params.leftMargin = 20;
		params.rightMargin = 20;
		params.bottomMargin = 20;
		textView.setLayoutParams(params);
		return textView;
	}
	
	//For textviews
	private View createTextView(Content content){
		TextView textView = new TextView(getActivity());
		textView.setText(Html.fromHtml(content.getValue()));
		LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.topMargin = 20;
		params.leftMargin = 20;
		params.rightMargin = 20;
		params.bottomMargin = 20;
		textView.setLayoutParams(params);
		return textView;
	}
	
	//For videoviews
	private View createVideoView(Content content){
		VideoView videoView = new VideoView(getActivity());
		videoView.setBackgroundResource(R.drawable.play);
		//Set the parameters for the video
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,550);
		params.gravity = Gravity.CENTER_HORIZONTAL;
		params.bottomMargin = 20;
		params.topMargin = 20;
		videoView.setLayoutParams(params);
        //URI either from net
        videoView.setVideoURI(Uri.parse(content.getValue()));
        videoView.seekTo(10);
        //Variables just for videoview because it needs constants.
        videoView.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getActionMasked() == MotionEvent.ACTION_UP){
					VideoView view = (VideoView)v;
					if (view.isPlaying()){
						view.pause();
						view.setBackgroundResource(R.drawable.play);
					} else {
						view.setBackground(null);
						view.start();
					}
				}
				return true;
			}
        	
        });
        //Keep track of videos so we can pause if it's playing since viewpager doesn't autopause
        mVideoViews.add(videoView);
        return videoView;
	}
	
	@Override
	public void setUserVisibleHint(boolean isVisible) {
		if (!isVisible){
			stopVideos();
		}
		super.onHiddenChanged(isVisible);
	}
	
	//Stop all videos if it has changed.
	private void stopVideos(){
		for (VideoView view : mVideoViews) {
			if (view.isPlaying()){
				view.pause();
			}
		}
	}
}
