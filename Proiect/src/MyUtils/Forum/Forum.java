package MyUtils.Forum;

import java.io.Serializable;
import java.util.ArrayList;

public class Forum implements Serializable {

	private ArrayList<ForumThread> threads;
	
	public Forum(){
		threads = new ArrayList<ForumThread>();
	}

	public ArrayList<ForumThread> getThreads() {
		return threads;
	}

	public void addThread(ForumThread ft) {
		threads.add(ft);
	}

	public void setThreads(ArrayList<ForumThread> threads) {
		this.threads = threads;
	}
}
