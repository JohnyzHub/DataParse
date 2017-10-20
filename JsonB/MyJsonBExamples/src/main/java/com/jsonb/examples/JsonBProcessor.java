package com.jsonb.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.JsonbException;
import javax.json.bind.config.PropertyOrderStrategy;

import com.jsonb.examples.adapter.StudentAdapter;

public class JsonBProcessor {

	public static void main(String[] args) {

		Student student1 = new Student(1, "FirstName1", "LastName1", "MiddleName1", "Graduation");
		Student student2 = new Student(2, "FirstName2", "LastName2", "MiddleName2", "Post-Graduation");
		List<Student> studentsList = new ArrayList<Student>(Arrays.asList(student1, student2));

		JsonbConfig jsonConfig = new JsonbConfig().withPropertyOrderStrategy(PropertyOrderStrategy.LEXICOGRAPHICAL)
				.withFormatting(true);
		Jsonb jsonB = JsonbBuilder.create(jsonConfig);
		String jsonStudents = jsonB.toJson(studentsList);
		System.out.println("-------Generic Collection--------");
		System.out.println("Serialized List: " + jsonStudents);

		List<Student> resultedList = jsonB.fromJson(jsonStudents, new ArrayList<Student>() {
			private static final long serialVersionUID = 1L;
		}.getClass().getGenericSuperclass());
		System.out.println("Deserialized List: " + resultedList);

		System.out.println("-------StudentList Object--------");
		StudentList studentList = new StudentList();
		studentList.setStudents(studentsList);

		// Write to console
		String studentListJson = jsonB.toJson(studentList);
		System.out.println("Serialized List: " + studentListJson);

		// write to file
		File jsonFile = getFile();
		try (OutputStream outStream = new FileOutputStream(jsonFile)) {
			jsonB.toJson(studentList, outStream);
		} catch (JsonbException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StudentList resultedStudentList = jsonB.fromJson(studentListJson, StudentList.class);
		System.out.println("Deserialized List: " + resultedStudentList);

		JsonbConfig jsonAdaptConfig = new JsonbConfig().withAdapters(new StudentAdapter()).withFormatting(true);
		Jsonb jsonBAdapt = JsonbBuilder.create(jsonAdaptConfig);
		File adapterFile = getAdapterFile();
		try (OutputStream outStream = new FileOutputStream(adapterFile)) {
			jsonBAdapt.toJson(studentList, outStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String studentsListWithAdapter = jsonBAdapt.toJson(studentList);
		StudentList resultWithAdapter = jsonBAdapt.fromJson(studentsListWithAdapter, StudentList.class);
		System.out.println("Deserialized List with Adapter: " + resultWithAdapter);
	}

	public static File getFile() {
		File jsonFile = Paths.get("src/main/resources/students.json").toFile();
		if (jsonFile.exists()) {
			jsonFile.delete();
		}
		return jsonFile;
	}

	public static File getAdapterFile() {
		File jsonFile = Paths.get("src/main/resources/WithAdapter_Students.json").toFile();
		if (jsonFile.exists()) {
			jsonFile.delete();
		}
		return jsonFile;
	}

}
