package com.dom.examples.parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParserExample {

	public static void main(String[] args) {

		System.out.println("..............VALID FILE........................");
		File validXmlOrderFile = Paths.get("src/main/resources/order.xml").toFile();
		if (!validXmlOrderFile.exists()) {
			System.out.println("validOrder.xml doesn't exist");
			return;
		}
		DomParserExample domParse = new DomParserExample();
		domParse.domParseFile(validXmlOrderFile);

		System.out.println("..............INVALID FILE........................");

		File invalidXmlOrderFile = Paths.get("src/main/resources/invalidOrder.xml").toFile();
		if (!invalidXmlOrderFile.exists()) {
			System.out.println("invalidOrder.xml doesn't exist");
			return;
		}
		domParse.domParseFile(invalidXmlOrderFile);

	}

	public void domParseFile(File xmlOrderFile) {
		File xmlSchemaFile = Paths.get("src/main/resources/order.xsd").toFile();
		if (!xmlSchemaFile.exists()) {
			System.out.println("order.xsd doesn't exist");
			return;
		}

		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		Document document = null;
		try {
			Schema schema = schemaFactory.newSchema(xmlSchemaFile);
			docBuilderFactory.setSchema(schema);
			DocumentBuilder documentBuilder = docBuilderFactory.newDocumentBuilder();
			documentBuilder.setErrorHandler(new DomParsingErrorHandler());
			document = documentBuilder.parse(xmlOrderFile);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (document == null)
			return;

		NodeList orderLinesNode = document.getElementsByTagName("order_line");
		int nodesLength = orderLinesNode.getLength();
		List<OrderLine> orderLineCollection = new ArrayList<OrderLine>(0);
		for (int index = 0; index < nodesLength; index++) {
			Element orderLineNode = (Element) orderLinesNode.item(index);
			OrderLine orderLine = new OrderLine();
			orderLine.setItemName(orderLineNode.getAttribute("item"));
			orderLine.setQuantity(Integer.valueOf(orderLineNode.getAttribute("quantity")));
			Node unitPriceNode = orderLineNode.getChildNodes().item(1);
			orderLine.setUnitPrice(Double.valueOf(unitPriceNode.getFirstChild().getNodeValue()));
			orderLineCollection.add(orderLine);
		}

		System.out.println("OrderLines: " + orderLineCollection);
	}

}
