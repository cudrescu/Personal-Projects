package Client;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import MyUtils.*;
import MyUtils.Forum.Forum;
import MyUtils.Forum.ForumInputWindow;
import MyUtils.Forum.ForumThread;
import MyUtils.Forum.ForumEntry;
import MyUtils.Users.User;

public class Window extends JFrame {
	
	Window fereastra;
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	JPanel panel, centerPanel;
	JButton bLogOut, bCursuri, bCatalog, bForum;
	ArrayList<JButton> threadButtons = new ArrayList<JButton>();
	User user;
	
	public Window(Socket socket, DataInputStream dis, DataOutputStream dos,
						ObjectInputStream ois, ObjectOutputStream oos) {
		fereastra = this;
		this.socket = socket;

		this.dis = dis;
		this.dos = dos;
		this.ois = ois;
		this.oos = oos;
		
		this.setSize(1024, 768);
		this.setLocationRelativeTo(null); // pentru centrare
		this.setResizable(false); 		  // neredimensionabil
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("EduSoft");
		

		panel = new JPanel();
		panel.setLayout(null);
		this.add(panel);

		bLogOut = new JButton("Log out");
		bLogOut.setToolTipText("Log out button");
		bLogOut.setLocation(getWidth()-bLogOut.getPreferredSize().width - 20, 20);
		bLogOut.setSize(bLogOut.getPreferredSize());
		panel.add(bLogOut);

		bCursuri = new JButton("Cursuri");
		bCursuri.setToolTipText("Cursurile mele");
		bCursuri.setLocation(new Point(20, 20));
		bCursuri.setSize(bCursuri.getPreferredSize());
		panel.add(bCursuri);
		
		bCatalog = new JButton("Catalog");
		bCatalog.setToolTipText("Notele mele");
		bCatalog.setLocation(new Point(100, 20));
		bCatalog.setSize(bCatalog.getPreferredSize());
		panel.add(bCatalog);

		bForum = new JButton("Forum");
		bForum.setToolTipText("Forum");
		bForum.setLocation(new Point(180, 20));
		bForum.setSize(bForum.getPreferredSize());
		panel.add(bForum);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(null);
		centerPanel.setSize(980, 660);
		centerPanel.setLocation(20, 60);
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(centerPanel);

		this.setVisible(true);
		

		ListenerButton listenerButton = new ListenerButton(fereastra);
		bLogOut.addActionListener(listenerButton);
		bCursuri.addActionListener(listenerButton);
		bCatalog.addActionListener(listenerButton);
		bForum.addActionListener(listenerButton);
	}
	
	private void logout() {
		
		fereastra.setVisible(false);
		fereastra.dispose();
		
		try {
			dos.writeInt(1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("[Client] Logged out");
		
		new LoginWindow(socket, dis, dos, ois, oos);
		
	}
	
	protected void addCoursesToPanel(){
		System.out.println("Add courses to panel in fereastra");
		
		centerPanel.removeAll();
		
		centerPanel.validate();
		centerPanel.repaint();
	}
	
	protected void addGradesToPanel(){
		System.out.println("Add catalog to panel in fereastra");
		
		centerPanel.removeAll();
		
		centerPanel.validate();
		centerPanel.repaint();
		
	}
	
	protected void addForumToPanel(){
		System.out.println("Add forum to panel in fereastra");

		centerPanel.removeAll();
		
		try {
			dos.writeInt(2);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		ArrayList <ForumThread> forumthreads = null;
		
		try {
			forumthreads = (ArrayList <ForumThread>) ois.readObject();
			System.out.print("Primeste threaduri. ");
			System.out.println("Forum size :  " + forumthreads.size());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		int i = 20, j = 20;
		JButton button;
		
		button = new JButton("Add thread");
		button.setToolTipText("Add thread");
		button.setLocation(new Point(i, j));
		button.setSize(button.getPreferredSize());
		button.addActionListener(new ListenerButton(fereastra));
		centerPanel.add(button);
		
		for(JButton threadButton : threadButtons){
			centerPanel.add(threadButton);
		}
		
		centerPanel.validate();
		centerPanel.repaint();
	}
	
	private class ListenerButton implements ActionListener{
		
		Window fereastra;
		
		ListenerButton(Window fereastra){
			this.fereastra = fereastra;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == bLogOut)
				logout();
			
			else if(e.getSource() == bCursuri)
				addCoursesToPanel();
			
			else if(e.getSource() == bCatalog)
				addGradesToPanel();
			
			else if(e.getSource() == bForum)
				addForumToPanel();

			else if(e.getSource() instanceof JButton &&
					((JButton)e.getSource()).getText().equals("Add thread")){
				
				try {
					dos.writeInt(3);
					
					oos.writeObject(new ForumThread("Thread nou"));
					
					ForumInputWindow ftWindow = new ForumInputWindow(socket, dis, dos, ois, oos, fereastra);
					
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			
		}
		
		
	}

	public void addForumThread(String text) {
	
		JButton button;
		
		if(threadButtons.isEmpty()){
			
			button = new JButton(text);
			button.setToolTipText(text);
			button.setLocation(new Point(20, 60));
			button.setSize(940, 20);
			button.addActionListener(new ListenerButton(fereastra));
			centerPanel.add(button);
			
			threadButtons.add(button);
		}
		else{
			
			button = new JButton(text);
			button.setToolTipText(text);
			button.setLocation(new Point(20,threadButtons.get(threadButtons.size()-1).getY() + 20));
			button.setSize(940, 20);
			button.addActionListener(new ListenerButton(fereastra));
			centerPanel.add(button);
			
			threadButtons.add(button);
	
		}
		
		centerPanel.validate();
		centerPanel.repaint();

	}
}
