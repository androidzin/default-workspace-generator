package br.com.androidzin.launchablesitens;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import br.com.androidzin.launchablesitens.HomeScreenDialog.NoticeDialogListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class HostActivity extends SherlockFragmentActivity implements NoticeDialogListener, OnItemCheckedListener  {

	private LaunchableItem currentItem;
	private List<LaunchableItem> launchableItens;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getSupportActionBar();
		// Hide Actionbar Icon
		actionBar.setDisplayShowHomeEnabled(false);

		// Hide Actionbar Title
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		FragmentInfoActivity infoActivity = new FragmentInfoActivity();
		Tab tab = actionBar.newTab().setTabListener(infoActivity);
		tab.setText(R.string.activities);
		actionBar.addTab(tab);
		
		
		FragmentInfoWidget infoWidget = new FragmentInfoWidget();
		tab = actionBar.newTab().setTabListener(infoWidget);
		tab.setText(R.string.widgets);
		actionBar.addTab(tab);
		
		launchableItens = new ArrayList<LaunchableItem>();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.generate_json){
			File savedFile = WorkspaceGenerator.saveXML(launchableItens);
			String feedback = getString(R.string.file_not_saved);
					
			if(savedFile != null){
				feedback = getString(R.string.file_saved_at) + savedFile.getAbsolutePath();
			}
			Toast.makeText(getApplicationContext(), feedback, Toast.LENGTH_LONG).show();
			
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
		
	}


	@Override
	public void onItenChecked(LaunchableItem item) {		
		currentItem = item;
		if(currentItem.isSelected()){
			new HomeScreenDialog().show(getSupportFragmentManager(), null);
			launchableItens.add(currentItem);
		} else {
			launchableItens.remove(currentItem);
		}
	}
	
	@Override
	public void onDialogPositiveClick(int selectedHomeScreen) {
		currentItem.setHomeScreenNumber(selectedHomeScreen);
	}
}
