package br.com.androidzin.launchablesitens;

import android.graphics.drawable.Drawable;

public class LaunchableListItem {
	
	private String itemLaunchable;
	private String itemPackage;
	private Drawable itemIcon;
	private boolean isSelected;
	
	public LaunchableListItem(String itemLaunchable, String itemPackage,
			Drawable itemIcon) {
		super();
		this.itemLaunchable = itemLaunchable;
		this.itemPackage = itemPackage;
		this.itemIcon = itemIcon;
		isSelected = false;
	}

	public String getItemLaunchable() {
		return itemLaunchable;
	}

	public void setItemLaunchable(String itemLaunchable) {
		this.itemLaunchable = itemLaunchable;
	}

	public String getItemPackage() {
		return itemPackage;
	}

	public void setItemPackage(String itemPackage) {
		this.itemPackage = itemPackage;
	}

	public Drawable getItemIcon() {
		return itemIcon;
	}

	public void setItemIcon(Drawable itemIcon) {
		this.itemIcon = itemIcon;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	

}
