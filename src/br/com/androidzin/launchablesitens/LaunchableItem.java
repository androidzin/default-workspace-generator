package br.com.androidzin.launchablesitens;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;

public abstract class LaunchableItem {
	
	protected enum ItemType{ ACTIVITY, WIDGET};
	
	private String itemLaunchable;
	private String itemPackage;
	private Drawable itemIcon;
	private boolean isSelected;
	private int homeScreenNumber;
	
	public int getHomeScreenNumber() {
		return homeScreenNumber;
	}

	public void setHomeScreenNumber(int homeScreenNumber) {
		this.homeScreenNumber = homeScreenNumber;
	}

	public LaunchableItem(String itemLaunchable, String itemPackage,
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
	
	public JSONObject toJSON(){
		JSONObject root = new JSONObject();
		try {
			root.put("packageName", getItemPackage());
			root.put("className", getItemLaunchable());
			return root;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public abstract ItemType getType();

}
