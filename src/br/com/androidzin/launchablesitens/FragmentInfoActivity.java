package br.com.androidzin.launchablesitens;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import br.com.androidzin.launchablesitens.HomeScreenDialog.NoticeDialogListener;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockListFragment;

public class FragmentInfoActivity extends SherlockListFragment implements TabListener{

	private static final String TAG = "Tab";
	private Fragment mFragment;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		List<LaunchableListItem> data = GetSampleData();
		ArrayAdapter adapter = new LaunchableItemAdapter(getActivity(), R.layout.item, data, (OnItemCheckedListener) getActivity());
		setListAdapter(adapter);
		return inflater.inflate(R.layout.launchable_activity, container, false);
	}

	private List<LaunchableListItem> GetSampleData() {
		List<LaunchableListItem> launchableItem = new ArrayList<LaunchableListItem>();

		PackageManager packageManager = getActivity().getPackageManager();
		List<ResolveInfo> result = getLauncheableActivityList(packageManager);
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			ResolveInfo resolveInfo = (ResolveInfo) iterator.next();
			Drawable icon = null;
			try {
				icon = packageManager.getApplicationIcon(resolveInfo.activityInfo.packageName);
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
			LaunchableListItem item = new LaunchableListItem(resolveInfo.activityInfo.name,
					resolveInfo.activityInfo.packageName, icon);
			launchableItem.add(item);
		}
		return launchableItem;
	}

	public List<ResolveInfo> getLauncheableActivityList(PackageManager packageManager) {
		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, 0);
		return resolveInfoList;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		Log.d(TAG, "OnTabReselected");
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		Log.d(TAG, "OnTabSelected");
		mFragment = new FragmentInfoActivity();
        ft.add(android.R.id.content, mFragment);
        ft.attach(mFragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		Log.d(TAG, "OnTabUnselected");
		ft.remove(mFragment);
	}
	
}
