package Assignment02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*import com.mysql.cj.result.Row;*/

public class RegistrationSwingPractical02 implements ActionListener {
	JLabel l1, l2, l3, l4, l5;
	JTextArea t1, t2, t3, t4;
	JRadioButton jr1, jr2;
	JButton b1, b2, b3, b4, b5 ,b6;
//	private static JTextField textid;
//	private static JTextField textname;
//	private static JTextField textgender;
//	private static JTextField textaddress;
//	private static JTextField textcontact;

	JTable table1 = new JTable();
	Object[] columns = { "ID", "NAME", "GENDER", "ADDRESS", "CONTACTNO" };
	// DefaultTableModel tableModel = new DefaultTableModel();
	// Object[] row = new Object[5];

	public RegistrationSwingPractical02() {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(800, 500);
		frame.setLayout(null);

		l1 = new JLabel("ID:");
		l1.setBounds(50, 60, 100, 30);
		frame.add(l1);

		l2 = new JLabel("NAME:");
		l2.setBounds(50, 100, 100, 30);
		frame.add(l2);

		l3 = new JLabel("GENDER:");
		l3.setBounds(50, 140, 100, 30);
		frame.add(l3);

		l4 = new JLabel("ADDRESS:");
		l4.setBounds(50, 180, 100, 30);
		frame.add(l4);

		l5 = new JLabel("CONTACTNO:");
		l5.setBounds(50, 230, 100, 30);
		frame.add(l5);

		t1 = new JTextArea();
		t1.setBounds(120, 60, 150, 20);
		frame.add(t1);

		t2 = new JTextArea();
		t2.setBounds(120, 100, 150, 20);
		frame.add(t2);

		jr1 = new JRadioButton("Male");
		jr1.setBounds(110, 140, 150, 30);
		frame.add(jr1);

		jr2 = new JRadioButton("Female");
		jr2.setBounds(260, 140, 150, 30);
		frame.add(jr2);

		t3 = new JTextArea();
		t3.setBounds(120, 185, 150, 20);
		frame.add(t3);

		t4 = new JTextArea();
		t4.setBounds(135, 235, 150, 20);
		frame.add(t4);

		b1 = new JButton("Exit");
		b1.setBounds(70, 300, 130, 20);
		frame.add(b1);

		b2 = new JButton("Register");
		b2.setBounds(220, 300, 130, 20);
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String data[] = { t1.getText(), t2.getText(), jr2.getText(), t3.getText(), t4.getText() };
				DefaultTableModel model = (DefaultTableModel) table1.getModel();
				model.addRow(data);
				model.setColumnIdentifiers(columns);
				table1.setModel(model);
			}
		});
		frame.add(b2);

		b3 = new JButton("Delete");
		b3.setBounds(70, 350, 130, 20);
		frame.add(b3);

		b4 = new JButton("Update");
		b4.setBounds(220, 350, 130, 20);
		frame.add(b4);

		b5 = new JButton("Reset");
		b5.setBounds(70, 400, 130, 20);
		frame.add(b5);
		
		b6 = new JButton("Search");
		b6.setBounds(220, 400, 130, 20);
		frame.add(b6);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(jr1);
		buttonGroup.add(jr2);

		table1.setBackground(Color.white);
		table1.setForeground(Color.black);
		table1.setSelectionBackground(Color.red);
		table1.setGridColor(Color.red);
		table1.setSelectionForeground(Color.white);
		table1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		table1.setRowHeight(100);
		table1.setAutoCreateRowSorter(true);

		JScrollPane jPane = new JScrollPane(table1);
		jPane.setBackground(Color.red);
		jPane.setForeground(Color.white);
		jPane.setBounds(550, 80, 500, 60);
		frame.getContentPane().add(jPane);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
	}

	public static Connection createConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/swingdemo", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		new RegistrationSwingPractical02();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub
		if (e.getSource() == b2) {
			int id = Integer.parseInt(t1.getText());
			String name = t2.getText();
			String gender = "";
			if (jr1.isSelected()) {
				gender = jr1.getText();
			} else {
				gender = jr2.getText();
			}
			String address = t3.getText();
			Long contactno = Long.parseLong(t4.getText());

			try {
				Connection connection = RegistrationSwingPractical02.createConnection();
				String query = "insert into assign_user (id,name,gender,address,contact) values (?,?,?,?,?)";
				PreparedStatement pStatement = connection.prepareStatement(query);
				pStatement.setInt(1, id);
				pStatement.setString(2, name);
				pStatement.setString(3, gender);
				pStatement.setString(4, address);
				pStatement.setLong(5, contactno);
				pStatement.executeUpdate();
				new UserRegistered02();
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				jr1.setSelected(false);
				jr2.setSelected(false);
				System.out.println("Data Inserted..");
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		} else if (e.getSource() == b3) {
			System.out.println("Delete button Clicked..");
			int id = Integer.parseInt(t1.getText());
			try {
				Connection connection = RegistrationSwingPractical02.createConnection();
				String sql = "delete from assign_user where id = ?";
				PreparedStatement pst = connection.prepareStatement(sql);
				pst.setInt(1, id);
				pst.executeUpdate();
				System.out.println("Data is Deleted..");
				t1.setText("");
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		} else if (e.getSource() == b4) {
			System.out.println("Update button is Clicked");
			int id = Integer.parseInt(t1.getText());
			String name = t2.getText();
			String gender = "";
			if (jr1.isSelected()) {
				gender = jr1.getText();
			} else {
				gender = jr2.getText();
			}
			String address = t3.getText();
			Long contactno = Long.parseLong(t4.getText());
			
			try {
				Connection connection = RegistrationSwingPractical02.createConnection();
				String sql = "update assign_user set name = ?,gender = ?,address = ?,contact = ? where id = ?";
				PreparedStatement pst = connection.prepareStatement(sql);
				pst.setString(1, name);
				pst.setString(2, gender);
				pst.setString(3, address);
				pst.setLong(4, contactno);
				pst.setInt(5, id);
				pst.executeUpdate();
				System.out.println("Data is Updated");
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		} else if (e.getSource() == b5) {
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			jr1.setSelected(false);
			jr2.setSelected(false);
		}else if (e.getSource() == b1) {
			System.exit(1);
		}else if (e.getSource() == b6) {
			int id = Integer.parseInt(t1.getText());
			try {
				Connection connection =RegistrationSwingPractical02.createConnection();
				String sql = "select * from assign_user where id = ?";
				PreparedStatement pStatement = connection.prepareStatement(sql);
				pStatement.setInt(1, id);
				ResultSet rSet = pStatement.executeQuery();
				if(rSet.next()) {
					t2.setText(rSet.getString("name"));
					t3.setText(rSet.getString("address"));
					t4.setText(rSet.getString("contact"));
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

	}
}
