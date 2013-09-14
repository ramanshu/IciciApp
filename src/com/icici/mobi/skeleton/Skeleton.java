package com.icici.mobi.skeleton;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.icici.mobi.settings.Standard;

public class Skeleton extends ArrayList<ScreenOption> {
	public OptionSet options;
	public ScreenSet screens;
	public Screen rootScreen;

	public OptionSet getOptions(InputStream optionsXML)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document root = dBuilder.parse(optionsXML);
		OptionSet options = new OptionSet();
		NodeList optionsList = root.getElementsByTagName("option");
		for (int index = 0; index < optionsList.getLength(); index++) {
			Element option = (Element) optionsList.item(index);
			if (option.getNodeType() == Node.ELEMENT_NODE) {
				String id = option.getAttribute("id");
				System.out.println(id);
				ScreenOption screenOption = getScreenOptionFromDOMElement(option);
				options.put(id, screenOption);
			}
		}
		return options;
	}

	public ScreenSet getScreens(InputStream screensXML)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document root = dBuilder.parse(screensXML);
		ScreenSet screens = new ScreenSet();
		NodeList screenList = root.getElementsByTagName("screen");
		for (int index = 0; index < screenList.getLength(); index++) {
			Element option = (Element) screenList.item(index);
			if (option.getNodeType() == Node.ELEMENT_NODE) {
				String id = option.getAttribute("id");
				Screen screen = this.getScreenFromDOMElement(option);
				screens.put(id, screen);

			}
		}
		return screens;
	}

	public void connectScreenButtons(InputStream skeletonXML)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document root = dBuilder.parse(skeletonXML);
		NodeList screenList = root.getElementsByTagName("screen");
		for (int index = 0; index < screenList.getLength(); index++) {
			Element screen = (Element) screenList.item(index);
			if (screen.getNodeType() == Node.ELEMENT_NODE) {
				String id = screen.getAttribute("id");
				System.out.println(id);
				Screen current = screens.get(id);
				NodeList optionList = root.getElementsByTagName("option");
				for (int x = 0; x < optionList.getLength(); x++) {
					Element option = (Element) optionList.item(x);
					if (option.getNodeType() == Node.ELEMENT_NODE) {
						String optionId = option.getAttribute("id");
						String screenId = option.getAttribute("screenid");
						ScreenOption screenOption = options.get(optionId);
						screenOption.screen = screens.get(screenId);
						current.options.put(optionId, screenOption);

					}
				}
			}
		}
		rootScreen = screens.get("root");
	}

	ScreenOption getScreenOptionFromDOMElement(Element option) {
		ScreenOption screenOption = new ScreenOption();
		screenOption.description = option.getAttribute("description");
		screenOption.id = option.getAttribute("id");
		screenOption.helpText = option.getAttribute("helpText");
		screenOption.thumbnailId = option.getAttribute("thumbnailID");
		screenOption.voiceHelpId = option.getAttribute("voiceHelpID");
		return screenOption;
	}

	Screen getScreenFromDOMElement(Element option) {
		Screen screen = new Screen();
		screen.description = option.getAttribute("description");
		screen.id = option.getAttribute("id");
		return screen;
	}

	public String getSkeletonFromXML()
			throws ParserConfigurationException, SAXException, IOException {
		options = getOptions(Standard.optionsXML);
		screens = getScreens(Standard.screensXML);
		connectScreenButtons(Standard.skeletonXML);
		return null;
	}
}
