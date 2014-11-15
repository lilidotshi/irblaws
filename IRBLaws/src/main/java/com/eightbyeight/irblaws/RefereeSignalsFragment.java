package com.eightbyeight.irblaws;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.eightbyeight.irblaws.adapters.ImageAdapter;
import com.eightbyeight.irblaws.jsonobjects.RefSignals;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RefereeSignalsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RefereeSignalsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RefereeSignalsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

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
        if (sectionNumber == -1){
            gridview.setAdapter(new ImageAdapter(getActivity(),signals.getAllRefSignals()));
        } else {
            gridview.setAdapter(new ImageAdapter(getActivity(),signals.getRefsignals().get(sectionNumber).getContent()));
        }

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(HelloGridView.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

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
