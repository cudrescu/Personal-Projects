package Client;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorWindow extends JFrame {
	JFrame frame;
	String message;
	JLabel label;
	JButton bOK;
	
	public ErrorWindow(String m){
		
		frame = this;
		
		this.setSize(200, 150);
		this.setLocationRelativeTo(null); // pentru centrare
		this.setResizable(false); // neredimensionabil
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Error");
	
	
		message = m;
		JPanel panel = new JPanel();
		panel.setLayout(null);
		this.add(panel);
		
		label = new JLabel(message);
		label.setLocation(getWidth() / 2 - label.getPreferredSize().width / 2, 30);
		label.setSize(label.getPreferredSize());
		panel.add(label);
		
		bOK = new JButton("OK");
		bOK.setToolTipText("Log in button");
		bOK.setLocation(new Point(this.getWidth()/2 - bOK.getPreferredSize().width / 2, 70));
		bOK.setSize(bOK.getPreferredSize());
		panel.add(bOK);
		
		this.setVisible(true);
		
		ListenerButton listenButton = new ListenerButton();
		bOK.addActionListener(listenButton);
	}
	private class ListenerButton implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == bOK){
				frame.setVisible(false);
				frame.dispose();
			}
		}
	}
}

