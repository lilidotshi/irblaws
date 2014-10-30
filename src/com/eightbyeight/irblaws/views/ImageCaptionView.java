package com.eightbyeight.irblaws.views;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eightbyeight.irblaws.R;
import com.eightbyeight.irblaws.jsonobjects.Content;

public class ImageCaptionView extends LinearLayout{

	public ImageCaptionView(Context context) {
		super(context);
		//Create linear layout
		setBackgroundResource(R.drawable.grey_rounded);
		LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.topMargin = 20;
		params.leftMargin = 20;
		params.rightMargin = 20;
		params.bottomMargin = 20;
		setLayoutParams(params);
		this.setOrientation(LinearLayout.VERTICAL);
		
	}

	public ImageCaptionView createView(Content content){
		
		//Add image view
		ImageView imageView = new ImageView(getContext());
		imageView.setAdjustViewBounds(true);
		LinearLayout.LayoutParams layoutParams = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
		layoutParams.topMargin = 10;
		layoutParams.leftMargin = 10;
		layoutParams.rightMargin = 10;
		imageView.setLayoutParams(layoutParams);
		int resID = getResources().getIdentifier(content.getValue() , "drawable", getContext().getPackageName());
		imageView.setImageResource(resID);
		addView(imageView);
		
		//Add textview
		if (content.getExtras() != null && !content.getExtras().isEmpty()){
			TextView textView = new TextView(getContext());
			textView.setText(Html.fromHtml(content.getExtras()));
			textView.setLayoutParams(layoutParams);
			addView(textView);
		}
		
		return this;
		
	}
}
