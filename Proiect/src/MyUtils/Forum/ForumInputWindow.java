package MyUtils.Forum;

import java.awt.Font;
import java.awt.Point;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Client.Window;
import MyUtils.Pair;

public class ForumInputWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8336897244839839743L;
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	JPanel panel;
	JLabel labelName, labelContains;
	JButton bcancel, baccept;
	JTextField ThreadTitle, ThreadText;
	
	Window main;

	public ForumInputWindow(Socket socket, DataInputStream dis, DataOutputStream dos,
			ObjectInputStream ois, ObjectOutputStream oos, Window main){
		
		this.socket = socket;
		
		this.dis = dis;
		this.dos = dos;
		this.ois = ois;
		this.oos = oos;
		
		this.main = main;
		
		this.setSize(480, 240);
		this.setLocationRelativeTo(null); 
		this.setResizable(false);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Thread Details");
		
		panel = new JPanel();
		panel.setLayout(null);
		this.add(panel);

		// LABEL
		labelName = new JLabel("Enter Thread Subject");
		labelName.setToolTipText("Topic");
		labelName.setLocation(170, 20);
		labelName.setSize(labelName.getPreferredSize());
		panel.add(labelName);
		
		// Text fields
		ThreadTitle = new JTextField("Subject", 14);
		ThreadTitle.setToolTipText("Subject");
		ThreadTitle.setLocation(new Point(50, 50));
		ThreadTitle.setSize(400, 20);
		panel.add(ThreadTitle);
		
		ThreadText = new JTextField("Wall of Text", 34);
		ThreadText.setToolTipText("Wall of Text");
		ThreadText.setLocation(new Point(50, 80));
		ThreadText.setSize(400,80);
		panel.add(ThreadText);
		

		// BUTONS
		baccept= new JButton("Accept");
		baccept.setToolTipText("Submit thread");
		baccept.setLocation(340, 180);
		baccept.setSize(100,20);
		panel.add(baccept);
		
		bcancel = new JButton("Cancel");
		bcancel.setToolTipText("Cancel thread");
		bcancel.setLocation(40, 180);
		bcancel.setSize(100,20);
		panel.add(bcancel);
		
		this.setVisible(true); 
	
		ListenerButton listenerButton = new ListenerButton(this);
		bcancel.addActionListener(listenerButton);
		baccept.addActionListener(listenerButton);

		ListenFocus focusListener = new ListenFocus();
		ThreadTitle.addFocusListener(focusListener);
		ThreadText.addFocusListener(focusListener);
		
	}

	private class ListenerButton implements ActionListener{
		
		private ForumInputWindow fereastra;
		
		ListenerButton(ForumInputWindow fiw){
			
			fereastra = fiw;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == baccept){
				try {
					oos.writeObject(new Pair<String, String>(ThreadTitle.getText(), ThreadText.getText()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				main.addForumThread(ThreadTitle.getText());
				
				/*int response = -2;
				try {
					response = dis.readInt();
				} catch (IOException e1) {
					e1.printStackTrace();
				}*/
			}
			
			else{
				fereastra.setVisible(false);
				fereastra.dispose();
			}
		}
	}
	
	private class ListenFocus implements FocusListener{

		@Override
		public void focusGained(FocusEvent e) {
			if(e.getSource() == ThreadTitle &&
					ThreadTitle.getText().equals("Subject"))
				ThreadTitle.setText("");
			if(e.getSource() == ThreadText &&
					ThreadText.getText().equals("Wall of Text"))
				ThreadText.setText("");
		}

		@Override
		public void focusLost(FocusEvent e) {}
	}
	
	
}
