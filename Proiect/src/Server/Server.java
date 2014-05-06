package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import MyUtils.Pair;
import MyUtils.Forum.Forum;

public class Server {
	
	final static int PORT = MyUtils.Pair.PORT;
	
	public static void main(String[] args){
		
		ServerSocket srvSocket = null;
		Socket socket = null;
		
		try {
			srvSocket = new ServerSocket(PORT);
			System.out.println("[Server] Opened socket on port " + PORT);
		} catch (IOException e) {
			System.out.print("[Server] Error opening socket : ");
			e.printStackTrace();
		}
		
		
	    
	    int id = 0;

		Forum forum = null;
		ObjectInputStream forumStream = null;
		FileInputStream f = null;
		
		try {
			
			f = new FileInputStream("forum.bin");
			try {
				forumStream = new ObjectInputStream(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				forum = (Forum) forumStream.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			System.out.println("[Server] Loaded forum from file");
		} catch (FileNotFoundException e1) {
			forum = new Forum();
			System.out.println("[Server] Created new forum file");
		}
		
		
	    while(true){
	    	try {
				socket = srvSocket.accept();
				System.out.println("[Server] Accepted connection on port " + PORT + " for client " + id);
			} catch (IOException e) {
				System.out.print("[Server] Error accepting socket : ");
				e.printStackTrace();
			}
	    	
	    	new Connection(id, socket, forum);
	    	id++;
	    }
	}
	
}
