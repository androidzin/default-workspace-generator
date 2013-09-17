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

public class FragmentInfoActivity extends FragmentInfo implements TabListener{


	protected List<LaunchableItem> getSampleData() {
		Log.d("teste", "act chamada");
		List<LaunchableItem> launchableItem = new ArrayList<LaunchableItem>();

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
			LaunchableItem item = new ActivityItem(resolveInfo.activityInfo.name,
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
}
