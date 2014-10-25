package com.eightbyeight.irblaws;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.eightbyeight.irblaws.jsonobjects.Laws;

public class ReaderActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	private int mLawNumber;
	
	private Laws mLaws;
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static int num_pages = 0;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

	private int mSectionNumber = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reader);
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(mSectionNumber);
	}
	
	@Override
	public void onNavigationDrawerItemSelected(int position) {
		//Set up our variables
		setUpVariablesForBundle(position);
		mTitle = mLaws.getLaws().get(mLawNumber).getLawName();
		
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);

		if (mPager != null)
			mPager.setCurrentItem(position, true);

	}

	private Bundle setUpVariablesForBundle(int position) {
		mLaws = (Laws) getIntent().getExtras().get("laws");
		mLawNumber = getIntent().getExtras().getInt("lawnumber");
		mSectionNumber = position;
		num_pages = mLaws.getLaws().get(mLawNumber).getContent().size();
		String title = mLaws.getLaws().get(mLawNumber).getContent().get(position).getSectionName();

		//Pass on arguments to our new fragment
		Bundle args = getIntent().getExtras();
		args.putSerializable("laws", mLaws);
		args.putInt("lawnumber", mLawNumber);
		args.putInt("sectionnumber", mSectionNumber);
		args.putString("sectiontitle", title.toString());
		return args;
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.reader, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
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
	
	@Override
	public void onBackPressed() {
		if (mNavigationDrawerFragment != null && mNavigationDrawerFragment.isDrawerOpen()){
			mNavigationDrawerFragment.closeDrawer();
		} else {
			super.onBackPressed();
		}
	}
	
	/**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        	Bundle args = setUpVariablesForBundle(position);
        	ContentFragment contentFragment = new ContentFragment();
        	contentFragment.setArguments(args);
            return contentFragment;
        }

        @Override
        public int getCount() {
            return num_pages;
        }
    }
}
