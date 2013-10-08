package br.com.androidzin.launchablesitens;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import android.os.Environment;

public class WorkspaceGenerator {

	private static final String DEFAULT_WORKSPACE_XML = "default_workspace.xml";

	public static File saveXML(List<LaunchableItem> selectedItens) {
		if (canWriteFile()) {

			File path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			File xml = new File(path, DEFAULT_WORKSPACE_XML);

			if(generateXML(selectedItens, xml)){
				return xml;
			}

		}
		return null;
	}

	private static boolean generateXML(List<LaunchableItem> selectedItens,
			File xml) {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElement("favorites");
			rootElement.setAttribute("xmlns:launcher",
					"http://schemas.android.com/apk/res/com.android.launcher");

			doc.appendChild(rootElement);

			for (LaunchableItem launchableItem : selectedItens) {
				Element newNode = launchableItem.toXML(doc);
				rootElement.appendChild(newNode);
			}

			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			DOMSource source = new DOMSource(doc);
			

			StreamResult result = new StreamResult(xml);
			transformer.transform(source, result);
			

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return xml.exists();
	}

	private static boolean canWriteFile() {
		boolean mExternalStorageAvailable = false;
		boolean mExternalStorageWriteable = false;

		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			mExternalStorageAvailable = true;
			mExternalStorageWriteable = false;
		} else {
			mExternalStorageAvailable = mExternalStorageWriteable = false;
		}

		return mExternalStorageWriteable;
	}

}
