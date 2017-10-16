package com.sax.examples.parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParserWithInvalideFile extends DefaultHandler {

	private String elementName = "";
	private int nbTabs = 0;
	private StringBuffer stringBuffer = new StringBuffer();

	public void parseOrderXML() throws SAXException, ParserConfigurationException, IOException {
		File orderXML = Paths.get("src/main/resources/order.xml").toFile();
		if (!orderXML.exists()) {
			System.out.println("invalidOrder.xml doesn't exist");
			return;
		}

		File orderXSD = Paths.get("src/main/resources/order.xsd").toFile();
		if (!orderXSD.exists()) {
			System.out.println("order.xsd doesn't exist");
			return;
		}

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(orderXSD);
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		parserFactory.setSchema(schema);
		SAXParser saxParser = parserFactory.newSAXParser();
		saxParser.parse(orderXML, this);
		System.out.println(stringBuffer.toString());
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (!(localName == null || localName.isEmpty())) {
			elementName = localName;
		} else {
			elementName = qName;
		}

		stringBuffer.append(tabs() + elementName + "{\n");
		nbTabs++;
		if (attributes != null) {
			int attributesLength = attributes.getLength();
			for (int index = 0; index < attributesLength; index++) {
				stringBuffer.append(tabs() + attributes.getLocalName(index) + "=" + attributes.getValue(index) + "\n");
			}
		}
	}

	public void characters(char ch[], int start, int length) throws SAXException {
		stringBuffer.append(tabs() + new String(ch, start, length) + "\n");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		nbTabs--;
		stringBuffer.append(tabs() + "}\n");

	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		System.out.println(e.getMessage());
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println(e.getMessage());
		throw e;
	}

	private String tabs() {
		String tabs = "";
		for (int i = 0; i < nbTabs; i++) {
			tabs += "\t";
		}
		return tabs;
	}
}