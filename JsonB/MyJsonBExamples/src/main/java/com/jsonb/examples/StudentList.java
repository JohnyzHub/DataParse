package com.jsonb.examples;

import java.util.ArrayList;
import java.util.List;

public class StudentList {

	private List<Student> students;

	public List<Student> getStudents() {
		if (students == null || students.size() == 0) {
			students = new ArrayList<Student>(0);
		}
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = new ArrayList<Student>(students);
	}

	@Override
	public String toString() {
		return "StudentList [students=" + students + "]";
	}

}
