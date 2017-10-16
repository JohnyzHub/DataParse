package com.stax.examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class StaxCursorWriter {

	public static void main(String[] args) {
		new StaxCursorWriter().performStaxCursorWriter();
	}

	public Map<String, String> getElementsMap() {
		Map<String, String> elementsMap = new HashMap<String, String>(0);
		elementsMap.put("name", "Employee1");
		elementsMap.put("age", "30");
		elementsMap.put("gender", "Male");
		elementsMap.put("role", "Programmer");
		return elementsMap;
	}

	public void performStaxCursorWriter() {
		File xmlFile = Paths.get("src/main/resources/generated/employee.xml").toFile();
		if (xmlFile.exists()) {
			xmlFile.delete();
			System.out.println("Existing file Deleted");
		}
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		Map<String, String> elementsMap = this.getElementsMap();
		Set<String> keySet = elementsMap.keySet();
		int keySize = keySet.size();

		try {
			XMLStreamWriter streamWriter = outputFactory.createXMLStreamWriter(new FileOutputStream(xmlFile), "UTF-8");
			streamWriter.writeStartDocument("UTF-8", "1.0");
			streamWriter.writeCharacters("\n");
			streamWriter.writeStartElement("Employee");
			streamWriter.writeAttribute("id", "1.0");

			for (String key : keySet) {
				streamWriter.writeCharacters("\n\t");
				this.createNode(streamWriter, key, elementsMap.get(key));
			}
			streamWriter.writeCharacters("\n");
			streamWriter.writeEndElement();
			streamWriter.writeEndDocument();

			streamWriter.flush();
			streamWriter.close();
			System.out.println("File generated: " + xmlFile.getAbsolutePath());
		} catch (FileNotFoundException | XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

	}

	public void createNode(XMLStreamWriter streamWriter, String key, String value) throws XMLStreamException {
		streamWriter.writeStartElement(key);
		streamWriter.writeCharacters(value);
		streamWriter.writeEndElement();
	}
}