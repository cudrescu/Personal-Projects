package MyUtils;

import MyUtils.Users.Professor;

public class Course {
	private int id;
	private String subject;
	Professor owner;
	
	public Course(int i, String m, Professor p){
		i = id;
		setSubject(m);
		owner = p;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
