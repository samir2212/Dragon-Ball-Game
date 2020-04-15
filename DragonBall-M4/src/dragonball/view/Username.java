package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Username extends JFrame{
	private userwindow user;
	private JTextField namee;
	private JComboBox box;
	private JButton worldButton;
	private JTextField fighterName;
	
	public Username(){
		user=new userwindow();
		user.setLayout(new FlowLayout());
		this.setTitle("Start a Game");
		//sthis.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(50, 50, 800, 500);
		JPanel panel1 = new JPanel();
		panel1.add(new JLabel("Enter your name: "));
		namee =new JTextField(20);
		panel1.add(namee);
		//create new fighter
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		String[] race = {"Earthling","Saiyan","Namekian","Frieza","Majin"};
		box = new JComboBox(race);
		worldButton = new JButton("World Mode");
		fighterName = new JTextField(15);
		JLabel label = new JLabel("Enter your fighter name :");
		panel2.add(label);
		panel2.add(fighterName);
		panel2.add(box);
		panel2.add(worldButton);
	
		user.add(panel1);
		user.add(panel2);
		this.add(user);
		
	}
	public static void main(String[]args){
		Username a=new Username();
	}
	public userwindow getUser() {
		return user;
	}
	public JTextField getNamee() {
		return namee;
	}
	public JComboBox getBox() {
		return box;
	}
	public JButton getWorldButton() {
		return worldButton;
	}
	public JTextField getFighterName() {
		return fighterName;
	}
   

}
