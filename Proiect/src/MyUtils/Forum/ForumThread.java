package MyUtils.Forum;

import java.io.Serializable;
import java.util.ArrayList;

public class ForumThread implements Serializable {
	
	private ArrayList<ForumEntry> entries;
	private String name;
	
	public ForumThread() {
		super();
	}

	public ForumThread(String name){
		this.name = name;
		entries = new ArrayList<ForumEntry>();
	}

	public String getName() {
		return name;
	}

	public ArrayList<ForumEntry> getEntries() {
		return entries;
	}

	public void setEntries(ArrayList<ForumEntry> entries) {
		this.entries = entries;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
