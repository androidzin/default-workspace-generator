package br.com.androidzin.launchablesitens;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class RetriveInfoActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launchable_activity);

		List<LaunchableListItem> data = GetSampleData();

		ArrayAdapter adapter = new LaunchableItemAdapter(this, R.layout.item, data);

		setListAdapter(adapter);

	}

	private List<LaunchableListItem> GetSampleData() {
		List<LaunchableListItem> launchableItem = new ArrayList<LaunchableListItem>();

		PackageManager packageManager = getPackageManager();

		List<ResolveInfo> result = getLauncheableActivityList(packageManager);

		for (Iterator iterator = result.iterator(); iterator.hasNext();) {

			ResolveInfo resolveInfo = (ResolveInfo) iterator.next();

			Drawable icon = null;
			try {
				icon = packageManager
						.getApplicationIcon(resolveInfo.activityInfo.packageName);

			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}

			LaunchableListItem item = new LaunchableListItem(
					resolveInfo.activityInfo.name,
					resolveInfo.activityInfo.packageName, icon);

			launchableItem.add(item);

		}
		return launchableItem;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public List<ResolveInfo> getLauncheableActivityList(
			PackageManager packageManager) {
		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> resolveInfoList = packageManager
				.queryIntentActivities(intent, 0);
		return resolveInfoList;
	}

}
