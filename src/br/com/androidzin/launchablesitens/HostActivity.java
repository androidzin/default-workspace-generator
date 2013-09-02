package br.com.androidzin.launchablesitens;

import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class HostActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getSupportActionBar();
		// Hide Actionbar Icon
		actionBar.setDisplayShowHomeEnabled(false);

		// Hide Actionbar Title
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Tab tab = actionBar.newTab().setTabListener(new FragmentInfoActivity());
		tab.setText(R.string.activities);
		actionBar.addTab(tab);
		
		tab = actionBar.newTab().setTabListener(new FragmentInfoWidget());
		tab.setText(R.string.widgets);
		actionBar.addTab(tab);
		
	}

}
