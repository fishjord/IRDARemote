package com.fishjord.irwidget.ir.codes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

import com.fishjord.irwidget.R;

public class CodeManager {
	private static CodeManager codeManager;
	private final Map<String, Manufacturer> manufacturers = new LinkedHashMap();
	
	private CodeManager(Context c) throws IOException, XmlPullParserException {
		XmlResourceParser parser = c.getResources().getXml(R.xml.remotes);
		while(parser.next() != XmlResourceParser.START_TAG);
		parseManufacturers(parser);
		parser.close();
		
		Log.i(this.getClass().getCanonicalName(), "Loaded " + manufacturers.size() + " manufacturers from xml");
	}
	
	private void parseManufacturers(XmlResourceParser parser) throws IOException, XmlPullParserException {
		parser.require(XmlResourceParser.START_TAG, null, "manufacturers");
		while(parser.next() != XmlResourceParser.END_TAG) {
			if(parser.getEventType() == XmlResourceParser.START_TAG) {
				parseManufacturer(parser);
			}
		}
		parser.require(XmlResourceParser.END_TAG, null, "manufacturers");
	}
	
	private void parseManufacturer(XmlResourceParser parser) throws IOException, XmlPullParserException {
		parser.require(XmlResourceParser.START_TAG, null, "manufacturer");
		
		String name = parser.getAttributeValue(null, "name");
		List<IRButton> buttons = new ArrayList();
		
		while(parser.next() != XmlResourceParser.END_TAG){
			if(parser.getEventType() == XmlResourceParser.START_TAG) {
				buttons.add(parseCode(parser));
			}
		}
		manufacturers.put(name,  new Manufacturer(name, buttons));
		Log.d(this.getClass().getCanonicalName(), "Loaded " + buttons.size() + " codes for " + name);
		parser.require(XmlResourceParser.END_TAG, null, "manufacturer");
	}
	
	private IRButton parseCode(XmlResourceParser parser) throws IOException, XmlPullParserException {
		parser.require(XmlResourceParser.START_TAG, null, "code");
		
		String name = parser.getAttributeValue(null, "name");
		String display = parser.getAttributeValue(null, "display");
		String group = parser.getAttributeValue(null, "group");

		IRCommand command = ProntoParser.parsePronto(parser.nextText().trim());

		Log.d(this.getClass().getCanonicalName(), "Loaded " + name + ": " + command);
		parser.require(XmlResourceParser.END_TAG, null, "code");
		
		return new IRButton(name, display, group, command);
	}
	
	public Manufacturer getManufacturer(String manufacturer) {
		return manufacturers.get(manufacturer);
	}
	
	public static CodeManager getInstance(Context context) throws IOException, XmlPullParserException {
		if(codeManager == null) {
			codeManager = new CodeManager(context);
		}
		return codeManager;
	}
	
	public List<String> getManufacturers() {
		return new ArrayList<String>(manufacturers.keySet());
	}
	
	public static CodeManager getInstance() {
		return codeManager;
	}
}
