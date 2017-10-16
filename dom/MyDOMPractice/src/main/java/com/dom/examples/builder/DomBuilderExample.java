package com.dom.examples.builder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class DomBuilderExample {

	public static void main(String[] args) {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		Document document = null;
		try {
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			document = docBuilder.newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (document == null) {
				System.out.println("Unable to create document");
				return;
			}
		}

		Element orderElement = document.createElement("order");
		orderElement.setAttribute("date", "05/06/2013");
		orderElement.setAttribute("id", "1234");
		document.appendChild(orderElement);

		Element contentElement = document.createElement("content");
		orderElement.appendChild(contentElement);

		Element orderLineElement = document.createElement("order_line");
		orderLineElement.setAttribute("item", "H2G2");
		orderLineElement.setAttribute("quantity", "1");
		Element unitPriceElement = document.createElement("unit_price");
		Text textNode = document.createTextNode("23.5");
		unitPriceElement.appendChild(textNode);
		orderLineElement.appendChild(unitPriceElement);
		contentElement.appendChild(orderLineElement);

		Element orderLineElement2 = document.createElement("order_line");
		orderLineElement2.setAttribute("item", "Matrix");
		orderLineElement2.setAttribute("quantity", "4");
		Element unitPriceElement2 = document.createElement("unit_price");
		Text textNode2 = document.createTextNode("37.99");
		unitPriceElement2.appendChild(textNode2);
		orderLineElement2.appendChild(unitPriceElement2);
		contentElement.appendChild(orderLineElement2);

		File xmlFile = new File("src/main/resources/generated/order.xml");
		if (xmlFile.exists()) {
			xmlFile.delete();
		}
		FileOutputStream fileOutStream = null;
		try {
			fileOutStream = new FileOutputStream(xmlFile);
			OutputFormat docOutputFormat = new OutputFormat("xml", "UTF-8", true);
			XMLSerializer serializer = new XMLSerializer(fileOutStream, docOutputFormat);
			serializer.serialize(document);

			StringWriter stringWriter = new StringWriter();
			serializer = new XMLSerializer(stringWriter, docOutputFormat);
			serializer.serialize(document);
			System.out.println(stringWriter.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}