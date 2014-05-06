package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import MyUtils.Pair;
import MyUtils.Forum.Forum;
import MyUtils.Forum.ForumThread;

public class Connection extends Thread {
	
	private int ID;
	private Socket socket;
	public Database.ProcessCommand prCom;
	Forum forum;
	
	public Connection(int id, Socket socket, Forum forum) {
		
		ID = id;
		this.socket = socket;
		this.forum = forum;
		//this.prCom = new Database.ProcessCommand();
		//prCom.start();
		this.start();
	}

	public int getID() {
		return ID;
	}
	

	@Override
	public void run() {
		
		DataInputStream dis = null;
		DataOutputStream dos = null;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		
		try {
			dis = new DataInputStream(socket.getInputStream());
			//System.out.print("*");
			dos = new DataOutputStream(socket.getOutputStream());
			//System.out.print("*");
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			//System.out.print("*");
			
			//System.out.println("*");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// Bucla principala
		while(true){
			
			// Bucla pentru logare
			while (true){
				Pair<String, String> o = null;
				try {
					o = (Pair<String, String>)ois.readObject();
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("[Server] Client " + ID + " connection closed");
					try {
						dis.close();
						dos.close();
						ois.close();
						oos.close();
						return;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					//e.printStackTrace();
				}
				
				// TODO verificare cu baza de date a utilizatorului
				if(o.getFirst().equals("Username") && o.getSecond().equals("Password")){
				//if(prCom.checkStudentRegistration(o.getFirst(), o.getSecond())){
					/* 0 - Student
					 * 1 - Professor
					 * 2 - Administrator
					 * */
					try {
						dos.writeInt(0);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("[Server] Client " + ID + " logged in");
					break;
				}
				try {
					dos.writeInt(-1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			int requestType = -1;
			
			// Bucla pentru fereastra principala
			while (true){
				// TODO tratarea cazurilor pentru diferitele cereri date de user
				
				try {
					requestType = dis.readInt();
				} catch (IOException e) {
					System.out.println("[Server] Client " + ID + " connection closed");
					try {
						dis.close();
						dos.close();
						ois.close();
						oos.close();
						return;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				System.out.println(requestType + "in connection");
				
				switch(requestType) {
					
					case 1 :
						System.out.println("[Server] Client " + ID + " logged out");
						break;
					case 2 : 
						try {
							System.out.println(forum.getThreads().size());
							oos.writeObject(forum.getThreads());
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case 3 : 
						try {
							ForumThread ft = (ForumThread) ois.readObject();
							forum.addThread(ft);
							System.out.println("[Server] Added forum thread with name : " + ft.getName() + ". Forum size :  " + forum.getThreads().size());
						} catch (ClassNotFoundException | IOException e) {
							e.printStackTrace();
						}
						break;
					default :
				}
				
				if(requestType == 1)
					break;
			}
		
		}
	}
}
