package br.com.androidzin.launchablesitens;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

public class RetrieveInfoWidgets extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launchable_activity);

		List<LaunchableListItem> data = GetSampleData();

		ArrayAdapter adapter = new LaunchableItemAdapter(this, R.layout.item, data);

		setListAdapter(adapter);
	}

	private List<LaunchableListItem> GetSampleData() {
		List<LaunchableListItem> list = new ArrayList<LaunchableListItem>();

		PackageManager packageManager = getPackageManager();

		AppWidgetManager manager = AppWidgetManager.getInstance(this);
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
}
