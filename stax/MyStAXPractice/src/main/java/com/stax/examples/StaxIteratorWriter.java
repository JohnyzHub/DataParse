package com.stax.examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StaxIteratorWriter {

	public static void main(String[] args) {
		new StaxIteratorWriter().performSTAXIteratorWriter();

	}

	public Map<String, String> getElementsMap() {
		Map<String, String> elementsMap = new HashMap<String, String>(0);
		elementsMap.put("name", "Employee1");
		elementsMap.put("age", "30");
		elementsMap.put("gender", "Male");
		elementsMap.put("role", "Programmer");
		return elementsMap;
	}

	public void performSTAXIteratorWriter() {
		File xmlFile = Paths.get("src/main/resources/generated/employee.xml").toFile();
		if (xmlFile.exists()) {
			xmlFile.delete();
			System.out.println("Existing file Deleted");
		}
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		try {
			XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(xmlFile), "UTF-8");
			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
			StartDocument startDocument = eventFactory.createStartDocument();
			XMLEvent endEvent = eventFactory.createDTD("\n");
			eventWriter.add(startDocument);
			eventWriter.add(endEvent);

			StartElement startElement = eventFactory.createStartElement("", "", "Employee");
			eventWriter.add(startElement);
			Attribute attribute = eventFactory.createAttribute("id", "1.0");
			eventWriter.add(attribute);
			eventWriter.add(endEvent);

			Map<String, String> elementsMap = this.getElementsMap();
			Set<String> elementSet = elementsMap.keySet();
			for (String element : elementSet) {
				this.createNode(eventWriter, element, elementsMap.get(element));
			}

			EndElement endElement = eventFactory.createEndElement("", "", "Employee");
			eventWriter.add(endElement);
			eventWriter.add(endEvent);
			EndDocument endDocument = eventFactory.createEndDocument();
			eventWriter.add(endDocument);
			eventWriter.close();
			System.out.println("File generated: " + xmlFile.getAbsolutePath());
		} catch (FileNotFoundException | XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createNode(XMLEventWriter eventWriter, String element, String value) throws XMLStreamException {
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent tab = eventFactory.createDTD("\t");
		XMLEvent end = eventFactory.createDTD("\n");

		StartElement startElement = eventFactory.createStartElement("", "", element);
		Characters elementValue = eventFactory.createCharacters(value);
		eventWriter.add(tab);
		eventWriter.add(startElement);
		eventWriter.add(elementValue);

		EndElement endElement = eventFactory.createEndElement("", "", element);
		eventWriter.add(endElement);
		eventWriter.add(end);

	}

}
