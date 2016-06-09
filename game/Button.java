package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class Button extends JFrame implements ActionListener {

	private JLabel label;
	private JTextField name;
	private JButton click;
	private String storeName;
	private boolean val;
	
	public Button() {
		
		val = false;
		setLayout(null);
		setSize(300,300);
		System.out.println("code");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label = new JLabel ("Enter Name:");
		click = new JButton ("Click");
		name = new JTextField();
		label.setBounds(60, 30, 120, 30);
		name.setBounds(	80, 60, 130, 30);
		click.setBounds(100, 190, 100, 40);
		click.addActionListener(this);
		add(click);
		add(name);
		add(label);
	}
		
	public void actionPerformed (ActionEvent e) {
//		System.out.println("button pressed!");
////		JFrame frame = new JFrame("click");
////		frame.setVisible(true);
////		frame.setSize(300,300);
//		JLabel label = new JLabel(" ");
//		JPanel panel = new JPanel();
//		//frame.add(panel);
//		panel.add(label);
		
		if(e.getSource() == click) {
			storeName = name.getText();
			val = true;
			System.out.println("Click");
		}

	}
	
	public boolean getVal()
	{
		return val;
	}
	
}




