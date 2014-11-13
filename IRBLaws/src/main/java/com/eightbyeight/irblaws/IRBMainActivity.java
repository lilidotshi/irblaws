package com.eightbyeight.irblaws;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class IRBMainActivity extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_irbmain);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.mainLayout, new IRBFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.irbmain, menu);
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static class IRBFragment extends Fragment implements OnClickListener{
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	        // Inflate the layout for this fragment
	    	View rootFragment = inflater.inflate(R.layout.activity_irbfragment, container, false);
	    	ArrayList<Button> menuButtons = new ArrayList<Button>();
			menuButtons.add((Button)rootFragment.findViewById(R.id.beforeButton));
			menuButtons.add((Button)rootFragment.findViewById(R.id.methodButton));
			menuButtons.add((Button)rootFragment.findViewById(R.id.fieldPlayButton));
			menuButtons.add((Button)rootFragment.findViewById(R.id.restartsButton));
			menuButtons.add((Button)rootFragment.findViewById(R.id.inGoalButton));
			menuButtons.add((Button)rootFragment.findViewById(R.id.variations));
			menuButtons.add((Button)rootFragment.findViewById(R.id.refSigs));
			
			for (Button button : menuButtons){
				button.setOnClickListener(this);
			}
	        return rootFragment;
	    }

		@Override
		public void onClick(View v) {
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			if (v.getId() == R.id.beforeButton){
				Fragment befTheGameFragment = MenuFragment.newInstance("before_match_laws.json");
				ft.replace(R.id.mainLayout, befTheGameFragment);
				ft.addToBackStack(null);
				ft.commit();
			} else if (v.getId() == R.id.methodButton) {
				Fragment methodFragment = MenuFragment.newInstance("method_of_playing.json");
				ft.replace(R.id.mainLayout, methodFragment);
				ft.addToBackStack(null);
				ft.commit();
			} else if (v.getId() == R.id.fieldPlayButton) {
				Fragment methodFragment = MenuFragment.newInstance("in_field_play.json");
				ft.replace(R.id.mainLayout, methodFragment);
				ft.addToBackStack(null);
				ft.commit();
			} else if (v.getId() == R.id.restartsButton) {
				Fragment methodFragment = MenuFragment.newInstance("restarts.json");
				ft.replace(R.id.mainLayout, methodFragment);
				ft.addToBackStack(null);
				ft.commit();
			} else if (v.getId() == R.id.inGoalButton) {
				Fragment methodFragment = MenuFragment.newInstance("in_goal.json");
				ft.replace(R.id.mainLayout, methodFragment);
				ft.addToBackStack(null);
				ft.commit();
			} else if (v.getId() == R.id.variations) {
				Fragment methodFragment = MenuFragment.newInstance("variations.json");
				ft.replace(R.id.mainLayout, methodFragment);
				ft.addToBackStack(null);
				ft.commit();
			} else if (v.getId() == R.id.refSigs){
                Intent refSignalsIntent = new Intent(getActivity(),GridActivity.class);
                Bundle extras = new Bundle();
                extras.putString("filename","ref_signals.json");
                refSignalsIntent.putExtras(extras);
                startActivity(refSignalsIntent);
            }
		}
	}

}
