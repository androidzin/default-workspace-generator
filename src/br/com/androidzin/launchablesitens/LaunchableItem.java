package br.com.androidzin.launchablesitens;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import android.graphics.drawable.Drawable;

public abstract class LaunchableItem implements Comparable<LaunchableItem> {
	
	protected enum ItemType{ ACTIVITY, WIDGET};
	protected static final  String PACKAGE_NAME = "launcher:packageName";
	protected static final  String CLASS_NAME = "launcher:className";
	protected static final  String LAUNCHER_SCREEN = "launcher:screen";
	protected static final  String X = "launcher:x";
	protected static final  String Y = "launcher:y";
	protected static final  String SPAN_X = "launcher:spanX";
	protected static final  String SPAN_Y = "launcher:spanY";
	
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
	
	public abstract Element toXML(Document document) throws ParserConfigurationException;
	
	public abstract ItemType getType();
	
	@Override
	public int compareTo(LaunchableItem another) {
		if(homeScreenNumber < another.getHomeScreenNumber()){
			return -1;
		} else if (homeScreenNumber == another.getHomeScreenNumber()){
			return 0;
		} else {
			return 1;
		}
	}

}
