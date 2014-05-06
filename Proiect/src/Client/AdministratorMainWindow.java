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


public class AdministratorMainWindow extends Window {
	JButton bGestiuneStud, bGestiuneProf, bGestiuneCourses;

	public AdministratorMainWindow(Socket socket,  DataInputStream dis, DataOutputStream dos,
			ObjectInputStream ois, ObjectOutputStream oos) {
		super(socket, dis, dos, ois, oos);

		bGestiuneStud = new JButton("Gestiune studenti");
		bGestiuneStud.setToolTipText("Gestiune studenti");
		bGestiuneStud.setLocation(new Point(253, 20));
		bGestiuneStud.setSize(bGestiuneStud.getPreferredSize());
		panel.add(bGestiuneStud);
		
		bGestiuneProf = new JButton("Gestiune profesori");
		bGestiuneProf.setToolTipText("Gestiune profesori");
		bGestiuneProf.setLocation(new Point(390, 20));
		bGestiuneProf.setSize(bGestiuneProf.getPreferredSize());
		panel.add(bGestiuneProf);
		
		bGestiuneCourses = new JButton("Gestiune cursuri");
		bGestiuneCourses.setToolTipText("Gestiune cursuri");
		bGestiuneCourses.setLocation(new Point(532, 20));
		bGestiuneCourses.setSize(bGestiuneCourses.getPreferredSize());
		panel.add(bGestiuneCourses);
	}

	
}
