package MyUtils.Users;

import java.util.ArrayList;

import MyUtils.Course;

public class User {
	private int id;
	protected UserEnum type;
	protected ArrayList<Course> courses;
	String username;
	String password;
	
	public User(String u, String p){
		username = u;
		password = p;
	}
	
	public UserEnum getType(){
		return type;
	}
	
	public ArrayList<Course> getCourses(){
		return courses;
	}
}
