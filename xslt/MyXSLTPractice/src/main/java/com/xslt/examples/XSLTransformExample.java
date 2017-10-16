package com.xslt.examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLTransformExample {

	public static void main(String[] args) {

		File xmlFile = Paths.get("src/main/resources/order.xml").toFile();
		File xslFile = Paths.get("src/main/resources/order.xsl").toFile();
		File htmlFile = new File("src/main/resources/generated/order.html");

		if (htmlFile.exists()) {
			htmlFile.delete();
		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer(new StreamSource(xslFile));
			StreamResult writeToFile = new StreamResult(new FileOutputStream(htmlFile));
			// Writing to File.
			transformer.transform(new StreamSource(xmlFile), writeToFile);

			// Write to console.
			StreamResult printHere = new StreamResult(System.out);
			transformer.transform(new StreamSource(xmlFile), printHere);

		} catch (TransformerException | FileNotFoundException e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
	}
}