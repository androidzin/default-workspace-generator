package br.com.androidzin.launchablesitens;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import br.com.androidzin.launchablesitens.HomeScreenDialog.NoticeDialogListener;
import br.com.androidzin.launchablesitens.LaunchableItem.ItemType;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class HostActivity extends SherlockFragmentActivity implements NoticeDialogListener, OnItemCheckedListener  {

	private static final String HOMESCREEN_JSON = "homescreen.json";
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
			generateJSON();
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
		
	}

	private void generateJSON(){
		if(!launchableItens.isEmpty()){
			JSONObject root = new JSONObject();
			JSONArray screens = new JSONArray();
			JSONArray widgets[] = new JSONArray[5];
			JSONArray activities[] = new JSONArray[5];
			
			for(int i = 0; i < 5; i++){
				activities[i] = new JSONArray();
				widgets[i] = new JSONArray();
			}
			
			for (LaunchableItem launchableItem : launchableItens) {
				if(launchableItem.getType() == ItemType.ACTIVITY){
					activities[launchableItem.getHomeScreenNumber()].put(launchableItem.toJSON());
				}else {
					widgets[launchableItem.getHomeScreenNumber()].put(launchableItem.toJSON());
				}
			}
			
			try {
				fillScreenArray(screens, widgets, activities);
				root.put("screens", screens);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			Log.d("homescreen", root.toString());
			
		}
	}

	private void fillScreenArray(JSONArray screens, JSONArray[] widgets,
			JSONArray[] activities) throws JSONException {
		for(int i = 0; i < 5; i++){
			JSONObject container = new JSONObject();
			
			if(activities[i].length() > 0){
				container.put("activities", activities[i]);
			}
			if(widgets[i].length() > 0){
				container.put("widgets", widgets[i]);
			}
			
			screens.put(i, container);
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
