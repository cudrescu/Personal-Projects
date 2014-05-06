package Client;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;

import MyUtils.Users.User;


public class StudentMainWindow extends Window {

	public StudentMainWindow(Socket socket, DataInputStream dis, DataOutputStream dos,
			ObjectInputStream ois, ObjectOutputStream oos) {
		super(socket, dis, dos, ois, oos);
		
	}

	
}
