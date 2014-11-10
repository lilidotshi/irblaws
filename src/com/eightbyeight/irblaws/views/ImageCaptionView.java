package com.eightbyeight.irblaws.views;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
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
		
		ArrayList<String> images = content.getImages();
		LinearLayout.LayoutParams layoutParams = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
		layoutParams.topMargin = 10;
		layoutParams.leftMargin = 10;
		layoutParams.rightMargin = 10;
		layoutParams.bottomMargin = 10;
		
		//Add image view
		for (String image : images){
			ImageView imageView = new ImageView(getContext());
			imageView.setAdjustViewBounds(true);
			imageView.setLayoutParams(layoutParams);
			int resID = getResources().getIdentifier(image, "drawable", getContext().getPackageName());
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), resID);
			imageView.setImageBitmap(getRoundedCornerBitmap(bmp));
			addView(imageView);
		}
		
		//Add textview
		if (content.getExtras() != null && !content.getExtras().isEmpty()){
			TextView textView = new TextView(getContext());
			textView.setText(Html.fromHtml(content.getExtras()));
			textView.setLayoutParams(layoutParams);
			addView(textView);
		}
		
		return this;
		
	}
	
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
	    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
	        bitmap.getHeight(), Config.ARGB_8888);
	    Canvas canvas = new Canvas(output);
	 
	    final int color = 0xff424242;
	    final Paint paint = new Paint();
	    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
	    final RectF rectF = new RectF(rect);
	    final float roundPx = 12;
	 
	    paint.setAntiAlias(true);
	    canvas.drawARGB(0, 0, 0, 0);
	    paint.setColor(color);
	    canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
	 
	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	    canvas.drawBitmap(bitmap, rect, rect, paint);
	 
	    return output;
	  }
}
