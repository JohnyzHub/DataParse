package com.dom.examples.parser;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DomParsingErrorHandler implements ErrorHandler {

	@Override
	public void error(SAXParseException arg0) throws SAXException {
		this.throwEx(arg0);

	}

	@Override
	public void fatalError(SAXParseException arg0) throws SAXException {
		this.throwEx(arg0);

	}

	@Override
	public void warning(SAXParseException arg0) throws SAXException {
		this.throwEx(arg0);

	}

	private void throwEx(SAXParseException arg0) throws SAXException {
		System.out.println(arg0.getMessage());
		throw arg0;
	}

}
