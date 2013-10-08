package br.com.androidzin.launchablesitens;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

	@Override
	public Element toXML(Document doc) throws ParserConfigurationException {
		
		Element favorite = doc.createElement("favorite");
		favorite.setAttribute(PACKAGE_NAME, getItemPackage());
		favorite.setAttribute(CLASS_NAME, getItemLaunchable());
		favorite.setAttribute(LAUNCHER_SCREEN, String.valueOf(getHomeScreenNumber()));
		favorite.setAttribute(X, "");
		favorite.setAttribute(Y, "");
		
		return favorite;
	}

}
