package br.com.androidzin.launchablesitens;

import android.graphics.drawable.Drawable;

public class ActivityItem extends LaunchableItem {

	public ActivityItem(String itemLaunchable, String itemPackage,
			Drawable itemIcon) {
		super(itemLaunchable, itemPackage, itemIcon);
	}

	@Override
	public ItemType getType() {
		return ItemType.ACTIVITY;
	}

}
