package MyUtils.Users;

import java.util.ArrayList;

import MyUtils.Course;
import MyUtils.Pair;


public class Professor extends User{
	
	ArrayList<Pair<Course, Student>> students;
	
	public Professor(String u, String p) {
		super(u, p);
		
		type = UserEnum.PROFESSOR;
	}

}
