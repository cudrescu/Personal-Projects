package MyUtils.Users;

import java.util.ArrayList;

import MyUtils.Course;
import MyUtils.Pair;

public class Administrator extends User{
	
	ArrayList<Pair<Course, ArrayList<Student>>> students;
	ArrayList<Professor> professors;
	
	public Administrator(String u, String p) {
		super(u, p);
		
		type = UserEnum.ADMINISTRATOR;
	}
	
	
	public void addCourse(Course c){
		courses.add(c);
	}
}
