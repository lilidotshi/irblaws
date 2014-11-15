package com.eightbyeight.irblaws;

import android.app.Dialog;
import android.app.Service;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.eightbyeight.irblaws.jsonobjects.Laws;
import com.eightbyeight.irblaws.jsonobjects.Signal;
import com.eightbyeight.irblaws.views.CustomVideoView;

import java.util.List;

/**
 * Created by MPU on 11/15/2014.
 */
public class VideoDialogFragment extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Signal curSignal = (Signal) getArguments().getSerializable("cursignal");
        View rootFragment = inflater.inflate(R.layout.popup_video, container, false);
        LayoutInflater layoutInflater
                = (LayoutInflater)getActivity().getBaseContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        TextView title = (TextView)rootFragment.findViewById(R.id.titleView);
        title.setText(curSignal.getText());
        TextView description = (TextView)rootFragment.findViewById(R.id.descriptionView);
        description.setText(curSignal.getDescription());
        CustomVideoView vw = (CustomVideoView)rootFragment.findViewById(R.id.video);
        vw.setZOrderOnTop(true);
        vw.resizeVideo(700,500);
        vw.setVideoURI(Uri.parse(curSignal.getVideo()));

        vw.start();
        final CustomVideoView constantVw = vw;
        vw.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                constantVw.seekTo(0);

                constantVw.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getActionMasked() == MotionEvent.ACTION_UP){

                            constantVw.start();
                            constantVw.setOnTouchListener(null);

                        }
                        return true;
                    }
                });
            }
        });

        return rootFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}
