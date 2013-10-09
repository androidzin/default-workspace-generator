package br.com.androidzin.launchablesitens;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

	@Override
	public Element toXML(Document doc) throws ParserConfigurationException {
		
		Element appwidget = doc.createElement("appwidget");
		appwidget.setAttribute(PACKAGE_NAME, getItemPackage());
		appwidget.setAttribute(CLASS_NAME, getItemLaunchable());
		appwidget.setAttribute(LAUNCHER_SCREEN, String.valueOf(getHomeScreenNumber()));
		appwidget.setAttribute(X, "");
		appwidget.setAttribute(Y, "");
		appwidget.setAttribute(SPAN_X, "");
		appwidget.setAttribute(SPAN_Y, "");
		
		return appwidget;
	}

}
