package Client;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MyUtils.Pair;

public class LoginWindow extends JFrame {
	
	LoginWindow fereastraLogin;
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	JPanel panel;
	JLabel labelLogin, labelWelcome;
	JButton button;
	JTextField textUsername, textPassword, textCheat;
	
	protected LoginWindow(Socket socket, DataInputStream dis, DataOutputStream dos,
								ObjectInputStream ois, ObjectOutputStream oos){
		
		this.socket = socket;
		
		this.dis = dis;
		this.dos = dos;
		this.ois = ois;
		this.oos = oos;
		
		fereastraLogin = this;
		
		this.setSize(800, 480);
		
		// FEREASTRA
		this.setLocationRelativeTo(null); // pentru centrare
		this.setResizable(false); // neredimensionabil
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Log in");

		// PANOU
		panel = new JPanel();
		panel.setLayout(null);
		this.add(panel);
		
		// LABEL
		labelWelcome = new JLabel("Welcome to EduSoft");
		labelWelcome.setToolTipText("Welcome back");
		labelWelcome.setLocation(270, 50);
		labelWelcome.setFont(new Font("font1", Font.PLAIN, 30));
		labelWelcome.setSize(labelWelcome.getPreferredSize());
		panel.add(labelWelcome);
		
		labelLogin = new JLabel("Log in");
		labelLogin.setToolTipText("Log in screen");
		labelLogin.setLocation(new Point(this.getWidth()/2 - labelLogin.getPreferredSize().width / 2, 140));
		labelLogin.setSize(labelLogin.getPreferredSize());
		panel.add(labelLogin);
		
		// TEXT FIELD
		
		// nu stiu cum sa fac altfel
		textCheat = new JTextField("textCheat", 14);
		textCheat.setToolTipText("textCheat");
		textCheat.setLocation(new Point(10, -20));
		textCheat.setSize(textCheat.getPreferredSize());
		textCheat.requestFocus();
		panel.add(textCheat);
		
		textUsername = new JTextField("Username", 14);
		textUsername.setToolTipText("Username");
		textUsername.setLocation(new Point(this.getWidth()/2 - textUsername.getPreferredSize().width / 2, 180));
		textUsername.setSize(textUsername.getPreferredSize());
		panel.add(textUsername);
		
		textPassword = new JTextField("Password", 14);
		textPassword.setToolTipText("Password");
		textPassword.setLocation(new Point(this.getWidth()/2 - textPassword.getPreferredSize().width / 2, 200));
		textPassword.setSize(textPassword.getPreferredSize());
		panel.add(textPassword);
		

		// BUTON
		button = new JButton("Log in");
		button.setToolTipText("Log in button");
		button.setLocation(new Point(this.getWidth()/2 - button.getPreferredSize().width / 2, 250));
		button.setSize(button.getPreferredSize());
		panel.add(button);
		
		
		this.setVisible(true); // necesara pentru a fi vizibila
		
		ListenerButton listenerButton = new ListenerButton();
		button.addActionListener(listenerButton);

		ListenFocus focusListener = new ListenFocus();
		textUsername.addFocusListener(focusListener);
		textPassword.addFocusListener(focusListener);
		
	}

	
	private class ListenerButton implements ActionListener{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == button){
				try {
					oos.writeObject(new Pair<String, String>(textUsername.getText(), textPassword.getText()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				int response = -2;
				try {
					response = dis.readInt();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				switch(response) {
					case 0: // Student
						System.out.println("[Client] Logged in as student");
						fereastraLogin.setVisible(false);
						fereastraLogin.dispose();
						
						new StudentMainWindow(socket, dis, dos, ois, oos);
						break;
					case 1: // Professor
						System.out.println("[Client] Logged in as professor");
						fereastraLogin.setVisible(false);
						fereastraLogin.dispose();
						
						new ProfessorMainWindow(socket, dis, dos, ois, oos);
						break;
					case 2: // Administrator
						System.out.println("[Client] Logged in as administrator");
						fereastraLogin.setVisible(false);
						fereastraLogin.dispose();
						
						new AdministratorMainWindow(socket, dis, dos, ois, oos);
						break;
					case -1:
						new ErrorWindow("Incorect username or password");
						break;
					default:
						System.out.println("[Client] Error reading server login response");
				}
			}
		}
	}

	private class ListenFocus implements FocusListener{

		@Override
		public void focusGained(FocusEvent e) {
			if(e.getSource() == textUsername &&
					textUsername.getText().equals("Username"))
				textUsername.setText("");
			if(e.getSource() == textPassword &&
					textPassword.getText().equals("Password"))
				textPassword.setText("");
		}

		@Override
		public void focusLost(FocusEvent e) {}
	}
	
}

