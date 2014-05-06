package MyUtils;

import java.io.Serializable;

public class Pair<T, V> implements Serializable {
	public final static int PORT = 10002;
	public static final String SERVER_ADDRESS = "127.0.0.1";
	
	private T first;
	private V second;
	
	public Pair(T t, V v){
		setFirst(t);
		setSecond(v);
	}

	public T getFirst() {
		return first;
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public V getSecond() {
		return second;
	}

	public void setSecond(V second) {
		this.second = second;
	}
	
	
}
