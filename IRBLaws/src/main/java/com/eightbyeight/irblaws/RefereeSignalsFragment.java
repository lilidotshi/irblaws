package com.eightbyeight.irblaws;

import android.app.Activity;
import android.app.Service;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.eightbyeight.irblaws.adapters.ImageAdapter;
import com.eightbyeight.irblaws.jsonobjects.RefSignals;
import com.eightbyeight.irblaws.jsonobjects.Signal;
import com.eightbyeight.irblaws.jsonobjects.Signals;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RefereeSignalsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RefereeSignalsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RefereeSignalsFragment extends Fragment implements AdapterView.OnItemClickListener{

    private OnFragmentInteractionListener mListener;
    private ArrayList<Signal> curFrgmtSignals;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    public static RefereeSignalsFragment newInstance(int position, RefSignals signals) {
        RefereeSignalsFragment fragment = new RefereeSignalsFragment();
        Bundle args = new Bundle();
        args.putInt("sectionnumber", position);
        args.putSerializable("signals",signals);
        fragment.setArguments(args);
        return fragment;
    }

    public RefereeSignalsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_referee_signals, container, false);
        GridView gridview = (GridView) rootView.findViewById(R.id.grid_view);
        int sectionNumber = getArguments().getInt("sectionnumber");
        RefSignals signals = (RefSignals) getArguments().getSerializable("signals");

        if (sectionNumber == 0){
            curFrgmtSignals = signals.getAllRefSignals();
            gridview.setAdapter(new ImageAdapter(getActivity(),curFrgmtSignals));
        } else {
            curFrgmtSignals = signals.getRefsignals().get(sectionNumber).getContent();
            gridview.setAdapter(new ImageAdapter(getActivity(),curFrgmtSignals));
        }

        gridview.setOnItemClickListener(this);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentManager fm = getActivity().getSupportFragmentManager();

        VideoDialogFragment videoDialogFragment = new VideoDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("cursignal",this.curFrgmtSignals.get(position));
        videoDialogFragment.setArguments(args);
        videoDialogFragment.show(fm,curFrgmtSignals.get(position).getText());
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
