package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	final static int PORT = MyUtils.Pair.PORT;
	final static String SERVER_ADDRESS = MyUtils.Pair.SERVER_ADDRESS;
	static LoginWindow loginWindow;
	static Window mainWindow;
	
	public static void main(String[] args) {
		
		Socket socket = null;
		
		try {
			socket = new Socket(SERVER_ADDRESS, PORT);
			System.out.println("[Client] Created connection with " + SERVER_ADDRESS + " on port " + PORT);
		} catch (IOException e) {
			System.out.print("[Client] Error creating connection : ");
			e.printStackTrace();
		}

		DataInputStream dis = null;
		DataOutputStream dos = null;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;

		try {
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			//System.out.print("*");
			
			//System.out.print("*");
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			//System.out.print("*");
			
			//System.out.println("*");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		loginWindow = new LoginWindow(socket, dis, dos, ois, oos);
		
	}
	
	

	
}
