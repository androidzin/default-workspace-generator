package br.com.androidzin.launchablesitens;

import android.os.Bundle;
import android.util.Log;
import br.com.androidzin.launchablesitens.HomeScreenDialog.NoticeDialogListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class HostActivity extends SherlockFragmentActivity implements NoticeDialogListener, OnItemCheckedListener  {

	private LaunchableListItem currentItem;
	
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId() == R.id.generate_json){
			
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
		
	}
	
	@Override
	public void onItenChecked(LaunchableListItem item) {		
		currentItem = item;
		new HomeScreenDialog().show(getSupportFragmentManager(), "a");
	}
	
	@Override
	public void onDialogPositiveClick(int selectedHomeScreen) {
		currentItem.setHomeScreenNumber(selectedHomeScreen);
	}

}
