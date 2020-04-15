package dragonball.view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import dragonball.model.player.Player;

public class NewFighterPanel extends JFrame {
	private JTextField fighterName;
	private JComboBox race;
	private JButton create;
	
	public JTextField getFighterName() {
		return fighterName;
	}

	public JComboBox getRace() {
		return race;
	}

	public JButton getCreate() {
		return create;
	}

	public NewFighterPanel(Player player){
		ImagePanel panel = new ImagePanel("photos.jpg");
		panel.setLayout(new FlowLayout());
		String[] raceList = {"Earthling","Saiyan","Namekian","Frieza","Majin"};
		race = new JComboBox(raceList);
		fighterName = new JTextField(20);
		create = new JButton("Create New Fighter");
		panel.add(fighterName);
		panel.add(race);
		panel.add(create);
		add(panel);
	}
}
