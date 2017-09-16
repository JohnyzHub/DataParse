package com.jaxb.examples.myjaxbpractice.collection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class TestJAXBList {

	public static void main(String[] args) {
		Employee emp1 = new Employee();
		emp1.setName("JOHNY");
		emp1.setNumber(1);

		List<Employee> emps = new ArrayList<Employee>(1);
		emps.add(emp1);
		Employee emp2 = new Employee();
		emp2.setName("BASHA");
		emp2.setNumber(2);
		emp2.setEmployeeList(emps);

		List<Employee> emps1 = new ArrayList<Employee>(1);
		emps1.add(emp1);
		emps1.add(emp2);
		Employee emp3 = new Employee();
		emp3.setName("SHAIK");
		emp3.setNumber(3);
		emp3.setEmployeeList(emps1);

		Employees empss = new Employees();
		empss.getEmployees().add(emp3);
		empss.getEmployees().add(emp2);
		empss.getEmployees().add(emp1);
		JAXBContext context = null;
		Marshaller marshaller = null;
		try {

			Path path = Paths.get("resources\\Employees.txt");
			Files.deleteIfExists(path);
			context = JAXBContext.newInstance(Employees.class);
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(empss, System.out);
			marshaller.marshal(empss, new File("resources\\Employees.txt"));
		} catch (IOException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
