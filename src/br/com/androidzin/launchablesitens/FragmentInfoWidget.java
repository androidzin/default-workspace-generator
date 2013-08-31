package br.com.androidzin.launchablesitens;

import java.util.ArrayList;
import java.util.List;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockListFragment;

public class FragmentInfoWidget extends SherlockListFragment implements TabListener {

	private Fragment mFragment;
	private static final String TAG = "Tab";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		List<LaunchableListItem> data = GetSampleData();
		ArrayAdapter adapter = new LaunchableItemAdapter(getActivity(), R.layout.item, data);
		setListAdapter(adapter);
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	private List<LaunchableListItem> GetSampleData() {
		List<LaunchableListItem> list = new ArrayList<LaunchableListItem>();

		PackageManager packageManager = getActivity().getPackageManager();

		AppWidgetManager manager = AppWidgetManager.getInstance(getActivity());
		List<AppWidgetProviderInfo> infoList = manager.getInstalledProviders();
		for (AppWidgetProviderInfo info : infoList) {
			Drawable icon = null;
			try {
				icon = packageManager.getApplicationIcon(info.provider.getPackageName());

			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
			LaunchableListItem item = new LaunchableListItem(info.provider.getClassName(),	info.provider.getPackageName(), icon);

			list.add(item);

			String TAG = "widget";
			Log.d(TAG, "Configure Name-" + info.configure);
			Log.d(TAG, "Provider Name-" + info.provider);
		}

		return list;
	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		Log.d(TAG, "OnTabReselected");
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		Log.d(TAG, "OnTabSelected");
		mFragment = new FragmentInfoWidget();
        ft.add(android.R.id.content, mFragment);
        ft.attach(mFragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		Log.d(TAG, "OnTabUnselected");
		ft.remove(mFragment);
	}
}
