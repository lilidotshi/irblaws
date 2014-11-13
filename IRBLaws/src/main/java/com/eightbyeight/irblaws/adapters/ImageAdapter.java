package com.eightbyeight.irblaws.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.eightbyeight.irblaws.jsonobjects.RefSignals;
import com.eightbyeight.irblaws.jsonobjects.Signal;
import com.eightbyeight.irblaws.jsonobjects.Signals;

import java.util.ArrayList;

/**
 * Created by lilidotshi on 11/13/2014.
 */
public class ImageAdapter extends BaseAdapter{

    private ImageAdapter(){}
    private ArrayList<Signal> signals;
    private Context context;

    public ImageAdapter(Context ctx, ArrayList<Signal> signals){
        this.signals = signals;
        this.context = ctx;
    }

    @Override
    public int getCount() {
        Log.d("lili's test", "size is " + signals.size());
        return signals.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(400, 400));
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        String image = signals.get(position).getImage();
        int resID = context.getResources().getIdentifier(image, "drawable", context.getPackageName());

        imageView.setImageResource(resID);
        return imageView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
}
