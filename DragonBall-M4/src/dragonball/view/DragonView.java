package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dragonball.model.dragon.Dragon;

public class DragonView extends JFrame {
	  private JButton SenzuBean;
	  private JButton abilityPoints;
	  private JButton SuperAttack;
	  private JButton ultimateAttack;
	  private Dragon dragon;
	public JButton getSenzuBean() {
		return SenzuBean;
	}
	public JButton getAbilityPoints() {
		return abilityPoints;
	}
	public Dragon getDragon() {
		return dragon;
	}
	public JButton getSuperAttack() {
		return SuperAttack;
	}
	public JButton getUltimateAttack() {
		return ultimateAttack;
	}
	 public DragonView(Dragon dragon){
		 this.dragon = dragon;
		 ImagePanel panel = new ImagePanel("dragon.jpg");
		 SenzuBean = new JButton("Senzu Beans");
		 abilityPoints = new JButton("Ability Points");
		 SuperAttack = new JButton("Super Attack");
		 ultimateAttack = new JButton("Ultimate Attack");
		 
		 JPanel xPanel = new JPanel(new GridLayout(1, 0));
		 xPanel.setBackground(Color.BLACK);
		 xPanel.add(SenzuBean);
		 xPanel.add(abilityPoints);
		 xPanel.add(SuperAttack);
		 xPanel.add(ultimateAttack);
		 add(panel,BorderLayout.CENTER);
		 add(xPanel,BorderLayout.SOUTH);
	 }
	 
}
