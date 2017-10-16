package com.xpath.examples;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MyXPathPractice {

	public static void main(String[] args) {

		try {

			Path path = Paths.get("src\\main\\resources\\order.xml");
			File file = path.toFile();
			if (file.exists()) {
				System.out.println("File: " + file.getName());
			} else {
				System.out.println("File doesn't exist.");
			}

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);

			XPath xPath = XPathFactory.newInstance().newXPath();

			String unitPrice_collection_expr = "//content/order_line/unit_price";
			NodeList unitPriceNodeList = (NodeList) xPath.evaluate(unitPrice_collection_expr, document,
					XPathConstants.NODESET);
			int size = unitPriceNodeList.getLength();
			for (int i = 0; i < size; i++) {
				Element unitPriceElement = (Element) unitPriceNodeList.item(i);

				System.out.println("UnitPrice: " + unitPriceElement.getTextContent());
			}

			String unitPrice_expr = "//content/order_line[unit_price<25]/unit_price";
			Double unitPrice = (Double) xPath.evaluate(unitPrice_expr, document, XPathConstants.NUMBER);
			System.out.println("\nUnit Price>25 : " + unitPrice);

			String order_name_expr = "//content/order_line[@item='Matrix']/unit_price";
			String orderName = (String) xPath.evaluate(order_name_expr, document, XPathConstants.STRING);
			System.out.println("\nUnit price for Matrix : " + orderName);

		} catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

	}

}
