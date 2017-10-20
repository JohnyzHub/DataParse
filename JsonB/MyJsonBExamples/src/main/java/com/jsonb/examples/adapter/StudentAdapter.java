package com.jsonb.examples.adapter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

import com.jsonb.examples.Student;

public class StudentAdapter implements JsonbAdapter<Student, JsonObject> {

	@Override
	public JsonObject adaptToJson(Student student) throws Exception {
		return Json.createObjectBuilder().add("id", student.getNumber()).add("firstName", student.getFirstName())
				.add("lastName", student.getLastName()).add("middleName", student.getMiddleName())
				.add("grade", student.getGrade()).build();
	}

	@Override
	public Student adaptFromJson(JsonObject jsonObject) throws Exception {
		Student student = new Student(jsonObject.getInt("id"), jsonObject.getString("firstName"),
				jsonObject.getString("lastName"), jsonObject.getString("middleName"), jsonObject.getString("grade"));

		return student;
	}

}
