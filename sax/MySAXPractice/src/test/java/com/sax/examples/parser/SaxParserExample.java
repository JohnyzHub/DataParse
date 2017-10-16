package com.sax.examples.parser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class SaxParserExample {

	public static void main(String[] args) {
		try {
			new SaxParserWithInvalideFile().parseOrderXML();
		} catch (SAXException | ParserConfigurationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
