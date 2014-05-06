package MyUtils.Forum;

import java.util.Date;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ForumEntry implements Serializable {
	
	private String name;
	private String post;
	private String date;
	
	public ForumEntry() {
		super();
	}

	public ForumEntry(String materie, String name, String post){
		this.name = "[" + materie + "] " + name;
		this.post = post;
		date = getDateTime();
	}
	
	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Date date = new Date();
	    return dateFormat.format(date);
	    
	    //Calendar rightNow = Calendar.getInstance();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
