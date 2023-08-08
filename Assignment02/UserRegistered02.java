package Assignment02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserRegistered02 implements ActionListener{
	JLabel l1;
	JButton b1;
	
	UserRegistered02(){
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(700, 500);
		frame.setLayout(null);
		
		l1 = new JLabel("Successfully Registered..");
		l1.setBounds(250, 200, 200, 30);
		frame.add(l1);
		
		b1 = new JButton("OK");
		b1.setBounds(270, 250, 120, 30);
		frame.add(b1);
		
		b1.addActionListener(this);
	}

	public static void main(String[] args) {
		new UserRegistered02();
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == b1) {
			new RegistrationSwingPractical02();
		}
	}

}
