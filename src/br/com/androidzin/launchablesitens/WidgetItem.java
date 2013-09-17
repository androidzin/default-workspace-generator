package br.com.androidzin.launchablesitens;

import android.graphics.drawable.Drawable;

public class WidgetItem extends LaunchableItem {

	public WidgetItem(String itemLaunchable, String itemPackage,
			Drawable itemIcon) {
		super(itemLaunchable, itemPackage, itemIcon);
	}

	@Override
	public ItemType getType() {
		return ItemType.WIDGET;
	}

}
