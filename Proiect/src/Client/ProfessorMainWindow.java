package Client;

import java.awt.Point;
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


public class ProfessorMainWindow extends Window {
	JButton bGestiuneStud;
	
	public ProfessorMainWindow(Socket socket, DataInputStream dis, DataOutputStream dos,
			ObjectInputStream ois, ObjectOutputStream oos) {
		super(socket, dis, dos, ois, oos);
		
		bGestiuneStud = new JButton("Gestiune studenti");
		bGestiuneStud.setToolTipText("Gestiune studenti");
		bGestiuneStud.setLocation(new Point(253, 20));
		bGestiuneStud.setSize(bGestiuneStud.getPreferredSize());
		panel.add(bGestiuneStud);
		
	}

	
}
