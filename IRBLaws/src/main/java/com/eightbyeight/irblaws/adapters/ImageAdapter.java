package com.eightbyeight.irblaws.adapters;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eightbyeight.irblaws.R;
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
        return signals.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {  // if it's not recycled, initialize some attributes
            LayoutInflater i = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = (View) i.inflate(R.layout.grid_item, parent, false);
        }
        String image = signals.get(position).getImage();
        int resID = context.getResources().getIdentifier(image, "drawable", context.getPackageName());

        TextView t = (TextView) convertView.findViewById(R.id.label);
        ImageView i = (ImageView) convertView.findViewById(R.id.image);
        t.setText(signals.get(position).getText());
        i.setImageResource(resID);
        return convertView;
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
