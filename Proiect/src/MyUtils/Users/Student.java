package MyUtils.Users;

import java.util.ArrayList;

import MyUtils.Course;
import MyUtils.Pair;

public class Student extends User {
	
	ArrayList<Pair<Course, ArrayList<Integer>>> grades;
	
	public Student(String u, String p) {
		super(u, p);
		
		type = UserEnum.STUDENT;
	}

}